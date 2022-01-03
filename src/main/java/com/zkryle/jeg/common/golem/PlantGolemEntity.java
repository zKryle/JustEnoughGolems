package com.zkryle.jeg.common.golem;

import java.util.UUID;

import javax.annotation.Nullable;

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
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
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

public class PlantGolemEntity extends CreatureEntity{

    private ItemStack seedSlot;
    
    /**
     * Used for emulating in-world interacting with the fake player
     */
    @Nullable
    private UUID ownerUUID;

    public PlantGolemEntity( EntityType <? extends PlantGolemEntity> entityType , World level ){
        super( entityType , level );
        this.seedSlot = new ItemStack( Items.AIR );
    }
    
    public static PlantGolemEntity createGolem(@Nullable UUID ownerUUID, World level) {
    	PlantGolemEntity golem = new PlantGolemEntity(Init.PLANT_GOLEM_ENTITY.get(), level);
    	golem.ownerUUID = ownerUUID;
    	return golem;
    }

    public static AttributeModifierMap.MutableAttribute createAttributes(){
        return MobEntity.createMobAttributes().add( Attributes.MAX_HEALTH , 15.0D );
    }

    @Override
    protected void registerGoals(){
        super.registerGoals();
        this.goalSelector.addGoal( 1 , new PlantSeedsGoal( this , 30 , 0.30D ) );
        this.goalSelector.addGoal( 2 , new LookRandomlyGoal( this ) );
        this.goalSelector.addGoal( 3 , new LookAtGoal( this , LivingEntity.class , 5.0f ) );
        this.goalSelector.addGoal( 4 , new PickSeedsUpGoal( this , 0.30D ) );
        this.goalSelector.addGoal( 5 , new RandomWalkingGoal( this , 0.30D ) );
        this.goalSelector.addGoal( 6 , new PanicGoal( this , 0.50D ) );
    }

    @Override
    protected ActionResultType mobInteract( PlayerEntity player , Hand hand ){
        if(player.isCrouching() && !seedSlot.isEmpty()){
            dropSlotsLoot();
            this.goalSelector.getRunningGoals().forEach( goal -> {
                        if(goal.getGoal() instanceof PickSeedsUpGoal){
                            PickSeedsUpGoal seedsgoal = (PickSeedsUpGoal) goal.getGoal();
                            seedsgoal.setDelay( 40 );
                        }
                    }
            );
            this.setItemSlot( EquipmentSlotType.MAINHAND , ItemStack.EMPTY );
            return ActionResultType.SUCCESS;
        }
        return super.mobInteract( player , hand );
    }

    @Override
    protected void dropAllDeathLoot( DamageSource source ){
        dropSlotsLoot();
        super.dropAllDeathLoot( source );

    }
    private void dropSlotsLoot(){
        level.addFreshEntity( new ItemEntity( this.level , this.position().x() , this.position().y() , this.position().z() , getSeedSlot() ) );
        setSeedSlot( new ItemStack( Items.AIR ) );
    }

    public ItemStack getSeedSlot(){
        return seedSlot;
    }

    public void setSeedSlot( ItemStack seedSlot ){
        this.seedSlot = seedSlot;
    }

    @Override
    public boolean save( CompoundNBT compound ){
        compound.put( "SeedSlot" , seedSlot.serializeNBT() );
        if (ownerUUID != null) {
        	compound.putUUID("ownerUUID", ownerUUID);
        }
        return super.save( compound );
    }

    @Override
    public void load( CompoundNBT compound ){
        this.seedSlot = ItemStack.of( compound.getCompound( "SeedSlot" ) );
        if (compound.hasUUID("ownerUUID")) {
        	this.ownerUUID = compound.getUUID("ownerUUID");
        }
        super.load( compound );
    }

    @Override
    public void tick(){
        if(!seedSlot.isEmpty()){
            this.setItemSlot( EquipmentSlotType.MAINHAND , seedSlot );
        }
        super.tick();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound(){
        return Init.PLANT_GOLEM_DIES.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound( DamageSource p_184601_1_ ){
        return Init.PLANT_GOLEM_HURTS.get();
    }

    @Override
    protected void playStepSound( BlockPos p_180429_1_ , BlockState p_180429_2_ ){
        this.playSound( Init.PLANT_GOLEM_STEP.get() , this.getSoundVolume() , this.getVoicePitch() );
        super.playStepSound( p_180429_1_ , p_180429_2_ );
    }

    @Override
    public void checkDespawn(){
    }
    
    @Nullable
    public UUID getOwnerUUID() {
    	return ownerUUID;
    }
}
