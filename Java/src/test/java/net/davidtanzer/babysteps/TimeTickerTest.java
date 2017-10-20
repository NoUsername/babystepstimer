package net.davidtanzer.babysteps;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

/**
 * @author Paul Klingelhuber
 */
public class TimeTickerTest
{

    private String lastText;
    private TimeTicker timeTicker;

    @Before
    public void setup() {
    }

    @Test
    public void tickOneSecond() throws Exception
    {
        initTimerTest(10);

        timeTicker.tick(TimeUnit.SECONDS.toMillis(1));

        assertThat(lastText)
            .contains("00:09");
    }

    @Test
    public void tickTimerElapsed() throws Exception
    {
        initTimerTest(10);

        timeTicker.tick(TimeUnit.SECONDS.toMillis(10));

        assertThat(lastText)
            .contains("00:00");
    }

    @Test
    public void tickTimerReset() throws Exception
    {
        initTimerTest(120);

        timeTicker.tick(TimeUnit.SECONDS.toMillis(120) + 980);

        assertThat(lastText)
            .contains("02:00");
    }

    void initTimerTest(int secondsInCycle) throws InterruptedException
    {
        timeTicker = new TimeTicker(secondsInCycle);
        timeTicker.initTimer();
        timeTicker.onTextChange = (val) -> {
            lastText = val;
        };
    }

}