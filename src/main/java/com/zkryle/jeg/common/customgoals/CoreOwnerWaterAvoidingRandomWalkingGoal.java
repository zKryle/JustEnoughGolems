package com.zkryle.jeg.common.customgoals;

import com.zkryle.jeg.common.ICoreOwner;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;

public class CoreOwnerWaterAvoidingRandomWalkingGoal extends WaterAvoidingRandomStrollGoal{

    ICoreOwner coreOwnerEntity;

    public CoreOwnerWaterAvoidingRandomWalkingGoal( ICoreOwner p_i1648_1_ , double p_i1648_2_ ){
        super( (PathfinderMob) p_i1648_1_ , p_i1648_2_ );
        this.coreOwnerEntity = p_i1648_1_;
    }

    @Override
    public boolean canUse(){
        if(this.coreOwnerEntity.isDelayElapsed() && coreOwnerEntity.getCorePercentage() > 2){
            return super.canUse();
        }
        return false;
    }

    @Override
    public boolean canContinueToUse(){
        if(this.coreOwnerEntity.isDelayElapsed() && coreOwnerEntity.getCorePercentage() > 2){
            return super.canContinueToUse();
        }
        return false;
    }

    @Override
    public void start() {
        this.mob.getNavigation().moveTo(this.wantedX, this.wantedY, this.wantedZ, this.speedModifier - ((( 100 - this.coreOwnerEntity.getCorePercentage()) / 100) / 2));
    }

}
