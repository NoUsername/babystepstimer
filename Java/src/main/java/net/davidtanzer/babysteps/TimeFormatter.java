package net.davidtanzer.babysteps;

import java.text.DecimalFormat;

/**
 * @author Paul Klingelhuber
 */
public class TimeFormatter
{
    private static final DecimalFormat twoDigitsFormat = new DecimalFormat("00");


    static String getRemainingTimeCaption(final long elapsedTime, long secondsInCycle)
    {
        long elapsedSeconds = elapsedTime / 1000;
        long remainingSeconds = secondsInCycle - elapsedSeconds;

        long remainingMinutes = remainingSeconds / 60;
        return twoDigitsFormat.format(remainingMinutes) + ":" + twoDigitsFormat.format(
            remainingSeconds - remainingMinutes * 60);
    }
}
