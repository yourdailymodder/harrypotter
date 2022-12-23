package com.minecraftserverzone.harrypotter.mobs.acromantula;

import com.minecraftserverzone.harrypotter.HarryPotterMod;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

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
 * vAragog_HP - Ati-90
 * Created using Modified Tabula 8.0.0 by YourDailyModder for 1.17.1/1.18.1
 */
public class AcromantulaModel<T extends Entity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(HarryPotterMod.MODID, "acromantula"), "main");
    public final ModelPart root;
    public final ModelPart Body;
    public final ModelPart Butt;
    public final ModelPart LegR1;
    public final ModelPart LegR2;
    public final ModelPart LegR3;
    public final ModelPart LegR4;
    public final ModelPart LegL1;
    public final ModelPart LegL2;
    public final ModelPart LegL3;
    public final ModelPart LegL4;
    public final ModelPart FrontLegR1;
    public final ModelPart FrontLegL1;
    public final ModelPart Head;
    public final ModelPart Mouth;
    public final ModelPart FrontLegR2;
    public final ModelPart FrontLegR3;
    public final ModelPart FrontLegL2;
    public final ModelPart FrontLegL3;
    public final ModelPart Butt2;
    public final ModelPart Butt2_1;
    public final ModelPart LegR11;
    public final ModelPart LegR12;
    public final ModelPart LegR21;
    public final ModelPart LegR22;
    public final ModelPart LegR31;
    public final ModelPart LegR32;
    public final ModelPart LegR41;
    public final ModelPart LegR42;
    public final ModelPart LegL11;
    public final ModelPart LegL12;
    public final ModelPart LegL21;
    public final ModelPart LegL22;
    public final ModelPart LegL31;
    public final ModelPart LegL32;
    public final ModelPart LegL41;
    public final ModelPart LegL42;

    public AcromantulaModel(ModelPart root) {
        this.root = root;
        this.Body = root.getChild("Body");
        this.Butt = root.getChild("Butt");
        this.LegR1 = root.getChild("LegR1");
        this.LegR2 = root.getChild("LegR2");
        this.LegR3 = root.getChild("LegR3");
        this.LegR4 = root.getChild("LegR4");
        this.LegL1 = root.getChild("LegL1");
        this.LegL2 = root.getChild("LegL2");
        this.LegL3 = root.getChild("LegL3");
        this.LegL4 = root.getChild("LegL4");
        this.FrontLegR1 = Body.getChild("FrontLegR1");
        this.FrontLegL1 = Body.getChild("FrontLegL1");
        this.Head = Body.getChild("Head");
        this.Mouth = Body.getChild("Mouth");
        this.FrontLegR2 = FrontLegR1.getChild("FrontLegR2");
        this.FrontLegR3 = FrontLegR2.getChild("FrontLegR3");
        this.FrontLegL2 = FrontLegL1.getChild("FrontLegL2");
        this.FrontLegL3 = FrontLegL2.getChild("FrontLegL3");
        this.Butt2 = Butt.getChild("Butt2");
        this.Butt2_1 = Butt2.getChild("Butt2_1");
        this.LegR11 = LegR1.getChild("LegR11");
        this.LegR12 = LegR11.getChild("LegR12");
        this.LegR21 = LegR2.getChild("LegR21");
        this.LegR22 = LegR21.getChild("LegR22");
        this.LegR31 = LegR3.getChild("LegR31");
        this.LegR32 = LegR31.getChild("LegR32");
        this.LegR41 = LegR4.getChild("LegR41");
        this.LegR42 = LegR41.getChild("LegR42");
        this.LegL11 = LegL1.getChild("LegL11");
        this.LegL12 = LegL11.getChild("LegL12");
        this.LegL21 = LegL2.getChild("LegL21");
        this.LegL22 = LegL21.getChild("LegL22");
        this.LegL31 = LegL3.getChild("LegL31");
        this.LegL32 = LegL31.getChild("LegL32");
        this.LegL41 = LegL4.getChild("LegL41");
        this.LegL42 = LegL41.getChild("LegL42");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
            PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create()
            .texOffs(0, 19)            
            .addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(0.0F, 15.0F, 0.0F));

            PartDefinition Butt = partdefinition.addOrReplaceChild("Butt", CubeListBuilder.create()
            .texOffs(0, 36)            
            .addBox(-4.0F, -3.3F, 0.1F, 8.0F, 6.6F, 7.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(0.0F, 14.3F, 2.4F, -0.0781907508222411F, 0.0F, 0.0F));

            PartDefinition LegR1 = partdefinition.addOrReplaceChild("LegR1", CubeListBuilder.create()
            .texOffs(39, 0)            
            .addBox(-1.0F, -1.0F, -9.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(-3.0F, 17.0F, -2.0F, -0.3490658503988659F, 0.6832964287873909F, 0.0F));

            PartDefinition LegR2 = partdefinition.addOrReplaceChild("LegR2", CubeListBuilder.create()
            .texOffs(39, 0)            
            .addBox(-1.0F, -1.0F, -9.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(-3.0F, 17.0F, -1.0F, -0.3490658503988659F, 1.1843804410559964F, 0.0F));

            PartDefinition LegR3 = partdefinition.addOrReplaceChild("LegR3", CubeListBuilder.create()
            .texOffs(39, 0)            
            .addBox(-1.0F, -1.0F, -9.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(-3.0F, 17.0F, 0.0F, -0.3490658503988659F, 1.7765706615839945F, 0.0F));

            PartDefinition LegR4 = partdefinition.addOrReplaceChild("LegR4", CubeListBuilder.create()
            .texOffs(39, 0)            
            .addBox(-1.0F, -1.0F, -9.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(-3.0F, 17.0F, 1.5F, -0.3490658503988659F, 2.2321015697229036F, 0.0F));

            PartDefinition LegL1 = partdefinition.addOrReplaceChild("LegL1", CubeListBuilder.create()
            .texOffs(39, 0)
            .mirror()
            .addBox(-1.0F, -1.0F, -9.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(3.0F, 17.0F, -2.0F, -0.3490658503988659F, -0.6832964287873909F, 0.0F));

            PartDefinition LegL2 = partdefinition.addOrReplaceChild("LegL2", CubeListBuilder.create()
            .texOffs(39, 0)
            .mirror()
            .addBox(-1.0F, -1.0F, -9.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(3.0F, 17.0F, -1.0F, -0.3490658503988659F, -1.1843804410559964F, 0.0F));

            PartDefinition LegL3 = partdefinition.addOrReplaceChild("LegL3", CubeListBuilder.create()
            .texOffs(39, 0)
            .mirror()
            .addBox(-1.0F, -1.0F, -9.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(3.0F, 17.0F, -0.0F, -0.3490658503988659F, -1.7765706615839945F, 0.0F));

            PartDefinition LegL4 = partdefinition.addOrReplaceChild("LegL4", CubeListBuilder.create()
            .texOffs(39, 0)
            .mirror()
            .addBox(-1.0F, -1.0F, -9.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(3.0F, 17.0F, 1.5F, -0.3490658503988659F, -2.2321015697229036F, 0.0F));

            PartDefinition FrontLegR1 = Body.addOrReplaceChild("FrontLegR1", CubeListBuilder.create()
            .texOffs(26, 27)            
            .addBox(-0.5F, -0.5F, -4.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(-2.1F, -0.5F, -2.3F, 0.2141519072091875F, 0.27366763203903305F, 0.0F));

            PartDefinition FrontLegL1 = Body.addOrReplaceChild("FrontLegL1", CubeListBuilder.create()
            .texOffs(26, 27)            
            .addBox(-0.5F, -0.5F, -4.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(2.1F, -0.5F, -2.3F, 0.2141519072091875F, -0.27366763203903305F, 0.0F));

            PartDefinition Head = Body.addOrReplaceChild("Head", CubeListBuilder.create()
            .texOffs(19, 3)            
            .addBox(-1.5F, -0.5F, -1.8F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offset(0.0F, -3.5F, -0.3F));

            PartDefinition Mouth = Body.addOrReplaceChild("Mouth", CubeListBuilder.create()
            .texOffs(0, 0)            
            .addBox(-2.5F, -3.0F, -2.7F, 5.0F, 6.0F, 3.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(0.0F, 1.9F, -2.3F, -0.1563815016444822F, 0.0F, 0.0F));

            PartDefinition FrontLegR2 = FrontLegR1.addOrReplaceChild("FrontLegR2", CubeListBuilder.create()
            .texOffs(26, 27)            
            .addBox(-0.5F, -0.5F, -4.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(0.0F, 0.0F, -3.7F, 0.4878195392482206F, 0.0F, 0.0F));

            PartDefinition FrontLegR3 = FrontLegR2.addOrReplaceChild("FrontLegR3", CubeListBuilder.create()
            .texOffs(28, 29)            
            .addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(0.0F, 0.0F, -3.7F, 0.2532472784591188F, 0.0F, 0.0F));

            PartDefinition FrontLegL2 = FrontLegL1.addOrReplaceChild("FrontLegL2", CubeListBuilder.create()
            .texOffs(26, 27)            
            .addBox(-0.5F, -0.5F, -4.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(0.0F, 0.0F, -3.7F, 0.4878195392482206F, 0.0F, 0.0F));

            PartDefinition FrontLegL3 = FrontLegL2.addOrReplaceChild("FrontLegL3", CubeListBuilder.create()
            .texOffs(28, 29)            
            .addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(0.0F, 0.0F, -3.7F, 0.2532472784591188F, 0.0F, 0.0F));

            PartDefinition Butt2 = Butt.addOrReplaceChild("Butt2", CubeListBuilder.create()
            .texOffs(0, 51)            
            .addBox(-3.5F, -3.8F, 0.0F, 7.0F, 6.0F, 4.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(0.0F, 0.8F, 6.0F, -0.11728612207217244F, 0.0F, 0.0F));

            PartDefinition Butt2_1 = Butt2.addOrReplaceChild("Butt2_1", CubeListBuilder.create()
            .texOffs(25, 51)            
            .addBox(-2.5F, -4.2F, 0.0F, 5.0F, 5.0F, 4.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(0.0F, 0.8F, 2.9F, -0.1563815016444822F, 0.0F, 0.0F));

            PartDefinition LegR11 = LegR1.addOrReplaceChild("LegR11", CubeListBuilder.create()
            .texOffs(39, 13)            
            .addBox(-0.5F, -1.0F, -9.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(0.0F, 0.4F, -8.7F, 1.0122909661567112F, 0.0F, 0.0F));

            PartDefinition LegR12 = LegR11.addOrReplaceChild("LegR12", CubeListBuilder.create()
            .texOffs(39, 27)            
            .addBox(-0.5F, -0.5F, -4.5F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(0.0F, 0.0F, -8.6F, 0.6832964287873909F, 0.0F, 0.0F));

            PartDefinition LegR21 = LegR2.addOrReplaceChild("LegR21", CubeListBuilder.create()
            .texOffs(39, 13)            
            .addBox(-0.5F, -1.0F, -9.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(0.0F, 0.4F, -8.7F, 1.0471975511965976F, 0.0F, 0.0F));

            PartDefinition LegR22 = LegR21.addOrReplaceChild("LegR22", CubeListBuilder.create()
            .texOffs(39, 27)            
            .addBox(-0.5F, -0.5F, -4.5F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(0.0F, 0.0F, -8.7F, 0.6832964287873909F, 0.0F, 0.0F));

            PartDefinition LegR31 = LegR3.addOrReplaceChild("LegR31", CubeListBuilder.create()
            .texOffs(39, 13)            
            .addBox(-0.5F, -1.0F, -9.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(0.0F, 0.4F, -8.7F, 1.0471975511965976F, 0.0F, 0.0F));

            PartDefinition LegR32 = LegR31.addOrReplaceChild("LegR32", CubeListBuilder.create()
            .texOffs(39, 27)            
            .addBox(-0.5F, -0.5F, -4.5F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(0.0F, 0.0F, -8.7F, 0.6832964287873909F, 0.0F, 0.0F));

            PartDefinition LegR41 = LegR4.addOrReplaceChild("LegR41", CubeListBuilder.create()
            .texOffs(39, 13)            
            .addBox(-0.5F, -1.0F, -9.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(0.0F, 0.4F, -8.7F, 1.0471975511965976F, 0.0F, 0.0F));

            PartDefinition LegR42 = LegR41.addOrReplaceChild("LegR42", CubeListBuilder.create()
            .texOffs(39, 27)            
            .addBox(-0.5F, -0.5F, -4.5F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(0.0F, 0.0F, -8.7F, 0.6832964287873909F, 0.0F, 0.0F));

            PartDefinition LegL11 = LegL1.addOrReplaceChild("LegL11", CubeListBuilder.create()
            .texOffs(39, 13)
            .mirror()
            .addBox(-0.5F, -1.0F, -9.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(0.0F, 0.4F, -8.7F, 1.0122909661567112F, 0.0F, 0.0F));

            PartDefinition LegL12 = LegL11.addOrReplaceChild("LegL12", CubeListBuilder.create()
            .texOffs(39, 27)
            .mirror()
            .addBox(-0.5F, -0.5F, -4.5F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(0.0F, 0.0F, -8.7F, 0.6832964287873909F, 0.0F, 0.0F));

            PartDefinition LegL21 = LegL2.addOrReplaceChild("LegL21", CubeListBuilder.create()
            .texOffs(39, 13)
            .mirror()
            .addBox(-0.5F, -1.0F, -9.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(0.0F, 0.4F, -8.7F, 1.0122909661567112F, 0.0F, 0.0F));

            PartDefinition LegL22 = LegL21.addOrReplaceChild("LegL22", CubeListBuilder.create()
            .texOffs(39, 27)
            .mirror()
            .addBox(-0.5F, -0.5F, -4.5F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(0.0F, 0.0F, -8.7F, 0.6832964287873909F, 0.0F, 0.0F));

            PartDefinition LegL31 = LegL3.addOrReplaceChild("LegL31", CubeListBuilder.create()
            .texOffs(39, 13)
            .mirror()
            .addBox(-0.5F, -1.0F, -9.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(0.0F, 0.4F, -8.7F, 1.0122909661567112F, 0.0F, 0.0F));

            PartDefinition LegL32 = LegL31.addOrReplaceChild("LegL32", CubeListBuilder.create()
            .texOffs(39, 27)
            .mirror()
            .addBox(-0.5F, -0.5F, -4.5F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(0.0F, 0.0F, -8.7F, 0.6832964287873909F, 0.0F, 0.0F));

            PartDefinition LegL41 = LegL4.addOrReplaceChild("LegL41", CubeListBuilder.create()
            .texOffs(39, 13)
            .mirror()
            .addBox(-0.5F, -1.0F, -9.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(0.0F, 0.4F, -8.7F, 1.0122909661567112F, 0.0F, 0.0F));

            PartDefinition LegL42 = LegL41.addOrReplaceChild("LegL42", CubeListBuilder.create()
            .texOffs(39, 27)
            .mirror()
            .addBox(-0.5F, -0.5F, -4.5F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
            , PartPose.offsetAndRotation(0.0F, 0.0F, -8.7F, 0.6832964287873909F, 0.0F, 0.0F));


        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    	this.Head.yRot = 0.04537855888737689F + (netHeadYaw * ((float)Math.PI / 180F));
        this.Head.xRot = 0.04537855888737689F + (headPitch * ((float)Math.PI / 180F));
        float f = ((float)Math.PI / 4F);
        this.LegR4.zRot = 0; 
        this.LegL4.zRot = 0;
        this.LegR3.zRot = 0;
        this.LegL3.zRot = 0;
        this.LegR2.zRot = 0;
        this.LegL2.zRot = 0;
        this.LegR1.zRot = 0;
        this.LegL1.zRot = 0;
        
        this.LegR4.xRot = -0.3490658503988659F; 
        this.LegL4.xRot = -0.3490658503988659F;
        this.LegR3.xRot = -0.3490658503988659F;
        this.LegL3.xRot = -0.3490658503988659F;
        this.LegR2.xRot = -0.3490658503988659F;
        this.LegL2.xRot = -0.3490658503988659F;
        this.LegR1.xRot = -0.3490658503988659F;
        this.LegL1.xRot = -0.3490658503988659F;
        float f1 = -0.0F;
        float f2 = ((float)Math.PI / 8F);
        this.LegR4.yRot = 2.2321015697229036F;
        this.LegL4.yRot = -2.2321015697229036F;
        this.LegR3.yRot = 1.7765706615839945F;
        this.LegL3.yRot = -1.7765706615839945F;
        this.LegR2.yRot = 1.1843804410559964F;
        this.LegL2.yRot = -1.1843804410559964F;
        this.LegR1.yRot = 0.6832964287873909F;
        this.LegL1.yRot = -0.6832964287873909F;
        float f3 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.6F) * limbSwingAmount;
        float f4 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + (float)Math.PI) * 0.6F) * limbSwingAmount;
        float f5 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + ((float)Math.PI / 2F)) * 0.6F) * limbSwingAmount;
        float f6 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + ((float)Math.PI * 1.5F)) * 0.6F) * limbSwingAmount;
        float f7 = Math.abs(Mth.sin(limbSwing * 0.6662F + 0.0F) * 0.6F) * limbSwingAmount;
        float f8 = Math.abs(Mth.sin(limbSwing * 0.6662F + (float)Math.PI) * 0.6F) * limbSwingAmount;
        float f9 = Math.abs(Mth.sin(limbSwing * 0.6662F + ((float)Math.PI / 2F)) * 0.6F) * limbSwingAmount;
        float f10 = Math.abs(Mth.sin(limbSwing * 0.6662F + ((float)Math.PI * 1.5F)) * 0.6F) * limbSwingAmount;
        this.LegR4.xRot += f3/5;
        this.LegL4.xRot += -f3/5;
        this.LegR3.xRot += f4/5;
        this.LegL3.xRot += -f4/5;
        this.LegR2.xRot += f5/5;
        this.LegL2.xRot += -f5/5;
        this.LegR1.xRot += f6/5;
        this.LegL1.xRot += -f6/5;
        this.LegR4.zRot = f7;
        this.LegL4.zRot = -f7;
        this.LegR3.zRot = f8;
        this.LegL3.zRot = -f8;
        this.LegR2.zRot = f9;
        this.LegL2.zRot = -f9;
        this.LegR1.zRot = f10;
        this.LegL1.zRot = -f10;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.renderToBuffer(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
