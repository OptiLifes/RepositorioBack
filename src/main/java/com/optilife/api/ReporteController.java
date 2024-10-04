package com.optilife.api;

import com.optilife.service.impl.ReportePDFServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    private ReportePDFServiceImpl reportePDFService;

    @GetMapping("/descargar")
    public ResponseEntity<byte[]> descargarReportePDF() {
        ByteArrayInputStream reporteStream = reportePDFService.generarReportePDF();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=reporte.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                .body(reporteStream.readAllBytes());
    }
}