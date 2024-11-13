package com.jmentor.jqlearn;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CPUTimeTest {

    @Test
    public void testGetCPUTime() {
        CPUTime cpuTime = new CPUTime();
        double time1 = cpuTime.getCPUTime();
        double time2 = cpuTime.getCPUTime();
        assertTrue(time2 >= time1);
    }

    @Test
    public void testBeginTimer() {
        CPUTime cpuTime = new CPUTime();
        cpuTime.BeginTimer();
        double time = cpuTime.getCPUTime();
        assertTrue(time >= 0);
    }

    @Test
    public void testEndTimer() {
        CPUTime cpuTime = new CPUTime();
        cpuTime.BeginTimer();
        double time = cpuTime.EndTimer();
        assertTrue(time >= 0);
    }

    @Test
    public void testUTimerReport() {
        CPUTime cpuTime = new CPUTime();
        cpuTime.BeginTimer();
        double time = cpuTime.uEndTimer();
        assertTrue(time >= 0);
        cpuTime.uTimerReport("Elapsed time", time);
    }
}
