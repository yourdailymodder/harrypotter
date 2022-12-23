package com.minecraftserverzone.harrypotter.spells.evanesco;

import com.minecraftserverzone.harrypotter.HarryPotterMod;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class EvanescoRenderer extends EntityRenderer<Evanesco> {
   private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(HarryPotterMod.MODID, "textures/entity/accio.png");
   private static final RenderType RENDER_TYPE = RenderType.entityCutoutNoCull(TEXTURE_LOCATION);

   public EvanescoRenderer(EntityRendererProvider.Context p_173962_) {
      super(p_173962_);
   }

   @Override
	protected int getBlockLightLevel(Evanesco p_114496_, BlockPos p_114497_) {
		return 15;
	}

   @Override
   public void render(Evanesco p_114080_, float p_114081_, float p_114082_, PoseStack p_114083_, MultiBufferSource p_114084_, int p_114085_) {
      p_114083_.pushPose();
//      float scale1 = (float) Math.abs(Math.sin(p_114080_.tickCount * 0.5f))/10f + 0.5f;
      p_114083_.translate(0, 0.3f, 0);
//      p_114083_.scale(scale1, scale1, scale1);
      p_114083_.mulPose(this.entityRenderDispatcher.cameraOrientation());
      p_114083_.mulPose(Vector3f.YP.rotationDegrees(180.0F));
      PoseStack.Pose posestack$pose = p_114083_.last();
      Matrix4f matrix4f = posestack$pose.pose();
      Matrix3f matrix3f = posestack$pose.normal();
      VertexConsumer vertexconsumer = p_114084_.getBuffer(RENDER_TYPE);
      vertex(vertexconsumer, matrix4f, matrix3f, p_114085_, 0.0F, 0, 0, 1);
      vertex(vertexconsumer, matrix4f, matrix3f, p_114085_, 1F, 0, 1, 1);
      vertex(vertexconsumer, matrix4f, matrix3f, p_114085_, 1F, 1, 1, 0);
      vertex(vertexconsumer, matrix4f, matrix3f, p_114085_, 0.0F, 1, 0, 0);
      p_114083_.popPose();
      super.render(p_114080_, p_114081_, p_114082_, p_114083_, p_114084_, p_114085_);
   }

   private static void vertex(VertexConsumer p_114090_, Matrix4f p_114091_, Matrix3f p_114092_, int p_114093_, float p_114094_, int p_114095_, int p_114096_, int p_114097_) {
      p_114090_.vertex(p_114091_, p_114094_ - 0.5F, (float)p_114095_ - 0.25F, 0.0F).color(255, 255, 255, 255).uv((float)p_114096_, (float)p_114097_).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(p_114093_).normal(p_114092_, 0.0F, 1.0F, 0.0F).endVertex();
   }

   @Override
   public ResourceLocation getTextureLocation(Evanesco p_114078_) {
      return TEXTURE_LOCATION;
   }
}