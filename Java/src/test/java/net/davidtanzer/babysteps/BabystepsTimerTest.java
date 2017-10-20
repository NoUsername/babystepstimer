package net.davidtanzer.babysteps;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Paul Klingelhuber
 */
public class BabystepsTimerTest {

    private BabystepsTimer babystepsTimer;
    private BabystepsTimerTestDriver babystepsTimerTestDriver;

    @Before
    public void setUp() throws Exception {
        babystepsTimer = new BabystepsTimer();
        babystepsTimerTestDriver = new BabystepsTimerTestDriver(babystepsTimer);
        babystepsTimerTestDriver.init();
    }

    @Test
    public void showsTwoMinutesAfterStartingUp() throws Exception {
        babystepsTimerTestDriver.waitFor(50L);
        assertTrue(babystepsTimerTestDriver.userInterfaceContains("02:00"));
    }

    @Test
    public void countDownAfterPressingStart() throws Exception {
        babystepsTimerTestDriver.init();
        babystepsTimerTestDriver.waitFor(50L);

        babystepsTimerTestDriver.press("start");
        babystepsTimerTestDriver.waitFor(1500);
        assertTrue(babystepsTimerTestDriver.userInterfaceContains("01:59"));
    }

    @Test
    public void stopCountdownAfterPressingStop() throws Exception {
        babystepsTimerTestDriver.init();
        babystepsTimerTestDriver.waitFor(50L);

        babystepsTimerTestDriver.press("start");
        babystepsTimerTestDriver.waitFor(1500);
        assertTrue(babystepsTimerTestDriver.userInterfaceContains("01:59"));
        babystepsTimerTestDriver.press("stop");
        babystepsTimerTestDriver.waitFor(1000);
        assertTrue(babystepsTimerTestDriver.userInterfaceContains("02:00"));
    }

    @Test
    public void resetResetsTimerToInitialTime() throws Exception {
        babystepsTimerTestDriver.init();
        final long time = 50L;
        babystepsTimerTestDriver.waitFor(time);

        babystepsTimerTestDriver.press("start");
        babystepsTimerTestDriver.waitFor(2500);
        assertTrue(babystepsTimerTestDriver.userInterfaceContains("01:58"));
        babystepsTimerTestDriver.press("reset");
        babystepsTimerTestDriver.waitFor(1500);
        assertTrue(babystepsTimerTestDriver.userInterfaceContains("01:59"));
    }

}