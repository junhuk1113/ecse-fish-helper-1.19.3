package net.pmkjun.ecsefishhelper.mixin;

import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedModelManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.pmkjun.ecsefishhelper.item.FishItems;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemModels.class)
public class ItemModelsMixin {
    @Shadow
    @Final
    private BakedModelManager modelManager;
    @Shadow
    public BakedModel getModel(Item item) {
        return null;
    }

    @Inject(method = {"getModel(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/client/render/model/BakedModel;"},at = {@At("TAIL")}, cancellable = true)
    public void getModelMixin(ItemStack stack, CallbackInfoReturnable<BakedModel> cir){
        int index;
        cir.cancel();

        index = FishItems.getFishItem(stack);
        if(index != -1){
            stack = new ItemStack((ItemConvertible) FishItems.COMMON_FISH[index], stack.getCount());
        }
        BakedModel bakedModel = getModel(stack.getItem());
        cir.setReturnValue((bakedModel == null) ? modelManager.getMissingModel() : bakedModel);
    }

}
