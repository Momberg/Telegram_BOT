package com.fiap.BOT.exception;

public class ComandoInvalidoException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public ComandoInvalidoException() {

	}

	public ComandoInvalidoException(String msg){
        super(msg);
    }

}
