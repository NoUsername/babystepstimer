package net.davidtanzer.babysteps;

/**
 * @author Paul Klingelhuber
 */
public class TestWallClock extends WallClock {

    private  long time = 0;

    public void advance(long offset) {
        time += offset;
    }

    @Override
    long getCurrentCycleStartTime() {
        return time;
    }

}
