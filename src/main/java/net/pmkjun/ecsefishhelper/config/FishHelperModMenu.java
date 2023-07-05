package net.pmkjun.ecsefishhelper.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.pmkjun.ecsefishhelper.gui.screen.ConfigScreen;

public class FishHelperModMenu implements ModMenuApi{
    public ConfigScreenFactory<?> getModConfigScreenFactory(){
        return ConfigScreen::new;
    }

}
