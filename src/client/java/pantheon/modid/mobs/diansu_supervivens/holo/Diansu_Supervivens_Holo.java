package pantheon.modid.mobs.diansu_supervivens.holo;

import net.minecraft.block.EnchantingTableBlock;
import net.minecraft.block.entity.EnchantingTableBlockEntity;
import net.minecraft.data.DataGenerator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import pantheon.modid.blocks.modBlocks;
import pantheon.modid.mobs.modMobs;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class Diansu_Supervivens_Holo extends PathAwareEntity implements GeoEntity {
    public Diansu_Supervivens_Holo(EntityType<? extends PathAwareEntity> type, World world) {
        super(type, world);
    }

    protected static final RawAnimation TEST = RawAnimation.begin().then("diansu_supervivens.test", Animation.LoopType.LOOP);

    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);


    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    public boolean isAttackable() {
        return false;
    }

    @Override
    public void setVelocity(Vec3d velocity) {
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {

    }

    protected <E extends Diansu_Supervivens_Holo> PlayState animController(final AnimationState<E> event) {
        event.getController().setAnimation(RawAnimation.begin().then("animation.diansu_supervivens.test", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "test", 5, this::animController));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }

    public static DefaultAttributeContainer.Builder createDSHAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 15)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0f);


    }

    @Override
    public void tick() {

        World world = getWorld();

        BlockPos pos = BlockPos.ofFloored(getPos());
        if(world.getBlockState(new BlockPos(pos.getX(), pos.getY()-1, pos.getZ())).getBlock() != modBlocks.HOLOGRAM_GENERATOR) {
            setPosition(new Vec3d(0, -64,0));
            kill();
        }
        PlayerEntity playerEntity = world.getClosestPlayer((double)pos.getX() + (double)0.5F, (double)pos.getY() + (double)0.5F, (double)pos.getZ() + (double)0.5F, (double)5.0F, false);
        if (playerEntity != null) {
            float xYaw = (float) (pos.toCenterPos().getX() - playerEntity.getX());
            float zYaw = (float) (pos.toCenterPos().getZ() - playerEntity.getZ());
            if(xYaw>=0) {
                setYaw(zYaw / (Math.abs(zYaw) + Math.abs(xYaw)) * 90 + 90);
            }
            else {
                setYaw(zYaw / (Math.abs(zYaw) + Math.abs(xYaw)) * -90 + 270);
            }

            setRotation(this.getYaw(), 90);
            setBodyYaw(getYaw());
        }
        noClip = true;
        super.tick();
        noClip = false;
        setYaw(getYaw());
    }
    @Override
    public boolean isSpectator() {
        return noClip;
    }
}

