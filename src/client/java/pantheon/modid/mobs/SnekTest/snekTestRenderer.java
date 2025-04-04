package pantheon.modid.mobs.SnekTest;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import pantheon.modid.LostPantheon;
import pantheon.modid.LostPantheonClient;

public class snekTestRenderer extends MobEntityRenderer<SnekTest, snekTestModel> {
    public snekTestRenderer(EntityRendererFactory.Context context) {
        super(context, new snekTestModel(context.getPart(LostPantheonClient.MODEL_CUBE_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(SnekTest entity) {
        return new Identifier(LostPantheon.MOD_ID,  "textures/entity/snek_test.png");
    }
}
