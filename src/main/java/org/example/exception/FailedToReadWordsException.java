package org.example.exception;

public class FailedToReadWordsException extends Exception{
    public FailedToReadWordsException(String message, Throwable cause) {
        super(message, cause);
    }
}
