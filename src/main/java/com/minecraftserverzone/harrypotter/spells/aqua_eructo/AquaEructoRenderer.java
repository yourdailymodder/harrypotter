package com.minecraftserverzone.harrypotter.spells.aqua_eructo;

import java.util.List;

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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class AquaEructoRenderer extends EntityRenderer<AquaEructo> {
   private static final ResourceLocation LOCATION = new ResourceLocation(HarryPotterMod.MODID, "textures/entity/aqua_eructo.png");
   private final AquaEructoModel<AquaEructo> model;
   private static final ResourceLocation GUARDIAN_BEAM_LOCATION = new ResourceLocation(HarryPotterMod.MODID, "textures/entity/water_jet.png");
   private static final RenderType BEAM_RENDER_TYPE = RenderType.entityCutoutNoCull(GUARDIAN_BEAM_LOCATION);

   public AquaEructoRenderer(EntityRendererProvider.Context p_174296_) {
      super(p_174296_);
      this.model = new AquaEructoModel<>(p_174296_.bakeLayer(AquaEructoModel.LAYER_LOCATION));
   }

   public void render(AquaEructo p_115373_, float p_115374_, float p_115375_, PoseStack p_115376_, MultiBufferSource p_115377_, int p_115378_) {
      p_115376_.pushPose();
      p_115376_.translate(0.0D, (double)-1.15F, 0.0D);

      List<Entity> livingEntitiesNear = p_115373_.level.getEntities(p_115373_, new AABB(p_115373_.getX() - 2.0D, p_115373_.getY() - 2.0D, p_115373_.getZ() - 2.0D, p_115373_.getX() + 3.0D, p_115373_.getY() + 2.0D, p_115373_.getZ() + 2.0D), Entity::isAlive);
		for(Entity entity : livingEntitiesNear) {
			if(entity instanceof AquaEructo) {
				AquaEructo livingentity = (AquaEructo) entity;
		         if (livingentity != null ) {
			         float f = 0.5f;
			         float f1 = (float)p_115373_.level.getGameTime() + p_115375_;
			         float f2 = f1 * 0.5F % 1.0F;
			         float f3 = p_115373_.getEyeHeight();
			         p_115376_.pushPose();
			         p_115376_.translate(0.0D, (double)1.55d, 0.0D);
			         Vec3 vec3 = this.getPosition(livingentity, (double)livingentity.getBbHeight() * 0.5D, p_115375_);
			         Vec3 vec31 = this.getPosition(p_115373_, (double)f3, p_115375_);
			         Vec3 vec32 = vec3.subtract(vec31);
			         float f4 = (float)(vec32.length() + 0.0D);
			         vec32 = vec32.normalize();
			         float f5 = (float)Math.acos(vec32.y);
			         float f6 = (float)Math.atan2(vec32.z, vec32.x);
			         p_115376_.mulPose(Vector3f.YP.rotationDegrees((((float)Math.PI / 2F) - f6) * (180F / (float)Math.PI)));
			         p_115376_.mulPose(Vector3f.XP.rotationDegrees(f5 * (180F / (float)Math.PI)));
			         int i = 1;
			         float f7 = f1 * 0.05F * -1.5F;
			         float f8 = f * f;
			         int j = 64 + (int)(f8 * 191.0F);
			         int k = 32 + (int)(f8 * 191.0F);
			         int l = 128 - (int)(f8 * 64.0F);
			         float f9 = 0.2F;
			         float f10 = 0.282F;
			         float f11 = Mth.cos(f7 + 2.3561945F) * 0.282F;
			         float f12 = Mth.sin(f7 + 2.3561945F) * 0.282F;
			         float f13 = Mth.cos(f7 + ((float)Math.PI / 4F)) * 0.282F;
			         float f14 = Mth.sin(f7 + ((float)Math.PI / 4F)) * 0.282F;
			         float f15 = Mth.cos(f7 + 3.926991F) * 0.282F;
			         float f16 = Mth.sin(f7 + 3.926991F) * 0.282F;
			         float f17 = Mth.cos(f7 + 5.4977875F) * 0.282F;
			         float f18 = Mth.sin(f7 + 5.4977875F) * 0.282F;
			         float f19 = Mth.cos(f7 + (float)Math.PI) * 0.2F;
			         float f20 = Mth.sin(f7 + (float)Math.PI) * 0.2F;
			         float f21 = Mth.cos(f7 + 0.0F) * 0.2F;
			         float f22 = Mth.sin(f7 + 0.0F) * 0.2F;
			         float f23 = Mth.cos(f7 + ((float)Math.PI / 2F)) * 0.2F;
			         float f24 = Mth.sin(f7 + ((float)Math.PI / 2F)) * 0.2F;
			         float f25 = Mth.cos(f7 + ((float)Math.PI * 1.5F)) * 0.2F;
			         float f26 = Mth.sin(f7 + ((float)Math.PI * 1.5F)) * 0.2F;
			         float f27 = 0.0F;
			         float f28 = 0.4999F;
			         float f29 = -1.0F + f2;
			         float f30 = f4 * 2.5F + f29;
			         VertexConsumer vertexconsumer = p_115377_.getBuffer(BEAM_RENDER_TYPE);
			         PoseStack.Pose posestack$pose = p_115376_.last();
			         Matrix4f matrix4f = posestack$pose.pose();
			         Matrix3f matrix3f = posestack$pose.normal();
			         vertex(vertexconsumer, matrix4f, matrix3f, f19, f4, f20, j, k, l, 0.4999F, f30);
			         vertex(vertexconsumer, matrix4f, matrix3f, f19, 0.0F, f20, j, k, l, 0.4999F, f29);
			         vertex(vertexconsumer, matrix4f, matrix3f, f21, 0.0F, f22, j, k, l, 0.0F, f29);
			         vertex(vertexconsumer, matrix4f, matrix3f, f21, f4, f22, j, k, l, 0.0F, f30);
			         vertex(vertexconsumer, matrix4f, matrix3f, f23, f4, f24, j, k, l, 0.4999F, f30);
			         vertex(vertexconsumer, matrix4f, matrix3f, f23, 0.0F, f24, j, k, l, 0.4999F, f29);
			         vertex(vertexconsumer, matrix4f, matrix3f, f25, 0.0F, f26, j, k, l, 0.0F, f29);
			         vertex(vertexconsumer, matrix4f, matrix3f, f25, f4, f26, j, k, l, 0.0F, f30);
			         float f31 = 0.0F;
			         if (p_115373_.tickCount % 2 == 0) {
			            f31 = 0.5F;
			         }
			
			         vertex(vertexconsumer, matrix4f, matrix3f, f11, f4, f12, j, k, l, 0.5F, f31 + 0.5F);
			         vertex(vertexconsumer, matrix4f, matrix3f, f13, f4, f14, j, k, l, 1.0F, f31 + 0.5F);
			         vertex(vertexconsumer, matrix4f, matrix3f, f17, f4, f18, j, k, l, 1.0F, f31);
			         vertex(vertexconsumer, matrix4f, matrix3f, f15, f4, f16, j, k, l, 0.5F, f31);
			         p_115376_.popPose();
		      }
			}
		}
//		p_115376_.scale(0.005f, 0.005f, 0.005f);
      this.model.setupAnim(p_115373_, p_115375_, 0.0F, -0.1F, 0.0F, 0.0F);
      VertexConsumer vertexconsumer = p_115377_.getBuffer(this.model.renderType(LOCATION));
      this.model.renderToBuffer(p_115376_, vertexconsumer, p_115378_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
      
      
      p_115376_.popPose();
      super.render(p_115373_, p_115374_, p_115375_, p_115376_, p_115377_, p_115378_);
   }
   
   private Vec3 getPosition(Entity livingentity, double p_114804_, float p_114805_) {
	      double d0 = Mth.lerp((double)p_114805_, livingentity.xOld, livingentity.getX());
	      double d1 = Mth.lerp((double)p_114805_, livingentity.yOld, livingentity.getY()) + p_114804_;
	      double d2 = Mth.lerp((double)p_114805_, livingentity.zOld, livingentity.getZ());
	      return new Vec3(d0, d1, d2);
	   }
   
   private static void vertex(VertexConsumer p_114842_, Matrix4f p_114843_, Matrix3f p_114844_, float p_114845_, float p_114846_, float p_114847_, int p_114848_, int p_114849_, int p_114850_, float p_114851_, float p_114852_) {
	      p_114842_.vertex(p_114843_, p_114845_, p_114846_, p_114847_).color(p_114848_, p_114849_, p_114850_, 255).uv(p_114851_, p_114852_).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(p_114844_, 0.0F, 1.0F, 0.0F).endVertex();
	   }

   public ResourceLocation getTextureLocation(AquaEructo p_115371_) {
      return LOCATION;
   }
}