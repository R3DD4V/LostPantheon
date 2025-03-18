package pantheon.modid.mobs.diansu_supervivens.holo;

import net.minecraft.util.Identifier;
import pantheon.modid.LostPantheon;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;

public class Diansu_Supervivens_Holo_Geo extends GeoModel<Diansu_Supervivens_Holo> {

    @Override
    public Identifier getModelResource(Diansu_Supervivens_Holo animatable) {
        return new Identifier(LostPantheon.MOD_ID, "geo/entity/diansu_supervivens_holo.geo.json");
    }

    @Override
    public Identifier getTextureResource(Diansu_Supervivens_Holo animatable) {
        return new Identifier(LostPantheon.MOD_ID, "textures/entity/diansu_supervivens_holo.png");
    }

    @Override
    public Identifier getAnimationResource(Diansu_Supervivens_Holo animatable) {
        return new Identifier(LostPantheon.MOD_ID, "animations/entity/diansu_supervivens_holo.animation.json");
    }
}
