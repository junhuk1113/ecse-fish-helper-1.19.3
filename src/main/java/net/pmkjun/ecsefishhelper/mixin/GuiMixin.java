package net.pmkjun.ecsefishhelper.mixin;

import net.pmkjun.ecsefishhelper.FishHelperClient;
import net.minecraft.text.Text;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.client.util.math.MatrixStack;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({InGameHud.class})
public class GuiMixin {
    @Shadow
    private Text title;
    @Shadow
    private Text overlayMessage;


    @Inject(method = {"render(Lnet/minecraft/client/util/math/MatrixStack;F)V"}, at = {@At("RETURN")} ,cancellable = false)
        private void renderMixin(MatrixStack poseStack, float tickDelta, CallbackInfo info) {
            FishHelperClient.getInstance().renderEvent(poseStack);
        }
}

