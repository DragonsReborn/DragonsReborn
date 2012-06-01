package me.ashtheking.dragons.magic;

import java.util.List;
import java.util.Random;

import me.ashtheking.dragons.meta.Fireball;
import me.ashtheking.dragons.mob.Dragon;
import net.minecraft.client.Minecraft;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityCreature;
import net.minecraft.src.EntityDragon;
import net.minecraft.src.EntityLightningBolt;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModLoader;
import net.minecraft.src.Vec3D;
import net.minecraft.src.World;

public class Shouts {
	public static Random rand = new Random();
	public static Minecraft mc = ModLoader.getMinecraftInstance();

	public static void FusRoDah(World worldObj, EntityPlayer player, AxisAlignedBB boundingBox) {
		List<Entity> targets = getNearbyEntities(worldObj, boundingBox);
		mc.ingameGUI.addChatMessage("<" + player.username + ">:§3 FUS RO DAH");
		for (Entity e : targets)
			if (player.canEntityBeSeen(e)) {
				knockBack(e, 2, -(e.posX - player.posX), -(e.posZ - player.posZ));
				for (int x = 0; x < 100; x++)
					worldObj.spawnParticle("portal", e.posX + rand.nextDouble() * 10D, e.posY
							+ rand.nextDouble() * 10D, e.posZ + rand.nextDouble() * 10D, 0.0D,
							0.0D, 0.0D);
			}
	}

	public static void StrunBahQo(World world, EntityPlayer player) {
		for (Entity el : getNearbyEntities(world, player.boundingBox))
			if (!(el == player))
				if (player.canEntityBeSeen(el))
					world.spawnEntityInWorld(new EntityLightningBolt(world, el.posX, el.posY,
							el.posZ));
		mc.ingameGUI.addChatMessage("<" + player.username + ">:§3 STRUN BAH QO");
	}

	public static void JoorZahFrul(final World world, final EntityPlayer player) {
		new Thread(new Runnable() {
			public void run() {
				List<Entity> targets = getNearbyEntities(world, player.boundingBox);
				for (int x = 0; x < 1000; x++) {
					try {
						for (Entity e : targets)
							if (e instanceof EntityDragon || e instanceof Dragon) {
								e.motionY -= 15;
								for (int k = 0; k < 100; k++)
									world.spawnParticle("portal", e.posX + rand.nextDouble() * 3D,
											e.posY + rand.nextDouble() * 3D,
											e.posZ + rand.nextDouble() * 3D, 0.0D, 0.0D, 0.0D);
							}
						Thread.sleep(10);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
		}).start();
		mc.ingameGUI.addChatMessage("<" + player.username + ">:§3 JOOR ZAH FRUL");
	}

	public static void LokVahKoor(World world, EntityPlayer player) {
		mc.ingameGUI.addChatMessage("<" + player.username + ">:§3 LOK VAH KOOR");
		world.setRainStrength(0F);
	}

	public static void YolToorShol(World world, final EntityPlayer player) {
		List<Entity> targets = getNearbyEntities(world, player.boundingBox);
		for (Entity e : targets)
			if (player.canEntityBeSeen(e))
				Magic.Destruction.fireball(world, e.posX, e.posY - 1, e.posZ);
		mc.ingameGUI.addChatMessage("<" + player.username + ">:§3 YOL TOOR SHOL");
	}

	public static void KaanDremOv(final World world, EntityPlayer player) {
		final List<Entity> targets = getNearbyEntities(world, player.boundingBox);
		mc.ingameGUI.addChatMessage("<" + player.username + ">:§3 KAAN DREM OV");
		new Thread(new Runnable() {
			public void run() {
				try {
					for (int x = 0; x < 500; x++) {
						for (Entity e : targets)
							if (e instanceof EntityCreature) {
								((EntityCreature) e).setTarget(null);
								for (int k = 0; k < 10; k++)
									world.spawnParticle("portal", e.posX + rand.nextDouble() * 3D,
											e.posY + rand.nextDouble() * 3D,
											e.posZ + rand.nextDouble() * 3D, 0.0D, 0.0D, 0.0D);
							}
						Thread.sleep(10);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public static void WuldNahKest(World world, EntityPlayer player) {
		player.motionX *= 2.5D;
		player.motionZ *= 2.5D;
		mc.ingameGUI.addChatMessage("<" + player.username + ">:§3 WULD NAH KEST");
	}

	public static List<Entity> getNearbyEntities(World worldObj, AxisAlignedBB boundingBox) {
		float f = 32F;
		List list = worldObj.getEntitiesWithinAABB(EntityLiving.class, boundingBox.expand(f, f, f));
		for (int x = 0; x < list.size(); x++)
			if (list.get(x) instanceof EntityPlayer)
				list.remove(x);
		return (List<Entity>) list;
	}

	public static void knockBack(Entity entity, int i, double d, double d1) {
		entity.isAirBorne = true;
		float f = MathHelper.sqrt_double(d * d + d1 * d1);
		float f1 = 1.0F * i;
		entity.motionX /= 2D;
		entity.motionY /= 2D;
		entity.motionZ /= 2D;
		entity.motionX -= (d / (double) f) * (double) f1;
		entity.motionY += 0.4D;
		entity.motionZ -= (d1 / (double) f) * (double) f1;
		if (entity.motionY > 0.4D)
			entity.motionY = 0.4D;
	}

	// public static void fireball(World worldObj, EntityLiving shooter) {
	// Fireball entityfireball = new Fireball(worldObj, shooter, shooter.posX,
	// shooter.posY,
	// shooter.posZ);
	// double d8 = 2D;
	// Vec3D vec3d = shooter.getLook(1.0F);
	// entityfireball.posX = shooter.posX + vec3d.xCoord * d8;
	// entityfireball.posY = shooter.posY + (double) (shooter.height / 2.0F) +
	// 0.5D;
	// entityfireball.posZ = shooter.posZ + vec3d.zCoord * d8;
	// entityfireball.onUpdate();
	// worldObj.spawnEntityInWorld(entityfireball);
	// }
}