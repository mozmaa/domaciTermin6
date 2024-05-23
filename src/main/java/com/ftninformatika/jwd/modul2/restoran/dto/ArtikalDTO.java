package com.ftninformatika.jwd.modul2.restoran.dto;

import java.util.Objects;

import com.ftninformatika.jwd.modul2.restoran.dto.validation.Validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ArtikalDTO {

	@Positive(message = "ID mora biti validan.", groups = {Validation.Update.class})
	private long id;
	
	@NotBlank(message = "Naziv ne sme biti prazan.", groups = {Validation.Add.class, Validation.Update.class})
	private String naziv;
	
	@NotNull(message = "Opis nije unet.", groups = {Validation.Add.class, Validation.Update.class})
	private String opis;
	
	@NotNull(message = "Unos cene ne sme biti prazan.", groups = {Validation.Add.class, Validation.Update.class}) //koristi @NotNull za brojeve
	@Positive(message = "Cena mora biti pozitivan broj" , groups = {Validation.Add.class, Validation.Update.class})
	private double cena;

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
		ArtikalDTO other = (ArtikalDTO) obj;
		return id == other.id;
	}
	
	
}
