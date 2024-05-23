package com.ftninformatika.jwd.modul2.restoran.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.modul2.restoran.model.Artikal;
import com.ftninformatika.jwd.modul2.restoran.model.Restoran;
import com.ftninformatika.jwd.modul2.restoran.repository.ArtikalDAO;

@Repository
public class JDBCArtikalDAO implements ArtikalDAO {
	
	private final JdbcTemplate jdbcTemplate;
	
	public JDBCArtikalDAO (JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public static class ArtikalRowMapper implements RowMapper<Artikal> {

		@Override
		public Artikal mapRow(ResultSet rset, int rowNum) throws SQLException {
			int kolona = 0;
			long aId = rset.getLong(++kolona);
			String aNaziv = rset.getString(++kolona);
			String aOpis = rset.getString(++kolona);
			double aCena = rset.getDouble(++kolona);
			
			Artikal artikal = new Artikal(aId, aNaziv, aOpis, aCena, null);
			
			long rId = rset.getLong(++kolona);
			if(rId != 0) {
				String rNaziv = rset.getString(++kolona);
				LocalDate rDatumOsnivanja = rset.getDate(++kolona).toLocalDate();
				
				Restoran restoran = new Restoran(rId, rNaziv, rDatumOsnivanja);
				artikal.setRestoran(restoran);
			}
			return artikal;
		}
		
	}

	@Override
	public Artikal get(long id) {
		String sql = "SELECT a.id, a.naziv, a.opis, a.cena, r.id, r.naziv, r.datumOsnivanja FROM artikli a" +
					 "	LEFT JOIN restorani r ON a.restoranId = r.id WHERE a.id = ?";
		
		List<Artikal> rezultat = jdbcTemplate.query(sql, new ArtikalRowMapper(), id);
		return !rezultat.isEmpty()? rezultat.get(0) : null;
	}

	@Override
	public Collection<Artikal> getAll() {
		String sql = "SELECT a.id, a.naziv, a.opis, a.cena, r.id, r.naziv, r.datumOsnivanja FROM artikli a" +
					 "	LEFT JOIN restorani r ON a.restoranId = r.id";
		
		return jdbcTemplate.query(sql, new ArtikalRowMapper());
	}

	@Override
	public Collection<Artikal> getAll(String naziv) {
		String sql = "SELECT a.id, a.naziv, a.opis, a.cena, r.id, r.naziv, r.datumOsnivanja FROM artikli a" +
				 "	LEFT JOIN restorani r ON a.restoranId = r.id WHERE ? IS NULL or naziv LIKE ?";
		
		return jdbcTemplate.query(sql, new ArtikalRowMapper(), naziv, "%" + naziv + "%");
	}

	@Override
	public void add(Artikal artikal) {
		String sql = "INSERT INTO artikli (naziv, opis, cena, restoranId) VALUES (?, ?, ?, ?)";

		jdbcTemplate.update(sql, artikal.getNaziv(),
								 artikal.getOpis(),
								 artikal.getCena(),
								 artikal.getRestoran().getId());
	}

	@Override
	public void update(Artikal artikal) {
		String sql = "UPDATE artikli SET naziv = ?, opis = ?, cena = ?, restoranId = ? WHERE id = ?";
		
		jdbcTemplate.update(sql, artikal.getNaziv(),
								 artikal.getOpis(),
								 artikal.getCena(),
								 artikal.getRestoran().getId(),
								 artikal.getId());
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM artikli WHERE id = ?";
		
		jdbcTemplate.update(sql, id);
	}

}
