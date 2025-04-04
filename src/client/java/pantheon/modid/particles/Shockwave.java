package pantheon.modid.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

public class Shockwave extends SpriteBillboardParticle {

    protected Shockwave(ClientWorld world, double x, double y, double z) {
        super(world, x, y, z);

        this.setBoundingBoxSpacing(0.2F, 0.2F);
        this.setPos(x, y, z);
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
        this.maxAge = (int)(4.0F / (this.random.nextFloat() * 0.9F + 0.1F));
    }

    public Shockwave(ClientWorld level, double xCoord, double yCoord, double zCoord, double xd, double yd, double zd, SpriteProvider spriteSet) {

        this(level, xCoord, yCoord, zCoord);

        this.velocityMultiplier = 1f;
        this.x = xCoord;
        this.y = yCoord;
        this.z = zCoord;
        this.scale = 3f;
        this.maxAge = 30;
        this.gravityStrength = 0;
        this.velocityX = xd;
        this.velocityY = yd;
        this.velocityZ = zd;

        this.setSpriteForAge(spriteSet);



        this.red = 1f;
        this.green = 1f;
        this.blue = 1f;
    }

    @Override
    public Particle move(float speed) {
        this.velocityX *= (double)speed;
        this.velocityY *= (double) speed;
        this.velocityZ *= (double)speed;
        return this;
    }

    @Override
    public void tick() {
        super.tick();
        fadeOut();
    }

    private void fadeOut() {
        this.alpha = (-(1/(float)maxAge) * age + 1);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider sprites;

        public Factory(SpriteProvider spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(DefaultParticleType particleType, ClientWorld level, double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new Shockwave(level, x, y, z, dx, dy, dz, this.sprites);
        }
    }
}
