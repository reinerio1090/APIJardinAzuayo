package com.jardinazuayo.APIJardinAzuayo.Controller;

import com.jardinazuayo.APIJardinAzuayo.Model.ErrorDetails;
import com.jardinazuayo.APIJardinAzuayo.Model.Incidencia;
import com.jardinazuayo.APIJardinAzuayo.Model.SuccessResponse;
import com.jardinazuayo.APIJardinAzuayo.Service.IncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/incidencias")

public class IncidenciaController {

    @Autowired
    private IncidenciaService incidenciaService;

   /* @GetMapping("/usuario-soporte/{idUsuarioSoporte}")
    public List<Incidencia> getIncidenciasByIdUsuarioSoporte(@PathVariable Long idUsuarioSoporte) {
        return incidenciaService.getIncidenciasByIdUsuarioSoporte(idUsuarioSoporte);
    }*/

    @GetMapping("/usuario-soporte/{idUsuarioSoporte}")
    public ResponseEntity<?> getIncidenciasByIdUsuarioSoporte(@PathVariable Long idUsuarioSoporte) {
        try {
            List<Incidencia> incidencias = incidenciaService.getIncidenciasByIdUsuarioSoporte(idUsuarioSoporte);

            if (!incidencias.isEmpty()) {
                SuccessResponse response = new SuccessResponse("Solicitud exitosa", 200, incidencias);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new SuccessResponse("No se encontraron incidencias", 404, null));
            }
        } catch (Exception e) {
            ErrorDetails error = new ErrorDetails(500, "Error interno del servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/{id}")
    public Incidencia getIncidenciaById(@PathVariable Long id) {
        return incidenciaService.getIncidenciaById(id).orElse(null);
    }

    @PostMapping
    public  ResponseEntity<?> createIncidencia(@RequestBody Map<String, Object> incidenciaData) {
       try {
           Incidencia incidencia = new Incidencia();
           incidencia.setEstado(false); // Estado predeterminado en false
           incidencia.setDescripcion((String) incidenciaData.get("descripcion"));
           Object idSocioObj = incidenciaData.get("idSocio");
           if (idSocioObj instanceof Integer)
               incidencia.setIdSocio(((Integer) idSocioObj).longValue());
           else if (idSocioObj instanceof Long)
               incidencia.setIdSocio((Long) idSocioObj);
           else
               throw new IllegalArgumentException("idSocio debe ser Integer o Long");

           Incidencia creada = incidenciaService.createIncidencia(incidencia);

           SuccessResponse response = new SuccessResponse("Solicitud exitosa", 200, creada);
           return ResponseEntity.ok(response);

       }catch (IllegalArgumentException e) {
            ErrorDetails error = new ErrorDetails(400, "Formato de datos incorrecto");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        } catch (Exception e) {
            ErrorDetails error = new ErrorDetails(500, "Error interno del servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllIncidencias() {
        try {
            List<Incidencia> incidencias = incidenciaService.getAllIncidencias();

            if (!incidencias.isEmpty()) {
                SuccessResponse response = new SuccessResponse("Solicitud exitosa", 200, incidencias);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new SuccessResponse("No se encontraron incidencias", 404, null));
            }
        } catch (Exception e) {
            ErrorDetails error = new ErrorDetails(500, "Error interno del servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/fecha")
    public ResponseEntity<?> getIncidenciasByFechaCreacionBetween(
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin) {
        try {
            LocalDateTime inicio = LocalDate.parse(fechaInicio).atStartOfDay();
            LocalDateTime fin = LocalDate.parse(fechaFin).atTime(LocalTime.MAX);
            List<Incidencia> incidencias = incidenciaService.getIncidenciasByFechaCreacionBetween(inicio, fin);

            if (!incidencias.isEmpty()) {
                SuccessResponse response = new SuccessResponse("Solicitud exitosa", 200, incidencias);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new SuccessResponse("No se encontraron incidencias en el rango de fechas especificado", 404, null));
            }
        } catch (DateTimeParseException e) {
            ErrorDetails error = new ErrorDetails(400, "Formato de datos incorrecto");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping("/estado")
    public ResponseEntity<?> getIncidenciasByEstadoFalse() {
        List<Incidencia> incidencias = incidenciaService.getIncidenciasByEstadoFalse();
        if (!incidencias.isEmpty()) {
            SuccessResponse response = new SuccessResponse("Solicitud exitosa", 200, incidencias);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new SuccessResponse("No se encontraron incidencias con el estado falso", 404, null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateIncidencia(@PathVariable Long id, @RequestBody Incidencia updatedIncidencia) {
        Optional<Incidencia> incidenciaOpt = incidenciaService.updateIncidencia(id, updatedIncidencia);
        if (incidenciaOpt.isPresent()) {
            SuccessResponse response = new SuccessResponse("Incidencia actualizada con éxito", 200, incidenciaOpt.get());
            return ResponseEntity.ok(response);
        } else {
            SuccessResponse response = new SuccessResponse("No se encontró la incidencia con el ID especificado", 404, null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}
