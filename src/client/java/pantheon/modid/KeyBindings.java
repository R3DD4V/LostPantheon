package pantheon.modid;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;
import pantheon.modid.effects.modEffects;
import pantheon.modid.items.modItems;
import pantheon.modid.mobs.diansu_supervivens.holo.Diansu_Supervivens_Holo;
import pantheon.modid.mobs.modMobs;
import pantheon.modid.mobs.virtus_hand.virtusHand;
import pantheon.modid.particles.modParticles;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class KeyBindings {
    static KeyBinding Ability1;
    static KeyBinding Ability2;
    static KeyBinding MidAirMovement;
    private static double mercur_side_thrust=0;
    private static int shockwaveTick;
    private static boolean shockwave;
    private static Vec3d shockwaveDirection;
    private static Vec3d shockWavestartingPos;

    public static void InitializeKeyBindings() {
        Ability1 = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.LostPantheon.ability1", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_P, "key.category.first.test"));
        MidAirMovement = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.LostPantheon.midAirMovement",InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_SPACE, "key.category.first.test"));
        Ability2 = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.LostPantheon.ability2", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_H, "key.category.first.test"));
    }


    public static void registerClientTickEvent() {

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (Ability1.wasPressed()) {
                if (client.player.getInventory().getArmorStack(2).isOf(modItems.NovaMercur)&&!client.player.hasStatusEffect(modEffects.mercur_dash_cooldown)) {
                    Vec3d looking = client.player.getRotationVector();
                    client.player.setVelocity(client.player.getVelocity().getX()+looking.x*2,looking.y*2, client.player.getVelocity().getZ()+looking.z*2);
                    client.player.addStatusEffect(new StatusEffectInstance(modEffects.mercur_dash_cooldown, 7*20, 0, false, false, false));
                }
                if (client.player.getInventory().getArmorStack(2).isOf(modItems.NovaTank)&& !shockwave) {
                    Vec3d looking = client.player.getRotationVector();
                    shockwaveDirection = client.player.getRotationVector();
                    shockWavestartingPos = client.player.getPos();
                    client.world.addParticle(modParticles.SHOCKWAVE, client.player.getX(), client.player.getY()+1.5f, client.player.getZ(), looking.x, looking.y, looking.z);
                    shockwave=true;
                }
            }
            if (shockwave) {
                shockwaveTick +=1;
                if (shockwaveTick>=30) {
                    shockwave = false;
                    shockwaveTick = 0;
                }
                else {
                    client.world.getEntities().forEach(entity -> {
                        client.player.sendMessage(Text.of((shockwaveDirection.getX()*shockwaveTick+shockWavestartingPos.getX())+" "+(shockwaveDirection.getY()*shockwaveTick+shockWavestartingPos.getY())+" "+(shockwaveDirection.getZ()*shockwaveTick+shockWavestartingPos.getZ())));

                        if (!entity.isPlayer()&&((entity.getX()<=shockwaveDirection.getX()*shockwaveTick+shockWavestartingPos.getX()&&entity.getX()>=shockwaveDirection.getX()*(shockwaveTick-1)+shockWavestartingPos.getX())||
                                (entity.getX()>=shockwaveDirection.getX()*shockwaveTick+shockWavestartingPos.getX()&&entity.getX()<=shockwaveDirection.getX()*(shockwaveTick-1)+shockWavestartingPos.getX())
                                &&
                                                ((entity.getZ()<=shockwaveDirection.getZ()*shockwaveTick+shockWavestartingPos.getZ()&&entity.getX()>=shockwaveDirection.getZ()*(shockwaveTick-1)+shockWavestartingPos.getZ())||(entity.getZ()>=shockwaveDirection.getZ()*shockwaveTick+shockWavestartingPos.getZ()&&entity.getX()<=shockwaveDirection.getZ()*(shockwaveTick-1)+shockWavestartingPos.getZ())))) {
                            entity.addVelocity(shockwaveDirection.getX()*3,  shockwaveDirection.getY()*3, shockwaveDirection.getZ()*3);

                        }
                    });
                }

            }

            if (client.player != null) {
                if (client.player.isSneaking() && client.player.getBlockStateAtPos().getBlock()== Blocks.AIR && client.player.getInventory().getArmorStack(2).isOf(modItems.NovaMercur)) {
                    mercur_side_thrust+=mercur_side_thrust*0.1+0.02;
                    if(mercur_side_thrust>client.player.getVelocity().getY()*-1) {
                        mercur_side_thrust = client.player.getVelocity().getY()*-1;
                    }
                    if(mercur_side_thrust==client.player.getVelocity().getY()*-1) {
                        client.player.setVelocity(client.player.getVelocity().getX(), 0, client.player.getVelocity().getZ());
                        mercur_side_thrust = client.player.getVelocity().getY() *-1;
                    }
                    if(mercur_side_thrust<0) {
                        mercur_side_thrust=0;
                    }
                    client.player.addVelocity(client.player.getVelocity().getX()*-0.1, mercur_side_thrust, client.player.getVelocity().getZ()*-0.1);
                    client.player.fallDistance = 0.1f;
                }
                else {
                    mercur_side_thrust-=0.1;
                    if(mercur_side_thrust>client.player.getVelocity().getY()*-1) {
                        mercur_side_thrust = client.player.getVelocity().getY()*-1;
                    }
                    if(mercur_side_thrust<0) {
                        mercur_side_thrust=0;
                    }
                }
                if (client.player.getStatusEffect(modEffects.mercur_dash_cooldown) != null) {
                    if (Objects.requireNonNull(client.player.getStatusEffect(modEffects.mercur_dash_cooldown)).isDurationBelow(1)) {
                        client.player.removeStatusEffect(modEffects.mercur_dash_cooldown);
                    }

                }
            }

        });
        ServerTickEvents.END_WORLD_TICK.register(serverWorld -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (serverWorld!=null&&client.player!=null) {
                PlayerEntity iPlayer = client.player;
                while (Ability2.wasPressed()) {
                    virtusHand yeeted = new virtusHand(modMobs.VIRTUS_HAND_ENTITY_TYPE, serverWorld);
                    yeeted.setOwner(iPlayer);
                    yeeted.setPosition(client.player.getPos().getX() + client.player.getRotationVector().getX(), client.player.getPos().getY() + client.player.getRotationVector().getY()+1, client.player.getPos().getZ() + client.player.getRotationVector().getZ());
                    yeeted.setReturning(false);

                    serverWorld.spawnEntity(yeeted);
                    yeeted.addVelocity(client.player.getRotationVector().getX()*1.5,client.player.getRotationVector().getY()*1.5,client.player.getRotationVector().getZ()*1.5);
                    client.player.sendMessage(Text.of(""+yeeted.getVelocity()));
                }
            }
        });
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (server!=null&&client.player!=null) {
                    PlayerEntity iPlayer = client.player;
                    if (iPlayer.isSneaking() && iPlayer.getBlockStateAtPos().getBlock()== Blocks.AIR && iPlayer.getInventory().getArmorStack(2).isOf(modItems.NovaMercur)) {
                        iPlayer.fallDistance = 0.1f;
                    }

                }



        });

    }
}
