package com.jardinazuayo.APIJardinAzuayo.Repository;

import com.jardinazuayo.APIJardinAzuayo.Model.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {
    List<Incidencia> findByIdUsuarioSoporte(Long idUsuarioSoporte);
    List<Incidencia> findByFechaCreacionBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
    List<Incidencia> findByEstado(boolean estado);
}
