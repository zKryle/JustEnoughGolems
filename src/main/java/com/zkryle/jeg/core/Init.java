package com.zkryle.jeg.core;

import com.zkryle.jeg.JustEnoughGolems;
import com.zkryle.jeg.common.blocks.ChargingTableBlock;
import com.zkryle.jeg.common.blocks.MagmaticObsidianBlock;
import com.zkryle.jeg.common.blocks.MossyObserver;
import com.zkryle.jeg.common.golem.EnragedGolemSpikeEntity;
import com.zkryle.jeg.common.golem.EnragedMagmaticGolemEntity;
import com.zkryle.jeg.common.golem.MagmaticGolemEntity;
import com.zkryle.jeg.common.golem.PlantGolemEntity;
import com.zkryle.jeg.common.items.MagmaticCharm;
import com.zkryle.jeg.common.tileentities.ChargingTableBlockEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.zkryle.jeg.core.JEGItemGroup.JEG_ITEM_GROUP;

public class Init{

    // _-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_- MOD BLOCKS -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_
    public static DeferredRegister <Block> BLOCKS = DeferredRegister.create( ForgeRegistries.BLOCKS , JustEnoughGolems.MOD_ID );

    public static RegistryObject <Block> PRECIOUS_MOSSY_COBBLESTONE = BLOCKS.register( "precious_mossy_cobblestone" ,
            () -> new Block( BlockBehaviour.Properties.of( Material.STONE ).requiresCorrectToolForDrops()
                    .strength( 2.0F , 6.0F ) ) );

    public static RegistryObject <Block> MAGMATIC_OBSIDIAN = BLOCKS.register( "magmatic_obsidian" ,
            () -> new MagmaticObsidianBlock( BlockBehaviour.Properties.copy( Blocks.CRYING_OBSIDIAN )));

    public static RegistryObject <Block> MOSSY_OBSERVER = BLOCKS.register( "mossy_observer" ,
            () -> new MossyObserver( BlockBehaviour.Properties.copy(Blocks.OBSERVER) ) );

    public static RegistryObject <Block> CHARGING_TABLE = BLOCKS.register( "charging_table" ,
            () -> new ChargingTableBlock( BlockBehaviour.Properties.copy(Blocks.OBSIDIAN) ) );


    // _-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_- MOD TILE ENTITIES -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_
    public static DeferredRegister <BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create( ForgeRegistries.BLOCK_ENTITIES , JustEnoughGolems.MOD_ID );

    public static final RegistryObject < BlockEntityType <ChargingTableBlockEntity> > CHARGING_TABLE_BLOCK_ENTITY_TYPE = BLOCK_ENTITY_TYPES
            .register("charging_table", () -> BlockEntityType.Builder.of( ChargingTableBlockEntity::new,
                    CHARGING_TABLE.get ()).build(null));

    // _-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_- MOD ITEMS -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_
    public static DeferredRegister <Item> ITEMS = DeferredRegister.create( ForgeRegistries.ITEMS , JustEnoughGolems.MOD_ID );

    public static RegistryObject <BlockItem> PRECIOUS_MOSSY_COBBLESTONE_ITEM = ITEMS.register( "precious_mossy_cobblestone" ,
            () -> new BlockItem( PRECIOUS_MOSSY_COBBLESTONE.get() ,
                    new Item.Properties().tab( CreativeModeTab.TAB_BUILDING_BLOCKS ).tab( JEG_ITEM_GROUP ) ) );

    public static RegistryObject <BlockItem> MOSSY_OBSERVER_ITEM = ITEMS.register( "mossy_observer" ,
            () -> new BlockItem( MOSSY_OBSERVER.get() ,
                    new Item.Properties().tab( CreativeModeTab.TAB_REDSTONE ).tab( JEG_ITEM_GROUP )  ) );

    public static RegistryObject <BlockItem> MAGMATIC_OBSIDIAN_ITEM = ITEMS.register( "magmatic_obsidian" ,
            () -> new BlockItem( MAGMATIC_OBSIDIAN.get() ,
                    new Item.Properties().fireResistant().tab( CreativeModeTab.TAB_BUILDING_BLOCKS ).tab( JEG_ITEM_GROUP )  ) );

    public static RegistryObject <BlockItem> CHARGING_TABLE_ITEM = ITEMS.register( "charging_table" ,
            () -> new BlockItem( CHARGING_TABLE.get() ,
                    new Item.Properties().fireResistant().tab( CreativeModeTab.TAB_MISC ).tab( JEG_ITEM_GROUP )  ) );

    public static RegistryObject <Item> MAGMATIC_OBSIDIAN_SHARD_ITEM = ITEMS.register( "magmatic_obsidian_shard" ,
            () -> new Item( new Item.Properties().fireResistant().tab( CreativeModeTab.TAB_MISC ).tab( JEG_ITEM_GROUP ) ) );

    public static RegistryObject <Item> MAGMATIC_CHARM_ITEM = ITEMS.register( "magmatic_charm" ,
            () -> new MagmaticCharm( new Item.Properties().fireResistant().stacksTo( 1 ).tab( CreativeModeTab.TAB_MISC ).tab( JEG_ITEM_GROUP ) ) );

    public static RegistryObject <Item> MAGMATIC_PENDANT_ITEM = ITEMS.register( "magmatic_pendant" ,
            () -> new Item( new Item.Properties().fireResistant().stacksTo( 1 ).tab( CreativeModeTab.TAB_MISC ).tab( JEG_ITEM_GROUP ) ) );


    public static RegistryObject <Item> NETHER_CORE_ITEM = ITEMS.register( "nether_core" ,
            () -> new Item( new Item.Properties().fireResistant().stacksTo( 1 ).tab( CreativeModeTab.TAB_MISC ).tab( JEG_ITEM_GROUP ) .durability( 1000 ) ) );

    // _-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_- MOD ENTITIES -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_
    public static DeferredRegister <EntityType <?>> ENTITIES = DeferredRegister.create( ForgeRegistries.ENTITIES , JustEnoughGolems.MOD_ID );

    public static RegistryObject <EntityType <PlantGolemEntity>> PLANT_GOLEM_ENTITY = ENTITIES.register( "plant_golem" ,
            () -> EntityType.Builder.of( PlantGolemEntity::new , MobCategory.CREATURE ).sized( 0.55f , 1.2f)
                    .build( new ResourceLocation( JustEnoughGolems.MOD_ID , "plant_golem" ).toString() ) );
    public static RegistryObject <EntityType <MagmaticGolemEntity>> MAGMATIC_GOLEM_ENTITY = ENTITIES.register( "magmatic_golem" ,
            () -> EntityType.Builder.of( MagmaticGolemEntity::new , MobCategory.MONSTER ).sized( 0.55f , 1.2f)
                    .build( new ResourceLocation( JustEnoughGolems.MOD_ID , "magmatic_golem" ).toString() ) );
    public static RegistryObject <EntityType <EnragedMagmaticGolemEntity>> ENRAGED_MAGMATIC_GOLEM_ENTITY = ENTITIES.register( "enraged_magmatic_golem" ,
            () -> EntityType.Builder.of( EnragedMagmaticGolemEntity::new , MobCategory.MONSTER ).sized(1.4F, 2.7F)
                    .build( new ResourceLocation( JustEnoughGolems.MOD_ID , "enraged_magmatic_golem" ).toString() ) );
    public static RegistryObject <EntityType <EnragedGolemSpikeEntity>> ENRAGED_GOLEM_SPIKE_ENTITY = ENTITIES.register( "enraged_golem_spike" ,
            () -> EntityType.Builder.of( EnragedGolemSpikeEntity::new, MobCategory.MISC).sized(0.8F, 0.8F)
                    .build( new ResourceLocation( JustEnoughGolems.MOD_ID , "enraged_golem_spike" ).toString() ));


    // _-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_- MOD SOUNDS -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_
    public static DeferredRegister <SoundEvent> SOUNDS = DeferredRegister.create( ForgeRegistries.SOUND_EVENTS, JustEnoughGolems.MOD_ID );

    public static final RegistryObject<SoundEvent> PLANT_GOLEM_DIES = SOUNDS.register(
            "entity.plant_golem_dies",
            () -> new SoundEvent(new ResourceLocation(JustEnoughGolems.MOD_ID, "entity.plant_golem_dies")));

    public static final RegistryObject<SoundEvent> PLANT_GOLEM_HURTS = SOUNDS.register(
            "entity.plant_golem_hurts",
            () -> new SoundEvent(new ResourceLocation(JustEnoughGolems.MOD_ID, "entity.plant_golem_hurts")));

    public static final RegistryObject<SoundEvent> PLANT_GOLEM_STEP = SOUNDS.register(
            "entity.plant_golem_step",
            () -> new SoundEvent(new ResourceLocation(JustEnoughGolems.MOD_ID, "entity.plant_golem_step")));

    public static final RegistryObject<SoundEvent> INSERTING_CORE_GOLEM = SOUNDS.register(
            "entity.inserting_core_golem",
            () -> new SoundEvent(new ResourceLocation(JustEnoughGolems.MOD_ID, "entity.inserting_core_golem")));

    public static final RegistryObject<SoundEvent> INSERTING_CORE = SOUNDS.register(
            "block.inserting_core",
            () -> new SoundEvent(new ResourceLocation(JustEnoughGolems.MOD_ID, "block.inserting_core")));

    public static final RegistryObject<SoundEvent> EXTRACTING_CORE = SOUNDS.register(
            "block.extracting_core",
            () -> new SoundEvent(new ResourceLocation(JustEnoughGolems.MOD_ID, "block.extracting_core")));

    public static final RegistryObject<SoundEvent> GOLEM_EXTRACTING_CORE = SOUNDS.register(
            "entity.golem_extracting_core",
            () -> new SoundEvent(new ResourceLocation(JustEnoughGolems.MOD_ID, "entity.golem_extracting_core")));

    public static final RegistryObject<SoundEvent> CHARGING_STATION_LOOP = SOUNDS.register(
            "block.charging_table.loop",
            () -> new SoundEvent(new ResourceLocation(JustEnoughGolems.MOD_ID, "block.charging_table.loop")));

    public static final RegistryObject<SoundEvent> CHARGING_STATION_LOOP_END = SOUNDS.register(
            "block.charging_table.loop_end",
            () -> new SoundEvent(new ResourceLocation(JustEnoughGolems.MOD_ID, "block.charging_table.loop_end")));

    public static final RegistryObject<SoundEvent> CHARGING_STATION_LAVA = SOUNDS.register(
            "block.charging_table.lava",
            () -> new SoundEvent(new ResourceLocation(JustEnoughGolems.MOD_ID, "block.charging_table.lava")));

    public static final RegistryObject<SoundEvent> MAGMATIC_GOLEM_HURTS = SOUNDS.register(
            "entity.magmatic_golem_hurts",
            () -> new SoundEvent(new ResourceLocation(JustEnoughGolems.MOD_ID, "entity.magmatic_golem_hurts")));

    public static final RegistryObject<SoundEvent> MAGMATIC_GOLEM_DIES = SOUNDS.register(
            "entity.magmatic_golem_dies",
            () -> new SoundEvent(new ResourceLocation(JustEnoughGolems.MOD_ID, "entity.magmatic_golem_dies")));

    public static final RegistryObject<SoundEvent> MAGMATIC_GOLEM_STARTING = SOUNDS.register(
            "entity.magmatic_golem_starting",
            () -> new SoundEvent(new ResourceLocation(JustEnoughGolems.MOD_ID, "entity.magmatic_golem_starting")));

    public static final RegistryObject<SoundEvent> MAGMATIC_GOLEM_SHUTDOWN = SOUNDS.register(
            "entity.magmatic_golem_shutdown",
            () -> new SoundEvent(new ResourceLocation(JustEnoughGolems.MOD_ID, "entity.magmatic_golem_shutdown")));

    public static final RegistryObject<SoundEvent> MAGMATIC_GOLEM_TRANSFORMS = SOUNDS.register(
            "entity.magmatic_golem_transforms",
            () -> new SoundEvent(new ResourceLocation(JustEnoughGolems.MOD_ID, "entity.magmatic_golem_transforms")));

    public static final RegistryObject<SoundEvent> ENRAGED_MAGMATIC_GOLEM_STEP = SOUNDS.register(
            "entity.enraged_magmatic_golem_step",
            () -> new SoundEvent(new ResourceLocation(JustEnoughGolems.MOD_ID, "entity.enraged_magmatic_golem_step")));

    public static final RegistryObject<SoundEvent> ENRAGED_MAGMATIC_GOLEM_HURT = SOUNDS.register(
            "entity.enraged_magmatic_golem_hurt",
            () -> new SoundEvent(new ResourceLocation(JustEnoughGolems.MOD_ID, "entity.enraged_magmatic_golem_hurt")));

    public static final RegistryObject<SoundEvent> ENRAGED_MAGMATIC_GOLEM_DIES = SOUNDS.register(
            "entity.enraged_magmatic_golem_dies",
            () -> new SoundEvent(new ResourceLocation(JustEnoughGolems.MOD_ID, "entity.enraged_magmatic_golem_dies")));

    public static final RegistryObject<SoundEvent> ENRAGED_MAGMATIC_GOLEM_ATTACK = SOUNDS.register(
            "entity.enraged_magmatic_golem_attack",
            () -> new SoundEvent(new ResourceLocation(JustEnoughGolems.MOD_ID, "entity.enraged_magmatic_golem_attack")));

    public static final RegistryObject<SoundEvent> ENRAGED_GOLEM_SPIKE_ATTACK = SOUNDS.register(
            "entity.enraged_golem_spike_attack",
            () -> new SoundEvent(new ResourceLocation(JustEnoughGolems.MOD_ID, "entity.enraged_golem_spike_attack")));

    public static final RegistryObject<SoundEvent> ENRAGED_GOLEM_SPIKE_SOUND = SOUNDS.register(
            "entity.enraged_golem_spike_sound",
            () -> new SoundEvent(new ResourceLocation(JustEnoughGolems.MOD_ID, "entity.enraged_golem_spike_sound")));

    public static final RegistryObject<SoundEvent> GROUND_PUNCH = SOUNDS.register(
            "entity.ground_punch",
            () -> new SoundEvent(new ResourceLocation(JustEnoughGolems.MOD_ID, "entity.ground_punch")));


}
