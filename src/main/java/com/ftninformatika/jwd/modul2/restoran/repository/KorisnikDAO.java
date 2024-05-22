package com.ftninformatika.jwd.modul2.restoran.repository;

import java.util.Collection;

import com.ftninformatika.jwd.modul2.restoran.model.Korisnik;

public interface KorisnikDAO {

	public Korisnik get(String korisnickoIme);
	public Collection<Korisnik> getAll();
	public Collection<Korisnik> getAll(String korisnickoIme);
	public void add(Korisnik korisnik);
	public void update(Korisnik korisnik);
	public void delete(long id);
}
