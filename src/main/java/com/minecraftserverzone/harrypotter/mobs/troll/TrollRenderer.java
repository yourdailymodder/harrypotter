package com.minecraftserverzone.harrypotter.mobs.troll;

import com.minecraftserverzone.harrypotter.HarryPotterMod;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.resources.ResourceLocation;


public class TrollRenderer extends MobRenderer<Troll, TrollModel<Troll>> {
   private static final ResourceLocation LOCATION_1 = new ResourceLocation(HarryPotterMod.MODID, "textures/entity/troll.png");

   public TrollRenderer(EntityRendererProvider.Context p_174340_) {
      super(p_174340_, new TrollModel<>(p_174340_.bakeLayer(TrollModel.LAYER_LOCATION)), 0.4F);
      this.addLayer(new CustomHeadLayer<>(this, p_174340_.getModelSet(), 1.4F, 1.05F, 1.2F));
   }

   public ResourceLocation getTextureLocation(Troll p_115697_) {
	   return LOCATION_1;
   }
}