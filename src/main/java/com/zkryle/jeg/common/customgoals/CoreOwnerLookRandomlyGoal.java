package com.zkryle.jeg.common.customgoals;

import com.zkryle.jeg.common.ICoreOwner;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;

public class CoreOwnerLookRandomlyGoal extends RandomLookAroundGoal{
    private final ICoreOwner coreOwnerEntity;

    public CoreOwnerLookRandomlyGoal( ICoreOwner p_i1647_1_ ){
        super( (Mob) p_i1647_1_ );
        this.coreOwnerEntity = p_i1647_1_;
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
}
