package com.minecraftserverzone.harrypotter.mobs.death_eater;

import com.minecraftserverzone.harrypotter.HarryPotterMod;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;


public class DeathEaterRenderer extends MobRenderer<DeathEater, DeathEaterModel<DeathEater>> {
   private static final ResourceLocation LOCATION_1 = new ResourceLocation(HarryPotterMod.MODID, "textures/entity/death_eater_1.png");
   private static final ResourceLocation LOCATION_2 = new ResourceLocation(HarryPotterMod.MODID, "textures/entity/death_eater_2.png");

   public DeathEaterRenderer(EntityRendererProvider.Context p_174340_) {
      super(p_174340_, new DeathEaterModel<>(p_174340_.bakeLayer(DeathEaterModel.LAYER_LOCATION)), 0.4F);
      this.addLayer(new ItemInHandLayer<>(this));
      this.addLayer(new CustomHeadLayer<>(this, p_174340_.getModelSet(), 1.0019531F, 1.0019531F, 1.0019531F));
   }

   public ResourceLocation getTextureLocation(DeathEater p_115697_) {
	   if(p_115697_.getColor() == 1) {
		   return LOCATION_1;
	   }else {
		   return LOCATION_2;
	   }
   }
}