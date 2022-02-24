package com.app.minhastarefas.controller;

import com.app.minhastarefas.dominio.Tarefa;
import com.app.minhastarefas.dominio.TarefaDTO;
import com.app.minhastarefas.dominio.TarefaRequestDTO;
import com.app.minhastarefas.service.TarefaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    @Autowired
    private TarefaService servico;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public List<TarefaDTO> todasTarefas(@RequestParam Map<String, String> parametros){
       List<Tarefa> tarefas = new ArrayList<>();

        if (parametros.isEmpty()) {
            tarefas = servico.obterTodasTarefas();
        } else {
            String descricao = parametros.get("descricao");
            tarefas =  servico.obterTarefaPorDescricao(descricao);
        }

        List<TarefaDTO> tarefasDTO = tarefas.stream()
                .map(tarefa -> mapper.map(tarefa, TarefaDTO.class))
                .collect(Collectors.toList());

        return tarefasDTO;
    }

    @PutMapping("/{id}/iniciar")
    public void iniciarTarefa(@PathVariable Long id) {
        servico.iniciarTarefaPorId(id);
    }

    @PutMapping("/{id}/concluir")
    public void concluirTarefa(@PathVariable Long id) {
        servico.concluirTarefaPorId(id);
    }

    @PutMapping("/{id}/cancelar")
    public void cancelarTarefa(@PathVariable Long id) {
        servico.cancelarTarefaPorId(id);
    }

    @GetMapping("/{id}")
    public EntityModel<TarefaDTO> retornarTarefaPorId(@PathVariable Long id) {
        Tarefa tarefa = servico.obterTarefaPorId(id);
        TarefaDTO tarefaDTO = mapper.map(tarefa, TarefaDTO.class);

        EntityModel<TarefaDTO> tarefaModel = EntityModel.of(tarefaDTO,
                linkTo(methodOn(TarefaController.class).retornarTarefaPorId(id)).withSelfRel(),
                linkTo(methodOn(TarefaController.class).todasTarefas(new HashMap<>())).withRel("tarefas"));

        return tarefaModel;
    }

    @PostMapping
    public TarefaDTO salvarTarefa(@Valid @RequestBody TarefaRequestDTO tarefaReq) {
        Tarefa tarefa = mapper.map(tarefaReq, Tarefa.class);

        return mapper.map(servico.salvarTarefa(tarefa), TarefaDTO.class);
    }

    @DeleteMapping("/{id}")
    public void deletarTarefaPorId(@PathVariable Long id) {
        servico.deletarTarefa(id);
    }
}
