package net.pmkjun.ecsefishhelper.gui.screen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.pmkjun.ecsefishhelper.FishHelperClient;
import net.pmkjun.ecsefishhelper.util.ConvertActivateTime;
import net.pmkjun.ecsefishhelper.util.ConvertCooldown;


public class ConfigScreen extends Screen {

    private MinecraftClient mc;
    private FishHelperClient client;
    private final Screen parentScreen;
    private TextFieldWidget totemCooldown_TextField;
    private TextFieldWidget totemActivate_TextField;
    private TextFieldWidget CooldownReduction_TextField;

    private ButtonWidget toggleTotemButton;

    private ActivateTimeSlider activateTimeSlider;
    private CooldownSlider cooldownSlider;

    private ButtonWidget timerXbtn;
    private Timer_y_Slider timerYSlider;

    public ConfigScreen(Screen parentScreen){
        super(Text.translatable("fishhelper.config.title"));
        this.parentScreen = parentScreen;
        this.mc = MinecraftClient.getInstance();
        this.client = FishHelperClient.getInstance();

    }

    @Override
    protected void init() {
        String toggleTotem;
        String timerxpos;

        System.out.println("width : "+this.width+" height"+this.height);


        if(client.data.toggleTotemtime){
            toggleTotem = "fishhelper.config.enable";
        }
        else{
            toggleTotem = "fishhelper.config.disable";
        }

        if(client.data.isTimerright){
            timerxpos = "fishhelper.config.timerright";
        }
        else{
            timerxpos = "fishhelper.config.timerleft";
        }

        activateTimeSlider = new ActivateTimeSlider(10,10,150,
                ConvertActivateTime.asLevel(this.client.data.valueTotemActivetime),0,20);

        cooldownSlider = new CooldownSlider(10, 35, 150,
                ConvertCooldown.asLevel(this.client.data.valueTotemCooldown),0,10);

        //timerXSlider = new Timer_x_Slider(10,100,150,this.client.data.Timer_xpos,2,this.width-43);
        timerXbtn = ButtonWidget.builder(Text.translatable(timerxpos),button -> {
            toggleTotemXpos();
        }).dimensions(10,115,150,20).build();
        this.addDrawableChild(timerXbtn);
        timerYSlider = new Timer_y_Slider(10,140,150,this.client.data.Timer_ypos,2,this.height-18);

        this.CooldownReduction_TextField = new TextFieldWidget(this.textRenderer,100,60,35,10,this.CooldownReduction_TextField,Text.translatable("fishhelper.config.cooldownreductionfield"));
        this.CooldownReduction_TextField.setText(Double.toString(this.client.data.valueCooldownReduction/(double)1000));
        this.addSelectableChild(this.CooldownReduction_TextField);

        toggleTotemButton = ButtonWidget.builder(Text.translatable(toggleTotem),button -> {
            toggleTotemtime();
        }).dimensions(10,75, 150,20).build();
        this.addDrawableChild(toggleTotemButton);

        this.addDrawableChild(ButtonWidget.builder(Text.translatable("fishhelper.config.backbutton"),button -> {
            mc.setScreen(parentScreen);
        }).dimensions(10,this.height-30, 50,20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.translatable("fishhelper.config.savebutton"),button -> {
            changeSetting();
            mc.setScreen(parentScreen);
        }).dimensions(this.width-60,this.height-30, 50,20).build());



        this.addDrawableChild(activateTimeSlider);
        this.addDrawableChild(cooldownSlider);

        this.addDrawableChild(timerYSlider);
    }

    @Override
    public void tick() {
        this.CooldownReduction_TextField.tick();
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return super.keyPressed(keyCode, scanCode, modifiers)
                || this.CooldownReduction_TextField.keyPressed(keyCode, scanCode, modifiers);
    }
    @Override
    public boolean charTyped(char chr, int keyCode) {
        return this.CooldownReduction_TextField.charTyped(chr, keyCode);
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        //drawTextWithShadow(matrices, this.textRenderer, Text.translatable("fishhelper.config.activatefield"), 10, 10, 0xFFFFFF);
        //drawTextWithShadow(matrices, this.textRenderer, Text.translatable("fishhelper.config.cooldownfield"), 10, 25, 0xFFFFFF);
        drawTextWithShadow(matrices, this.textRenderer, Text.translatable("fishhelper.config.cooldownreductionfield"), 10, 60, 0xFFFFFF);
        drawTextWithShadow(matrices,this.textRenderer,Text.translatable("fishhelper.config.changepos"),10,100,0xFFFFFF);

        this.activateTimeSlider.render(matrices, mouseX, mouseY, delta);
        this.cooldownSlider.render(matrices, mouseX, mouseY, delta);

        this.timerYSlider.render(matrices, mouseX, mouseY, delta);

        this.CooldownReduction_TextField.render(matrices, mouseX, mouseY, delta);

        //drawCenteredTextWithShadow(matrices, this.textRenderer, this.title, this.width / 2, 20, 0xFFFFFF);

        super.render(matrices, mouseX, mouseY, delta);
    }
    private void changeSetting(){
        try{
            client.data.valueTotemActivetime = ConvertActivateTime.asMinute(activateTimeSlider.getValue());
            client.data.valueTotemCooldown = ConvertCooldown.asMinute(cooldownSlider.getValue());
            client.data.valueCooldownReduction = (long)(Double.parseDouble(CooldownReduction_TextField.getText())*1000);

            client.configManage.save();
        }
        catch (NumberFormatException e){
            System.out.println("NumberFormatException!");
        }

    }

    private void toggleTotemXpos(){
        if(client.data.isTimerright){
            timerXbtn.setMessage(Text.translatable("fishhelper.config.timerleft"));
            client.data.isTimerright = false;
        }
        else{
            timerXbtn.setMessage(Text.translatable("fishhelper.config.timerright"));
            client.data.isTimerright = true;
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
