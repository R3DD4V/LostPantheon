package pantheon.modid.items.book;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import pantheon.modid.items.book.MagicComponents.Focus;

import java.util.ArrayList;

public class bookScreen extends Screen {

    private static final Identifier TEXTURE = new Identifier("textures/gui/book.png");
    public ArrayList<Focus> foci = new ArrayList<>();
    private ItemStack Boss;

    protected bookScreen(Text title, ArrayList<Focus> foci, ItemStack Boss) {
        super(title);
        this.foci = foci;
        this.Boss = Boss;
    }


    @Override
    protected void init() {
        for (Focus f : foci) {
            this.addDrawableChild(f);
        }

    }

    @Override
    public void close() {
        super.close();
    }

    public void noBoss() {
        Boss = null;
    }

    public ArrayList<Integer> Xcoords() {
        ArrayList<Integer> xcoords = new ArrayList<>();
        for (Focus f : foci) {
            xcoords.add(f.getX());
        }
        return xcoords;
    }
    public ArrayList<Integer> Ycoords() {
        ArrayList<Integer> ycoords = new ArrayList<>();
        for (Focus f : foci) {
            ycoords.add(f.getY());
        }
        return ycoords;
    }


}
