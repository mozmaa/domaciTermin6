package com.ftninformatika.jwd.modul2.restoran.dto;

import java.util.Objects;

public abstract class KategorijaDTO {

	private long id;
	private String naziv;
	
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
		KategorijaDTO other = (KategorijaDTO) obj;
		return id == other.id;
	}
	
	
}
