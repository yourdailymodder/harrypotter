package com.minecraftserverzone.harrypotter.mobs.patronus_deer;

import com.minecraftserverzone.harrypotter.HarryPotterMod;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PatronusDeerRenderer extends MobRenderer<PatronusDeer, PatronusDeerModel<PatronusDeer>> {
   public static final ResourceLocation LOCATION = new ResourceLocation(HarryPotterMod.MODID, "textures/entity/patronus_deer.png");

   public PatronusDeerRenderer(EntityRendererProvider.Context p_174340_) {
      super(p_174340_, new PatronusDeerModel<>(p_174340_.bakeLayer(PatronusDeerModel.LAYER_LOCATION)), 0.0F);
   }

   public ResourceLocation getTextureLocation(PatronusDeer p_115697_) {
      return LOCATION;
   }
}