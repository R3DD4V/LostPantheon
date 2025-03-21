package pantheon.modid.mobs.barrier;

import net.minecraft.util.Identifier;
import pantheon.modid.LostPantheon;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.model.GeoModel;

public class barrier_geo extends GeoModel<barrier> {
    @Override
    public Identifier getModelResource(barrier animatable) {
        return new Identifier(LostPantheon.MOD_ID, "geo/entity/barrier.geo.json");
    }

    @Override
    public Identifier getTextureResource(barrier animatable) {
        return new Identifier(LostPantheon.MOD_ID, "textures/entity/barrier.png");
    }

    @Override
    public Identifier getAnimationResource(barrier animatable) {
        return null;
    }
}
