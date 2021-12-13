package com.zkryle.jeg.core;

import com.zkryle.jeg.JustEnoughGolems;
import com.zkryle.jeg.common.blocks.MossyObserver;
import com.zkryle.jeg.common.golem.PlantGolemEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Init{

    // _-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_- MOD BLOCKS -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_
    public static DeferredRegister <Block> BLOCKS = DeferredRegister.create( ForgeRegistries.BLOCKS , JustEnoughGolems.MOD_ID );

    public static RegistryObject <Block> PRECIOUS_MOSSY_COBBLESTONE = BLOCKS.register( "precious_mossy_cobblestone" ,
            () -> new Block( BlockBehaviour.Properties.of( Material.STONE ).requiresCorrectToolForDrops()
                    .strength( 2.0F , 6.0F ) ) );
    public static RegistryObject <Block> MOSSY_OBSERVER = BLOCKS.register( "mossy_observer" ,
            () -> new MossyObserver( BlockBehaviour.Properties.copy( Blocks.OBSERVER ) ) );


    // _-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_- MOD ITEMS -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_
    public static DeferredRegister <Item> ITEMS = DeferredRegister.create( ForgeRegistries.ITEMS , JustEnoughGolems.MOD_ID );

    public static RegistryObject <BlockItem> PRECIOUS_MOSSY_COBBLESTONE_ITEM = ITEMS.register( "precious_mossy_cobblestone" ,
            () -> new BlockItem( PRECIOUS_MOSSY_COBBLESTONE.get() ,
                    new Item.Properties().tab( CreativeModeTab.TAB_BUILDING_BLOCKS ) ) );

    public static RegistryObject <BlockItem> MOSSY_OBSERVER_ITEM = ITEMS.register( "mossy_observer" ,
            () -> new BlockItem( MOSSY_OBSERVER.get() ,
                    new Item.Properties().tab( CreativeModeTab.TAB_REDSTONE ) ) );

    // _-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_- MOD ENTITIES -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_
    public static DeferredRegister <EntityType <?>> ENTITIES = DeferredRegister.create( ForgeRegistries.ENTITIES , JustEnoughGolems.MOD_ID );

    public static RegistryObject <EntityType <PlantGolemEntity>> PLANT_GOLEM_ENTITY = ENTITIES.register( "plant_golem" ,
            () -> EntityType.Builder.of( PlantGolemEntity::new , MobCategory.CREATURE ).sized( 0.55f , 1.2f)
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

    public static final RegistryObject <SoundEvent> PLANT_GOLEM_STEPS = SOUNDS.register(
            "entity.plant_golem_step",
            () -> new SoundEvent(new ResourceLocation(JustEnoughGolems.MOD_ID, "entity.plant_golem_step")));
}
