package com.harry.exception;

public class HarryEmptyException extends Exception {

	private static final long serialVersionUID = -8079454849611022224L;

	public HarryEmptyException() {
		super();
	}

	public HarryEmptyException(final String message) {
		super(message);
	}

}