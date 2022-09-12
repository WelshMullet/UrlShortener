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
public class InvalidUrlExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(InvalidUrlException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> handleInvalidUrlException(InvalidUrlException notFoundException, WebRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiError().message("Provided URL is invalid"));
	}

}
