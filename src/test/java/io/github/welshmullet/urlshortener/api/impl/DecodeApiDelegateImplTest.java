package io.github.welshmullet.urlshortener.api.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import io.github.welshmullet.urlshortener.data.EncodedUrlStorage;
import io.github.welshmullet.urlshortener.generated.model.UrlRequest;
import io.github.welshmullet.urlshortener.generated.model.UrlResponse;

@ExtendWith(MockitoExtension.class)
class DecodeApiDelegateImplTest {
	@InjectMocks
	private DecodeApiDelegateImpl decodeApiDelegateImpl;

	@Mock
	private EncodedUrlStorage mockEncodedUrlStorage;

	@Test
	void testThatImplementaionDecodesStoredUrl() {
		final String testEncodedUrl = "https://localhost/api/12345";
		final String responseUrl = "www.google.com";
		when(mockEncodedUrlStorage.retrieveOriginalUrl(testEncodedUrl)).thenReturn(responseUrl);
		final ResponseEntity<UrlResponse> response = decodeApiDelegateImpl
				.decodeUrl(new UrlRequest().url(testEncodedUrl));
		assertEquals(responseUrl, response.getBody().getUrl());
	}

}
