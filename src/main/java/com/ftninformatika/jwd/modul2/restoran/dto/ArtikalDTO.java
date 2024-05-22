package com.ftninformatika.jwd.modul2.restoran.dto;

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
	
	@NotBlank(message = "Unos cene ne sme biti prazan.", groups = {Validation.Add.class, Validation.Update.class})
	@Positive(message = "Cena mora biti pozitivan broj" , groups = {Validation.Add.class, Validation.Update.class})
	private double cena;
}
