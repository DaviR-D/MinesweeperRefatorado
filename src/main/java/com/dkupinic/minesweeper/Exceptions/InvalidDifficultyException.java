/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author  : Dino Kupinic
 * @date    : 29.1.2023
 *
 * @details
 * Exception class for when there is an invalid difficulty
 */

package com.dkupinic.minesweeper.Exceptions;

public class InvalidDifficultyException extends Exception{
    public InvalidDifficultyException() {
        super("Found invalid Difficulty");
    }
}
