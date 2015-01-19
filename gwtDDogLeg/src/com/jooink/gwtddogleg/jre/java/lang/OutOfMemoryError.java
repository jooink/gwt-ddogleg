package java.lang;

import com.google.gwt.user.client.Window;

public class OutOfMemoryError extends NullPointerException {

	private static final long serialVersionUID = 1L;

	public OutOfMemoryError() {
		Window.alert("Alert! OutOfMemoryError() called. ");
	}
}
