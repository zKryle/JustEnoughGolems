package com.zkryle.jeg.client.golem.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.zkryle.jeg.JustEnoughGolems;
import com.zkryle.jeg.client.golem.models.EnragedGolemSpikeModel;
import com.zkryle.jeg.client.golem.models.EnragedMagmaticGolemModel;
import com.zkryle.jeg.common.golem.EnragedGolemSpikeEntity;
import com.zkryle.jeg.common.golem.EnragedMagmaticGolemEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.joml.AxisAngle4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import static com.zkryle.jeg.client.golem.models.EnragedGolemSpikeModel.LAYER_LOCATION;

public class EnragedGolemSpikeRenderer<T extends EnragedGolemSpikeEntity> extends EntityRenderer<T>{
    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation( JustEnoughGolems.MOD_ID , "textures/entity/enragedmagmaticgolem/enragedmagmaticgolem_100_b.png" );
    private final EnragedGolemSpikeModel model;
    public EnragedGolemSpikeRenderer( EntityRendererProvider.Context p_i46179_1_ ){
        super( p_i46179_1_ );
        this.model = new EnragedGolemSpikeModel(p_i46179_1_.bakeLayer( LAYER_LOCATION ));
    }

    @Override
    public void render( T pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight){
        float f = pEntity.getAnimationProgress(pPartialTicks);
        if (f != 0.0F) {
            float f1 = 2.0F;
            if (f > 0.9F) {
                f1 = (float)((double)f1 * ((1.0D - (double)f) / (double)0.1F));
            }

            pMatrixStack.pushPose();
            pMatrixStack.mulPose( new Quaternionf(new AxisAngle4f((float)Math.toRadians(90.0F - pEntity.yRotO), 0, 1, 0 )));
            pMatrixStack.scale(-f1, -f1, f1);
            pMatrixStack.translate(0.0D, (double)-1.75F - pEntity.getRaiseAnim() , 0.0D);
            pMatrixStack.scale(1.5F, 1.5F, 1.5F);
            this.model.setupAnim(pEntity, f, 0.0F, 0.0F, pEntity.yRotO, pEntity.xRotO);
            VertexConsumer ivertexbuilder = pBuffer.getBuffer(this.model.renderType(TEXTURE_LOCATION));
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
