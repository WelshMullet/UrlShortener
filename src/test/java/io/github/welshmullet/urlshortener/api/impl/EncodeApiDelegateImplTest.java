package io.github.welshmullet.urlshortener.api.impl;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import io.github.welshmullet.urlshortener.generated.model.UrlRequest;

class EncodeApiDelegateImplTest {
	private final EncodeApiDelegateImpl encodeApiDelegateImpl = new EncodeApiDelegateImpl();

	@Test
	void testThatImplementationEncodesProvidedUrl() {
		assertNotNull(encodeApiDelegateImpl.encodeUrl(new UrlRequest().url("www.google.com")).getBody().getUrl());
	}

}
