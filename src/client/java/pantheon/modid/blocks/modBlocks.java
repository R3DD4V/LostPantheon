package pantheon.modid.blocks;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import pantheon.modid.LostPantheon;


public final class modBlocks {

    public static final HologramGenerator HOLOGRAM_GENERATOR = registerBlock("hologram_generator", new HologramGenerator(AbstractBlock.Settings.create().nonOpaque()));
    public static final Receiver RECEIVER = registerBlock("receiver", new Receiver(AbstractBlock.Settings.create()));

    private static <T extends Block> T registerBlock(String name, T block) {
        Registry.register(Registries.ITEM, Identifier.of(LostPantheon.MOD_ID, name), new BlockItem(block, new Item.Settings()));
        Registry.register(Registries.BLOCK, new Identifier(LostPantheon.MOD_ID, name), block);
        return block;
    }

    public static void registerModBlocks() {
        BlockRenderLayerMap.INSTANCE.putBlock(modBlocks.HOLOGRAM_GENERATOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(modBlocks.RECEIVER, RenderLayer.getCutout());
    }
}