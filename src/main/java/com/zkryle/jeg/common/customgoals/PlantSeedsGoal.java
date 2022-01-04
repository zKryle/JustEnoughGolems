package com.zkryle.jeg.common.customgoals;

import com.zkryle.jeg.common.JEGFakePlayer;
import com.zkryle.jeg.common.golem.PlantGolemEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.Random;


public class PlantSeedsGoal extends Goal{

    private static final Direction[] DIRECTIONS = Direction.values();
    private final PlantGolemEntity entity;
    private Level level;
    private final int range;
    private final double speedModifier;

    public PlantSeedsGoal( PlantGolemEntity entity , int range , double speedModifier ){
        this.entity = entity;
        this.range = range;
        this.level = entity.level;
        this.speedModifier = speedModifier;
    }

    @Override
    public boolean canUse(){
        if(!entity.getSeedSlot().isEmpty()){
            for(BlockPos block : BlockPos.betweenClosed( (int) entity.getX() - range , (int) entity.getY() - 2 , (int) entity.getZ() - range ,
                    (int) entity.getX() + range , (int) entity.getY() + 2 , (int) entity.getZ() + range )){

                if(level.getBlockState( block ).getBlock() == Blocks.FARMLAND && level.getBlockState( block.above() ).getBlock() == Blocks.AIR){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean canContinueToUse(){
        return !this.entity.getNavigation().isDone();
    }

    @Override
    public void start(){

        BlockState crop = Blocks.WHEAT.defaultBlockState();
        if(entity.getSeedSlot().getItem() instanceof ItemNameBlockItem){
            ItemNameBlockItem item = (ItemNameBlockItem) entity.getSeedSlot().getItem();
            if(item.getBlock() instanceof CropBlock){
                crop = item.getBlock().defaultBlockState();
            }
        }

        for(BlockPos block : BlockPos.betweenClosed( (int) entity.getX() - range , (int) entity.getY() - 2 , (int) entity.getZ() - range ,
                (int) entity.getX() + range , (int) entity.getY() + 2 , (int) entity.getZ() + range )){
            if(level.getBlockState( block ).getBlock() == Blocks.FARMLAND && level.getBlockState( block.above() ).getBlock() == Blocks.AIR){
                this.entity.getNavigation().moveTo(
                        new Random().nextInt() * 10 > 5 ? block.getX() + 0.5F : block.getX() - 0.5F , block.getY() ,
                        new Random().nextInt() * 10 > 5 ? block.getZ() + 0.5F : block.getZ() - 0.5F ,
                        this.speedModifier );
                break;
            }
        }

        if(!entity.getSeedSlot().isEmpty()){
            if(level.getBlockState( new BlockPos( entity.position() ) ).getBlock() == Blocks.FARMLAND &&
                    level.getBlockState( new BlockPos( entity.position() ).above() ).getBlock() == Blocks.AIR &&
                    (this.entity.level.getRawBrightness( new BlockPos( this.entity.position() ).above() , 0 ) >= 8 ||
                            this.entity.level.canSeeSky( new BlockPos( this.entity.position() ).above() ))){
            	BlockPos targetPos = new BlockPos(entity.position()).above();
            	if (canGolemPlace(crop, targetPos)) {
            		level.setBlock(targetPos, crop, 3);
                	this.entity.getSeedSlot().shrink(1);
            	}
            }
        }

        for(Direction d : DIRECTIONS){
            if(!entity.getSeedSlot().isEmpty()){
                if(level.getBlockState( new BlockPos( entity.position() ).relative( d , 1 ) ).getBlock() == Blocks.FARMLAND &&
                        level.getBlockState( new BlockPos( entity.position() ).above().relative( d , 1 ) ).getBlock() == Blocks.AIR &&
                        (this.entity.level.getRawBrightness( new BlockPos( entity.position() ).above().relative( d , 1 ) , 0 ) >= 8 ||
                                this.entity.level.canSeeSky( new BlockPos( entity.position() ).above().relative( d , 1 ) ))){
                    BlockPos targetPos = new BlockPos(entity.position()).relative(d, 1).above();
                    if (canGolemPlace(crop, targetPos)) {
                		level.setBlock(targetPos, crop, 3);
                    	this.entity.getSeedSlot().shrink(1);
                	}
                }
            }

            if(!entity.getSeedSlot().isEmpty()){
                if(level.getBlockState( new BlockPos( entity.position() ).relative( d , 1 ).below() ).getBlock() == Blocks.FARMLAND &&
                        level.getBlockState( new BlockPos( entity.position() ).relative( d , 1 ) ).getBlock() == Blocks.AIR &&
                        (this.entity.level.getRawBrightness( new BlockPos( entity.position() ).relative( d , 1 ) , 0 ) >= 8 ||
                                this.entity.level.canSeeSky( new BlockPos( entity.position() ).relative( d , 1 ) ))){
                	BlockPos targetPos = new BlockPos(entity.position()).relative(d, 1);
                	if (canGolemPlace(crop, targetPos)) {
                		level.setBlock(targetPos, crop, 3);
                    	this.entity.getSeedSlot().shrink(1);
                	}
                }
            }
        }
    }
    
    private boolean canGolemPlace(BlockState state, BlockPos pos) {
    	if (entity.getOwnerUUID() == null) {
    		return true;
    	}
        return JEGFakePlayer.withFakePlayer((ServerLevel) level, this.entity.getX(), this.entity.getY(), this.entity.getZ(), player -> {
            player.setEmulatingUUID(entity.getOwnerUUID()); //pretend to be the golem owner
            return !ForgeEventFactory.onBlockPlace(player, BlockSnapshot.create(entity.level.dimension(), entity.level, pos), Direction.UP);
        });
    }

    @Override
    public boolean isInterruptable(){
        return false;
    }
}
