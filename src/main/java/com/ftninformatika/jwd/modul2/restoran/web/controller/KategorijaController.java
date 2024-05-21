package com.ftninformatika.jwd.modul2.restoran.web.controller;

import java.util.Iterator;
import java.util.Map.Entry;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ftninformatika.jwd.modul2.restoran.model.Artikal;
import com.ftninformatika.jwd.modul2.restoran.model.Kategorija;
import com.ftninformatika.jwd.modul2.restoran.model.Restoran;
import com.ftninformatika.jwd.modul2.restoran.service.impl.Dostava;

@Controller
@RequestMapping("/kategorije")
public class KategorijaController {
  
	private Dostava dostava;

	public KategorijaController(Dostava dostava) {
		this.dostava = dostava;
	}

	@GetMapping("") // bez @ResponseBody
	public String getAll(ModelMap request) {
		request.addAttribute("kategorije", dostava.getKategorije().values());
		return "kategorije";
	}

	@GetMapping("/prikaz") // bez @ResponseBody
	public String get(ModelMap request, 
			@RequestParam long id) {
		request.addAttribute("kategorija", dostava.getKategorije().get(id));
		return "kategorije-prikaz"; // forwarding na template
	}
	@GetMapping("/dodavanje") // bez @ResponseBody
	public String add() {
		return "kategorije-dodavanje"; // forwarding na template
	}

	@PostMapping("/dodavanje")
	public String add(@RequestParam String naziv) {
		long id = dostava.nextKategorijaId();
		Kategorija kategorija = new Kategorija(id, naziv);
		dostava.getKategorije().put(id, kategorija);

		return "redirect:/kategorije";
	}

	@PostMapping("/izmena")
	public String update(@RequestParam long id, 
			@RequestParam String naziv) {
		Kategorija kategorija = dostava.getKategorije().get(id);
		kategorija.setNaziv(naziv);

		return "redirect:/kategorije";
	}

	@PostMapping("/brisanje")
	public String delete(@RequestParam long id) {
		// kaskadno brisanje
		Iterator<Entry<Long, Restoran>> itEntryRestoran = dostava.getRestorani().entrySet().iterator();
		while (itEntryRestoran.hasNext()) {
			Restoran itRestoran = itEntryRestoran.next().getValue();
			for (Kategorija itKategorija: itRestoran.getKategorije()) {
				if (itKategorija.getId() == id) {
					Iterator<Entry<Long, Artikal>> itEntryArtikal = dostava.getArtikli().entrySet().iterator();
					while (itEntryArtikal.hasNext()) {
						Artikal itArtikal = itEntryArtikal.next().getValue();
						if (itArtikal.getRestoran().getId() == itRestoran.getId()) {
							itEntryArtikal.remove();
						}
					}
					itEntryRestoran.remove();
				}
			}
		}
		dostava.getKategorije().remove(id);

		return "redirect:/kategorije";
	}

}
