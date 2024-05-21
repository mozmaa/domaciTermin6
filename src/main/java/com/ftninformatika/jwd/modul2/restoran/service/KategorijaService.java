package com.ftninformatika.jwd.modul2.restoran.service;

import java.util.Collection;

import com.ftninformatika.jwd.modul2.restoran.dto.KategorijaDTOGet;

public interface KategorijaService {

	public Collection<KategorijaDTOGet> getAll();
	public KategorijaDTOGet get(long id);

}
