package com.mssistemamovimientosbancarios.exception.handler;


import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.mssistemamovimientosbancarios.domain.error.ErrorMessage;
import com.mssistemamovimientosbancarios.domain.error.FieldError;
import com.mssistemamovimientosbancarios.exception.DeleteException;
import com.mssistemamovimientosbancarios.exception.InsertException;
import com.mssistemamovimientosbancarios.exception.TransactionNotFoundException;
import com.mssistemamovimientosbancarios.util.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ErrorHandler {
   
    //Metodo que controla las validaciones para los parámetros en el json body
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ErrorMessage onWebExchangeBindException(MethodArgumentNotValidException e) {
        log.error(Constants.LOG_PATTERN_MSG, e.getMessage());
        List<FieldError> fieldErrorList = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> {
                    org.springframework.validation.FieldError fieldError = (org.springframework.validation.FieldError) error;
                    return new FieldError(fieldError.getField(), fieldError.getDefaultMessage());
                }).collect(Collectors.toList());
        return new ErrorMessage(Constants.ILLEGAL_ARGUMENT_CODE, fieldErrorList);
    }

    //Metodo que controla las validaciones para los parámetros en query params
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ErrorMessage onConstraintValidationException(ConstraintViolationException e) {
        log.error(Constants.LOG_PATTERN_MSG, e.getMessage());
        List<FieldError> fieldErrorList = e.getConstraintViolations()
                .stream()
                .map(violation -> {
                    String[] pathSplit = violation.getPropertyPath().toString().split("\\.");
                    String fieldName = pathSplit[pathSplit.length - 1];
                    return new FieldError(fieldName, violation.getMessage());
                }).collect(Collectors.toList());
        return new ErrorMessage(Constants.ILLEGAL_ARGUMENT_CODE, fieldErrorList);
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ErrorMessage missingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error(Constants.LOG_PATTERN_MSG, e.getMessage());
        return new ErrorMessage(Constants.ILLEGAL_ARGUMENT_CODE, String.format(Constants.REQUIRED_FIELD_PATTERN_MSG, e.getParameterName()));
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ErrorMessage uniqueConstraintViolation(DataIntegrityViolationException e) {
        log.error(Constants.LOG_PATTERN_MSG, e.getMessage());
        return new ErrorMessage(Constants.INTERNAL_BD_ERROR_CODE, Constants.UNIQUE_CONSTRAINT_VIOLATION_BD_ERROR);
    }

    @ExceptionHandler({DataAccessException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ErrorMessage badSqlGrammarError(DataAccessException e) {
        log.error(Constants.LOG_PATTERN_MSG, e.getMessage());
        return new ErrorMessage(Constants.INTERNAL_BD_ERROR_CODE, Constants.INTERNAL_BD_ERROR);
    }

    @ExceptionHandler({TransactionNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    ErrorMessage transactionError(TransactionNotFoundException e) {
        log.error(Constants.LOG_PATTERN_MSG, e.getMessage());
        return new ErrorMessage(Constants.TRANSACTION_ERROR_CODE, e.getMessage());
    }

    @ExceptionHandler({InsertException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ErrorMessage insertException(InsertException e) {
        log.error(Constants.LOG_PATTERN_MSG, e.getMessage());
        return new ErrorMessage(Constants.INSERT_ERROR_CODE, e.getMessage());
    }

    @ExceptionHandler({DeleteException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    ErrorMessage deleteException(DeleteException e) {
        log.error(Constants.LOG_PATTERN_MSG, e.getMessage());
        return new ErrorMessage(Constants.DELETE_ERROR_CODE, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ErrorMessage onException(Exception e) {
        log.error(Constants.LOG_PATTERN_MSG, e.getMessage());
        return new ErrorMessage(Constants.INTERNAL_SERVER_ERROR_CODE, Constants.INTERNAL_SERVER_ERROR);
    }
}