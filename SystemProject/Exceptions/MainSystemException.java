package Exceptions;

public class MainSystemException extends Exception {

	private static final long serialVersionUID = 1L;

	public MainSystemException() {
		super();
		
	}

	public MainSystemException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		
	}

	public MainSystemException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	
	}

	public MainSystemException(String arg0) {
		super(arg0);
		
	}

	public MainSystemException(Throwable arg0) {
		super(arg0);
		
	}

}