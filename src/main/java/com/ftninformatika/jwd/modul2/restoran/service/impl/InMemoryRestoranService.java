package com.ftninformatika.jwd.modul2.restoran.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice.Local;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.ftninformatika.jwd.modul2.restoran.dto.RestoranDTOAddUpdate;
import com.ftninformatika.jwd.modul2.restoran.dto.RestoranDTOGet;
import com.ftninformatika.jwd.modul2.restoran.dto.validation.Validation;
import com.ftninformatika.jwd.modul2.restoran.model.Artikal;
import com.ftninformatika.jwd.modul2.restoran.model.Kategorija;
import com.ftninformatika.jwd.modul2.restoran.model.Restoran;
import com.ftninformatika.jwd.modul2.restoran.service.RestoranService;

import jakarta.validation.Valid;

@Service
@Validated
public class InMemoryRestoranService implements RestoranService {

	private final Dostava dostava;
	private final ModelMapper mapper = new ModelMapper();
	
	InMemoryRestoranService(Dostava dostava){
		this.dostava = dostava;
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
		Restoran restoran = dostava.getRestorani().get(id);
		if(restoran == null)
			throw new NoSuchElementException("Restoran nije pronadjen");
		
		return createDTO(restoran);
	}

	@Override
	public Collection<RestoranDTOGet> getAll() {
		Collection<Restoran> restorani = dostava.getRestorani().values();
		return createDTO(restorani);
	}

//	@Override
//	public Collection<RestoranDTOGet> getAll(long  kategorijaId, String naziv, LocalDate datumOsnivanjaOd, LocalDate datumOsnivanjaDo ) {
//		Collection<Restoran> restorani = dostava.getRestorani().values();
//		boolean contains;
//		
//		List<Restoran> rezultat = new ArrayList<Restoran>();
//		for(Restoran itRestoran : restorani) {
//			contains = false;
//			for(Kategorija itKategorija : itRestoran.getKategorije()) {
//				for(int i = 0; i < kategorijaId.length; i++) {
//					if(itKategorija.getId() == kategorijaId[i]) {
//						contains = true;
//					}
//				}
//			}
//			if ((naziv == null || itRestoran.getNaziv().toLowerCase().contains(naziv.toLowerCase())) && 
//					(datumOsnivanjaOd == null || itRestoran.getDatumOsnivanja().compareTo(datumOsnivanjaOd) >= 0) && 
//					(datumOsnivanjaDo == null || itRestoran.getDatumOsnivanja().compareTo(datumOsnivanjaDo) <= 0)&&
//					(kategorijaId.length == 0 || contains)){
//				
//					rezultat.add(itRestoran);
//			}
//		}
//		return createDTO(rezultat);
//	}

	@Override
	@Validated(Validation.Add.class)
	public void add(@Valid RestoranDTOAddUpdate restoranDTO) {
		Set<Kategorija> kategorije = new LinkedHashSet<Kategorija>();
		for(long itKategorijaId : restoranDTO.getKategorijaIds()) {
			Kategorija itKategorija = dostava.getKategorije().get(itKategorijaId);
			kategorije.add(itKategorija);
		}
		if(kategorije.isEmpty()) {
			throw new NoSuchElementException("Nije pronađena nijedna kategorija!"); 
		}
		
		long id = dostava.nextRestoranId();
		Restoran restoran = mapper.map(restoranDTO, Restoran.class);
		restoran.setId(id);
		restoran.setKategorije(kategorije);
		dostava.getRestorani().put(id, restoran);
		
	}

	@Override
	@Validated(Validation.Update.class)
	public void update(@Valid RestoranDTOAddUpdate restoranDTO) {
		Set<Kategorija> kategorije = new LinkedHashSet<Kategorija>();
		for(long itKategorijaId : restoranDTO.getKategorijaIds()) {
			Kategorija itKategorija = dostava.getKategorije().get(itKategorijaId);
			kategorije.add(itKategorija);
		}
		if(kategorije.isEmpty()) {
			throw new NoSuchElementException("Nije pronađena nijedna kategorija!"); 
		}
		
		Restoran restoran = dostava.getRestorani().get(restoranDTO.getId());
		mapper.map(restoranDTO, restoran);
		restoran.setKategorije(kategorije);
		
	}

	@Override
	public void delete(long id) {
		Iterator<Entry<Long, Artikal>> itEntryArtikal = dostava.getArtikli().entrySet().iterator();
		while(itEntryArtikal.hasNext()) {
			Artikal itArtikal = itEntryArtikal.next().getValue();
			if(itArtikal.getRestoran().getId() == id) {
				itEntryArtikal.remove();
			}
		}
		dostava.getRestorani().remove(id);
	}

	@Override
	public Collection<RestoranDTOGet> getAll(long kategorijaId, String naziv, LocalDate datumOsnivanjaOd,
			LocalDate datumOsnivanjaDo) {
		// TODO Auto-generated method stub
		return null;
	}

}
