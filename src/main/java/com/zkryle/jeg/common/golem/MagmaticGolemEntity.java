package com.zkryle.jeg.common.golem;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.level.Level;

public class MagmaticGolemEntity extends PathfinderMob{

    public MagmaticGolemEntity( EntityType <? extends MagmaticGolemEntity> entityType , Level level){
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add( Attributes.MAX_HEALTH, 25.0D );
    }

    @Override
    protected void registerGoals(){
        super.registerGoals();
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.30D));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, LivingEntity.class, 5.0f));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));

    }

}
