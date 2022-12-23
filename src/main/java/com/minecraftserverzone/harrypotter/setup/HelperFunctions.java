package com.minecraftserverzone.harrypotter.setup;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.Gui;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraftforge.common.ForgeMod;

public class HelperFunctions {

	/** get texture for blit **/
	public static void bind(ResourceLocation res){
        RenderSystem.setShaderTexture(0, res);
    }
	
	/** render name plate if its in range of 400 square Distance**/
	public static boolean isNameplateInRenderDistance(Entity entity, double squareDistance) {
        if (entity instanceof LivingEntity) {
            final AttributeInstance attribute = ((LivingEntity) entity).getAttribute(ForgeMod.NAMETAG_DISTANCE.get());
            if (attribute != null) {
                return !(squareDistance > (attribute.getValue() * attribute.getValue()));
            }
        }
        return !(squareDistance > 400.0f);
    }
	
	/** blit function for drawing **/
	public static void blit(PoseStack matrixStack, int x, int y, int uOffset, int vOffset, int uWidth, int vHeight) {
	      Gui.blit(matrixStack, x, y, 0, (float)uOffset, (float)vOffset, uWidth, vHeight, 256, 256);
	}
}
