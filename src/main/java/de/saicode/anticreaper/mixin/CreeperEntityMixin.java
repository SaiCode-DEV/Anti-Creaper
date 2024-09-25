package de.saicode.anticreaper.mixin;

import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;
import java.util.Iterator;

import static de.saicode.anticreaper.Anticreeper.DO_CHARGED_CREEPER_EXPLOSIONS_BLOCKDAMAGE;
import static de.saicode.anticreaper.Anticreeper.DO_CREEPER_EXPLOSIONS_BLOCKDAMAGE;

@Mixin(CreeperEntity.class)
public abstract class CreeperEntityMixin extends LivingEntity {
    @Shadow
    private int explosionRadius;

    @Shadow @Final private static TrackedData<Boolean> CHARGED;

    public CreeperEntityMixin(EntityType<? extends CreeperEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "explode", at = @At("HEAD"), cancellable = true)
    private void explode(CallbackInfo ci) {
        ci.cancel();
        if(!this.getWorld().isClient()) {
            float f = this.dataTracker.get(CHARGED) ? 2.0F : 1.0F;
            this.dead = true;
            if (!this.getWorld().getGameRules().getBoolean(DO_CREEPER_EXPLOSIONS_BLOCKDAMAGE) && !this.dataTracker.get(CHARGED)) {
                this.getWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), (float) this.explosionRadius * f, World.ExplosionSourceType.NONE);
            } else if (!this.getWorld().getGameRules().getBoolean(DO_CHARGED_CREEPER_EXPLOSIONS_BLOCKDAMAGE) && this.dataTracker.get(CHARGED)) {
                this.getWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), (float)this.explosionRadius * f, World.ExplosionSourceType.NONE);
            } else {
                this.getWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), (float)this.explosionRadius * f, World.ExplosionSourceType.MOB);
            }
            this.discard();

            // spawn effect cloud
            Collection<StatusEffectInstance> collection = this.getStatusEffects();
            if (!collection.isEmpty()) {
                AreaEffectCloudEntity areaEffectCloudEntity = getAreaEffectCloudEntity();

                for (StatusEffectInstance statusEffectInstance : collection) {
                    areaEffectCloudEntity.addEffect(new StatusEffectInstance(statusEffectInstance));
                }

                this.getWorld().spawnEntity(areaEffectCloudEntity);
            }
        }
    }

    @Unique
    private @NotNull AreaEffectCloudEntity getAreaEffectCloudEntity() {
        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(this.getWorld(), this.getX(), this.getY(), this.getZ());
        areaEffectCloudEntity.setRadius(5F);
        areaEffectCloudEntity.setRadiusOnUse(-0.5F);
        areaEffectCloudEntity.setWaitTime(10);
        areaEffectCloudEntity.setDuration(areaEffectCloudEntity.getDuration() / 2);
        areaEffectCloudEntity.setRadiusGrowth(-areaEffectCloudEntity.getRadius() / (float)areaEffectCloudEntity.getDuration());
        return areaEffectCloudEntity;
    }
}
