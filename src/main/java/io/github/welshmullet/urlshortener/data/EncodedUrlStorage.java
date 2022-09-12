package io.github.welshmullet.urlshortener.data;

public interface EncodedUrlStorage {
	public void storeEncodedUrl(String originalUrl, String encodedUrl);

	public String retrieveOriginalUrl(String encodedUrl);
}
