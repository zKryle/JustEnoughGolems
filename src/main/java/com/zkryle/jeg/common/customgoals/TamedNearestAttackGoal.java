package com.zkryle.jeg.common.customgoals;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.passive.TameableEntity;

public class TamedNearestAttackGoal<T extends LivingEntity> extends NearestAttackableTargetGoal{
    public TamedNearestAttackGoal( TameableEntity p_i50313_1_ , Class p_i50313_2_ , boolean p_i50313_3_ ){
        super( p_i50313_1_ , p_i50313_2_ , p_i50313_3_ );
    }

    @Override
    public boolean canUse(){
        if(!((TameableEntity)this.mob).isTame()){
            return super.canUse();
        }
        return false;
    }
}
