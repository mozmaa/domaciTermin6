package com.ftninformatika.jwd.modul2.restoran.service;

import java.util.Collection;

import com.ftninformatika.jwd.modul2.restoran.dto.ArtikalDTOAddUpdate;
import com.ftninformatika.jwd.modul2.restoran.dto.ArtikalDTOGet;

import jakarta.validation.Valid;

public interface ArtikalService {

	public ArtikalDTOGet get(long id);
	public Collection<ArtikalDTOGet> getAll();
	public Collection<ArtikalDTOGet> getAll(String naziv);
	public void add(@Valid ArtikalDTOAddUpdate artikalDTO);
	public void update(@Valid ArtikalDTOAddUpdate artikalDTO);
	public void delete(long id);
	
}
