package com.jmentor.jqlearn;

import com.jmentor.jtictactoe.Board;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QMinimaxTest {

    @Test
    public void testEvaluate() {
        QLearn qLearn = new QLearn();
        QMinimax qMinimax = new QMinimax(4, qLearn);
        int[] board = new int[Board.BOXSIZE];
        double result = qMinimax.Evaluate(board, 1);
        assertEquals(0.1, result);
    }
}
