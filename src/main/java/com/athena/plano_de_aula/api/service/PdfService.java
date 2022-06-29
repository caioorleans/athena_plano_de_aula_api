package com.athena.plano_de_aula.api.service;

import java.io.ByteArrayOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import com.athena.plano_de_aula.api.model.PlanoDeAula;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;

@Service
public class PdfService {
	
	private final TemplateEngine templateEngine;
	
	public PdfService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }
	
	public byte[] createPdf(String orderHtml) {	
		ByteArrayOutputStream target = new ByteArrayOutputStream();
        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri("http://localhost:8080");
        /* Call convert method */
        HtmlConverter.convertToPdf(orderHtml, target, converterProperties);

        /* extract output as bytes */
        return target.toByteArray();
	}
	
	public String toHtml(PlanoDeAula plano, HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) {
		WebContext context = new WebContext(request, response, servletContext);
        context.setVariable("plano", plano);
        return templateEngine.process("plano", context);
	}
	
	
	
	public ResponseEntity download(byte[] bytes) {
		return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=plano_de_aula.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);
	}
	
	public ResponseEntity view(byte[] bytes) {
		return ResponseEntity.ok()
	            .contentType(MediaType.APPLICATION_PDF)
	            .body(bytes);
	}
}
