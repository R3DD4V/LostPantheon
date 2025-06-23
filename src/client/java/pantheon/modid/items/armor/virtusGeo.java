package pantheon.modid.items.armor;

import net.minecraft.util.Identifier;
import pantheon.modid.LostPantheon;
import software.bernie.geckolib.model.GeoModel;

public class virtusGeo extends GeoModel<nova_virtus> {
    @Override
    public Identifier getModelResource(nova_virtus animatable) {
        return new Identifier(LostPantheon.MOD_ID, "geo/armor/nova_virtus.geo.json");
    }

    @Override
    public Identifier getTextureResource(nova_virtus animatable) {
        return new Identifier(LostPantheon.MOD_ID, "textures/armor/nova_virtus.png");
    }

    @Override
    public Identifier getAnimationResource(nova_virtus animatable) {
        return new Identifier(LostPantheon.MOD_ID, "animations/armor/nova_virtus.animation.json");
    }
}
