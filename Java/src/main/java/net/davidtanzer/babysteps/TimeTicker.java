package net.davidtanzer.babysteps;

import java.util.function.Consumer;

/**
 * @author Paul Klingelhuber
 */
public class TimeTicker
{
    long currentCycleStartTime;
    boolean timerRunning;
    String lastRemainingTime;
    Consumer<String> onTextChange = (val) -> {
    };

    private long secondsInCycle;

    public TimeTicker(long secondsInCycle)
    {

        this.secondsInCycle = secondsInCycle;
    }

    public void tick(long elapsedTime)
    {
        if (elapsedTime >= secondsInCycle * 1000 + 980)
        {
            initTimer();
            elapsedTime = System.currentTimeMillis() - currentCycleStartTime;
        }
        if (elapsedTime >= 5000 && elapsedTime < 6000 && !BabystepsTimer.BACKGROUND_COLOR_NEUTRAL.equals(
            BabystepsTimer.bodyBackgroundColor))
        {
            BabystepsTimer.bodyBackgroundColor = BabystepsTimer.BACKGROUND_COLOR_NEUTRAL;
        }

        String remainingTime = TimeFormatter.getRemainingTimeCaption(elapsedTime, secondsInCycle);
        if (!remainingTime.equals(lastRemainingTime))
        {
            onTextChange.accept(remainingTime);

            lastRemainingTime = remainingTime;
        }
    }

    void initTimer()
    {
        currentCycleStartTime = System.currentTimeMillis();
    }

    public void start()
    {
        new TimerThread().start();
    }

    final class TimerThread extends Thread {
        @Override
        public void run() {
            timerRunning = true;
            initTimer();

            while(timerRunning) {
                long elapsedTime = System.currentTimeMillis() - currentCycleStartTime;

                tick(elapsedTime);
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    //We don't really care about this one...
                }
            }
        }
    }
}
