package com.jmentor.jqlearn;

import com.jmentor.jtictactoe.Board;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TeachTest {

    @Test
    public void testInitializeQLearn() {
        Teach teach = new Teach();
        teach.initializeQLearn();
        assertEquals(0.10, teach.qlearn.getAlpha());
        assertEquals(0.00, teach.qlearn.getEpsilon());
    }

    @Test
    public void testCorrectPredictions() {
        Teach teach = new Teach();
        Board previous = new Board();
        Board board = new Board();
        teach.correctPredictions(previous, board, true, 1.0);
        assertEquals(1.0, teach.qlearn.getValue(board));
    }

    @Test
    public void testPlay() {
        Teach teach = new Teach();
        teach.ngames = 1;
        teach.Play(new String[]{});
        assertEquals(1, teach.Num_Games);
    }
}
