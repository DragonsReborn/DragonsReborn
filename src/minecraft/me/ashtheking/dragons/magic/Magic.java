package me.ashtheking.dragons.magic;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import me.ashtheking.dragons.meta.FireExplosion;
import me.ashtheking.dragons.meta.IceExplosion;
import me.ashtheking.dragons.mob.Necromancer;
import net.minecraft.src.Block;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityMob;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntitySkeleton;
import net.minecraft.src.EntityTameable;
import net.minecraft.src.EntityZombie;
import net.minecraft.src.EnumMovingObjectType;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.World;
import net.minecraft.src.mod_Dragon;

public class Magic {

	public static class Alteration {

		public static HashMap<EntityLiving, Integer> playerSkill, playerLevel, xpToNext;

		public static void mageList(World world, Entity follow, int duration, EntityLiving owner) {
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

		public static HashMap<EntityLiving, Integer> playerSkill, playerLevel, xpToNext;

		public static void summonCreature(World world, Class<? extends EntityTameable> creature,
				double x, double y, double z, int duration, EntityLiving owner) {
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
				if (owner instanceof EntityPlayer)
					en.setOwner(((EntityPlayer) owner).username);
				en.setTamed(true);
				world.spawnEntityInWorld(en);
				new Thread(new Watcher(en, duration)).start();
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}

		}

		public static void summonNecro(World world, double posX, double posY, double posZ,
				EntityLiving owner, Entity target) {
			int x = world.rand.nextInt(2);
			EntityMob en = x == 0 ? new EntitySkeleton(world) : new EntityZombie(world);
			en.setLocationAndAngles(posX, posY, posZ, 0.0F, 0.0F);
			world.spawnEntityInWorld(en);
			en.setAttackTarget((EntityLiving) target);
			Necromancer necro = new Necromancer(world);
			necro.setLocationAndAngles(posX, posY, posZ, 0, 0);
			world.spawnEntityInWorld(necro);
			new Thread(new BlockTarget(owner, en)).start();
			new Thread(new Watcher(en, (int) Math.min(playerLevel.get(owner), 60))).start();
		}

		private static class BlockTarget implements Runnable {

			EntityMob watch;
			EntityLiving owner;

			public BlockTarget(EntityLiving owner, EntityMob watch) {
				this.owner = owner;
				this.watch = watch;
			}

			@Override
			public void run() {
				try {
					while (this.watch.isEntityAlive() && this.owner.isEntityAlive()) {
						Thread.sleep(1000);
						if (this.watch.getAttackTarget() == owner)
							this.watch.setAttackTarget(null);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
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

		public static HashMap<EntityLiving, Integer> playerSkill, playerLevel, xpToNext;

		public static void fireball(World worldObj, double posX, double posY, double posZ) {
			// worldObj.setBlock((int) posX, (int) posY, (int) posZ,
			// mod_Dragon.blockFire.blockID);
			// ((BlockFireball) mod_Dragon.blockFire).spreadSource(worldObj,
			// (int) posX, (int) posY,
			// (int) posZ);
			FireExplosion explosion = new FireExplosion(worldObj, null, posX, posY, posZ, 1F);
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

		public static void firebolt(EntityLiving shooter, EntityLiving collide) {
			new Thread(new FireDamage(collide, (int) Math.min(playerLevel.get(shooter), 10)))
					.start();
		}

		public static void icebolt(EntityLiving shooter, EntityLiving collide) {
			new Thread(new FrostDamage(collide, (int) Math.min(playerLevel.get(shooter), 10), 0.4))
					.start();
		}

		public static void lightningbolt(EntityLiving shooter, EntityLiving collide) {
			new Thread(
					new LightningDamage(collide, (int) Math.min(playerLevel.get(shooter), 10), 2))
					.start();
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
		public static HashMap<EntityLiving, Integer> playerSkill, playerLevel, xpToNext;
		public static void heal(World world, EntityLiving player, Random rand) {
			player.heal(2);
			world.spawnParticle("portal", player.posX + rand.nextInt(2), player.posY, player.posZ
					+ rand.nextInt(2), 0.0F, 0.0F, 0.0F);
		}
	}

	public static ArrayList<Item> staves = new ArrayList<Item>();

	public static void addStaves() {
		Item mageLight = new ItemStaff(mod_Dragon.LAST_ID++, "Magelight", Block.glowStone.blockID,
				false) {
			public StaffEntity getFireball(World world, final EntityLiving ep, float f) {
				StaffEntity e = new StaffEntity(world, ep, f * 2.0F) {
					public void onLoad() {
						int duration = (int) Math.min(Alteration.playerLevel.get(ep), 20);
						Magic.Alteration.mageList(worldObj, this, duration, ep);
					}
				};
				e.particletype = "spell";
				return e;
			}

			public void updateMagic(EntityLiving el) {
				if (Alteration.playerSkill.containsKey(el))
					Alteration.playerSkill.put(el, Alteration.playerSkill.get(el) + 1);
				else
					Alteration.playerSkill.put(el, 1);
			}
		};
		Item atronach = new ItemStaff(mod_Dragon.LAST_ID++, "Atronach", Item.blazeRod.shiftedIndex,
				true) {
			public StaffEntity getFireball(World world, final EntityLiving ep, float f) {
				StaffEntity e = new StaffEntity(world, ep, f * 2.0F) {
					public void onCollide() {
						Magic.Conjuration
								.summonCreature(worldObj, Atronach.class, posX, posY, posZ,
										(int) Math.max(Magic.Conjuration.playerLevel.get(ep), 60),
										ep);
					}
				};
				e.particletype = "flame";
				return e;
			}

			public void updateMagic(EntityLiving el) {
				if (Conjuration.playerSkill.containsKey(el))
					Conjuration.playerSkill.put(el, Conjuration.playerSkill.get(el) + 1);
				else
					Conjuration.playerSkill.put(el, 1);
			}
		};
		Item fireball = new ItemStaff(mod_Dragon.LAST_ID++, "Fireball",
				Item.fireballCharge.shiftedIndex, true) {
			public StaffEntity getFireball(World world, final EntityLiving ep, float f) {
				StaffEntity e = new StaffEntity(world, ep, f * 2.0F) {
					public void onCollide() {
						Magic.Destruction.fireball(worldObj, posX, posY, posZ);
					}
				};
				e.particletype = "flame";
				return e;
			}
		};
		Item iceball = new ItemStaff(mod_Dragon.LAST_ID++, "Ice Blast", Item.snowball.shiftedIndex,
				true) {
			public StaffEntity getFireball(World world, final EntityLiving ep, float f) {
				StaffEntity e = new StaffEntity(world, ep, f * 2.0F) {
					public void onCollide() {
						Magic.Destruction.iceball(worldObj, posX, posY, posZ);
					}
				};
				e.particletype = "splash";
				return e;
			}
		};
		Item firebolt = new ItemStaff(mod_Dragon.LAST_ID++, "Firebolt",
				Item.flintAndSteel.shiftedIndex, true) {
			public StaffEntity getFireball(World world, final EntityLiving ep, float f) {
				StaffEntity e = new StaffEntity(world, ep, f * 2.0F) {
					public void onCollide() {
						if (collided != null)
							Magic.Destruction.firebolt(ep, collided);

					}
				};
				e.particletype = "flame";
				return e;
			}

			public void updateMagic(EntityLiving el) {
				if (Destruction.playerSkill.containsKey(el))
					Destruction.playerSkill.put(el, Destruction.playerSkill.get(el) + 1);
				else
					Destruction.playerSkill.put(el, 1);
			}
		};
		Item icebolt = new ItemStaff(mod_Dragon.LAST_ID++, "Ice Bolt", Block.blockSnow.blockID,
				false) {
			public StaffEntity getFireball(World world, final EntityLiving ep, float f) {
				StaffEntity e = new StaffEntity(world, ep, f * 2.0F) {
					public void onCollide() {
						if (collided != null)
							Magic.Destruction.icebolt(ep, collided);
					}
				};
				e.particletype = "splash";
				return e;
			}

			public void updateMagic(EntityLiving el) {
				if (Destruction.playerSkill.containsKey(el))
					Destruction.playerSkill.put(el, Destruction.playerSkill.get(el) + 1);
				else
					Destruction.playerSkill.put(el, 1);
			}
		};
		Item lightningbolt = new ItemStaff(mod_Dragon.LAST_ID++, "Lightning Bolt",
				Item.redstone.shiftedIndex, true) {
			public StaffEntity getFireball(World world, final EntityLiving ep, float f) {
				StaffEntity e = new StaffEntity(world, ep, f * 2.0F) {
					public void onCollide() {
						if (collided != null)
							Magic.Destruction.lightningbolt(ep, collided);
					}
				};
				e.particletype = "reddust";
				return e;
			}

			public void updateMagic(EntityLiving el) {
				if (Destruction.playerSkill.containsKey(el))
					Destruction.playerSkill.put(el, Destruction.playerSkill.get(el) + 1);
				else
					Destruction.playerSkill.put(el, 1);
			}
		};
		Item icespike = new ItemStaff(mod_Dragon.LAST_ID++, "Ice Spike", Block.ice.blockID, false) {
			public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityLiving e) {
				// ep.setItemInUse(itemstack, getMaxItemUseDuration(itemstack));
				if (!(e instanceof EntityPlayer))
					return itemstack;
				EntityPlayer ep = (EntityPlayer) e;
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
			public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityLiving ep) {
				Restoration.heal(world, ep, world.rand);
				itemstack.damageItem(2, ep);
				for(int x = 0; x < 10; x++)
				world.spawnParticle("heart", ep.posX, ep.posY, ep.posZ, 0, 0, 0);
				return itemstack;
			}

			public void updateMagic(EntityLiving el) {
				if (Restoration.playerSkill.containsKey(el))
					Restoration.playerSkill.put(el, Restoration.playerSkill.get(el) + 1);
				else
					Restoration.playerSkill.put(el, 1);
			}
		};
		Item necro = new ItemStaff(mod_Dragon.LAST_ID++, "Raise Undead",
				Item.rottenFlesh.shiftedIndex, true) {
			public StaffEntity getFireball(World world, final EntityLiving ep, float f) {
				StaffEntity e = new StaffEntity(world, ep, f * 2.0F) {
					public void onCollide() {
						Magic.Conjuration.summonNecro(worldObj, posX, posY, posZ, ep, null);
					}
				};
				e.particletype = "townaura";
				return e;
			}

			public void updateMagic(EntityLiving el) {
				if (Conjuration.playerSkill.containsKey(el))
					Conjuration.playerSkill.put(el, Conjuration.playerSkill.get(el) + 1);
				else
					Conjuration.playerSkill.put(el, 1);
			}
		};
		staves.add(mageLight); // 0
		staves.add(atronach); // 1
		staves.add(fireball); // 2
		staves.add(iceball); // 3
		staves.add(firebolt); // 4
		staves.add(icebolt); // 5
		staves.add(lightningbolt); // 6
		staves.add(icespike); // 7
		staves.add(healing); // 8
		staves.add(necro); // 9
	}

	public static void updateStats(EntityLiving el) {
		if (el == null)
			return;
		if (Destruction.playerLevel == null || Destruction.playerSkill == null
				|| Destruction.xpToNext == null) {
			Destruction.playerLevel = new HashMap<EntityLiving, Integer>();
			Destruction.playerSkill = new HashMap<EntityLiving, Integer>();
			Destruction.xpToNext = new HashMap<EntityLiving, Integer>();
		}
		if (Conjuration.playerLevel == null || Conjuration.playerSkill == null
				|| Conjuration.xpToNext == null) {
			Conjuration.playerLevel = new HashMap<EntityLiving, Integer>();
			Conjuration.playerSkill = new HashMap<EntityLiving, Integer>();
			Conjuration.xpToNext = new HashMap<EntityLiving, Integer>();
		}
		if (Restoration.playerLevel == null || Restoration.playerSkill == null
				|| Restoration.xpToNext == null) {
			Restoration.playerLevel = new HashMap<EntityLiving, Integer>();
			Restoration.playerSkill = new HashMap<EntityLiving, Integer>();
			Restoration.xpToNext = new HashMap<EntityLiving, Integer>();
		}
		if (Alteration.playerLevel == null || Alteration.playerSkill == null
				|| Alteration.xpToNext == null) {
			Alteration.playerLevel = new HashMap<EntityLiving, Integer>();
			Alteration.playerSkill = new HashMap<EntityLiving, Integer>();
			Alteration.xpToNext = new HashMap<EntityLiving, Integer>();
		}

		if (Destruction.playerSkill.containsKey(el))
			Destruction.playerSkill.put(el, Destruction.playerSkill.get(el) + 1);
		else {
			Destruction.playerSkill.put(el, 1);
			Destruction.playerLevel.put(el, 1);
			Destruction.xpToNext.put(el,  5);
		}
		if (Alteration.playerSkill.containsKey(el))
			Alteration.playerSkill.put(el, Alteration.playerSkill.get(el) + 1);
		else {
			Alteration.playerSkill.put(el, 1);
			Alteration.playerLevel.put(el, 1);
			Alteration.xpToNext.put(el,  5);
		}
		if (Restoration.playerSkill.containsKey(el))
			Restoration.playerSkill.put(el, Restoration.playerSkill.get(el) + 1);
		else {
			Restoration.playerSkill.put(el, 1);
			Restoration.playerLevel.put(el, 1);
			Restoration.xpToNext.put(el,  5);
		}
		if (Conjuration.playerSkill.containsKey(el))
			Conjuration.playerSkill.put(el, Conjuration.playerSkill.get(el) + 1);
		else {
			Conjuration.playerSkill.put(el, 1);
			Conjuration.playerLevel.put(el, 1);
			Conjuration.xpToNext.put(el,  5);
		}

		if (Magic.Destruction.playerSkill.get(el) > Magic.Destruction.xpToNext.get(el)) {
			Destruction.playerLevel.put(el, Destruction.playerLevel.get(el) + 1);
			Magic.Destruction.xpToNext.put(el, Magic.Destruction.playerLevel.get(el) * 5);
		}
		if (Magic.Conjuration.playerSkill.get(el) > Magic.Conjuration.xpToNext.get(el)) {
			Conjuration.playerLevel.put(el, Conjuration.playerLevel.get(el) + 1);
			Magic.Conjuration.xpToNext.put(el, Magic.Conjuration.playerLevel.get(el) * 5);
		}
		if (Magic.Restoration.playerSkill.get(el) > Magic.Restoration.xpToNext.get(el)) {
			Restoration.playerLevel.put(el, Restoration.playerLevel.get(el) + 1);
			Magic.Restoration.xpToNext.put(el, Magic.Restoration.playerLevel.get(el) * 5);
		}
		if (Magic.Alteration.playerSkill.get(el) > Magic.Alteration.xpToNext.get(el)) {
			Alteration.playerLevel.put(el, Alteration.playerLevel.get(el) + 1);
			Magic.Alteration.xpToNext.put(el, Magic.Alteration.playerLevel.get(el) * 5);
		}
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