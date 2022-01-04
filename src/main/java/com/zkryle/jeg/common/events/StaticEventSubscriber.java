package com.zkryle.jeg.common.events;

import com.zkryle.jeg.common.golem.EnragedMagmaticGolemEntity;
import com.zkryle.jeg.common.golem.MagmaticGolemEntity;
import com.zkryle.jeg.common.golem.PlantGolemEntity;
import com.zkryle.jeg.core.Init;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING;

@Mod.EventBusSubscriber
public class StaticEventSubscriber{

    // Handle Golem
    @SubscribeEvent
    public static void onGolemPlaced( BlockEvent.EntityPlaceEvent event ){
        Level level = (Level) event.getWorld();
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
        if(event.getEntity() instanceof Player){
            // A bit of an unnecesarry cast, but to be on the safe side
            ownerUUID = ((Player) event.getEntity()).getUUID();
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
            event.getWorld().playLocalSound( event.getPos().getX() , event.getPos().getY() , event.getPos().getZ() , SoundEvents.PUMPKIN_CARVE , SoundSource.BLOCKS , 1.0F , 1.0F , true );
            event.getPlayer().getMainHandItem().hurtAndBreak( 1 , event.getPlayer() , p -> p.broadcastBreakEvent( InteractionHand.MAIN_HAND ) );
            spawnPlantGolem( event.getWorld() , event.getPos().below() , event.getPos() , event.getPlayer().getUUID() );
        }
    }

    private static void spawnPlantGolem( Level level , BlockPos blockBelow , BlockPos pumpkin , @Nullable UUID owner ){
        if(level.getBlockState( blockBelow ).getBlock() == Init.PRECIOUS_MOSSY_COBBLESTONE.get()
                && level.getBlockState( pumpkin ).getBlock() == Blocks.CARVED_PUMPKIN){
            level.destroyBlock( pumpkin , false );
            level.destroyBlock( blockBelow , false );
            PlantGolemEntity plantGolem = PlantGolemEntity.createGolem( owner , level );
            plantGolem.setPos( blockBelow.getX() + 0.5D , blockBelow.getY() , blockBelow.getZ() + 0.5D );
            level.addFreshEntity( plantGolem );
        }
    }

    private static void spawnMagmaticGolem( Level level , BlockPos blockBelow , BlockPos pumpkin , Entity pEntity ){
        if(level.getBlockState( blockBelow ).getBlock() == Init.MAGMATIC_OBSIDIAN.get()
                && level.getBlockState( pumpkin ).getBlock() == Blocks.CARVED_PUMPKIN){
            level.destroyBlock( pumpkin , false );
            level.destroyBlock( blockBelow , false );
            MagmaticGolemEntity magmaticGolemEntity = MagmaticGolemEntity.createMagmaticGolemEntity( level );
            magmaticGolemEntity.setPos( blockBelow.getX() + 0.5D , blockBelow.getY() , blockBelow.getZ() + 0.5D );
            level.addFreshEntity( magmaticGolemEntity );
            if(pEntity instanceof Player){
                magmaticGolemEntity.setOn( false );
                magmaticGolemEntity.setTamed( (Player) pEntity );
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
        if(entity instanceof Zombie){
            ((Zombie) entity).targetSelector.addGoal( 3 , new NearestAttackableTargetGoal <>( (Zombie) entity , EnragedMagmaticGolemEntity.class , true ) );
        }else if(entity instanceof AbstractSkeleton){
            if(!(entity instanceof WitherSkeleton)){
                ((AbstractSkeleton) entity).targetSelector.addGoal( 3 , new NearestAttackableTargetGoal <>( (AbstractSkeleton) entity , EnragedMagmaticGolemEntity.class , true ) );
            }
        }else if(entity instanceof Creeper){
            ((Creeper) entity).targetSelector.addGoal( 3 , new NearestAttackableTargetGoal <>( (Creeper) entity , EnragedMagmaticGolemEntity.class , true ) );
        }else if(entity instanceof Spider){
            ((Spider) entity).targetSelector.addGoal( 3 , new Spider.SpiderTargetGoal <>( (Spider) entity , EnragedMagmaticGolemEntity.class ) );
        }else if(entity instanceof Raider){
            ((Raider) entity).targetSelector.addGoal( 3 , new NearestAttackableTargetGoal <>( (Raider) entity , EnragedMagmaticGolemEntity.class , true ) );
        } else if(entity instanceof Slime){
            ((Slime) entity).targetSelector.addGoal( 3 , new NearestAttackableTargetGoal <>( (Slime) entity , EnragedMagmaticGolemEntity.class , true ) );
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
            event.getWorld().playSound( event.getPlayer(), event.getPos(), SoundEvents.BUCKET_EMPTY_LAVA, SoundSource.BLOCKS, 1.0F, 1.0F );
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onBiomeLoad(final BiomeLoadingEvent event) {
        if (event.getName() == null)
            return;
        if (event.getCategory().equals( Biome.BiomeCategory.NETHER)) {
            event.getSpawns().addSpawn(MobCategory.MONSTER,
                    new MobSpawnSettings.SpawnerData(Init.MAGMATIC_GOLEM_ENTITY.get(), 5, 1, 1));
        }
    }
}
