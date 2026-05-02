package com.example.throughwalls.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    @Inject(method = "renderEntity", at = @At("HEAD"))
    private void throughwalls$beforeRenderEntity(CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.getEntityRenderDispatcher().shouldRenderHitboxes()) {
            RenderSystem.disableDepthTest();
        }
    }

    @Inject(method = "renderEntity", at = @At("TAIL"))
    private void throughwalls$afterRenderEntity(CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.getEntityRenderDispatcher().shouldRenderHitboxes()) {
            RenderSystem.enableDepthTest();
        }
    }
}
