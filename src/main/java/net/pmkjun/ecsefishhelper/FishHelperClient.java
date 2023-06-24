package net.pmkjun.ecsefishhelper;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.pmkjun.ecsefishhelper.gui.totemCooltimeGui;
import net.pmkjun.ecsefishhelper.util.Timer;

public class FishHelperClient {
    private MinecraftClient mc;
    private static FishHelperClient instance;

    private totemCooltimeGui totemcooltimeGui;
    private Timer timer = new Timer();
    public FishHelperClient(){
        this.totemcooltimeGui = new totemCooltimeGui();
    }
    public void init(){

    }
    public void renderEvent(MatrixStack poseStack) {
        this.totemcooltimeGui.renderTick(poseStack,this.timer);
        this.timer.updateTime();
    }

    public static  FishHelperClient getInstance(){
        return instance;
    }
}
