package net.pmkjun.ecsefishhelper.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class FishItems {
    public static final Item[] COMMON_FISH = new Item[2];
    public static void register(){
        for(int i = 0; i<2; i++){
            COMMON_FISH[i] = new Item(new Item.Settings());
            Registry.register((Registry)Registries.ITEM, new Identifier("ecse-fish-helper", "common_"+i), COMMON_FISH[i]);
        }

    }
    public static int getFishItem(ItemStack itemStack){
        String name = itemStack.getName().getString();

        if(name.equals("평범한 물고기"))
        {
            return 0;
        }
        else if(name.equals("흰동가리")) return 1;
        return -1;
    }
}
