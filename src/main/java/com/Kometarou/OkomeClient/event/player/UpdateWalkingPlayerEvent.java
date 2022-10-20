package com.Kometarou.OkomeClient.event.player;

import com.Kometarou.OkomeClient.event.SHGREvent;

public class UpdateWalkingPlayerEvent extends SHGREvent {
    public boolean isPre = false;

    public UpdateWalkingPlayerEvent(boolean pre) {
        this.isPre = pre;
    }
}
