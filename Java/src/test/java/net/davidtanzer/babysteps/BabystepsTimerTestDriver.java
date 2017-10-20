package net.davidtanzer.babysteps;

import org.junit.Test;

import javax.swing.event.HyperlinkEvent;

import static org.junit.Assert.assertTrue;

/**
 * @author Paul Klingelhuber
 */
public class BabystepsTimerTestDriver {


    public static boolean userInterfaceContains(String text) {
        return BabystepsTimer.timerPane.getText().contains(text);
    }

    public static void press(String command) throws InterruptedException {
        BabystepsTimer.timerPane.getHyperlinkListeners()[0].hyperlinkUpdate(
                new HyperlinkEvent(BabystepsTimer.timerPane, HyperlinkEvent.EventType.ACTIVATED, null, "command://" + command));
        Thread.sleep(50L);
    }

    public static void init() throws Exception {
        BabystepsTimer.main(new String[0]);
    }
}