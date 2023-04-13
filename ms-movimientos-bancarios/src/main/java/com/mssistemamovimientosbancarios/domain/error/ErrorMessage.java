package com.mssistemamovimientosbancarios.domain.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@NoArgsConstructor
public class ErrorMessage {
    private int code;
    @JsonInclude(NON_NULL)
    private String message;
    @JsonInclude(NON_NULL)
    private Object errorDetails;

    public ErrorMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorMessage(int code, Object errorDetails) {
        this.code = code;
        this.errorDetails = errorDetails;
    }
}