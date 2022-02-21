package com.app.minhastarefas.controller;

import com.app.minhastarefas.dominio.Tarefa;
import com.app.minhastarefas.repository.TarefaRepository;
import com.app.minhastarefas.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    @Autowired
    private TarefaService servico;

    @GetMapping()
    public List<Tarefa> todasTarefas(@RequestParam Map<String, String> parametros){
        if(parametros.isEmpty()){
            return servico.obterTodasTarefas();
        }

        String descricao = parametros.get("descricao");
        return servico.obterTarefaPorDescricao(descricao);

    }


    @PostMapping
    public void salvarTarefa(@Valid @RequestBody Tarefa tarefa) {
        servico.iniciarTarefa(tarefa);
    }
}
