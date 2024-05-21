package com.ftninformatika.jwd.modul2.restoran.dto;


import com.ftninformatika.jwd.modul2.restoran.dto.validation.Validation;

import jakarta.validation.constraints.NotEmpty;

public class RestoranDTOAddUpdate extends RestoranDTO {

	@NotEmpty(message = "Bar jedna kategorija mora biti zadata.", groups = {Validation.Add.class, Validation.Update.class})
	private long[] kategorijaIds;

	public long[] getKategorijaIds() {
		return kategorijaIds;
	}

	public void setKategorijaIds(long[] kategorijaIds) {
		this.kategorijaIds = kategorijaIds;
	}
}
