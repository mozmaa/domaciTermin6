package com.ftninformatika.jwd.modul2.restoran.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.modul2.restoran.dto.KategorijaDTOAddUpdate;
import com.ftninformatika.jwd.modul2.restoran.dto.KategorijaDTOGet;

import jakarta.validation.Valid;

@Service
public interface KategorijaService {

	public Collection<KategorijaDTOGet> getAll();
	public KategorijaDTOGet get(long id);
	public Collection<KategorijaDTOGet> getAll(String naziv);
	public void add(@Valid KategorijaDTOAddUpdate kategorijaDTO);
	public void update(@Valid KategorijaDTOAddUpdate kategorijaDTO);
	public void delete(long id);

}
