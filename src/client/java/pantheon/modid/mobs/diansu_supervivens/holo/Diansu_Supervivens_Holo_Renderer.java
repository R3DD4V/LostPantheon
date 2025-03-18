package pantheon.modid.mobs.diansu_supervivens.holo;

import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class Diansu_Supervivens_Holo_Renderer extends GeoEntityRenderer<Diansu_Supervivens_Holo> {
    public Diansu_Supervivens_Holo_Renderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new Diansu_Supervivens_Holo_Geo());
    }
}
