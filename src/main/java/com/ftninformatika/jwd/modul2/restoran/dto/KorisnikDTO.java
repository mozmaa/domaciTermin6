package com.ftninformatika.jwd.modul2.restoran.dto;


import java.util.Objects;

import com.ftninformatika.jwd.modul2.restoran.dto.validation.Validation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public abstract class KorisnikDTO {

	@NotBlank(message = "Korisničko ime ne sme biti prazno.", groups = {Validation.Add.class, Validation.Update.class})
	private String korisnickoIme;
	
	@NotBlank(message = "E-mail ne sme biti prazan.", groups = {Validation.Add.class, Validation.Update.class})
	@Email(message = "E-mail nije validan.", groups = {Validation.Add.class, Validation.Update.class})
	private String eMail;
	
	@NotBlank(message = "Pol ne sme biti prazan.", groups = {Validation.Add.class, Validation.Update.class})
	@Pattern(regexp = "muški|ženski", message = "Pol mora biti muški ili ženski.", groups = {Validation.Add.class, Validation.Update.class})
	private String pol;
	
	private boolean administrator;

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getPol() {
		return pol;
	}

	public void setPol(String pol) {
		this.pol = pol;
	}

	public boolean isAdministrator() {
		return administrator;
	}

	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}

	@Override
	public int hashCode() {
		return Objects.hash(korisnickoIme);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KorisnikDTO other = (KorisnikDTO) obj;
		return Objects.equals(korisnickoIme, other.korisnickoIme);
	}
	
	
}
