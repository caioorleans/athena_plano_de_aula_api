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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;

import com.athena.plano_de_aula.api.dto.PlanoDTO;
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
	
	@PreAuthorize("permitAll()")
	@GetMapping("buscarPorId/{id}")
	public PlanoDeAula findById(@PathVariable Integer id) {
		return service.findById(id);
	}
	
	@PreAuthorize("permitAll()")
	@PostMapping("salvar")
	public void save(@Valid @RequestBody PlanoFormulario form) {
		service.save(form);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("atualizar")
	public void update(@Valid @RequestBody PlanoFormulario form) {
		service.update(form);
	}
	
	@PreAuthorize("permitAll()")
	@GetMapping("buscaSimples/{pag}/{titulo}")
	public Page<PlanoDTO> findByTituloRecurso(@PathVariable Integer pag, @PathVariable String titulo){
		Page<PlanoDTO> planos = service.findByRecursos(pag, titulo).map(x -> new PlanoDTO(x));
		return planos;
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("apagar")
	public void update(Integer id) {
		service.delete(id);
	}
	
	@PreAuthorize("permitAll()")
	@GetMapping("buscaAvancada/{pag}/{filtro}")
	public Page<PlanoDTO> findByFiltro(@PathVariable Integer pag, @PathVariable String filtro){
		Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),", Pattern.UNICODE_CHARACTER_CLASS);
		Matcher matcher = pattern.matcher(filtro + ",");
		Page<PlanoDTO> planos = service.findByFiltro(pag, matcher).map(x -> new PlanoDTO(x));
		return planos;
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("alterarVisibilidade")
	public void updatePublico(@RequestBody Integer id) {
		service.updatePublico(id);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("procurarPrivados/{pag}")
	public Page<PlanoDTO> getPrivate(@PathVariable Integer pag){
		Page<PlanoDTO> planos = service.findPrivate(pag).map(x -> new PlanoDTO(x));
		return planos;
	}
	
	@PreAuthorize("permitAll()")
	@GetMapping("procurarPorRecursoId/{pag}/{plataforma}/{id}")
	public Page<PlanoDTO> getByRecursoId(@PathVariable Integer pag,  @PathVariable Plataforma plataforma, @PathVariable Integer id){
		RecursoId rId = new RecursoId();
		rId.setRecursoId(id);
		rId.setRecursoPlataforma(plataforma);
		Page<PlanoDTO> planos = service.findByRecurso(rId, pag).map(x -> new PlanoDTO(x));
		return planos;
	}
	
	@PreAuthorize("permitAll()")
	@GetMapping(path = "/downloadPdf/{id}")
    public ResponseEntity<?> getPDF(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        PlanoDeAula plano = service.findById(id);
        String planoHtml = pdfService.toHtml(plano, request, response, servletContext);
        byte[] bytes = pdfService.createPdf(planoHtml);
        return pdfService.download(bytes);
    }
	
	@PreAuthorize("permitAll()")
	@GetMapping(path = "/verPdf/{id}")
    public ResponseEntity<?> viewPDF(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		PlanoDeAula plano = service.findById(id);
		String planoHtml = pdfService.toHtml(plano, request, response, servletContext);
        byte[] bytes = pdfService.createPdf(planoHtml);
		return pdfService.view(bytes);
	}
}
