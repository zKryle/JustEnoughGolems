package com.zkryle.jeg.common.golem;

import com.zkryle.jeg.common.customgoals.PickSeedsUpGoal;
import com.zkryle.jeg.common.customgoals.PlantSeedsGoal;
import com.zkryle.jeg.core.Init;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.UUID;

public class PlantGolemEntity extends PathfinderMob{

    private ItemStack seedSlot;
    
    /**
     * Used for emulating in-world interacting with the fake player
     */
    @Nullable
    private UUID ownerUUID;

    public PlantGolemEntity( EntityType <? extends PlantGolemEntity> entityType , Level level ){
        super( entityType , level );
        this.seedSlot = new ItemStack( Items.AIR );
    }
    
    public static PlantGolemEntity createGolem(@Nullable UUID ownerUUID, Level level) {
    	PlantGolemEntity golem = new PlantGolemEntity(Init.PLANT_GOLEM_ENTITY.get(), level);
    	golem.ownerUUID = ownerUUID;
    	return golem;
    }

    public static AttributeSupplier.Builder createAttributes(){
        return Mob.createMobAttributes().add( Attributes.MAX_HEALTH , 15.0D );
    }

    @Override
    protected void registerGoals(){
        super.registerGoals();
        this.goalSelector.addGoal( 1 , new PlantSeedsGoal( this , 30 , 0.30D ) );
        this.goalSelector.addGoal( 2 , new RandomLookAroundGoal( this ) );
        this.goalSelector.addGoal( 3 , new LookAtPlayerGoal( this , LivingEntity.class , 5.0f ) );
        this.goalSelector.addGoal( 4 , new PickSeedsUpGoal( this , 0.30D ) );
        this.goalSelector.addGoal( 5 , new RandomStrollGoal( this , 0.30D ) );
        this.goalSelector.addGoal( 6 , new PanicGoal( this , 0.50D ) );
    }

    @Override
    protected InteractionResult mobInteract( Player player , InteractionHand hand ){
        if(player.isCrouching() && !seedSlot.isEmpty()){
            dropSlotsLoot();
            this.goalSelector.getRunningGoals().forEach( goal -> {
                        if(goal.getGoal() instanceof PickSeedsUpGoal){
                            PickSeedsUpGoal seedsgoal = (PickSeedsUpGoal) goal.getGoal();
                            seedsgoal.setDelay( 40 );
                        }
                    }
            );
            this.setItemSlot( EquipmentSlot.MAINHAND , ItemStack.EMPTY );
            return InteractionResult.SUCCESS;
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
    public boolean save( CompoundTag compound ){
        compound.put( "SeedSlot" , seedSlot.serializeNBT() );
        if (ownerUUID != null) {
        	compound.putUUID("ownerUUID", ownerUUID);
        }
        return super.save( compound );
    }

    @Override
    public void load( CompoundTag compound ){
        this.seedSlot = ItemStack.of( compound.getCompound( "SeedSlot" ) );
        if (compound.hasUUID("ownerUUID")) {
        	this.ownerUUID = compound.getUUID("ownerUUID");
        }
        super.load( compound );
    }

    @Override
    public void tick(){
        if(!seedSlot.isEmpty()){
            this.setItemSlot( EquipmentSlot.MAINHAND , seedSlot );
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
