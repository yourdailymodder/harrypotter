package com.minecraftserverzone.harrypotter.mobs.death_eater;

import com.minecraftserverzone.harrypotter.HarryPotterMod;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.ArmedModel;
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
import net.minecraft.world.entity.HumanoidArm;

/**
 * DeathEater - Ati-1
 * Created using Modified Tabula 8.0.0 by YourDailyModder for 1.17.1/1.18.1
 */
public class DeathEaterModel<T extends Entity> extends HierarchicalModel<T> implements ArmedModel, HeadedModel{
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(HarryPotterMod.MODID, "death_eater"), "main");
    public final ModelPart root;
    public final ModelPart Head;
    public final ModelPart Body;
    public final ModelPart ArmL;
    public final ModelPart ArmR;
    public final ModelPart LegL;
    public final ModelPart LegR;
    public final ModelPart Head_1;
    public final ModelPart Hat;
    public final ModelPart Hat_1;
    public final ModelPart Hat_2;
    public final ModelPart Body_1;
    public final ModelPart ArmL_1;
    public final ModelPart ArmR_1;
    public final ModelPart LegL_1;
    public final ModelPart LegR_1;

    public DeathEaterModel(ModelPart root) {
        this.root = root;
        this.Head = root.getChild("Head");
        this.Body = root.getChild("Body");
        this.ArmL = root.getChild("ArmL");
        this.ArmR = root.getChild("ArmR");
        this.LegL = root.getChild("LegL");
        this.LegR = root.getChild("LegR");
        this.Head_1 = Head.getChild("Head_1");
        this.Hat = Head.getChild("Hat");
        this.Hat_1 = Hat.getChild("Hat_1");
        this.Hat_2 = Hat_1.getChild("Hat_2");
        this.Body_1 = Body.getChild("Body_1");
        this.ArmL_1 = ArmL.getChild("ArmL_1");
        this.ArmR_1 = ArmR.getChild("ArmR_1");
        this.LegL_1 = LegL.getChild("LegL_1");
        this.LegR_1 = LegR.getChild("LegR_1");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
            PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create()
            .texOffs(0, 0)            
            .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F, 0.0F, -0.5F))
            , PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create()
            .texOffs(0, 16)            
            .addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition ArmL = partdefinition.addOrReplaceChild("ArmL", CubeListBuilder.create()
            .texOffs(48, 16)
            .mirror()
            .addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(5.0F, 2.0F, 0.1F));

            PartDefinition ArmR = partdefinition.addOrReplaceChild("ArmR", CubeListBuilder.create()
            .texOffs(48, 16)            
            .addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(-5.0F, 2.0F, 0.1F));

            PartDefinition LegL = partdefinition.addOrReplaceChild("LegL", CubeListBuilder.create()
            .texOffs(0, 32)
            .mirror()
            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(2.0F, 12.0F, 0.0F));

            PartDefinition LegR = partdefinition.addOrReplaceChild("LegR", CubeListBuilder.create()
            .texOffs(0, 32)            
            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(-2.0F, 12.0F, 0.0F));

            PartDefinition Head_1 = Head.addOrReplaceChild("Head_1", CubeListBuilder.create()
            .texOffs(32, 0)            
            .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.2F, 0.2F, 0.2F))
            , PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition Hat = Head.addOrReplaceChild("Hat", CubeListBuilder.create()
            .texOffs(17, 48)            
            .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.2F, 0.2F, 0.2F))
            , PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition Hat_1 = Hat.addOrReplaceChild("Hat_1", CubeListBuilder.create()
            .texOffs(17, 39)            
            .addBox(-3.0F, -2.2F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.2F, 0.2F, 0.2F))
            , PartPose.offset(0.0F, -9.0F, 0.4F));

            PartDefinition Hat_2 = Hat_1.addOrReplaceChild("Hat_2", CubeListBuilder.create()
            .texOffs(17, 33)            
            .addBox(-1.5F, -2.1F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.2F, 0.2F, 0.2F))
            , PartPose.offset(0.0F, -3.3F, 1.0F));

            PartDefinition Body_1 = Body.addOrReplaceChild("Body_1", CubeListBuilder.create()
            .texOffs(24, 16)            
            .addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.1F, 0.2F, 0.2F))
            , PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition ArmL_1 = ArmL.addOrReplaceChild("ArmL_1", CubeListBuilder.create()
            .texOffs(48, 32)
            .mirror()
            .addBox(-1.0F, -2.1F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2F, 0.2F, 0.2F))
            , PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition ArmR_1 = ArmR.addOrReplaceChild("ArmR_1", CubeListBuilder.create()
            .texOffs(48, 32)            
            .addBox(-3.0F, -2.1F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2F, 0.2F, 0.2F))
            , PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition LegL_1 = LegL.addOrReplaceChild("LegL_1", CubeListBuilder.create()
            .texOffs(0, 48)
            .mirror()
            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2F, 0.2F, 0.2F))
            , PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition LegR_1 = LegR.addOrReplaceChild("LegR_1", CubeListBuilder.create()
            .texOffs(0, 48)            
            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2F, 0.2F, 0.2F))
            , PartPose.offset(0.0F, 0.0F, 0.0F));


        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	      this.Head.xRot = headPitch * ((float)Math.PI / 180F);
	      this.Head.yRot = netHeadYaw * ((float)Math.PI / 180F);
	      
	      boolean flag = ((DeathEater) entity).getFallFlyingTicks() > 4;
	      float f = 1.0F;
	      if (flag) {
	         f = (float)entity.getDeltaMovement().lengthSqr();
	         f /= 0.2F;
	         f *= f * f;
	      }

	      if (f < 1.0F) {
	         f = 1.0F;
	      }
	      
	      this.LegR.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
	      this.LegL.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / f;
    }
    
    public void prepareMobModel(T p_102957_, float p_102958_, float p_102959_, float p_102960_) {
        int i = ((DeathEater) p_102957_).getAttackAnimationTick();
        if (i > 0) {
           this.ArmR.xRot = -2.0F + 1.5F * Mth.triangleWave((float)i - p_102960_, 10.0F);
           this.ArmL.xRot = (-0.2F - 1.5F * Mth.triangleWave(p_102958_, 13.0F)) * p_102959_;
        } else {
           this.ArmR.xRot = (-0.2F + 1.5F * Mth.triangleWave(p_102958_, 13.0F)) * p_102959_;
           this.ArmL.xRot = (-0.2F - 1.5F * Mth.triangleWave(p_102958_, 13.0F)) * p_102959_;
        }
     }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.renderToBuffer(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

	@Override
    public void translateToHand(HumanoidArm p_102854_, PoseStack p_102855_) {
        this.getArm(p_102854_).translateAndRotate(p_102855_);
     }
	
	protected ModelPart getArm(HumanoidArm p_102852_) {
	      return this.ArmR;
	   }

	@Override
	public ModelPart getHead() {
		return Head;
	}
}
