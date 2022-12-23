package com.minecraftserverzone.harrypotter.items.wand;

import com.minecraftserverzone.harrypotter.HarryPotterMod;
import com.minecraftserverzone.harrypotter.setup.Registrations;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class ApprenticeWandRenderer extends BlockEntityWithoutLevelRenderer{

	private ApprenticeWandModel apprenticewand;
	private final EntityModelSet entityModelSet;
	public static final ResourceLocation TEXTURE = new ResourceLocation(HarryPotterMod.MODID, "entity/apprentice_wand");
	@SuppressWarnings("deprecation")
	public static final Material BASE = new Material(TextureAtlas.LOCATION_BLOCKS, TEXTURE);

	public ApprenticeWandRenderer(BlockEntityRenderDispatcher p_172550_, EntityModelSet p_172551_) {
		super(p_172550_, p_172551_);
	    this.entityModelSet = p_172551_;
	    this.apprenticewand = new ApprenticeWandModel(this.entityModelSet.bakeLayer(ApprenticeWandModel.LAYER_LOCATION));
	}

	@Override
	public void renderByItem(ItemStack p_108830_, TransformType p_108831_, PoseStack p_108832_,
			MultiBufferSource p_108833_, int p_108834_, int p_108835_) {
	    	  if (p_108830_.is(Registrations.APPRENTICE_WAND.get())) {
	              p_108832_.pushPose();
	              p_108832_.scale(1.0F, -1.0F, -1.0F);
	              Material material = BASE;
	              VertexConsumer vertexconsumer = material.sprite().wrap(ItemRenderer.getFoilBufferDirect(p_108833_, this.apprenticewand.renderType(material.atlasLocation()), true, p_108830_.hasFoil()));
	              this.apprenticewand.bone().render(p_108832_, vertexconsumer, p_108834_, p_108835_, 1.0F, 1.0F, 1.0F, 1.0F);
	              }
	              p_108832_.popPose();
	}
}
