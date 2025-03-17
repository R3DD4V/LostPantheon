package pantheon.modid.blocks;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HologramGenerator extends TransparentBlock {

    protected HologramGenerator(AbstractBlock.Settings settings) {
        super(settings);
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            if (world.getBlockState(new BlockPos(pos.getX(), pos.getY()-1, pos.getZ())).getBlock() == Blocks.REDSTONE_BLOCK) {
                player.sendMessage(Text.of("Test"), false);
            }
        }

        return ActionResult.SUCCESS;
    }
}

