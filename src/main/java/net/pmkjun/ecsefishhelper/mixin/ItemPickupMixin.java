package net.pmkjun.ecsefishhelper.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.SlotActionType;
import net.pmkjun.ecsefishhelper.FishHelperClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScreenHandler.class)
public class ItemPickupMixin {
	private static final Logger LOGGER = LogManager.getLogger("ItemPickupMixin");
	@Shadow
	private ItemStack cursorStack;
	@Inject(method = "onSlotClick", at = @At("RETURN"))
	private void onSlotClick(int slotIndex, int button, SlotActionType actionType, PlayerEntity player, CallbackInfo ci) {
		if (player instanceof ClientPlayerEntity) {
			ClientPlayerEntity clientPlayer = (ClientPlayerEntity) player;
			if (!cursorStack.isEmpty()&&!cursorStack.hasNbt()){
				LOGGER.info("dont have nbt");
			}
			if (!cursorStack.isEmpty()&&cursorStack.hasNbt()) {
				//Text itemName = cursorStack.getName();
				//LOGGER.info("item.pickup: " + itemName.getString());

				LOGGER.info("item.pickup: " + cursorStack.getName().getString());
				if(cursorStack.getName().getString().equals("토템 발동")){
					LOGGER.info("토템 발동 버튼 눌림");
					FishHelperClient.getInstance().updateLastTotemtime();
				}
				//LOGGER.info("item.pickup: " + cursorStack.getTooltipData().);
			}
		}
	}
}