package io.github.welshmullet.urlshortener.api.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Priority;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.github.welshmullet.urlshortener.generated.model.ApiError;

@ControllerAdvice
@Priority(0)
public class MethodArgumentNotValidExceptionHandler extends ResponseEntityExceptionHandler {
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		final List<String> details = new ArrayList<>();
		for (final ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getObjectName() + " : " + error.getDefaultMessage());
		}
		final ApiError error = new ApiError().message(details.stream().collect(Collectors.joining(",")));
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

}
