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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Kategorija kategorija) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Kategorija kategorija) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

}
