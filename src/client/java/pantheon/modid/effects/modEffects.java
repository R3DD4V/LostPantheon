package pantheon.modid.effects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import pantheon.modid.LostPantheon;

public class modEffects {

    public static final StatusEffect mercur_dash_cooldown = Registry.register(Registries.STATUS_EFFECT, new Identifier(LostPantheon.MOD_ID, "mercur_dash_cooldown"),
            new mercur_dash_cooldown(StatusEffectCategory.NEUTRAL, 0xe9b8b3));
    public static void InitializeModEffects() {

    }
}
