package com.ftninformatika.jwd.modul2.restoran.dto;

import com.ftninformatika.jwd.modul2.restoran.dto.validation.Validation;

import jakarta.validation.constraints.Positive;

public class ArtikalDTOAddUpdate {

	@Positive(message = "Restoran mora biti unet" , groups = {Validation.Add.class , Validation.Update.class})
	private long restoranId;

	public long getRestoranId() {
		return restoranId;
	}

	public void setRestoranId(long restoranId) {
		this.restoranId = restoranId;
	}

	
	
}
