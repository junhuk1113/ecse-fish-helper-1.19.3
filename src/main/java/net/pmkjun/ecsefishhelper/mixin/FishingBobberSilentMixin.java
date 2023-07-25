package net.pmkjun.ecsefishhelper.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FishingBobberEntity.class)
public class FishingBobberSilentMixin extends ProjectileEntity {

    @Shadow
    @Final
    private static TrackedData<Integer> HOOK_ENTITY_ID;

    @Shadow
    @Final
    private static TrackedData<Boolean> CAUGHT_FISH;

    public FishingBobberSilentMixin(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initDataTracker() {
        this.getDataTracker().startTracking(HOOK_ENTITY_ID, 0);
        this.getDataTracker().startTracking(CAUGHT_FISH, false);
    }


    @Inject(method = "setOwner(Lnet/minecraft/entity/Entity;)V", at = @At("RETURN"))
    private void setSlientMixin(@Nullable Entity entity, CallbackInfo ci){
        System.out.println("setOwner called");
        if(entity != null){
            entity.setSilent(true);
            System.out.println("isSilent : "+entity.isSilent());
        }
        else System.out.println("entity is null!");
    }
}