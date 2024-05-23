package com.ftninformatika.jwd.modul2.restoran.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.ftninformatika.jwd.modul2.restoran.dto.KorisnikDTOAddUpdate;
import com.ftninformatika.jwd.modul2.restoran.dto.KorisnikDTOGet;
import com.ftninformatika.jwd.modul2.restoran.dto.validation.Validation;
import com.ftninformatika.jwd.modul2.restoran.model.Korisnik;
import com.ftninformatika.jwd.modul2.restoran.repository.KorisnikDAO;
import com.ftninformatika.jwd.modul2.restoran.service.KorisnikService;

import jakarta.validation.Valid;

@Primary
@Service
@Validated
public class DataBaseKorisnikService implements KorisnikService {
	
	private final KorisnikDAO korisnikDAO;
	private final ModelMapper mapper = new ModelMapper();
	
	
	public DataBaseKorisnikService(KorisnikDAO korisnikDAO) {
		this.korisnikDAO = korisnikDAO;
	}
	
	private KorisnikDTOGet createDTO(Korisnik korisnik) {
		return mapper.map(korisnik, KorisnikDTOGet.class);
	}

	private Collection<KorisnikDTOGet> createDTO(Collection<Korisnik> korisnici) {
		Collection<KorisnikDTOGet> korisnikDTOs = new ArrayList<>();
		for (Korisnik itKorisnik: korisnici) {
			KorisnikDTOGet korisnikDTO = createDTO(itKorisnik);
			korisnikDTOs.add(korisnikDTO);
		}
		return korisnikDTOs;
	}

	@Override
	public KorisnikDTOGet get(String korisnickoIme) {
		Korisnik korisnik = korisnikDAO.get(korisnickoIme);
		if(korisnik == null)
			throw new NoSuchElementException("Korisnik nije pronadjen!");
		
		return createDTO(korisnik);
	}

	@Override
	public Collection<KorisnikDTOGet> getAll() {
		Collection<Korisnik> korisnici = korisnikDAO.getAll();
		return createDTO(korisnici);
	}

	@Override
	public Collection<KorisnikDTOGet> getAll(String korisnickoIme) {
		Collection<Korisnik> korisnici = korisnikDAO.getAll(korisnickoIme);
		return createDTO(korisnici);
		
	}

	@Override
	@Validated(Validation.Add.class)
	public void add(@Valid KorisnikDTOAddUpdate korisnikDTO) {
		Korisnik korisnik = mapper.map(korisnikDTO, Korisnik.class);
		korisnikDAO.add(korisnik);

	}

	@Override
	@Validated(Validation.Update.class)
	public void update(@Valid KorisnikDTOAddUpdate korisnikDTO) {
		Korisnik korisnik = mapper.map(korisnikDTO, Korisnik.class);
		korisnikDAO.update(korisnik);


	}

	@Override
	public void delete(String korisnickoIme) {
		korisnikDAO.delete(korisnickoIme);

	}

}
