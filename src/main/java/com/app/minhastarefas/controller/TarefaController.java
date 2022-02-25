package com.app.minhastarefas.controller;

import com.app.minhastarefas.controller.assembler.TarefaModelAssembler;
import com.app.minhastarefas.dominio.Tarefa;
import com.app.minhastarefas.dominio.TarefaDTO;
import com.app.minhastarefas.dominio.TarefaRequestDTO;
import com.app.minhastarefas.service.TarefaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private TarefaModelAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<TarefaDTO>> todasTarefas(@RequestParam Map<String, String> parametros){
       List<Tarefa> tarefas = new ArrayList<>();

        if (parametros.isEmpty()) {
            tarefas = servico.obterTodasTarefas();
        } else {
            String descricao = parametros.get("descricao");
            tarefas = servico.obterTarefaPorDescricao(descricao);
        }

        List<EntityModel<TarefaDTO>> tarefasDTO = tarefas.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(tarefasDTO,
                linkTo(methodOn(TarefaController.class).todasTarefas(new HashMap<>())).withSelfRel());
    }

    @PutMapping("/{id}/iniciar")
    public EntityModel<TarefaDTO> iniciarTarefa(@PathVariable Long id) {
        Tarefa tarefa = servico.iniciarTarefaPorId(id);
        return assembler.toModel(tarefa);
    }

    @PutMapping("/{id}/concluir")
    public EntityModel<TarefaDTO> concluirTarefa(@PathVariable Long id) {
        Tarefa tarefa = servico.concluirTarefaPorId(id);
        return assembler.toModel(tarefa);
    }

    @PutMapping("/{id}/cancelar")
    public EntityModel<TarefaDTO> cancelarTarefa(@PathVariable Long id) {
        Tarefa tarefa = servico.cancelarTarefaPorId(id);
        return assembler.toModel(tarefa);
    }

    @GetMapping("/{id}")
    public EntityModel<TarefaDTO> retornarTarefaPorId(@PathVariable Long id) {
        Tarefa tarefa = servico.obterTarefaPorId(id);
        return assembler.toModel(tarefa);
    }

    @PostMapping
    public ResponseEntity<EntityModel<TarefaDTO>> salvarTarefa(@Valid @RequestBody TarefaRequestDTO tarefaReq) {
        Tarefa tarefa = mapper.map(tarefaReq, Tarefa.class);
        Tarefa tarefaSalva = servico.salvarTarefa(tarefa);

        EntityModel<TarefaDTO> tarefaModel = assembler.toModel(tarefaSalva);

        return ResponseEntity
                .created(tarefaModel.getRequiredLink(IanaLinkRelations.SELF)
                        .toUri()).body(tarefaModel);
    }

    @DeleteMapping("/{id}")
    public void deletarTarefaPorId(@PathVariable Long id) {
        servico.deletarTarefa(id);
    }
}
