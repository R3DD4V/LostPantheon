package pantheon.modid.items.cookingKnife;

import net.minecraft.util.Identifier;
import pantheon.modid.LostPantheon;
import software.bernie.geckolib.model.GeoModel;

public class cookingKnifeGeo extends GeoModel<cookingKnife> {


    @Override
    public Identifier getModelResource(cookingKnife cookingKnife) {
        return new Identifier(LostPantheon.MOD_ID,"geo/item/demonic_cooking_knife.geo.json");
    }

    @Override
    public Identifier getTextureResource(cookingKnife cookingKnife) {
        return new Identifier(LostPantheon.MOD_ID, "textures/item/demonic_cooking_knife.png");
    }

    @Override
    public Identifier getAnimationResource(cookingKnife cookingKnife) {
        return new Identifier(LostPantheon.MOD_ID, "animations/item/demonic_cooking_knife.animation.json");
    }
}
