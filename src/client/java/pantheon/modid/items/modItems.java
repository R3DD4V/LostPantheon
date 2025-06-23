package pantheon.modid.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import pantheon.modid.LostPantheon;
import pantheon.modid.items.armor.modArmorMaterials;
import pantheon.modid.items.armor.nova_virtus;
import pantheon.modid.items.book.magicBook;
import pantheon.modid.items.cookingKnife.cookingKnife;

public class modItems {

    public static final Item DemonicCookingKnife = registerItem("demonic_cooking_knife", new cookingKnife(new FabricItemSettings()));
    public static final Item NovaMercur = registerItem("nova_mercur", new ArmorItem(modArmorMaterials.MERCUR, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item NovaTank = registerItem("nova_tank", new ArmorItem(modArmorMaterials.NOVA_TANK, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item NovaVirtus = registerItem("nova_virtus", new nova_virtus(modArmorMaterials.NOVA_VIRTUS, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item MagicBook =  registerItem("magic_book", new magicBook(new FabricItemSettings()));

    private static void addItemsToTabs(FabricItemGroupEntries entries) {

    }
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(LostPantheon.MOD_ID, name), item);
    }


    public static final ItemGroup PANTHEON_GROUP = Registry.register(Registries.ITEM_GROUP, new Identifier(LostPantheon.MOD_ID, "pantheon_group"),FabricItemGroup.builder()
            .icon(() -> new ItemStack(modItems.DemonicCookingKnife))
            .displayName(Text.of("Lost Pantheon"))
            .entries((context, entries) -> {
                entries.add(DemonicCookingKnife);
                entries.add(NovaMercur);
                entries.add(NovaTank);
                entries.add(NovaVirtus);
                entries.add(MagicBook);
            })
            .build());




    public static void registerModItems() {
        LostPantheon.LOGGER.info("Registering Mod Items  for " + LostPantheon.MOD_ID);

    }
}
