package com.zkryle.jeg.client.golem.models;// Made with Blockbench 4.0.5
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.zkryle.jeg.common.golem.EnragedMagmaticGolemEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class EnragedMagmaticGolemModel<T extends EnragedMagmaticGolemEntity> extends EntityModel <T>{
    private final ModelRenderer corpo;
    private final ModelRenderer bone14;
    private final ModelRenderer bone2;
    private final ModelRenderer bone3;
    private final ModelRenderer bone10;
    private final ModelRenderer bone13;
    private final ModelRenderer bone15;
    private final ModelRenderer bone16;
    private final ModelRenderer bone7;
    private final ModelRenderer bone6;
    private final ModelRenderer bone9;
    private final ModelRenderer cube_r1;
    private final ModelRenderer bone11;
    private final ModelRenderer cube_r2;
    private final ModelRenderer bone12;
    private final ModelRenderer cube_r3;
    private final ModelRenderer bone18;
    private final ModelRenderer cube_r4;
    private final ModelRenderer bracciodx;
    private final ModelRenderer bone20;
    private final ModelRenderer bone4;
    private final ModelRenderer bone5;
    private final ModelRenderer head;
    private final ModelRenderer cube_r5;
    private final ModelRenderer cube_r6;
    private final ModelRenderer cube_r7;
    private final ModelRenderer bracciosx;
    private final ModelRenderer bone17;
    private final ModelRenderer bone8;
    private final ModelRenderer bone19;
    private final ModelRenderer gambasinistra;
    private final ModelRenderer gambadestra;

    public EnragedMagmaticGolemModel(){
        texWidth = 128;
        texHeight = 128;

        corpo = new ModelRenderer( this );
        corpo.setPos( 0.0F , 14.0F , 0.5F );
        corpo.texOffs( 0 , 40 ).addBox( -8.5F , -17.5F , -5.5F , 17.0F , 9.0F , 11.0F , 0.0F , false );
        corpo.texOffs( 0 , 70 ).addBox( -4.5F , -8.5F , -3.5F , 9.0F , 8.0F , 6.0F , 0.0F , false );
        corpo.texOffs( 43 , 26 ).addBox( -1.2F , -18.5F , -5.1F , 3.0F , 1.0F , 3.0F , 0.0F , false );

        bone14 = new ModelRenderer( this );
        bone14.setPos( -4.525F , -16.7698F , 1.6387F );
        corpo.addChild( bone14 );
        setRotationAngle( bone14 , -0.3491F , 0.0F , 0.0F );
        bone14.texOffs( 82 , 110 ).addBox( -0.475F , -4.6453F , -3.0643F , 1.0F , 3.0F , 1.0F , 0.0F , false );
        bone14.texOffs( 82 , 110 ).addBox( -1.475F , -1.1453F , -3.0643F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone14.texOffs( 82 , 110 ).addBox( 0.525F , -1.1453F , -3.0643F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone14.texOffs( 82 , 110 ).addBox( 0.025F , -3.6453F , -3.0643F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone14.texOffs( 82 , 110 ).addBox( -0.725F , -3.6456F , -3.5601F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone14.texOffs( 82 , 110 ).addBox( -0.975F , -3.6456F , -3.0601F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone14.texOffs( 82 , 110 ).addBox( -0.475F , -3.6453F , -2.5643F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone14.texOffs( 82 , 110 ).addBox( -0.475F , -1.1453F , -4.0643F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        bone14.texOffs( 82 , 110 ).addBox( -0.475F , -1.1453F , -2.0643F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        bone14.texOffs( 47 , 88 ).addBox( -0.975F , -1.6453F , -3.5643F , 2.0F , 4.0F , 2.0F , 0.0F , false );

        bone2 = new ModelRenderer( this );
        bone2.setPos( 6.5F , -14.0F , 1.75F );
        corpo.addChild( bone2 );
        setRotationAngle( bone2 , -0.6981F , 0.0F , 0.0F );
        bone2.texOffs( 82 , 110 ).addBox( -0.5F , -10.2462F , 0.1782F , 1.0F , 3.0F , 1.0F , 0.0F , false );
        bone2.texOffs( 82 , 110 ).addBox( -1.5F , -6.7462F , 0.1782F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone2.texOffs( 82 , 110 ).addBox( 0.5F , -6.7462F , 0.1782F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone2.texOffs( 82 , 110 ).addBox( 0.0F , -9.2462F , 0.1782F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone2.texOffs( 82 , 110 ).addBox( -0.75F , -9.2465F , -0.3177F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone2.texOffs( 82 , 110 ).addBox( -1.0F , -9.2465F , 0.1823F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone2.texOffs( 82 , 110 ).addBox( -0.5F , -9.2462F , 0.6782F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone2.texOffs( 82 , 110 ).addBox( -0.5F , -6.7462F , -0.8218F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        bone2.texOffs( 82 , 110 ).addBox( -0.5F , -6.7462F , 1.1782F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        bone2.texOffs( 47 , 88 ).addBox( -1.0F , -7.2462F , -0.3218F , 2.0F , 4.0F , 2.0F , 0.0F , false );

        bone3 = new ModelRenderer( this );
        bone3.setPos( -7.5F , -19.0F , 1.75F );
        corpo.addChild( bone3 );
        setRotationAngle( bone3 , -0.6981F , 0.0F , 0.0F );
        bone3.texOffs( 82 , 110 ).addBox( 0.5F , -6.2462F , 3.1782F , 1.0F , 3.0F , 1.0F , 0.0F , false );
        bone3.texOffs( 82 , 110 ).addBox( -0.5F , -2.7462F , 3.1782F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone3.texOffs( 82 , 110 ).addBox( 1.5F , -2.7462F , 3.1782F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone3.texOffs( 82 , 110 ).addBox( 1.0F , -5.2462F , 3.1782F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone3.texOffs( 82 , 110 ).addBox( 0.25F , -5.2465F , 2.6823F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone3.texOffs( 82 , 110 ).addBox( 0.0F , -5.2465F , 3.1823F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone3.texOffs( 82 , 110 ).addBox( 0.5F , -5.2462F , 3.6782F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone3.texOffs( 82 , 110 ).addBox( 0.5F , -2.7462F , 2.1782F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        bone3.texOffs( 82 , 110 ).addBox( 0.5F , -2.7462F , 4.1782F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        bone3.texOffs( 47 , 88 ).addBox( 0.0F , -3.2462F , 2.6782F , 2.0F , 4.0F , 2.0F , 0.0F , false );

        bone10 = new ModelRenderer( this );
        bone10.setPos( 0.475F , -10.5433F , 8.0085F );
        corpo.addChild( bone10 );
        setRotationAngle( bone10 , -1.0908F , 0.0F , 0.0F );
        bone10.texOffs( 82 , 110 ).addBox( -0.475F , -2.5994F , -3.1984F , 1.0F , 3.0F , 1.0F , 0.0F , false );
        bone10.texOffs( 82 , 110 ).addBox( -1.475F , 0.9006F , -3.1984F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone10.texOffs( 82 , 110 ).addBox( 0.525F , 0.9006F , -3.1984F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone10.texOffs( 82 , 110 ).addBox( 0.025F , -1.5994F , -3.1984F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone10.texOffs( 82 , 110 ).addBox( -0.725F , -1.5997F , -3.6942F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone10.texOffs( 82 , 110 ).addBox( -0.975F , -1.5997F , -3.1942F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone10.texOffs( 82 , 110 ).addBox( -0.475F , -1.5994F , -2.6984F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone10.texOffs( 82 , 110 ).addBox( -0.475F , 0.9006F , -4.1984F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        bone10.texOffs( 82 , 110 ).addBox( -0.475F , 0.9006F , -2.1984F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        bone10.texOffs( 47 , 88 ).addBox( -0.975F , 0.4006F , -3.6984F , 2.0F , 4.0F , 2.0F , 0.0F , false );

        bone13 = new ModelRenderer( this );
        bone13.setPos( 4.475F , -16.7698F , 1.6387F );
        corpo.addChild( bone13 );
        setRotationAngle( bone13 , -0.3491F , 0.0F , 0.0F );
        bone13.texOffs( 82 , 110 ).addBox( -0.475F , -4.6453F , -3.0643F , 1.0F , 3.0F , 1.0F , 0.0F , false );
        bone13.texOffs( 82 , 110 ).addBox( -1.475F , -1.1453F , -3.0643F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone13.texOffs( 82 , 110 ).addBox( 0.525F , -1.1453F , -3.0643F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone13.texOffs( 82 , 110 ).addBox( 0.025F , -3.6453F , -3.0643F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone13.texOffs( 82 , 110 ).addBox( -0.725F , -3.6456F , -3.5601F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone13.texOffs( 82 , 110 ).addBox( -0.975F , -3.6456F , -3.0601F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone13.texOffs( 82 , 110 ).addBox( -0.475F , -3.6453F , -2.5643F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone13.texOffs( 82 , 110 ).addBox( -0.475F , -1.1453F , -4.0643F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        bone13.texOffs( 82 , 110 ).addBox( -0.475F , -1.1453F , -2.0643F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        bone13.texOffs( 47 , 88 ).addBox( -0.975F , -1.6453F , -3.5643F , 2.0F , 4.0F , 2.0F , 0.0F , false );

        bone15 = new ModelRenderer( this );
        bone15.setPos( -4.525F , -18.7698F , 4.6387F );
        corpo.addChild( bone15 );
        setRotationAngle( bone15 , -0.3491F , 0.0F , 0.0F );
        bone15.texOffs( 82 , 110 ).addBox( -0.475F , -4.6453F , -3.0643F , 1.0F , 3.0F , 1.0F , 0.0F , false );
        bone15.texOffs( 82 , 110 ).addBox( -1.475F , -1.1453F , -3.0643F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone15.texOffs( 82 , 110 ).addBox( 0.525F , -1.1453F , -3.0643F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone15.texOffs( 82 , 110 ).addBox( 0.025F , -3.6453F , -3.0643F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone15.texOffs( 82 , 110 ).addBox( -0.725F , -3.6456F , -3.5601F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone15.texOffs( 82 , 110 ).addBox( -0.975F , -3.6456F , -3.0601F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone15.texOffs( 82 , 110 ).addBox( -0.475F , -3.6453F , -2.5643F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone15.texOffs( 82 , 110 ).addBox( -0.475F , -1.1453F , -4.0643F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        bone15.texOffs( 82 , 110 ).addBox( -0.475F , -1.1453F , -2.0643F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        bone15.texOffs( 47 , 88 ).addBox( -0.975F , -1.6453F , -3.5643F , 2.0F , 4.0F , 2.0F , 0.0F , false );

        bone16 = new ModelRenderer( this );
        bone16.setPos( -4.525F , -18.7698F , 4.6387F );
        corpo.addChild( bone16 );
        setRotationAngle( bone16 , -0.3491F , 0.0F , 0.0F );
        bone16.texOffs( 82 , 110 ).addBox( 8.525F , -4.6453F , -3.0643F , 1.0F , 3.0F , 1.0F , 0.0F , false );
        bone16.texOffs( 82 , 110 ).addBox( 7.525F , -1.1453F , -3.0643F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone16.texOffs( 82 , 110 ).addBox( 9.525F , -1.1453F , -3.0643F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone16.texOffs( 82 , 110 ).addBox( 9.025F , -3.6453F , -3.0643F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone16.texOffs( 82 , 110 ).addBox( 8.275F , -3.6456F , -3.5601F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone16.texOffs( 82 , 110 ).addBox( 8.025F , -3.6456F , -3.0601F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone16.texOffs( 82 , 110 ).addBox( 8.525F , -3.6453F , -2.5643F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone16.texOffs( 82 , 110 ).addBox( 8.525F , -1.1453F , -4.0643F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        bone16.texOffs( 82 , 110 ).addBox( 8.525F , -1.1453F , -2.0643F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        bone16.texOffs( 47 , 88 ).addBox( 8.025F , -1.6453F , -3.5643F , 2.0F , 4.0F , 2.0F , 0.0F , false );

        bone7 = new ModelRenderer( this );
        bone7.setPos( -3.525F , -11.6764F , 7.1378F );
        corpo.addChild( bone7 );
        setRotationAngle( bone7 , -0.8727F , 0.0F , 0.0F );
        bone7.texOffs( 82 , 110 ).addBox( -0.475F , -3.2034F , -3.3185F , 1.0F , 3.0F , 1.0F , 0.0F , false );
        bone7.texOffs( 82 , 110 ).addBox( -1.475F , 0.2966F , -3.3185F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone7.texOffs( 82 , 110 ).addBox( 0.525F , 0.2966F , -3.3185F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone7.texOffs( 82 , 110 ).addBox( 0.025F , -2.2034F , -3.3185F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone7.texOffs( 82 , 110 ).addBox( -0.725F , -2.2037F , -3.8143F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone7.texOffs( 82 , 110 ).addBox( -0.975F , -2.2037F , -3.3143F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone7.texOffs( 82 , 110 ).addBox( -0.475F , -2.2034F , -2.8185F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone7.texOffs( 82 , 110 ).addBox( -0.475F , 0.2966F , -4.3185F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        bone7.texOffs( 82 , 110 ).addBox( -0.475F , 0.2966F , -2.3185F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        bone7.texOffs( 47 , 88 ).addBox( -0.975F , -0.2034F , -3.8185F , 2.0F , 4.0F , 2.0F , 0.0F , false );

        bone6 = new ModelRenderer( this );
        bone6.setPos( -3.025F , -11.6764F , 7.1378F );
        corpo.addChild( bone6 );
        setRotationAngle( bone6 , -0.8727F , 0.0F , 0.0F );
        bone6.texOffs( 82 , 110 ).addBox( 7.025F , -3.2034F , -3.3185F , 1.0F , 3.0F , 1.0F , 0.0F , false );
        bone6.texOffs( 82 , 110 ).addBox( 6.025F , 0.2966F , -3.3185F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone6.texOffs( 82 , 110 ).addBox( 8.025F , 0.2966F , -3.3185F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone6.texOffs( 82 , 110 ).addBox( 7.525F , -2.2034F , -3.3185F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone6.texOffs( 82 , 110 ).addBox( 6.775F , -2.2037F , -3.8143F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone6.texOffs( 82 , 110 ).addBox( 6.525F , -2.2037F , -3.3143F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone6.texOffs( 82 , 110 ).addBox( 7.025F , -2.2034F , -2.8185F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone6.texOffs( 82 , 110 ).addBox( 7.025F , 0.2966F , -4.3185F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        bone6.texOffs( 82 , 110 ).addBox( 7.025F , 0.2966F , -2.3185F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        bone6.texOffs( 47 , 88 ).addBox( 6.525F , -0.2034F , -3.8185F , 2.0F , 4.0F , 2.0F , 0.0F , false );

        bone9 = new ModelRenderer( this );
        bone9.setPos( 3.475F , -13.6647F , -1.5752F );
        corpo.addChild( bone9 );
        setRotationAngle( bone9 , -1.3777F , -0.3185F , 2.0444F );


        cube_r1 = new ModelRenderer( this );
        cube_r1.setPos( 0.0F , -0.0872F , -0.9962F );
        bone9.addChild( cube_r1 );
        setRotationAngle( cube_r1 , 3.0543F , 0.0F , 0.0F );
        cube_r1.texOffs( 47 , 88 ).addBox( -3.2916F , -1.8809F , -2.7662F , 2.0F , 4.0F , 2.0F , 0.0F , false );
        cube_r1.texOffs( 82 , 110 ).addBox( -2.7916F , -1.3809F , -1.2662F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        cube_r1.texOffs( 82 , 110 ).addBox( -2.7916F , -1.3809F , -3.2662F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        cube_r1.texOffs( 82 , 110 ).addBox( -2.7916F , -3.8809F , -1.7662F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        cube_r1.texOffs( 82 , 110 ).addBox( -3.2916F , -3.8812F , -2.262F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        cube_r1.texOffs( 82 , 110 ).addBox( -3.0416F , -3.8812F , -2.762F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        cube_r1.texOffs( 82 , 110 ).addBox( -2.2916F , -3.8809F , -2.2662F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        cube_r1.texOffs( 82 , 110 ).addBox( -1.7916F , -1.3809F , -2.2662F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        cube_r1.texOffs( 82 , 110 ).addBox( -3.7916F , -1.3809F , -2.2662F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        cube_r1.texOffs( 82 , 110 ).addBox( -2.7916F , -4.8809F , -2.2662F , 1.0F , 3.0F , 1.0F , 0.0F , false );

        bone11 = new ModelRenderer( this );
        bone11.setPos( -2.525F , -13.6647F , -1.5752F );
        corpo.addChild( bone11 );
        setRotationAngle( bone11 , -1.606F , 0.3692F , -0.6363F );


        cube_r2 = new ModelRenderer( this );
        cube_r2.setPos( 0.0F , -0.0872F , -0.9962F );
        bone11.addChild( cube_r2 );
        setRotationAngle( cube_r2 , 3.0543F , 0.0F , 0.0F );
        cube_r2.texOffs( 47 , 88 ).addBox( 0.855F , -2.0722F , -0.5798F , 2.0F , 4.0F , 2.0F , 0.0F , false );
        cube_r2.texOffs( 82 , 110 ).addBox( 1.355F , -1.5722F , 0.9202F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        cube_r2.texOffs( 82 , 110 ).addBox( 1.355F , -1.5722F , -1.0798F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        cube_r2.texOffs( 82 , 110 ).addBox( 1.355F , -4.0722F , 0.4202F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        cube_r2.texOffs( 82 , 110 ).addBox( 0.855F , -4.0725F , -0.0756F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        cube_r2.texOffs( 82 , 110 ).addBox( 1.105F , -4.0725F , -0.5756F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        cube_r2.texOffs( 82 , 110 ).addBox( 1.855F , -4.0722F , -0.0798F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        cube_r2.texOffs( 82 , 110 ).addBox( 2.355F , -1.5722F , -0.0798F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        cube_r2.texOffs( 82 , 110 ).addBox( 0.355F , -1.5722F , -0.0798F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        cube_r2.texOffs( 82 , 110 ).addBox( 1.355F , -5.0722F , -0.0798F , 1.0F , 3.0F , 1.0F , 0.0F , false );

        bone12 = new ModelRenderer( this );
        bone12.setPos( -3.275F , -8.6647F , -1.8252F );
        corpo.addChild( bone12 );
        setRotationAngle( bone12 , -1.7397F , 0.1104F , 0.7427F );


        cube_r3 = new ModelRenderer( this );
        cube_r3.setPos( 0.0F , -0.0872F , -0.9962F );
        bone12.addChild( cube_r3 );
        setRotationAngle( cube_r3 , 3.0543F , 0.0F , 0.0F );
        cube_r3.texOffs( 47 , 88 ).addBox( -2.099F , -2.8904F , -1.1171F , 2.0F , 4.0F , 2.0F , 0.0F , false );
        cube_r3.texOffs( 82 , 110 ).addBox( -1.599F , -2.3904F , 0.3829F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        cube_r3.texOffs( 82 , 110 ).addBox( -1.599F , -2.3904F , -1.6171F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        cube_r3.texOffs( 82 , 110 ).addBox( -1.599F , -4.8904F , -0.1171F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        cube_r3.texOffs( 82 , 110 ).addBox( -2.099F , -4.8907F , -0.613F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        cube_r3.texOffs( 82 , 110 ).addBox( -1.849F , -4.8907F , -1.113F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        cube_r3.texOffs( 82 , 110 ).addBox( -1.099F , -4.8904F , -0.6171F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        cube_r3.texOffs( 82 , 110 ).addBox( -0.599F , -2.3904F , -0.6171F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        cube_r3.texOffs( 82 , 110 ).addBox( -2.599F , -2.3904F , -0.6171F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        cube_r3.texOffs( 82 , 110 ).addBox( -1.599F , -5.8904F , -0.6171F , 1.0F , 3.0F , 1.0F , 0.0F , false );

        bone18 = new ModelRenderer( this );
        bone18.setPos( 2.975F , -8.6647F , -1.8252F );
        corpo.addChild( bone18 );
        setRotationAngle( bone18 , -1.4019F , -0.1104F , -2.3989F );


        cube_r4 = new ModelRenderer( this );
        cube_r4.setPos( 0.0F , -0.0872F , -0.9962F );
        bone18.addChild( cube_r4 );
        setRotationAngle( cube_r4 , 3.0543F , 0.0F , 0.0F );
        cube_r4.texOffs( 47 , 88 ).addBox( 0.149F , -2.6998F , -3.2949F , 2.0F , 4.0F , 2.0F , 0.0F , false );
        cube_r4.texOffs( 82 , 110 ).addBox( 0.649F , -2.1998F , -1.7949F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        cube_r4.texOffs( 82 , 110 ).addBox( 0.649F , -2.1998F , -3.7949F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        cube_r4.texOffs( 82 , 110 ).addBox( 0.649F , -4.6998F , -2.2949F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        cube_r4.texOffs( 82 , 110 ).addBox( 0.149F , -4.7002F , -2.7907F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        cube_r4.texOffs( 82 , 110 ).addBox( 0.399F , -4.7002F , -3.2907F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        cube_r4.texOffs( 82 , 110 ).addBox( 1.149F , -4.6998F , -2.7949F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        cube_r4.texOffs( 82 , 110 ).addBox( 1.649F , -2.1998F , -2.7949F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        cube_r4.texOffs( 82 , 110 ).addBox( -0.351F , -2.1998F , -2.7949F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        cube_r4.texOffs( 82 , 110 ).addBox( 0.649F , -5.6998F , -2.7949F , 1.0F , 3.0F , 1.0F , 0.0F , false );

        bracciodx = new ModelRenderer( this );
        bracciodx.setPos( -8.5F , -15.5F , -0.5F );
        corpo.addChild( bracciodx );
        bracciodx.texOffs( 60 , 58 ).addBox( -4.0F , -3.0F , -3.0F , 4.0F , 20.0F , 6.0F , 0.0F , false );

        bone20 = new ModelRenderer( this );
        bone20.setPos( -3.025F , -1.2698F , 2.1387F );
        bracciodx.addChild( bone20 );
        setRotationAngle( bone20 , -0.3341F , -0.103F , -0.288F );
        bone20.texOffs( 82 , 110 ).addBox( -0.1156F , -4.59F , -3.0643F , 1.0F , 3.0F , 1.0F , 0.0F , false );
        bone20.texOffs( 82 , 110 ).addBox( -1.1156F , -1.09F , -3.0643F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone20.texOffs( 82 , 110 ).addBox( 0.8844F , -1.09F , -3.0643F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone20.texOffs( 82 , 110 ).addBox( 0.3844F , -3.59F , -3.0643F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone20.texOffs( 82 , 110 ).addBox( -0.3656F , -3.5903F , -3.5601F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone20.texOffs( 82 , 110 ).addBox( -0.6156F , -3.5903F , -3.0601F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone20.texOffs( 82 , 110 ).addBox( -0.1156F , -3.59F , -2.5643F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone20.texOffs( 82 , 110 ).addBox( -0.1156F , -1.09F , -4.0643F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        bone20.texOffs( 82 , 110 ).addBox( -0.1156F , -1.09F , -2.0643F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        bone20.texOffs( 47 , 88 ).addBox( -0.6156F , -1.59F , -3.5643F , 2.0F , 4.0F , 2.0F , 0.0F , false );

        bone4 = new ModelRenderer( this );
        bone4.setPos( 18.975F , 1.3781F , 5.1713F );
        bracciodx.addChild( bone4 );
        setRotationAngle( bone4 , -0.3927F , 0.0F , 0.0F );
        bone4.texOffs( 82 , 110 ).addBox( -21.475F , -4.5323F , -3.114F , 1.0F , 3.0F , 1.0F , 0.0F , false );
        bone4.texOffs( 82 , 110 ).addBox( -22.475F , -1.0323F , -3.114F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone4.texOffs( 82 , 110 ).addBox( -20.475F , -1.0323F , -3.114F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone4.texOffs( 82 , 110 ).addBox( -20.975F , -3.5323F , -3.114F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone4.texOffs( 82 , 110 ).addBox( -21.725F , -3.5326F , -3.6098F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone4.texOffs( 82 , 110 ).addBox( -21.975F , -3.5326F , -3.1098F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone4.texOffs( 82 , 110 ).addBox( -21.475F , -3.5323F , -2.614F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone4.texOffs( 82 , 110 ).addBox( -21.475F , -1.0323F , -4.114F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        bone4.texOffs( 82 , 110 ).addBox( -21.475F , -1.0323F , -2.114F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        bone4.texOffs( 47 , 88 ).addBox( -21.975F , -1.5323F , -3.614F , 2.0F , 4.0F , 2.0F , 0.0F , false );

        bone5 = new ModelRenderer( this );
        bone5.setPos( 18.975F , 5.3781F , 6.1713F );
        bracciodx.addChild( bone5 );
        setRotationAngle( bone5 , -0.3927F , 0.0F , 0.0F );
        bone5.texOffs( 82 , 110 ).addBox( -21.475F , -1.5323F , -3.114F , 1.0F , 3.0F , 1.0F , 0.0F , false );
        bone5.texOffs( 82 , 110 ).addBox( -22.475F , 1.9677F , -3.114F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone5.texOffs( 82 , 110 ).addBox( -20.475F , 1.9677F , -3.114F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone5.texOffs( 82 , 110 ).addBox( -20.975F , -0.5323F , -3.114F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone5.texOffs( 82 , 110 ).addBox( -21.725F , -0.5326F , -3.6098F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone5.texOffs( 82 , 110 ).addBox( -21.975F , -0.5326F , -3.1098F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone5.texOffs( 82 , 110 ).addBox( -21.475F , -0.5323F , -2.614F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone5.texOffs( 82 , 110 ).addBox( -21.475F , 1.9677F , -4.114F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        bone5.texOffs( 82 , 110 ).addBox( -21.475F , 1.9677F , -2.114F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        bone5.texOffs( 47 , 88 ).addBox( -21.975F , 1.4677F , -3.614F , 2.0F , 4.0F , 2.0F , 0.0F , false );

        head = new ModelRenderer( this );
        head.setPos( 0.2188F , -18.532F , -3.38F );
        corpo.addChild( head );
        setRotationAngle( head , 0.0F , 3.1416F , 0.0F );
        head.texOffs( 16 , 23 ).addBox( -2.3812F , -3.868F , -2.48F , 5.0F , 4.0F , 5.0F , 0.0F , false );
        head.texOffs( 4 , 36 ).addBox( -0.3812F , -1.868F , 2.12F , 1.0F , 2.0F , 2.0F , 0.0F , false );

        cube_r5 = new ModelRenderer( this );
        cube_r5.setPos( -0.0812F , -4.868F , -1.88F );
        head.addChild( cube_r5 );
        setRotationAngle( cube_r5 , 0.0F , 0.0F , -0.1309F );
        cube_r5.texOffs( 4 , 24 ).addBox( -3.1526F , -2.5428F , 2.0F , 1.0F , 2.0F , 1.0F , 0.0F , false );

        cube_r6 = new ModelRenderer( this );
        cube_r6.setPos( -0.0812F , -4.868F , -1.88F );
        head.addChild( cube_r6 );
        setRotationAngle( cube_r6 , 0.0F , 0.0F , 0.48F );
        cube_r6.texOffs( 4 , 24 ).addBox( 2.0587F , -1.8149F , 2.0F , 1.0F , 2.0F , 1.0F , 0.0F , false );

        cube_r7 = new ModelRenderer( this );
        cube_r7.setPos( -0.0812F , -4.868F , -1.88F );
        head.addChild( cube_r7 );
        setRotationAngle( cube_r7 , 0.0F , 0.0F , -0.48F );
        cube_r7.texOffs( 4 , 24 ).addBox( -2.8087F , -1.5649F , 2.0F , 1.0F , 2.0F , 1.0F , 0.0F , false );

        bracciosx = new ModelRenderer( this );
        bracciosx.setPos( 8.5F , -15.5F , -0.5F );
        corpo.addChild( bracciosx );
        bracciosx.texOffs( 60 , 58 ).addBox( 0.0F , -3.0F , -3.0F , 4.0F , 20.0F , 6.0F , 0.0F , false );

        bone17 = new ModelRenderer( this );
        bone17.setPos( 1.975F , 7.3781F , 5.1713F );
        bracciosx.addChild( bone17 );
        setRotationAngle( bone17 , -0.3927F , 0.0F , 0.0F );
        bone17.texOffs( 82 , 110 ).addBox( -0.475F , -4.5323F , -3.114F , 1.0F , 3.0F , 1.0F , 0.0F , false );
        bone17.texOffs( 82 , 110 ).addBox( -1.475F , -1.0323F , -3.114F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone17.texOffs( 82 , 110 ).addBox( 0.525F , -1.0323F , -3.114F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone17.texOffs( 82 , 110 ).addBox( 0.025F , -3.5323F , -3.114F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone17.texOffs( 82 , 110 ).addBox( -0.725F , -3.5326F , -3.6098F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone17.texOffs( 82 , 110 ).addBox( -0.975F , -3.5326F , -3.1098F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone17.texOffs( 82 , 110 ).addBox( -0.475F , -3.5323F , -2.614F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone17.texOffs( 82 , 110 ).addBox( -0.475F , -1.0323F , -4.114F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        bone17.texOffs( 82 , 110 ).addBox( -0.475F , -1.0323F , -2.114F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        bone17.texOffs( 47 , 88 ).addBox( -0.975F , -1.5323F , -3.614F , 2.0F , 4.0F , 2.0F , 0.0F , false );

        bone8 = new ModelRenderer( this );
        bone8.setPos( 1.975F , 1.3781F , 5.1713F );
        bracciosx.addChild( bone8 );
        setRotationAngle( bone8 , -0.3927F , 0.0F , 0.0F );
        bone8.texOffs( 82 , 110 ).addBox( -0.475F , -4.5323F , -3.114F , 1.0F , 3.0F , 1.0F , 0.0F , false );
        bone8.texOffs( 82 , 110 ).addBox( -1.475F , -1.0323F , -3.114F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone8.texOffs( 82 , 110 ).addBox( 0.525F , -1.0323F , -3.114F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone8.texOffs( 82 , 110 ).addBox( 0.025F , -3.5323F , -3.114F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone8.texOffs( 82 , 110 ).addBox( -0.725F , -3.5326F , -3.6098F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone8.texOffs( 82 , 110 ).addBox( -0.975F , -3.5326F , -3.1098F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone8.texOffs( 82 , 110 ).addBox( -0.475F , -3.5323F , -2.614F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone8.texOffs( 82 , 110 ).addBox( -0.475F , -1.0323F , -4.114F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        bone8.texOffs( 82 , 110 ).addBox( -0.475F , -1.0323F , -2.114F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        bone8.texOffs( 47 , 88 ).addBox( -0.975F , -1.5323F , -3.614F , 2.0F , 4.0F , 2.0F , 0.0F , false );

        bone19 = new ModelRenderer( this );
        bone19.setPos( 2.975F , -1.2698F , 2.1387F );
        bracciosx.addChild( bone19 );
        setRotationAngle( bone19 , -0.3381F , 0.0886F , 0.2467F );
        bone19.texOffs( 82 , 110 ).addBox( -0.7844F , -4.6046F , -3.0643F , 1.0F , 3.0F , 1.0F , 0.0F , false );
        bone19.texOffs( 82 , 110 ).addBox( -1.7844F , -1.1046F , -3.0643F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone19.texOffs( 82 , 110 ).addBox( 0.2156F , -1.1046F , -3.0643F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone19.texOffs( 82 , 110 ).addBox( -0.2844F , -3.6046F , -3.0643F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone19.texOffs( 82 , 110 ).addBox( -1.0344F , -3.6049F , -3.5601F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone19.texOffs( 82 , 110 ).addBox( -1.2844F , -3.6049F , -3.0601F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone19.texOffs( 82 , 110 ).addBox( -0.7844F , -3.6046F , -2.5643F , 1.0F , 2.0F , 1.0F , 0.0F , false );
        bone19.texOffs( 82 , 110 ).addBox( -0.7844F , -1.1046F , -4.0643F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        bone19.texOffs( 82 , 110 ).addBox( -0.7844F , -1.1046F , -2.0643F , 1.0F , 4.0F , 1.0F , 0.0F , false );
        bone19.texOffs( 47 , 88 ).addBox( -1.2844F , -1.6046F , -3.5643F , 2.0F , 4.0F , 2.0F , 0.0F , false );

        gambasinistra = new ModelRenderer( this );
        gambasinistra.setPos( 2.0F , 15.0F , 0.0F );
        gambasinistra.texOffs( 60 , 5 ).addBox( -0.5F , -2.0F , -2.5F , 6.0F , 11.0F , 5.0F , 0.0F , false );

        gambadestra = new ModelRenderer( this );
        gambadestra.setPos( -2.0F , 15.0F , 0.0F );
        gambadestra.texOffs( 60 , 5 ).addBox( -5.5F , -2.0F , -2.5F , 6.0F , 11.0F , 5.0F , 0.0F , false );
    }

    @Override
    public void setupAnim( EnragedMagmaticGolemEntity entity , float limbSwing , float limbSwingAmount , float ageInTicks , float netHeadYaw , float headPitch ){
        //previously the render function, render code was moved to a method below
        this.head.yRot = (float) (netHeadYaw * (Math.PI / 180F) + Math.PI);
        this.head.xRot = entity.headInclination >= 0.0F ? headPitch * ((float) Math.PI / -180F) : entity.headInclination;
        this.gambasinistra.xRot = entity.isOn() ? -1.2F * MathHelper.triangleWave( limbSwing , 15.0F ) * limbSwingAmount : 0.0F;
        this.gambadestra.xRot = entity.isOn() ?  1.2F * MathHelper.triangleWave( limbSwing , 15.0F ) * limbSwingAmount : 0.0F;
        this.gambasinistra.yRot = 0.0F;
        this.gambadestra.yRot = 0.0F;
        this.corpo.xRot = entity.bodyInclination + entity.altBodyInclination;
        if(entity.bodyInclination > 0){
            this.bracciodx.xRot = -entity.bodyInclination;
            this.bracciosx.xRot = -entity.bodyInclination;
        }
    }

    @Override
    public void renderToBuffer( MatrixStack matrixStack , IVertexBuilder buffer , int packedLight , int packedOverlay , float red , float green , float blue , float alpha ){
        matrixStack.scale( 1.3f , 1.3f , 1.3f );
        matrixStack.translate( 0.0f , -0.35f , 0.0f );
        corpo.render( matrixStack , buffer , packedLight , packedOverlay );
        gambasinistra.render( matrixStack , buffer , packedLight , packedOverlay );
        gambadestra.render( matrixStack , buffer , packedLight , packedOverlay );
    }

    public void prepareMobModel( T pEntity , float pLimbSwing , float pLimbSwingAmount , float pPartialTick ){
        int i = pEntity.getAttackAnimationTick();
        int j = pEntity.getRangedAttackAnimationTick();
        if(j > 0){
            this.bracciodx.xRot = -pEntity.altBodyInclination - (j/100.0F)*5F;
            this.bracciosx.xRot = -pEntity.altBodyInclination - (j/100.0F)*5F;
        }
        else if(i > 0){
            this.bracciodx.xRot = -2.0F + 1.2F * MathHelper.triangleWave( (float) i - pPartialTick , 15.0F );
            this.bracciosx.xRot = -2.0F + 1.2F * MathHelper.triangleWave( (float) i - pPartialTick , 15.0F );
        }else{
            this.bracciodx.xRot = (-0.2F - 1.2F * MathHelper.triangleWave( pLimbSwing , 15.0F )) * pLimbSwingAmount;
            this.bracciosx.xRot = (-0.2F + 1.2F * MathHelper.triangleWave( pLimbSwing , 15.0F )) * pLimbSwingAmount;
        }
    }


    public void setRotationAngle( ModelRenderer modelRenderer , float x , float y , float z ){
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}