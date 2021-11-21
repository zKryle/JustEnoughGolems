package com.zkryle.jeg.common.golem;

import com.zkryle.jeg.common.customgoals.PickSeedsUpGoal;
import com.zkryle.jeg.common.customgoals.PlantSeedsGoal;
import com.zkryle.jeg.core.Init;
import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class MagmaticGolemEntity extends CreatureEntity{

    public MagmaticGolemEntity( EntityType <? extends MagmaticGolemEntity> entityType , World level){
        super(entityType, level);
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add( Attributes.MAX_HEALTH, 25.0D );
    }

    @Override
    protected void registerGoals(){
        super.registerGoals();
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.30D));
        this.goalSelector.addGoal(2, new LookAtGoal(this, LivingEntity.class, 5.0f));
        this.goalSelector.addGoal(3, new LookRandomlyGoal(this));

    }

}
