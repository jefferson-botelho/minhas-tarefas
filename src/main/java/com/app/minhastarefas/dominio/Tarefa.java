package com.app.minhastarefas.dominio;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "tarefas")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "{tarefa.descricao.not-blank}")
    @Size(min = 5, max = 150, message = "{tarefa.descricao.size}")
    @Column(name = "desc_tarefa", nullable = false, length = 150)
    private String descricao;

    @Enumerated(EnumType.STRING)
    private TarefaStatus tarefaStatus;

    @FutureOrPresent(message = "{tarefa.descricao.future-or-present}.")
    private LocalDate data;

    @ManyToOne
    @JoinColumn(nullable = false)
    private TarefaCategoria categoria;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;
}
