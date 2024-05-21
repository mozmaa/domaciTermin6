package com.ftninformatika.jwd.modul2.restoran.model;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Restoran {

	private long id;
	private String naziv;
	private LocalDate datumOsnivanja;
	
	private final Set<Kategorija> kategorije = new LinkedHashSet<>();

	public Restoran() {}

	public Restoran(long id, String naziv, LocalDate datumOsnivanja) {
		this.id = id;
		this.naziv = naziv;
		this.datumOsnivanja = datumOsnivanja;
	}

	public Kategorija getKategorija(long kategorijaId) {
		Kategorija kategorija = null;
		for (Kategorija itKategorija: kategorije) {
			if (itKategorija.getId() == kategorijaId) {
				kategorija = itKategorija;
				break;
			}
		}
		return kategorija;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Restoran other = (Restoran) obj;
		return id == other.id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public LocalDate getDatumOsnivanja() {
		return datumOsnivanja;
	}

	public void setDatumOsnivanja(LocalDate datumOsnivanja) {
		this.datumOsnivanja = datumOsnivanja;
	}

	public Set<Kategorija> getKategorije() {
		return Collections.unmodifiableSet(kategorije);
	}

	public void setKategorije(Set<Kategorija> kategorije) {
	    this.kategorije.clear();
	    this.kategorije.addAll(kategorije);
	}

	public void addKategorija(Kategorija kategorija) {
		kategorije.add(kategorija);
	}

}
