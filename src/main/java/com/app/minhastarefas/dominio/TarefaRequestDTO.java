package com.app.minhastarefas.dominio;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class TarefaRequestDTO {

    private Integer id;

    @NotBlank(message = "{tarefa.descricao.not-blank}")
    @Size(min = 5, max = 150, message = "{tarefa.descricao.size}")
    private String descricao;

    @FutureOrPresent(message = "{tarefa.descricao.future-or-present}.")
    private LocalDate data;

    @NotNull(message = "{tarefa.categoria.not-null}")
    @Min(value = 1, message = "{tarefa.categoria.min}")
    private Integer categoriaId;

    @NotNull(message = "{tarefa.usuario.not-null}")
    @Min(value = 1, message = "{tarefa.usuario.min}")
    private Integer usuarioId;

}
