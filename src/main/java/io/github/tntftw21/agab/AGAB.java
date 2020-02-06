package io.github.tntftw21.agab;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.tntftw21.agab.init.ModBlocks;
import io.github.tntftw21.agab.init.ModEntityTypes;
import io.github.tntftw21.agab.init.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(AGAB.MODID)
public class AGAB {
	
	public static final String MODID = "agab";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	
	public AGAB() {
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ModBlocks.BLOCKS.register(modEventBus);
		ModItems.ITEMS.register(modEventBus);
		ModEntityTypes.ENTITY_TYPES.register(modEventBus);
	}
}
