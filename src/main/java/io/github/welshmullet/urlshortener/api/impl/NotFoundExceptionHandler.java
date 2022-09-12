package io.github.welshmullet.urlshortener.api.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.github.welshmullet.urlshortener.generated.model.ApiError;

@ControllerAdvice
public class NotFoundExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> handleNotFoundException(NotFoundException notFoundException, WebRequest request) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError().message("Encoded URL not found"));
	}

}
