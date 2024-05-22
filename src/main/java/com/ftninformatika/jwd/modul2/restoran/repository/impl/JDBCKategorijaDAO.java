package com.ftninformatika.jwd.modul2.restoran.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.modul2.restoran.model.Kategorija;
import com.ftninformatika.jwd.modul2.restoran.repository.KategorijaDAO;

@Repository
public class JDBCKategorijaDAO implements KategorijaDAO {
	
	private final JdbcTemplate jdbcTemplate;

	public JDBCKategorijaDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	private static class KategorijaRowMapper implements RowMapper<Kategorija> {

		@Override
		public Kategorija mapRow(ResultSet rset, int rowNum) throws SQLException {
			int kolona = 0;
			long id = rset.getLong(++kolona);
			String naziv = rset.getString(++kolona);
			
			Kategorija kategorija = new Kategorija(id, naziv);
			return kategorija;
		}
		
	}

	@Override
	public Kategorija get(long id) {
		String sql = "SELECT id, naziv FROM kategorije WHERE id = ?";
		
		List<Kategorija> rezultat = jdbcTemplate.query(sql, new KategorijaRowMapper(), id);
		return !rezultat.isEmpty()? rezultat.get(0) : null;
	}

	@Override
	public Collection<Kategorija> getAll() {
		String sql = "SELECT id, naziv FROM kategorije";
		
		return jdbcTemplate.query(sql,new KategorijaRowMapper());
	}

	@Override
	public Collection<Kategorija> getAll(String naziv) {
		String sql = "SELECT id, naziv FROM kategorije WHERE ? IS NULL OR naziv LIKE ?";
		
		return jdbcTemplate.query(sql, new KategorijaRowMapper(),
				naziv,
				"%" + naziv + "%");
	}

	@Override
	public void add(Kategorija kategorija) {
		String sql = "INSERT INTO kategorije (naziv) VALUES (?)";
		
		jdbcTemplate.update(sql, kategorija.getNaziv());
	}

	@Override
	public void update(Kategorija kategorija) {
		String sql = "UPDATE kategorije SET naziv = ? WHERE id = ?";
		jdbcTemplate.update(sql, kategorija.getNaziv(), kategorija.getId());

	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM restoranKategorija WHERE kategorijaId = ?";
		jdbcTemplate.update(sql, id);
		
		sql = "DELETE FROM kategorije WHERE id = ?";
		jdbcTemplate.update(sql, id);

	}

}
