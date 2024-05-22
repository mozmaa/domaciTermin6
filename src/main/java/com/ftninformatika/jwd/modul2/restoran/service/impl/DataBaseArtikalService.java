package com.ftninformatika.jwd.modul2.restoran.service.impl;

import java.util.Collection;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.ftninformatika.jwd.modul2.restoran.dto.ArtikalDTOAddUpdate;
import com.ftninformatika.jwd.modul2.restoran.dto.ArtikalDTOGet;
import com.ftninformatika.jwd.modul2.restoran.service.ArtikalService;

import jakarta.validation.Valid;

@Primary
@Service
@Validated
public class DataBaseArtikalService implements ArtikalService {

	@Override
	public ArtikalDTOGet get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ArtikalDTOGet> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ArtikalDTOGet> getAll(String naziv) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(@Valid ArtikalDTOAddUpdate artikalDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(@Valid ArtikalDTOAddUpdate artikalDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

}
