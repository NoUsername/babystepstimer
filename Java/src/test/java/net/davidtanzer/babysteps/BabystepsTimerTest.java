package net.davidtanzer.babysteps;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Paul Klingelhuber
 */
public class BabystepsTimerTest {

    @Test
    public void showsTwoMinutesAfterStartingUp() throws Exception {
        BabystepsTimerTestDriver.init();
        Thread.sleep(50L);
        assertTrue(BabystepsTimerTestDriver.userInterfaceContains("02:00"));
    }

}