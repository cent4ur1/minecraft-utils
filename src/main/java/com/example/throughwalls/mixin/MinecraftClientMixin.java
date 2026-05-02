package com.example.throughwalls.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.EndCrystalItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Shadow
    private int itemUseCooldown;

    @Inject(method = "tick", at = @At("TAIL"))
    private void throughwalls$removeCrystalPlaceDelay(CallbackInfo ci) {
        MinecraftClient client = (MinecraftClient) (Object) this;
        if (client.player == null) {
            return;
        }

        ItemStack mainHand = client.player.getMainHandStack();
        ItemStack offHand = client.player.getOffHandStack();
        if (mainHand.getItem() instanceof EndCrystalItem || offHand.getItem() instanceof EndCrystalItem) {
            this.itemUseCooldown = 0;
        }
    }
}
