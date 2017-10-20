package net.davidtanzer.babysteps;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Paul Klingelhuber
 */
public class TimeFormatterTest
{

    @Test
    public void testGetRemainingTimeCaptionBasic()
    {

        assertThat(TimeFormatter.getRemainingTimeCaption(0, 0))
            .isEqualTo("00:00");
    }

    @Test
    public void testGetRemainingTimeCaptionElapsed()
    {

        assertThat(TimeFormatter.getRemainingTimeCaption(1000, 10))
            .isEqualTo("00:09");
    }

}
