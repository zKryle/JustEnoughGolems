package com.zkryle.jeg.client.tileentityrenderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.zkryle.jeg.common.tileentities.ChargingTableBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.client.model.data.ModelData;
import org.joml.AxisAngle4f;
import org.joml.Quaternionf;

import java.util.Random;

import static com.zkryle.jeg.client.events.StaticClientEventSubscriber.FULL_CORE;
import static com.zkryle.jeg.client.events.StaticClientEventSubscriber.RECHARGING_CORE;
import static java.lang.Math.*;

public class ChargingTableBlockEntityRenderer implements BlockEntityRenderer <ChargingTableBlockEntity>{
    public ChargingTableBlockEntityRenderer( BlockEntityRendererProvider.Context context ){
    }

    @Override
    public void render( ChargingTableBlockEntity pBlockEntity , float pPartialTicks , PoseStack pMatrixStack , MultiBufferSource pBuffer , int pCombinedLight , int pCombinedOverlay ){
        if(pBlockEntity.hasCore()){
            Minecraft mc = Minecraft.getInstance();
            pMatrixStack.pushPose();
            pMatrixStack.translate( 0.5F , 0.5F , 0.5F );
            pMatrixStack.mulPose( new Quaternionf(new AxisAngle4f((float)Math.toRadians(pBlockEntity.getRotationAnim()), 0, 1, 0 )));
            pMatrixStack.translate( -0.5F , -0.5F , -0.5F );
            pMatrixStack.mulPose( new Quaternionf(new AxisAngle4f((float)Math.toRadians(90.0F), -1, 0, 0 )));
            pMatrixStack.translate( -0.03F , -1.03F , 0.37F );
            pMatrixStack.scale( 1.0F , 1.0F , 1.0F );
            RenderType renderType = ItemBlockRenderTypes.getRenderType( mc.level.getBlockState( pBlockEntity.getBlockPos() ) , false );
            pMatrixStack.translate( 0 , 0 , pBlockEntity.getCorePercentage() / 100.0F - 0.01F );
            mc.getBlockRenderer().getModelRenderer().renderModel( pMatrixStack.last() , pBuffer.getBuffer( renderType ) ,
                    mc.level.getBlockState( pBlockEntity.getBlockPos() ) , this.getCoreModel( pBlockEntity ) ,
                    1.0F, 1.0F, 1.0F, pCombinedLight , OverlayTexture.NO_OVERLAY,
                    ModelData.EMPTY , renderType );
            pMatrixStack.popPose();

            if(pBlockEntity.getCore().getDamageValue() == 0){
                renderParticleSpiral( pBlockEntity );
            }
        }
    }

    private BakedModel getCoreModel( ChargingTableBlockEntity pBlockEntity ){
        Minecraft mc = Minecraft.getInstance();
        short charge = pBlockEntity.getCharge();
        BakedModel coreModel = mc.getModelManager().getModel( RECHARGING_CORE );
        BakedModel fullCoreModel = mc.getModelManager().getModel( FULL_CORE );
        if(charge > 0 && pBlockEntity.getCorePercentage() < 100){
            return coreModel;
        }else{
            if(pBlockEntity.getCorePercentage() <= 75){
                return mc.getItemRenderer().getModel( pBlockEntity.getCore() , mc.level , mc.player , 0 );
            }else{
                return fullCoreModel;
            }
        }
    }

    private void renderParticleSpiral( BlockEntity pBlockEntity ){
        Minecraft mc = Minecraft.getInstance();
        double degrees = toRadians( 0.2 );

        double end = 360 * 2 * 10 * degrees;
        double a = 0;
        double b = 0.12;
        double c = 2;
        BlockPos pos = pBlockEntity.getBlockPos().above();

        for(double theta = 0; theta < end; theta += degrees){
            double r = a + b * pow( theta , 1 / c );
            double x = r * cos( theta );
            double z = r * sin( theta );
            if(new Random().nextInt( 20000 ) == 9){
                mc.level.addParticle( ParticleTypes.REVERSE_PORTAL , pos.getX() + 0.5F + x , pos.getY() + theta / 25 , pos.getZ() + 0.5F + z , 0 , 0 , 0 );
            }
        }
    }
}



