package com.ftninformatika.jwd.modul2.restoran.service.impl;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul2.restoran.model.Artikal;
import com.ftninformatika.jwd.modul2.restoran.model.Kategorija;
import com.ftninformatika.jwd.modul2.restoran.model.Korisnik;
import com.ftninformatika.jwd.modul2.restoran.model.Restoran;

@Component
public class Dostava {

	private final Map<Long, Kategorija> kategorije = new LinkedHashMap<>();
	private final Map<Long, Restoran> restorani = new LinkedHashMap<>();
	private final Map<Long, Artikal> artikli = new LinkedHashMap<>();
	private final Map<String, Korisnik> korisnici = new LinkedHashMap<>();
	
	private long maxKategorijaId = 0;
	private long maxRestoranId = 0;
	private long maxArtikalId = 0;

	public Dostava() {
		// kreiranje kategorija 
		kategorije.put(1L, new Kategorija(1L, "Burgeri"));
		kategorije.put(2L, new Kategorija(2L, "Sendviči"));
		kategorije.put(3L, new Kategorija(3L, "Suši"));
		kategorije.put(4L, new Kategorija(4L, "Onigiri"));
		kategorije.put(5L, new Kategorija(5L, "Burito"));

		// kreiranje restorana
		restorani.put(1L, new Restoran(1L, "Restoran 1", LocalDate.parse("2001-01-01")));
		restorani.put(2L, new Restoran(2L, "Restoran 2", LocalDate.parse("2012-02-01")));
		restorani.put(3L, new Restoran(3L, "Restoran 3", LocalDate.parse("2023-03-01")));
		
		// kreiranje artikala
		artikli.put(1L, new Artikal(1L, "Dupli burger", "Burger meso 120 grama, toljeni sir, burger majonez, heinz kečap, ajsberg, kiseli krastavac", 650, restorani.get(1L)));
		artikli.put(2L, new Artikal(2L, "Pileći burger", "Pileći burger, tost sir, pančeta, burger majonez, ajsberg, paradajz", 550, restorani.get(1L)));
		artikli.put(3L, new Artikal(3L, "Indeks sendvič", "Praška šunka, sir, šampinjoni", 320, restorani.get(1L)));
		artikli.put(4L, new Artikal(4L, "Vratolomija sendvič", "Suvi vrat, sir, jaja", 290, restorani.get(1L)));	
		artikli.put(5L, new Artikal(5L, "Crispy specijal", "Hrskave sushi rolnice, pohovane u tempuri. Nori alga, pirinač, pohovana rolnica sa dodatkom krastavca, krem sira, lososa i japanskog nitsume sosa", 920, restorani.get(2L)));
		artikli.put(6L, new Artikal(6L, "Samurai roll", "8 komada, nori alga, pirinač, losos, tuna, avokado, obložena breniranim lososom i tunom,unagi sos, samuraj sos, hrskavi crunch krompir", 990, restorani.get(2L)));
		artikli.put(7L, new Artikal(7L, "Onigiri sa tunom", "pirinač, tuna, crni susam, japanski majonez i nori alge", 510, restorani.get(2L)));
		artikli.put(8L, new Artikal(8L, "Onigiri sa lososom", "pirinač, losos, crni susam, japanski majonez i nori alge", 670, restorani.get(2L)));
		artikli.put(9L, new Artikal(9L, "Achiote piletina burito", "Meksički pirinač, Crni pasulj, Mokahete Salsa, Pico de gallo, Kukuruz salsa, Marinirani Krastavac, Kačkavalj, Crema Fresca", 560, restorani.get(3L)));
		artikli.put(10L, new Artikal(10L, "Chorizo burito", "Meksički pirinač, Pinto pasulj, Mokahete Salsa, Pico de gallo, Kukuruz Salsa, Kačkavalj, Marinirani krastavac, Marinirani crveni luk, Chipotle aioli", 610, restorani.get(3L)));
		artikli.put(11L, new Artikal(11L, "Pečene pečurke burito", "Beli pirinač, Crni pasulj, Pečene pečurke, Pico de gallo, Kukuruz salsa, Marinirani krastavac, Mokahete salsa, Mix zelenih salata, Krema Freska", 480, restorani.get(3L)));

		// povezivanje restorana i kategorija
		restorani.get(1L).addKategorija(kategorije.get(1L));
		restorani.get(1L).addKategorija(kategorije.get(2L));
		restorani.get(2L).addKategorija(kategorije.get(3L));
		restorani.get(2L).addKategorija(kategorije.get(4L));
		restorani.get(3L).addKategorija(kategorije.get(5L));

		maxKategorijaId = 5L;
		maxRestoranId = 3L;
		maxArtikalId = 11L;
		
		// kreiranje korisnika
		korisnici.put("a", new Korisnik("a", "a", "a@a.com", "muški", true));
		korisnici.put("b", new Korisnik("b", "b", "b@b.com", "ženski", false));
		korisnici.put("c", new Korisnik("c", "c", "c@c.com", "muški", false));
	}

	public Map<Long, Kategorija> getKategorije() {
		return kategorije;
	}

	public Map<Long, Restoran> getRestorani() {
		return restorani;
	}

	public Map<Long, Artikal> getArtikli() {
		return artikli;
	}

	public Map<String, Korisnik> getKorisnici() {
		return korisnici;
	}

	public long nextKategorijaId() {
		maxKategorijaId++;
		return maxKategorijaId;
	}

	public long nextRestoranId() {
		maxRestoranId++;
		return maxRestoranId;
	}

	public long nextArtikalId() {
		maxArtikalId++;
		return maxArtikalId;
	}

}
