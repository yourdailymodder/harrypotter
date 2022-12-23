package com.minecraftserverzone.harrypotter.mobs.acromantula;

import com.minecraftserverzone.harrypotter.HarryPotterMod;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class AcromantulaRenderer<T extends Acromantula> extends MobRenderer<T, AcromantulaModel<T>> {
   private static final ResourceLocation LOCATION = new ResourceLocation(HarryPotterMod.MODID, "textures/entity/acromantula.png");

   public AcromantulaRenderer(EntityRendererProvider.Context p_174403_) {
      super(p_174403_, new AcromantulaModel<>(p_174403_.bakeLayer(AcromantulaModel.LAYER_LOCATION)), 0.4F);
//      this.addLayer(new AcromantulaEyesLayer<>(this));
   }

   protected float getFlipDegrees(T p_116011_) {
      return 180.0F;
   }
   
   public void render(T p_115976_, float p_115977_, float p_115978_, PoseStack p_115979_, MultiBufferSource p_115980_, int p_115981_) {
	      this.shadowRadius = 0.05F * (float)p_115976_.getSize() + 0.25f;
	      super.render(p_115976_, p_115977_, p_115978_, p_115979_, p_115980_, p_115981_);
	   }

   
   protected void scale(T p_115395_, PoseStack p_115396_, float p_115397_) {
	      float i = p_115395_.getSize();
	      p_115396_.scale(i*0.05f + 0.25f, i*0.05f + 0.25f, i*0.05f + 0.25f);
	   }

   public ResourceLocation getTextureLocation(T p_116009_) {
      return LOCATION;
   }
}