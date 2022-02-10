package com.app.minhastarefas.repository;

import com.app.minhastarefas.dominio.TarefaCategoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaCategoriaRepository extends JpaRepository<TarefaCategoria, Long> {
}
