package com.Kometarou.OkomeClient.event.player;

import com.Kometarou.OkomeClient.event.SHGREvent;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class PlayerDamageBlockEvent extends SHGREvent {
    public int stage;
    public BlockPos pos;
    public EnumFacing facing;

    public PlayerDamageBlockEvent(int stage, BlockPos pos, EnumFacing facing) {
        this.stage = stage;
        this.pos = pos;
        this.facing = facing;
    }
}
