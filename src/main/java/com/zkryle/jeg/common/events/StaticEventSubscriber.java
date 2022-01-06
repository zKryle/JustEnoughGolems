package com.zkryle.jeg.common.events;

import com.zkryle.jeg.JustEnoughGolems;
import com.zkryle.jeg.common.golem.EnragedMagmaticGolemEntity;
import com.zkryle.jeg.common.golem.MagmaticGolemEntity;
import com.zkryle.jeg.common.golem.PlantGolemEntity;
import com.zkryle.jeg.common.tileentities.ChargingTableTileEntity;
import com.zkryle.jeg.core.Init;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.UUID;

import static net.minecraft.state.properties.BlockStateProperties.HORIZONTAL_FACING;

@Mod.EventBusSubscriber(modid = JustEnoughGolems.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class StaticEventSubscriber{

    // Handle Golem
    @SubscribeEvent
    public static void onGolemPlaced( BlockEvent.EntityPlaceEvent event ){
        World level = (World) event.getWorld();
        BlockPos pos = event.getPos();
        Block block = event.getState().getBlock();

        BlockPos pumpkin;
        BlockPos blockBelow;

        if(block == Blocks.CARVED_PUMPKIN){
            pumpkin = pos;
            blockBelow = pos.below();
        }else{
            pumpkin = pos.above();
            blockBelow = pos;
        }

        UUID ownerUUID = null;
        // We only care about players, as we are simulating the player placing.
        if(event.getEntity() instanceof PlayerEntity){
            // A bit of an unnecesarry cast, but to be on the safe side
            ownerUUID = ((PlayerEntity) event.getEntity()).getUUID();
        }

        spawnPlantGolem( level , blockBelow , pumpkin , ownerUUID );
        spawnMagmaticGolem( level , blockBelow , pumpkin , event.getEntity() );
    }

    @SubscribeEvent
    public static void onGolemShears( PlayerInteractEvent.RightClickBlock event ){
        if(event.getPlayer().getMainHandItem().getItem() == Items.SHEARS
                && event.getWorld().getBlockState( event.getPos() ).getBlock() == Blocks.PUMPKIN){
            Direction facing = event.getPlayer().getDirection().getOpposite();
            event.getWorld().setBlock( event.getPos() , Blocks.CARVED_PUMPKIN.defaultBlockState().setValue( HORIZONTAL_FACING , facing ) , 3 );
            event.setCanceled( true );
            event.getWorld().playLocalSound( event.getPos().getX() , event.getPos().getY() , event.getPos().getZ() , SoundEvents.PUMPKIN_CARVE , SoundCategory.BLOCKS , 1.0F , 1.0F , true );
            event.getPlayer().getMainHandItem().hurtAndBreak( 1 , event.getPlayer() , p -> p.broadcastBreakEvent( Hand.MAIN_HAND ) );
            spawnPlantGolem( event.getWorld() , event.getPos().below() , event.getPos() , event.getPlayer().getUUID() );
        }
    }

    private static void spawnPlantGolem( World level , BlockPos blockBelow , BlockPos pumpkin , @Nullable UUID owner ){
        if(level.getBlockState( blockBelow ).getBlock() == Init.PRECIOUS_MOSSY_COBBLESTONE.get()
                && level.getBlockState( pumpkin ).getBlock() == Blocks.CARVED_PUMPKIN){
            level.destroyBlock( pumpkin , false );
            level.destroyBlock( blockBelow , false );
            PlantGolemEntity plantGolem = PlantGolemEntity.createGolem( owner , level );
            plantGolem.setPos( blockBelow.getX() + 0.5D , blockBelow.getY() , blockBelow.getZ() + 0.5D );
            level.addFreshEntity( plantGolem );
        }
    }

    private static void spawnMagmaticGolem( World level , BlockPos blockBelow , BlockPos pumpkin , Entity pEntity ){
        if(level.getBlockState( blockBelow ).getBlock() == Init.MAGMATIC_OBSIDIAN.get()
                && level.getBlockState( pumpkin ).getBlock() == Blocks.CARVED_PUMPKIN){
            level.destroyBlock( pumpkin , false );
            level.destroyBlock( blockBelow , false );
            MagmaticGolemEntity magmaticGolemEntity = MagmaticGolemEntity.createMagmaticGolemEntity( level );
            magmaticGolemEntity.setPos( blockBelow.getX() + 0.5D , blockBelow.getY() , blockBelow.getZ() + 0.5D );
            level.addFreshEntity( magmaticGolemEntity );
            if(pEntity instanceof PlayerEntity){
                magmaticGolemEntity.setOn( false );
                magmaticGolemEntity.setTamed( (PlayerEntity) pEntity );
            }
        }
    }

    @SubscribeEvent
    public static void checkExplosionMagmaticGolem( LivingAttackEvent event ){
        if(event.getEntity() instanceof MagmaticGolemEntity){
            if(((MagmaticGolemEntity) event.getEntity()).isTransforming) event.setCanceled( true );
        }
    }

    @SubscribeEvent
    public static void addEnragedMagmaticGolemAsTarget( EntityJoinWorldEvent event ){
        Entity entity = event.getEntity();
        if(entity instanceof ZombieEntity){
            ((ZombieEntity) entity).targetSelector.addGoal( 3 , new NearestAttackableTargetGoal <>( (ZombieEntity) entity , EnragedMagmaticGolemEntity.class , true ) );
        }else if(entity instanceof AbstractSkeletonEntity){
            if(!(entity instanceof WitherSkeletonEntity)){
                ((AbstractSkeletonEntity) entity).targetSelector.addGoal( 3 , new NearestAttackableTargetGoal <>( (AbstractSkeletonEntity) entity , EnragedMagmaticGolemEntity.class , true ) );
            }
        }else if(entity instanceof CreeperEntity){
            ((CreeperEntity) entity).targetSelector.addGoal( 3 , new NearestAttackableTargetGoal <>( (CreeperEntity) entity , EnragedMagmaticGolemEntity.class , true ) );
        }else if(entity instanceof SpiderEntity){
            ((SpiderEntity) entity).targetSelector.addGoal( 3 , new SpiderEntity.TargetGoal <>( (SpiderEntity) entity , EnragedMagmaticGolemEntity.class ) );
        }else if(entity instanceof AbstractRaiderEntity){
            ((AbstractRaiderEntity) entity).targetSelector.addGoal( 3 , new NearestAttackableTargetGoal <>( (AbstractRaiderEntity) entity , EnragedMagmaticGolemEntity.class , true ) );
        } else if(entity instanceof SlimeEntity){
            ((SlimeEntity) entity).targetSelector.addGoal( 3 , new NearestAttackableTargetGoal <>( (SlimeEntity) entity , EnragedMagmaticGolemEntity.class , true ) );
        }
    }

    @SubscribeEvent
    public static void onBlockInteraction( PlayerInteractEvent.RightClickBlock event ){
        if(event.getWorld().getBlockState( event.getPos() ).getBlock() == Blocks.CRYING_OBSIDIAN &&
                event.getPlayer().getItemInHand( event.getHand() ).getItem() == Items.LAVA_BUCKET &&
                !event.getPlayer().isShiftKeyDown()){
            event.getWorld().setBlockAndUpdate( event.getPos(), Init.MAGMATIC_OBSIDIAN.get().defaultBlockState() );
            event.getPlayer().setItemInHand( event.getHand(), new ItemStack( Items.BUCKET ) );
            event.getPlayer().swing( event.getHand() );
            event.getWorld().playSound( event.getPlayer(), event.getPos(), SoundEvents.BUCKET_EMPTY_LAVA, SoundCategory.BLOCKS, 1.0F, 1.0F );
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onBiomeLoad(final BiomeLoadingEvent event) {
        if (event.getName() == null)
            return;
        if (event.getCategory().equals( Biome.Category.NETHER)) {
            event.getSpawns().addSpawn(EntityClassification.MONSTER,
                    new MobSpawnInfo.Spawners(Init.MAGMATIC_GOLEM_ENTITY.get(), 5, 1, 1));
        }
    }

    @SubscribeEvent
    public static void blockBroken(final BlockEvent.BreakEvent event){
        if(event.getWorld().getBlockEntity( event.getPos() ) instanceof ChargingTableTileEntity){
            ChargingTableTileEntity te = (ChargingTableTileEntity) event.getWorld().getBlockEntity( event.getPos() );
            if(te.getCore() != null) event.getWorld().addFreshEntity( new ItemEntity( (World) event.getWorld() , event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), te.getCore() ) );
        }
    }
}
