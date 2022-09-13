package io.github.welshmullet.urlshortener.api.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.github.welshmullet.urlshortener.data.EncodedUrlStorage;
import io.github.welshmullet.urlshortener.generated.api.DecodeApiDelegate;
import io.github.welshmullet.urlshortener.generated.model.UrlRequest;
import io.github.welshmullet.urlshortener.generated.model.UrlResponse;

/**
 * Implementation for the /decode endpoint
 * 
 * @author Daniel
 *
 */
@Service
public class DecodeApiDelegateImpl implements DecodeApiDelegate {
	@Autowired
	private EncodedUrlStorage encodedUrlStorage;

	@Override
	public ResponseEntity<UrlResponse> decodeUrl(UrlRequest urlRequest) {
		final Optional<ResponseEntity<UrlResponse>> result = Optional
				.ofNullable(encodedUrlStorage.retrieveOriginalUrl(urlRequest.getUrl()))
				.map(url -> ResponseEntity.ok(new UrlResponse().url(url)));

		return result.orElseThrow(() -> new NotFoundException());
	}
}
