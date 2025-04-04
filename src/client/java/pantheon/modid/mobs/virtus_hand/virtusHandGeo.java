package pantheon.modid.mobs.virtus_hand;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import pantheon.modid.LostPantheon;
import software.bernie.geckolib.core.molang.MolangParser;
import software.bernie.geckolib.core.molang.MolangQueries;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.util.RenderUtils;

public class virtusHandGeo extends GeoModel<virtusHand> {
    @Override
    public Identifier getModelResource(virtusHand animatable) {
        return new Identifier(LostPantheon.MOD_ID, "geo/entity/virtus_hand.geo.json");
    }

    @Override
    public Identifier getTextureResource(virtusHand animatable) {
        return new Identifier(LostPantheon.MOD_ID, "textures/entity/virtus_hand.png");
    }

    @Override
    public Identifier getAnimationResource(virtusHand animatable) {
        return null;
    }
}
