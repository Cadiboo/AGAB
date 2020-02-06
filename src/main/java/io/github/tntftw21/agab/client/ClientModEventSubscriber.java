package io.github.tntftw21.agab.client;

import io.github.tntftw21.agab.AGAB;
import io.github.tntftw21.agab.client.render.DartRenderer;
import io.github.tntftw21.agab.init.ModEntityTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = AGAB.MODID, bus=EventBusSubscriber.Bus.MOD, value=Dist.CLIENT)
public class ClientModEventSubscriber {

	@SubscribeEvent
	public static void onClientSetup(final FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.DART.get(), DartRenderer::new);
	}

}