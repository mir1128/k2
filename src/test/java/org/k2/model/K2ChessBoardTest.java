package org.k2.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class K2ChessBoardTest {

    @Test
    public void should_create_board_correctly() throws Exception {
        String status = "[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15]";

        K2ChessBoard k2ChessBoard = new K2ChessBoard(status);

        assertEquals(status, k2ChessBoard.getCurrentStatus());
    }
}