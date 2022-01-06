package com.zkryle.jeg.common.customgoals;

import com.zkryle.jeg.common.ICoreOwner;
import com.zkryle.jeg.common.golem.EnragedMagmaticGolemEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.passive.TameableEntity;

public class CoreOwnerMeleeAttackGoal extends MeleeAttackGoal{
    private final ICoreOwner coreOwnerEntity;

    public CoreOwnerMeleeAttackGoal( ICoreOwner p_i1636_1_ , double p_i1636_2_ , boolean p_i1636_4_ ){
        super( (CreatureEntity) p_i1636_1_ , p_i1636_2_ , p_i1636_4_ );
        this.coreOwnerEntity = p_i1636_1_;
    }

    @Override
    public boolean canUse(){
        if(this.coreOwnerEntity.isDelayElapsed() && coreOwnerEntity.getCorePercentage() > 2 && ((TameableEntity)coreOwnerEntity).goalSelector.getRunningGoals()
                .noneMatch( goal -> goal.getGoal() instanceof EnragedMagmaticGolemEntity.SpikeAttackGoal )){
            return super.canUse();
        }
        return false;
    }

    @Override
    public boolean canContinueToUse(){
        if(this.coreOwnerEntity.isDelayElapsed() && coreOwnerEntity.getCorePercentage() > 2 && ((TameableEntity)coreOwnerEntity).goalSelector.getRunningGoals()
                .noneMatch( goal -> goal.getGoal() instanceof EnragedMagmaticGolemEntity.SpikeAttackGoal )){
            return super.canContinueToUse();
        }
        return false;
    }

    @Override
    public void start() {
        this.mob.getNavigation().moveTo(this.path, this.speedModifier - ((( 100 - this.coreOwnerEntity.getCorePercentage()) / 100) / 2));
        this.mob.setAggressive(true);
        this.ticksUntilNextPathRecalculation = 0;
        this.ticksUntilNextAttack = 0;
    }

}
