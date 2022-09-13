package io.github.welshmullet.urlshortener.api.impl;

/**
 * Simple exception to allow constructing the correct error response when a
 * requested URL is not present within the encoded URL store.
 * 
 * @author Daniel
 *
 */
public class NotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5459207725569253978L;

}
