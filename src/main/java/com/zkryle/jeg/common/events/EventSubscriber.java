package com.zkryle.jeg.common.events;

import com.zkryle.jeg.client.golem.renderers.EnragedGolemSpikeRenderer;
import com.zkryle.jeg.client.golem.renderers.EnragedMagmaticGolemRenderer;
import com.zkryle.jeg.client.golem.renderers.MagmaticGolemEntityRenderer;
import com.zkryle.jeg.client.golem.renderers.PlantGolemEntityRenderer;
import com.zkryle.jeg.client.tileentityrenderers.ChargingTableTileEntityRenderer;
import com.zkryle.jeg.common.golem.EnragedGolemSpikeEntity;
import com.zkryle.jeg.common.golem.EnragedMagmaticGolemEntity;
import com.zkryle.jeg.common.golem.MagmaticGolemEntity;
import com.zkryle.jeg.common.golem.PlantGolemEntity;
import com.zkryle.jeg.core.Init;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class EventSubscriber{

    @SubscribeEvent
    public void onAttributesCreated( EntityAttributeCreationEvent event ){
        event.put( Init.PLANT_GOLEM_ENTITY.get(), PlantGolemEntity.createAttributes().build() );
        event.put( Init.MAGMATIC_GOLEM_ENTITY.get(), MagmaticGolemEntity.createAttributes().build() );
        event.put( Init.ENRAGED_MAGMATIC_GOLEM_ENTITY.get(), EnragedMagmaticGolemEntity.createAttributes().build() );
    }

    @SubscribeEvent
    public void onClientSetup( FMLClientSetupEvent event ) {
        ClientRegistry.bindTileEntityRenderer( Init.CHARGING_TABLE_TILE_ENTITY_TYPE.get(), ChargingTableTileEntityRenderer::new );
        RenderingRegistry.registerEntityRenderingHandler( Init.PLANT_GOLEM_ENTITY.get(), PlantGolemEntityRenderer::new );
        RenderingRegistry.registerEntityRenderingHandler( Init.MAGMATIC_GOLEM_ENTITY.get(), MagmaticGolemEntityRenderer::new );
        RenderingRegistry.registerEntityRenderingHandler( Init.ENRAGED_MAGMATIC_GOLEM_ENTITY.get(), EnragedMagmaticGolemRenderer::new );
        RenderingRegistry.registerEntityRenderingHandler( Init.ENRAGED_GOLEM_SPIKE_ENTITY.get(), EnragedGolemSpikeRenderer::new );
    }
}
