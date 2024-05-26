package com.jardinazuayo.APIJardinAzuayo.Service;

import com.jardinazuayo.APIJardinAzuayo.Model.Incidencia;
import com.jardinazuayo.APIJardinAzuayo.Repository.IncidenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class IncidenciaService {

    @Autowired
    private IncidenciaRepository incidenciaRepository;

    public Incidencia createIncidencia(Incidencia incidencia) {
        return incidenciaRepository.save(incidencia);
    }

    public List<Incidencia> getIncidenciasByIdUsuarioSoporte(Long idUsuarioSoporte) {
        return incidenciaRepository.findByIdUsuarioSoporte(idUsuarioSoporte);
    }

    public Optional<Incidencia> getIncidenciaById(Long id) {
        return incidenciaRepository.findById(id);
    }


    public List<Incidencia> getAllIncidencias() {
        return incidenciaRepository.findAll();
    }

    public List<Incidencia> getIncidenciasByFechaCreacionBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return incidenciaRepository.findByFechaCreacionBetween(fechaInicio, fechaFin);
    }

    public List<Incidencia> getIncidenciasByEstadoFalse() {
        return incidenciaRepository.findByEstado(false);
    }

    public Optional<Incidencia> updateIncidencia(Long id, Incidencia updatedIncidencia) {
        Optional<Incidencia> incidenciaOpt = incidenciaRepository.findById(id);
        if (incidenciaOpt.isPresent()) {
            Incidencia incidencia = incidenciaOpt.get();
            incidencia.setObservacion(updatedIncidencia.getObservacion());
            incidencia.setIdUsuarioSoporte(updatedIncidencia.getIdUsuarioSoporte());
            incidencia.setEstado(true); // Actualiza el estado automáticamente a true
            incidencia.setFechaActualizacion(LocalDateTime.now()); // Establece la fecha de actualización a la fecha actual
            incidenciaRepository.save(incidencia);
            return Optional.of(incidencia);
        }
        return Optional.empty();
    }
}
