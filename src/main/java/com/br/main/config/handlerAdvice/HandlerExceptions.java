package com.br.main.config.handlerAdvice;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.br.main.services.exceptions.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class HandlerExceptions extends ResponseEntityExceptionHandler {
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
    	MethodArgumentNotValidException ex, HttpHeaders headers,
        HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        //Get all errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        // TODO Auto-generated method stub

        Map<String, Object> resp = new LinkedHashMap<>();
        resp.put("timestamp", new Date());
        resp.put("status", status.value());

        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());

        resp.put("errors", errors);

        return new ResponseEntity<>(resp, headers, status);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> entityNotFound(NotFoundException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        Map<String, Object> resp = new LinkedHashMap<>();
        resp.put("timestamp", new Date());
        resp.put("status", status.value());

        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());

        resp.put("errors", errors);

        return new ResponseEntity<>(resp, status);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> databaseException(DataIntegrityViolationException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Map<String, Object> resp = new LinkedHashMap<>();
        resp.put("timestamp", new Date());
        resp.put("status", status.value());

        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());

        resp.put("errors", errors);

        return new ResponseEntity<>(resp, status);
    }
}
