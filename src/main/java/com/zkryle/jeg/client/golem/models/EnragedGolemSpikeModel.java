package com.zkryle.jeg.client.golem.models;// Made with Blockbench 4.1.1
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.zkryle.jeg.JustEnoughGolems;
import com.zkryle.jeg.common.golem.EnragedGolemSpikeEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class EnragedGolemSpikeModel<T extends EnragedGolemSpikeEntity> extends EntityModel <T>{
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation( JustEnoughGolems.MOD_ID, "enraged_golem_spike"), "enraged_golem_spike");
	private final ModelPart bone;

	public EnragedGolemSpikeModel(ModelPart root) {
		this.bone = root.getChild("bone");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(-1.0F, 57.5F, -2.0F));

		PartDefinition bone4 = bone.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(82, 110).addBox(-0.475F, -3.4499F, -0.5008F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-1.475F, 0.0501F, -0.5008F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(0.525F, 0.0501F, -0.5008F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(0.025F, -2.4499F, -0.5008F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.725F, -2.4503F, -0.9967F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.975F, -2.4503F, -0.4967F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.475F, -2.4499F, -0.0008F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.475F, 0.0501F, -1.5008F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.475F, 0.0501F, 0.4992F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(47, 88).addBox(-0.975F, -0.4499F, -1.0008F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4645F, -30.4471F, 3.3733F, -0.6449F, -0.0007F, -0.4646F));

		PartDefinition bone3 = bone.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(82, 110).addBox(-1.8504F, 0.5007F, -5.001F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-2.8504F, 4.0007F, -5.001F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.8504F, 4.0007F, -5.001F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-1.3504F, 1.5007F, -5.001F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-2.1004F, 1.5004F, -5.4969F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-2.3504F, 1.5004F, -4.9969F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-1.8504F, 1.5007F, -4.501F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-1.8504F, 4.0007F, -6.001F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-1.8504F, 4.0007F, -4.001F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(47, 88).addBox(-2.3504F, 3.5007F, -5.501F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.5047F, -30.3534F, 1.7417F, -1.9338F, 1.3935F, -1.2082F));

		PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(82, 110).addBox(-4.9741F, -5.5798F, 3.8833F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-5.9741F, -2.0798F, 3.8833F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-3.9741F, -2.0798F, 3.8833F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-4.4741F, -4.5798F, 3.8833F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-5.2241F, -4.5801F, 3.3874F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-5.4741F, -4.5801F, 3.8874F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-4.9741F, -4.5798F, 4.3833F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-4.9741F, -2.0798F, 2.8833F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-4.9741F, -2.0798F, 4.8833F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(47, 88).addBox(-5.4741F, -2.5798F, 3.3833F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.475F, -30.5388F, 0.2765F, 0.5989F, -0.6051F, -0.7826F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer( PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bone.render(poseStack, buffer, packedLight, packedOverlay);
	}
}