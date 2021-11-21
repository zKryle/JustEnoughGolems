package com.zkryle.jeg.common.customgoals;

import com.zkryle.jeg.common.golem.PlantGolemEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class PlantSeedsGoal extends Goal{

    private static final Direction[] DIRECTIONS = Direction.values();
    private final PlantGolemEntity entity;
    private World level;
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
                    start();
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void start(){

        BlockState crop = Blocks.WHEAT.defaultBlockState();
        if(entity.getSeedSlot().getItem() instanceof BlockNamedItem){
            BlockNamedItem item = (BlockNamedItem) entity.getSeedSlot().getItem();
            if(item.getBlock() instanceof CropsBlock){
                crop = item.getBlock().defaultBlockState();
            }
        }

        for(BlockPos block : BlockPos.betweenClosed( (int) entity.getX() - range , (int) entity.getY() - 2 , (int) entity.getZ() - range ,
                (int) entity.getX() + range , (int) entity.getY() + 2 , (int) entity.getZ() + range )){
            if(level.getBlockState( block ).getBlock() == Blocks.FARMLAND && level.getBlockState( block.above() ).getBlock() == Blocks.AIR){
                this.entity.getNavigation().moveTo( block.getX() , block.getY() , block.getZ() , this.speedModifier );
                break;
            }
        }

        if(!entity.getSeedSlot().isEmpty()){
            if(level.getBlockState( new BlockPos( entity.position() ) ).getBlock() == Blocks.FARMLAND &&
                    level.getBlockState( new BlockPos( entity.position() ).above() ).getBlock() == Blocks.AIR){
                level.setBlock( new BlockPos( entity.position() ).above() , crop , 3 );
                this.entity.getSeedSlot().shrink( 1 );
            }
        }

        for(Direction d : DIRECTIONS) {
            if(!entity.getSeedSlot().isEmpty()){
                if(level.getBlockState( new BlockPos( entity.position() ).relative( d , 1 ) ).getBlock() == Blocks.FARMLAND &&
                        level.getBlockState( new BlockPos( entity.position() ).above().relative( d , 1 ) ).getBlock() == Blocks.AIR){
                    level.setBlock( new BlockPos( entity.position() ).relative( d , 1 ).above() , crop , 3 );
                    this.entity.getSeedSlot().shrink( 1 );
                }
            }

            if(!entity.getSeedSlot().isEmpty()){
                if(level.getBlockState( new BlockPos( entity.position() ).relative( d , 1 ).below() ).getBlock() == Blocks.FARMLAND &&
                        level.getBlockState( new BlockPos( entity.position() ).relative( d , 1 ) ).getBlock() == Blocks.AIR){
                    level.setBlock( new BlockPos( entity.position() ).relative( d , 1 ) , crop , 3 );
                    this.entity.getSeedSlot().shrink( 1 );
                }
            }
        }
    }

    @Override
    public void stop(){
        this.entity.getNavigation().stop();
        super.stop();
    }

    @Override
    public boolean isInterruptable(){
        return true;
    }
}
