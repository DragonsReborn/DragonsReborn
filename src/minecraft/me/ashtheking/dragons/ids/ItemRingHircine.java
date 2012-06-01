package me.ashtheking.dragons.ids;

import java.util.Random;

import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.World;
import net.minecraft.src.mod_Dragon;

public class ItemRingHircine extends Item {

	public ItemRingHircine(int par1) {
		super(par1);

	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		mod_Dragon.isWerewolf = !mod_Dragon.isWerewolf;
		stack.damageItem(1, player);
		new Thread(new Watcher(player)).start();
		return stack;
	}

	private class Watcher implements Runnable {
		EntityPlayer player;
		String texture;

		public Watcher(EntityPlayer player) {
			this.player = player;
			try {
				this.texture = (String) ModLoader.getPrivateValue(EntityLiving.class, player,
						"texture");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void run() {
			try {
				smoke();
				while (mod_Dragon.isWerewolf) {
					ModLoader.setPrivateValue(EntityLiving.class, player, "texture",
							"/mob/werewolf.png");					
				}
				ModLoader.setPrivateValue(EntityLiving.class, player, "texture", "/mob/char.png");
				smoke();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private void smoke() {
			Random rand = player.worldObj.rand;
			for (int x = 0; x < 30; x++) {
				double d = rand.nextGaussian() * 0.02D;
				double d1 = rand.nextGaussian() * 0.02D;
				double d2 = rand.nextGaussian() * 0.02D;
				player.worldObj.spawnParticle("smoke", (player.posX + (double) (rand.nextFloat()
						* player.width * 2.0F))
						- (double) player.width, player.posY - 0.5D
						+ (double) (rand.nextFloat() * player.height),
						(player.posZ + (double) (rand.nextFloat() * player.width * 2.0F))
								- (double) player.width, d, d1, d2);
			}
		}
	}
}
