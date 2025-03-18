package pantheon.modid.mobs;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import pantheon.modid.LostPantheon;
import pantheon.modid.mobs.diansu_supervivens.holo.Diansu_Supervivens_Holo;

public class modMobs {
    public static final EntityType<Diansu_Supervivens_Holo> DSH = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(LostPantheon.MOD_ID, "diansu_supervivens_holo"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, Diansu_Supervivens_Holo::new)
                    .dimensions(EntityDimensions.fixed(1f, 1f)).build());

    public static void registerModMobs() {
    }
}
