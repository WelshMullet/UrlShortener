package io.github.welshmullet.urlshortener.api.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import io.github.welshmullet.urlshortener.data.EncodedUrlStorage;
import io.github.welshmullet.urlshortener.generated.model.UrlRequest;

@ExtendWith(MockitoExtension.class)
class EncodeApiDelegateImplTest {
	private static final String TEST_URL = "https://www.google.com";

	@InjectMocks
	private EncodeApiDelegateImpl encodeApiDelegateImpl;

	@Mock
	private EncodedUrlStorage mockEncodedUrlStorage;

	@BeforeEach
	void init() {
		// There's probably a more correct way to do this. I keep reading about not
		// using value / autowire and providing a constructor instead. That would likely
		// be a better solution if I were to refactor this project further.
		MockitoAnnotations.openMocks(this);
		ReflectionTestUtils.setField(encodeApiDelegateImpl, "baseUrl", "https://testbaseurl");
		ReflectionTestUtils.setField(encodeApiDelegateImpl, "port", 9000);
		ReflectionTestUtils.setField(encodeApiDelegateImpl, "path", "/testDifferentPath/");
	}

	@Test
	public void testThatImplementationEncodesProvidedUrl() {
		assertNotNull(encodeApiDelegateImpl.encodeUrl(new UrlRequest().url(TEST_URL)).getBody().getUrl());
	}

	@Test
	public void testThatImplemenationStoresEncodedUrl() {
		encodeApiDelegateImpl.encodeUrl(new UrlRequest().url(TEST_URL));
		verify(mockEncodedUrlStorage).storeEncodedUrl(eq(TEST_URL), any());
	}

	@Test
	public void testThatImplementationThrowsNotFoundExceptionIfUrlInvalid() {
		final String testEncodedUrl = "not-a-url";
		Assertions.assertThrows(InvalidUrlException.class, () -> {
			encodeApiDelegateImpl.encodeUrl(new UrlRequest().url(testEncodedUrl));
		});
	}

	@Test
	public void testThatImplementationUsesConfiguredValuesToConstructUrl() {
		assertTrue(encodeApiDelegateImpl.encodeUrl(new UrlRequest().url(TEST_URL)).getBody().getUrl()
				.startsWith("https://testbaseurl:9000/testDifferentPath/"));

	}

}
