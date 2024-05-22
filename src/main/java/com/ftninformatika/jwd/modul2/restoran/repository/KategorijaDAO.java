package com.ftninformatika.jwd.modul2.restoran.repository;

import java.util.Collection;

import com.ftninformatika.jwd.modul2.restoran.model.Kategorija;

public interface KategorijaDAO {

	public Kategorija get(long id);
	public Collection<Kategorija> getAll();
	public Collection<Kategorija> getAll(String naziv);
	public void add(Kategorija kategorija);
	public void update(Kategorija kategorija);
	public void delete(long id);
}
