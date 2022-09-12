package io.github.welshmullet.urlshortener.api.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.github.welshmullet.urlshortener.generated.model.ApiError;

class InvalidUrlExceptionHandlerTest {
	@Test
	void testHandlerReturnsResponseWithCorrectCode() {
		final ResponseEntity<?> response = new InvalidUrlExceptionHandler().handleInvalidUrlException(null, null);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	void testHandlerReturnsResponseWithCorrectMessage() {
		final ResponseEntity<?> response = new InvalidUrlExceptionHandler().handleInvalidUrlException(null, null);
		assertEquals("Provided URL is invalid", ((ResponseEntity<ApiError>) response).getBody().getMessage());
	}

}
