package pantheon.modid.items.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import pantheon.modid.LostPantheon;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class virtusRender extends GeoArmorRenderer<nova_virtus> {
    public virtusRender() {
        super(new virtusGeo());
    }

    @Override
    protected void applyBoneVisibilityBySlot(EquipmentSlot currentSlot) {
        setVisible(false);

        switch (currentSlot) {
            case HEAD -> setBoneVisible(this.head, true);
            case CHEST -> {
                setBoneVisible(this.body, true);
                setBoneVisible(this.rightArm, true);
                setBoneVisible(this.leftArm, true);
                setBoneVisible(this.head, true);
            }
            case LEGS -> {
                setBoneVisible(this.rightLeg, true);
                setBoneVisible(this.leftLeg, true);
            }
            case FEET -> {
                setBoneVisible(this.rightBoot, true);
                setBoneVisible(this.leftBoot, true);
            }
            default -> {}
        }
    }
}
