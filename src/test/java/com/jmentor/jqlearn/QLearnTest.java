package com.jmentor.jqlearn;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QLearnTest {

    @Test
    public void testGetMin() {
        QLearn qLearn = new QLearn(0.10, 0.00, -1000.0, 1000.0, false, new double[QLearn.getPower(3, 9)], QLearn.getPower(3, 9), new Random(), new MersenneTwister(13));
        double min = qLearn.GetMin();
        assertEquals(1000.0, min);
    }

    @Test
    public void testGetMax() {
        QLearn qLearn = new QLearn(0.10, 0.00, -1000.0, 1000.0, false, new double[QLearn.getPower(3, 9)], QLearn.getPower(3, 9), new Random(), new MersenneTwister(13));
        double max = qLearn.GetMax();
        assertEquals(-1000.0, max);
    }

    @Test
    public void testSetAlpha() {
        QLearn qLearn = new QLearn(0.10, 0.00, -1000.0, 1000.0, false, new double[QLearn.getPower(3, 9)], QLearn.getPower(3, 9), new Random(), new MersenneTwister(13));
        qLearn.setAlpha(0.5);
        assertEquals(0.5, qLearn.getAlpha());
    }

    @Test
    public void testSetEpsilon() {
        QLearn qLearn = new QLearn(0.10, 0.00, -1000.0, 1000.0, false, new double[QLearn.getPower(3, 9)], QLearn.getPower(3, 9), new Random(), new MersenneTwister(13));
        qLearn.setEpsilon(0.1);
        assertEquals(0.1, qLearn.getEpsilon());
    }
}
