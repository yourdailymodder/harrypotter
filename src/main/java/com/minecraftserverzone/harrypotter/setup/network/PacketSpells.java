package com.minecraftserverzone.harrypotter.setup.network;

import java.util.function.Supplier;

import com.minecraftserverzone.harrypotter.HarryPotterMod;
import com.minecraftserverzone.harrypotter.mobs.dementor.Dementor;
import com.minecraftserverzone.harrypotter.setup.Registrations;
import com.minecraftserverzone.harrypotter.setup.capabilities.PlayerStatsProvider;
import com.minecraftserverzone.harrypotter.spells.accio.Accio;
import com.minecraftserverzone.harrypotter.spells.alarte_ascandare.AlarteAscandare;
import com.minecraftserverzone.harrypotter.spells.aqua_eructo.AquaEructoSpawner;
import com.minecraftserverzone.harrypotter.spells.avada_kedavra.AvadaKedavra;
import com.minecraftserverzone.harrypotter.spells.avis.Avis;
import com.minecraftserverzone.harrypotter.spells.confringo.Confringo;
import com.minecraftserverzone.harrypotter.spells.depulso.Depulso;
import com.minecraftserverzone.harrypotter.spells.episkey.Episkey;
import com.minecraftserverzone.harrypotter.spells.evanesco.Evanesco;
import com.minecraftserverzone.harrypotter.spells.expecto_patronum.ExpectoPatronum;
import com.minecraftserverzone.harrypotter.spells.expelliarmus.Expelliarmus;
import com.minecraftserverzone.harrypotter.spells.finite.Finite;
import com.minecraftserverzone.harrypotter.spells.fire_storm.FireStormSpawner;
import com.minecraftserverzone.harrypotter.spells.fumos.Fumos;
import com.minecraftserverzone.harrypotter.spells.glacius.Glacius;
import com.minecraftserverzone.harrypotter.spells.incendio.Incendio;
import com.minecraftserverzone.harrypotter.spells.melofors.Melofors;
import com.minecraftserverzone.harrypotter.spells.mobilicorpus.Mobilicorpus;
import com.minecraftserverzone.harrypotter.spells.reparo.Reparo;
import com.minecraftserverzone.harrypotter.spells.sectumsempra.Sectumsempra;
import com.minecraftserverzone.harrypotter.spells.wingardium_leviosa.WingardiumLeviosa;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;


public class PacketSpells {

	private int StatName;

    public PacketSpells(FriendlyByteBuf buf) {
    	this.StatName = buf.readInt();
    }

    public PacketSpells(int StatName) {
    	this.StatName = StatName;
    }

    public void toBytes(FriendlyByteBuf buf) {
    	buf.writeInt(StatName);
    }
    
    @SuppressWarnings({ "unused"})
	public void handle(Supplier<NetworkEvent.Context> ctx) {
	    ctx.get().enqueueWork(() -> {
	    	// the client that sent this packet
	        ServerPlayer sender = ctx.get().getSender(); 
			Vec3 look = sender.getLookAngle();
	        // do stuff
	        CompoundTag tag = sender.getPersistentData();
	        
	        if (!sender.level.isClientSide) {
	        	sender.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
	        		if(h.getSpellsLevel() != null && StatName < 25) {
		        		if(h.getSpellsLevel()[StatName] > 0 || sender.isCreative()) {

					        if(StatName == 0) {
					 			
						 			Accio accio = new Accio(sender.level, sender, look.x * 2, look.y * 2, look.z * 2);
						 			accio.xPower = look.x * 1.25f;
						 			accio.yPower = look.y * 1.25f;
						 			accio.zPower = look.z * 1.25f;
						 			accio.setPos((sender.getX()) + look.x * 1.5D, sender.getY() + 1 + look.y * 1.5D, sender.getZ() + look.z * 1.5D);
						            sender.level.addFreshEntity(accio);
				
				 			}else if(StatName == 1) { //aqua erecto
				
					 				h.setUsingSkill(1);
				
					 				AquaEructoSpawner aquaEructoSpawner = new AquaEructoSpawner(sender.level, sender);
					 				aquaEructoSpawner.shootFromRotation(sender, sender.getXRot(), sender.getYRot(), 0.0F, 0F, 0F);
					 				aquaEructoSpawner.setOwner(sender);
					 				sender.level.addFreshEntity(aquaEructoSpawner);
				
				 			}else if(StatName == 2) { //ascendio
				
					 			
				 			}else if(StatName == 3) { //avada kedavra
						 			AvadaKedavra avadaKedavra = new AvadaKedavra(sender.level, sender, look.x * 2, look.y * 2, look.z * 2);
						 			avadaKedavra.xPower = look.x * 1.0f;
						 			avadaKedavra.yPower = look.y * 1.0f;
						 			avadaKedavra.zPower = look.z * 1.0f;
						 			avadaKedavra.setPos((sender.getX()) + look.x * 1.5D, sender.getY() + 1 + look.y * 1.5D, sender.getZ() + look.z * 1.5D);
						            sender.level.addFreshEntity(avadaKedavra);
				 				
				 			}else if(StatName == 4) { //avis
					 				Avis avis = new Avis(sender.level, sender, look.x * 2, look.y * 2, look.z * 2);
					 				avis.setPos((sender.getX()) + look.x * 1.5D, sender.getY() + 1 + look.y * 1.5D, sender.getZ() + look.z * 1.5D);
						            sender.level.addFreshEntity(avis);
				 			}else if(StatName == 5) { //Confringo
				
					 				Confringo confringo = new Confringo(sender.level, sender, look.x * 2, look.y * 2, look.z * 2);
					 				confringo.setPos((sender.getX()) + look.x * 1.5D, sender.getY() + 1 + look.y * 1.5D, sender.getZ() + look.z * 1.5D);
						            sender.level.addFreshEntity(confringo);
				
					 			
				 			}else if(StatName == 6) { //Depulso	
				
					 				Depulso depulso = new Depulso(sender.level, sender, look.x * 2, look.y * 2, look.z * 2);
					 				depulso.xPower = look.x * 0.75f;
					 				depulso.yPower = look.y * 0.75f;
					 				depulso.zPower = look.z * 0.75f;
					 				depulso.setPos((sender.getX()) + look.x * 1.5D, sender.getY() + 1 + look.y * 1.5D, sender.getZ() + look.z * 1.5D);
						            sender.level.addFreshEntity(depulso);
				
				 			}else if(StatName == 7) { //expectopatronum
					 				ExpectoPatronum expectopatronum = new ExpectoPatronum(sender.level, sender, look.x * 2, look.y * 2, look.z * 2);
					 				expectopatronum.setPos((sender.getX()) + look.x * 1.5D, sender.getY() + 1 + look.y * 1.5D, sender.getZ() + look.z * 1.5D);
						            sender.level.addFreshEntity(expectopatronum);
					 			
				 			}else if(StatName == 8) { //expeliarmus
				 				Expelliarmus expeliarmus = new Expelliarmus(sender.level, sender, look.x * 2, look.y * 2, look.z * 2);
				 				expeliarmus.setPos((sender.getX()) + look.x * 1.5D, sender.getY() + 1 + look.y * 1.5D, sender.getZ() + look.z * 1.5D);
					            sender.level.addFreshEntity(expeliarmus);
					 			
				 			}else if(StatName == 9) { //fumos
				
					 				Fumos fumos = new Fumos(sender.level, sender, look.x * 2, look.y * 2, look.z * 2);
					 				fumos.setPos((sender.getX()) + look.x * 1.5D, sender.getY() + 1 + look.y * 1.5D, sender.getZ() + look.z * 1.5D);
						            sender.level.addFreshEntity(fumos);
				
				 			}else if(StatName == 10) { //glacius
				
					 				Glacius glacius = new Glacius(sender.level, sender, look.x * 2, look.y * 2, look.z * 2);
					 				glacius.setPos((sender.getX()) + look.x * 1.5D, sender.getY() + 1 + look.y * 1.5D, sender.getZ() + look.z * 1.5D);
						            sender.level.addFreshEntity(glacius);
					
				 			}else if(StatName == 11) { //herbivicus
					 			
				 			}else if(StatName == 12) { //incendio
				
					 				Incendio incendio = new Incendio(sender.level, sender, look.x * 2, look.y * 2, look.z * 2);
					 				incendio.setPos((sender.getX()) + look.x * 1.5D, sender.getY() + 1 + look.y * 1.5D, sender.getZ() + look.z * 1.5D);
						            sender.level.addFreshEntity(incendio);
				
				 			}else if(StatName == 13) { //lumos
				
					 				if(sender.level.getBlockState(sender.blockPosition()).getBlock().equals(Blocks.AIR)) {
				//	 					sender.level.setBlock(sender.blockPosition().above(1), Registrations.GLOWING_AIR.get().defaultBlockState(), 2);
				//	 					sender.level.setBlock(sender.blockPosition().above(1), Registrations.GLOWING_AIR.get().defaultBlockState(), 3);
					 					sender.level.setBlock(sender.blockPosition(), Registrations.GLOWING_AIR.get().defaultBlockState(), 2);
					 					sender.level.gameEvent(sender, GameEvent.BLOCK_PLACE, sender.blockPosition());
				
				 				}
				 				
				 			}else if(StatName == 14) { //melofors
				
				 	 				Melofors melofors = new Melofors(sender.level, sender, look.x * 2, look.y * 2, look.z * 2);
				 	 				melofors.setPos((sender.getX()) + look.x * 1.5D, sender.getY() + 1 + look.y * 1.5D, sender.getZ() + look.z * 1.5D);
				 		            sender.level.addFreshEntity(melofors);
				
				 			}else if(StatName == 15) { //mobilicorpus
				
				 					Mobilicorpus mobilicorpus = new Mobilicorpus(sender.level, sender, look.x * 2, look.y * 2, look.z * 2);
				 	 				mobilicorpus.setPos((sender.getX()) + look.x * 1.5D, sender.getY() + 1 + look.y * 1.5D, sender.getZ() + look.z * 1.5D);
				 		            sender.level.addFreshEntity(mobilicorpus);
				
				 			}else if(StatName == 16) { //reparo
				
				 					Reparo reparo = new Reparo(sender.level, sender, look.x * 2, look.y * 2, look.z * 2);
				 					reparo.setPos((sender.getX()) + look.x * 1.5D, sender.getY() + 1 + look.y * 1.5D, sender.getZ() + look.z * 1.5D);
				 		            sender.level.addFreshEntity(reparo);
				 				
				 			}else if(StatName == 17) { //melofors
				
				 	 				Sectumsempra sectumsempra = new Sectumsempra(sender.level, sender, look.x * 2, look.y * 2, look.z * 2);
				 	 				sectumsempra.setPos((sender.getX()) + look.x * 1.5D, sender.getY() + 1 + look.y * 1.5D, sender.getZ() + look.z * 1.5D);
				 		            sender.level.addFreshEntity(sectumsempra);
				
				 			}else if(StatName == 18) { //vulnera_sanentur (on caster)
				 					if(h.getUsingSkill() == 0) {
				// 						System.out.println("packet: use on caster");
				 						sender.heal(10);
				 					}else {
				// 						System.out.println("packet: cant use on caster");
				 						h.setUsingSkill(0);
				 					}
			
				 			}else if(StatName == 19) { //wingardium_leviosa
				
				 	 				WingardiumLeviosa wingardiumleviosa = new WingardiumLeviosa(sender.level, sender, look.x * 2, look.y * 2, look.z * 2);
				 	 				wingardiumleviosa.setPos((sender.getX()) + look.x * 1.5D, sender.getY() + 1 + look.y * 1.5D, sender.getZ() + look.z * 1.5D);
				 		            sender.level.addFreshEntity(wingardiumleviosa);
				 				
				 			}else if(StatName == 19) { //wingardium_leviosa
				
				 	 				WingardiumLeviosa wingardiumleviosa = new WingardiumLeviosa(sender.level, sender, look.x * 2, look.y * 2, look.z * 2);
				 	 				wingardiumleviosa.setPos((sender.getX()) + look.x * 1.5D, sender.getY() + 1 + look.y * 1.5D, sender.getZ() + look.z * 1.5D);
				 		            sender.level.addFreshEntity(wingardiumleviosa);
				
				 			}else if(StatName == 20) { //episkey
				
					 				Episkey episkey = new Episkey(sender.level, sender, look.x * 2, look.y * 2, look.z * 2);
					 				episkey.setPos((sender.getX()) + look.x * 1.5D, sender.getY() + 1 + look.y * 1.5D, sender.getZ() + look.z * 1.5D);
						            sender.level.addFreshEntity(episkey);
				
				 			}else if(StatName == 21) { //alarte ascandare
				
					 				AlarteAscandare alarteascandare = new AlarteAscandare(sender.level, sender, look.x * 2, look.y * 2, look.z * 2);
					 				alarteascandare.setPos((sender.getX()) + look.x * 1.5D, sender.getY() + 1 + look.y * 1.5D, sender.getZ() + look.z * 1.5D);
						            sender.level.addFreshEntity(alarteascandare);
					
				 			}else if(StatName == 22) { //finite
				
					 				Finite finite = new Finite(sender.level, sender, look.x * 2, look.y * 2, look.z * 2);
					 				finite.setPos((sender.getX()) + look.x * 1.5D, sender.getY() + 1 + look.y * 1.5D, sender.getZ() + look.z * 1.5D);
						            sender.level.addFreshEntity(finite);
				
				 			}else if(StatName == 23) { //evanesco
				
									Evanesco evanesco = new Evanesco(sender.level, sender, look.x * 2, look.y * 2, look.z * 2);
					 				evanesco.setPos((sender.getX()) + look.x * 1.5D, sender.getY() + 1 + look.y * 1.5D, sender.getZ() + look.z * 1.5D);
						            sender.level.addFreshEntity(evanesco);
				
				 			}else if(StatName == 24) { //fire_storm
					 				h.setUsingSkill(1);
									
									FireStormSpawner aquaEructoSpawner = new FireStormSpawner(sender.level, sender);
					 				aquaEructoSpawner.shootFromRotation(sender, sender.getXRot(), sender.getYRot(), 0.0F, 0F, 0F);
					 				aquaEructoSpawner.setOwner(sender);
					 				sender.level.addFreshEntity(aquaEructoSpawner);
				
					        /** TODO NEW SPELLS */
								
				 			}
					        
					        if(StatName < 25 && StatName != 1 && !sender.isCreative()) { // && StatName != 24
				 					h.setSpellCD((int)HarryPotterMod.spellCooldownOrDamage(StatName, h.getSpellsLevel()[StatName], false) * 2, StatName);
					        }
    			
			    		}
					}
	        	});
	        	
	        	
	        	if(StatName == 91) { //discard entities that work only when the player is using an item
	        		
 	 				sender.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
 	 					h.setUsingSkill(0);
 	 				});

	 			}else if(StatName == 92) { //spawn dementor
	
	 					Dementor cs = Registrations.DEMENTOR.get().create(sender.level);
	 					cs.moveTo(sender.getRandomX(30)-15, sender.getY() + 5D, sender.getRandomZ(30)-15, 0.0F, 0.0F);
	 					sender.level.addFreshEntity(cs);
	
	 			}else if(StatName > 99 && StatName < 109) { //player capabilities
	
	 	 				sender.getCapability(PlayerStatsProvider.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
	 	 					h.setSelectedHotbar(StatName-100);
	 	 					h.setUsingSkill(0);
	 	 				});
	 			}
	        }
	    });
	    ctx.get().setPacketHandled(true);
	    
	}

}