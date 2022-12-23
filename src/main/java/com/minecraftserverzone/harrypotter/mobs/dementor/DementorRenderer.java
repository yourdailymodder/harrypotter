package com.minecraftserverzone.harrypotter.mobs.dementor;

import com.minecraftserverzone.harrypotter.HarryPotterMod;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.resources.ResourceLocation;


public class DementorRenderer extends MobRenderer<Dementor, DementorModel<Dementor>> {
   private static final ResourceLocation LOCATION = new ResourceLocation(HarryPotterMod.MODID, "textures/entity/dementor.png");

   public DementorRenderer(EntityRendererProvider.Context p_174340_) {
      super(p_174340_, new DementorModel<>(p_174340_.bakeLayer(DementorModel.LAYER_LOCATION)), 0.4F);
      this.addLayer(new CustomHeadLayer<>(this, p_174340_.getModelSet(), 1.1F, 1.1F, 1.1F, p_174340_.getItemInHandRenderer()));
   }

   public ResourceLocation getTextureLocation(Dementor p_115697_) {
      return LOCATION;
   }
}