package com.ftninformatika.jwd.modul2.restoran.repository.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ftninformatika.jwd.modul2.restoran.model.Kategorija;
import com.ftninformatika.jwd.modul2.restoran.model.Restoran;
import com.ftninformatika.jwd.modul2.restoran.repository.RestoranDAO;

@Repository
public class JDBCRestoranDAO implements RestoranDAO{

	private final JdbcTemplate jdbcTemplate;

	public JDBCRestoranDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	private static class RestoranRowCallbackHandler implements RowCallbackHandler {
		
		private final Map<Long, Restoran> restorani = new LinkedHashMap<>();
		
		@Override
		public void processRow(ResultSet rset) throws SQLException {
			int kolona = 0;
			long rId = rset.getLong(++kolona);
			String rNaziv = rset.getString(++kolona);
			LocalDate rDatumOsnivanja = rset.getDate(++kolona).toLocalDate();
			
			Restoran restoran = restorani.get(rId);
			if(restoran == null) {
				restoran = new Restoran(rId, rNaziv, rDatumOsnivanja);
				restorani.put(rId, restoran);
			}
			
			long kId = rset.getLong(++kolona);
			if(kId != 0) {
				String naziv = rset.getString(++kolona);
				
				Kategorija kategorija = new Kategorija(kId, naziv);
				restoran.addKategorija(kategorija);
			}
		}
		
		public List<Restoran> getRestorani () { return new ArrayList<>(restorani.values());}
	}
	
	@Override
	public Restoran get(long id) {
		RestoranRowCallbackHandler rowCallbackHandler = new RestoranRowCallbackHandler();
		
		String sql = 
				"SELECT r.id, r.naziv, r.datumOsnivanja, k.id, k.naziv FROM restorani r "
				+ "	LEFT JOIN restoranKategorija rk ON r.id = rk.restoranID "
				+ "	LEFT JOIN kategorije k ON k.id = rk.kategorijaID "
				+ "	WHERE r.id = ?";
		jdbcTemplate.query(sql, rowCallbackHandler, id);
		
		List<Restoran> restorani = rowCallbackHandler.getRestorani();
		return !restorani.isEmpty()? restorani.get(0) : null;
	}

	@Override
	public Collection<Restoran> getAll() {
		RestoranRowCallbackHandler rowCallbackHandler = new RestoranRowCallbackHandler();
		
		String sql = 
				"SELECT r.id, r.naziv, r.datumOsnivanja, k.id, k.naziv FROM restorani r "
				+ "	LEFT JOIN restoranKategorija rk ON r.id = rk.restoranID "
				+ "	LEFT JOIN kategorije k ON k.id = rk.kategorijaID "
				+ "	ORDER BY r.id";
		jdbcTemplate.query(sql, rowCallbackHandler);
		
		return rowCallbackHandler.getRestorani();
	}

	@Override
	public Collection<Restoran> getAll(long kategorijaId , String naziv, LocalDate datumOsnivanjaOd, LocalDate datumOsnivanjaDo) {
		RestoranRowCallbackHandler rowCallbackHandler = new RestoranRowCallbackHandler();
		Date dateStampOd = (datumOsnivanjaOd != null)? Date.valueOf(datumOsnivanjaOd) : null;
		Date dateStampDo = (datumOsnivanjaDo != null)? Date.valueOf(datumOsnivanjaDo) : null;
		
		String sql =
				"SELECT r1.rId, r1.rNaziv, r1.rDatumOsnivanja, r1.kId, r1.kNaziv FROM (" +
				"	SELECT r.id AS rId, r.naziv AS rNaziv, r.datumOsnivanja AS rDatumOsnivanja, k.id AS kId, k.naziv AS kNaziv FROM restorani r" +
				"	LEFT JOIN restoranKategorija rk ON r.id = rk.restoranID" +
				"	LEFT JOIN kategorije k ON k.id = rk.kategorijaID " +
				"	WHERE (? IS NULL OR r.naziv LIKE ?)" +
				"	AND (? IS NULL OR r.datumOsnivanja >= ?)" +
				"	AND (? IS NULL OR r.datumOsnivanja <= ?)" +
				") AS r1 JOIN (" +
				"	SELECT r.id AS rId FROM restorani r " +
				"	LEFT JOIN restoranKategorija rk ON r.id = rk.restoranId "+
				"	WHERE ? <= 0 OR rk.kategorijaId = ?" +
				") AS r2 ON r1.rId = r2.rId";
		
		jdbcTemplate.query(sql, rowCallbackHandler,
				naziv, "%" + naziv + "%",
				dateStampOd, dateStampOd,
				dateStampDo, dateStampDo,
				kategorijaId, kategorijaId);
		
		return rowCallbackHandler.getRestorani();
	}

	@Override
	@Transactional
	public void add(Restoran restoran) {
		PreparedStatementCreator preparedStatemntCreator = new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				String sql = "INSERT INTO restorani (naziv, datumOsnivanja) VALUES (?, ?)";
				
				PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				int param = 0;
				preparedStatement.setString(++param, restoran.getNaziv());
				preparedStatement.setDate(++param, Date.valueOf(restoran.getDatumOsnivanja()));
				
				return preparedStatement;
			}
		};
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		
		boolean uspeh = jdbcTemplate.update(preparedStatemntCreator , keyHolder) == 1;
		if(uspeh) {
			String sql = "INSERT INTO restoranKategorija (restoranId, kategorijaId) VALUES (?, ?)";
			for(Kategorija itKategorija : restoran.getKategorije()) {
				jdbcTemplate.update(sql, keyHolder.getKey(), itKategorija.getId());
			}
		}
		
	}

	@Override
	@Transactional
	public void update(Restoran restoran) {
		String sql = "DELETE FROM restoranKategorija WHERE restoranId = ?";
		jdbcTemplate.update(sql , restoran.getId());
		
		sql = "INSERT INTO restoranKategorija (restoranId, kategorijaId) VALUES (? , ?)";
		for(Kategorija itKategorija : restoran.getKategorije()) {
			jdbcTemplate.update(sql, restoran.getId(), itKategorija.getId());
		}
		sql= "UPDATE restorani SET naziv = ?, datumOsnivanja = ? WHERE id = ?";
		jdbcTemplate.update(sql, restoran.getNaziv() , restoran.getDatumOsnivanja(), restoran.getId());
		
	}

	@Override
	@Transactional
	public void delete(long id) {
		String sql = "DELETE FROM restoranKategorija WHERE restoranId = ?";
		jdbcTemplate.update(sql, id);
		
		sql = "DELETE FROM restorani WHERE id = ?";
		jdbcTemplate.update(sql, id);
		
	}

}
