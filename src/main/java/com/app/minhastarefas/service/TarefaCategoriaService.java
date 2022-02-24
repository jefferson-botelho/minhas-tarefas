package com.app.minhastarefas.service;

import com.app.minhastarefas.dominio.Tarefa;
import com.app.minhastarefas.dominio.TarefaCategoria;
import com.app.minhastarefas.repository.TarefaCategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TarefaCategoriaService {

    @Autowired
    private TarefaCategoriaRepository repository;

    public List<TarefaCategoria> listarTodasCategorias() {
        return repository.findAll();
    }

    public TarefaCategoria obterCategoriaPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public List<TarefaCategoria> obterCategoriaPorNome(String categoria) {
        return repository.findByCategoriaLike("%" + categoria + "%");
    }

    public TarefaCategoria criarCategoria(TarefaCategoria tarefa) {
        repository.save(tarefa);

        return tarefa;
    }

    public void deletarCategoriaPorId(Long id) {
        TarefaCategoria tarefaCategoria = obterCategoriaPorId(id);
        repository.delete(tarefaCategoria);
    }

}
