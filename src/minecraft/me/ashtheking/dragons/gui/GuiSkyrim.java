package me.ashtheking.dragons.gui;

import me.ashtheking.dragons.world.Hold;
import me.ashtheking.dragons.world.HoldManager;
import me.ashtheking.dragons.world.Location;
import me.ashtheking.dragons.world.QuestHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Gui;
import net.minecraft.src.ModLoader;
import net.minecraft.src.RenderHelper;
import net.minecraft.src.ScaledResolution;
import net.minecraft.src.World;
import net.minecraft.src.mod_Dragon;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class GuiSkyrim extends Gui {
	/** Holds the instance of the game (Minecraft) */
	private Minecraft theGame;

	/** Holds the latest width scaled to fit the game window. */
	private int achievementWindowWidth;

	/** Holds the latest height scaled to fit the game window. */
	private int achievementWindowHeight;

	/** Holds the achievement that will be displayed on the GUI. */
	private String title;
	private String description;
	public String currentHold;
	private int bounty;
	private long achievementTime;

	/**
	 * Holds a instance of RenderItem, used to draw the achievement icons on
	 * screen (is based on ItemStack)
	 */
	private boolean haveAchiement;

	public GuiSkyrim(Minecraft par1Minecraft) {
		theGame = par1Minecraft;
	}

	public void startQuest(QuestHandler quest) {
		title = "Started Quest: " + quest.title;
		description = quest.description;
		achievementTime = System.currentTimeMillis();
		haveAchiement = true;
	}

	public void enterHold(Hold hold) {
		title = "Entered Hold: " + hold.name;
		description = "Bounty: " + hold.bounty;
		achievementTime = System.currentTimeMillis();
		currentHold = hold.name;
		bounty = hold.bounty;
		haveAchiement = true;
	}

	public void setNotices(String title, String description) {
		this.title = title;
		this.description = description;
		haveAchiement = true;
	}

	/**
	 * Update the display of the achievement window to match the game window.
	 */
	private void updateAchievementWindowScale() {
		GL11.glViewport(0, 0, theGame.displayWidth, theGame.displayHeight);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		achievementWindowWidth = theGame.displayWidth;
		achievementWindowHeight = theGame.displayHeight;
		ScaledResolution scaledresolution = new ScaledResolution(
				theGame.gameSettings, theGame.displayWidth,
				theGame.displayHeight);
		achievementWindowWidth = scaledresolution.getScaledWidth();
		achievementWindowHeight = scaledresolution.getScaledHeight();
		GL11.glClear(256);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0.0D, achievementWindowWidth, achievementWindowHeight,
				0.0D, 1000D, 3000D);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		GL11.glTranslatef(0.0F, 0.0F, -2000F);
	}

	public void update() {
		EntityPlayer p = ModLoader.getMinecraftInstance().thePlayer;
		World world = p.worldObj;
		Location loc = new Location((int) p.posX, (int) p.posY, (int) p.posZ);
		if (mod_Dragon.holdManager == null
				|| mod_Dragon.holdManager.world != world)
			mod_Dragon.holdManager = new HoldManager(world);
		if (world != null && mod_Dragon.holdManager.world != null) {
			Hold h = mod_Dragon.holdManager.getHold(loc);
			if (h != null) {
				bounty = h.bounty;
				currentHold = h.name;

			} else {
				bounty = -1;
				currentHold = "Wilderness";
			}
		}
	}

	/**
	 * Updates the small achievement tooltip window, showing a queued
	 * achievement if is needed.
	 */
	public void updateAchievementWindow() {
		int k = theGame.renderEngine.getTexture("/achievement/bg.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, k);
		GL11.glDisable(GL11.GL_LIGHTING);
		drawTexturedModalRect(10, 7, 96, 202, 160, 32);
		update();
		theGame.fontRenderer.drawString(currentHold, 14, 7, -1);
		theGame.fontRenderer.drawString("Bounty: " + bounty, 14, 17, -1);
		if (title == null || achievementTime == 0L) {
			return;
		}

		double d = (double) (System.currentTimeMillis() - achievementTime) / 3000D;

		if (!haveAchiement && (d < 0.0D || d > 1.0D)) {
			achievementTime = 0L;
			return;
		}

		updateAchievementWindowScale();
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		double d1 = d * 2D;

		if (d1 > 1.0D) {
			d1 = 2D - d1;
		}

		d1 *= 4D;
		d1 = 1.0D - d1;

		if (d1 < 0.0D) {
			d1 = 0.0D;
		}

		d1 *= d1;
		d1 *= d1;
		int i = achievementWindowWidth - 160;
		int j = 0 - (int) (d1 * 36D);
		drawTexturedModalRect(i, j, 96, 202, 160, 32);

		if (haveAchiement) {
			theGame.fontRenderer.drawString(title, i + 2, j + 7, -1);
			theGame.fontRenderer.drawString(description, i + 2, j + 18, -1);
		}

		RenderHelper.enableGUIStandardItemLighting();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDepthMask(true);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
	}
}
