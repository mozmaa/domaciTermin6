package com.ftninformatika.jwd.modul2.restoran.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.ftninformatika.jwd.modul2.restoran.dto.KategorijaDTOGet;
import com.ftninformatika.jwd.modul2.restoran.model.Kategorija;
import com.ftninformatika.jwd.modul2.restoran.service.KategorijaService;

@Service
@Validated
public class InMemoryKategorijaService implements KategorijaService {
	
	private final Dostava dostava;
	private final ModelMapper mapper = new ModelMapper();
	
	InMemoryKategorijaService(Dostava dostava){
		this.dostava = dostava;
	}
	
	private KategorijaDTOGet createDTO(Kategorija kategorija) {
		return mapper.map(kategorija, KategorijaDTOGet.class);
	}

	private Collection<KategorijaDTOGet> createDTO(Collection<Kategorija> filmovi) {
		Collection<KategorijaDTOGet> kategorijaDTOs = new ArrayList<>();
		for (Kategorija itkategorija : filmovi) {
			KategorijaDTOGet kategorijaDTO = createDTO(itkategorija);
			kategorijaDTOs.add(kategorijaDTO);
		}
		return kategorijaDTOs;
	}
	
	@Override
	public KategorijaDTOGet get(long id) {
		Kategorija kategorija = dostava.getKategorije().get(id);
		if(kategorija == null)
			throw new NoSuchElementException("Kategorija nije pronadjena");
		
		return createDTO(kategorija);
	}

	@Override
	public Collection<KategorijaDTOGet> getAll() {
		Collection<Kategorija> kategorije = dostava.getKategorije().values();
		return createDTO(kategorije);
	}

}
