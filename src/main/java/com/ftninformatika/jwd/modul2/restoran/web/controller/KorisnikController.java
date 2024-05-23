package com.ftninformatika.jwd.modul2.restoran.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ftninformatika.jwd.modul2.restoran.dto.KorisnikDTOAddUpdate;
import com.ftninformatika.jwd.modul2.restoran.service.KorisnikService;

@Controller
@RequestMapping("/korisnici")
public class KorisnikController {

	private final KorisnikService korisnikService;

	@Autowired
	public KorisnikController(KorisnikService korisnikService) {
		this.korisnikService = korisnikService;
	}

	@GetMapping("") // bez @ResponseBody
	public String getAll(ModelMap request,
			@RequestParam(required = false, defaultValue = "") String korisnickoIme) {
		request.addAttribute("korisnici", korisnikService.getAll(korisnickoIme));
		return "korisnici"; // forwarding na template
	}

	@GetMapping("/prikaz") // bez @ResponseBody
	public String get(ModelMap request,
			@RequestParam String korisnickoIme) {
		request.addAttribute("korisnik" , korisnikService.get(korisnickoIme));
		return "korisnici-prikaz"; // forwarding na template
	}

	@GetMapping("/dodavanje") // bez @ResponseBody
	public String add() {
		return "korisnici-dodavanje"; // forwarding na template
	}
	
	@PostMapping("/dodavanje")
	public String add(@ModelAttribute KorisnikDTOAddUpdate korisnikDTO) {  // ne-check-irani checkbox ne šalje parametar
		korisnikService.add(korisnikDTO);
		return "redirect:/korisnici";
	}

	@PostMapping("/izmena")
	public String update(@ModelAttribute KorisnikDTOAddUpdate korisnikDTO) { // ne-check-irani checkbox ne šalje parametar	
		korisnikService.update(korisnikDTO);

		return "redirect:/korisnici";
	}

	@PostMapping("/brisanje")
	public String delete(@RequestParam String korisnickoIme) {
		korisnikService.delete(korisnickoIme);
		
		return "redirect:/korisnici";
	}
}
