package com.Kometarou.OkomeClient.mixin.mixins;

import com.Kometarou.OkomeClient.event.player.PlayerDamageBlockEvent;
import com.Kometarou.OkomeClient.util.client.EventUtil;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerControllerMP.class)
public class MixinPlayerControllerMP {
    @Inject(method = "onPlayerDamageBlock", at = @At("HEAD"), cancellable = true)
    private void onPlayerDamageBlockHook(BlockPos pos, EnumFacing face, CallbackInfoReturnable<Boolean> ci) {
        PlayerDamageBlockEvent event = new PlayerDamageBlockEvent(0, pos, face);
        EventUtil.post(event);
        if (event.isCanceled()) ci.setReturnValue(true);
    }
}
