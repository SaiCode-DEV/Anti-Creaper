package de.saicode.anticreaper;

import net.fabricmc.api.ModInitializer;
import net.minecraft.world.GameRules;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Anticreeper implements ModInitializer {
	public static final String MOD_ID = "anti-creeper";

	public  static final GameRules.Key<GameRules.BooleanRule> DO_CREEPER_EXPLOSIONS_BLOCKDAMAGE = GameRuleRegistry.register("doCreeperExplosionsBlockDamage", GameRules.Category.MOBS, GameRuleFactory.createBooleanRule(false));
	public  static final GameRules.Key<GameRules.BooleanRule> DO_CHARGED_CREEPER_EXPLOSIONS_BLOCKDAMAGE = GameRuleRegistry.register("doChargedCreeperExplosionsBlockDamage", GameRules.Category.MOBS, GameRuleFactory.createBooleanRule(false));
	public  static final GameRules.Key<GameRules.BooleanRule> DO_EXPLOSIONS_DECORATION_DAMAGE = GameRuleRegistry.register("doExplosionsDecorationDamage", GameRules.Category.MOBS, GameRuleFactory.createBooleanRule(false));

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Anti-Creeper initialized. Have fun!");
	}
}