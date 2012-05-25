package me.ashtheking.dragons.gui;

import me.ashtheking.dragons.magic.ItemShout;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.ModLoader;
import net.minecraft.src.mod_Dragon;

import org.lwjgl.opengl.GL11;

public class GNGOptions extends GuiScreen {

	private GNGOptionsSlider gNosRoam;
	private GNGOptionsSlider gNosFollow;
	private String message1;
	private String message2;
	private String message3;
	private GuiButton protectYes;
	private GuiButton protectNo;
	private GuiScreen parentScreen;

	public GNGOptions(GuiScreen guiScreen) {
		super();
		parentScreen = guiScreen;
	}

	@Override
	protected void actionPerformed(GuiButton guibutton) {
		if (!guibutton.enabled)
			return;
		//		if (guibutton.id == 0)
		//			npc.canProtect = true;
		//		if (guibutton.id == 1)
		//			npc.canProtect = false;
		//		if (guibutton.id == 4) {
		//			npc.canProtect = true;
		//			npc.roamingDistance = 8;
		//			npc.followDistance = 8;
		//			gNosRoam.sliderValue = 8;
		//			gNosRoam.displayString = "8";
		//			gNosFollow.sliderValue = 8;
		//			gNosFollow.displayString = "8";
		//		}
		if (guibutton.id == 5) {
			if(mod_Dragon.currentShout + 1 < mod_Dragon.maxShouts)
				mod_Dragon.currentShout++;
			else
				mod_Dragon.currentShout = 0;
			guibutton.displayString = ItemShout.shoutNames[mod_Dragon.currentShout];
		}
		if (guibutton.id == 6)
			mc.displayGuiScreen(parentScreen);
	}

	public void drawCenterImage(int imageHeight, int imageWidth, String image) {
		GL11.glDisable(2896 /* GL_LIGHTING */);
		GL11.glDisable(2912 /* GL_FOG */);
		GL11.glBindTexture(3553 /* GL_TEXTURE_2D */, mc.renderEngine.getTexture(image));
		int j = (width - imageWidth) / 2;
		int k = (height - imageHeight) / 2;
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		drawTexturedModalRect(j, k + (int) (imageHeight * 1F / 16F), 0, 0, imageWidth, (int) (imageHeight * 4F / 8F));
		drawTexturedModalRect(j, k + (int) (imageHeight * 9F / 16F), 0, (int) (imageHeight * 5F / 8F), imageWidth, (int) (imageHeight * 3F / 8F));
	}

	@Override
	public void drawScreen(int i, int j, float f) {
		drawDefaultBackground();
		drawCenterImage(256, 256, "/dragons/gui/window.png");
		drawCenteredString(fontRenderer, message1, width / 2, height / 2 - 100, 0xFFFFFF);
		drawCenteredString(fontRenderer, message2, width / 2, height / 2 + 50 - 100, 0xFFFFFF);
		drawCenteredString(fontRenderer, message3, width / 2, height / 2 + 100 - 100, 0xFFFFFF);
		//		if (npc.canProtect) {
		//			protectYes.enabled = false;
		//			protectNo.enabled = true;
		//		} else {
		//			protectYes.enabled = true;
		//			protectNo.enabled = false;
		//		}
		GL11.glTranslatef(0, 0, 15);
		drawCenteredString(fontRenderer, ItemShout.shoutNames[mod_Dragon.currentShout], width / 2, height / 2 + 53, 0xFFFFFF);
		GL11.glTranslatef(0, 0, -15);
		super.drawScreen(i, j, f);
	}

	@Override
	public void initGui() {
//		protectYes = new GNGButton(0, width / 2 - 70, height / 2 + 15 - 100, 50, 20, "Yes");
//		protectNo = new GNGButton(1, width / 2 + 20, height / 2 + 15 - 100, 50, 20, "No");
//		//		gNosRoam = new GNGOptionsSlider(2, width / 2 - 75, height / 2 - 35, npc.roamingDistance == 0 ? "Disabled" : Integer.toString(npc.roamingDistance), npc.roamingDistance, npc, 0);
//		//		gNosFollow = new GNGOptionsSlider(3, width / 2 - 75, height / 2 + 15, npc.followDistance == 0 ? "Disabled" : Integer.toString(npc.followDistance), npc.followDistance, npc, 1);
//		controlList.add(protectYes);
//		controlList.add(protectNo);
		//		controlList.add(gNosRoam);
		//		controlList.add(gNosFollow);
		//controlList.add(new GNGOptionsSlider(0, width / 2 - 75, height / 2 - 35, "Magicka", 100, 0));
		controlList.add(new GNGButton(4, width / 2 - 80, height / 2 + 80, 50, 20, "Reset"));
		controlList.add(new GNGButton(5, width / 2 - 45, height / 2 + 47, 90, 20, ""));
		controlList.add(new GNGButton(6, width / 2 + 30, height / 2 + 80, 50, 20, "Done"));
		mc = ModLoader.getMinecraftInstance();
	}
}
