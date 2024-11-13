package com.jmentor.jqlearn;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QLearnTest {

    @Test
    public void testGetMin() {
        QLearn qLearn = new QLearn();
        double min = qLearn.GetMin();
        assertEquals(1000.0, min);
    }

    @Test
    public void testGetMax() {
        QLearn qLearn = new QLearn();
        double max = qLearn.GetMax();
        assertEquals(-1000.0, max);
    }

    @Test
    public void testSetAlpha() {
        QLearn qLearn = new QLearn();
        qLearn.setAlpha(0.5);
        assertEquals(0.5, qLearn.getAlpha());
    }

    @Test
    public void testSetEpsilon() {
        QLearn qLearn = new QLearn();
        qLearn.setEpsilon(0.1);
        assertEquals(0.1, qLearn.getEpsilon());
    }
}
