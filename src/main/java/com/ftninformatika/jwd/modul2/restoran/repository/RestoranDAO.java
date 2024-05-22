package com.ftninformatika.jwd.modul2.restoran.repository;

import java.time.LocalDate;
import java.util.Collection;

import com.ftninformatika.jwd.modul2.restoran.model.Restoran;


public interface RestoranDAO {

	public Restoran get(long id);
	public Collection<Restoran> getAll();
	public Collection<Restoran> getAll(long kategorijaId, String naziv, LocalDate datumOsnivanjaOd,
			LocalDate datumOsnivanjaDo);
	public void add(Restoran restoran);
	public void update(Restoran restoran);
	public void delete(long id);
}
