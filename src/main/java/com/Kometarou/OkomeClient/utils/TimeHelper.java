package com.Kometarou.OkomeClient.utils;

public class TimeHelper {
    private long lastMS;

    public long getCurrentMS() {
        return System.nanoTime() / 1000000L;
    }

    public long getLastMS() {
        return this.lastMS;
    }

    public boolean hasReached(float f) {
        return (float)(this.getCurrentMS() - this.lastMS) >= f;
    }

    public boolean hasReached(double f) {
        return (double)(this.getCurrentMS() - this.lastMS) >= f;
    }
    public boolean hasReached(long f) {
        return (long)(this.getCurrentMS() - this.lastMS) >= (float)f;
    }

    public void reset() {
        this.lastMS = this.getCurrentMS();
    }

    public void setLastMS(long currentMS) {
        this.lastMS = currentMS;
    }
}
