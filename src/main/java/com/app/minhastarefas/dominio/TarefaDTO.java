package com.app.minhastarefas.dominio;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TarefaDTO {

    private Long id;

    private String descricao;

    private String status;

    private LocalDate data;

    private Long categoriaId;

    private Long usuarioId;

}
