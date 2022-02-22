package com.app.minhastarefas.exception;

public class TarefaStatusException extends RuntimeException {

    private static final long serialVersionUID = 1607426296998184744L;

    public TarefaStatusException() {
    }

    public TarefaStatusException(String message) {
        super(message);
    }

    public TarefaStatusException(String message, Throwable cause) {
        super(message, cause);
    }

    public TarefaStatusException(Throwable cause) {
        super(cause);
    }

    public TarefaStatusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
