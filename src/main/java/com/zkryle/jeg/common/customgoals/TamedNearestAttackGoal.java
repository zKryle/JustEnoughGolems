package com.zkryle.jeg.common.customgoals;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;

public class TamedNearestAttackGoal<T extends LivingEntity> extends NearestAttackableTargetGoal{
    public TamedNearestAttackGoal( TamableAnimal p_i50313_1_ , Class p_i50313_2_ , boolean p_i50313_3_ ){
        super( p_i50313_1_ , p_i50313_2_ , p_i50313_3_ );
    }

    @Override
    public boolean canUse(){
        if(!((TamableAnimal)this.mob).isTame()){
            return super.canUse();
        }
        return false;
    }
}
