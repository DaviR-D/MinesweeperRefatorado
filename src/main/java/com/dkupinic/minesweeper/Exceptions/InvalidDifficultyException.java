// InvalidDifficultyException.java
package com.dkupinic.minesweeper.Exceptions;

public class InvalidDifficultyException extends Exception {
    public InvalidDifficultyException() {
        super("Found invalid Difficulty");
    }

    public InvalidDifficultyException(String message) {
        super(message);
    }

    public InvalidDifficultyException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDifficultyException(Throwable cause) {
        super(cause);
    }
}