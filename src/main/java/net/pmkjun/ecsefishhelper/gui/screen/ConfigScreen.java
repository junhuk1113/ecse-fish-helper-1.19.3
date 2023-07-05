package net.pmkjun.ecsefishhelper.gui.screen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ToggleButtonWidget;
import net.pmkjun.ecsefishhelper.FishHelperClient;
import net.minecraft.client.gui.widget.SliderWidget;


public class ConfigScreen extends Screen {

    private MinecraftClient mc;
    private FishHelperClient client;
    private final Screen parentScreen;
    private TextFieldWidget totemCooldown_TextField;
    private TextFieldWidget totemActivate_TextField;
    private TextFieldWidget CooldownReduction_TextField;

    private ButtonWidget toggleTotemButton;

    public ConfigScreen(Screen parentScreen){
        super(Text.translatable("fishhelper.config.title"));
        this.parentScreen = parentScreen;
        this.mc = MinecraftClient.getInstance();
        this.client = FishHelperClient.getInstance();

    }

    @Override
    protected void init() {
        String toggleTotem;
        if(client.data.toggleTotemtime){
            toggleTotem = "fishhelper.config.enable";
        }
        else{
            toggleTotem = "fishhelper.config.disable";
        }

        this.totemActivate_TextField = new TextFieldWidget(this.textRenderer,100,10,35,10,this.totemActivate_TextField,Text.translatable("fishhelper.config.activatefield"));
        this.totemActivate_TextField.setText(Integer.toString(this.client.data.valueTotemActivetime));
        this.addSelectableChild(this.totemActivate_TextField);
        this.totemActivate_TextField.setTextFieldFocused(true);

        this.totemCooldown_TextField = new TextFieldWidget(this.textRenderer,100,10+15,35,10,this.totemCooldown_TextField,Text.translatable("fishhelper.config.cooldownfield"));
        this.totemCooldown_TextField.setText(Integer.toString(this.client.data.valueTotemCooldown));
        this.addSelectableChild(this.totemCooldown_TextField);

        this.CooldownReduction_TextField = new TextFieldWidget(this.textRenderer,100,25 + 15,35,10,this.CooldownReduction_TextField,Text.translatable("fishhelper.config.cooldownreductionfield"));
        this.CooldownReduction_TextField.setText(Double.toString(this.client.data.valueCooldownReduction/(double)1000));
        this.addSelectableChild(this.CooldownReduction_TextField);

        toggleTotemButton = ButtonWidget.builder(Text.translatable(toggleTotem),button -> {
            toggleTotemtime();
        }).dimensions(95,55, 50,20).build();
        this.addDrawableChild(toggleTotemButton);

        this.addDrawableChild(ButtonWidget.builder(Text.translatable("fishhelper.config.backbutton"),button -> {
            mc.setScreen(parentScreen);
        }).dimensions(10,this.height-30, 50,20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.translatable("fishhelper.config.savebutton"),button -> {
            changeSetting();
            mc.setScreen(parentScreen);
        }).dimensions(this.width-60,this.height-30, 50,20).build());
    }

    @Override
    public void tick() {
        //this.totemActivate_TextField.tick();
        this.totemCooldown_TextField.tick();
        //this.CooldownReduction_TextField.tick();

    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return super.keyPressed(keyCode, scanCode, modifiers)
                || this.totemCooldown_TextField.keyPressed(keyCode, scanCode, modifiers)
                || this.totemActivate_TextField.keyPressed(keyCode, scanCode, modifiers)
                || this.CooldownReduction_TextField.keyPressed(keyCode, scanCode, modifiers);
    }
    @Override
    public boolean charTyped(char chr, int keyCode) {
        return this.totemCooldown_TextField.charTyped(chr, keyCode)
                ||this.totemActivate_TextField.charTyped(chr, keyCode)
                ||this.CooldownReduction_TextField.charTyped(chr, keyCode);
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);

        drawTextWithShadow(matrices, this.textRenderer, Text.translatable("fishhelper.config.activatefield"), 10, 10, 0xFFFFFF);
        drawTextWithShadow(matrices, this.textRenderer, Text.translatable("fishhelper.config.cooldownfield"), 10, 25, 0xFFFFFF);
        drawTextWithShadow(matrices, this.textRenderer, Text.translatable("fishhelper.config.cooldownreductionfield"), 10, 40, 0xFFFFFF);

        drawTextWithShadow(matrices, this.textRenderer, Text.translatable("fishhelper.config.showtime"), 10, 55, 0xFFFFFF);

        this.totemActivate_TextField.render(matrices, mouseX, mouseY, delta);
        this.totemCooldown_TextField.render(matrices, mouseX, mouseY, delta);
        this.CooldownReduction_TextField.render(matrices, mouseX, mouseY, delta);
        //drawCenteredTextWithShadow(matrices, this.textRenderer, this.title, this.width / 2, 20, 0xFFFFFF);
        super.render(matrices, mouseX, mouseY, delta);
    }
    private void changeSetting(){
        try{
            client.data.valueTotemActivetime = Integer.parseInt(totemActivate_TextField.getText());
            client.data.valueTotemCooldown = Integer.parseInt(totemCooldown_TextField.getText());
            client.data.valueCooldownReduction = (long)(Double.parseDouble(CooldownReduction_TextField.getText())*1000);

            client.configManage.save();
        }
        catch (NumberFormatException e){
            System.out.println("NumberFormatException!");
        }

    }
    private void toggleTotemtime(){
        if(client.data.toggleTotemtime){
            toggleTotemButton.setMessage(Text.translatable("fishhelper.config.disable"));
            client.data.toggleTotemtime = false;
        }
        else{
            toggleTotemButton.setMessage(Text.translatable("fishhelper.config.enable"));
            client.data.toggleTotemtime = true;
        }
    }


}
