package com.minecraftserverzone.harrypotter.spells.glacius;

import com.minecraftserverzone.harrypotter.HarryPotterMod;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class GlaciusRenderer extends EntityRenderer<Glacius> {
   private static final ResourceLocation LOCATION = new ResourceLocation(HarryPotterMod.MODID, "textures/entity/depulso.png");
   private static final ResourceLocation LOCATION2 = new ResourceLocation(HarryPotterMod.MODID, "textures/entity/ice_entity.png");
   private final GlaciusModel<Glacius> model;
   private final IceEntityModel<Glacius> model2;
   
   public GlaciusRenderer(EntityRendererProvider.Context p_174296_) {
      super(p_174296_);
      this.model = new GlaciusModel<>(p_174296_.bakeLayer(GlaciusModel.LAYER_LOCATION));
      this.model2 = new IceEntityModel<>(p_174296_.bakeLayer(IceEntityModel.LAYER_LOCATION));
   }
   
  @Override
	protected int getBlockLightLevel(Glacius p_114496_, BlockPos p_114497_) {
		return 15;
	}

   public void render(Glacius p_115373_, float p_115374_, float p_115375_, PoseStack p_115376_, MultiBufferSource p_115377_, int p_115378_) {
      p_115376_.pushPose();
      p_115376_.translate(0.0D, (double)-1.3F, 0.0D);

      if(p_115373_.isPassenger()) {
    	  double offsetY = p_115373_.getVehicle().getPassengersRidingOffset();
    	  p_115376_.translate(0.0D, (double)-offsetY - 0.2f, 0.0D);
    	  this.model2.setupAnim(p_115373_, p_115375_, 0.0F, -0.1F, 0.0F, 0.0F);
          VertexConsumer vertexconsumer = p_115377_.getBuffer(RenderType.entityTranslucent(LOCATION2));
          this.model2.renderToBuffer(p_115376_, vertexconsumer, p_115378_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
          
      }else {
    	  this.model.setupAnim(p_115373_, p_115375_, 0.0F, -0.1F, 0.0F, 0.0F);
          VertexConsumer vertexconsumer = p_115377_.getBuffer(this.model.renderType(LOCATION));
          this.model.renderToBuffer(p_115376_, vertexconsumer, p_115378_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
      }
    	 
      p_115376_.popPose();
      super.render(p_115373_, p_115374_, p_115375_, p_115376_, p_115377_, p_115378_);
   }

   public ResourceLocation getTextureLocation(Glacius p_115371_) {
	   if(p_115371_.isPassenger()) {
		   return LOCATION2;
	   }else {
		   return LOCATION;
	   }
   }
}