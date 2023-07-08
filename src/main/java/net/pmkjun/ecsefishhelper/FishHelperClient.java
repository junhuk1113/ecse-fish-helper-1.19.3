package net.pmkjun.ecsefishhelper;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.pmkjun.ecsefishhelper.config.FishHelperModMenu;
import net.pmkjun.ecsefishhelper.file.Data;
import net.pmkjun.ecsefishhelper.gui.totemCooltimeGui;
import net.pmkjun.ecsefishhelper.util.Timer;
import net.pmkjun.ecsefishhelper.config.ConfigManage;

public class FishHelperClient {
    private MinecraftClient mc;
    private static FishHelperClient instance;
    public Data data;
    public ConfigManage configManage;

    private totemCooltimeGui totemcooltimeGui;
    private Timer timer = new Timer();
    public FishHelperClient(){
        this.mc = MinecraftClient.getInstance();
        instance = this;
        this.configManage = new ConfigManage();
        this.data = this.configManage.load();
        if(this.data == null){
            this.data = new Data();
            this.configManage.save();
        }
        this.totemcooltimeGui = new totemCooltimeGui();
    }
    public void init(){
        new FishHelperModMenu();
    }
    public void renderEvent(MatrixStack poseStack) {
        this.totemcooltimeGui.renderTick(poseStack,this.timer);
        this.timer.updateTime();
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
