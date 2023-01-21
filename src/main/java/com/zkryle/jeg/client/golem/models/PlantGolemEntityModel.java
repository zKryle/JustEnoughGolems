package com.zkryle.jeg.client.golem.models;// Made with Blockbench 4.0.5
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.zkryle.jeg.JustEnoughGolems;
import com.zkryle.jeg.common.golem.PlantGolemEntity;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import org.joml.AxisAngle4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class PlantGolemEntityModel<T extends PlantGolemEntity> extends EntityModel <T> implements ArmedModel{
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation( JustEnoughGolems.MOD_ID, "plant_golem"), "plant_golem");
	private final ModelPart neck;
	private final ModelPart wholehead;
	private final ModelPart stomach;
	private final ModelPart leg2;
	private final ModelPart leg1;
	private final ModelPart arm2;
	private final ModelPart arm1;

	public PlantGolemEntityModel(ModelPart root) {
		this.neck = root.getChild("neck");
		this.wholehead = root.getChild("wholehead");
		this.stomach = root.getChild("stomach");
		this.leg2 = root.getChild("leg2");
		this.leg1 = root.getChild("leg1");
		this.arm2 = root.getChild("arm2");
		this.arm1 = root.getChild("arm1");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition neck = partdefinition.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(15, 12).addBox(-1.6F, -1.0F, -1.6F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.6F, 16.0F, 0.0F));

		PartDefinition wholehead = partdefinition.addOrReplaceChild("wholehead", CubeListBuilder.create(), PartPose.offset(-0.6F, 15.6F, 0.0F));

		PartDefinition head = wholehead.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 12).addBox(-2.3F, -4.0F, -2.6F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(10, 28).addBox(-0.3F, -2.0F, 2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.1F, 0.0F, 0.0F));

		PartDefinition twig = wholehead.addOrReplaceChild("twig", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.1F, -4.0F, -0.1F));

		PartDefinition leaves = twig.addOrReplaceChild("leaves", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, 0.0F));

		PartDefinition leaf1 = leaves.addOrReplaceChild("leaf1", CubeListBuilder.create().texOffs(0, 12).addBox(-1.0F, -1.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 0.0F, 0.0F));

		PartDefinition leaf2 = leaves.addOrReplaceChild("leaf2", CubeListBuilder.create().texOffs(0, 3).addBox(0.0F, -1.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 0.0F, 0.0F));

		PartDefinition stomach = partdefinition.addOrReplaceChild("stomach", CubeListBuilder.create().texOffs(0, 0).addBox(-3.6F, -3.0F, -3.0F, 7.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.4F, 19.0F, 0.0F));

		PartDefinition leg2 = partdefinition.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(20, 0).addBox(-1.0F, 0.0F, -0.9F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 22.0F, 0.0F));

		PartDefinition leg1 = partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(8, 21).addBox(-1.0F, 0.0F, -1.1F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 22.0F, 0.0F));

		PartDefinition arm2 = partdefinition.addOrReplaceChild("arm2", CubeListBuilder.create().texOffs(18, 19).addBox(0.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 17.0F, 0.0F));

		PartDefinition arm1 = partdefinition.addOrReplaceChild("arm1", CubeListBuilder.create().texOffs(0, 21).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 17.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of(this.stomach, this.leg1, this.leg2, this.arm1, this.arm2, this.neck, this.wholehead);
	}

	@Override
	public void setupAnim(T entity, float pLimbSwing, float pLimbSwingAmount, float ageInTicks, float pNetHeadYaw, float pHeadPitch) {
		this.wholehead.xRot = pHeadPitch * ((float)Math.PI / -180F);
		this.wholehead.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.leg1.xRot = Mth.cos(pLimbSwing * 0.6662F + (float)Math.PI) * 1.4F * pLimbSwingAmount;
		this.leg2.xRot = Mth.cos(pLimbSwing * 0.6662F) * 1.4F * pLimbSwingAmount;
		this.arm2.xRot = Mth.cos(pLimbSwing * 0.6662F + (float)Math.PI) * 1.4F * pLimbSwingAmount + (!entity.getMainHandItem().isEmpty() ? 0.5F : 0F);
		this.arm1.xRot = Mth.cos(pLimbSwing * 0.6662F) * 1.4F * pLimbSwingAmount;
		this.wholehead.getChild( "twig" ).xRot = (Mth.sin(  entity.animationSpeed * 2.0F) + 0.1F) < 0.0F ? -1 * (Mth.sin(  entity.animationSpeed * 2.0F) + 0.1F) :
				(Mth.sin(  entity.animationSpeed * 2.0F) + 0.1F);
	}

	@Override
	public void renderToBuffer( PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		poseStack.scale( 1.5f, 1.5f, 1.5f );
		poseStack.translate( -0.025f, -0.5f, 0.0f );
		poseStack.mulPose( new Quaternionf(new AxisAngle4f((float)Math.toRadians(180.0F), 0, 1, 0 )));
		bodyParts().forEach( part -> part.render(poseStack, buffer, packedLight, packedOverlay));
	}

	@Override
	public void translateToHand( HumanoidArm humanoidArm , PoseStack poseStack ){
		arm2.translateAndRotate(poseStack);
		poseStack.scale( 0.5f, 0.5f, 0.5f );
		poseStack.translate( 0.2d, 1.3d, -0.1d );
		poseStack.mulPose( new Quaternionf(new AxisAngle4f((float)Math.toRadians(180.0F), -1, 0, 0 )));

	}
}