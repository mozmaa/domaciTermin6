package com.ftninformatika.jwd.modul2.restoran.dto;

import java.time.LocalDate;
import java.util.Objects;

import com.ftninformatika.jwd.modul2.restoran.dto.validation.Validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

public abstract class RestoranDTO {

	@Positive(message = "ID mora biti validan.", groups = {Validation.Update.class})
	private long id;

	@NotBlank(message = "Naziv ne sme biti prazan.", groups = {Validation.Add.class, Validation.Update.class})
	private String naziv;

	@NotNull(message = "Datum nije zadat.", groups = {Validation.Add.class, Validation.Update.class})
	@PastOrPresent(message = "Datum osnivanja ne sme biti u budućnosti.", groups = {Validation.Add.class, Validation.Update.class})
	private LocalDate datumOsnivanja;

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
		RestoranDTO other = (RestoranDTO) obj;
		return id == other.id;
	}
}
