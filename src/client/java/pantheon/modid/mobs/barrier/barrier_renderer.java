package pantheon.modid.mobs.barrier;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class barrier_renderer extends GeoEntityRenderer<barrier> {
    public barrier_renderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new barrier_geo());
        };
    }



