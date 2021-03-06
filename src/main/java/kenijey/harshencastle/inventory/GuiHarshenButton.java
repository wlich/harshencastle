package kenijey.harshencastle.inventory;

import org.lwjgl.opengl.GL11;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.network.HarshenNetwork;
import kenijey.harshencastle.network.packets.MessagePacketOpenInv;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;

public class GuiHarshenButton extends GuiButton {

	private final GuiContainer parentGui;
	private final boolean directionToInventory;

	public GuiHarshenButton(int buttonId, GuiContainer parentGui, int x, int y, int width, int height, String buttonText, boolean directionToInventory) {
		super(buttonId, x, parentGui.getGuiTop() + y, width, height, buttonText);
		this.parentGui = parentGui;
		this.directionToInventory = directionToInventory;
	}

	@Override
	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
		boolean pressed = super.mousePressed(mc, mouseX - this.parentGui.getGuiLeft(), mouseY);
		if (pressed)
			if(directionToInventory)
			{
				mc.player.openGui(HarshenCastle.instance, GuiHandler.CUSTOMINVENTORY, mc.player.world, (int)mc.player.posX, (int)mc.player.posY, (int)mc.player.posZ);
				HarshenNetwork.sendToServer(new MessagePacketOpenInv());
			}
			else
				mc.displayGuiScreen(new GuiInventory(mc.player));
		return pressed;
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
	{
		if (this.visible)
		{
			int x = this.x + this.parentGui.getGuiLeft();
			FontRenderer fontrenderer = mc.fontRenderer;
			int k = this.getHoverState(this.hovered);
			mc.getTextureManager().bindTexture(new ResourceLocation(HarshenCastle.MODID, "textures/gui/accessory_button" + (k==1? "" : "_over") + ".png"));
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			this.hovered = mouseX >= x && mouseY >= this.y && mouseX < x + this.width && mouseY < this.y + this.height;
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GlStateManager.pushMatrix();
			GlStateManager.translate(0, 0, 200);
			this.drawModalRectWithCustomSizedTexture(x, this.y, 0, 0, 24, 24, 24, 24);
			if (k!=1)
				this.drawCenteredString(fontrenderer, I18n.translateToLocalFormatted(this.displayString), x + 5, this.y + this.height, 0xffffff);
			GlStateManager.popMatrix();

			this.mouseDragged(mc, mouseX, mouseY);
		}
	}
}
