package other;

import java.io.Serializable;

public class Packet implements Serializable {
	private static final long serialVersionUID = -6711932079263224190L;

	private String author;
	private String message;

	public Packet( String author, String message ) {
		this.author = author;
		this.message = message;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
}
