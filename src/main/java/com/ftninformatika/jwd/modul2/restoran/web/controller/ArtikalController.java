
package com.ftninformatika.jwd.modul2.restoran.web.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ftninformatika.jwd.modul2.restoran.model.Artikal;
import com.ftninformatika.jwd.modul2.restoran.model.Restoran;
import com.ftninformatika.jwd.modul2.restoran.service.impl.Dostava;

@Controller
@RequestMapping("/artikli")
public class ArtikalController {

	private Dostava dostava;

	public ArtikalController(Dostava dostava) {
		this.dostava = dostava;
	}

	@GetMapping("") // bez @ResponseBody
	public String getAll(ModelMap request, 
			@RequestParam(required = false, defaultValue = "0") long restoranId) { // ako parametar nije poslat, vrednost će biti "0"; ako je parametar poslat, a vrednost ne može da se konvertuje u traženi tip, nastaće izuzetak
		Collection<Artikal> rezultat = new ArrayList<>();
		for (Artikal itArtikal: dostava.getArtikli().values()) { // pretraga projekcija po film id
			if (restoranId == 0 || itArtikal.getRestoran().getId() == restoranId) {
				rezultat.add(itArtikal);
			}
		}
		request.addAttribute("artikli", rezultat);
		return "artikli";
	}

	@GetMapping("/prikaz") // bez @ResponseBody
	public String get(ModelMap request, 
			@RequestParam long id) {
		request.addAttribute("artikal", dostava.getArtikli().get(id));
		request.addAttribute("restorani", dostava.getRestorani().values());
		return "artikli-prikaz"; // forwarding na template
	}

	@GetMapping("/dodavanje") // bez @ResponseBody
	public String add(ModelMap request) {
		request.addAttribute("restorani", dostava.getRestorani().values());
		return "artikli-dodavanje"; // forwarding na template
	}
	@PostMapping("/dodavanje")
	public String add(
			@RequestParam String naziv, 
			@RequestParam String opis, 
			@RequestParam double cena,
			@RequestParam long restoranId) {
		Restoran restoran = dostava.getRestorani().get(restoranId);
		if (restoran == null) { // da li je restoran pronađen?
			return "redirect:/artikli"; // spreči dodavanje
		}

		long id = dostava.nextArtikalId();
		Artikal artikal = new Artikal(id, naziv, opis, cena, restoran); // povezivanje
		dostava.getArtikli().put(id, artikal);
		
		return "redirect:/artikli";
	}

	@PostMapping("/izmena")
	public String update(@RequestParam long id, 
			@RequestParam String naziv, 
			@RequestParam String opis, 
			@RequestParam double cena,
			@RequestParam long restoranId) {
		Restoran restoran = dostava.getRestorani().get(restoranId);
		if (restoran == null) { // da li je restoran pronađen?
			return "redirect:/artikli"; // spreči izmenu
		}

		Artikal artikal = dostava.getArtikli().get(id);
		artikal.setNaziv(naziv);
		artikal.setOpis(opis);
		artikal.setCena(cena);
		artikal.setRestoran(restoran); // povezivanje

		return "redirect:/artikli";
	}

	@PostMapping("/brisanje")
	public String delete(@RequestParam long id) {
		dostava.getArtikli().remove(id);

		return "redirect:/artikli";
	}

}
