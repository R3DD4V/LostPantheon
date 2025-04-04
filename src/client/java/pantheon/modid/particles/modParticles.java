package pantheon.modid.particles;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import pantheon.modid.LostPantheon;

public class modParticles {
    public static final DefaultParticleType SHOCKWAVE = FabricParticleTypes.simple();

    public static void registerParticles() {
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(LostPantheon.MOD_ID, "shockwave_particle"), SHOCKWAVE);
    }
}
