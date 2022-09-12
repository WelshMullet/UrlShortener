package io.github.welshmullet.urlshortener.api.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.github.welshmullet.urlshortener.generated.api.DecodeApiDelegate;
import io.github.welshmullet.urlshortener.generated.model.UrlRequest;
import io.github.welshmullet.urlshortener.generated.model.UrlResponse;

@Service
public class DecodeApiDelegateImpl implements DecodeApiDelegate {

	@Override
	public ResponseEntity<UrlResponse> decodeUrl(UrlRequest urlRequest) {
		return null;
	}
}
