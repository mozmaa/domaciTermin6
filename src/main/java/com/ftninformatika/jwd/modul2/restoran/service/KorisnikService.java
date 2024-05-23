package com.ftninformatika.jwd.modul2.restoran.service;

import java.util.Collection;

import com.ftninformatika.jwd.modul2.restoran.dto.KorisnikDTOAddUpdate;
import com.ftninformatika.jwd.modul2.restoran.dto.KorisnikDTOGet;

import jakarta.validation.Valid;

public interface KorisnikService {

	public KorisnikDTOGet get(String korisnickoIme);
	public Collection<KorisnikDTOGet> getAll();
	public Collection<KorisnikDTOGet> getAll(String korisnickoIme);
	public void add(@Valid KorisnikDTOAddUpdate korisnikDTO);
	public void update(@Valid KorisnikDTOAddUpdate korisnikDTO);
	public void delete(String korisnickoIme);
}
