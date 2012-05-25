package me.ashtheking.dragons.gui;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import me.ashtheking.dragons.magic.ItemShout;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.World;
import net.minecraft.src.mod_Dragon;

import org.lwjgl.opengl.GL11;

public class GNGStats extends GuiScreen {

	public static List<Number> getValuesInKey(Map<String, String> map,
			String key) {
		String s = map.get(key);
		String as[] = s.split(",");
		for (int j = 0; j < as.length; j++)
			as[j] = as[j].trim();
		ArrayList<Number> arraylist = new ArrayList<Number>();
		for (String s1 : as)
			try {
				arraylist.add(Integer.valueOf(s1));
			} catch (NumberFormatException numberformatexception) {
				try {
					arraylist.add(Double.valueOf(s1));
				} catch (NumberFormatException numberformatexception1) {
					numberformatexception1.printStackTrace();
				}
			}
		return arraylist;
	}

	private World world;

	private GuiScreen parentScreen;

	private Map<String, String> stats;

	public GNGStats(World theWorld, EntityPlayer player) {
		this(null, theWorld, player);
	}

	public GNGStats(GuiScreen guiScreen, World theWorld, EntityPlayer player) {
		parentScreen = guiScreen;
		world = theWorld;
		stats = new LinkedHashMap<String, String>();
		stats.put("Health", player.getHealth() + "," + player.getMaxHealth());
		stats.put("Magicka", 0 + "," + 100);
		stats.put("Experience", player.experience + ","
				+ player.experienceLevel);
		// stats.put("Health", Integer.toString(genuineentitynpc.health) + "," +
		// Integer.toString(genuineentitynpc.npcMaxHealth));
		// stats.put("Mana", Integer.toString(genuineentitynpc.npcCurrentMana) +
		// "," + Integer.toString(genuineentitynpc.npcMaxMana));
		// stats.put("Experience",
		// Integer.toString(genuineentitynpc.npcCurrentExp) + "," +
		// Integer.toString(genuineentitynpc.npcMaxExp));
		// stats.put("Strength",
		// Integer.toString(genuineentitynpc.npcCurrentStrength) + "," +
		// Integer.toString(genuineentitynpc.npcMaxStrength));
		// stats.put("Speed", Float.toString(genuineentitynpc.moveSpeed) + "," +
		// Float.toString(genuineentitynpc.npcMaxSpeed));
		// stats.put("Size", Float.toString(genuineentitynpc.npcCurrentSize) +
		// "," + Float.toString(genuineentitynpc.npcMaxSize));
	}

	@Override
	protected void actionPerformed(GuiButton guibutton) {
		if (!guibutton.enabled)
			return;
		// if (guibutton.id == 0)
		// mc.displayGuiScreen(new GNGOptions(this));
		// if (guibutton.id == 1)
		// mc.displayGuiScreen(new GNGRename(this, world));
		if (guibutton.id == 1) {
			if (mod_Dragon.currentShout + 1 < mod_Dragon.maxShouts)
				mod_Dragon.currentShout++;
			else
				mod_Dragon.currentShout = 0;
			guibutton.displayString = ItemShout.shoutNames[mod_Dragon.currentShout]
					+ (ItemShout.shoutEnabled[mod_Dragon.currentShout] ? ""
							: "(Disabled)");
			// guibutton.enabled =
			// ItemShout.shoutEnabled[mod_Dragon.currentShout];
		}
		if (guibutton.id == 2)
			ItemShout.shoutEnabled[mod_Dragon.currentShout] = true;
		if (guibutton.id == 3)
			mc.displayGuiScreen(parentScreen);
	}

	protected void drawCenterImage(int imageHeight, int imageWidth, String image) {
		GL11.glDisable(2896 /* GL_LIGHTING */);
		GL11.glDisable(2912 /* GL_FOG */);
		GL11.glBindTexture(3553 /* GL_TEXTURE_2D */,
				mc.renderEngine.getTexture(image));
		int j = (width - imageWidth) / 2;
		int k = (height - imageHeight) / 2;
		// This is where you can set the color manually.
		GL11.glColor3d(1.0F, 1.0F, 1.0F);
		drawTexturedModalRect(j, k + (int) (imageHeight * 1F / 16F), 0, 0,
				imageWidth, (int) (imageHeight * 4F / 8F));
		drawTexturedModalRect(j, k + (int) (imageHeight * 9F / 16F), 0,
				(int) (imageHeight * 5F / 8F), imageWidth,
				(int) (imageHeight * 3F / 8F));
	}

	@Override
	public void drawScreen(int i, int j, float f) {
		drawDefaultBackground();
		// ((GuiButton) controlList.get(1)).enabled =
		// !ItemShout.shoutEnabled[mod_Dragon.currentShout];
		drawStats(i, j);
		// You might need to change these to use your mod's data structure.
		drawCenterImage(256, 256, "/dragons/gui/window.png");
		// drawCenteredString(fontRenderer, npc.npcName + " Level " +
		// npc.npcLevel, width / 2, height / 2 - 105, 0xFFFFFF);
		GL11.glTranslatef(0, 0, 15);
		//drawCenteredString(fontRenderer,
			//	ItemShout.shoutNames[mod_Dragon.currentShout], width / 2,
				//height / 2 + 53, 0xFFFFFF);
		GL11.glTranslatef(0, 0, -15);
		super.drawScreen(i, j, f);
	}

	protected void drawStatBar(double currentStat, double maxStat, int type) {
		double percent = currentStat / maxStat;
		int width = 179;
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(3553);
		// You might need to change these to use your mod's data structure.
		GL11.glBindTexture(3553,
				mc.renderEngine.getTexture("/dragons/gui/icons.png"));
		GL11.glTranslatef(0F, 5F, 0F);
		drawTexturedModalRect(-181 / 2, 0, 0, 0, 181, 7);
		// Thses are the colors for the status bars.
		if (percent > 0) {
			if (type == 0)
				GL11.glColor3d(0, 1, 0);
			if (type == 1)
				GL11.glColor3d(0, 3D / 4D, 1);
			if (type == 2)
				GL11.glColor3d(1, 1, 0);
			if (type == 3)
				GL11.glColor3d(1, 0.25D, 0.25D);
			if (type == 4)
				GL11.glColor3d(0.75D, 0, 1);
			if (type == 5)
				GL11.glColor3d(145D / 256D, 90D / 256D, 0);
			drawTexturedModalRect(-(181 / 2) + 1, 1, 0, 7,
					(int) (percent > 1 ? width : percent * width), 5);
		}
		GL11.glColor3d(1, 1, 1);
		for (int i = 1; i < percent; i++)
			drawTexturedModalRect(-(181 / 2) + 1, 1, 0,
					(int) (System.currentTimeMillis() / 10 % 180) + 12,
					(int) (percent - i > 1 ? width : (percent - i) * width), 5);
		GL11.glTranslatef(0F, -5F, 0F);
	}

	protected void drawStats(int i, int j) {
		GL11.glDisable(2896 /* GL_LIGHTING */);
		GL11.glDisable(2912 /* GL_FOG */);
		GL11.glColor4f(1, 1, 1, 1);
		for (int counter = 0; counter < stats.size(); counter++) {
			String key = "";
			switch (counter) {
			case 0:
				key = "Health";
				break;
			case 1:
				key = "Magicka";
				break;
			case 2:
				key = "Experience";
				break;
			case 3:
				key = "Strength";
				break;
			// case 4:
			// key = "Speed";
			// break;
			// case 5:
			// key = "Size";
			// break;
			default:
				key = "new";
			}
			List<Number> stringArray = getValuesInKey(stats, key);
			int buttonPosX = width / 2 - 200;
			int buttonPosY = height / 2 - 90 + 25 * counter;
			int textPosX = width / 2 - 163;
			int textPosY = height / 2 - 84 + 25 * counter;
			int offsetX = 0;
			int offsetY = 20 * counter % 40 + 20;
			// You might need to change these to use your mod's data structure.
			GL11.glBindTexture(3553,
					mc.renderEngine.getTexture("/dragons/gui/buttons.png"));
			// More color options.
			GL11.glColor3d(1.0F, 1.0F, 1.0F);
			drawTexturedModalRect(buttonPosX, buttonPosY, offsetX, offsetY,
					100, 20);
			drawCenteredString(fontRenderer, key, textPosX, textPosY, 0xFFFFFF);
			GL11.glTranslated(width / 2, textPosY, 1);
			try {
				drawStatBar(Integer.valueOf(stringArray.get(0).toString()),
						Integer.valueOf(stringArray.get(1).toString()), counter);
			} catch (NumberFormatException numberformatexception) {
				try {
					drawStatBar(Float.valueOf(stringArray.get(0).toString()),
							Float.valueOf(stringArray.get(1).toString()),
							counter);
				} catch (NumberFormatException numberformatexception1) {
					try {
						drawStatBar(
								Double.valueOf(stringArray.get(0).toString()),
								Double.valueOf(stringArray.get(1).toString()),
								counter);
					} catch (NumberFormatException numberformatexception2) {
						numberformatexception2.printStackTrace();
					}
				}
			}
			drawCenteredString(fontRenderer, stringArray.get(0).toString()
					+ " / " + stringArray.get(1).toString(), 0, -4, 0xFFFFFF);
			GL11.glTranslated(-(width / 2), -textPosY, -1);
		}
	}

	@Override
	public void initGui() {
		// controlList.add(new GNGButton(0, width / 2 - 80, height / 2
		// + 80, 50, 20, "Options"));
		// controlList.add(new GNGButton(1, width / 2 - 25, height / 2
		// + 80, 50, 20, "Rename"));
		controlList.add(new GNGButton(1, width / 2 - 45, height / 2 + 47, 50,
				20, ""));
		controlList.add(new GNGButton(2, width / 2 + 50, height / 2 + 47, 50,
				20, "Unlock"));
		controlList.add(new GNGButton(3, width / 2 + 30, height / 2 + 80, 50,
				20, "Done"));
		((GuiButton) controlList.get(1)).enabled = !ItemShout.shoutEnabled[mod_Dragon.currentShout];
	}
}
