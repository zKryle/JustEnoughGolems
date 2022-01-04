package com.zkryle.jeg.common.golem;

import com.zkryle.jeg.common.customgoals.TamedNearestAttackGoal;
import com.zkryle.jeg.core.Init;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MagmaticGolemEntity extends TamableAnimal{

    public float headInclination;
    private boolean isOn = true;
    private ArrayList <Goal> registeredGoals;
    public boolean isTransforming = false;

    public MagmaticGolemEntity( EntityType <? extends MagmaticGolemEntity> entityType , Level level ){
        super( entityType , level );
    }

    private MagmaticGolemEntity( Level level ){
        this( Init.MAGMATIC_GOLEM_ENTITY.get() , level );
    }

    public static MagmaticGolemEntity createMagmaticGolemEntity( Level level ){
        return new MagmaticGolemEntity( level );
    }

    public static AttributeSupplier.Builder createAttributes(){
        return Mob.createMobAttributes().add( Attributes.MAX_HEALTH , 40.0D ).add( Attributes.ATTACK_DAMAGE , 1.5D )
                .add( Attributes.KNOCKBACK_RESISTANCE , 1.0D );
    }

    @Override
    public SpawnGroupData finalizeSpawn( ServerLevelAccessor p_146746_ , DifficultyInstance p_146747_ , MobSpawnType p_146748_ , @Nullable SpawnGroupData p_146749_ , @Nullable CompoundTag p_146750_ ){
        if(!p_146748_.equals( MobSpawnType.CHUNK_GENERATION )) this.setItemInHand( InteractionHand.MAIN_HAND , new ItemStack( Items.GOLDEN_AXE ) );
        return super.finalizeSpawn( p_146746_ , p_146747_ , p_146748_ , p_146749_ , p_146750_ );
    }

    @Override
    protected void registerGoals(){
        super.registerGoals();
        addToRegisteredGoals(
                new MeleeAttackGoal( this , 0.45D , false ) ,
                new FollowOwnerGoal( this , 0.45D , 7.5F , 2.0F , false ) ,
                new WaterAvoidingRandomStrollGoal( this , 0.30D ) ,
                new LookAtPlayerGoal( this , LivingEntity.class , 5.0f ) ,
                new RandomLookAroundGoal( this ) );
        this.targetSelector.addGoal( 2 , new OwnerHurtTargetGoal( this ) );
        this.targetSelector.addGoal( 3 , (new HurtByTargetGoal( this )).setAlertOthers() );
        this.targetSelector.addGoal( 4 , new NearestAttackableTargetGoal <>( this , Piglin.class , false ) );
        this.targetSelector.addGoal( 5 , new NearestAttackableTargetGoal <>( this , AbstractSkeleton.class , false ) );
        this.targetSelector.addGoal( 6 , new TamedNearestAttackGoal <>( this , Player.class , false ) );
    }

    private void addToRegisteredGoals( Goal... goals ){
        ArrayList <Goal> goalsArray = new ArrayList <>( Arrays.stream( goals ).toArray().length );
        goalsArray.addAll( Arrays.asList( goals ) );
        this.registeredGoals = goalsArray;
        int i = 1;
        for(Goal goal : registeredGoals){
            this.goalSelector.addGoal( i , goal );
            i++;
        }
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring( ServerLevel p_241840_1_ , AgeableMob p_241840_2_ ){
        return null;
    }

    /**
     * Deprecated call, overriding/implementing is fine.
     */
    @Deprecated
    public void setTamed( Player pPlayer ){
        this.tame( pPlayer );
        this.navigation.stop();
        this.setTarget( (LivingEntity) null );
        this.level.broadcastEntityEvent( this , (byte) 7 );
        this.setItemInHand( InteractionHand.MAIN_HAND , ItemStack.EMPTY );
    }

    @Override
    public InteractionResult mobInteract( Player pPlayer , InteractionHand pHand ){
        if(pPlayer.getItemInHand( pHand ).getItem() == Items.BLAZE_ROD &&
                pPlayer.getUUID().equals( this.getOwnerUUID() )){

            if(this.isOn()){
                this.playSound( Init.MAGMATIC_GOLEM_SHUTDOWN.get() , 1.0F , 1.0F );
            }else this.playSound( Init.MAGMATIC_GOLEM_STARTING.get() , 1.0F , 1.0F );

            this.setOn( !this.isOn() );

            return InteractionResult.SUCCESS;

        }else if((pPlayer.getItemInHand( pHand ).getItem() instanceof SwordItem ||
                pPlayer.getItemInHand( pHand ).getItem() instanceof AxeItem) &&
                this.getItemBySlot( EquipmentSlot.MAINHAND ).isEmpty() &&
                pPlayer.getUUID().equals( this.getOwnerUUID() )){

            pPlayer.level.playSound( pPlayer , this.blockPosition() , SoundEvents.ARMOR_EQUIP_GENERIC , SoundSource.AMBIENT , 1.0f , 1.0f );
            this.setItemSlot( EquipmentSlot.MAINHAND , pPlayer.getItemInHand( pHand ).copy() );
            pPlayer.getItemInHand( pHand ).shrink( 1 );

            return InteractionResult.SUCCESS;

        }else if(pPlayer.getItemInHand( pHand ).isEmpty() && pPlayer.getUUID().equals( this.getOwnerUUID() ) &&
                !this.getItemBySlot( EquipmentSlot.MAINHAND ).isEmpty()){

            pPlayer.setItemInHand( pHand , this.getItemBySlot( EquipmentSlot.MAINHAND ).copy() );
            this.setItemSlot( EquipmentSlot.MAINHAND , ItemStack.EMPTY );

            return InteractionResult.SUCCESS;
        }else if(pPlayer.getItemInHand( pHand ).getItem() == Init.MAGMATIC_OBSIDIAN_ITEM.get()
                && this.getHealth() < this.getMaxHealth()){

            if(!pPlayer.getAbilities().instabuild){
                pPlayer.getItemInHand( pHand ).shrink( 1 );
            }
            this.playSound( SoundEvents.IRON_GOLEM_REPAIR , 1.0f , 1.0f );
            this.heal( 8.0f );

            return InteractionResult.SUCCESS;
        }else if(pPlayer.getItemInHand( pHand ).getItem() == Init.MAGMATIC_PENDANT_ITEM.get()){
            if(!pPlayer.getAbilities().instabuild){
                pPlayer.getItemInHand( pHand ).shrink( 1 );
            }
            return executeTransformation( pPlayer );
        }
        return InteractionResult.PASS;
    }

    public boolean isOn(){
        return this.isOn;
    }

    public void setOn( boolean on ){
        if(!this.level.isClientSide()){
            if(on){
                for(int i = 0; i < registeredGoals.size(); i++){
                    this.goalSelector.addGoal( i , this.registeredGoals.get( i ) );
                }
            }else{
                registeredGoals.forEach( this.goalSelector::removeGoal );
            }
        }
        if(on){
            this.level.broadcastEntityEvent( this , (byte) 5 );
        }else this.level.broadcastEntityEvent( this , (byte) 4 );
        this.isOn = on;
    }

    @Override
    public void handleEntityEvent( byte pId ){
        if(pId == 4){
            this.isOn = false;
        }else if(pId == 5){
            this.isOn = true;
        }else if(pId == 7){
            this.setItemInHand( InteractionHand.MAIN_HAND , ItemStack.EMPTY );
            super.handleEntityEvent( pId );
        }
        super.handleEntityEvent( pId );
    }

    @Override
    public void tick(){
        super.tick();
        if(!this.isOn()){
            if(headInclination > -0.5f){
                headInclination -= 0.05f;
            }
        }else{
            if(headInclination < 0.0f){
                headInclination += 0.05f;
            }
        }
    }

    @Override
    protected void dropEquipment(){
        if(this.isTame()){
            this.level.addFreshEntity( new ItemEntity( level , this.position().x() , this.position().y() , this.position().z() ,
                    this.getItemBySlot( EquipmentSlot.MAINHAND ).copy() ) );
            this.setItemSlot( EquipmentSlot.MAINHAND , ItemStack.EMPTY );
        }
    }

    @Override
    public boolean fireImmune(){
        return true;
    }

    @Override
    public boolean checkSpawnRules( LevelAccessor pLevel , MobSpawnType pSpawnReason ){
        BlockState blockBelow = pLevel.getBlockState( this.blockPosition().below() );
        return blockBelow.isValidSpawn( pLevel , this.blockPosition().below() , Init.MAGMATIC_GOLEM_ENTITY.get() )
                && blockBelow.getBlock() != Blocks.NETHER_WART_BLOCK;
    }

    @Override
    public void aiStep(){
        this.updateSwingTime();
        super.aiStep();
    }

    @Override
    public boolean checkSpawnObstruction( LevelReader pLevel ){
        return pLevel.isUnobstructed( this ) && !pLevel.containsAnyLiquid( this.getBoundingBox() );
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound( DamageSource pDamageSource ){
        return Init.MAGMATIC_GOLEM_HURTS.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound(){
        return Init.MAGMATIC_GOLEM_DIES.get();
    }

    @Override
    public boolean doHurtTarget( Entity pEntity ){
        if(this.isTame()) this.getMainHandItem().hurtAndBreak( 1 , this , ( p_213833_1_ ) -> p_213833_1_.broadcastBreakEvent( InteractionHand.MAIN_HAND ));
        boolean fireFlag = new Random().nextInt( 100 ) < 10;
        if(fireFlag) pEntity.setSecondsOnFire( 10 );
        pEntity.level.playSound( null , pEntity.blockPosition() , SoundEvents.PLAYER_ATTACK_STRONG , SoundSource.HOSTILE , 1.0F , 1.0F );
        return super.doHurtTarget( pEntity );
    }

    @Override
    public void checkDespawn(){
        if(!this.isTame()){
            super.checkDespawn();
        }
    }

    public InteractionResult executeTransformation( Player pPlayer ){
        if(this.getOwnerUUID() != null && this.getOwnerUUID().equals( pPlayer.getUUID() )){
            this.isTransforming = true;
            this.remove(RemovalReason.DISCARDED);
            this.playSound( Init.MAGMATIC_GOLEM_TRANSFORMS.get() , 1.0F , 1.0F );
            if(!level.isClientSide()) this.level.explode( null , this.getX() , this.getY() , this.getZ() , 3 , true , Explosion.BlockInteraction.DESTROY );
            this.drawExplosionSphere();
            EnragedMagmaticGolemEntity enragedGolem = new EnragedMagmaticGolemEntity( Init.ENRAGED_MAGMATIC_GOLEM_ENTITY.get() , level );
            enragedGolem.setPos( this.getX() , this.getY() , this.getZ() );
            level.addFreshEntity( enragedGolem );
            this.dropEquipment();
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    private void drawExplosionSphere(){
        if(!level.isClientSide()){
            Vec3 pos = this.position();
            int radius = 3;
            int r2 = radius * radius;
            for(int X = -radius; X <= radius; X += 1){
                int x2 = X * X;
                for(int Y = -radius; Y <= radius; Y += 1){
                    int y2 = Y * Y;
                    for(int Z = -radius; Z <= radius; Z += 1)
                        if(x2 + y2 + (Z * Z) <= r2)
                            ((ServerLevel) level).sendParticles( ParticleTypes.EXPLOSION , pos.x() + 0.5F + X , pos.y() + 0.5F + Y , pos.z() + 0.5F + Z , 2 , 0 , 0 , 0 , 1 );
                }
            }
        }
    }
}
