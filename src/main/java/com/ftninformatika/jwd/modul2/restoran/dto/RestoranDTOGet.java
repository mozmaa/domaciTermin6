package com.ftninformatika.jwd.modul2.restoran.dto;


public class RestoranDTOGet extends RestoranDTO {

	private KategorijaDTOGet[] kategorije;

	public KategorijaDTOGet[] getKategorije() {
		return kategorije;
	}

	public void setKategorije(KategorijaDTOGet[] kategorije) {
		this.kategorije = kategorije;
	}
	
}
