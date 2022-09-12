package io.github.welshmullet.urlshortener.data;

/**
 * Interface for storing encoded URLs. Allows for easy mocking, and to easily
 * replace the basic map implementation with an actual data store at a later
 * date.
 * 
 * @author Daniel
 *
 */
public interface EncodedUrlStorage {
	public void storeEncodedUrl(String originalUrl, String encodedUrl);

	public String retrieveOriginalUrl(String encodedUrl);
}
