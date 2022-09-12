package io.github.welshmullet.urlshortener.api.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.github.welshmullet.urlshortener.data.EncodedUrlStorage;
import io.github.welshmullet.urlshortener.generated.api.EncodeApiDelegate;
import io.github.welshmullet.urlshortener.generated.model.UrlRequest;
import io.github.welshmullet.urlshortener.generated.model.UrlResponse;

@Service
public class EncodeApiDelegateImpl implements EncodeApiDelegate {
	private static final String BASE_URL = "https://localhost:8080/url/";

	@Autowired
	private EncodedUrlStorage encodedUrlStorage;

	@Override
	public ResponseEntity<UrlResponse> encodeUrl(UrlRequest urlRequest) {
		final String encodedUrl = BASE_URL + UUID.randomUUID();
		encodedUrlStorage.storeEncodedUrl(urlRequest.getUrl(), encodedUrl);
		return ResponseEntity.ok(new UrlResponse().url(encodedUrl));
	}
}
