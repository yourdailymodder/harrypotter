package com.minecraftserverzone.harrypotter.mobs.troll;

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
 * Troll_HarryPotty - Attila_Nagy
 * Created using Modified Tabula 8.0.0 by YourDailyModder for 1.17.1/1.18.1
 */
public class TrollModel<T extends Entity> extends HierarchicalModel<T> implements HeadedModel{
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(HarryPotterMod.MODID, "troll"), "main");
    public final ModelPart root;
    public final ModelPart Head;
    public final ModelPart Body;
    public final ModelPart ArmR;
    public final ModelPart ArmL;
    public final ModelPart LegR;
    public final ModelPart LegL;
    public final ModelPart EarR;
    public final ModelPart EarL;
    public final ModelPart Head_1;
    public final ModelPart Belly;
    public final ModelPart ExtraFront;
    public final ModelPart ExtraBack;
    public final ModelPart Clothing;
    public final ModelPart ArmR2;
    public final ModelPart ArmR3;
    public final ModelPart Weapon;
    public final ModelPart Weapon2;
    public final ModelPart Weapon3;
    public final ModelPart ArmL2;
    public final ModelPart ArmL3;
    public final ModelPart LegR2;
    public final ModelPart LegR3;
    public final ModelPart LegL2;
    public final ModelPart LegL3;

    public TrollModel(ModelPart root) {
        this.root = root;
        this.Head = root.getChild("Head");
        this.Body = root.getChild("Body");
        this.ArmR = root.getChild("ArmR");
        this.ArmL = root.getChild("ArmL");
        this.LegR = root.getChild("LegR");
        this.LegL = root.getChild("LegL");
        this.EarR = Head.getChild("EarR");
        this.EarL = Head.getChild("EarL");
        this.Head_1 = Head.getChild("Head_1");
        this.Belly = Body.getChild("Belly");
        this.ExtraFront = Body.getChild("ExtraFront");
        this.ExtraBack = Body.getChild("ExtraBack");
        this.Clothing = Belly.getChild("Clothing");
        this.ArmR2 = ArmR.getChild("ArmR2");
        this.ArmR3 = ArmR2.getChild("ArmR3");
        this.Weapon = ArmR3.getChild("Weapon");
        this.Weapon2 = Weapon.getChild("Weapon2");
        this.Weapon3 = Weapon2.getChild("Weapon3");
        this.ArmL2 = ArmL.getChild("ArmL2");
        this.ArmL3 = ArmL2.getChild("ArmL3");
        this.LegR2 = LegR.getChild("LegR2");
        this.LegR3 = LegR2.getChild("LegR3");
        this.LegL2 = LegL.getChild("LegL2");
        this.LegL3 = LegL2.getChild("LegL3");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
            PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create()
            .texOffs(0, 0)            
            .addBox(-4.0F, -9.2F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(0.0F, -20.0F, 0.0F));

            PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create()
            .texOffs(0, 19)            
            .addBox(-8.0F, 0.0F, -2.0F, 16.0F, 20.0F, 6.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(0.0F, -20.0F, 0.0F));

            PartDefinition ArmR = partdefinition.addOrReplaceChild("ArmR", CubeListBuilder.create()
            .texOffs(45, 45)            
            .addBox(-4.0F, -2.0F, -3.0F, 5.0F, 12.0F, 6.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(-9.5F, -18.0F, 1.0F, 0.08726646259971647F, 0.0F, 0.08726646259971647F));

            PartDefinition ArmL = partdefinition.addOrReplaceChild("ArmL", CubeListBuilder.create()
            .texOffs(45, 45)
            .mirror()
            .addBox(-1.0F, -2.0F, -3.0F, 5.0F, 12.0F, 6.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(9.5F, -18.0F, 1.0F, 0.08726646259971647F, 0.0F, -0.08726646259971647F));

            PartDefinition LegR = partdefinition.addOrReplaceChild("LegR", CubeListBuilder.create()
            .texOffs(45, 64)            
            .addBox(-3.5F, 0.0F, -3.0F, 7.0F, 12.0F, 6.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(-4.0F, 0.0F, 1.0F));

            PartDefinition LegL = partdefinition.addOrReplaceChild("LegL", CubeListBuilder.create()
            .texOffs(45, 64)
            .mirror()
            .addBox(-3.5F, 0.0F, -3.0F, 7.0F, 12.0F, 6.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(4.0F, 0.0F, 1.0F));

            PartDefinition EarR = Head.addOrReplaceChild("EarR", CubeListBuilder.create()
            .texOffs(0, 0)            
            .addBox(-3.0F, -2.0F, -0.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(-4.0F, -5.0F, 0.0F, 0.0F, 0.4363323129985824F, 0.0F));

            PartDefinition EarL = Head.addOrReplaceChild("EarL", CubeListBuilder.create()
            .texOffs(0, 0)
            .mirror()
            .addBox(0.0F, -2.0F, -0.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(4.0F, -5.0F, 0.0F, 0.0F, -0.4363323129985824F, 0.0F));

            PartDefinition Head_1 = Head.addOrReplaceChild("Head_1", CubeListBuilder.create()
            .texOffs(33, 0)            
            .addBox(-4.0F, -9.2F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.2F, 0.4F, 0.2F))
            , PartPose.offset(0.0F, -0.0F, 0.0F));

            PartDefinition Belly = Body.addOrReplaceChild("Belly", CubeListBuilder.create()
            .texOffs(0, 46)            
            .addBox(-6.0F, 7.0F, -3.7F, 12.0F, 12.0F, 8.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition ExtraFront = Body.addOrReplaceChild("ExtraFront", CubeListBuilder.create()
            .texOffs(79, 1)            
            .addBox(-7.0F, -0.2F, -0.2F, 14.0F, 10.0F, 0.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(0.0F, 19.0F, -1.9F));

            PartDefinition ExtraBack = Body.addOrReplaceChild("ExtraBack", CubeListBuilder.create()
            .texOffs(65, 1)            
            .addBox(-7.0F, -0.2F, 0.2F, 14.0F, 10.0F, 0.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(0.0F, 19.0F, 3.9F));

            PartDefinition Clothing = Belly.addOrReplaceChild("Clothing", CubeListBuilder.create()
            .texOffs(45, 23)            
            .addBox(-8.5F, -0.2F, -2.6F, 17.0F, 14.0F, 7.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition ArmR2 = ArmR.addOrReplaceChild("ArmR2", CubeListBuilder.create()
            .texOffs(68, 45)            
            .addBox(-3.0F, 0.0F, -3.0F, 6.0F, 12.0F, 6.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(-1.5F, 10.0F, 0.0F, -0.17453292519943295F, 0.0F, 0.0F));

            PartDefinition ArmR3 = ArmR2.addOrReplaceChild("ArmR3", CubeListBuilder.create()
            .texOffs(93, 49)            
            .addBox(-2.6F, 0.0F, -3.0F, 5.0F, 8.0F, 6.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(0.0F, 12.0F, 0.0F));

            PartDefinition Weapon = ArmR3.addOrReplaceChild("Weapon", CubeListBuilder.create()
            .texOffs(0, 83)            
            .addBox(-1.3F, -1.4F, -3.8F, 3.0F, 3.0F, 12.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(0.5F, 3.9F, 0.0F));

            PartDefinition Weapon2 = Weapon.addOrReplaceChild("Weapon2", CubeListBuilder.create()
            .texOffs(31, 87)            
            .addBox(-2.0F, -2.0F, -8.0F, 4.0F, 4.0F, 8.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(0.0F, 0.0F, -3.7F));

            PartDefinition Weapon3 = Weapon2.addOrReplaceChild("Weapon3", CubeListBuilder.create()
            .texOffs(56, 87)            
            .addBox(-2.5F, -2.5F, -8.0F, 5.0F, 5.0F, 8.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(0.0F, 0.0F, -8.0F));

            PartDefinition ArmL2 = ArmL.addOrReplaceChild("ArmL2", CubeListBuilder.create()
            .texOffs(68, 45)
            .mirror()
            .addBox(-3.0F, 0.0F, -3.0F, 6.0F, 12.0F, 6.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(1.5F, 10.0F, 0.0F, -0.17453292519943295F, 0.0F, 0.0F));

            PartDefinition ArmL3 = ArmL2.addOrReplaceChild("ArmL3", CubeListBuilder.create()
            .texOffs(93, 49)
            .mirror()
            .addBox(-2.6F, 0.0F, -3.0F, 5.0F, 8.0F, 6.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(0.0F, 12.0F, 0.0F));

            PartDefinition LegR2 = LegR.addOrReplaceChild("LegR2", CubeListBuilder.create()
            .texOffs(72, 64)            
            .addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(-0.0F, 12.0F, 0.0F));

            PartDefinition LegR3 = LegR2.addOrReplaceChild("LegR3", CubeListBuilder.create()
            .texOffs(101, 76)            
            .addBox(-4.0F, -2.0F, -2.0F, 8.0F, 4.0F, 2.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(-0.0F, 10.1F, -3.0F));

            PartDefinition LegL2 = LegL.addOrReplaceChild("LegL2", CubeListBuilder.create()
            .texOffs(72, 64)
            .mirror()
            .addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(-0.0F, 12.0F, 0.0F));

            PartDefinition LegL3 = LegL2.addOrReplaceChild("LegL3", CubeListBuilder.create()
            .texOffs(101, 76)
            .mirror()
            .addBox(-4.0F, -2.0F, -2.0F, 8.0F, 4.0F, 2.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(-0.0F, 10.1F, -3.0F));


        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	      this.Head.xRot = headPitch * ((float)Math.PI / 180F);
	      this.Head.yRot = netHeadYaw * ((float)Math.PI / 180F);
	      
	      boolean flag = ((Troll) entity).getFallFlyingTicks() > 4;
	      float f = 1.0F;
	      if (flag) {
	         f = (float)entity.getDeltaMovement().lengthSqr();
	         f /= 0.2F;
	         f *= f * f;
	      }

	      if (f < 1.0F) {
	         f = 1.0F;
	      }
	      
	      this.LegR.xRot = Mth.cos(limbSwing * 0.6662F) * 0.6F * limbSwingAmount / f;
	      this.LegL.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 0.6F * limbSwingAmount / f;
    }
    
    public void prepareMobModel(T p_102957_, float p_102958_, float p_102959_, float p_102960_) {
        int i = ((Troll) p_102957_).getAttackAnimationTick();
        if (i > 0) {
           this.ArmR.xRot = -2.0F + 1.5F * Mth.triangleWave((float)i - p_102960_, 10.0F);
           this.ArmL.xRot = -2.0F + 1.5F * Mth.triangleWave((float)i - p_102960_, 10.0F);
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
	public ModelPart getHead() {
		return Head;
	}
}
