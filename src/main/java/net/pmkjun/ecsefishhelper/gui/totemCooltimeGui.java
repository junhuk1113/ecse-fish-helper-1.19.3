package net.pmkjun.ecsefishhelper.gui;
import net.pmkjun.ecsefishhelper.util.Timer;

import net.pmkjun.ecsefishhelper.FishHelperClient;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import  net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;



public class totemCooltimeGui extends DrawableHelper {
    private MinecraftClient mc;
    private FishHelperClient client;
    private TextRenderer font;

    private static final Identifier TOTEM_ICON = new Identifier("ecse-fish-helper","totem.png");
    private static final Identifier TOTEM_SLEEP_ICON = new Identifier("ecse-fish-helper","sleepingtotem3.png");

    private Text lastTitle = null;

    public totemCooltimeGui(){
        this.mc = MinecraftClient.getInstance();
        this.client = FishHelperClient.getInstance();
    }

    public void renderTick(MatrixStack poseStack, Timer timer){
        int activesecond,cooldownsecond;

        activesecond = this.client.data.valueTotemActivetime * 60 - (int)timer.getDifference(this.client.data.lastTotemTime);
        cooldownsecond = this.client.data.valueTotemCooldown * 60 - (int)timer.getDifference(this.client.data.lastTotemCooldownTime);

        if (activesecond < 0)
            activesecond = 0;


        if (cooldownsecond>0&&cooldownsecond<this.client.data.valueTotemCooldown*60)
            this.client.data.isTotemCooldown = true;
        else
            this.client.data.isTotemCooldown = false;

        if(this.client.data.toggleTotemtime&&this.client.data.isTotemCooldown){
            render(poseStack, TOTEM_SLEEP_ICON, cooldownsecond);
        }
        else if(this.client.data.toggleTotemtime){
            render(poseStack, TOTEM_ICON, activesecond);
        }
    }

    private void render(MatrixStack poseStack,Identifier texture, int second){
        int Timer_xpos=2;
        double percent = (double)this.client.data.Timer_ypos / 1000;
        int Timer_ypos = this.mc.getWindow().getScaledHeight()-18-2;
        Timer_ypos = (int)((int)Timer_ypos * percent+2);

        if(this.client.data.isTimerright){
            Timer_xpos = this.mc.getWindow().getScaledWidth()-43;

        }
        poseStack.push();
        poseStack.translate(Timer_xpos,Timer_ypos,0.0D);
        poseStack.scale(0.0625F, 0.0625F, 0.0625F);

        RenderSystem.setShaderTexture(0,texture);
        drawTexture(poseStack, 0, 0, 0, 0, 256, 256);
        poseStack.scale(16.0F, 16.0F, 16.0F);
        poseStack.pop();

        if (this.client.data.toggleTotemtimeText) {
            this.font = this.mc.textRenderer;
            int minute = second / 60;
            second -= minute * 60;
            poseStack.push();
            poseStack.translate((Timer_xpos + 16 + 2), Timer_ypos+4, 0.0D);
            poseStack.scale(0.9090909F, 0.9090909F, 0.9090909F);
            drawTextWithShadow(poseStack, this.font, (Text)Text.literal(String.format("%02d:%02d", new Object[] { Integer.valueOf(minute), Integer.valueOf(second) })), 0, 0, 16777215);
            poseStack.scale(1.1F, 1.1F, 1.1F);

            poseStack.pop();
        }
    }


}
