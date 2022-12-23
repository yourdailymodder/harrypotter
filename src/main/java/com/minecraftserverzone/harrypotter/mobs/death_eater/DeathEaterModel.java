package com.minecraftserverzone.harrypotter.mobs.death_eater;

import org.joml.Quaternionf;

import com.minecraftserverzone.harrypotter.HarryPotterMod;
import com.minecraftserverzone.harrypotter.setup.config.HarryPotterModConfig;
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
    
    private final ModelPart head;
	private final ModelPart hat;
	private final ModelPart nose;
	private final ModelPart body;
	private final ModelPart arms;
	private final ModelPart left_arm;
	private final ModelPart right_arm;
	private final ModelPart left_leg;
	private final ModelPart right_leg;
	private ModelPart root2;

    public DeathEaterModel(ModelPart rootx) {
        this.root = rootx;
        this.root2 = rootx;
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
        
        this.head = root2.getChild("head");
		this.hat = root2.getChild("hat");
		this.nose = root2.getChild("nose");
		this.body = root2.getChild("body");
		this.arms = root2.getChild("arms");
		this.left_arm = root2.getChild("left_arm");
		this.right_arm = root2.getChild("right_arm");
		this.left_leg = root2.getChild("left_leg");
		this.right_leg = root2.getChild("right_leg");
    }
    
    public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(32, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition nose = partdefinition.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 18.0F, 6.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition arms = partdefinition.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, 3.5F, 0.3F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 46).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 46).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 22).addBox(-6.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 12.0F, 0.0F));

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
		
//		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(0, 0, 0, 0, 0, 0), PartPose.ZERO);
//
//	            PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(0, 0, 0, 0, 0, 0), PartPose.ZERO);
//
//	            PartDefinition ArmL = partdefinition.addOrReplaceChild("ArmL", CubeListBuilder.create().texOffs(0, 0).addBox(0, 0, 0, 0, 0, 0), PartPose.ZERO);
//
//	            PartDefinition ArmR = partdefinition.addOrReplaceChild("ArmR", CubeListBuilder.create().texOffs(0, 0).addBox(0, 0, 0, 0, 0, 0), PartPose.ZERO);
//
//	            PartDefinition LegL = partdefinition.addOrReplaceChild("LegL", CubeListBuilder.create().texOffs(0, 0).addBox(0, 0, 0, 0, 0, 0), PartPose.ZERO);
//
//	            PartDefinition LegR = partdefinition.addOrReplaceChild("LegR", CubeListBuilder.create().texOffs(0, 0).addBox(0, 0, 0, 0, 0, 0), PartPose.ZERO);
//
//	            PartDefinition Head_1 = Head.addOrReplaceChild("Head_1", CubeListBuilder.create().texOffs(0, 0).addBox(0, 0, 0, 0, 0, 0), PartPose.ZERO);
//
//	            PartDefinition Hat = Head.addOrReplaceChild("Hat", CubeListBuilder.create().texOffs(0, 0).addBox(0, 0, 0, 0, 0, 0), PartPose.ZERO);
//
//	            PartDefinition Hat_1 = Hat.addOrReplaceChild("Hat_1", CubeListBuilder.create().texOffs(0, 0).addBox(0, 0, 0, 0, 0, 0), PartPose.ZERO);
//
//	            PartDefinition Hat_2 = Hat_1.addOrReplaceChild("Hat_2", CubeListBuilder.create().texOffs(0, 0).addBox(0, 0, 0, 0, 0, 0), PartPose.ZERO);
//
//	            PartDefinition Body_1 = Body.addOrReplaceChild("Body_1", CubeListBuilder.create().texOffs(0, 0).addBox(0, 0, 0, 0, 0, 0), PartPose.ZERO);
//
//	            PartDefinition ArmL_1 = ArmL.addOrReplaceChild("ArmL_1", CubeListBuilder.create().texOffs(0, 0).addBox(0, 0, 0, 0, 0, 0), PartPose.ZERO);
//
//	            PartDefinition ArmR_1 = ArmR.addOrReplaceChild("ArmR_1", CubeListBuilder.create().texOffs(0, 0).addBox(0, 0, 0, 0, 0, 0), PartPose.ZERO);
//
//	            PartDefinition LegL_1 = LegL.addOrReplaceChild("LegL_1", CubeListBuilder.create().texOffs(0, 0).addBox(0, 0, 0, 0, 0, 0), PartPose.ZERO);
//
//	            PartDefinition LegR_1 = LegR.addOrReplaceChild("LegR_1", CubeListBuilder.create().texOffs(0, 0).addBox(0, 0, 0, 0, 0, 0), PartPose.ZERO);
		
		return LayerDefinition.create(meshdefinition, 64, 64);
	}
//
//    public static LayerDefinition createBodyLayer2() {
//        MeshDefinition meshdefinition = new MeshDefinition();
//        PartDefinition partdefinition = meshdefinition.getRoot();
//            PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create()
//            .texOffs(0, 0)            
//            .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F, 0.0F, -0.5F))
//            , PartPose.offset(0.0F, 0.0F, 0.0F));
//
//            PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create()
//            .texOffs(0, 16)            
//            .addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
//            , PartPose.offset(0.0F, 0.0F, 0.0F));
//
//            PartDefinition ArmL = partdefinition.addOrReplaceChild("ArmL", CubeListBuilder.create()
//            .texOffs(48, 16)
//            .mirror()
//            .addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
//            , PartPose.offset(5.0F, 2.0F, 0.1F));
//
//            PartDefinition ArmR = partdefinition.addOrReplaceChild("ArmR", CubeListBuilder.create()
//            .texOffs(48, 16)            
//            .addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
//            , PartPose.offset(-5.0F, 2.0F, 0.1F));
//
//            PartDefinition LegL = partdefinition.addOrReplaceChild("LegL", CubeListBuilder.create()
//            .texOffs(0, 32)
//            .mirror()
//            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
//            , PartPose.offset(2.0F, 12.0F, 0.0F));
//
//            PartDefinition LegR = partdefinition.addOrReplaceChild("LegR", CubeListBuilder.create()
//            .texOffs(0, 32)            
//            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F, 0.0F, 0.0F))
//            , PartPose.offset(-2.0F, 12.0F, 0.0F));
//
//            PartDefinition Head_1 = Head.addOrReplaceChild("Head_1", CubeListBuilder.create()
//            .texOffs(32, 0)            
//            .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.2F, 0.2F, 0.2F))
//            , PartPose.offset(0.0F, 0.0F, 0.0F));
//
//            PartDefinition Hat = Head.addOrReplaceChild("Hat", CubeListBuilder.create()
//            .texOffs(17, 48)            
//            .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.2F, 0.2F, 0.2F))
//            , PartPose.offset(0.0F, 0.0F, 0.0F));
//
//            PartDefinition Hat_1 = Hat.addOrReplaceChild("Hat_1", CubeListBuilder.create()
//            .texOffs(17, 39)            
//            .addBox(-3.0F, -2.2F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.2F, 0.2F, 0.2F))
//            , PartPose.offset(0.0F, -9.0F, 0.4F));
//
//            PartDefinition Hat_2 = Hat_1.addOrReplaceChild("Hat_2", CubeListBuilder.create()
//            .texOffs(17, 33)            
//            .addBox(-1.5F, -2.1F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.2F, 0.2F, 0.2F))
//            , PartPose.offset(0.0F, -3.3F, 1.0F));
//
//            PartDefinition Body_1 = Body.addOrReplaceChild("Body_1", CubeListBuilder.create()
//            .texOffs(24, 16)            
//            .addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.1F, 0.2F, 0.2F))
//            , PartPose.offset(0.0F, 0.0F, 0.0F));
//
//            PartDefinition ArmL_1 = ArmL.addOrReplaceChild("ArmL_1", CubeListBuilder.create()
//            .texOffs(48, 32)
//            .mirror()
//            .addBox(-1.0F, -2.1F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2F, 0.2F, 0.2F))
//            , PartPose.offset(0.0F, 0.0F, 0.0F));
//
//            PartDefinition ArmR_1 = ArmR.addOrReplaceChild("ArmR_1", CubeListBuilder.create()
//            .texOffs(48, 32)            
//            .addBox(-3.0F, -2.1F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2F, 0.2F, 0.2F))
//            , PartPose.offset(0.0F, 0.0F, 0.0F));
//
//            PartDefinition LegL_1 = LegL.addOrReplaceChild("LegL_1", CubeListBuilder.create()
//            .texOffs(0, 48)
//            .mirror()
//            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2F, 0.2F, 0.2F))
//            , PartPose.offset(0.0F, 0.0F, 0.0F));
//
//            PartDefinition LegR_1 = LegR.addOrReplaceChild("LegR_1", CubeListBuilder.create()
//            .texOffs(0, 48)            
//            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2F, 0.2F, 0.2F))
//            , PartPose.offset(0.0F, 0.0F, 0.0F));
//
//            
//            
//            PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(0, 0, 0, 0, 0, 0), PartPose.ZERO);
//
//            		PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 0).addBox(0, 0, 0, 0, 0, 0), PartPose.ZERO);
//
//            		PartDefinition nose = partdefinition.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(0, 0).addBox(0, 0, 0, 0, 0, 0), PartPose.ZERO);
//
//            		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(0, 0, 0, 0, 0, 0), PartPose.ZERO);
//
//            		PartDefinition arms = partdefinition.addOrReplaceChild("arms", CubeListBuilder.create().texOffs(0, 0).addBox(0, 0, 0, 0, 0, 0), PartPose.ZERO);
//
//            		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 0).addBox(0, 0, 0, 0, 0, 0), PartPose.ZERO);
//
//            		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 0).addBox(0, 0, 0, 0, 0, 0), PartPose.ZERO);
//
//            		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 0).addBox(0, 0, 0, 0, 0, 0), PartPose.ZERO);
//
//            		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 0).addBox(0, 0, 0, 0, 0, 0), PartPose.ZERO);
//
//        return LayerDefinition.create(meshdefinition, 64, 64);
//    }

    @Override
    public ModelPart root() {
        return this.root2;
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
	      
	      //
	      this.head.xRot = headPitch * ((float)Math.PI / 180F);
	      this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
	      this.nose.xRot = this.head.xRot;
	      this.nose.yRot = this.head.yRot;
	      this.nose.zRot = this.head.zRot;
	      
	      

	      if (f < 1.0F) {
	         f = 1.0F;
	      }
	      
	      this.right_leg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
	      this.left_leg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / f;
    }
    
    public void prepareMobModel(T p_102957_, float p_102958_, float p_102959_, float p_102960_) {
        int i = ((DeathEater) p_102957_).getAttackAnimationTick();
        if (i > 0) {
           this.ArmR.xRot = -2.0F + 1.5F * Mth.triangleWave((float)i - p_102960_, 10.0F);
           this.ArmL.xRot = (-0.2F - 1.5F * Mth.triangleWave(p_102958_, 13.0F)) * p_102959_;
           
           this.right_arm.xRot = -2.0F + 1.5F * Mth.triangleWave((float)i - p_102960_, 10.0F);
           this.left_arm.xRot = (-0.2F - 1.5F * Mth.triangleWave(p_102958_, 13.0F)) * p_102959_;
        } else {
           this.ArmR.xRot = (-0.2F + 1.5F * Mth.triangleWave(p_102958_, 13.0F)) * p_102959_;
           this.ArmL.xRot = (-0.2F - 1.5F * Mth.triangleWave(p_102958_, 13.0F)) * p_102959_;

           this.left_arm.xRot = (-0.2F + 1.5F * Mth.triangleWave(p_102958_, 13.0F)) * p_102959_;
           this.right_arm.xRot = (-0.2F - 1.5F * Mth.triangleWave(p_102958_, 13.0F)) * p_102959_;
        }
     }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if(HarryPotterModConfig.DEATH_EATER_MODEL.get()==0) {
        	this.head.render(poseStack, buffer, packedLight, packedOverlay);
    		this.hat.render(poseStack, buffer, packedLight, packedOverlay);
    		this.nose.render(poseStack, buffer, packedLight, packedOverlay);
    		this.body.render(poseStack, buffer, packedLight, packedOverlay);
    		this.arms.render(poseStack, buffer, packedLight, packedOverlay);
    		this.left_arm.render(poseStack, buffer, packedLight, packedOverlay);
    		this.right_arm.render(poseStack, buffer, packedLight, packedOverlay);
    		this.left_leg.render(poseStack, buffer, packedLight, packedOverlay);
    		this.right_leg.render(poseStack, buffer, packedLight, packedOverlay);
        }else {
        	this.Head.render(poseStack, buffer, packedLight, packedOverlay);
            this.Body.render(poseStack, buffer, packedLight, packedOverlay);
            this.ArmL.render(poseStack, buffer, packedLight, packedOverlay);
            this.ArmR.render(poseStack, buffer, packedLight, packedOverlay);
            this.LegL.render(poseStack, buffer, packedLight, packedOverlay);
            this.LegR.render(poseStack, buffer, packedLight, packedOverlay);
        }
    	
//    	super.renderToBuffer(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

	@Override
    public void translateToHand(HumanoidArm p_102854_, PoseStack p_102855_) {
        translateAndRotate(p_102855_, this.getArm(p_102854_));
     }
	
	public void translateAndRotate(PoseStack p_104300_, ModelPart part) {
	      p_104300_.translate(part.x / 10.0F, part.y / 10.0F, part.z / 16.0F);
	      if (part.xRot != 0.0F || part.yRot != 0.0F || part.zRot != 0.0F) {
	         p_104300_.mulPose((new Quaternionf()).rotationZYX(part.zRot, part.yRot, part.xRot));
	      }

	      if (part.xScale != 1.0F || part.yScale != 1.0F || part.zScale != 1.0F) {
	         p_104300_.scale(part.xScale, part.yScale, part.zScale);
	      }

	   }
	
	protected ModelPart getArm(HumanoidArm p_102852_) {
		return this.right_arm;
	   }

	@Override
	public ModelPart getHead() {
		return head;
	}
}
