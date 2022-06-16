package com.athena.plano_de_aula.api.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
import org.thymeleaf.context.WebContext;

import com.athena.plano_de_aula.api.dto.PlanoFormulario;
import com.athena.plano_de_aula.api.model.PlanoDeAula;
import com.athena.plano_de_aula.api.service.PlanoDeAulaService;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;

@RestController
@RequestMapping("/planosDeAula")
public class PlanoDeAulaController {

	@Autowired
	private PlanoDeAulaService service;
	
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
	
	@GetMapping("procurarPorTituloRecurso/{titulo}")
	public List<PlanoDeAula> findByTituloRecurso(@PathVariable String titulo){
		return service.findByRecursos(titulo);
	}
	
	@DeleteMapping("apagar")
	public void update(Integer id) {
		service.delete(id);
	}
	
	@RequestMapping(path = "/pdf/{id}")
    public ResponseEntity<?> getPDF(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws IOException {

        /* Do Business Logic*/

        PlanoDeAula plano = service.findById(id);

        /* Create HTML using Thymeleaf template Engine */

        WebContext context = new WebContext(request, response, servletContext);
        context.setVariable("plano", plano);
        String orderHtml = templateEngine.process("plano", context);

        /* Setup Source and target I/O streams */

        ByteArrayOutputStream target = new ByteArrayOutputStream();
        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri("http://localhost:8080");
        /* Call convert method */
        HtmlConverter.convertToPdf(orderHtml, target, converterProperties);

        /* extract output as bytes */
        byte[] bytes = target.toByteArray();


        /* Send the response as downloadable PDF */

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=order.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);

    }
}
