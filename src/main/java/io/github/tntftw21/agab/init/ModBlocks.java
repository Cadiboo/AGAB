package io.github.tntftw21.agab.init;

import io.github.tntftw21.agab.AGAB;
import net.minecraft.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
	
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, AGAB.MODID);

	//public static final RegistryObject<Block> EXAMPLE_ORE = BLOCKS.register("example_ore", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)));
	
}
