package pantheon.modid;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pantheon.modid.blocks.modBlocks;
import pantheon.modid.effects.modEffects;
import pantheon.modid.items.book.MagicComponents.Focus;
import pantheon.modid.items.book.bookScreen;
import pantheon.modid.items.modItems;
import pantheon.modid.mobs.barrier.barrier;
import pantheon.modid.mobs.diansu_supervivens.holo.Diansu_Supervivens_Holo;
import pantheon.modid.mobs.modMobs;
import pantheon.modid.mobs.virtus_hand.virtusHand;
import pantheon.modid.particles.modParticles;
import pantheon.modid.mobs.SnekTest.SnekTest;

import java.util.ArrayList;

import static pantheon.modid.KeyBindings.registerClientTickEvent;

public class LostPantheon implements ModInitializer {
	public static final String MOD_ID = "lost-pantheon";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");

		modItems.registerModItems();

		registerClientTickEvent();
		modBlocks.registerModBlocks();
		modMobs.registerModMobs();
		modEffects.InitializeModEffects();
		modParticles.registerParticles();

		FabricDefaultAttributeRegistry.register(modMobs.DSH, Diansu_Supervivens_Holo.createDSHAttributes());
		FabricDefaultAttributeRegistry.register(modMobs.SnekTest_ENTITY_TYPE, SnekTest.createTestSnekAttributes());
		FabricDefaultAttributeRegistry.register(modMobs.VIRTUS_HAND_ENTITY_TYPE, virtusHand.createAttributes());

	}
	public void setScreen(Screen screen) {
		MinecraftClient.getInstance().setScreen(screen);
	}
}