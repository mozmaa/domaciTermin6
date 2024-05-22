package com.ftninformatika.jwd.modul2.restoran.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.ftninformatika.jwd.modul2.restoran.dto.KategorijaDTOAddUpdate;
import com.ftninformatika.jwd.modul2.restoran.dto.KategorijaDTOGet;
import com.ftninformatika.jwd.modul2.restoran.dto.validation.Validation;
import com.ftninformatika.jwd.modul2.restoran.model.Kategorija;
import com.ftninformatika.jwd.modul2.restoran.repository.KategorijaDAO;
import com.ftninformatika.jwd.modul2.restoran.service.KategorijaService;

import jakarta.validation.Valid;

@Primary
@Service
@Validated
public class DataBaseKategorijaService implements KategorijaService {
	
	private final KategorijaDAO kategorijaDAO;
	private final ModelMapper mapper = new ModelMapper();
	
	public DataBaseKategorijaService(KategorijaDAO kategorijaDAO){
		this.kategorijaDAO = kategorijaDAO;
	}
	
	private KategorijaDTOGet createDTO(Kategorija kategorija) {
		return mapper.map(kategorija, KategorijaDTOGet.class);
	}

	private Collection<KategorijaDTOGet> createDTO(Collection<Kategorija> kategorije) {
		Collection<KategorijaDTOGet> kategorijaDTOs = new ArrayList<>();
		for (Kategorija itKategorija: kategorije) {
			KategorijaDTOGet restoranDTO = createDTO(itKategorija);
			kategorijaDTOs.add(restoranDTO);
		}
		return kategorijaDTOs;
	}

	@Override
	public Collection<KategorijaDTOGet> getAll() {
		Collection<Kategorija> kategorije = kategorijaDAO.getAll();
		return createDTO(kategorije);
	}

	@Override
	public KategorijaDTOGet get(long id) {
		Kategorija kategorija = kategorijaDAO.get(id);
		if(kategorija == null)
			throw new NoSuchElementException("Kategroija nije pronadjena!");
		return createDTO(kategorija);
	}

	@Override
	public Collection<KategorijaDTOGet> getAll(String naziv) {
		Collection<Kategorija> kategorije = kategorijaDAO.getAll(naziv);
		return createDTO(kategorije);
	}

	@Override
	@Validated(Validation.Add.class)
	public void add(@Valid KategorijaDTOAddUpdate kategorijaDTO) {
		Kategorija kategorija = mapper.map(kategorijaDTO, Kategorija.class);
		
		kategorijaDAO.add(kategorija);
	}

	@Override
	@Validated(Validation.Update.class)
	public void update(@Valid KategorijaDTOAddUpdate kategorijaDTO) {
		Kategorija kategorija = mapper.map(kategorijaDTO, Kategorija.class);
		
		kategorijaDAO.update(kategorija);
	}

	@Override
	public void delete(long id) {
		kategorijaDAO.delete(id);
		
	}

}
