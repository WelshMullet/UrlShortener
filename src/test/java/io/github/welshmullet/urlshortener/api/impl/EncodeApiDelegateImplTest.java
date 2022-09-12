package io.github.welshmullet.urlshortener.api.impl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.github.welshmullet.urlshortener.data.EncodedUrlStorage;
import io.github.welshmullet.urlshortener.generated.model.UrlRequest;

@ExtendWith(MockitoExtension.class)
class EncodeApiDelegateImplTest {
	private static final String TEST_URL = "www.google.com";

	@InjectMocks
	private EncodeApiDelegateImpl encodeApiDelegateImpl;

	@Mock
	private EncodedUrlStorage mockEncodedUrlStorage;
	
	@Test
	void testThatImplementationEncodesProvidedUrl() {
		assertNotNull(encodeApiDelegateImpl.encodeUrl(new UrlRequest().url(TEST_URL)).getBody().getUrl());
	}
	
	@Test
	void testThatImplemenationStoresEncodedUrl() {
		encodeApiDelegateImpl.encodeUrl(new UrlRequest().url(TEST_URL));
		verify(mockEncodedUrlStorage).storeEncodedUrl(eq(TEST_URL), any());
	}
	
	@Test
	void testThatImplementationThrowsNotFoundExceptionIfUrlInvalid() {
		final String testEncodedUrl = "not-a-url";
		when(mockEncodedUrlStorage.retrieveOriginalUrl(testEncodedUrl)).thenReturn(null);
		Assertions.assertThrows(InvalidUrlException.class, () -> {
			encodeApiDelegateImpl.encodeUrl(new UrlRequest().url(testEncodedUrl));
		});
	}

}
