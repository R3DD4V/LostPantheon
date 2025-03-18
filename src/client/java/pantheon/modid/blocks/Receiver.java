package pantheon.modid.blocks;

import net.minecraft.block.*;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.StairShape;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class Receiver extends HorizontalFacingBlock {
    private String ID;



    public Receiver(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity player, ItemStack itemStack) {
        if (!world.isClient) {
            String name = player.getEntityName();
            ArrayList converter = new ArrayList();
            ArrayList letters = new ArrayList();
            ArrayList alphabet = new ArrayList();
            ArrayList convertedLetters = new ArrayList();
            ArrayList outputNumbers = new ArrayList();
            ArrayList output = new ArrayList();
            String stringAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String nameSymbols = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_";
            for (int i = 0; i < nameSymbols.length(); i++) {
                converter.add(nameSymbols.charAt(i));
            }
            for (int i = 0; i < stringAlphabet.length(); i++) {
                alphabet.add(stringAlphabet.charAt(i));
            }
            for (int i = 0; i < name.length(); i++) {
                letters.add(name.charAt(i));
            }
            for (int i = 0; i < 16; i++) {
                if (i < letters.size()) {
                    convertedLetters.add(i, 1 + converter.indexOf(letters.get(i)));
                } else {
                    convertedLetters.add(i, 0);
                }
            }
            for (int i = 0; i < 4; i++) {
                outputNumbers.add(i, convertedLetters.get(i));
            }
            for (int i = 4; i < 8; i++) {
                for (int x = 0; x < (int) convertedLetters.get(i); ) {
                    for (int y = 0; y < 4; y++) {
                        if (x < (int) convertedLetters.get(i)) {
                            outputNumbers.set(y, (int) outputNumbers.get(y) + 1);
                            x += 1;
                        }
                    }
                }
            }
            for (int i = 0; i < 4; i++) {
                outputNumbers.set(i, (int) outputNumbers.get(i) + (int) convertedLetters.get(i + 8));
                outputNumbers.set(i, (int) outputNumbers.get(i) - (int) convertedLetters.get(i + 12));
                while ((int) outputNumbers.get(i) > 26) {
                    outputNumbers.set(i, (int) outputNumbers.get(i) - 26);
                }

                output.add(alphabet.get((int) outputNumbers.get(i) - 1));


            }

            super.onPlaced(world, pos, state, player, itemStack);
            player.sendMessage(Text.of(String.valueOf(output)));
            player.isPushable();
            player.isFallFlying();
        }
    }
}
