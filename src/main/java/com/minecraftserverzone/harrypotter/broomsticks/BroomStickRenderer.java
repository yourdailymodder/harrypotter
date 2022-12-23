package com.minecraftserverzone.harrypotter.broomsticks;

import com.minecraftserverzone.harrypotter.HarryPotterMod;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.ArmorStand;

public class BroomStickRenderer extends LivingEntityRenderer<BroomStick, BroomStickModel> {
   public static final ResourceLocation DEFAULT_SKIN_LOCATION = new ResourceLocation(HarryPotterMod.MODID, "textures/entity/broomstick.png");

   public BroomStickRenderer(EntityRendererProvider.Context p_173915_) {
      super(p_173915_, new BroomStickModel(p_173915_.bakeLayer(BroomStickModel.LAYER_LOCATION)), 0.0F);
   }

   public ResourceLocation getTextureLocation(BroomStick p_113798_) {
      return DEFAULT_SKIN_LOCATION;
   }

   protected void setupRotations(ArmorStand p_113800_, PoseStack p_113801_, float p_113802_, float p_113803_, float p_113804_) {
//      p_113801_.mulPose(Vector3f.YP.rotationDegrees(180.0F - p_113803_));
//      float f = (float)(p_113800_.level.getGameTime() - p_113800_.lastHit) + p_113804_;
//      if (f < 5.0F) {
//         p_113801_.mulPose(Vector3f.YP.rotationDegrees(Mth.sin(f / 1.5F * (float)Math.PI) * 3.0F));
//      }

   }

//   protected boolean shouldShowName(BroomStick p_113815_) {
//      double d0 = this.entityRenderDispatcher.distanceToSqr(p_113815_);
//      float f = p_113815_.isCrouching() ? 32.0F : 64.0F;
//      return d0 >= (double)(f * f) ? false : p_113815_.isCustomNameVisible();
//   }
}