package com.minecraftserverzone.harrypotter.mobs.dementor;

import com.minecraftserverzone.harrypotter.HarryPotterMod;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

/**
 * Dementor - Ati-1
 * Created using Modified Tabula 8.0.0 by YourDailyModder for 1.17.1/1.18.1
 */
public class DementorModel<T extends Entity> extends HierarchicalModel<T> implements HeadedModel{
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(HarryPotterMod.MODID, "dementor"), "main");
    public final ModelPart root;
    public final ModelPart Head;
    public final ModelPart Body;
    public final ModelPart ArmL;
    public final ModelPart ArmR;
    public final ModelPart Head_1;
    public final ModelPart UpperBody;
    public final ModelPart LowerBody;
    public final ModelPart ArmL_1;
    public final ModelPart ArmR_1;

    public DementorModel(ModelPart root) {
        this.root = root;
        this.Head = root.getChild("Head");
        this.Body = root.getChild("Body");
        this.ArmL = root.getChild("ArmL");
        this.ArmR = root.getChild("ArmR");
        this.Head_1 = Head.getChild("Head_1");
        this.UpperBody = Body.getChild("UpperBody");
        this.LowerBody = Body.getChild("LowerBody");
        this.ArmL_1 = ArmL.getChild("ArmL_1");
        this.ArmR_1 = ArmR.getChild("ArmR_1");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
            PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create()
            .texOffs(32, 0)            
            .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.1F, 0.1F, 0.1F))
            , PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create()
            .texOffs(24, 16)            
            .addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F, 0.0F, 0.2F))
            , PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition ArmL = partdefinition.addOrReplaceChild("ArmL", CubeListBuilder.create()
            .texOffs(48, 16)
            .mirror()
            .addBox(-1.1F, -1.7F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2F, 0.4F, 0.2F))
            , PartPose.offset(5.2F, 2.0F, 0.0F));

            PartDefinition ArmR = partdefinition.addOrReplaceChild("ArmR", CubeListBuilder.create()
            .texOffs(48, 16)            
            .addBox(-2.9F, -1.7F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2F, 0.4F, 0.2F))
            , PartPose.offset(-5.2F, 2.0F, 0.0F));

            PartDefinition Head_1 = Head.addOrReplaceChild("Head_1", CubeListBuilder.create()
            .texOffs(0, 0)            
            .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F, 0.0F, -0.7F))
            , PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition UpperBody = Body.addOrReplaceChild("UpperBody", CubeListBuilder.create()
            .texOffs(0, 16)            
            .addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition LowerBody = Body.addOrReplaceChild("LowerBody", CubeListBuilder.create()
            .texOffs(0, 32)            
            .addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(0.0F, 11.5F, 0.0F, 0.1563815016444822F, 0.0F, 0.0F));

            PartDefinition ArmL_1 = ArmL.addOrReplaceChild("ArmL_1", CubeListBuilder.create()
            .texOffs(48, 32)
            .mirror()
            .addBox(-0.7F, -1.5F, -1.5F, 3.0F, 12.0F, 3.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition ArmR_1 = ArmR.addOrReplaceChild("ArmR_1", CubeListBuilder.create()
            .texOffs(48, 32)            
            .addBox(-2.3F, -1.5F, -1.5F, 3.0F, 12.0F, 3.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(0.0F, 0.0F, 0.0F));


        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    	  float f1 = 16.0F;
	      float f = (f1 + ageInTicks) * 7.448451F * ((float)Math.PI / 180F);
	      
	      this.Head.xRot = headPitch * ((float)Math.PI / 180F);
	      this.Head.yRot = netHeadYaw * ((float)Math.PI / 180F);
	      
	      this.Body.xRot = (-(Mth.cos(f * 2.0F) * 2.0F) * ((float)Math.PI / 180F));
	      this.LowerBody.xRot = 0.1563815016444822F -(Mth.cos(f * 2.0F) * 5.0F) * ((float)Math.PI / 180F);

    	  this.ArmR.xRot = -(Mth.cos(f * 1.0F) * 5.0F) * ((float)Math.PI / 180F)*2;
          this.ArmL.xRot = (Mth.cos(f * 1.0F) * 5.0F) * ((float)Math.PI / 180F)*2;

	      this.ArmR.yRot = -(Mth.cos(f * 1.0F) * 5.0F) * ((float)Math.PI / 180F);
	      this.ArmR.zRot = -(Mth.cos(f * 1.0F) * 1.0F) * ((float)Math.PI / 180F);

	      this.ArmL.yRot = (Mth.cos(f * 1.0F) * 5.0F) * ((float)Math.PI / 180F);
	      this.ArmL.zRot = (Mth.cos(f * 1.0F) * 1.0F) * ((float)Math.PI / 180F); 
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.renderToBuffer(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
	public ModelPart getHead() {
		return Head;
	}
}
