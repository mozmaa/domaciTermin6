package com.ftninformatika.jwd.modul2.restoran.model;

import java.util.Objects;

public class Artikal {

	private long id;
	private String naziv;
	private String opis;
	private double cena;

	private Restoran restoran;
	
	public Artikal() {}

	public Artikal(long id, String naziv, String opis, double cena, Restoran restoran) {
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.cena = cena;
		this.restoran = restoran;
	}

	@Override
	public String toString() {
		return "Artikal [id=" + id + ", naziv=" + naziv + ", opis=" + opis + ", cena=" + cena + ", restoran=" + restoran
				+ "]";
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
		Artikal other = (Artikal) obj;
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

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public Restoran getRestoran() {
		return restoran;
	}

	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}

}
