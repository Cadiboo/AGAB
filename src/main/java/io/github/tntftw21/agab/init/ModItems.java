package io.github.tntftw21.agab.init;

import io.github.tntftw21.agab.AGAB;
import io.github.tntftw21.agab.item.ItemAtlatl;
import io.github.tntftw21.agab.item.ItemDart;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
	
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, AGAB.MODID);

	//public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
	public static final RegistryObject<Item> WOODEN_ATLATL = ITEMS.register("wooden_atlatl", () -> new ItemAtlatl(new Item.Properties().group(ModItemGroups.AGAB_WEAPONS).maxDamage(288), 1.33F));
	public static final RegistryObject<Item> ENHANCED_ATLATL = ITEMS.register("enhanced_atlatl", () -> new ItemAtlatl(new Item.Properties().group(ModItemGroups.AGAB_WEAPONS).maxDamage(288), 1.75F));
	
	public static final RegistryObject<Item> WOODEN_DART = ITEMS.register("wooden_dart", () -> new ItemDart(new Item.Properties().group(ModItemGroups.AGAB_WEAPONS), 1.5F));
	public static final RegistryObject<Item> STONE_DART = ITEMS.register("stone_dart", () -> new ItemDart(new Item.Properties().group(ModItemGroups.AGAB_WEAPONS), 1.75F));
	
}
