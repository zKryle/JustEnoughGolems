package com.zkryle.jeg.common.customgoals;

import com.zkryle.jeg.common.ICoreOwner;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;

public class CoreOwnerFollowOwnerGoal extends FollowOwnerGoal{
    private final ICoreOwner coreOwnerEntity;

    public CoreOwnerFollowOwnerGoal( ICoreOwner p_i225711_1_ , double p_i225711_2_ , float p_i225711_4_ , float p_i225711_5_ , boolean p_i225711_6_ ){
        super( (TamableAnimal) p_i225711_1_ , p_i225711_2_ , p_i225711_4_ , p_i225711_5_ , p_i225711_6_ );
        this.coreOwnerEntity = p_i225711_1_;
    }

    @Override
    public boolean canUse(){
        if(this.coreOwnerEntity.isDelayElapsed() && coreOwnerEntity.getCorePercentage() > 2) {
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
    public void tick() {
        ((TamableAnimal)this.coreOwnerEntity).getLookControl().setLookAt(this.owner, 10.0F, (float)((TamableAnimal)this.coreOwnerEntity).getMaxHeadXRot());
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = 10;
            if (!((TamableAnimal)this.coreOwnerEntity).isLeashed() && !((TamableAnimal)this.coreOwnerEntity).isPassenger()) {
                if (((TamableAnimal)this.coreOwnerEntity).distanceToSqr(this.owner) >= 144.0D) {
                    this.teleportToOwner();
                } else {
                    this.navigation.moveTo(this.owner, this.speedModifier - ((( 100 - this.coreOwnerEntity.getCorePercentage()) / 100) / 2));
                }

            }
        }
    }
}
