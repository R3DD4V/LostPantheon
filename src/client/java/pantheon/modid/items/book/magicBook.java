package pantheon.modid.items.book;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.BookScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import pantheon.modid.LostPantheon;
import pantheon.modid.LostPantheonClient;
import pantheon.modid.items.book.MagicComponents.Bolt;
import pantheon.modid.items.book.MagicComponents.Focus;
import pantheon.modid.items.book.MagicComponents.Self;

import java.util.ArrayList;

public class magicBook extends Item {
    public bookScreen screen;
    NbtCompound nbt = new NbtCompound();
    Focus[] foci = new Focus[] {new Focus(0, 0, 40, 40, Text.empty()),
    new Bolt(0, 0, 40, 40, Text.empty()),
    new Self(0, 0, 40, 40, Text.empty())} ;



    public magicBook(FabricItemSettings settings) {
        super(settings);

        nbt.putIntArray("XCoords", new int[]{-1, -1, -1});
        nbt.putIntArray("YCoords", new int[]{-1, -1, -1});
        nbt.putIntArray("Unlocked", new int[]{1, 1, 0});
    }



    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ArrayList<Focus> aFoci = new ArrayList<>();
        for (int i = 0; i < nbt.getIntArray("Unlocked").length; i++) {
            if (nbt.getIntArray("Unlocked")[i] == 1) {
                Focus f = foci[i];
                aFoci.add(f.copy(nbt.getIntArray("XCoords")[i], nbt.getIntArray("YCoords")[i]));
            }
        }
        openBookScreen(MinecraftClient.getInstance(), aFoci, user.getStackInHand(hand));
        nbt.putIntArray("XCoords", screen.Xcoords());
        nbt.putIntArray("YCoords", screen.Ycoords());
        user.getStackInHand(hand).setNbt(nbt);
        return TypedActionResult.success(user.getStackInHand(hand));

    }
    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        nbt.putIntArray("XCoords", screen.Xcoords());
        nbt.putIntArray("YCoords", screen.Ycoords());
    }

    public void close() {
        nbt.putIntArray("XCoords", screen.Xcoords());
        nbt.putIntArray("YCoords", screen.Ycoords());
    }



    public void openBookScreen(MinecraftClient client, ArrayList<Focus> foci, ItemStack stack) {
        // Schedule the screen opening on the client thread
        client.execute(() -> {
            screen = new bookScreen(Text.of("Magic Book"), foci, stack); // Your book screen instance
            client.setScreen(screen);
        });
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (this.screen != null) {
            nbt.putIntArray("XCoords", screen.Xcoords());
            nbt.putIntArray("YCoords", screen.Ycoords());
            stack.setNbt(nbt);
        }
    }





}
