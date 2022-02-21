package com.app.minhastarefas.repository;

import com.app.minhastarefas.dominio.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    public List<Tarefa> findByDescricaoLike(String descricao);
}
