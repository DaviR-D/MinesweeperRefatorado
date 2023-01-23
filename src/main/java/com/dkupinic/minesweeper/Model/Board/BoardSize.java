/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author  : Dino Kupinic
 * @date    : 23.1.2023
 *
 * @details
 * Class containing the length and width of the board
 */

package com.dkupinic.minesweeper.Model.Board;

public class BoardSize {
    private static final int length = 400;
    private static final int width = 400;

    public static int getLength() {
        return length;
    }

    public static int getWidth() {
        return width;
    }
}
