package io.github.tntftw21.agab.item;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

/**
 * Custom version of the BowItem class which allows for manipulation of the arrow's launch velocity (and indirectly its damage).
 * 
 * @author Tony
 *
 */
public class ItemAtlatl extends BowItem {
	
	public final float velocityMult;

	/**
	 * Default constructor.
	 * @param builder The Item.Properties to be used by this item
	 * @param velocityMultIn The velocity multiplier to be used for this "bow" (vanilla bow uses 3.0F)
	 */
	public ItemAtlatl(Properties builder, float velocityMultIn) {
		super(builder);
		this.velocityMult = velocityMultIn;
	}
	
	/**
	 * Called when the player stops using an Item (stops holding the right mouse button).
	 */
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
		if (entityLiving instanceof PlayerEntity) {
			PlayerEntity playerentity = (PlayerEntity)entityLiving;
			boolean doesNotNeedArrow = playerentity.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
			ItemStack itemstack = playerentity.findAmmo(stack);

			int timeCharged = this.getUseDuration(stack) - timeLeft;
			timeCharged = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, playerentity, timeCharged, !itemstack.isEmpty() || doesNotNeedArrow);
			if (timeCharged < 0) return;

			if (!itemstack.isEmpty() || doesNotNeedArrow) {
				if (itemstack.isEmpty()) {
					itemstack = new ItemStack(Items.ARROW);
				}

				float arrowVelocity = getArrowVelocity(timeCharged);
				if (!((double)arrowVelocity < 0.1D)) {
					boolean usesArrow = playerentity.abilities.isCreativeMode || (itemstack.getItem() instanceof ArrowItem && ((ArrowItem)itemstack.getItem()).isInfinite(itemstack, stack, playerentity));
					if (!worldIn.isRemote) {
						ArrowItem arrowitem = (ArrowItem)(itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
						AbstractArrowEntity abstractarrowentity = arrowitem.createArrow(worldIn, itemstack, playerentity);
						abstractarrowentity = customeArrow(abstractarrowentity);
						//Near as I can tell, the multiplier on arrowVelocity is exactly that - a multiplier. Changing it allows us to control how hard we lob an arrow.
						abstractarrowentity.shoot(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, arrowVelocity * this.velocityMult, 1.0F);
						if (arrowVelocity == 1.0F) {
							abstractarrowentity.setIsCritical(true);
						}
						
						//So weird thing, arrows have a base damage of 2, and the bow doesn't *add* any damage, it just passes in the arrow's velocity.
						
						int powerLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
						if (powerLevel > 0) {
							abstractarrowentity.setDamage(abstractarrowentity.getDamage() + (double)powerLevel * 0.5D + 0.5D);
						}

						int punchLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
						if (punchLevel > 0) {
							abstractarrowentity.setKnockbackStrength(punchLevel);
						}

						if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
							abstractarrowentity.setFire(100);
						}

						stack.damageItem(1, playerentity, (p_220009_1_) -> {
							p_220009_1_.sendBreakAnimation(playerentity.getActiveHand());
						});
						if (usesArrow || playerentity.abilities.isCreativeMode && (itemstack.getItem() == Items.SPECTRAL_ARROW || itemstack.getItem() == Items.TIPPED_ARROW)) {
							abstractarrowentity.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
						}

						worldIn.addEntity(abstractarrowentity);
					}

					worldIn.playSound((PlayerEntity)null, playerentity.func_226277_ct_(), playerentity.func_226278_cu_(), playerentity.func_226281_cx_(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + arrowVelocity * 0.5F);
					if (!usesArrow && !playerentity.abilities.isCreativeMode) {
						itemstack.shrink(1);
						if (itemstack.isEmpty()) {
							playerentity.inventory.deleteStack(itemstack);
						}
					}

					playerentity.addStat(Stats.ITEM_USED.get(this));
				}
			}
		}
	}

}