package com.minecraftserverzone.harrypotter.mobs.inferius;

import com.minecraftserverzone.harrypotter.HarryPotterMod;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;


public class InferiusRenderer extends MobRenderer<Inferius, InferiusModel<Inferius>> {
   private static final ResourceLocation LOCATION_1 = new ResourceLocation(HarryPotterMod.MODID, "textures/entity/inferius.png");

   public InferiusRenderer(EntityRendererProvider.Context p_174340_) {
      super(p_174340_, new InferiusModel<>(p_174340_.bakeLayer(InferiusModel.LAYER_LOCATION)), 0.4F);
   }

   public ResourceLocation getTextureLocation(Inferius p_115697_) {
	   return LOCATION_1;
   }
}