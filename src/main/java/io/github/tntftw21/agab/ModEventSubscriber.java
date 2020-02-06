package io.github.tntftw21.agab;

import io.github.tntftw21.agab.init.ModBlocks;
import io.github.tntftw21.agab.init.ModItems;
import io.github.tntftw21.agab.item.ItemDart;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = AGAB.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {

	@SubscribeEvent
	public static void onRegisterItems(RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();
		((ItemDart) (ModItems.WOODEN_DART.get())).setItemReference(ModItems.WOODEN_DART);
		((ItemDart) (ModItems.STONE_DART.get())).setItemReference(ModItems.STONE_DART);
		ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
			final Item.Properties properties = new Item.Properties();//.group(ModItemGroups.MOD_ITEM_GROUP);
			final BlockItem blockItem = new BlockItem(block, properties);
			registry.register(setup(blockItem, block.getRegistryName()));
		});
	}
	
	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
		return setup(entry, new ResourceLocation(AGAB.MODID, name));
	}
	
	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
		entry.setRegistryName(registryName);
		return entry;
	}
	
}
