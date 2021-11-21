package com.zkryle.jeg.common.events;

import com.zkryle.jeg.client.golem.renderers.MagmaticGolemEntityRenderer;
import com.zkryle.jeg.client.golem.renderers.PlantGolemEntityRenderer;
import com.zkryle.jeg.common.golem.MagmaticGolemEntity;
import com.zkryle.jeg.common.golem.PlantGolemEntity;
import com.zkryle.jeg.core.Init;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class EventSubscriber{

    @SubscribeEvent
    public void onAttributesCreated( EntityAttributeCreationEvent event ){
        event.put( Init.PLANT_GOLEM_ENTITY.get(), PlantGolemEntity.createAttributes().build() );
        /*event.put( Init.MAGMATIC_GOLEM_ENTITY.get(), PlantGolemEntity.createAttributes().build() );*/
    }

    @SubscribeEvent
    public void onClientSetup( FMLClientSetupEvent event ) {
        RenderingRegistry.registerEntityRenderingHandler( Init.PLANT_GOLEM_ENTITY.get(), PlantGolemEntityRenderer::new );
        /*RenderingRegistry.registerEntityRenderingHandler( Init.MAGMATIC_GOLEM_ENTITY.get(), MagmaticGolemEntityRenderer::new );*/
    }
}
