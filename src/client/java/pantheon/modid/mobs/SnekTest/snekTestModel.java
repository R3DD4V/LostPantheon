package pantheon.modid.mobs.SnekTest;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class snekTestModel extends EntityModel<SnekTest> {
	private static ModelPart Module306;
	private static final List Parts = new ArrayList<ModelPartData>();
	public snekTestModel(ModelPart root) {

		Module306 = root.getChild("Module306");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData Module1 = modelPartData.addChild("Module306", ModelPartBuilder.create(), ModelTransform.of(-15.0F, 8.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		for (int di = 0; di < 305; di++) {
			float i= (float) (di*0.5f);
			Module1.addChild("Ye"+di, ModelPartBuilder.create()
							.uv(0, 11).cuboid(-15.0F, -0.5F+i, -5.0F, 1.0F, 1.0F, 10.0F, new Dilation(0.0F))
							.uv(0, 0).cuboid(15.0F, -0.5F+i, -5.0F, 1.0F, 1.0F, 10.0F, new Dilation(0.0F))
							.uv(0, 22).cuboid(-5.0F, -0.5F+i, 15.0F, 10.0F, 1.0F, 1.0F, new Dilation(0.0F))
							.uv(22, 0).cuboid(-5.0F, -0.5F+i, -16.0F, 10.0F, 1.0F, 1.0F, new Dilation(0.0F))
							.uv(0, 24).cuboid(-7.0F, -0.5F+i, 14.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
							.uv(6, 24).cuboid(-7.0F, -0.5F+i, -15.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
							.uv(12, 24).cuboid(5.0F, -0.5F+i, 14.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
							.uv(0, 26).cuboid(5.0F, -0.5F+i, -15.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
							.uv(6, 26).cuboid(-9.0F, -0.5F+i, 13.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
							.uv(12, 26).cuboid(-9.0F, -0.5F+i, -14.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
							.uv(18, 26).cuboid(7.0F, -0.5F+i, 13.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
							.uv(24, 26).cuboid(7.0F, -0.5F+i, -14.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
							.uv(18, 24).cuboid(-10.0F, -0.5F+i, 12.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
							.uv(0, 28).cuboid(-10.0F, -0.5F+i, -13.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
							.uv(28, 2).cuboid(9.0F, -0.5F+i, 12.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
							.uv(4, 28).cuboid(9.0F, -0.5F+i, -13.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
							.uv(28, 4).cuboid(-11.0F, -0.5F+i, 11.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
							.uv(28, 6).cuboid(-11.0F, -0.5F+i, -12.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
							.uv(8, 28).cuboid(10.0F, -0.5F+i, 11.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
							.uv(28, 8).cuboid(10.0F, -0.5F+i, -12.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
							.uv(28, 10).cuboid(-12.0F, -0.5F+i, 10.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
							.uv(12, 28).cuboid(11.0F, -0.5F+i, 10.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
							.uv(28, 12).cuboid(-12.0F, -0.5F+i, -11.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
							.uv(28, 14).cuboid(11.0F, -0.5F+i, -11.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
							.uv(16, 28).cuboid(-13.0F, -0.5F+i, 9.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
							.uv(28, 16).cuboid(-13.0F, -0.5F+i, -10.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
							.uv(28, 18).cuboid(12.0F, -0.5F+i, 9.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
							.uv(20, 28).cuboid(12.0F, -0.5F+i, -10.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
							.uv(22, 2).cuboid(-14.0F, -0.5F+i, 7.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
							.uv(22, 5).cuboid(-14.0F, -0.5F+i, -9.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
							.uv(22, 8).cuboid(13.0F, -0.5F+i, 7.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
							.uv(22, 11).cuboid(13.0F, -0.5F+i, -9.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
							.uv(22, 14).cuboid(-15.0F, -0.5F+i, 5.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
							.uv(22, 17).cuboid(-15.0F, -0.5F+i, -7.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
							.uv(22, 20).cuboid(14.0F, -0.5F+i, 5.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
							.uv(22, 23).cuboid(14.0F, -0.5F+i, -7.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
					, ModelTransform.pivot(15.0F, -0.5F+i, 0.0F));
			Parts.add(Module1.getChild("Ye"+di));
		}

		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		Module306.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public void setAngles(SnekTest entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
	}
}