package com.minecraftserverzone.harrypotter.items.wand;

import com.minecraftserverzone.harrypotter.HarryPotterMod;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class ApprenticeWandModel extends Model {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(HarryPotterMod.MODID, "apprentice_wand"), "main");
	private final ModelPart bone;

	public ApprenticeWandModel(ModelPart root) {
		super(RenderType::entitySolid);
		this.bone = root.getChild("bone");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -22.0F, -4.0F, 8.0F, 22.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(16, 30).addBox(-3.0F, -48.0F, -3.0F, 6.0F, 26.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 30).addBox(-2.0F, -76.0F, -2.0F, 4.0F, 28.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
	
	public ModelPart bone() {
	      return this.bone;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.bone.render(poseStack, buffer, packedLight, packedOverlay);
	}
}