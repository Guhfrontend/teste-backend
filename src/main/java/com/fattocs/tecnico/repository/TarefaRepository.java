package com.fattocs.tecnico.repository;

import com.fattocs.tecnico.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    boolean existsByNome(String nome);
}
