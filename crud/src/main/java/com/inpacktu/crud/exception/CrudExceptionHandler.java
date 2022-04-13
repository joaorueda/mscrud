package com.inpacktu.crud.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CrudExceptionHandler extends ResponseEntityExceptionHandler{

	public final ResponseEntity<ExceptionResponse> handlerBadRequestException(Exception ex, WebRequest req){
		ExceptionResponse exResp = new ExceptionResponse(new Date(),ex.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(exResp, HttpStatus.BAD_REQUEST);
	}
}
