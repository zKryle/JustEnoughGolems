package com.zkryle.jeg.common.customgoals;

import com.zkryle.jeg.common.golem.PlantGolemEntity;
import net.minecraft.block.CropsBlock;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;

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
                    new AxisAlignedBB( (int) entity.getX() - 60 , (int) entity.getY() - 2 , (int) entity.getZ() - 60 ,
                            (int) entity.getX() + 60 , (int) entity.getY() + 2 , (int) entity.getZ() + 60 ) )){
                if(item.getItem().getItem() instanceof BlockNamedItem){
                    BlockNamedItem item1 = (BlockNamedItem) item.getItem().getItem();
                    if(item1.getBlock() instanceof CropsBlock){
                        this.entity.getNavigation().moveTo( item.position().x() < 0 ? item.position().x() - 1 : item.position().x() + 1 , item.position().y(),
                                item.position().z() < 0 ? item.position().z() - 1 : item.position().z() + 1, this.speedModifier );
                        if(entity.distanceTo( item ) < 1.25F){
                            if(this.entity.getSeedSlot().isEmpty()){
                                this.entity.setSeedSlot( item.getItem().copy() );
                                item.kill();
                            } else if (item.getItem().getItem() == entity.getSeedSlot().getItem()) {
                                if(item.getItem().getCount() + this.entity.getSeedSlot().getCount() <= 64){
                                    this.entity.getSeedSlot().setCount( item.getItem().getCount() + this.entity.getSeedSlot().getCount() );
                                    item.kill();
                                } else if (this.entity.getSeedSlot().getCount() < 64) {
                                    this.entity.level.addFreshEntity( new ItemEntity( this.entity.level, this.entity.position().x, this.entity.position().y,
                                            this.entity.position().z , new ItemStack( item.getItem().getItem(),
                                            item.getItem().getCount() - (64 - this.entity.getSeedSlot().getCount()))));
                                    this.entity.getSeedSlot().setCount( 64 );
                                    item.kill();
                                }
                            }
                            this.entity.playSound( SoundEvents.ITEM_PICKUP, 0.5f, 1.0f );
                            delay = 60;
                        }
                    }
                }
            }
        }
    }

        @Override
        public boolean canUse () {
            for(ItemEntity item : entity.level.getEntitiesOfClass( ItemEntity.class ,
                    new AxisAlignedBB( (int) entity.getX() - 60 , (int) entity.getY() - 2 , (int) entity.getZ() - 60 ,
                            (int) entity.getX() + 60 , (int) entity.getY() + 2 , (int) entity.getZ() + 60 ) )){
                if(this.entity.getSeedSlot().isEmpty() || item.getItem().getItem() == entity.getSeedSlot().getItem()){
                    start();
                    return true;
                }
            }
            return false;
        }

    @Override
    public void stop(){
        this.entity.getNavigation().stop();
    }

    @Override
        public void tick () {
            if(delay > 0){
                delay--;
            }
            super.tick();
        }
    }
