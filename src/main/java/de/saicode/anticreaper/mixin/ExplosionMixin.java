package de.saicode.anticreaper.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.decoration.GlowItemFrameEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.decoration.painting.PaintingEntity;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;
import java.util.Set;

import static de.saicode.anticreaper.Anticreeper.DO_EXPLOSIONS_DECORATION_DAMAGE;

@Mixin(Explosion.class)
public class ExplosionMixin {

    @Shadow
    private World world;

    // List of entities that should be protected from the explosion
    @Unique
    private static final Set<Class<? extends Entity>> PROTECTED_ENTITIES = Set.of(
            ArmorStandEntity.class,
            ItemFrameEntity.class,
            PaintingEntity.class,
            GlowItemFrameEntity.class
    );

    @ModifyVariable(method = "collectBlocksAndDamageEntities", at = @At("STORE"), ordinal = 0)
    private List<Entity> protect_Entities(List<Entity> entityList) {

        if(!this.world.getGameRules().getBoolean(DO_EXPLOSIONS_DECORATION_DAMAGE)) {
            entityList.removeIf(entity -> PROTECTED_ENTITIES.contains(entity.getClass()));
        }
        return entityList;
    }
}
