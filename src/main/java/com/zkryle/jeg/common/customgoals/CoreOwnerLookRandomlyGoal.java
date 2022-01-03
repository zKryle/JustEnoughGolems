package com.zkryle.jeg.common.customgoals;

import com.zkryle.jeg.common.ICoreOwner;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;

public class CoreOwnerLookRandomlyGoal extends LookRandomlyGoal{
    private final ICoreOwner coreOwnerEntity;

    public CoreOwnerLookRandomlyGoal( ICoreOwner p_i1647_1_ ){
        super( (MobEntity) p_i1647_1_ );
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
