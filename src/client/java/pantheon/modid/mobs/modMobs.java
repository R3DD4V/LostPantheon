package pantheon.modid.mobs;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import pantheon.modid.LostPantheon;
import pantheon.modid.mobs.SnekTest.SnekTest;
import pantheon.modid.mobs.barrier.barrier;
import pantheon.modid.mobs.diansu_supervivens.holo.Diansu_Supervivens_Holo;
import pantheon.modid.mobs.virtus_hand.virtusHand;

public class modMobs {
    public static final EntityType<Diansu_Supervivens_Holo> DSH = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(LostPantheon.MOD_ID, "diansu_supervivens_holo"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, Diansu_Supervivens_Holo::new)
                    .dimensions(EntityDimensions.fixed(0.75f, 1.25f)).build());
    public static final EntityType<barrier> BARRIER_ENTITY_TYPE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(LostPantheon.MOD_ID, "barrier"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, barrier::new)
                    .dimensions(EntityDimensions.fixed(0.75f, 1.25f)).build());
    public static final EntityType<SnekTest> SnekTest_ENTITY_TYPE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(LostPantheon.MOD_ID, "snek_test"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, SnekTest::new)
                    .dimensions(EntityDimensions.fixed(0.75f, 1.25f)).build());
    public static final EntityType<virtusHand> VIRTUS_HAND_ENTITY_TYPE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(LostPantheon.MOD_ID, "virtus_hand"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, virtusHand::new)
                    .dimensions(EntityDimensions.fixed(1f,  0.5f)).build());
    public static void registerModMobs() {


    }
}
