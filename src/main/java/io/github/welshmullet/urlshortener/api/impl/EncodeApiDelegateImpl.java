package io.github.welshmullet.urlshortener.api.impl;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.github.welshmullet.urlshortener.generated.api.EncodeApiDelegate;
import io.github.welshmullet.urlshortener.generated.model.UrlRequest;
import io.github.welshmullet.urlshortener.generated.model.UrlResponse;

@Service
public class EncodeApiDelegateImpl implements EncodeApiDelegate {
	private static final String BASE_URL = "https://localhost:8080/url/";

	@Override
	public ResponseEntity<UrlResponse> encodeUrl(UrlRequest urlRequest) {
		return ResponseEntity.ok(new UrlResponse().url(BASE_URL + UUID.randomUUID()));
	}
}
