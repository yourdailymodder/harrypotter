package com.minecraftserverzone.harrypotter.mobs.death_eater;

import com.minecraftserverzone.harrypotter.HarryPotterMod;
import com.minecraftserverzone.harrypotter.setup.config.HarryPotterModConfig;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;


public class DeathEaterRenderer extends MobRenderer<DeathEater, DeathEaterModel<DeathEater>> {
   private static final ResourceLocation LOCATION_1 = new ResourceLocation(HarryPotterMod.MODID, "textures/entity/death_eater_1.png");
   private static final ResourceLocation LOCATION_2 = new ResourceLocation(HarryPotterMod.MODID, "textures/entity/death_eater_2.png");
   private static final ResourceLocation LOCATION_A_1 = new ResourceLocation(HarryPotterMod.MODID, "textures/entity/death_eater_a_1.png");

   public DeathEaterRenderer(EntityRendererProvider.Context p_174340_) {
      super(p_174340_, new DeathEaterModel<>(p_174340_.bakeLayer(DeathEaterModel.LAYER_LOCATION)), 0.4F);
      this.addLayer(new ItemInHandLayer<>(this, p_174340_.getItemInHandRenderer()));
      this.addLayer(new CustomHeadLayer<>(this, p_174340_.getModelSet(), 1.0019531F, 1.0019531F, 1.0019531F,  p_174340_.getItemInHandRenderer()));
   }

   public ResourceLocation getTextureLocation(DeathEater p_115697_) {
	   if(HarryPotterModConfig.DEATH_EATER_MODEL.get()==0) {
		   return LOCATION_A_1;
	   }
	   if(p_115697_.getColor() == 1) {
		   return LOCATION_1;
	   }else {
		   return LOCATION_2;
	   }
   }
}