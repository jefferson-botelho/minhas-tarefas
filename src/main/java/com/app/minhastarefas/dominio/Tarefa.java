package com.app.minhastarefas.dominio;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "tarefas")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "desc_tarefa", nullable = false, length = 100)
    private String descricao;

    @Enumerated(EnumType.STRING)
    private TarefaStatus tarefaStatus;

    private LocalDate data;

    @ManyToOne
    @JoinColumn(nullable = false)
    private TarefaCategoria categoria;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;
}
