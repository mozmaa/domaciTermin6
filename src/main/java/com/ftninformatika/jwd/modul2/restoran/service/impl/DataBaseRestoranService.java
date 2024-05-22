package com.ftninformatika.jwd.modul2.restoran.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.ftninformatika.jwd.modul2.restoran.dto.RestoranDTOAddUpdate;
import com.ftninformatika.jwd.modul2.restoran.dto.RestoranDTOGet;
import com.ftninformatika.jwd.modul2.restoran.dto.validation.Validation;
import com.ftninformatika.jwd.modul2.restoran.model.Kategorija;
import com.ftninformatika.jwd.modul2.restoran.model.Restoran;
import com.ftninformatika.jwd.modul2.restoran.repository.KategorijaDAO;
import com.ftninformatika.jwd.modul2.restoran.repository.RestoranDAO;
import com.ftninformatika.jwd.modul2.restoran.service.RestoranService;

import jakarta.validation.Valid;

@Primary
@Service
@Validated
public class DataBaseRestoranService implements RestoranService {
	
	private final RestoranDAO restoranDAO;
	private final KategorijaDAO kategorijaDAO;
	private final ModelMapper mapper = new ModelMapper();
	
	public DataBaseRestoranService(RestoranDAO restoranDAO, KategorijaDAO kategorijaDAO) {
		this.restoranDAO = restoranDAO;
		this.kategorijaDAO = kategorijaDAO;
	}
	
	private RestoranDTOGet createDTO(Restoran restoran) {
		return mapper.map(restoran, RestoranDTOGet.class);
	}

	private Collection<RestoranDTOGet> createDTO(Collection<Restoran> filmovi) {
		Collection<RestoranDTOGet> restoranDTOs = new ArrayList<>();
		for (Restoran itRestoran: filmovi) {
			RestoranDTOGet restoranDTO = createDTO(itRestoran);
			restoranDTOs.add(restoranDTO);
		}
		return restoranDTOs;
	}

	@Override
	public RestoranDTOGet get(long id) {
		Restoran restoran = restoranDAO.get(id);
		if(restoran == null)
			throw new NoSuchElementException("Restoran nije pronadjen!");
		
		return createDTO(restoran);
	}

	@Override
	public Collection<RestoranDTOGet> getAll() {
		Collection<Restoran> restorani = restoranDAO.getAll();
		return createDTO(restorani);
	}

	@Override
	public Collection<RestoranDTOGet> getAll(long kategorijaId, String naziv, LocalDate datumOsnivanjaOd,
			LocalDate datumOsnivanjaDo) {
		Collection<Restoran> restorani = restoranDAO.getAll(kategorijaId, naziv, datumOsnivanjaOd, datumOsnivanjaDo);
		return createDTO(restorani);
	}

	@Override
	@Validated(Validation.Add.class)
	public void add(@Valid RestoranDTOAddUpdate restoranDTO) {
		Set<Kategorija> kategorije =  checkKategorija(restoranDTO);
		
		Restoran restoran = mapper.map(restoranDTO, Restoran.class);
		restoran.setKategorije(kategorije);
		
		restoranDAO.add(restoran);

	}

	@Override
	@Validated(Validation.Update.class)
	public void update(@Valid RestoranDTOAddUpdate restoranDTO) {
		Set<Kategorija> kategorije =  checkKategorija(restoranDTO);
		
		Restoran restoran = mapper.map(restoranDTO, Restoran.class);
		restoran.setKategorije(kategorije);
		
		restoranDAO.update(restoran);

	}

	@Override
	public void delete(long id) {
		restoranDAO.delete(id);

	}
	
	@Validated(Validation.class)
	Set<Kategorija> checkKategorija(@Valid RestoranDTOAddUpdate restoranDTO) {
		Set <Kategorija> kategorije = new LinkedHashSet<>();
		for(long itKategorijaId : restoranDTO.getKategorijaIds()) {
			Kategorija kategorija = kategorijaDAO.get(itKategorijaId);
			kategorije.add(kategorija);
		}
		if(kategorije.isEmpty())
			throw new NoSuchElementException("Nije pronadjena nijedna kategorija!");
		
		return kategorije;
	}

}
