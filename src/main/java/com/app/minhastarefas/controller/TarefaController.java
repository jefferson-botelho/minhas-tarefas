package com.app.minhastarefas.controller;

import com.app.minhastarefas.dominio.Tarefa;
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

    @GetMapping("/{id}")
    public Tarefa retornarTarefaPorId(@PathVariable Long id) {
        return servico.obterTarefaPorId(id);
    }

    @PostMapping
    public void salvarTarefa(@Valid @RequestBody Tarefa tarefa) {
        servico.salvarTarefa(tarefa);
    }

    @DeleteMapping("/{id}")
    public void deletarTarefaPorId(@PathVariable Long id) {
        servico.deletarTarefa(id);
    }
}
