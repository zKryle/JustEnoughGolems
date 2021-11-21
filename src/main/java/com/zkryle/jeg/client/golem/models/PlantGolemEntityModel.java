package com.zkryle.jeg.client.golem.models;// Made with Blockbench 4.0.4
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.zkryle.jeg.common.golem.PlantGolemEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

public class PlantGolemEntityModel<T extends PlantGolemEntity> extends EntityModel <T> implements IHasArm{
	private final ModelRenderer wholebody;
	private final ModelRenderer neck;
	private final ModelRenderer arm1;
	private final ModelRenderer arm2;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer stomach;
	private final ModelRenderer wholehead;
	private final ModelRenderer head;
	private final ModelRenderer twig;
	private final ModelRenderer leaves;
	private final ModelRenderer leaf1;
	private final ModelRenderer leaf2;

	public PlantGolemEntityModel() {
		texWidth = 32;
		texHeight = 32;

		wholebody = new ModelRenderer(this);
		wholebody.setPos(-0.5F, 18.0F, 0.0F);
		setRotationAngle(wholebody, -3.1416F, 0.0F, 3.1416F);
		

		neck = new ModelRenderer(this);
		neck.setPos(-0.1F, -2.0F, 0.0F);
		wholebody.addChild(neck);
		neck.texOffs(15, 12).addBox(-1.6F, -1.0F, -1.6F, 3.0F, 1.0F, 3.0F, 0.0F, false);

		arm1 = new ModelRenderer(this);
		arm1.setPos(-3.5F, -1.0F, 0.0F);
		wholebody.addChild(arm1);
		arm1.texOffs(0, 21).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		arm2 = new ModelRenderer(this);
		arm2.setPos(3.5F, -1.0F, 0.0F);
		wholebody.addChild(arm2);
		arm2.texOffs(18, 19).addBox(0.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		leg1 = new ModelRenderer(this);
		leg1.setPos(-1.5F, 4.0F, 0.0F);
		wholebody.addChild(leg1);
		leg1.texOffs(8, 21).addBox(-1.0F, 0.0F, -1.1F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		leg2 = new ModelRenderer(this);
		leg2.setPos(1.5F, 4.0F, 0.0F);
		wholebody.addChild(leg2);
		leg2.texOffs(20, 0).addBox(-1.0F, 0.0F, -0.9F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		stomach = new ModelRenderer(this);
		stomach.setPos(0.1F, 1.0F, 0.0F);
		wholebody.addChild(stomach);
		stomach.texOffs(0, 0).addBox(-3.6F, -3.0F, -3.0F, 7.0F, 6.0F, 6.0F, 0.0F, false);

		wholehead = new ModelRenderer(this);
		wholehead.setPos(-0.1F, -2.4F, 0.0F);
		wholebody.addChild(wholehead);
		

		head = new ModelRenderer(this);
		head.setPos(-0.1F, 0.0F, 0.0F);
		wholehead.addChild(head);
		head.texOffs(0, 12).addBox(-2.3F, -4.0F, -2.6F, 5.0F, 4.0F, 5.0F, 0.0F, false);
		head.texOffs(10, 28).addBox(-0.3F, -2.0F, 2.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);

		twig = new ModelRenderer(this);
		twig.setPos(0.1F, -4.0F, -0.1F);
		wholehead.addChild(twig);
		twig.texOffs(0, 0).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		leaves = new ModelRenderer(this);
		leaves.setPos(0.0F, -2.0F, 0.0F);
		twig.addChild(leaves);
		

		leaf1 = new ModelRenderer(this);
		leaf1.setPos(-0.5F, 0.0F, 0.0F);
		leaves.addChild(leaf1);
		leaf1.texOffs(0, 12).addBox(-1.0F, -1.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		leaf2 = new ModelRenderer(this);
		leaf2.setPos(0.5F, 0.0F, 0.0F);
		leaves.addChild(leaf2);
		leaf2.texOffs(0, 3).addBox(0.0F, -1.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setupAnim( PlantGolemEntity entity, float pLimbSwing, float pLimbSwingAmount, float ageInTicks, float pNetHeadYaw, float pHeadPitch){
		//previously the render function, render code was moved to a method below
		this.wholehead.xRot = pHeadPitch * ((float)Math.PI / -180F);
		this.wholehead.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.leg1.xRot = MathHelper.cos(pLimbSwing * 0.6662F + (float)Math.PI) * 1.4F * pLimbSwingAmount;
		this.leg2.xRot = MathHelper.cos(pLimbSwing * 0.6662F) * 1.4F * pLimbSwingAmount;
		this.arm2.xRot = MathHelper.cos(pLimbSwing * 0.6662F + (float)Math.PI) * 1.4F * pLimbSwingAmount;
		this.arm1.xRot = MathHelper.cos(pLimbSwing * 0.6662F) * 1.4F * pLimbSwingAmount;
		if(!entity.getMainHandItem().isEmpty()){
			this.arm2.xRot = arm2.xRot + 0.5f;
		}
		this.twig.xRot = (MathHelper.sin(  entity.animationSpeed * 2.0F) + 0.1F) < 0.0F ? -1 * (MathHelper.sin(  entity.animationSpeed * 2.0F) + 0.1F) :
				(MathHelper.sin(  entity.animationSpeed * 2.0F) + 0.1F);
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

	@Override
	public void translateToHand( HandSide pSide , MatrixStack pMatrixStack ){
		pMatrixStack.scale( 0.5f, 0.5f, 0.5f );
		pMatrixStack.translate( -0.75d, 2.3d, 0.0d );
		pMatrixStack.mulPose( Vector3f.XN.rotationDegrees(60) );
		ModelRenderer dummyarm = new ModelRenderer( this );
		dummyarm.setPos(3.5F, -1.0F, 0.0F);
		dummyarm.xRot = arm1.xRot + 0.5f;
		dummyarm.translateAndRotate(pMatrixStack);
	}
}