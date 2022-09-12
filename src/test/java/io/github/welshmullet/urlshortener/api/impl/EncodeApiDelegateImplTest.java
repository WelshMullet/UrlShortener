package io.github.welshmullet.urlshortener.api.impl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

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

}
