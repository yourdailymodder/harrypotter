package com.minecraftserverzone.harrypotter.broomsticks;

import com.minecraftserverzone.harrypotter.HarryPotterMod;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class BroomStickModel extends EntityModel<BroomStick> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(HarryPotterMod.MODID, "broomstick"), "main");
	private final ModelPart broomstick;

	public BroomStickModel(ModelPart root) {
		this.broomstick = root.getChild("broomstick");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition broomstick = partdefinition.addOrReplaceChild("broomstick", CubeListBuilder.create().texOffs(29, 1).addBox(-1.5F, -1.5F, 22.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 24.0F, new CubeDeformation(0.0F))
		.texOffs(0, 27).addBox(-2.0F, -2.0F, 26.0F, 4.0F, 4.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(0, 7).addBox(-1.5F, -1.5F, 37.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.0F, 0.0F, 23.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 21).addBox(3.0F, 4.0F, 23.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 21).addBox(-5.0F, 4.0F, 23.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 14.0F, -10.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition cube_r1 = broomstick.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 13).addBox(-3.0F, 1.0F, 23.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0873F));

		PartDefinition cube_r2 = broomstick.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 13).addBox(2.0F, 1.0F, 23.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(BroomStick entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		poseStack.pushPose();
		poseStack.translate(0, 0, -0.6f);
		broomstick.render(poseStack, buffer, packedLight, packedOverlay);
		poseStack.popPose();
	}
}