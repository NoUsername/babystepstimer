package net.davidtanzer.babysteps;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author Paul Klingelhuber
 */
public class BabystepsTimerTest {

    public static final long UI_INIT_TIME = 50L;
    private BabystepsTimer babystepsTimer;
    private BabystepsTimerTestDriver babystepsTimerTestDriver;

    private TestWallClock wallClock = new TestWallClock();

    @Before
    public void setUp() throws Exception {
        babystepsTimer = new BabystepsTimer();
        babystepsTimerTestDriver = new BabystepsTimerTestDriver(babystepsTimer, wallClock);
        babystepsTimerTestDriver.init();
        babystepsTimerTestDriver.waitFor(UI_INIT_TIME);
    }

    @Test
    public void showsTwoMinutesAfterStartingUp() throws Exception {
        assertTrue(babystepsTimerTestDriver.userInterfaceContains("02:00"));
    }

    @Test
    public void countDownAfterPressingStart() throws Exception {
        babystepsTimerTestDriver.press("start");

        babystepsTimerTestDriver.waitFor(1500);
        assertTrue(babystepsTimerTestDriver.userInterfaceContains("01:59"));
    }

    @Test
    public void stopCountdownAfterPressingStop() throws Exception {
        babystepsTimerTestDriver.press("start");

        babystepsTimerTestDriver.waitFor(1500);
        assertTrue(babystepsTimerTestDriver.userInterfaceContains("01:59"));
        babystepsTimerTestDriver.press("stop");
        babystepsTimerTestDriver.waitFor(1000);
        assertTrue(babystepsTimerTestDriver.userInterfaceContains("02:00"));
    }

    @Test
    public void resetResetsTimerToInitialTime() throws Exception {
        babystepsTimerTestDriver.press("start");

        babystepsTimerTestDriver.waitFor(2500);
        assertTrue(babystepsTimerTestDriver.userInterfaceContains("01:58"));
        babystepsTimerTestDriver.press("reset");
        babystepsTimerTestDriver.waitFor(1500);
        assertTrue(babystepsTimerTestDriver.userInterfaceContains("01:59"));
    }

    @Test
    public void resetTimerAfterRanOut() throws Exception {
        babystepsTimerTestDriver.press("start");

        babystepsTimerTestDriver.waitFor(TimeUnit.MINUTES.toMillis(2));
        assertTrue(babystepsTimerTestDriver.userInterfaceContains("00:00"));
        babystepsTimerTestDriver.waitFor(1000);
        assertTrue(babystepsTimerTestDriver.userInterfaceContains("02:00"));
    }

    @Test
    public void changeBackgroundColorWhenRanOut() throws Exception {
        babystepsTimerTestDriver.press("start");

        babystepsTimerTestDriver.waitFor(TimeUnit.MINUTES.toMillis(2));
        assertThat(babystepsTimerTestDriver.getBackgroundColor())
                .isEqualTo(BabystepsTimer.BACKGROUND_COLOR_FAILED);
    }

    @Test
    public void changeBackgroundColorWhenReset() throws Exception {
        babystepsTimerTestDriver.press("start");
        babystepsTimerTestDriver.press("reset");
        babystepsTimerTestDriver.waitFor(TimeUnit.SECONDS.toMillis(1));
        assertThat(babystepsTimerTestDriver.getBackgroundColor())
                .isEqualTo(BabystepsTimer.BACKGROUND_COLOR_PASSED);
    }

}