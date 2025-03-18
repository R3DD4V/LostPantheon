package pantheon.modid.blocks;

import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;
import pantheon.modid.mobs.diansu_supervivens.holo.Diansu_Supervivens_Holo;
import pantheon.modid.mobs.modMobs;

public class HologramGenerator extends TransparentBlock {

    private Diansu_Supervivens_Holo hologram = null;
    private boolean active = false;

    protected HologramGenerator(AbstractBlock.Settings settings) {
        super(settings);
    }

    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity player, ItemStack itemStack) {
        hologram = new Diansu_Supervivens_Holo(modMobs.DSH,world);
        hologram.setPosition(new Vec3d(pos.toCenterPos().getX(), pos.getY()+1, pos.toCenterPos().getZ()));
    }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            if      (world.getBlockState(new BlockPos(pos.getX()+1, pos.getY(), pos.getZ())).getBlock() == modBlocks.RECEIVER ||
                    world.getBlockState(new BlockPos(pos.getX()-1, pos.getY(), pos.getZ())).getBlock() == modBlocks.RECEIVER ||
                    world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()-1)).getBlock() == modBlocks.RECEIVER ||
                    world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()+1)).getBlock() == modBlocks.RECEIVER) {
                if (hologram == null)  {
                    hologram = new Diansu_Supervivens_Holo(modMobs.DSH,world);
                    hologram.setPosition(new Vec3d(pos.toCenterPos().getX(), pos.getY()+1, pos.toCenterPos().getZ()));
                }
                if(!active) {
                   world.spawnEntity(hologram);
                   active = true;
                }
                else {
                   hologram.setPosition(new Vec3d(0, -64,0));
                   hologram.kill();
                   hologram = new Diansu_Supervivens_Holo(modMobs.DSH,world);
                   hologram.setPosition(new Vec3d(pos.toCenterPos().getX(), pos.getY()+1, pos.toCenterPos().getZ()));
                   active = false;
                }
                player.sendMessage(Text.of("Test"), false);
            }
        }

        return ActionResult.SUCCESS;
    }


}

