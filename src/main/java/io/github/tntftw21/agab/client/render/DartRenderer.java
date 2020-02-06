package io.github.tntftw21.agab.client.render;

import io.github.tntftw21.agab.AGAB;
import io.github.tntftw21.agab.entity.EntityDart;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

/**
 * Custom ArrowRenderer for EntityDart. Designed to be usable with all Dart types.
 * 
 * @author TNTftw21
 */
public class DartRenderer extends ArrowRenderer<EntityDart> {

	public DartRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	public ResourceLocation getEntityTexture(EntityDart entity) {
		Item refItem = entity.getArrowStack().getItem();
		return new ResourceLocation(AGAB.MODID, "textures/entity/projectile/" + refItem.getRegistryName().getPath());
	}

}
