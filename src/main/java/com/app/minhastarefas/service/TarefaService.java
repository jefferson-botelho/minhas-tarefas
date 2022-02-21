package com.app.minhastarefas.service;

import com.app.minhastarefas.dominio.Tarefa;
import com.app.minhastarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Tarefa> obterTodasTarefas() {
        return tarefaRepository.findAll();
    }

    public List<Tarefa> obterTarefaPorDescricao(String descricao) {
        return tarefaRepository.findByDescricaoLike("%" + descricao + "%");
    }

    public Tarefa iniciarTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }
}
