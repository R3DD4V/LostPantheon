package pantheon.modid.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import pantheon.modid.LostPantheon;
import pantheon.modid.items.armor.modArmorMaterials;
import pantheon.modid.items.cookingKnife.cookingKnife;

public class modItems {

    public static final Item DemonicCookingKnife = registerItem("demonic_cooking_knife", new cookingKnife(new FabricItemSettings()));
    public static final Item NovaMercur = registerItem("nova_mercur", new ArmorItem(modArmorMaterials.MERCUR, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));

    private static void addItemsToTabs(FabricItemGroupEntries entries) {
        entries.add(DemonicCookingKnife);
        entries.add(NovaMercur);
    }
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(LostPantheon.MOD_ID, name), item);
    }

    public static void registerModItems() {
        LostPantheon.LOGGER.info("Registering Mod Items  for " + LostPantheon.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(modItems::addItemsToTabs);
    }
}
