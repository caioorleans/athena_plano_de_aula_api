package com.athena.plano_de_aula.api.controller;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;

import com.athena.plano_de_aula.api.dto.PlanoFormulario;
import com.athena.plano_de_aula.api.model.PlanoDeAula;
import com.athena.plano_de_aula.api.model.Plataforma;
import com.athena.plano_de_aula.api.model.RecursoId;
import com.athena.plano_de_aula.api.service.PdfService;
import com.athena.plano_de_aula.api.service.PlanoDeAulaService;

@RestController
@RequestMapping("/planosDeAula")
public class PlanoDeAulaController {

	@Autowired
	private PlanoDeAulaService service;
	
	@Autowired
	private PdfService pdfService;
	
	@Autowired
    ServletContext servletContext;
	
	private final TemplateEngine templateEngine;
	
	public PlanoDeAulaController(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }
	
	@GetMapping
	public List<PlanoDeAula> findAll(){
		return service.findAll();
	}
	
	@PostMapping("salvar")
	public void save(@Valid @RequestBody PlanoFormulario form) {
		service.save(form);
	}
	
	@PutMapping("atualizar")
	public void update(@Valid @RequestBody PlanoFormulario form) {
		service.update(form);
	}
	
	@GetMapping("buscaSimples/{pag}/{titulo}")
	public Page<PlanoDeAula> findByTituloRecurso(@PathVariable Integer pag, @PathVariable String titulo){
		return service.findByRecursos(pag, titulo);
	}
	
	@DeleteMapping("apagar")
	public void update(Integer id) {
		service.delete(id);
	}
	
	@GetMapping("buscaAvançada/{pag}/{filtro}")
	public Page<PlanoDeAula> findByFiltro(@PathVariable Integer pag, @PathVariable String filtro){
		Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),", Pattern.UNICODE_CHARACTER_CLASS);
		Matcher matcher = pattern.matcher(filtro + ",");
		return service.findByFiltro(pag, matcher);
	}
	
	@PutMapping("alterarVisibilidade")
	public void updatePublico(@RequestBody Integer id) {
		service.updatePublico(id);
	}
	
	@GetMapping("procurarPrivados/{pag}")
	public Page<PlanoDeAula> getPrivate(@PathVariable Integer pag){
		return service.findPrivate(pag);
	}
	
	@GetMapping("procurarPorRecursoId/{pag}/{plataforma}/{id}")
	public Page<PlanoDeAula> getByRecursoId(@PathVariable Integer pag,  @PathVariable Plataforma plataforma, @PathVariable Integer id){
		RecursoId rId = new RecursoId();
		rId.setRecursoId(id);
		rId.setRecursoPlataforma(plataforma);
		return service.findByRecurso(rId, pag);
	}
	
	@GetMapping(path = "/downloadPdf/{id}")
    public ResponseEntity<?> getPDF(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        PlanoDeAula plano = service.findById(id);
        String planoHtml = pdfService.toHtml(plano, request, response, servletContext);
        byte[] bytes = pdfService.createPdf(planoHtml);
        return pdfService.download(bytes);
    }
	
	@GetMapping(path = "/verPdf/{id}")
    public ResponseEntity<?> viewPDF(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		PlanoDeAula plano = service.findById(id);
		String planoHtml = pdfService.toHtml(plano, request, response, servletContext);
        byte[] bytes = pdfService.createPdf(planoHtml);
		return pdfService.view(bytes);
	}
}
