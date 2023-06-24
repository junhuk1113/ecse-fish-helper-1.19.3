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
    private static final Identifier BLACK_ICON = new Identifier("ecse-fish-helper", "resources/asstes.ecse-fish-helper/icon.png");

    private Text lastTitle = null;

    public totemCooltimeGui(){
        this.mc = MinecraftClient.getInstance();
        this.client = FishHelperClient.getInstance();
    }

    public void renderTick(MatrixStack poseStack, Timer timer){

    }




}
