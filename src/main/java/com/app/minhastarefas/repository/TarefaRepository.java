package com.app.minhastarefas.repository;

import com.app.minhastarefas.dominio.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
