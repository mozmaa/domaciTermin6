package com.ftninformatika.jwd.modul2.restoran.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.ftninformatika.jwd.modul2.restoran.dto.ArtikalDTOAddUpdate;
import com.ftninformatika.jwd.modul2.restoran.dto.ArtikalDTOGet;
import com.ftninformatika.jwd.modul2.restoran.dto.validation.Validation;
import com.ftninformatika.jwd.modul2.restoran.model.Artikal;
import com.ftninformatika.jwd.modul2.restoran.model.Restoran;
import com.ftninformatika.jwd.modul2.restoran.repository.ArtikalDAO;
import com.ftninformatika.jwd.modul2.restoran.repository.RestoranDAO;
import com.ftninformatika.jwd.modul2.restoran.service.ArtikalService;

import jakarta.validation.Valid;

@Primary
@Service
@Validated
public class DataBaseArtikalService implements ArtikalService {
	
	private final ArtikalDAO artikalDAO;
	private final RestoranDAO restoranDAO;
	private final ModelMapper mapper = new ModelMapper();
	
	public DataBaseArtikalService (ArtikalDAO artikalDAO, RestoranDAO restoranDAO) {
		this.artikalDAO = artikalDAO;
		this.restoranDAO = restoranDAO;
	}
	
	private ArtikalDTOGet createDTO (Artikal artikal) {
		return mapper.map(artikal, ArtikalDTOGet.class);
	}
	
	private Collection<ArtikalDTOGet> createDTOs (Collection<Artikal> artikli){
		Collection<ArtikalDTOGet> artikalDTOs = new ArrayList<>();
		for(Artikal itArtikal : artikli) {
			ArtikalDTOGet artikalDTO = createDTO(itArtikal);
			artikalDTOs.add(artikalDTO);
		}
		return artikalDTOs;
	}

	@Override
	public ArtikalDTOGet get(long id) {
		Artikal artikal = artikalDAO.get(id);
		if(artikal == null) {
			throw new NoSuchElementException("Artikal nije pronadjen");
		}
		return createDTO(artikal);
	}

	@Override
	public Collection<ArtikalDTOGet> getAll() {
		Collection<Artikal> artikli = artikalDAO.getAll();
		return createDTOs(artikli);
	}

	@Override
	public Collection<ArtikalDTOGet> getAll(String naziv) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Validated(Validation.Add.class)
	public void add(@Valid ArtikalDTOAddUpdate artikalDTO) {
		Restoran restoran = restoranDAO.get(artikalDTO.getRestoranId());
		if(restoran == null) 
			throw new NoSuchElementException("Restoran nije pronadjen");
		
		Artikal artikal = mapper.map(artikalDTO, Artikal.class);
		artikal.setRestoran(restoran);
		
		artikalDAO.add(artikal);
	}

	@Override
	@Validated(Validation.Update.class)
	public void update(@Valid ArtikalDTOAddUpdate artikalDTO) {
		Restoran restoran = restoranDAO.get(artikalDTO.getRestoranId());
		if(restoran == null) 
			throw new NoSuchElementException("Restoran nije pronadjen");
		
		Artikal artikal = mapper.map(artikalDTO, Artikal.class);
		artikal.setRestoran(restoran);
		
		artikalDAO.update(artikal);
	}

	@Override
	public void delete(long id) {
		artikalDAO.delete(id);

	}

}
