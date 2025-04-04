package pantheon.modid.mobs.virtus_hand;

import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class virtusHandRenderer extends GeoEntityRenderer<virtusHand> {
    public virtusHandRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new virtusHandGeo());
    }
}

