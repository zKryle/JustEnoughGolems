package com.zkryle.jeg.client.golem.renderers;

import com.zkryle.jeg.JustEnoughGolems;
import com.zkryle.jeg.client.golem.models.PlantGolemEntityModel;
import com.zkryle.jeg.common.golem.PlantGolemEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

public class PlantGolemEntityRenderer extends MobRenderer <PlantGolemEntity, PlantGolemEntityModel <PlantGolemEntity>>{

    public static final ResourceLocation TEXTURE = new ResourceLocation( JustEnoughGolems.MOD_ID , "textures/entity/plantgolem/plantgolem.png" );

    public PlantGolemEntityRenderer( EntityRendererProvider.Context manager ){
        super( manager , new PlantGolemEntityModel <>(manager.bakeLayer( PlantGolemEntityModel.LAYER_LOCATION  )) , 0.4f );
        this.addLayer(new ItemInHandLayer <>(this));
    }


    @Override
    public ResourceLocation getTextureLocation( PlantGolemEntity pEntity ){
        return TEXTURE;
    }
}
