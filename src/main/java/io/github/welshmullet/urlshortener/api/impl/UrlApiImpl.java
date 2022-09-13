package io.github.welshmullet.urlshortener.api.impl;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.github.welshmullet.urlshortener.data.EncodedUrlStorage;
import io.github.welshmullet.urlshortener.generated.api.UrlApiDelegate;

@Service
public class UrlApiImpl implements UrlApiDelegate {

	@Value("${urlshortener.baseurl:'https://localhost'}")
	private String baseUrl;
	@Value("${urlshortener.path:'/api/'}")
	private String path;
	@Value("${server.port:8080}")
	private int port;

	@Autowired
	private EncodedUrlStorage encodedUrlStorage;

	@Override
	public ResponseEntity<Void> urlIdGet(String id) {

		final Optional<ResponseEntity<Void>> result = Optional
				.ofNullable(encodedUrlStorage.retrieveOriginalUrl(String.format("%s:%d%s%s", baseUrl, port, path, id)))
				.map(url -> ResponseEntity.status(HttpStatus.FOUND).location(URI.create(url)).build());

		return result.orElseThrow(() -> new NotFoundException());
	}
}
