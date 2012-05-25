package me.ashtheking.dragons.magic;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Random;

import me.ashtheking.dragons.meta.FireExplosion;
import me.ashtheking.dragons.meta.IceExplosion;
import net.minecraft.src.Block;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityTameable;
import net.minecraft.src.EnumMovingObjectType;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.World;
import net.minecraft.src.mod_Dragon;

public class Magic {

	public static class Alteration {

		public static void mageList(World world, Entity follow, int duration, EntityPlayer owner) {
			new Thread(new MageLight(follow, duration)).start();
		}

		private static class MageLight implements Runnable {

			public Entity e;
			public int duration;

			public MageLight(Entity e, int duration) {
				this.e = e;
				this.duration = duration;
			}

			@Override
			public void run() {
				try {
					World world = e.worldObj;
					double x = e.posX, y = e.posY, z = e.posZ;
					for (int i = 0; i < duration * 1000; i++) {
						try {
							if (e.isDead)
								return;
							x = e.posX;
							y = e.posY;
							z = e.posZ;
							if (e.motionX == 0 && e.motionY == 0 && e.motionZ == 0)
								continue;
							new Thread(new BlockWatcher(world, x, y, z, mod_Dragon.lightId, 2))
									.start();
							Thread.sleep(1);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					e.setDead();
					x = e.posX;
					y = e.posY;
					z = e.posZ;
					for (int k = 0; k < 10000; k++)
						world.spawnParticle("portal", x + (world.rand.nextDouble() * 3.0)
								* (world.rand.nextBoolean() ? -1 : 1), y
								+ (world.rand.nextDouble() * 3.0)
								* (world.rand.nextBoolean() ? -1 : 1), z
								+ (world.rand.nextDouble() * 3.0)
								* (world.rand.nextBoolean() ? -1 : 1), 0, 0, 0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		private static class BlockWatcher implements Runnable {

			private World world;
			private double x, y, z;
			private int id, duration;

			public BlockWatcher(World world, double x2, double y2, double z2, int id, int duration) {
				this.world = world;
				this.x = x2;
				this.y = y2;
				this.z = z2;
				this.id = id;
				this.duration = duration;
			}

			@Override
			public void run() {
				world.setBlock((int) x, (int) y, (int) z, id);
				for (int k = 0; k < duration; k++)
					try {
						Thread.sleep(200);
					} catch (Exception e) {
						e.printStackTrace();
					}
				world.setBlock((int) x, (int) y, (int) z, 0);
			}

		}
	}

	public static class Conjuration {

		public static void summonCreature(World world, Class<? extends EntityTameable> creature,
				double x, double y, double z, int duration, EntityPlayer owner) {
			for (int k = 0; k < 10000; k++)
				world.spawnParticle("portal",
						x + (world.rand.nextDouble() * 3.0) * (world.rand.nextBoolean() ? -1 : 1),
						y + (world.rand.nextDouble() * 3.0) * (world.rand.nextBoolean() ? -1 : 1),
						z + (world.rand.nextDouble() * 3.0) * (world.rand.nextBoolean() ? -1 : 1),
						0, 0, 0);
			try {
				Constructor<? extends EntityTameable> c = creature.getConstructor(World.class);
				EntityTameable en = c.newInstance(world);
				en.setPosition(x, y, z);
				en.setOwner(owner.username);
				en.setTamed(true);
				world.spawnEntityInWorld(en);
				new Thread(new Watcher(en, duration)).start();
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}

		}

		private static class Watcher implements Runnable {

			public EntityLiving e;
			public int duration;

			public Watcher(EntityLiving e, int duration) {
				this.e = e;
				this.duration = duration;
			}

			@Override
			public void run() {
				try {
					World world = e.worldObj;
					double x = e.posX, y = e.posY, z = e.posZ;
					for (int i = 0; i < duration; i++) {
						try {
							if (e.isDead)
								return;
							Thread.sleep(1000);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					e.setDead();
					x = e.posX;
					y = e.posY;
					z = e.posZ;
					for (int k = 0; k < 10000; k++)
						world.spawnParticle("portal", x + (world.rand.nextDouble() * 3.0)
								* (world.rand.nextBoolean() ? -1 : 1), y
								+ (world.rand.nextDouble() * 3.0)
								* (world.rand.nextBoolean() ? -1 : 1), z
								+ (world.rand.nextDouble() * 3.0)
								* (world.rand.nextBoolean() ? -1 : 1), 0, 0, 0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}

	public static class Destruction {
		public static void fireball(World worldObj, double posX, double posY, double posZ) {
			// worldObj.setBlock((int) posX, (int) posY, (int) posZ,
			// mod_Dragon.blockFire.blockID);
			// ((BlockFireball) mod_Dragon.blockFire).spreadSource(worldObj,
			// (int) posX, (int) posY,
			// (int) posZ);
			FireExplosion explosion = new FireExplosion(worldObj, null, posX, posY, posZ, 3F);
			explosion.doExplosionA();
			explosion.doExplosionB(true);
		}

		public static void iceball(World worldObj, double posX, double posY, double posZ) {
			// worldObj.setBlock((int) posX, (int) posY, (int) posZ,
			// mod_Dragon.blockFire.blockID);
			// ((BlockFireball) mod_Dragon.blockFire).spreadSource(worldObj,
			// (int) posX, (int) posY,
			// (int) posZ);
			IceExplosion explosion = new IceExplosion(worldObj, null, posX, posY, posZ, 3F);
			explosion.doExplosionA();
			explosion.doExplosionB(true);
		}

		public static void firebolt(EntityLiving collide) {
			new Thread(new FireDamage(collide, 4)).start();
		}

		public static void icebolt(EntityLiving collide) {
			new Thread(new FrostDamage(collide, 4, 0.4)).start();
		}

		public static void lightningbolt(EntityLiving collide) {
			new Thread(new LightningDamage(collide, 4, 2)).start();
		}

		public static void icespike(World worldObj, double posX, double posY, double posZ) {
			if (worldObj.getBlockId((int) posX, (int) posY, (int) posZ) == 0)
				worldObj.setBlockAndMetadataWithNotify((int) posX, (int) posY, (int) posZ,
						mod_Dragon.blockIce.blockID, 0);
		}

		private static class FireDamage implements Runnable {
			EntityLiving follow;
			int duration;

			public FireDamage(EntityLiving follow, int duration) {
				this.follow = follow;
				this.duration = duration;
			}

			public void run() {
				for (int x = 0; x < duration; x++) {
					try {
						follow.setFire(100);
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		private static class FrostDamage implements Runnable {
			EntityLiving follow;
			int duration;
			double slowDown;

			public FrostDamage(EntityLiving follow, int duration, double slowDown) {
				this.follow = follow;
				this.duration = duration;
				this.slowDown = slowDown;
			}

			public void run() {
				for (int x = 0; x < duration; x++) {
					try {
						follow.motionX *= slowDown;
						follow.motionY *= slowDown;
						follow.motionZ *= slowDown;
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		private static class LightningDamage implements Runnable {
			EntityLiving follow;
			int duration, damage;

			public LightningDamage(EntityLiving follow, int duration, int damage) {
				this.follow = follow;
				this.duration = duration;
				this.damage = damage;
			}

			public void run() {
				for (int x = 0; x < duration; x++) {
					try {
						follow.attackEntityFrom(DamageSource.magic, damage);
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static class Restoration {
		public static void heal(World world, EntityPlayer player, Random rand) {
			player.heal(2);
			world.spawnParticle("portal", player.posX + rand.nextInt(2), player.posY, player.posZ
					+ rand.nextInt(2), 0.0F, 0.0F, 0.0F);
		}
	}

	public static ArrayList<Item> staves = new ArrayList<Item>();

	public static void addStaves() {
		Item mageLight = new ItemStaff(mod_Dragon.LAST_ID++, "Magelight", Block.glowStone.blockID,
				false) {
			public StaffEntity getFireball(World world, final EntityPlayer ep, float f) {
				return new StaffEntity(world, ep, f * 2.0F) {
					public void onLoad() {
						Magic.Alteration.mageList(worldObj, this, 20, ep);
					}
				};
			}
		};
		Item atronach = new ItemStaff(mod_Dragon.LAST_ID++, "Atronach", Item.blazeRod.shiftedIndex,
				true) {
			public StaffEntity getFireball(World world, final EntityPlayer ep, float f) {
				return new StaffEntity(world, ep, f * 2.0F) {
					public void onCollide() {
						Magic.Conjuration.summonCreature(worldObj, Atronach.class, posX, posY,
								posZ, 20, ep);
					}
				};
			}
		};
		Item fireball = new ItemStaff(mod_Dragon.LAST_ID++, "Fireball",
				Item.fireballCharge.shiftedIndex, true) {
			public StaffEntity getFireball(World world, final EntityPlayer ep, float f) {
				return new StaffEntity(world, ep, f * 2.0F) {
					public void onCollide() {
						Magic.Destruction.fireball(worldObj, posX, posY, posZ);
					}
				};
			}
		};
		Item iceball = new ItemStaff(mod_Dragon.LAST_ID++, "Ice Blast", Item.snowball.shiftedIndex,
				true) {
			public StaffEntity getFireball(World world, final EntityPlayer ep, float f) {
				return new StaffEntity(world, ep, f * 2.0F) {
					public void onCollide() {
						Magic.Destruction.iceball(worldObj, posX, posY, posZ);
					}
				};
			}
		};
		Item firebolt = new ItemStaff(mod_Dragon.LAST_ID++, "Firebolt",
				Item.flintAndSteel.shiftedIndex, true) {
			public StaffEntity getFireball(World world, final EntityPlayer ep, float f) {
				return new StaffEntity(world, ep, f * 2.0F) {
					public void onCollide() {
						if (collided != null)
							Magic.Destruction.firebolt(collided);

					}
				};
			}
		};
		Item icebolt = new ItemStaff(mod_Dragon.LAST_ID++, "Ice Bolt", Block.blockSnow.blockID,
				false) {
			public StaffEntity getFireball(World world, final EntityPlayer ep, float f) {
				return new StaffEntity(world, ep, f * 2.0F) {
					public void onCollide() {
						if (collided != null)
							Magic.Destruction.icebolt(collided);
					}
				};
			}
		};
		Item lightningbolt = new ItemStaff(mod_Dragon.LAST_ID++, "Lightning Bolt",
				Item.redstone.shiftedIndex, true) {
			public StaffEntity getFireball(World world, final EntityPlayer ep, float f) {
				return new StaffEntity(world, ep, f * 2.0F) {
					public void onCollide() {
						if (collided != null)
							Magic.Destruction.lightningbolt(collided);
					}
				};
			}
		};
		Item icespike = new ItemStaff(mod_Dragon.LAST_ID++, "Ice Spike", Block.ice.blockID, false) {
			public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer ep) {
				// ep.setItemInUse(itemstack, getMaxItemUseDuration(itemstack));
				float f = 1.0F;
				double d = ep.prevPosX + (ep.posX - ep.prevPosX) * (double) f;
				double d1 = (ep.prevPosY + (ep.posY - ep.prevPosY) * (double) f + 1.62D)
						- (double) ep.yOffset;
				double d2 = ep.prevPosZ + (ep.posZ - ep.prevPosZ) * (double) f;
				boolean flag = false;
				MovingObjectPosition movingobjectposition = getMovingObjectPositionFromPlayer(
						world, ep, flag);

				if (movingobjectposition == null)
					return itemstack;
				if (movingobjectposition.typeOfHit == EnumMovingObjectType.TILE) {
					int i = movingobjectposition.blockX;
					int j = movingobjectposition.blockY;
					int k = movingobjectposition.blockZ;
					if (movingobjectposition.sideHit == 0)
						j--;
					if (movingobjectposition.sideHit == 1)
						j++;
					if (movingobjectposition.sideHit == 2)
						k--;
					if (movingobjectposition.sideHit == 3)
						k++;
					if (movingobjectposition.sideHit == 4)
						i--;
					if (movingobjectposition.sideHit == 5)
						i++;
					if (!ep.canPlayerEdit(i, j, k))
						return itemstack;
					if (world.isAirBlock(i, j, k) || !world.getBlockMaterial(i, j, k).isSolid())
						Magic.Destruction.icespike(world, i, j, k);
				}
				return itemstack;
			}
		};
		Item healing = new ItemStaff(mod_Dragon.LAST_ID++, "Heal", Item.appleRed.shiftedIndex, true) {
			public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer ep) {
				Restoration.heal(world, ep, world.rand);
				itemstack.damageItem(2, ep);
				return itemstack;
			}
		};
		staves.add(mageLight);
		staves.add(atronach);
		staves.add(fireball);
		staves.add(iceball);
		staves.add(firebolt);
		staves.add(icebolt);
		staves.add(lightningbolt);
		staves.add(icespike);
		staves.add(healing);
	}

	public static void loadItems() {
		int staffTexture = ModLoader.addOverride("/gui/items.png", "/dragons/staff-fireball.png");
		for (Item i : staves) {
			ItemStaff staff = (ItemStaff) i;
			String s = staff.spell;
			i.setIconIndex(staffTexture).setItemName("staff" + s);
			ItemStack it = new ItemStack(i, 1);
			ModLoader.addName(it, s + " Staff");
			ModLoader.addRecipe(it, new Object[] { "D", "#", "#", Character.valueOf('D'),
					staff.item ? Item.itemsList[staff.craft] : Block.blocksList[staff.craft],
					Character.valueOf('#'), Item.stick });
		}
	}
}