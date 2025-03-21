package pantheon.modid;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.player.PlayerEntity;
import pantheon.modid.blocks.modBlocks;
import pantheon.modid.items.modItems;
import pantheon.modid.mobs.barrier.barrier;
import pantheon.modid.mobs.barrier.barrier_renderer;
import pantheon.modid.mobs.diansu_supervivens.holo.Diansu_Supervivens_Holo;
import pantheon.modid.mobs.diansu_supervivens.holo.Diansu_Supervivens_Holo_Geo;
import pantheon.modid.mobs.diansu_supervivens.holo.Diansu_Supervivens_Holo_Renderer;
import pantheon.modid.mobs.modMobs;
import pantheon.modid.effects.modEffects;
import software.bernie.geckolib.model.GeoModel;

import static pantheon.modid.KeyBindings.registerClientTickEvent;

public class LostPantheonClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		modItems.registerModItems();
		KeyBindings.InitializeKeyBindings();
		registerClientTickEvent();
		modBlocks.registerModBlocks();
		modMobs.registerModMobs();
		modEffects.InitializeModEffects();

		FabricDefaultAttributeRegistry.register(modMobs.DSH, Diansu_Supervivens_Holo.createDSHAttributes());
		EntityRendererRegistry.INSTANCE.register(modMobs.DSH, Diansu_Supervivens_Holo_Renderer::new);
		EntityRendererRegistry.INSTANCE.register(modMobs.BARRIER_ENTITY_TYPE, barrier_renderer::new);
	}


}