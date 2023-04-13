package com.mssistemamovimientosbancarios.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenericBooleanResponse {
	private Boolean booleanValue;
	private Integer errorCode = null;
    private String message;
    private Object data = null;
}