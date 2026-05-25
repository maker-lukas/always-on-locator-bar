package com.orangopontotango.alwaysonlocatorbar.client.mixin;

import com.orangopontotango.alwaysonlocatorbar.client.config.AlwaysOnLocatorBarConfig;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.contextualbar.ContextualBarRenderer;
import net.minecraft.client.gui.contextualbar.LocatorBarRenderer;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Gui.class)
public abstract class GuiMixin {
    @Shadow @Final
    private Minecraft minecraft;

    @Shadow
    private Pair<?, ContextualBarRenderer> contextualInfoBar;

    @Unique
    private LocatorBarRenderer alwaysonlocatorbar$overlayLocator;

    @Inject(method = "willPrioritizeExperienceInfo", at = @At("HEAD"), cancellable = true)
    private void alwaysonlocatorbar$forceXpAlwaysVisible(CallbackInfoReturnable<Boolean> cir) {
        AlwaysOnLocatorBarConfig config = AlwaysOnLocatorBarConfig.get();
        if (config.enabled && config.xpBarAlwaysVisible) {
            cir.setReturnValue(true);
        }
    }


    @Inject(
        method = "extractHotbarAndDecorations",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/contextualbar/ContextualBarRenderer;extractRenderState(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Lnet/minecraft/client/DeltaTracker;)V",
            shift = At.Shift.AFTER
        )
    )
    private void alwaysonlocatorbar$overlayLocatorDots(
            GuiGraphicsExtractor graphics,
            DeltaTracker deltaTracker,
            CallbackInfo ci) {
        if (!AlwaysOnLocatorBarConfig.get().enabled) return;        
        if (this.minecraft.player == null) return;
        if (this.contextualInfoBar.getValue() instanceof LocatorBarRenderer) return;
        if (!this.minecraft.player.connection.getWaypointManager().hasWaypoints()) return;
        if (this.alwaysonlocatorbar$overlayLocator == null) {
            this.alwaysonlocatorbar$overlayLocator = new LocatorBarRenderer(this.minecraft);
        }
        this.alwaysonlocatorbar$overlayLocator.extractRenderState(graphics, deltaTracker);
    }
}