package com.dkupinic.minesweeper.Exceptions;

public class InvalidDifficultyException extends Exception{
    public InvalidDifficultyException() {
        super("Found invalid Difficulty");
    }
}
