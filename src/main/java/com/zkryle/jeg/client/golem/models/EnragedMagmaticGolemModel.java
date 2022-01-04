package com.zkryle.jeg.client.golem.models;// Made with Blockbench 4.1.1
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.zkryle.jeg.JustEnoughGolems;
import com.zkryle.jeg.common.golem.EnragedMagmaticGolemEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;


public class EnragedMagmaticGolemModel<T extends EnragedMagmaticGolemEntity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation( JustEnoughGolems.MOD_ID, "enraged_magmatic_golem"), "enraged_magmatic_golem");
	private final ModelPart corpo;
	private final ModelPart gambasinistra;
	private final ModelPart gambadestra;

	public EnragedMagmaticGolemModel(ModelPart root) {
		this.corpo = root.getChild("corpo");
		this.gambasinistra = root.getChild("gambasinistra");
		this.gambadestra = root.getChild("gambadestra");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition corpo = partdefinition.addOrReplaceChild("corpo", CubeListBuilder.create().texOffs(0, 40).addBox(-8.5F, -17.5F, -5.5F, 17.0F, 9.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(0, 70).addBox(-4.5F, -8.5F, -3.5F, 9.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(43, 26).addBox(-1.2F, -18.5F, -5.1F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 14.0F, 0.5F));

		PartDefinition bone14 = corpo.addOrReplaceChild("bone14", CubeListBuilder.create().texOffs(82, 110).addBox(-0.475F, -4.6453F, -3.0643F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-1.475F, -1.1453F, -3.0643F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(0.525F, -1.1453F, -3.0643F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(0.025F, -3.6453F, -3.0643F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.725F, -3.6456F, -3.5601F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.975F, -3.6456F, -3.0601F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.475F, -3.6453F, -2.5643F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.475F, -1.1453F, -4.0643F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.475F, -1.1453F, -2.0643F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(47, 88).addBox(-0.975F, -1.6453F, -3.5643F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.525F, -16.7698F, 1.6387F, -0.3491F, 0.0F, 0.0F));

		PartDefinition bone2 = corpo.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(82, 110).addBox(-0.5F, -10.2462F, 0.1782F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-1.5F, -6.7462F, 0.1782F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(0.5F, -6.7462F, 0.1782F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(0.0F, -9.2462F, 0.1782F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.75F, -9.2465F, -0.3177F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-1.0F, -9.2465F, 0.1823F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.5F, -9.2462F, 0.6782F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.5F, -6.7462F, -0.8218F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.5F, -6.7462F, 1.1782F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(47, 88).addBox(-1.0F, -7.2462F, -0.3218F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.5F, -14.0F, 1.75F, -0.6981F, 0.0F, 0.0F));

		PartDefinition bone3 = corpo.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(82, 110).addBox(0.5F, -6.2462F, 3.1782F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.5F, -2.7462F, 3.1782F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(1.5F, -2.7462F, 3.1782F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(1.0F, -5.2462F, 3.1782F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(0.25F, -5.2465F, 2.6823F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(0.0F, -5.2465F, 3.1823F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(0.5F, -5.2462F, 3.6782F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(0.5F, -2.7462F, 2.1782F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(0.5F, -2.7462F, 4.1782F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(47, 88).addBox(0.0F, -3.2462F, 2.6782F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.5F, -19.0F, 1.75F, -0.6981F, 0.0F, 0.0F));

		PartDefinition bone10 = corpo.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(82, 110).addBox(-0.475F, -2.5994F, -3.1984F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-1.475F, 0.9006F, -3.1984F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(0.525F, 0.9006F, -3.1984F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(0.025F, -1.5994F, -3.1984F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.725F, -1.5997F, -3.6942F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.975F, -1.5997F, -3.1942F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.475F, -1.5994F, -2.6984F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.475F, 0.9006F, -4.1984F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.475F, 0.9006F, -2.1984F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(47, 88).addBox(-0.975F, 0.4006F, -3.6984F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.475F, -10.5433F, 8.0085F, -1.0908F, 0.0F, 0.0F));

		PartDefinition bone13 = corpo.addOrReplaceChild("bone13", CubeListBuilder.create().texOffs(82, 110).addBox(-0.475F, -4.6453F, -3.0643F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-1.475F, -1.1453F, -3.0643F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(0.525F, -1.1453F, -3.0643F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(0.025F, -3.6453F, -3.0643F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.725F, -3.6456F, -3.5601F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.975F, -3.6456F, -3.0601F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.475F, -3.6453F, -2.5643F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.475F, -1.1453F, -4.0643F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.475F, -1.1453F, -2.0643F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(47, 88).addBox(-0.975F, -1.6453F, -3.5643F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.475F, -16.7698F, 1.6387F, -0.3491F, 0.0F, 0.0F));

		PartDefinition bone15 = corpo.addOrReplaceChild("bone15", CubeListBuilder.create().texOffs(82, 110).addBox(-0.475F, -4.6453F, -3.0643F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-1.475F, -1.1453F, -3.0643F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(0.525F, -1.1453F, -3.0643F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(0.025F, -3.6453F, -3.0643F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.725F, -3.6456F, -3.5601F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.975F, -3.6456F, -3.0601F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.475F, -3.6453F, -2.5643F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.475F, -1.1453F, -4.0643F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.475F, -1.1453F, -2.0643F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(47, 88).addBox(-0.975F, -1.6453F, -3.5643F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.525F, -18.7698F, 4.6387F, -0.3491F, 0.0F, 0.0F));

		PartDefinition bone16 = corpo.addOrReplaceChild("bone16", CubeListBuilder.create().texOffs(82, 110).addBox(8.525F, -4.6453F, -3.0643F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(7.525F, -1.1453F, -3.0643F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(9.525F, -1.1453F, -3.0643F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(9.025F, -3.6453F, -3.0643F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(8.275F, -3.6456F, -3.5601F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(8.025F, -3.6456F, -3.0601F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(8.525F, -3.6453F, -2.5643F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(8.525F, -1.1453F, -4.0643F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(8.525F, -1.1453F, -2.0643F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(47, 88).addBox(8.025F, -1.6453F, -3.5643F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.525F, -18.7698F, 4.6387F, -0.3491F, 0.0F, 0.0F));

		PartDefinition bone7 = corpo.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(82, 110).addBox(-0.475F, -3.2034F, -3.3185F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-1.475F, 0.2966F, -3.3185F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(0.525F, 0.2966F, -3.3185F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(0.025F, -2.2034F, -3.3185F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.725F, -2.2037F, -3.8143F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.975F, -2.2037F, -3.3143F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.475F, -2.2034F, -2.8185F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.475F, 0.2966F, -4.3185F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.475F, 0.2966F, -2.3185F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(47, 88).addBox(-0.975F, -0.2034F, -3.8185F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.525F, -11.6764F, 7.1378F, -0.8727F, 0.0F, 0.0F));

		PartDefinition bone6 = corpo.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(82, 110).addBox(7.025F, -3.2034F, -3.3185F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(6.025F, 0.2966F, -3.3185F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(8.025F, 0.2966F, -3.3185F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(7.525F, -2.2034F, -3.3185F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(6.775F, -2.2037F, -3.8143F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(6.525F, -2.2037F, -3.3143F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(7.025F, -2.2034F, -2.8185F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(7.025F, 0.2966F, -4.3185F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(7.025F, 0.2966F, -2.3185F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(47, 88).addBox(6.525F, -0.2034F, -3.8185F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.025F, -11.6764F, 7.1378F, -0.8727F, 0.0F, 0.0F));

		PartDefinition bone9 = corpo.addOrReplaceChild("bone9", CubeListBuilder.create(), PartPose.offsetAndRotation(3.475F, -13.6647F, -1.5752F, -1.3777F, -0.3185F, 2.0444F));

		PartDefinition cube_r1 = bone9.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(47, 88).addBox(-3.2916F, -1.8809F, -2.7662F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-2.7916F, -1.3809F, -1.2662F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-2.7916F, -1.3809F, -3.2662F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-2.7916F, -3.8809F, -1.7662F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-3.2916F, -3.8812F, -2.262F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-3.0416F, -3.8812F, -2.762F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-2.2916F, -3.8809F, -2.2662F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-1.7916F, -1.3809F, -2.2662F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-3.7916F, -1.3809F, -2.2662F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-2.7916F, -4.8809F, -2.2662F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.0872F, -0.9962F, 3.0543F, 0.0F, 0.0F));

		PartDefinition bone11 = corpo.addOrReplaceChild("bone11", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.525F, -13.6647F, -1.5752F, -1.606F, 0.3692F, -0.6363F));

		PartDefinition cube_r2 = bone11.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(47, 88).addBox(0.855F, -2.0722F, -0.5798F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(1.355F, -1.5722F, 0.9202F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(1.355F, -1.5722F, -1.0798F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(1.355F, -4.0722F, 0.4202F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(0.855F, -4.0725F, -0.0756F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(1.105F, -4.0725F, -0.5756F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(1.855F, -4.0722F, -0.0798F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(2.355F, -1.5722F, -0.0798F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(0.355F, -1.5722F, -0.0798F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(1.355F, -5.0722F, -0.0798F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.0872F, -0.9962F, 3.0543F, 0.0F, 0.0F));

		PartDefinition bone12 = corpo.addOrReplaceChild("bone12", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.275F, -8.6647F, -1.8252F, -1.7397F, 0.1104F, 0.7427F));

		PartDefinition cube_r3 = bone12.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(47, 88).addBox(-2.099F, -2.8904F, -1.1171F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-1.599F, -2.3904F, 0.3829F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-1.599F, -2.3904F, -1.6171F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-1.599F, -4.8904F, -0.1171F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-2.099F, -4.8907F, -0.613F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-1.849F, -4.8907F, -1.113F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-1.099F, -4.8904F, -0.6171F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.599F, -2.3904F, -0.6171F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-2.599F, -2.3904F, -0.6171F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-1.599F, -5.8904F, -0.6171F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.0872F, -0.9962F, 3.0543F, 0.0F, 0.0F));

		PartDefinition bone18 = corpo.addOrReplaceChild("bone18", CubeListBuilder.create(), PartPose.offsetAndRotation(2.975F, -8.6647F, -1.8252F, -1.4019F, -0.1104F, -2.3989F));

		PartDefinition cube_r4 = bone18.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(47, 88).addBox(0.149F, -2.6998F, -3.2949F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(0.649F, -2.1998F, -1.7949F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(0.649F, -2.1998F, -3.7949F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(0.649F, -4.6998F, -2.2949F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(0.149F, -4.7002F, -2.7907F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(0.399F, -4.7002F, -3.2907F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(1.149F, -4.6998F, -2.7949F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(1.649F, -2.1998F, -2.7949F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.351F, -2.1998F, -2.7949F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(0.649F, -5.6998F, -2.7949F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.0872F, -0.9962F, 3.0543F, 0.0F, 0.0F));

		PartDefinition bracciosx = corpo.addOrReplaceChild("bracciosx", CubeListBuilder.create().texOffs(60, 58).addBox(0.0F, -3.0F, -3.0F, 4.0F, 20.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(8.5F, -15.5F, -0.5F));

		PartDefinition bone17 = bracciosx.addOrReplaceChild("bone17", CubeListBuilder.create().texOffs(82, 110).addBox(-0.475F, -4.5323F, -3.114F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-1.475F, -1.0323F, -3.114F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(0.525F, -1.0323F, -3.114F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(0.025F, -3.5323F, -3.114F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.725F, -3.5326F, -3.6098F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.975F, -3.5326F, -3.1098F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.475F, -3.5323F, -2.614F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.475F, -1.0323F, -4.114F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.475F, -1.0323F, -2.114F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(47, 88).addBox(-0.975F, -1.5323F, -3.614F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.975F, 7.3781F, 5.1713F, -0.3927F, 0.0F, 0.0F));

		PartDefinition bone8 = bracciosx.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(82, 110).addBox(-0.475F, -4.5323F, -3.114F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-1.475F, -1.0323F, -3.114F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(0.525F, -1.0323F, -3.114F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(0.025F, -3.5323F, -3.114F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.725F, -3.5326F, -3.6098F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.975F, -3.5326F, -3.1098F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.475F, -3.5323F, -2.614F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.475F, -1.0323F, -4.114F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.475F, -1.0323F, -2.114F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(47, 88).addBox(-0.975F, -1.5323F, -3.614F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.975F, 1.3781F, 5.1713F, -0.3927F, 0.0F, 0.0F));

		PartDefinition bone19 = bracciosx.addOrReplaceChild("bone19", CubeListBuilder.create().texOffs(82, 110).addBox(-0.7844F, -4.6046F, -3.0643F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-1.7844F, -1.1046F, -3.0643F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(0.2156F, -1.1046F, -3.0643F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.2844F, -3.6046F, -3.0643F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-1.0344F, -3.6049F, -3.5601F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-1.2844F, -3.6049F, -3.0601F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.7844F, -3.6046F, -2.5643F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.7844F, -1.1046F, -4.0643F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.7844F, -1.1046F, -2.0643F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(47, 88).addBox(-1.2844F, -1.6046F, -3.5643F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.975F, -1.2698F, 2.1387F, -0.3381F, 0.0886F, 0.2467F));

		PartDefinition bracciodx = corpo.addOrReplaceChild("bracciodx", CubeListBuilder.create().texOffs(60, 58).addBox(-4.0F, -3.0F, -3.0F, 4.0F, 20.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.5F, -15.5F, -0.5F));

		PartDefinition bone20 = bracciodx.addOrReplaceChild("bone20", CubeListBuilder.create().texOffs(82, 110).addBox(-0.1156F, -4.59F, -3.0643F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-1.1156F, -1.09F, -3.0643F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(0.8844F, -1.09F, -3.0643F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(0.3844F, -3.59F, -3.0643F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.3656F, -3.5903F, -3.5601F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.6156F, -3.5903F, -3.0601F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.1156F, -3.59F, -2.5643F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.1156F, -1.09F, -4.0643F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-0.1156F, -1.09F, -2.0643F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(47, 88).addBox(-0.6156F, -1.59F, -3.5643F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.025F, -1.2698F, 2.1387F, -0.3341F, -0.103F, -0.288F));

		PartDefinition bone4 = bracciodx.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(82, 110).addBox(-21.475F, -4.5323F, -3.114F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-22.475F, -1.0323F, -3.114F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-20.475F, -1.0323F, -3.114F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-20.975F, -3.5323F, -3.114F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-21.725F, -3.5326F, -3.6098F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-21.975F, -3.5326F, -3.1098F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-21.475F, -3.5323F, -2.614F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-21.475F, -1.0323F, -4.114F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-21.475F, -1.0323F, -2.114F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(47, 88).addBox(-21.975F, -1.5323F, -3.614F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(18.975F, 1.3781F, 5.1713F, -0.3927F, 0.0F, 0.0F));

		PartDefinition bone5 = bracciodx.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(82, 110).addBox(-21.475F, -1.5323F, -3.114F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-22.475F, 1.9677F, -3.114F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-20.475F, 1.9677F, -3.114F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-20.975F, -0.5323F, -3.114F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-21.725F, -0.5326F, -3.6098F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-21.975F, -0.5326F, -3.1098F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-21.475F, -0.5323F, -2.614F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-21.475F, 1.9677F, -4.114F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 110).addBox(-21.475F, 1.9677F, -2.114F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(47, 88).addBox(-21.975F, 1.4677F, -3.614F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(18.975F, 5.3781F, 6.1713F, -0.3927F, 0.0F, 0.0F));

		PartDefinition head = corpo.addOrReplaceChild("head", CubeListBuilder.create().texOffs(16, 23).addBox(-2.3812F, -3.868F, -2.48F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(4, 36).addBox(-0.3812F, -1.868F, 2.12F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2188F, -18.532F, -3.38F, -3.1416F, 0.0F, 0.0F));

		PartDefinition cube_r5 = head.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(4, 24).addBox(-3.1526F, -2.5428F, 2.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0812F, -4.868F, -1.88F, 0.0F, 0.0F, -0.1309F));

		PartDefinition cube_r6 = head.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(4, 24).addBox(2.0587F, -1.8149F, 2.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0812F, -4.868F, -1.88F, 0.0F, 0.0F, 0.48F));

		PartDefinition cube_r7 = head.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(4, 24).addBox(-2.8087F, -1.5649F, 2.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0812F, -4.868F, -1.88F, 0.0F, 0.0F, -0.48F));

		PartDefinition gambasinistra = partdefinition.addOrReplaceChild("gambasinistra", CubeListBuilder.create().texOffs(60, 5).addBox(-0.5F, -2.0F, -2.5F, 6.0F, 11.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 15.0F, 0.0F));

		PartDefinition gambadestra = partdefinition.addOrReplaceChild("gambadestra", CubeListBuilder.create().texOffs(60, 5).addBox(-5.5F, -2.0F, -2.5F, 6.0F, 11.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 15.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
		this.corpo.getChild( "head" ).yRot = (float) (netHeadYaw * (Math.PI / 180F) + Math.PI);
		this.corpo.getChild( "head" ).xRot = entity.headInclination >= 0.0F ? headPitch * ((float) Math.PI / -180F) : entity.headInclination;
		this.gambasinistra.xRot = entity.isOn() ? -1.2F * Mth.triangleWave( limbSwing , 15.0F ) * limbSwingAmount : 0.0F;
		this.gambadestra.xRot = entity.isOn() ?  1.2F * Mth.triangleWave( limbSwing , 15.0F ) * limbSwingAmount : 0.0F;
		this.gambasinistra.yRot = 0.0F;
		this.gambadestra.yRot = 0.0F;
		this.corpo.xRot = entity.bodyInclination + entity.altBodyInclination;
		if(entity.bodyInclination > 0){
			this.corpo.getChild( "bracciodx" ).xRot = -entity.bodyInclination;
			this.corpo.getChild( "bracciosx" ).xRot = -entity.bodyInclination;
		}
	}

	@Override
	public void prepareMobModel( T pEntity , float pLimbSwing , float pLimbSwingAmount , float pPartialTick ){
		int i = pEntity.getAttackAnimationTick();
		int j = pEntity.getRangedAttackAnimationTick();
		if(j > 0){
			this.corpo.getChild( "bracciodx" ).xRot = -pEntity.altBodyInclination - (j/100.0F)*5F;
			this.corpo.getChild( "bracciosx" ).xRot = -pEntity.altBodyInclination - (j/100.0F)*5F;
		}
		else if(i > 0){
			this.corpo.getChild( "bracciodx" ).xRot = -2.0F + 1.2F * Mth.triangleWave( (float) i - pPartialTick , 15.0F );
			this.corpo.getChild( "bracciosx" ).xRot = -2.0F + 1.2F * Mth.triangleWave( (float) i - pPartialTick , 15.0F );
		}else{
			this.corpo.getChild( "bracciodx" ).xRot = (-0.2F - 1.2F * Mth.triangleWave( pLimbSwing , 15.0F )) * pLimbSwingAmount;
			this.corpo.getChild( "bracciosx" ).xRot = (-0.2F + 1.2F * Mth.triangleWave( pLimbSwing , 15.0F )) * pLimbSwingAmount;
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		poseStack.scale( 1.3f , 1.3f , 1.3f );
		poseStack.translate( 0.0f , -0.35f , 0.0f );
		corpo.render(poseStack, buffer, packedLight, packedOverlay);
		gambasinistra.render(poseStack, buffer, packedLight, packedOverlay);
		gambadestra.render(poseStack, buffer, packedLight, packedOverlay);
	}
}