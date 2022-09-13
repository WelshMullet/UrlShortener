package io.github.welshmullet.urlshortener.api.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.github.welshmullet.urlshortener.generated.model.ApiError;

/**
 * Basic exception handler to allow constructing the correct error response when
 * a provided URL is invalid.
 * 
 * @author Daniel
 *
 */
@ControllerAdvice
public class InvalidUrlExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * 
	 * @param invalidUrlException the exception that was thrown.
	 * @param request             the web request that caused the exception.
	 * @return a bad-request response with a suitable message.
	 */
	@ExceptionHandler(InvalidUrlException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> handleInvalidUrlException(InvalidUrlException invalidUrlException,
			WebRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiError().message("Provided URL is invalid"));
	}

}
