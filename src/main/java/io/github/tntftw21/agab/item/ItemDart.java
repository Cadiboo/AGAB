package io.github.tntftw21.agab.item;

import io.github.tntftw21.agab.entity.EntityDart;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;

/**
 * A version of ArrowItem which allows for custom damage values as well as
 * having the resultant entity drop its respective dart (i.e. EntityStoneDart can drop a stone
 * instead of a wooden one)
 * 
 * @author TNTftw21
 */
public class ItemDart extends ArrowItem {
	
	/**
	 * The amount of damage this Dart does by default.
	 */
	public final float damage;
	
	private RegistryObject<Item> ref;

	/**
	 * 
	 * @param properties Default Item.Properties
	 * @param damageIn Amount of damage this dart deals as base (will be multiplied by projectile velocity later!)
	 */
	public ItemDart(Properties properties, float damageIn) {
		super(properties);
		this.damage = damageIn;
	}
	
	/**
	 * Set an internal reference to the represented item.
	 * This is needed specifically for ensuring that EntityDart drops the correct dart item.
	 * @param refIn a RegistryObject for building the Item
	 * @returns Reference to this object, for method chaining
	 */
	public ItemDart setItemReference(RegistryObject<Item> refIn) {
		this.ref = refIn;
		return this;
	}
	
	/**
	 * Create an ArrowEntity representing this Item.
	 * @returns the relevant Entity (in this case EntityDart) for use by the firing tool.
	 */
	@Override
	public EntityDart createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
		EntityDart arrowentity = new EntityDart(shooter, worldIn, ref.get());
		arrowentity.setDamage(this.damage);
		return arrowentity;
	}

	/**
	 * @returns whether the Dart is infinite and, therefore, whether one should be expended upon firing.
	 */
	// We override this method here because the version in ArrowItem *directly* compares against ArrowItem.class, rather than this more flexible check.
	@Override
	public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.entity.player.PlayerEntity player) {
		int enchant = net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(net.minecraft.enchantment.Enchantments.INFINITY, bow);
		return enchant <= 0 ? false : this instanceof ArrowItem;
	}

}