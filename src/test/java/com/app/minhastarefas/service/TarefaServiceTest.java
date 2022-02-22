package com.app.minhastarefas.service;

import com.app.minhastarefas.dominio.Tarefa;
import com.app.minhastarefas.dominio.TarefaStatus;
import com.app.minhastarefas.exception.TarefaStatusException;
import com.app.minhastarefas.repository.TarefaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TarefaServiceTest {

    @Mock
    private TarefaRepository repositorio;

    @InjectMocks
    private TarefaService service;

    @Test
    void deveListarTarefas() {

        List<Tarefa> tarefas = new ArrayList<>();

        Tarefa tarefa1 = new Tarefa();
        tarefa1.setId(1L);
        tarefa1.setDescricao("Teste 01");
        tarefa1.setTarefaStatus(TarefaStatus.ABERTA);

        Tarefa tarefa2 = new Tarefa();
        tarefa2.setId(2L);
        tarefa2.setDescricao("Teste 02");
        tarefa2.setTarefaStatus(TarefaStatus.ABERTA);

        tarefas.add(tarefa1);
        tarefas.add(tarefa2);

        Mockito.when(repositorio.findAll())
                .thenReturn(tarefas);

        List<Tarefa> retornaTarefas = service.obterTodasTarefas();

        Assertions.assertEquals(tarefas, retornaTarefas);
    }

    @Test
    void deveRetornarTarefaPorId() {

        Long idTeste = 1L;

        Tarefa tarefa = new Tarefa();
        tarefa.setId(idTeste);
        tarefa.setDescricao("Teste 01");
        tarefa.setTarefaStatus(TarefaStatus.ABERTA);

        Mockito.when(repositorio.findById(idTeste))
                .thenReturn(Optional.of(tarefa));

        Tarefa tarefaService = service.obterTarefaPorId(idTeste);

        Assertions.assertEquals(tarefa, tarefaService);
    }

    @Test
    void deveSalvarTarefa() {

        Long idTeste = 1L;

        Tarefa tarefa = new Tarefa();
        tarefa.setId(idTeste);
        tarefa.setDescricao("Teste 01");
        tarefa.setTarefaStatus(TarefaStatus.ABERTA);

        Mockito.when(repositorio.save(tarefa))
                .thenReturn(tarefa);

        Tarefa salvarTarefa = service.salvarTarefa(tarefa);

        Assertions.assertEquals(tarefa, salvarTarefa);
    }

    @Test
    void deveIniciarTarefa() {

        Long idTeste = 1L;

        Tarefa tarefa = new Tarefa();
        tarefa.setId(idTeste);
        tarefa.setDescricao("Teste 01");
        tarefa.setTarefaStatus(TarefaStatus.ABERTA);

        Mockito.when(repositorio.findById(idTeste))
                .thenReturn(Optional.of(tarefa));

        tarefa = service.iniciarTarefaPorId(idTeste);

        Assertions.assertEquals(TarefaStatus.EM_ANDAMENTO, tarefa.getTarefaStatus());
    }

    @Test
    void naoDeveConcluirTarefaCancelada() {

        Long idTeste = 1L;

        Tarefa tarefa = new Tarefa();
        tarefa.setId(idTeste);
        tarefa.setDescricao("Teste 01");
        tarefa.setTarefaStatus(TarefaStatus.CANCELADA);

        Mockito.when(repositorio.findById(idTeste))
                .thenReturn(Optional.of(tarefa));

        Assertions.assertThrows(TarefaStatusException.class,
                () -> service.concluirTarefaPorId(idTeste));
    }

    @Test
    void naoDeveIniciarTarefaConcluida() {

        Long idTeste = 1L;

        Tarefa tarefa = new Tarefa();
        tarefa.setId(idTeste);
        tarefa.setDescricao("Teste 01");
        tarefa.setTarefaStatus(TarefaStatus.CONCLUIDA);

        Mockito.when(repositorio.findById(idTeste))
                .thenReturn(Optional.of(tarefa));

        Assertions.assertThrows(TarefaStatusException.class,
                () -> service.iniciarTarefaPorId(idTeste));
    }

}
