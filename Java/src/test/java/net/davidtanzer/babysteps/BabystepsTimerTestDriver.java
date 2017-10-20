package net.davidtanzer.babysteps;

import javax.swing.event.HyperlinkEvent;

import static org.junit.Assert.assertTrue;

/**
 * @author Paul Klingelhuber
 */
public class BabystepsTimerTestDriver {

    private BabystepsTimer babystepsTimer;

    public BabystepsTimerTestDriver(BabystepsTimer babystepsTimer) {
        this.babystepsTimer = babystepsTimer;
    }

    public boolean userInterfaceContains(String text) {
        return babystepsTimer.timerPane.getText().contains(text);
    }

    public void press(String command) throws InterruptedException {
        babystepsTimer.timerPane.getHyperlinkListeners()[0].hyperlinkUpdate(
                new HyperlinkEvent(babystepsTimer.timerPane, HyperlinkEvent.EventType.ACTIVATED, null, "command://" + command));
        Thread.sleep(50L);
    }

    public void init() throws Exception {
        babystepsTimer.init();
    }

    static void waitFor(long timeInMilliseconds) throws InterruptedException {
        Thread.sleep(timeInMilliseconds);
    }
}