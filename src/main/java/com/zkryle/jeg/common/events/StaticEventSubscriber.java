package com.zkryle.jeg.common.events;

import static net.minecraft.state.properties.BlockStateProperties.HORIZONTAL_FACING;

import java.util.UUID;

import javax.annotation.Nullable;

import com.zkryle.jeg.JustEnoughGolems;
import com.zkryle.jeg.common.golem.PlantGolemEntity;
import com.zkryle.jeg.core.Init;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JustEnoughGolems.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class StaticEventSubscriber{

    // Handle Golem
    @SubscribeEvent
    public static void onGolemPlaced ( BlockEvent.EntityPlaceEvent event ) {
        World level = (World) event.getWorld();
        BlockPos pos = event.getPos();
        Block block = event.getState().getBlock();

        BlockPos pumpkin;
        BlockPos mossycobblestone;

        if (block == Blocks.CARVED_PUMPKIN) {
            pumpkin = pos;
            mossycobblestone = pos.below();
        } else if (block == Init.PRECIOUS_MOSSY_COBBLESTONE.get()) {
            pumpkin = pos.above();
            mossycobblestone = pos;
        } else return;
        
        UUID ownerUUID = null;
        // We only care about players, as we are simulating the player placing.
        if (event.getEntity() instanceof PlayerEntity) {
        	// A bit of an unnecesarry cast, but to be on the safe side
        	ownerUUID = ((PlayerEntity) event.getEntity()).getUUID();
        }
        
        spawnPlantGolem(level, mossycobblestone, pumpkin, ownerUUID);
    }

    @SubscribeEvent
    public static void onGolemShears( PlayerInteractEvent.RightClickBlock event) {
        if (event.getPlayer().getMainHandItem().getItem() == Items.SHEARS
                && event.getWorld().getBlockState(event.getPos()).getBlock() == Blocks.PUMPKIN) {
            Direction facing = event.getPlayer().getDirection().getOpposite();
            event.getWorld().setBlock(event.getPos(), Blocks.CARVED_PUMPKIN.defaultBlockState().setValue( HORIZONTAL_FACING, facing), 3);
            event.setCanceled(true);
            event.getWorld().playLocalSound(event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), SoundEvents.PUMPKIN_CARVE, SoundCategory.BLOCKS, 1.0F, 1.0F, true);
            event.getPlayer().getMainHandItem().hurtAndBreak(1, event.getPlayer(), p -> p.broadcastBreakEvent( Hand.MAIN_HAND));
            spawnPlantGolem(event.getWorld(), event.getPos().below(), event.getPos(), event.getPlayer().getUUID());
        }
    }

    private static void spawnPlantGolem( World level, BlockPos mossycobblestone, BlockPos pumpkin, @Nullable UUID owner){
        if(level.getBlockState( mossycobblestone ).getBlock() == Init.PRECIOUS_MOSSY_COBBLESTONE.get()
                && level.getBlockState( pumpkin ).getBlock() == Blocks.CARVED_PUMPKIN){
            level.destroyBlock( pumpkin, false );
            level.destroyBlock( mossycobblestone, false );
            PlantGolemEntity plantGolem = PlantGolemEntity.createGolem(owner, level);
            plantGolem.setPos( mossycobblestone.getX() + 0.5D , mossycobblestone.getY() , mossycobblestone.getZ() + 0.5D );
            level.addFreshEntity( plantGolem );
        }
    }



}
