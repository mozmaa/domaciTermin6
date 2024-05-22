package com.ftninformatika.jwd.modul2.restoran.service.impl;

import java.util.Collection;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.ftninformatika.jwd.modul2.restoran.dto.KorisnikDTOAddUpdate;
import com.ftninformatika.jwd.modul2.restoran.dto.KorisnikDTOGet;
import com.ftninformatika.jwd.modul2.restoran.service.KorisnikService;

import jakarta.validation.Valid;

@Primary
@Service
@Validated
public class DataBaseKorisnikService implements KorisnikService {

	@Override
	public KorisnikDTOGet get(String korisnickoIme) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<KorisnikDTOGet> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<KorisnikDTOGet> getAll(String korisnickoIme) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(@Valid KorisnikDTOAddUpdate korisnikDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(@Valid KorisnikDTOAddUpdate korisnikDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

}
