package com.ftninformatika.jwd.modul2.restoran.repository.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.modul2.restoran.model.Korisnik;
import com.ftninformatika.jwd.modul2.restoran.repository.KorisnikDAO;

@Repository
public class JDBCKorisnikDAO implements KorisnikDAO {

	@Override
	public Korisnik get(String korisnickoIme) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Korisnik> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Korisnik> getAll(String korisnickoIme) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Korisnik korisnik) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Korisnik korisnik) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

}
