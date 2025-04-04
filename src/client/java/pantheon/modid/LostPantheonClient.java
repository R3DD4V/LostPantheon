package pantheon.modid;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registry;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import pantheon.modid.blocks.modBlocks;
import pantheon.modid.items.modItems;
import pantheon.modid.mobs.barrier.barrier_renderer;
import pantheon.modid.mobs.diansu_supervivens.holo.Diansu_Supervivens_Holo;
import pantheon.modid.mobs.diansu_supervivens.holo.Diansu_Supervivens_Holo_Renderer;
import pantheon.modid.mobs.SnekTest.*;
import pantheon.modid.mobs.SnekTest.snekTestModel;
import pantheon.modid.mobs.modMobs;
import pantheon.modid.effects.modEffects;
import pantheon.modid.particles.modParticles;
import pantheon.modid.mobs.virtus_hand.virtusHandRenderer;
import pantheon.modid.particles.Shockwave;

import static pantheon.modid.KeyBindings.registerClientTickEvent;

public class LostPantheonClient implements ClientModInitializer {
	public static final EntityModelLayer MODEL_CUBE_LAYER = new EntityModelLayer(new Identifier("lost-pantheon", "snek_test"), "main");
	public static final DefaultParticleType Shockwave = FabricParticleTypes.simple();


	@Override
	public void onInitializeClient() {
		EntityModelLayerRegistry.registerModelLayer(MODEL_CUBE_LAYER, snekTestModel::getTexturedModelData);
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.


		EntityRendererRegistry.INSTANCE.register(modMobs.DSH, Diansu_Supervivens_Holo_Renderer::new);
		EntityRendererRegistry.INSTANCE.register(modMobs.BARRIER_ENTITY_TYPE, barrier_renderer::new);
		EntityRendererRegistry.INSTANCE.register(modMobs.SnekTest_ENTITY_TYPE, snekTestRenderer::new);
		EntityRendererRegistry.INSTANCE.register(modMobs.VIRTUS_HAND_ENTITY_TYPE, virtusHandRenderer::new);

		KeyBindings.InitializeKeyBindings();

		ParticleFactoryRegistry.getInstance().register(modParticles.SHOCKWAVE, pantheon.modid.particles.Shockwave.Factory::new);
	}




}