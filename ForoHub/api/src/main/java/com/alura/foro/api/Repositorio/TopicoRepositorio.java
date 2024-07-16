package com.alura.foro.api.Repositorio;

import com.alura.foro.api.Modelo.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepositorio extends JpaRepository<Topico, Long> {
}
