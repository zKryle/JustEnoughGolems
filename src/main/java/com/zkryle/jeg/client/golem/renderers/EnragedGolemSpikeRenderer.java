package com.zkryle.jeg.client.golem.renderers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.zkryle.jeg.JustEnoughGolems;
import com.zkryle.jeg.client.golem.models.EnragedGolemSpikeModel;
import com.zkryle.jeg.client.golem.models.EnragedMagmaticGolemModel;
import com.zkryle.jeg.common.golem.EnragedGolemSpikeEntity;
import com.zkryle.jeg.common.golem.EnragedMagmaticGolemEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

public class EnragedGolemSpikeRenderer<T extends EnragedGolemSpikeEntity> extends EntityRenderer<T>{
    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation( JustEnoughGolems.MOD_ID , "textures/entity/enragedmagmaticgolem/enragedmagmaticgolem_100_b.png" );
    private final EnragedGolemSpikeModel model = new EnragedGolemSpikeModel();
    public EnragedGolemSpikeRenderer( EntityRendererManager p_i46179_1_ ){
        super( p_i46179_1_ );
    }

    @Override
    public void render( T pEntity, float pEntityYaw, float pPartialTicks, MatrixStack pMatrixStack, IRenderTypeBuffer pBuffer, int pPackedLight){
        float f = pEntity.getAnimationProgress(pPartialTicks);
        if (f != 0.0F) {
            float f1 = 2.0F;
            if (f > 0.9F) {
                f1 = (float)((double)f1 * ((1.0D - (double)f) / (double)0.1F));
            }

            pMatrixStack.pushPose();
            pMatrixStack.mulPose( Vector3f.YP.rotationDegrees(90.0F - pEntity.yRot));
            pMatrixStack.scale(-f1, -f1, f1);
            pMatrixStack.translate(0.0D, (double)-1.75F - pEntity.getRaiseAnim() , 0.0D);
            pMatrixStack.scale(1.5F, 1.5F, 1.5F);
            this.model.setupAnim(pEntity, f, 0.0F, 0.0F, pEntity.yRot, pEntity.xRot);
            IVertexBuilder ivertexbuilder = pBuffer.getBuffer(this.model.renderType(TEXTURE_LOCATION));
            this.model.renderToBuffer(pMatrixStack, ivertexbuilder, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            pMatrixStack.popPose();
            super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
        }
    }

    @Override
    public ResourceLocation getTextureLocation( EnragedGolemSpikeEntity pEntity ){
        return TEXTURE_LOCATION;
    }
}
