package pantheon.modid.mobs.virtus_hand;

import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.particle.SpellParticle;
import net.minecraft.client.render.entity.ArrowEntityRenderer;
import net.minecraft.client.render.entity.model.ParrotEntityModel;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SnowballItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.potion.Potions;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Arm;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import pantheon.modid.mobs.modMobs;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class virtusHand extends MobEntity implements GeoEntity {

    private boolean returning;
    private Entity entity;
    private Entity owner;

    public virtusHand(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean handleAttack(Entity attacker) {
        return true;
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }



    @Override
    public boolean hasNoDrag() {
        return true;
    }

    @Override
    public boolean canTakeDamage() {
        return false;
    }

    @Override
    protected void onBlockCollision(BlockState state) {
        state.isAir();
        super.onBlockCollision(state);

    }

    public void setReturning(Boolean bool) {
        returning = bool;
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        boolean bl = super.handleFallDamage(fallDistance, damageMultiplier, damageSource);
        int i = this.computeFallDamage(fallDistance, damageMultiplier);
        if (i > 0) {
            this.playBlockFallSound();
            return true;
        } else {
            return bl;
        }
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        return false;
    }

    public void setOwner(PlayerEntity pOwner) {
        owner = pOwner;
    }

    @Override
    public void tick() {

        checkBlockCollision();
        if (owner != null) {
            double X = owner.getPos().getX() - this.getPos().getX();
            double Y = owner.getPos().getY() - this.getPos().getY();
            double Z = owner.getPos().getZ() - this.getPos().getZ();
            double Sum = Math.abs(X) + Math.abs(Y) + Math.abs(Z);


            double tZ = this.getVelocity().getZ() / (Math.abs(getVelocity().getZ()) + Math.abs(getVelocity().getX()));
            double tX = this.getVelocity().getX()/(Math.abs(getVelocity().getZ())+Math.abs(getVelocity().getX()));
            float tempYaw = (float) ((tZ + 1) * 90 * ((tX < 0) ? -1 : 1) + 180 * ((tX < 0) ? 1 : 1) );


            setPitch((float) (Y/Sum));
            if (returning) {
                if (Sum <= 3) {
                    removeAllPassengers();
                    if (entity != null) {
                        entity.addVelocity(X / Sum, Y / Sum, Z / Sum);
                        entity = null;
                    }
                    owner.sendMessage(Text.of("Fuck you"));
                    discard();
                }
                Sum -= Math.pow(Sum * 0.1, 2);
                setVelocity(2*X / Sum, 2*Y / Sum, 2*Z / Sum);
            } else {
                if(isInsideWall()) {
                    returning = true;
                }

                this.move(MovementType.SELF, this.getVelocity());
                setYaw(tempYaw);
                setBodyYaw(getYaw());
                owner.sendMessage(Text.of(""+getYaw()));
                if (Sum >= 50) {
                    returning = true;
                }
                Vec3d vec3d = this.getVelocity();
                Vec3d vec3d3 = this.getPos();
                Vec3d vec3d2 = vec3d3.add(vec3d);
                double d2 = Double.MAX_VALUE;
                Entity entity2 = null;

                World world = getWorld();
                Box box = this.getBoundingBox().stretch(this.getVelocity()).expand((double) 1f);
                Predicate<Entity> predicate = this::canHit;

                for(Entity entity3 : world.getOtherEntities(this, box, predicate)) {
                    Box box2 = entity3.getBoundingBox().expand((double)0.5f);
                    Optional<Vec3d> optional = box2.raycast(vec3d3, vec3d2);
                    if (optional.isPresent()) {
                        double e = vec3d3.squaredDistanceTo((Vec3d)optional.get());
                        if (e < d2) {
                            entity2 = entity3;
                            d2 = e;
                        }
                    }
                    if (entity2!=null&&entity2 != owner) {
                        entity2.startRiding(this);
                        entity2.setPose(EntityPose.STANDING);
                        entity=entity2;
                        returning = true;
                    }
                }



                /*if (this.prevPitch == 0.0F && this.prevYaw == 0.0F) {
                    double d = vec3d.horizontalLength();
                    this.setYaw((float)(MathHelper.atan2(vec3d.x, vec3d.z) * (double)(180F / (float)Math.PI)));
                    this.setPitch((float)(MathHelper.atan2(vec3d.y, d) * (double)(180F / (float)Math.PI)));
                    this.prevYaw = this.getYaw();
                    this.prevPitch = this.getPitch();
                }*/
            }
        }
        else {
        }
        noClip = true;
        super.tick();
        if(entity!=null)  {
            entity.setPose(EntityPose.STANDING);
        }
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    private boolean canHit(Entity entity) {
        super.canHit();
        return !this.isRemoved();
    }

    @Override
    public boolean isInsideWall() {
        if (!this.noClip) {
            return false;
        } else {
            float f = this.getDimensions(getPose()).width * 0.8F;
            Box box = Box.of(new Vec3d(getPos().x, getPos().y-getHeight()/2, getPos().getZ()), (double)f, 1.0E-6, (double)f);
            return BlockPos.stream(box)
                    .anyMatch(
                            pos -> {
                                BlockState blockState = this.getWorld().getBlockState(pos);
                                return !blockState.isAir()
                                        && VoxelShapes.matchesAnywhere(
                                        blockState.getCollisionShape(this.getWorld(), pos).offset((double)pos.getX(), (double)pos.getY(), (double)pos.getZ()),
                                        VoxelShapes.cuboid(box),
                                        BooleanBiFunction.AND
                                );
                            }
                    );
        }
    }



    @Override
    public Arm getMainArm() {
        return null;
    }



    protected void onBlockHit (BlockState state) {
            setVelocity(0,0,0);
            returning = true;
    }

    @Override
    protected void updatePassengerPosition(Entity passenger, PositionUpdater positionUpdater) {
        super.updatePassengerPosition(passenger, positionUpdater);
        passenger.setVelocity(0f,0f,0f);
        positionUpdater.accept(passenger, getX()+0.7f*getRotationVector().getX(), getY()-passenger.getHeight()/2+getHeight()/2+0.7f*getRotationVector().getY(), getZ()+0.7f*getRotationVector().getZ());
        passenger.setPose(EntityPose.STANDING);
    }

    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 15)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0f);
    }

    protected<E extends virtusHand> PlayState animController(final AnimationState<E> event) {

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

    public boolean isBaby() {
        return false;
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {

    }
}
