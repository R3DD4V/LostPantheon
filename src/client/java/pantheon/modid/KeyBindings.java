package pantheon.modid;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.AirBlock;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;
import pantheon.modid.effects.modEffects;
import pantheon.modid.items.cookingKnife.cookingKnife;
import pantheon.modid.items.modItems;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.RawAnimation;

import java.util.Objects;

public class KeyBindings {
    static KeyBinding Ability1;
    static KeyBinding MidAirMovement;
    private static double mercur_side_thrust=0;

    public static void InitializeKeyBindings() {
        Ability1 = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.LostPantheon.ability1", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_P, "key.category.first.test"));
        MidAirMovement = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.LostPantheon.midAirMovement",InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_SPACE, "key.category.first.test"));
    }
    public static void registerClientTickEvent() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (Ability1.wasPressed()) {
                if (client.player.getInventory().getArmorStack(2).isOf(modItems.NovaMercur)&&!client.player.hasStatusEffect(modEffects.mercur_dash_cooldown)) {
                    Vec3d looking = client.player.getRotationVector();
                    client.player.setVelocity(client.player.getVelocity().getX()+looking.x*2,looking.y*2, client.player.getVelocity().getZ()+looking.z*2);
                    client.player.addStatusEffect(new StatusEffectInstance(modEffects.mercur_dash_cooldown, 7*20, 0, false, false, false));
                }
            }

            if (client.player != null) {
                if (client.player.isSneaking() && client.player.getBlockStateAtPos().getBlock()== Blocks.AIR && client.player.getInventory().getArmorStack(2).isOf(modItems.NovaMercur)) {
                    mercur_side_thrust+=mercur_side_thrust*0.1+0.02;
                    if(mercur_side_thrust>client.player.getVelocity().getY()*-1) {
                        mercur_side_thrust = client.player.getVelocity().getY()*-1;
                    }
                    if(mercur_side_thrust<0) {
                        mercur_side_thrust=0;
                    }
                    client.player.addVelocity(client.player.getVelocity().getX()*-0.1, mercur_side_thrust, client.player.getVelocity().getZ()*-0.1);
                    client.player.fallDistance = 0;
                    client.player.sendMessage(Text.of(client.player.fallDistance+""+client.player.getVelocity().getY()));
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
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (server!=null&&client.player!=null) {
                    PlayerEntity iPlayer = client.player;
                    if (iPlayer.isSneaking() && iPlayer.getBlockStateAtPos().getBlock()== Blocks.AIR && iPlayer.getInventory().getArmorStack(2).isOf(modItems.NovaMercur)) {
                        iPlayer.fallDistance = 0;
                    }
                }
        });

    }
}
