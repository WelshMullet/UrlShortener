package io.github.welshmullet.urlshortener.api.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.github.welshmullet.urlshortener.generated.model.ApiError;

class NotFoundExceptionHandlerTest {

	@Test
	void testHandlerReturnsResponseWithCorrectCode() {
		final ResponseEntity<?> response = new NotFoundExceptionHandler().handleNotFoundException(null, null);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	void testHandlerReturnsResponseWithCorrectMessage() {
		final ResponseEntity<?> response = new NotFoundExceptionHandler().handleNotFoundException(null, null);
		assertEquals("Encoded URL not found", ((ResponseEntity<ApiError>) response).getBody().getMessage());
	}

}
