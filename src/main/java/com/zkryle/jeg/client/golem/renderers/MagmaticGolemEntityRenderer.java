package com.zkryle.jeg.client.golem.renderers;

import com.zkryle.jeg.JustEnoughGolems;
import com.zkryle.jeg.client.golem.models.MagmaticGolemModel;
import com.zkryle.jeg.common.golem.MagmaticGolemEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

public class MagmaticGolemEntityRenderer extends MobRenderer <MagmaticGolemEntity, MagmaticGolemModel <MagmaticGolemEntity>>{

    public static final ResourceLocation TEXTURE = new ResourceLocation( JustEnoughGolems.MOD_ID , "textures/entity/magmaticgolem/magmaticgolem.png" );
    public static final ResourceLocation TEXTURE_LIT = new ResourceLocation( JustEnoughGolems.MOD_ID , "textures/entity/magmaticgolem/magmaticgolem_active.png" );

    public MagmaticGolemEntityRenderer( EntityRendererProvider.Context context ){
        super( context , new MagmaticGolemModel <>(context.bakeLayer( MagmaticGolemModel.LAYER_LOCATION )) , 0.4f );
        this.addLayer( new ItemInHandLayer <>( this, context.getItemInHandRenderer() ) );
    }

    @Override
    public ResourceLocation getTextureLocation( MagmaticGolemEntity pEntity ){
        if(pEntity.isTame() && pEntity.isOn()){
            return TEXTURE_LIT;
        } else if (!pEntity.isTame()) {
            return TEXTURE_LIT;
        } else {
            return TEXTURE;
        }
    }
}
