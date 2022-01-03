package com.zkryle.jeg.client.golem.models;// Made with Blockbench 4.1.1
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.zkryle.jeg.common.golem.EnragedGolemSpikeEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class EnragedGolemSpikeModel<T extends EnragedGolemSpikeEntity> extends EntityModel <T>{
	private final ModelRenderer bone;
	private final ModelRenderer bone4;
	private final ModelRenderer bone3;
	private final ModelRenderer bone2;

	public EnragedGolemSpikeModel() {
		texWidth = 128;
		texHeight = 128;

		bone = new ModelRenderer(this);
		bone.setPos(-1.0F, 57.5F, -2.0F);
		

		bone4 = new ModelRenderer(this);
		bone4.setPos(-0.4645F, -30.4471F, 3.3733F);
		bone.addChild(bone4);
		setRotationAngle(bone4, -0.6449F, -0.0007F, -0.4646F);
		bone4.texOffs(82, 110).addBox(-0.475F, -3.4499F, -0.5008F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		bone4.texOffs(82, 110).addBox(-1.475F, 0.0501F, -0.5008F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		bone4.texOffs(82, 110).addBox(0.525F, 0.0501F, -0.5008F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		bone4.texOffs(82, 110).addBox(0.025F, -2.4499F, -0.5008F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		bone4.texOffs(82, 110).addBox(-0.725F, -2.4503F, -0.9967F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		bone4.texOffs(82, 110).addBox(-0.975F, -2.4503F, -0.4967F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		bone4.texOffs(82, 110).addBox(-0.475F, -2.4499F, -0.0008F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		bone4.texOffs(82, 110).addBox(-0.475F, 0.0501F, -1.5008F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone4.texOffs(82, 110).addBox(-0.475F, 0.0501F, 0.4992F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone4.texOffs(47, 88).addBox(-0.975F, -0.4499F, -1.0008F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(8.5047F, -30.3534F, 1.7417F);
		bone.addChild(bone3);
		setRotationAngle(bone3, -1.9338F, 1.3935F, -1.2082F);
		bone3.texOffs(82, 110).addBox(-1.8504F, 0.5007F, -5.001F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		bone3.texOffs(82, 110).addBox(-2.8504F, 4.0007F, -5.001F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		bone3.texOffs(82, 110).addBox(-0.8504F, 4.0007F, -5.001F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		bone3.texOffs(82, 110).addBox(-1.3504F, 1.5007F, -5.001F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		bone3.texOffs(82, 110).addBox(-2.1004F, 1.5004F, -5.4969F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		bone3.texOffs(82, 110).addBox(-2.3504F, 1.5004F, -4.9969F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		bone3.texOffs(82, 110).addBox(-1.8504F, 1.5007F, -4.501F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		bone3.texOffs(82, 110).addBox(-1.8504F, 4.0007F, -6.001F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone3.texOffs(82, 110).addBox(-1.8504F, 4.0007F, -4.001F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone3.texOffs(47, 88).addBox(-2.3504F, 3.5007F, -5.501F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(6.475F, -30.5388F, 0.2765F);
		bone.addChild(bone2);
		setRotationAngle(bone2, 0.5989F, -0.6051F, -0.7826F);
		bone2.texOffs(82, 110).addBox(-4.9741F, -5.5798F, 3.8833F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		bone2.texOffs(82, 110).addBox(-5.9741F, -2.0798F, 3.8833F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		bone2.texOffs(82, 110).addBox(-3.9741F, -2.0798F, 3.8833F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		bone2.texOffs(82, 110).addBox(-4.4741F, -4.5798F, 3.8833F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		bone2.texOffs(82, 110).addBox(-5.2241F, -4.5801F, 3.3874F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		bone2.texOffs(82, 110).addBox(-5.4741F, -4.5801F, 3.8874F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		bone2.texOffs(82, 110).addBox(-4.9741F, -4.5798F, 4.3833F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		bone2.texOffs(82, 110).addBox(-4.9741F, -2.0798F, 2.8833F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone2.texOffs(82, 110).addBox(-4.9741F, -2.0798F, 4.8833F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone2.texOffs(47, 88).addBox(-5.4741F, -2.5798F, 3.3833F, 2.0F, 4.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(EnragedGolemSpikeEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void renderToBuffer( MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		bone.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle( ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}