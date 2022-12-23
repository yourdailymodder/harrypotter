package com.minecraftserverzone.harrypotter.mobs.acromantula;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class AcromantulaEyesLayer<T extends Entity, M extends AcromantulaModel<T>> extends EyesLayer<T, M> {
   private static final RenderType SPIDER_EYES = RenderType.eyes(new ResourceLocation("textures/entity/spider_eyes.png"));

   public AcromantulaEyesLayer(RenderLayerParent<T, M> p_117507_) {
      super(p_117507_);
   }

   public RenderType renderType() {
      return SPIDER_EYES;
   }
}