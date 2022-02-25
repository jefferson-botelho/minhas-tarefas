package com.app.minhastarefas.controller.assembler;

import com.app.minhastarefas.controller.TarefaCategoriaController;
import com.app.minhastarefas.controller.TarefaController;
import com.app.minhastarefas.controller.UsuarioController;
import com.app.minhastarefas.dominio.Tarefa;
import com.app.minhastarefas.dominio.TarefaDTO;
import com.app.minhastarefas.dominio.TarefaStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.HashMap;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TarefaModelAssembler implements RepresentationModelAssembler<Tarefa, EntityModel<TarefaDTO>> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public EntityModel<TarefaDTO> toModel(Tarefa tarefa) {
        TarefaDTO tarefaDTO = mapper.map(tarefa, TarefaDTO.class);

        EntityModel<TarefaDTO> tarefaModel = EntityModel.of(tarefaDTO,
                linkTo(methodOn(TarefaController.class).retornarTarefaPorId(tarefaDTO.getId())).withSelfRel(),
                linkTo(methodOn(TarefaController.class).todasTarefas(new HashMap<>())).withRel("tarefas"),
                linkTo(methodOn(TarefaCategoriaController.class).retornarCategoriaPorId(tarefaDTO.getCategoriaId())).withRel("categoria"),
                linkTo(methodOn(UsuarioController.class).obterUsuarioPorId(tarefaDTO.getUsuarioId())).withRel("usuario"));

        if (tarefa.getTarefaStatus().equals(TarefaStatus.EM_ANDAMENTO)) {
            tarefaModel.add(
                    linkTo(methodOn(TarefaController.class).concluirTarefa(tarefaDTO.getId())).withRel("concluir"),
                    linkTo(methodOn(TarefaController.class).cancelarTarefa(tarefaDTO.getId())).withRel("cancelar")
            );
        }

        if (tarefa.getTarefaStatus().equals(TarefaStatus.ABERTA)) {
            tarefaModel.add(
                    linkTo(methodOn(TarefaController.class).iniciarTarefa(tarefaDTO.getId())).withRel("iniciar")
            );
        }

        return tarefaModel;
    }
}
