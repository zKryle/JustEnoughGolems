package com.zkryle.jeg.client.golem.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.zkryle.jeg.JustEnoughGolems;
import com.zkryle.jeg.client.golem.models.EnragedMagmaticGolemModel;
import com.zkryle.jeg.client.golem.models.PlantGolemEntityModel;
import com.zkryle.jeg.common.golem.EnragedMagmaticGolemEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.joml.AxisAngle4f;
import org.joml.Quaternionf;

public class EnragedMagmaticGolemRenderer extends MobRenderer <EnragedMagmaticGolemEntity, EnragedMagmaticGolemModel<EnragedMagmaticGolemEntity>>{

    public static final ResourceLocation TEXTURE_NOCORE = new ResourceLocation( JustEnoughGolems.MOD_ID , "textures/entity/enragedmagmaticgolem/enragedmagmaticgolem_nocore.png" );
    public static final ResourceLocation TEXTURE_COREOFF = new ResourceLocation( JustEnoughGolems.MOD_ID , "textures/entity/enragedmagmaticgolem/enragedmagmaticgolem_off.png" );
    public static final ResourceLocation TEXTURE_25_A = new ResourceLocation( JustEnoughGolems.MOD_ID , "textures/entity/enragedmagmaticgolem/enragedmagmaticgolem_25_a.png" );
    public static final ResourceLocation TEXTURE_25_B = new ResourceLocation( JustEnoughGolems.MOD_ID , "textures/entity/enragedmagmaticgolem/enragedmagmaticgolem_25_b.png" );
    public static final ResourceLocation TEXTURE_50_A = new ResourceLocation( JustEnoughGolems.MOD_ID , "textures/entity/enragedmagmaticgolem/enragedmagmaticgolem_50_a.png" );
    public static final ResourceLocation TEXTURE_50_B = new ResourceLocation( JustEnoughGolems.MOD_ID , "textures/entity/enragedmagmaticgolem/enragedmagmaticgolem_50_b.png" );
    public static final ResourceLocation TEXTURE_75_A = new ResourceLocation( JustEnoughGolems.MOD_ID , "textures/entity/enragedmagmaticgolem/enragedmagmaticgolem_75_a.png" );
    public static final ResourceLocation TEXTURE_75_B = new ResourceLocation( JustEnoughGolems.MOD_ID , "textures/entity/enragedmagmaticgolem/enragedmagmaticgolem_75_b.png" );
    public static final ResourceLocation TEXTURE_100_A = new ResourceLocation( JustEnoughGolems.MOD_ID , "textures/entity/enragedmagmaticgolem/enragedmagmaticgolem_100_a.png" );
    public static final ResourceLocation TEXTURE_100_B = new ResourceLocation( JustEnoughGolems.MOD_ID , "textures/entity/enragedmagmaticgolem/enragedmagmaticgolem_100_b.png" );

    public EnragedMagmaticGolemRenderer( EntityRendererProvider.Context context ){
        super( context , new EnragedMagmaticGolemModel<>(context.bakeLayer( EnragedMagmaticGolemModel.LAYER_LOCATION  )) , 0.9f );
    }

    @Override
    protected void setupRotations( EnragedMagmaticGolemEntity pEntityLiving, PoseStack pMatrixStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pMatrixStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        if (!((double)pEntityLiving.animationSpeed < 0.01D)) {
            float f1 = pEntityLiving.animationPosition - pEntityLiving.animationSpeed * (1.0F - pPartialTicks) + 6.0F;
            float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
            pMatrixStack.mulPose( new Quaternionf(new AxisAngle4f((float)Math.toRadians(6.5F * f2), 0, 0, 1 )));
        }
    }

    @Override
    public ResourceLocation getTextureLocation( EnragedMagmaticGolemEntity pEntity ){
        if(pEntity.hasCoreClient()){
            if(pEntity.getCorePercentageClient() < 3){
                return TEXTURE_COREOFF;
            } else if( pEntity.getCorePercentageClient() <= 25){
                if(pEntity.bodyInclination <= 0){
                    return TEXTURE_25_B;
                } else return TEXTURE_25_A;
            } else if( pEntity.getCorePercentageClient() <= 50){
                if(pEntity.bodyInclination <= 0){
                    return TEXTURE_50_B;
                } else return TEXTURE_50_A;
            } else if( pEntity.getCorePercentageClient() <= 75){
                if(pEntity.bodyInclination <= 0){
                    return TEXTURE_75_B;
                } else return TEXTURE_75_A;
            } else if( pEntity.getCorePercentageClient() <= 100){
                if(pEntity.bodyInclination <= 0){
                    return TEXTURE_100_B;
                } else return TEXTURE_100_A;
            }
        }
        return TEXTURE_NOCORE;
    }
}
