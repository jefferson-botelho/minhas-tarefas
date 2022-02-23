package com.app.minhastarefas.dominio;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TarefaRequestDTO {

    private Integer id;

    private String descricao;

    private LocalDate data;

    private Integer categoriaId;

    private Integer usuarioId;

}
