package com.ftninformatika.jwd.modul2.restoran.repository;

import java.util.Collection;

import com.ftninformatika.jwd.modul2.restoran.model.Artikal;

public interface ArtikalDAO {

	public Artikal get(long id);
	public Collection<Artikal> getAll();
	public Collection<Artikal> getAll(String naziv);
	public void add(Artikal artiakl);
	public void update(Artikal artiakl);
	public void delete(long id);
}
