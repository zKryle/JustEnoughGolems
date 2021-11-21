package com.zkryle.jeg.client.golem.models;// Made with Blockbench 4.0.4
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.zkryle.jeg.common.golem.MagmaticGolemEntity;
import com.zkryle.jeg.common.golem.PlantGolemEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class MagmaticGolemEntityModel<T extends MagmaticGolemEntity> extends EntityModel <T> {
	private final ModelRenderer wholebody;
	private final ModelRenderer neck;
	private final ModelRenderer arm1;
	private final ModelRenderer arm2;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer stomach;
	private final ModelRenderer wholehead;
	private final ModelRenderer head;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;

	public MagmaticGolemEntityModel() {
		texWidth = 32;
		texHeight = 32;

		wholebody = new ModelRenderer(this);
		wholebody.setPos(-0.5F, 18.0F, 0.0F);
		setRotationAngle(wholebody, -3.1416F, 0.0F, 3.1416F);
		

		neck = new ModelRenderer(this);
		neck.setPos(-0.1F, -2.0F, 0.0F);
		wholebody.addChild(neck);
		neck.texOffs(0, 20).addBox(-1.6F, -1.0F, -1.6F, 3.0F, 1.0F, 3.0F, 0.0F, false);

		arm1 = new ModelRenderer(this);
		arm1.setPos(-3.5F, -1.0F, 0.0F);
		wholebody.addChild(arm1);
		arm1.texOffs(16, 24).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		arm2 = new ModelRenderer(this);
		arm2.setPos(3.5F, -1.0F, 0.0F);
		wholebody.addChild(arm2);
		arm2.texOffs(24, 24).addBox(0.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		leg1 = new ModelRenderer(this);
		leg1.setPos(-1.5F, 4.0F, 0.0F);
		wholebody.addChild(leg1);
		leg1.texOffs(8, 24).addBox(-1.0F, 0.0F, -1.1F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		leg2 = new ModelRenderer(this);
		leg2.setPos(1.5F, 4.0F, 0.0F);
		wholebody.addChild(leg2);
		leg2.texOffs(8, 28).addBox(-1.0F, 0.0F, -0.9F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		stomach = new ModelRenderer(this);
		stomach.setPos(0.1F, 1.0F, 0.0F);
		wholebody.addChild(stomach);
		stomach.texOffs(0, 8).addBox(-3.1F, 0.0F, -1.25F, 6.0F, 3.0F, 3.0F, 0.0F, false);
		stomach.texOffs(6, 0).addBox(-4.1F, -3.0F, -2.25F, 8.0F, 3.0F, 5.0F, 0.0F, false);

		wholehead = new ModelRenderer(this);
		wholehead.setPos(-0.1F, -2.4F, 0.0F);
		wholebody.addChild(wholehead);
		

		head = new ModelRenderer(this);
		head.setPos(-0.1F, 0.0F, 0.0F);
		wholehead.addChild(head);
		head.texOffs(12, 15).addBox(-2.3F, -4.0F, -2.6F, 5.0F, 4.0F, 5.0F, 0.0F, false);
		head.texOffs(0, 28).addBox(-0.3F, -2.0F, 2.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(0.0F, 0.0F, 0.0F);
		head.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.0F, -0.1309F);
		cube_r1.texOffs(0, 16).addBox(-2.5F, -7.5F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(0.0F, 0.0F, 0.0F);
		head.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, 0.48F);
		cube_r2.texOffs(0, 16).addBox(-0.25F, -6.25F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setPos(0.0F, 0.0F, 0.0F);
		head.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, -0.48F);
		cube_r3.texOffs(0, 16).addBox(-0.5F, -6.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setupAnim( MagmaticGolemEntity entity, float pLimbSwing, float pLimbSwingAmount, float ageInTicks, float pNetHeadYaw, float pHeadPitch){
		this.wholehead.xRot = pHeadPitch * ((float)Math.PI / -180F);
		this.wholehead.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.leg1.xRot = MathHelper.cos(pLimbSwing * 0.6662F + (float)Math.PI) * 1.4F * pLimbSwingAmount;
		this.leg2.xRot = MathHelper.cos(pLimbSwing * 0.6662F) * 1.4F * pLimbSwingAmount;
		this.arm2.xRot = MathHelper.cos(pLimbSwing * 0.6662F + (float)Math.PI) * 1.4F * pLimbSwingAmount;
		this.arm1.xRot = MathHelper.cos(pLimbSwing * 0.6662F) * 1.4F * pLimbSwingAmount;
	}

	@Override
	public void renderToBuffer( MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		matrixStack.scale( 1.5f, 1.5f, 1.5f );
		matrixStack.translate( 0.025f, -0.5f, 0.0f );
		wholebody.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}