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
 * a requested URL is not present within the encoded URL store.
 * 
 * @author Daniel
 *
 */
@ControllerAdvice
public class NotFoundExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * 
	 * @param notFoundException the exception that was thrown.
	 * @param request           the web request that caused the exception.
	 * @return a not-found response with a suitable message.
	 */
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> handleNotFoundException(NotFoundException notFoundException, WebRequest request) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError().message("Encoded URL not found"));
	}

}
