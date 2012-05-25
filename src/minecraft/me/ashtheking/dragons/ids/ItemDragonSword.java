   package me.ashtheking.dragons.ids;

import me.ashtheking.dragons.mob.Dragon;
import net.minecraft.src.Entity;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.ItemSword;

   public class ItemDragonSword extends ItemSword
   {
      public ItemDragonSword(int i) {
         super(i, EnumToolMaterial.EMERALD);
      }
      public int getDamageVsEntity(Entity entity)
      {
         if(entity instanceof Dragon)
            return 60;
         else
            return 2;
      }
   }