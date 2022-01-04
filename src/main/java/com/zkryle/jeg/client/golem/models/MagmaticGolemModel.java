package com.zkryle.jeg.client.golem.models;// Made with Blockbench 4.1.1
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.zkryle.jeg.JustEnoughGolems;
import com.zkryle.jeg.common.golem.MagmaticGolemEntity;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;

public class MagmaticGolemModel<T extends MagmaticGolemEntity> extends EntityModel <T> implements ArmedModel{
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation( JustEnoughGolems.MOD_ID, "magmatic_golem"), "magmatic_golem");
	private final ModelPart wholebody;

	public MagmaticGolemModel( ModelPart root) {
		this.wholebody = root.getChild("wholebody");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition wholebody = partdefinition.addOrReplaceChild("wholebody", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.5F, 18.0F, 0.0F, -3.1416F, 0.0F, 3.1416F));

		PartDefinition neck = wholebody.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 20).addBox(-1.6F, -1.0F, -1.6F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.1F, -2.0F, 0.0F));

		PartDefinition arm1 = wholebody.addOrReplaceChild("arm1", CubeListBuilder.create().texOffs(16, 24).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, -1.0F, 0.0F));

		PartDefinition arm2 = wholebody.addOrReplaceChild("arm2", CubeListBuilder.create().texOffs(24, 24).addBox(0.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, -1.0F, 0.0F));

		PartDefinition leg1 = wholebody.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(8, 24).addBox(-1.0F, 0.0F, -1.1F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 4.0F, 0.0F));

		PartDefinition leg2 = wholebody.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(8, 28).addBox(-1.0F, 0.0F, -0.9F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 4.0F, 0.0F));

		PartDefinition stomach = wholebody.addOrReplaceChild("stomach", CubeListBuilder.create().texOffs(0, 8).addBox(-3.1F, 0.0F, -1.25F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(6, 0).addBox(-4.1F, -3.0F, -2.25F, 8.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.1F, 1.0F, 0.0F));

		PartDefinition wholehead = wholebody.addOrReplaceChild("wholehead", CubeListBuilder.create(), PartPose.offset(-0.1F, -2.4F, 0.0F));

		PartDefinition head = wholehead.addOrReplaceChild("head", CubeListBuilder.create().texOffs(12, 15).addBox(-2.3F, -4.0F, -2.6F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 28).addBox(-0.3F, -2.0F, 2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.1F, 0.0F, 0.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 16).addBox(-2.5F, -7.5F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1309F));

		PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 16).addBox(-0.25F, -6.25F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.48F));

		PartDefinition cube_r3 = head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 16).addBox(-0.5F, -6.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.48F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		boolean flag = entity.getFallFlyingTicks() > 4;
		boolean isOn = entity.isOn();
		this.wholebody.getChild( "wholehead" ).xRot = entity.headInclination >= 0.0F ? headPitch * ((float)Math.PI / -180F) : entity.headInclination;
		this.wholebody.getChild( "wholehead" ).yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.wholebody.getChild( "leg1" ).xRot = isOn ? Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount : 0.0F;
		this.wholebody.getChild( "leg2" ).xRot = isOn ? Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount : 0.0F;
		float f = 1.0F;
		if (flag) {
			f = (float)entity.getDeltaMovement().lengthSqr();
			f = f / 0.2F;
			f = f * f * f;
		}

		if (f < 1.0F) {
			f = 1.0F;
		}
		this.wholebody.getChild( "arm2" ).xRot = isOn ? Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F / -f : 0.0F;
		this.wholebody.getChild( "arm1" ).xRot = isOn ? Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / -f : 0.0F;
		this.wholebody.getChild( "arm2" ).zRot = 0.0F;
		this.wholebody.getChild( "arm1" ).zRot = 0.0F;
		if(!entity.getMainHandItem().isEmpty()){
			this.wholebody.getChild( "arm2" ).xRot = this.wholebody.getChild( "arm2" ).xRot * 0.5F + ((float)Math.PI / 10F);
		}
		setupAttackAnimation( entity, ageInTicks );
	}

	@Override
	public void translateToHand( HumanoidArm pSide , PoseStack pMatrixStack ){
		pMatrixStack.scale( 0.5f, 0.5f, 0.5f );
		pMatrixStack.translate( -0.35d, 2.3d, 0.05d );
		pMatrixStack.mulPose( Vector3f.YP.rotationDegrees( 180.0F ) );
		this.wholebody.getChild( "arm2" ).translateAndRotate(pMatrixStack);
		pMatrixStack.mulPose( Vector3f.YN.rotationDegrees( 180.0F ) );
		pMatrixStack.mulPose( Vector3f.XN.rotationDegrees( 5.0F ) );
	}

	@Override
	public void renderToBuffer( PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		poseStack.scale( 1.5f, 1.5f, 1.5f );
		poseStack.translate( 0.025f, -0.5f, 0.0f );
		wholebody.render(poseStack, buffer, packedLight, packedOverlay);
	}

	protected void setupAttackAnimation(T p_230486_1_, float p_230486_2_){
		if(!(this.attackTime <= 0.0F)){
			float f;
			f = 1.0F - this.attackTime;
			f = f * f;
			f = f * f;
			f = 1.0F - f;
			float f1 = Mth.sin( f * (float) Math.PI );
			float f2 = Mth.sin( this.attackTime * (float) Math.PI ) * -(this.wholebody.getChild( "wholehead" ).xRot - 0.7F) * 0.75F;
			this.wholebody.getChild( "arm2" ).xRot = (float) ((double) this.wholebody.getChild( "arm2" ).xRot + ((double) f1 * 1.2D + (double) f2));
			this.wholebody.getChild( "arm2" ).zRot += Mth.sin( this.attackTime * (float) Math.PI ) * 0.4F;
		}
	}

	@Override
	public void copyPropertiesTo(EntityModel<T> p_217111_1_) {
		p_217111_1_.attackTime = this.attackTime;
		p_217111_1_.riding = this.riding;
		p_217111_1_.young = this.young;
		super.copyPropertiesTo( p_217111_1_ );
	}
}