package com.zkryle.jeg.common.customgoals;

import com.zkryle.jeg.common.ICoreOwner;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;

public class CoreOwnerLookAtGoal extends LookAtPlayerGoal{
    private final ICoreOwner coreOwnerEntity;

    public CoreOwnerLookAtGoal( ICoreOwner p_i1631_1_ , Class <? extends LivingEntity> p_i1631_2_ , float p_i1631_3_ ){
        super( (Mob) p_i1631_1_ , p_i1631_2_ , p_i1631_3_ );
        this.coreOwnerEntity = p_i1631_1_;
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
