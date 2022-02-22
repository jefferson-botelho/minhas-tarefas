package com.app.minhastarefas.service;

import com.app.minhastarefas.dominio.Tarefa;
import com.app.minhastarefas.dominio.TarefaStatus;
import com.app.minhastarefas.exception.TarefaStatusException;
import com.app.minhastarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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

    public Tarefa obterTarefaPorId(Long id) {
        return tarefaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public Tarefa salvarTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public void deletarTarefa(Long id) {
        tarefaRepository.deleteById(id);
    }

    public Tarefa iniciarTarefaPorId(Long id) {
        Tarefa tarefa = this.obterTarefaPorId(id);
        if(!tarefa.getTarefaStatus().equals(TarefaStatus.ABERTA)) {
            throw new TarefaStatusException();
        }
        tarefa.setTarefaStatus(TarefaStatus.EM_ANDAMENTO);

        tarefaRepository.save(tarefa);
        return tarefa;
    }

    public Tarefa concluirTarefaPorId(Long id) {
        Tarefa tarefa = this.obterTarefaPorId(id);
        if(tarefa.getTarefaStatus().equals(TarefaStatus.CANCELADA)) {
            throw new TarefaStatusException();
        }
        tarefa.setTarefaStatus(TarefaStatus.CONCLUIDA);

        tarefaRepository.save(tarefa);
        return tarefa;
    }
}
