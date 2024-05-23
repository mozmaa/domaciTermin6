
package com.ftninformatika.jwd.modul2.restoran.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ftninformatika.jwd.modul2.restoran.dto.ArtikalDTOAddUpdate;
import com.ftninformatika.jwd.modul2.restoran.service.ArtikalService;
import com.ftninformatika.jwd.modul2.restoran.service.RestoranService;

@Controller
@RequestMapping("/artikli")
public class ArtikalController {

	private final ArtikalService artikalService;
	private final RestoranService restoranService;

	public ArtikalController(ArtikalService artikalService, RestoranService restoranService) {
		this.artikalService = artikalService;
		this.restoranService = restoranService;
	}

	@GetMapping("") // bez @ResponseBody
	public String getAll(ModelMap request, 
			@RequestParam(required = false, defaultValue = "") String naziv) { // ako parametar nije poslat, vrednost će biti "0"; ako je parametar poslat, a vrednost ne može da se konvertuje u traženi tip, nastaće izuzetak
		request.addAttribute("artikli", artikalService.getAll());
		request.addAttribute("restorani" , restoranService.getAll());
		return "artikli";
	}

	@GetMapping("/prikaz") // bez @ResponseBody
	public String get(ModelMap request, 
			@RequestParam long id) {
		request.addAttribute("artikal", artikalService.get(id));
		request.addAttribute("restorani" , restoranService.getAll());
		return "artikli-prikaz"; // forwarding na template
	}

	@GetMapping("/dodavanje") // bez @ResponseBody
	public String add(ModelMap request) {
		request.addAttribute("restorani" , restoranService.getAll());
		return "artikli-dodavanje"; // forwarding na template
	}
	
	@PostMapping("/dodavanje")
	public String add(@ModelAttribute ArtikalDTOAddUpdate artikalDTO) {
		artikalService.add(artikalDTO);
		return "redirect:/artikli"; // spreči dodavanje
		}

	@PostMapping("/izmena")
	public String update(@ModelAttribute ArtikalDTOAddUpdate artikalDTO) {
		artikalService.update(artikalDTO);
		return "redirect:/artikli"; // spreči izmenu
		}

	@PostMapping("/brisanje")
	public String delete(@RequestParam long id) {
		artikalService.delete(id);

		return "redirect:/artikli";
	}

}
