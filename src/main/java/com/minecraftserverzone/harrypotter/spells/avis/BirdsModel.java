package com.minecraftserverzone.harrypotter.spells.avis;

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
import net.minecraft.world.entity.Entity;

public class BirdsModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(HarryPotterMod.MODID, "birds"), "main");
	private final ModelPart main;
	private final ModelPart main2;
	private final ModelPart main3;
	private final ModelPart main4;
	private final ModelPart main5;

	public BirdsModel(ModelPart root) {
		this.main = root.getChild("main");
		this.main2 = root.getChild("main2");
		this.main3 = root.getChild("main3");
		this.main4 = root.getChild("main4");
		this.main5 = root.getChild("main5");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(6, 6).addBox(-0.5F, -6.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, -6.0F, -6.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 4).addBox(-0.5F, -6.0F, -8.0F, 1.0F, 0.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 7).addBox(-0.5F, -5.3F, -3.5F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 24.0F, 1.0F, 0.0F, 2.2253F, 0.0F));

				PartDefinition lwing_r1 = main.addOrReplaceChild("lwing_r1", CubeListBuilder.create().texOffs(5, 0).addBox(-2.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -6.0F, -4.5F, 0.0F, 0.0F, 0.0436F));

				PartDefinition rwing_r1 = main.addOrReplaceChild("rwing_r1", CubeListBuilder.create().texOffs(5, 4).addBox(0.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -6.0F, -4.5F, 0.0F, 0.0F, -0.0436F));

				PartDefinition main2 = partdefinition.addOrReplaceChild("main2", CubeListBuilder.create().texOffs(6, 6).addBox(6.1962F, -4.0F, -2.5981F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.1962F, -4.0F, -4.5981F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 4).addBox(6.1962F, -4.0F, -6.5981F, 1.0F, 0.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 7).addBox(6.1962F, -3.3F, -2.0981F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 24.0F, -2.0F, 0.0F, -1.0472F, 0.0F));

				PartDefinition lwing_r2 = main2.addOrReplaceChild("lwing_r2", CubeListBuilder.create().texOffs(5, 0).addBox(-2.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.1962F, -4.0F, -3.0981F, 0.0F, 0.0F, 0.0436F));

				PartDefinition rwing_r2 = main2.addOrReplaceChild("rwing_r2", CubeListBuilder.create().texOffs(5, 4).addBox(0.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.1962F, -4.0F, -3.0981F, 0.0F, 0.0F, -0.0436F));

				PartDefinition main3 = partdefinition.addOrReplaceChild("main3", CubeListBuilder.create().texOffs(6, 6).addBox(-4.9987F, -8.0F, -3.796F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.9987F, -8.0F, -5.796F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 4).addBox(-4.9987F, -8.0F, -7.796F, 1.0F, 0.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 7).addBox(-4.9987F, -7.3F, -3.296F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 24.0F, -1.0F, 0.0F, 0.48F, 0.0F));

				PartDefinition lwing_r3 = main3.addOrReplaceChild("lwing_r3", CubeListBuilder.create().texOffs(5, 0).addBox(-2.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.9987F, -8.0F, -4.296F, 0.0F, 0.0F, 0.0436F));

				PartDefinition rwing_r3 = main3.addOrReplaceChild("rwing_r3", CubeListBuilder.create().texOffs(5, 4).addBox(0.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.9987F, -8.0F, -4.296F, 0.0F, 0.0F, -0.0436F));

				PartDefinition main4 = partdefinition.addOrReplaceChild("main4", CubeListBuilder.create().texOffs(6, 6).addBox(4.9378F, -5.0F, -2.5357F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(4.9378F, -5.0F, -4.5357F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 4).addBox(4.9378F, -5.0F, -6.5357F, 1.0F, 0.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 7).addBox(4.9378F, -4.3F, -2.0357F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 24.0F, 0.0F, 0.0F, -2.0071F, 0.0F));

				PartDefinition lwing_r4 = main4.addOrReplaceChild("lwing_r4", CubeListBuilder.create().texOffs(5, 0).addBox(-2.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.9378F, -5.0F, -3.0357F, 0.0F, 0.0F, 0.0436F));

				PartDefinition rwing_r4 = main4.addOrReplaceChild("rwing_r4", CubeListBuilder.create().texOffs(5, 4).addBox(0.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.9378F, -5.0F, -3.0357F, 0.0F, 0.0F, -0.0436F));

				PartDefinition main5 = partdefinition.addOrReplaceChild("main5", CubeListBuilder.create().texOffs(6, 6).addBox(5.4762F, -6.0F, 2.9434F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.4762F, -6.0F, 0.9434F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 4).addBox(5.4762F, -6.0F, -1.0566F, 1.0F, 0.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 7).addBox(5.4762F, -5.3F, 3.4434F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 24.0F, 0.0F, 0.0F, 1.3963F, 0.0F));

				PartDefinition lwing_r5 = main5.addOrReplaceChild("lwing_r5", CubeListBuilder.create().texOffs(5, 0).addBox(-2.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.4762F, -6.0F, 2.4434F, 0.0F, 0.0F, 0.0436F));

				PartDefinition rwing_r5 = main5.addOrReplaceChild("rwing_r5", CubeListBuilder.create().texOffs(5, 4).addBox(0.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.4762F, -6.0F, 2.4434F, 0.0F, 0.0F, -0.0436F));

				return LayerDefinition.create(meshdefinition, 16, 16);
	}


	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float tick = entity.tickCount;
		main.yRot =2.7053F + tick * -0.05f; main.zRot = 3.14f;
		main2.yRot = 2.7053F + tick * -0.05f;  main2.zRot = 3.14f;
		main3.yRot = -0.8727F + tick * 0.05f;  main3.zRot = 3.14f;
		main4.yRot = 0.7854F + tick * -0.05f;  main4.zRot = 3.14f;
		main5.yRot = -0.2182F + tick * -0.05f;  main5.zRot = 3.14f;
		
		main.getChild("lwing_r1").zRot = (float) (Math.sin(tick*1f)*0.5f);
		main.getChild("rwing_r1").zRot = -main.getChild("lwing_r1").zRot;
		
		main2.getChild("lwing_r2").zRot = (float) (Math.sin(tick*1f)*0.5f);
		main2.getChild("rwing_r2").zRot = -main2.getChild("lwing_r2").zRot;
		
		main3.getChild("lwing_r3").zRot = (float) (Math.sin(tick*1f)*0.5f);
		main3.getChild("rwing_r3").zRot = -main3.getChild("lwing_r3").zRot;
		
		main4.getChild("lwing_r4").zRot = (float) (Math.sin(tick*1f)*0.5f);
		main4.getChild("rwing_r4").zRot = -main4.getChild("lwing_r4").zRot;
		
		main5.getChild("lwing_r5").zRot = (float) (Math.sin(tick*1f)*0.5f);
		main5.getChild("rwing_r5").zRot = -main5.getChild("lwing_r5").zRot;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		main.render(poseStack, buffer, packedLight, packedOverlay);
		main2.render(poseStack, buffer, packedLight, packedOverlay);
		main3.render(poseStack, buffer, packedLight, packedOverlay);
		main4.render(poseStack, buffer, packedLight, packedOverlay);
		main5.render(poseStack, buffer, packedLight, packedOverlay);
	}
}
