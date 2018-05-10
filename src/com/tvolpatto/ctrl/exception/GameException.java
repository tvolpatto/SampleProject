package com.tvolpatto.ctrl.exception;

/**
 * @author Thyago Volpatto
 * @since 0.1.0 05-10-2018
 */
public class GameException extends RuntimeException{

    public GameException() {
        super();
    }

    public GameException(String message) {
        super(message);
    }

    public GameException(String message, Throwable cause) {
        super(message, cause);
    }


    public GameException(Throwable cause) {
        super(cause);
    }
}
