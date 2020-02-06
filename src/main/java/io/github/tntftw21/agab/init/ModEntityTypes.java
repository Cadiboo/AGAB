package io.github.tntftw21.agab.init;

import io.github.tntftw21.agab.AGAB;
import io.github.tntftw21.agab.entity.EntityDart;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, AGAB.MODID);
	
	public static final String DART_NAME = "dart";
	public static final RegistryObject<EntityType<EntityDart>> DART = ENTITY_TYPES.register("dart",
			() -> EntityType.Builder.<EntityDart>create(EntityDart::new, EntityClassification.MISC).size(0.5F, 0.5F).build(new ResourceLocation(AGAB.MODID, DART_NAME).toString()));

}
