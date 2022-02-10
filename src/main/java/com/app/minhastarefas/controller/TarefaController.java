package com.app.minhastarefas.controller;

import com.app.minhastarefas.dominio.Tarefa;
import com.app.minhastarefas.repository.TarefaRepository;
import com.app.minhastarefas.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    @Autowired
    private TarefaService servico;

    @GetMapping()
    public List<Tarefa> todasTarefas(){
        return servico.obterTodasTarefas();
    }

    @PostMapping
    public void salvarTarefa(@RequestBody Tarefa tarefa) {
        servico.iniciarTarefa(tarefa);
    }
}
