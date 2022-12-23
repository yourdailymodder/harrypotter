package com.minecraftserverzone.harrypotter.mobs.inferius;

import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class InferiusAttackGoal extends MeleeAttackGoal {
   private final Inferius Inferius;
   private int raiseArmTicks;

   public InferiusAttackGoal(Inferius p_26019_, double p_26020_, boolean p_26021_) {
      super(p_26019_, p_26020_, p_26021_);
      this.Inferius = p_26019_;
   }

   public void start() {
      super.start();
      this.raiseArmTicks = 0;
   }

   public void stop() {
      super.stop();
      this.Inferius.setAggressive(false);
   }

   public void tick() {
      super.tick();
      ++this.raiseArmTicks;
      if (this.raiseArmTicks >= 5 && this.getTicksUntilNextAttack() < this.getAttackInterval() / 2) {
         this.Inferius.setAggressive(true);
      } else {
         this.Inferius.setAggressive(false);
      }

   }
}