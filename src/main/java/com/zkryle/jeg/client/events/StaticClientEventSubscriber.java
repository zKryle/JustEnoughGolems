package com.zkryle.jeg.client.events;

import com.zkryle.jeg.JustEnoughGolems;
import com.zkryle.jeg.client.golem.models.EnragedGolemSpikeModel;
import com.zkryle.jeg.client.golem.models.EnragedMagmaticGolemModel;
import com.zkryle.jeg.client.golem.models.MagmaticGolemModel;
import com.zkryle.jeg.client.golem.models.PlantGolemEntityModel;
import com.zkryle.jeg.client.golem.renderers.EnragedGolemSpikeRenderer;
import com.zkryle.jeg.client.golem.renderers.EnragedMagmaticGolemRenderer;
import com.zkryle.jeg.client.golem.renderers.MagmaticGolemEntityRenderer;
import com.zkryle.jeg.client.golem.renderers.PlantGolemEntityRenderer;
import com.zkryle.jeg.client.tileentityrenderers.ChargingTableBlockEntityRenderer;
import com.zkryle.jeg.core.Init;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ForgeModelBakery;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JustEnoughGolems.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class StaticClientEventSubscriber{
    public static ResourceLocation RECHARGING_CORE = new ResourceLocation( JustEnoughGolems.MOD_ID, "item/special/recharging_core" );
    public static ResourceLocation FULL_CORE = new ResourceLocation( JustEnoughGolems.MOD_ID, "item/special/full_core" );

    @SubscribeEvent
    public static void registerAdditionalModels( ModelRegistryEvent event ){
        ForgeModelBakery.addSpecialModel( RECHARGING_CORE );
        ForgeModelBakery.addSpecialModel( FULL_CORE );
    }

    @SubscribeEvent
    public static void registerEntityRenderers( EntityRenderersEvent.RegisterRenderers event ){
        event.registerBlockEntityRenderer( Init.CHARGING_TABLE_BLOCK_ENTITY_TYPE.get(), ChargingTableBlockEntityRenderer::new );
        event.registerEntityRenderer( Init.PLANT_GOLEM_ENTITY.get(), PlantGolemEntityRenderer::new );
        event.registerEntityRenderer( Init.MAGMATIC_GOLEM_ENTITY.get(), MagmaticGolemEntityRenderer::new );
        event.registerEntityRenderer( Init.ENRAGED_MAGMATIC_GOLEM_ENTITY.get(), EnragedMagmaticGolemRenderer::new);
        event.registerEntityRenderer( Init.ENRAGED_GOLEM_SPIKE_ENTITY.get(), EnragedGolemSpikeRenderer::new );
    }

    @SubscribeEvent
    public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition( PlantGolemEntityModel.LAYER_LOCATION , PlantGolemEntityModel::createBodyLayer);
        event.registerLayerDefinition( MagmaticGolemModel.LAYER_LOCATION, MagmaticGolemModel::createBodyLayer);
        event.registerLayerDefinition( EnragedMagmaticGolemModel.LAYER_LOCATION, EnragedMagmaticGolemModel::createBodyLayer);
        event.registerLayerDefinition( EnragedGolemSpikeModel.LAYER_LOCATION , EnragedGolemSpikeModel::createBodyLayer);
    }
}
