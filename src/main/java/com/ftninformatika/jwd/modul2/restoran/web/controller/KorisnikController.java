package com.ftninformatika.jwd.modul2.restoran.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ftninformatika.jwd.modul2.restoran.model.Korisnik;
import com.ftninformatika.jwd.modul2.restoran.service.impl.Dostava;

@Controller
@RequestMapping("/korisnici")
public class KorisnikController {

	private Dostava dostava;

	@Autowired
	public KorisnikController(Dostava dostava) {
		this.dostava = dostava;
	}

	@GetMapping("") // bez @ResponseBody
	public String getAll(ModelMap request) {
		request.addAttribute("korisnici", dostava.getKorisnici().values());
		return "korisnici"; // forwarding na template
	}

	@GetMapping("/prikaz") // bez @ResponseBody
	public String get(ModelMap request,
			@RequestParam String korisnickoIme) {
		request.addAttribute("korisnik", dostava.getKorisnici().get(korisnickoIme));
		return "korisnici-prikaz"; // forwarding na template
	}

	@GetMapping("/dodavanje") // bez @ResponseBody
	public String add() {
		return "korisnici-dodavanje"; // forwarding na template
	}
	
	@PostMapping("/dodavanje")
	public String add(
			@RequestParam String korisnickoIme, 
			@RequestParam String lozinka, 
			@RequestParam String eMail, 
			@RequestParam String pol, 
			@RequestParam(required = false, defaultValue = "false") boolean administrator) {  // ne-check-irani checkbox ne šalje parametar
		Korisnik korisnik = new Korisnik(korisnickoIme, lozinka, eMail, pol, administrator);
		dostava.getKorisnici().put(korisnickoIme, korisnik);

		return "redirect:/korisnici";
	}

	@PostMapping("/izmena")
	public String update(
			@RequestParam String korisnickoIme, 
			@RequestParam String lozinka, 
			@RequestParam String eMail, 
			@RequestParam String pol, 
			@RequestParam(required = false, defaultValue = "false") boolean administrator) { // ne-check-irani checkbox ne šalje parametar	
		Korisnik korisnik = dostava.getKorisnici().get(korisnickoIme);
		korisnik.setLozinka(lozinka);
		korisnik.seteMail(eMail);
		korisnik.setPol(pol);
		korisnik.setAdministrator(administrator);

		return "redirect:/korisnici";
	}

	@PostMapping("/brisanje")
	public String delete(@RequestParam String korisnickoIme) {
		dostava.getKorisnici().remove(korisnickoIme);
		
		return "redirect:/korisnici";
	}
}
