package other;

import java.io.Serializable;

public class Packet implements Serializable {
	private static final long serialVersionUID = -6711932079263224190L;

	private String message;

	public Packet( String message ) {
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return String.format( "Mesage: %s (%s)", message, this.hashCode() );
	}
}
