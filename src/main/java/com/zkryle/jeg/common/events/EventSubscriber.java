package com.zkryle.jeg.common.events;

import com.zkryle.jeg.client.golem.models.EnragedGolemSpikeModel;
import com.zkryle.jeg.client.golem.models.EnragedMagmaticGolemModel;
import com.zkryle.jeg.client.golem.models.MagmaticGolemModel;
import com.zkryle.jeg.client.golem.models.PlantGolemEntityModel;
import com.zkryle.jeg.client.golem.renderers.EnragedGolemSpikeRenderer;
import com.zkryle.jeg.client.golem.renderers.EnragedMagmaticGolemRenderer;
import com.zkryle.jeg.client.golem.renderers.MagmaticGolemEntityRenderer;
import com.zkryle.jeg.client.golem.renderers.PlantGolemEntityRenderer;
import com.zkryle.jeg.client.tileentityrenderers.ChargingTableBlockEntityRenderer;
import com.zkryle.jeg.common.golem.EnragedGolemSpikeEntity;
import com.zkryle.jeg.common.golem.EnragedMagmaticGolemEntity;
import com.zkryle.jeg.common.golem.MagmaticGolemEntity;
import com.zkryle.jeg.common.golem.PlantGolemEntity;
import com.zkryle.jeg.core.Init;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventSubscriber{

    @SubscribeEvent
    public void onAttributesCreated( EntityAttributeCreationEvent event ){
        event.put( Init.PLANT_GOLEM_ENTITY.get(), PlantGolemEntity.createAttributes().build() );
        event.put( Init.MAGMATIC_GOLEM_ENTITY.get(), MagmaticGolemEntity.createAttributes().build() );
        event.put( Init.ENRAGED_MAGMATIC_GOLEM_ENTITY.get(), EnragedMagmaticGolemEntity.createAttributes().build() );
    }

}
