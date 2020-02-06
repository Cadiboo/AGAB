package io.github.tntftw21.agab.init;

import com.google.common.base.Supplier;

import io.github.tntftw21.agab.AGAB;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroups {
	
	//public static final ItemGroup MOD_ITEM_GROUP = new ModItemGroup(AGAB.MODID, () -> new ItemStack(ModItems.EXAMPLE_ITEM.get()));
	public static final ItemGroup AGAB_WEAPONS = new ModItemGroup(AGAB.MODID + "_weapons", () -> new ItemStack(ModItems.WOODEN_ATLATL.get()));
	
	private static class ModItemGroup extends ItemGroup {
		private final Supplier<ItemStack> iconSupplier;
		public ModItemGroup(String name, Supplier<ItemStack> iconSupplier) {
			super(name);
			this.iconSupplier = iconSupplier;
		}
		
		@Override
		public ItemStack createIcon() {
			return iconSupplier.get();
		}
	}

}
