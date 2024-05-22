package com.ftninformatika.jwd.modul2.restoran.repository.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.modul2.restoran.model.Artikal;
import com.ftninformatika.jwd.modul2.restoran.repository.ArtikalDAO;

@Repository
public class JDBCArtikalDAO implements ArtikalDAO {

	@Override
	public Artikal get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Artikal> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Artikal> getAll(String naziv) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Artikal artiakl) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Artikal artiakl) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

}
