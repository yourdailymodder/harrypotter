package com.minecraftserverzone.harrypotter.mobs.inferius;

import com.minecraftserverzone.harrypotter.HarryPotterMod;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.AnimationUtils;
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
 * vZombie_HP - -
 * Created using Modified Tabula 8.0.0 by YourDailyModder for 1.17.1/1.18.1
 */
public class InferiusModel<T extends Entity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(HarryPotterMod.MODID, "inferius"), "main");
    public final ModelPart root;
    public final ModelPart Head;
    public final ModelPart Body1;
    public final ModelPart ArmR1;
    public final ModelPart ArmL1;
    public final ModelPart LegR1;
    public final ModelPart LegL1;
    public final ModelPart Body2;
    public final ModelPart Body3;
    public final ModelPart ArmR2;
    public final ModelPart ArmL2;
    public final ModelPart LegR2;
    public final ModelPart LegL2;

    public InferiusModel(ModelPart root) {
        this.root = root;
        this.Head = root.getChild("Head");
        this.Body1 = root.getChild("Body1");
        this.ArmR1 = root.getChild("ArmR1");
        this.ArmL1 = root.getChild("ArmL1");
        this.LegR1 = root.getChild("LegR1");
        this.LegL1 = root.getChild("LegL1");
        this.Body2 = Body1.getChild("Body2");
        this.Body3 = Body2.getChild("Body3");
        this.ArmR2 = ArmR1.getChild("ArmR2");
        this.ArmL2 = ArmL1.getChild("ArmL2");
        this.LegR2 = LegR1.getChild("LegR2");
        this.LegL2 = LegL1.getChild("LegL2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
            PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create()
            .texOffs(0, 0)            
            .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition Body1 = partdefinition.addOrReplaceChild("Body1", CubeListBuilder.create()
            .texOffs(16, 16)            
            .addBox(-3.5F, 0.0F, -2.0F, 7.0F, 6.0F, 4.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition ArmR1 = partdefinition.addOrReplaceChild("ArmR1", CubeListBuilder.create()
            .texOffs(40, 7)            
            .addBox(-1.0F, -2.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(-4.7F, 2.0F, 0.0F, 0.0F, 0.0F, 0.10000736647217022F));

            PartDefinition ArmL1 = partdefinition.addOrReplaceChild("ArmL1", CubeListBuilder.create()
            .texOffs(40, 7)
            .mirror()
            .addBox(-1.0F, -2.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(4.7F, 2.0F, 0.0F, 0.0F, 0.0F, -0.10000736647217022F));

            PartDefinition LegR1 = partdefinition.addOrReplaceChild("LegR1", CubeListBuilder.create()
            .texOffs(0, 16)            
            .addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(-2.0F, 12.0F, 0.1F));

            PartDefinition LegL1 = partdefinition.addOrReplaceChild("LegL1", CubeListBuilder.create()
            .texOffs(0, 16)
            .mirror()
            .addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(2.0F, 12.0F, 0.1F));

            PartDefinition Body2 = Body1.addOrReplaceChild("Body2", CubeListBuilder.create()
            .texOffs(16, 26)            
            .addBox(-3.0F, 0.0F, -1.5F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(0.0F, 6.0F, 0.0F));

            PartDefinition Body3 = Body2.addOrReplaceChild("Body3", CubeListBuilder.create()
            .texOffs(35, 26)            
            .addBox(-3.5F, 0.0F, -1.5F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(0.0F, 3.0F, 0.0F));

            PartDefinition ArmR2 = ArmR1.addOrReplaceChild("ArmR2", CubeListBuilder.create()
            .texOffs(40, 16)            
            .addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(0.0F, 4.0F, 0.0F));

            PartDefinition ArmL2 = ArmL1.addOrReplaceChild("ArmL2", CubeListBuilder.create()
            .texOffs(40, 16)            
            .addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(0.0F, 4.0F, 0.0F));

            PartDefinition LegR2 = LegR1.addOrReplaceChild("LegR2", CubeListBuilder.create()
            .texOffs(0, 24)            
            .addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(0.0F, 6.0F, 0.0F));

            PartDefinition LegL2 = LegL1.addOrReplaceChild("LegL2", CubeListBuilder.create()
            .texOffs(0, 24)
            .mirror()
            .addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(0.0F, 6.0F, 0.0F));


        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    	@Override
        public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    	      this.Head.xRot = headPitch * ((float)Math.PI / 180F);
    	      this.Head.yRot = netHeadYaw * ((float)Math.PI / 180F);
    	      
    	      boolean flag = ((Inferius) entity).getFallFlyingTicks() > 4;
    	      float f = 1.0F;
    	      if (flag) {
    	         f = (float)entity.getDeltaMovement().lengthSqr();
    	         f /= 0.2F;
    	         f *= f * f;
    	      }

    	      if (f < 1.0F) {
    	         f = 1.0F;
    	      }
    	      
    	      this.LegR1.xRot = Mth.cos(limbSwing * 0.6662F) * 0.6F * limbSwingAmount / f;
    	      this.LegL1.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 0.6F * limbSwingAmount / f;
    	      
    	      AnimationUtils.animateZombieArms(this.ArmL1, this.ArmR1, this.isAggressive(entity), this.attackTime, ageInTicks);
    }
        
    public boolean isAggressive(T p_104155_) {
    	return ((Inferius) p_104155_).isAggressive();
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.renderToBuffer(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
