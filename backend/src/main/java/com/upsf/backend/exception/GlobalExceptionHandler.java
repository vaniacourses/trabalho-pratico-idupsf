package com.upsf.backend.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<ErrorResponse> handleEntidadeNaoEncontrada(
            EntidadeNaoEncontradaException e,
            HttpServletRequest request) {

        return new ResponseEntity<>(
                new ErrorResponse(
                        LocalDateTime.now(),
                        HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.name(),
                        request.getMethod(),
                        request.getRequestURI(),
                        null,
                        e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntidadeJaExistenteException.class)
    public ResponseEntity<ErrorResponse> handleEntidadeJaExistente(
            EntidadeJaExistenteException e,
            HttpServletRequest request) {

        return new ResponseEntity<>(
                new ErrorResponse(
                        LocalDateTime.now(),
                        HttpStatus.CONFLICT.value(),
                        HttpStatus.CONFLICT.name(),
                        request.getMethod(),
                        request.getRequestURI(),
                        null,
                        e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e,
            HttpServletRequest request) {

        Map<String, String> map = new HashMap<>();
        for (FieldError fe : e.getBindingResult().getFieldErrors()) {
            map.put(fe.getField(), fe.getDefaultMessage());
        }

        return new ResponseEntity<>(
                new ErrorResponse(
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.name(),
                        request.getMethod(),
                        request.getRequestURI(),
                        map,
                        e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<ErrorResponse> handleRegraNegocioException(
            RegraNegocioException e, HttpServletRequest request) {

        return new ResponseEntity<>(
                new ErrorResponse(
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(), // 400 Bad Request
                        "Violação de Regra de Negócio",
                        request.getMethod(),
                        request.getRequestURI(),
                        null,
                        e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
