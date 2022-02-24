package com.app.minhastarefas.repository;

import com.app.minhastarefas.dominio.Tarefa;
import com.app.minhastarefas.dominio.TarefaCategoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaCategoriaRepository extends JpaRepository<TarefaCategoria, Long> {

    public List<TarefaCategoria> findByCategoriaLike(String nome);
}
