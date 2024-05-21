package com.ftninformatika.jwd.modul2.restoran.model;

import java.util.Objects;

public class Kategorija {

	private long id;
	private String naziv;

	public Kategorija() {}

	public Kategorija(long id, String naziv) {
		this.id = id;
		this.naziv = naziv;
	}

	@Override
	public String toString() {
		return "Kategorija [id=" + id + ", naziv=" + naziv + "]";
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
		Kategorija other = (Kategorija) obj;
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

}
