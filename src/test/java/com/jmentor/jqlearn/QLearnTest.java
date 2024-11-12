package com.jmentor.jqlearn;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QLearnTest {

    @Test
    public void testGetMin() {
        QLearn qLearn = new QLearn();
        assertEquals(1000.0, qLearn.GetMin());
    }

    @Test
    public void testGetMax() {
        QLearn qLearn = new QLearn();
        assertEquals(-1000.0, qLearn.GetMax());
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
        qLearn.setEpsilon(0.5);
        assertEquals(0.5, qLearn.getEpsilon());
    }
}
