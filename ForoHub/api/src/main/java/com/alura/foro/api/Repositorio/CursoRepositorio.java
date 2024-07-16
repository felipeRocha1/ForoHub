package com.alura.foro.api.Repositorio;

import com.alura.foro.api.Modelo.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepositorio extends JpaRepository<Curso, Long> {
}
