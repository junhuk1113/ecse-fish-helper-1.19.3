package net.pmkjun.ecsefishhelper;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.pmkjun.ecsefishhelper.file.Data;
import net.pmkjun.ecsefishhelper.gui.totemCooltimeGui;
import net.pmkjun.ecsefishhelper.util.Timer;

public class FishHelperClient {
    private MinecraftClient mc;
    private static FishHelperClient instance;
    public Data data;

    private totemCooltimeGui totemcooltimeGui;
    private Timer timer = new Timer();
    public FishHelperClient(){
        this.mc = MinecraftClient.getInstance();
        instance = this;
        this.data = new Data();
        this.totemcooltimeGui = new totemCooltimeGui();
    }
    public void init(){

    }
    public void renderEvent(MatrixStack poseStack) {
        this.totemcooltimeGui.renderTick(poseStack,this.timer);
        this.timer.updateTime();
    }
    public void debugFishingMixin(String str){ //테스트 함수
        this.data.FishingMixinString = str;
    }
    public void updateLastTotemtime(){
        this.data.lastTotemTime = this.timer.getCurrentTime();
        this.data.lastTotemCooldownTime = this.timer.getCurrentTime()+(long)this.data.valueTotemActivetime * 60 * 1000;
    }
    public void reduceCooldownTotem(){
        this.data.lastTotemCooldownTime += this.data.valueCooldownReduction;
    }

    public String getUsername(){
        return this.mc.player.getName().getString();
    }

    public static  FishHelperClient getInstance(){
        return instance;
    }
}
