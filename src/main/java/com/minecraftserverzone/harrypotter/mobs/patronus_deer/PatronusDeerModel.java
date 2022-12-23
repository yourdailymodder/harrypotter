package com.minecraftserverzone.harrypotter.mobs.patronus_deer;

import com.minecraftserverzone.harrypotter.HarryPotterMod;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

/**
 * Deer_HP - Ati-90
 * Created using Modified Tabula 8.0.0 by YourDailyModder for 1.17.1/1.18.1
 */
public class PatronusDeerModel<T extends Entity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(HarryPotterMod.MODID, "patronus_deer"), "main");
    public final ModelPart root;
    public final ModelPart HeadBase;
    public final ModelPart Body1;
    public final ModelPart FrontLegL;
    public final ModelPart FrontLegR;
    public final ModelPart BackLegR;
    public final ModelPart BackLegL;
    public final ModelPart Head1;
    public final ModelPart EarL;
    public final ModelPart EarR;
    public final ModelPart NeckBase;
    public final ModelPart Body2;
    public final ModelPart Neck;
    public final ModelPart Body3;
    public final ModelPart FrontLegL2;
    public final ModelPart FrontLegL3;
    public final ModelPart FrontLegR2;
    public final ModelPart FrontLegR3;
    public final ModelPart BackLegR2;
    public final ModelPart BackLegR3;
    public final ModelPart BackLegR4;
    public final ModelPart BackLegL2;
    public final ModelPart BackLegL3;
    public final ModelPart BackLegL4;

    public PatronusDeerModel(ModelPart root) {
        this.root = root;
        this.HeadBase = root.getChild("HeadBase");
        this.Body1 = root.getChild("Body1");
        this.FrontLegL = root.getChild("FrontLegL");
        this.FrontLegR = root.getChild("FrontLegR");
        this.BackLegR = root.getChild("BackLegR");
        this.BackLegL = root.getChild("BackLegL");
        this.Head1 = HeadBase.getChild("Head1");
        this.EarL = HeadBase.getChild("EarL");
        this.EarR = HeadBase.getChild("EarR");
        this.NeckBase = Body1.getChild("NeckBase");
        this.Body2 = Body1.getChild("Body2");
        this.Neck = NeckBase.getChild("Neck");
        this.Body3 = Body2.getChild("Body3");
        this.FrontLegL2 = FrontLegL.getChild("FrontLegL2");
        this.FrontLegL3 = FrontLegL2.getChild("FrontLegL3");
        this.FrontLegR2 = FrontLegR.getChild("FrontLegR2");
        this.FrontLegR3 = FrontLegR2.getChild("FrontLegR3");
        this.BackLegR2 = BackLegR.getChild("BackLegR2");
        this.BackLegR3 = BackLegR2.getChild("BackLegR3");
        this.BackLegR4 = BackLegR3.getChild("BackLegR4");
        this.BackLegL2 = BackLegL.getChild("BackLegL2");
        this.BackLegL3 = BackLegL2.getChild("BackLegL3");
        this.BackLegL4 = BackLegL3.getChild("BackLegL4");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
            PartDefinition HeadBase = partdefinition.addOrReplaceChild("HeadBase", CubeListBuilder.create()
            .texOffs(0, 0)            
            .addBox(-3.0F, -3.0F, -3.9F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(0.0F, -4.4F, -6.2F));

            PartDefinition Body1 = partdefinition.addOrReplaceChild("Body1", CubeListBuilder.create()
            .texOffs(21, 15)            
            .addBox(-4.0F, 0.0F, 0.0F, 8.0F, 8.0F, 10.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(0.0F, 1.0F, -4.6F));

            PartDefinition FrontLegL = partdefinition.addOrReplaceChild("FrontLegL", CubeListBuilder.create()
            .texOffs(59, 0)
            .mirror()
            .addBox(-1.5F, -1.3F, -1.0F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(4.0F, 5.5F, -3.0F));

            PartDefinition FrontLegR = partdefinition.addOrReplaceChild("FrontLegR", CubeListBuilder.create()
            .texOffs(59, 0)
            .mirror()
            .addBox(-1.5F, -1.3F, -1.0F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(-4.0F, 5.5F, -3.0F));

            PartDefinition BackLegR = partdefinition.addOrReplaceChild("BackLegR", CubeListBuilder.create()
            .texOffs(59, 15)
            .mirror()
            .addBox(-2.0F, -1.3F, -1.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(-3.0F, 5.0F, 6.9F, -0.10471975511965977F, 0.0F, 0.0F));

            PartDefinition BackLegL = partdefinition.addOrReplaceChild("BackLegL", CubeListBuilder.create()
            .texOffs(59, 15)
            .mirror()
            .addBox(-2.0F, -1.3F, -1.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(3.0F, 5.0F, 6.9F, -0.10471975511965977F, 0.0F, 0.0F));

            PartDefinition Head1 = HeadBase.addOrReplaceChild("Head1", CubeListBuilder.create()
            .texOffs(1, 12)            
            .addBox(-1.5F, -1.9F, -3.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.2F, 0.0F, 0.0F))
            , PartPose.offset(0.0F, 1.4F, -3.3F));

            PartDefinition EarL = HeadBase.addOrReplaceChild("EarL", CubeListBuilder.create()
            .texOffs(21, 1)
            .mirror()
            .addBox(-1.0F, -3.0F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(2.0F, -2.0F, -1.5F, 0.17453292519943295F, -0.19198621771937624F, 0.6981317007977318F));

            PartDefinition EarR = HeadBase.addOrReplaceChild("EarR", CubeListBuilder.create()
            .texOffs(21, 1)            
            .addBox(-1.0F, -3.0F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(-2.0F, -2.0F, -1.5F, 0.17453292519943295F, 0.19198621771937624F, -0.6981317007977318F));

            PartDefinition NeckBase = Body1.addOrReplaceChild("NeckBase", CubeListBuilder.create()
            .texOffs(0, 35)            
            .addBox(-2.5F, -3.0F, -3.0F, 5.0F, 6.0F, 6.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(0.0F, 2.4F, 0.0F, 0.3909537457888271F, 0.0F, 0.0F));

            PartDefinition Body2 = Body1.addOrReplaceChild("Body2", CubeListBuilder.create()
            .texOffs(26, 35)            
            .addBox(-3.0F, -3.0F, -0.0F, 6.0F, 7.0F, 5.0F, new CubeDeformation(0.2F, 0.0F, 0.0F))
            , PartPose.offset(0.0F, 3.3F, 10.0F));

            PartDefinition Neck = NeckBase.addOrReplaceChild("Neck", CubeListBuilder.create()
            .texOffs(0, 22)            
            .addBox(-2.0F, -5.0F, -2.5F, 4.0F, 6.0F, 5.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(0.0F, -3.0F, -0.1F, -0.27366763203903305F, 0.0F, 0.0F));

            PartDefinition Body3 = Body2.addOrReplaceChild("Body3", CubeListBuilder.create()
            .texOffs(19, 16)            
            .addBox(-1.5F, -4.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.2F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(0.0F, -1.5F, 4.8F, -0.6646214111173737F, 0.0F, 0.0F));

            PartDefinition FrontLegL2 = FrontLegL.addOrReplaceChild("FrontLegL2", CubeListBuilder.create()
            .texOffs(73, 1)
            .mirror()
            .addBox(-1.5F, 0.0F, -1.0F, 3.0F, 9.0F, 2.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(0.0F, 7.3F, 0.3F));

            PartDefinition FrontLegL3 = FrontLegL2.addOrReplaceChild("FrontLegL3", CubeListBuilder.create()
            .texOffs(84, 7)
            .mirror()
            .addBox(-1.4F, -0.6F, -2.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(0.0F, 9.0F, 0.0F));

            PartDefinition FrontLegR2 = FrontLegR.addOrReplaceChild("FrontLegR2", CubeListBuilder.create()
            .texOffs(73, 1)
            .mirror()
            .addBox(-1.5F, 0.0F, -1.0F, 3.0F, 9.0F, 2.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(0.0F, 7.3F, 0.3F));

            PartDefinition FrontLegR3 = FrontLegR2.addOrReplaceChild("FrontLegR3", CubeListBuilder.create()
            .texOffs(84, 7)
            .mirror()
            .addBox(-1.4F, -0.6F, -2.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(0.0F, 9.0F, 0.0F));

            PartDefinition BackLegR2 = BackLegR.addOrReplaceChild("BackLegR2", CubeListBuilder.create()
            .texOffs(76, 20)
            .mirror()
            .addBox(-1.5F, -1.5F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(0.0F, 6.6F, 1.2F, 1.0430087316970393F, 0.0F, 0.0F));

            PartDefinition BackLegR3 = BackLegR2.addOrReplaceChild("BackLegR3", CubeListBuilder.create()
            .texOffs(87, 16)
            .mirror()
            .addBox(-1.5F, 0.0F, -1.0F, 3.0F, 9.0F, 2.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(0.0F, 2.5F, 0.3F, -0.9384635467868342F, 0.0F, 0.0F));

            PartDefinition BackLegR4 = BackLegR3.addOrReplaceChild("BackLegR4", CubeListBuilder.create()
            .texOffs(98, 22)
            .mirror()
            .addBox(-1.4F, -0.6F, -2.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(0.0F, 9.0F, 0.0F));

            PartDefinition BackLegL2 = BackLegL.addOrReplaceChild("BackLegL2", CubeListBuilder.create()
            .texOffs(76, 20)
            .mirror()
            .addBox(-1.5F, -1.5F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(0.0F, 6.6F, 1.2F, 1.0430087316970393F, 0.0F, 0.0F));

            PartDefinition BackLegL3 = BackLegL2.addOrReplaceChild("BackLegL3", CubeListBuilder.create()
            .texOffs(87, 16)
            .mirror()
            .addBox(-1.5F, 0.0F, -1.0F, 3.0F, 9.0F, 2.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(0.0F, 2.5F, 0.3F, -0.9384635467868342F, 0.0F, 0.0F));

            PartDefinition BackLegL4 = BackLegL3.addOrReplaceChild("BackLegL4", CubeListBuilder.create()
            .texOffs(98, 22)
            .mirror()
            .addBox(-1.4F, -0.6F, -2.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(0.0F, 9.0F, 0.0F));


        return LayerDefinition.create(meshdefinition, 128, 64);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    	this.HeadBase.xRot = headPitch * ((float)Math.PI / 180F);
        this.HeadBase.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.BackLegR.xRot = Mth.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount;
        this.BackLegL.xRot = BackLegR.xRot;
        this.FrontLegR.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 0.8F * limbSwingAmount;
        this.FrontLegL.xRot = FrontLegR.xRot;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    	MultiBufferSource.BufferSource bufferIn = Minecraft.getInstance().renderBuffers().bufferSource();
    	VertexConsumer vertexconsumer = bufferIn.getBuffer(RenderType.entityTranslucent(PatronusDeerRenderer.LOCATION));
    	super.renderToBuffer(poseStack, vertexconsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
