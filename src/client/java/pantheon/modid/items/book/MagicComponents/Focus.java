package pantheon.modid.items.book.MagicComponents;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.ToggleButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class Focus extends ToggleButtonWidget {
    private Identifier Texture = new Identifier("minecraft", "textures/block/andesite.png");
    public Focus(int x, int y, int width, int height, Text message) {
        super(x, y, width, height, false);
    }

    public Focus copy(int x, int y) {
        return new Focus(x, y, this.width, this.height, Text.empty());
    }


    @Override
    public void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {

        context.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        if (!this.toggled) {
            setX((int)((this.getX()-width/2)/44.0+0.5)*44+width/2);
            setY((int)((this.getY()-height/2)/44.0+0.5)*44+height/2);
            context.drawTexture(Texture, getX(), getY(), 0,0,width,height,32, 32);
        }
        else {
            setX(mouseX);
            setY(mouseY);
            context.setShaderColor(0.9F, 0.9F, 0.9F, this.alpha);
            context.drawTexture(Texture, mouseX-width/2, mouseY-width/2, 0, 0, width,height,32, 32);
        }



    }

    @Override
    public void appendClickableNarrations(NarrationMessageBuilder builder) {
        this.appendDefaultNarrations(builder);
    }

    @Override
    protected void onDrag(double mouseX, double mouseY, double deltaX, double deltaY) {
        super.onDrag(mouseX, mouseY, deltaX, deltaY);
        setX((int) (mouseX+deltaX));
        setY((int) (mouseY+deltaY));
        System.out.println(1);
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        super.onClick(mouseX, mouseY);
        setToggled(!toggled);
    }
}
