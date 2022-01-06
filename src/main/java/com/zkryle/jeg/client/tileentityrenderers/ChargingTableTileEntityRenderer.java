package com.zkryle.jeg.client.tileentityrenderers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.zkryle.jeg.common.tileentities.ChargingTableTileEntity;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.client.model.data.EmptyModelData;

import java.util.Random;

import static com.zkryle.jeg.client.events.StaticClientEventSubscriber.FULL_CORE;
import static com.zkryle.jeg.client.events.StaticClientEventSubscriber.RECHARGING_CORE;
import static java.lang.Math.*;

public class ChargingTableTileEntityRenderer extends TileEntityRenderer <ChargingTableTileEntity>{
    public ChargingTableTileEntityRenderer( TileEntityRendererDispatcher p_i226006_1_ ){
        super( p_i226006_1_ );
    }

    @Override
    public void render( ChargingTableTileEntity pBlockEntity , float pPartialTicks , MatrixStack pMatrixStack , IRenderTypeBuffer pBuffer , int pCombinedLight , int pCombinedOverlay ){
        if(pBlockEntity.hasCore()){
            Minecraft mc = Minecraft.getInstance();
            pMatrixStack.pushPose();
            pMatrixStack.translate( 0.5F , 0.5F , 0.5F );
            pMatrixStack.mulPose( Vector3f.YP.rotationDegrees( pBlockEntity.getRotationAnim() ) );
            pMatrixStack.translate( -0.5F , -0.5F , -0.5F );
            pMatrixStack.mulPose( Vector3f.XN.rotationDegrees( 90.0F ) );
            pMatrixStack.translate( -0.03F , -1.03F , 0.37F );
            pMatrixStack.scale( 1.0F , 1.0F , 1.0F );
            RenderType renderType = RenderTypeLookup.getRenderType( mc.level.getBlockState( pBlockEntity.getBlockPos() ) , false );
            pMatrixStack.translate( 0 , 0 , pBlockEntity.getCorePercentage() / 100.0F - 0.01F );
            mc.getBlockRenderer().getModelRenderer().renderModel( mc.level , this.getCoreModel( pBlockEntity ) ,
                    mc.level.getBlockState( pBlockEntity.getBlockPos() ) , pBlockEntity.getBlockPos() ,
                    pMatrixStack , pBuffer.getBuffer( renderType ) , false , mc.level.random ,
                    mc.level.getBlockState( pBlockEntity.getBlockPos() ).getSeed( pBlockEntity.getBlockPos() ) ,
                    OverlayTexture.NO_OVERLAY , EmptyModelData.INSTANCE );
            pMatrixStack.popPose();

            if(pBlockEntity.getCore().getDamageValue() == 0){
                renderParticleSpiral( pBlockEntity );
            }
        }
    }

    private IBakedModel getCoreModel(ChargingTableTileEntity pBlockEntity){
        Minecraft mc = Minecraft.getInstance();
        short charge = pBlockEntity.getCharge();
        IBakedModel coreModel = mc.getModelManager().getModel( RECHARGING_CORE );
        IBakedModel fullCoreModel = mc.getModelManager().getModel( FULL_CORE );
        if(charge > 0 && pBlockEntity.getCorePercentage() < 100){
            return coreModel;
        } else {
            if(pBlockEntity.getCorePercentage() <= 75){
                return mc.getItemRenderer().getModel( pBlockEntity.getCore() , mc.level , mc.player );
            } else {
                return fullCoreModel;
            }
        }
    }

    private void renderParticleSpiral ( TileEntity pBlockEntity ){
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
            if(new Random().nextInt(20000) == 9){
                mc.level.addParticle( ParticleTypes.REVERSE_PORTAL , pos.getX() + 0.5F + x , pos.getY() + theta / 25 , pos.getZ() + 0.5F + z , 0 , 0 , 0 );
            }
        }
    }
}



