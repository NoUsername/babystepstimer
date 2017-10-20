package net.davidtanzer.babysteps;

import javax.swing.event.HyperlinkEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

/**
 * @author Paul Klingelhuber
 */
public class BabystepsTimerTestDriver {

    private BabystepsTimer babystepsTimer;
    private TestWallClock wallClock;

    public BabystepsTimerTestDriver(BabystepsTimer babystepsTimer, TestWallClock wallClock) {
        this.babystepsTimer = babystepsTimer;
        this.wallClock = wallClock;
    }

    public boolean userInterfaceContains(String text) {
        return getUiHtml().contains(text);
    }

    private String getUiHtml() {
        return babystepsTimer.timerPane.getText();
    }

    public void press(String command) throws InterruptedException {
        babystepsTimer.timerPane.getHyperlinkListeners()[0].hyperlinkUpdate(
                new HyperlinkEvent(babystepsTimer.timerPane, HyperlinkEvent.EventType.ACTIVATED, null, "command://" + command));
        Thread.sleep(50L);
    }

    public void init() throws Exception {
        babystepsTimer.init(wallClock);
    }

    void waitFor(long timeInMilliseconds) throws InterruptedException {
        wallClock.advance(timeInMilliseconds);
        Thread.sleep(50L);
    }

    public String getBackgroundColor() {
        final Pattern pattern = Pattern.compile("background-color:([^;]+);");
        final Matcher matcher = pattern.matcher(getUiHtml());
        matcher.find();

        return matcher.group(1).trim();
    }
}