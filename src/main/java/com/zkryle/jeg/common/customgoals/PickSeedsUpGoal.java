package com.zkryle.jeg.common.customgoals;

import com.zkryle.jeg.common.golem.PlantGolemEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.phys.AABB;

public class PickSeedsUpGoal extends Goal{

    private PlantGolemEntity entity;
    private final double speedModifier;
    private int delay = 0;

    public PickSeedsUpGoal( PlantGolemEntity entity , double speedModifier ){
        this.entity = entity;
        this.speedModifier = speedModifier;
    }

    @Override
    public void start(){

        if(delay == 0){
            for(ItemEntity item : entity.level.getEntitiesOfClass( ItemEntity.class ,
                    new AABB( (int) entity.getX() - 60 , (int) entity.getY() - 2 , (int) entity.getZ() - 60 ,
                            (int) entity.getX() + 60 , (int) entity.getY() + 2 , (int) entity.getZ() + 60 ) )){
                if(item.getItem().getItem() instanceof ItemNameBlockItem){
                    ItemNameBlockItem item1 = (ItemNameBlockItem) item.getItem().getItem();
                    if(item1.getBlock() instanceof CropBlock){
                        this.entity.getNavigation().moveTo( item , this.speedModifier );
                        if(entity.distanceTo( item ) < 1.8F){
                            if(this.entity.getSeedSlot().isEmpty()){
                                this.entity.setSeedSlot( item.getItem().copy() );
                                item.kill();
                            }else if(item.getItem().getItem() == entity.getSeedSlot().getItem()){
                                if(item.getItem().getCount() + this.entity.getSeedSlot().getCount() <= 64){
                                    this.entity.getSeedSlot().setCount( item.getItem().getCount() + this.entity.getSeedSlot().getCount() );
                                    item.kill();
                                }else if(this.entity.getSeedSlot().getCount() < 64){
                                    this.entity.level.addFreshEntity( new ItemEntity( this.entity.level , this.entity.position().x , this.entity.position().y ,
                                            this.entity.position().z , new ItemStack( item.getItem().getItem() ,
                                            item.getItem().getCount() - (64 - this.entity.getSeedSlot().getCount()) ) ) );
                                    this.entity.getSeedSlot().setCount( 64 );
                                    item.kill();
                                }
                            }
                            this.entity.playSound( SoundEvents.ITEM_PICKUP , 0.5f , 1.0f );
                            delay = 120;
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean canUse(){

        if(this.entity.goalSelector.getRunningGoals().noneMatch( goal -> goal.getGoal() instanceof PlantSeedsGoal  )) {
            for(ItemEntity item : entity.level.getEntitiesOfClass( ItemEntity.class ,
                    new AABB( (int) entity.getX() - 60 , (int) entity.getY() - 2 , (int) entity.getZ() - 60 ,
                            (int) entity.getX() + 60 , (int) entity.getY() + 2 , (int) entity.getZ() + 60 ) )){
                if((this.entity.getSeedSlot().isEmpty() || item.getItem().getItem() == entity.getSeedSlot().getItem()) &&
                        this.entity.getSeedSlot().getCount() < 64){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean canContinueToUse(){
        return !this.entity.getNavigation().isDone() && this.entity.goalSelector.getRunningGoals().noneMatch( goal -> goal.getGoal() instanceof PlantSeedsGoal  );
    }

    @Override
    public void tick(){
        if(delay > 0){
            delay--;
        }
        super.tick();
    }

    public void setDelay( int delay ){
        this.delay = delay;
    }
}
