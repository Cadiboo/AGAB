package io.github.tntftw21.agab.entity;

import io.github.tntftw21.agab.AGAB;
import io.github.tntftw21.agab.init.ModEntityTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityDart extends AbstractArrowEntity {

	private final Item referenceItem;

	@SuppressWarnings("unchecked")
	public EntityDart(EntityType<?> type, World world) {
		super((EntityType<? extends AbstractArrowEntity>) type, world);
		this.referenceItem = null;
	}
	
	public EntityDart(LivingEntity shooter, World world, Item referenceItemIn) {
		super(ModEntityTypes.DART.get(), shooter, world);
		this.referenceItem = referenceItemIn;
	}

	@Override
	public ItemStack getArrowStack() {
		AGAB.LOGGER.debug("Getting Arrow Stack");
		return new ItemStack(this.referenceItem);
	}

}
