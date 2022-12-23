package com.minecraftserverzone.harrypotter.setup;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class ModMobEffect extends MobEffect{

	protected ModMobEffect(MobEffectCategory p_19451_, int p_19452_) {
		super(p_19451_, p_19452_);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int p_19468_) {
		if(this == Registrations.WINGARDIUM_LEVIOSA_EFFECT.get()) {
			entity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 40));
			entity.setDeltaMovement(0, 0.1f, 0);
		}
		super.applyEffectTick(entity, p_19468_);
	}
	
	
	@Override
	public boolean isDurationEffectTick(int p_19455_, int p_19456_) {
		if(this == Registrations.WINGARDIUM_LEVIOSA_EFFECT.get()) {
			int i = 10 >> p_19456_;
			
			if(i > 0) {
				return p_19455_ % i == 0;
			}
		}
		return super.isDurationEffectTick(p_19455_, p_19456_);
	}
}
