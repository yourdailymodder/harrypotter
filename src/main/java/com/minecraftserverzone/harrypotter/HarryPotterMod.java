package com.minecraftserverzone.harrypotter;

import org.slf4j.Logger;

import com.minecraftserverzone.harrypotter.setup.Registrations;
import com.minecraftserverzone.harrypotter.setup.config.ConfigHolder;
import com.minecraftserverzone.harrypotter.setup.events.PlayerEventHandler;
import com.mojang.logging.LogUtils;

import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(HarryPotterMod.MODID)
public class HarryPotterMod
{
    public static final String MODID = "harrypotter";
    public static final Logger LOGGER = LogUtils.getLogger();

    /**
     * number = spell id
     * level = owner spell level
     * get = get Damage if true, get cooldown if false
     * */
    public static float spellCooldownOrDamage(int number, int level, boolean get) {
    	level -= 1;
    	if(number == 0) {
//			info = "Accio";
        	return Math.max(10f - (level / 2f), 1);
		}else if(number == 1) {
//			info = "Aqua Eructo";
			return Math.min(1f + ((level) / 2f), 100);
		}else if(number == 2) {
//			info = "Ascendo";
			return Math.max(10f - (level / 2f), 1);
		}else if(number == 3) {
//			info = "Avada Kedavra";
			return Math.max(60f - (level / 2f), 1);
		}else if(number == 4) {
//			info = "Avis";
			if(get) {
				return Math.max(7f + ((level) / 2f), 1);
			}else {
				return Math.max(10f - (level / 2f), 1);
			}
		}else if(number == 5) {
//			info = "Confringo";
			if(get) {
				return Math.max(4f + ((level) / 2f), 1);
			}else {
				return Math.max(15f - (level / 2f), 1);
			}
		}else if(number == 6) {
//			info = "Depulso";
			return Math.max(10f - (level / 2f), 1);
		}else if(number == 7) {
//			info = "Expecto Patronum";
			return Math.max(15f - (level / 2f), 1);
		}else if(number == 8) {
//			info = "Expelliarmus";
			return Math.max(30f - (level / 2f), 1);
		}else if(number == 9) {
//			info = "Fumos";
			return Math.max(30f - (level / 2f), 1);
		}else if(number == 10) {
//			info = "Glacius";
			if(get) {
				return Math.max(4f + ((level) / 2f), 1);
			}else {
				return Math.max(10f - (level / 2f), 1);
			}
		}else if(number == 11) {
//			info = "Herbivicus";
			return Math.max(10f - (level / 2f), 1);
		}else if(number == 12) {
//			info = "Incendio";
			if(get) {
				return Math.max(5f + ((level) / 2f), 1);
			}else {
				return Math.max(10f - (level / 2f), 1);
			}
		}else if(number == 13) {
//			info = "Lumos";
			return Math.max(5f - (level / 2f), 1);
		}else if(number == 14) {
//			info = "Melofors";
			return Math.max(30f - (level / 2f), 1);
		}else if(number == 15) {
//			info = "Mobilicorpus";
			return Math.max(30f - (level / 2f), 1);
		}else if(number == 16) {
//			info = "Reparo";
			return Math.max(30f - (level / 2f), 1);
		}else if(number == 17) {
//			info = "Sectumsempra";
			if(get) {
				return Math.max(15f + ((level) / 2f), 1);
			}else {
				return Math.max(30f - (level / 2f), 1);
			}
		}else if(number == 18) {
//			info = "Vulnera Sanentur";
			return Math.max(30f - (level / 2f), 1);
		}else if(number == 19) {
//			info = "Wingardium Leviosa";
			return Math.max(30f - (level / 2f), 1);
		}else if(number == 20) {
//			info = "Episkey";
			return Math.max(15f - (level / 2f), 1);
		}else if(number == 21) {
//			info = "Alarte Ascendare";
			return Math.max(10f - (level / 2f), 1);
		}else if(number == 22) {
//			info = "Finite";
			return Math.max(15f - (level / 2f), 1);
		}else if(number == 23) {
//			info = "Evanesco";
			return Math.max(30f - (level / 2f), 1);
		}else if(number == 24) {
//			info = "Fire Strom";
			if(get) {
				return Math.max(4f + ((level) / 2f), 1);
			}else {
				//max Channel length
				return Math.max(30f + (level / 2f), 1);
			}
		}
		return number;
    }

    public HarryPotterMod() {
        Registrations.init();
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigHolder.CLIENT_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigHolder.COMMON_SPEC);
        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> "", (a, b) -> true));
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
//        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, Structures::addDimensionalSpacing);
//        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, Structures::setupStructureSpawns);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
    	MinecraftForge.EVENT_BUS.addGenericListener(Entity.class, PlayerEventHandler::onAttachCapabilitiesEvent);
    	event.enqueueWork(()-> registerThings());
    	
    }
    
    private void registerThings() {
//    	Structures.setupStructures();
//    	Structures.registerConfiguredStructures();
    }
	
    
    
    
    
    
}
