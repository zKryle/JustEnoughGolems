package com.zkryle.jeg.core;

import com.zkryle.jeg.JustEnoughGolems;
import com.zkryle.jeg.common.golem.MagmaticGolemEntity;
import com.zkryle.jeg.common.golem.PlantGolemEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.audio.Sound;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Init{

    // _-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_- MOD BLOCKS -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_
    public static DeferredRegister <Block> BLOCKS = DeferredRegister.create( ForgeRegistries.BLOCKS , JustEnoughGolems.MOD_ID );

    public static RegistryObject <Block> PRECIOUS_MOSSY_COBBLESTONE = BLOCKS.register( "precious_mossy_cobblestone" ,
            () -> new Block( AbstractBlock.Properties.of( Material.STONE ).requiresCorrectToolForDrops()
                    .strength( 2.0F , 6.0F ) ) );


    // _-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_- MOD ITEMS -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_
    public static DeferredRegister <Item> ITEMS = DeferredRegister.create( ForgeRegistries.ITEMS , JustEnoughGolems.MOD_ID );

    public static RegistryObject <BlockItem> PRECIOUS_MOSSY_COBBLESTONE_ITEM = ITEMS.register( "precious_mossy_cobblestone" ,
            () -> new BlockItem( PRECIOUS_MOSSY_COBBLESTONE.get() ,
                    new Item.Properties().tab( ItemGroup.TAB_BUILDING_BLOCKS ) ) );

    // _-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_- MOD ENTITIES -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_
    public static DeferredRegister <EntityType <?>> ENTITIES = DeferredRegister.create( ForgeRegistries.ENTITIES , JustEnoughGolems.MOD_ID );

    public static RegistryObject <EntityType <PlantGolemEntity>> PLANT_GOLEM_ENTITY = ENTITIES.register( "plant_golem" ,
            () -> EntityType.Builder.of( PlantGolemEntity::new , EntityClassification.CREATURE ).sized( 0.55f , 1.2f)
                    .build( new ResourceLocation( JustEnoughGolems.MOD_ID , "plant_golem" ).toString() ) );
    /*public static RegistryObject <EntityType <MagmaticGolemEntity>> MAGMATIC_GOLEM_ENTITY = ENTITIES.register( "magmatic_golem" ,
            () -> EntityType.Builder.of( MagmaticGolemEntity::new , EntityClassification.CREATURE ).sized( 0.55f , 1.2f)
                    .build( new ResourceLocation( JustEnoughGolems.MOD_ID , "magmatic_golem" ).toString() ) );*/


    // _-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_- MOD SOUNDS -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_
    public static DeferredRegister <SoundEvent> SOUNDS = DeferredRegister.create( ForgeRegistries.SOUND_EVENTS, JustEnoughGolems.MOD_ID );

    public static final RegistryObject<SoundEvent> PLANT_GOLEM_DEATH = SOUNDS.register(
            "entity.plant_golem_death",
            () -> new SoundEvent(new ResourceLocation(JustEnoughGolems.MOD_ID, "entity.plant_golem_death")));

    public static final RegistryObject<SoundEvent> PLANT_GOLEM_HIT = SOUNDS.register(
            "entity.plant_golem_hit",
            () -> new SoundEvent(new ResourceLocation(JustEnoughGolems.MOD_ID, "entity.plant_golem_hit")));

    public static final RegistryObject<SoundEvent> PLANT_GOLEM_STEPS = SOUNDS.register(
            "entity.plant_golem_step",
            () -> new SoundEvent(new ResourceLocation(JustEnoughGolems.MOD_ID, "entity.plant_golem_step")));
}
