package com.zkryle.jeg.common.golem;

import com.zkryle.jeg.common.ICoreOwner;
import com.zkryle.jeg.common.customgoals.*;
import com.zkryle.jeg.core.Init;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Random;

public class EnragedMagmaticGolemEntity extends TamableAnimal implements ICoreOwner, IEntityAdditionalSpawnData{
    private float fallingSpeed = 0.0f;
    private short coreDischargeCounter = 1200;
    public float headInclination;
    public float bodyInclination;
    public float altBodyInclination;
    private ItemStack core = ItemStack.EMPTY;
    protected static final EntityDataAccessor<Boolean> isOn = SynchedEntityData.defineId(EnragedMagmaticGolemEntity.class, EntityDataSerializers.BOOLEAN);
    private int attackAnimationTick;
    private int rangedAttackAnimationTick;
    private byte coreDelay = 80;
    private boolean soundFlag;

    public EnragedMagmaticGolemEntity( EntityType <? extends TamableAnimal> p_i48574_1_ , Level p_i48574_2_ ){
        super( p_i48574_1_ , p_i48574_2_ );
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(isOn, false);
    }

    public static AttributeSupplier.Builder createAttributes(){
        return Mob.createMobAttributes().add(Attributes.FOLLOW_RANGE, 35.0D).add( Attributes.MAX_HEALTH , 300.0D ).add(Attributes.MOVEMENT_SPEED, 0.30D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.95D).add(Attributes.ATTACK_DAMAGE, 30.0D);
    }

    @Override
    protected void registerGoals(){
        this.goalSelector.addGoal( 1, new SpikeAttackGoal(this, 11.0F) );
        this.goalSelector.addGoal(2, new CoreOwnerMeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(3, new CoreOwnerFollowOwnerGoal( this , 1.0D , 7.5F , 2.0F , false ) );
        this.goalSelector.addGoal( 4, new CoreOwnerWaterAvoidingRandomWalkingGoal( this, 0.9D ) );
        this.goalSelector.addGoal( 5, new CoreOwnerLookAtGoal( this, LivingEntity.class, 4.0F ) );
        this.goalSelector.addGoal( 6, new CoreOwnerLookRandomlyGoal( this) );
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal <>(this, Mob.class, 5, false, false,
                ( p_234199_0_) -> p_234199_0_ instanceof Enemy && !(p_234199_0_ instanceof ZombifiedPiglin) ));

    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring( ServerLevel p_241840_1_ , AgeableMob p_241840_2_ ){
        return null;
    }


    @Override
    public boolean hasCore(){
        return !this.core.isEmpty();
    }

    @Override
    public void setCore( ItemStack core){
        this.core = core;
    }

    @Override
    public ItemStack getCore(){
        return this.core;
    }

    @Override
    public boolean isDelayElapsed(){
        return this.coreDelay <= 0;
    }

    @Override
    public void tick(){
        if(this.getCorePercentage() <= 2 && this.isOn()){
            this.setOn( false );
        } else if(this.getCorePercentage() > 2 && !this.isOn()){
            this.setOn( true );
        }
        if(!this.isOn()){
            if(bodyInclination < 1.055f) fallingSpeed += 0.0025f;
            if(headInclination > -0.5f){
                headInclination -= 0.05f;
            }
            if(bodyInclination < 1.055f && headInclination < -0.25f){
                bodyInclination += fallingSpeed;
            }
        } else {
            if(headInclination < 0.0f && bodyInclination <= 0.0f){
                headInclination += 0.05f;
            }
            if(bodyInclination > 0.0f ){
                bodyInclination -= 0.05f;
            }
            if(this.rangedAttackAnimationTick > 10 && altBodyInclination < 0.85F){
                altBodyInclination += 0.3f;
            }
            fallingSpeed = 0.0001f;
        }
        if(this.rangedAttackAnimationTick <= 10 && altBodyInclination > 0.0F){
            altBodyInclination -= 0.15f;
        }
        if(Math.floor(bodyInclination * 10) == 9 && !this.hasCore()){
            this.spawnHitParticles(30);
            this.soundFlag = true;
            if(!this.level.isClientSide()){
                this.level.playSound(null, this.blockPosition(), Init.GROUND_PUNCH.get() , SoundSource.NEUTRAL , 1.0F , 1.0F);
            }
        }
        if(Math.floor(bodyInclination * 10) == 1 && !this.hasCore() && this.soundFlag){
            if(!this.level.isClientSide()){
                this.soundFlag = false;
                this.level.playSound(null, this.blockPosition(), Init.GOLEM_EXTRACTING_CORE.get() , SoundSource.NEUTRAL , 1.0F , 1.0F);
            }
        }
        if(this.isOn()){
            this.coreDischargeCounter--;
            if(coreDischargeCounter <= 0){
                coreDischargeCounter = 1200;
                if(!this.level.isClientSide()){
                    this.core.hurt( 5 , RandomSource.create() , null );
                }
            }
        }
        this.coreDelay = (byte) Math.max( 0, --coreDelay );
        super.tick();
    }

    public void spawnHitParticles(int amountOfParticles){
        Vec3 particlePos = this.position().add( this.getLookAngle() );
        if (this.level instanceof ServerLevel) {
            ((ServerLevel)this.level).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, this.level.getBlockState( new BlockPos(particlePos).below() )), particlePos.x(), particlePos.y(), particlePos.z(), amountOfParticles, this.getBbWidth() / 4.0F , this.getBbHeight() / 4.0F , this.getBbWidth() / 4.0F , 0.1D);
        }
    }

    @Override
    public InteractionResult mobInteract( Player pPlayer , InteractionHand pHand ){
        if(pPlayer.getItemInHand( pHand ).getItem() == Init.NETHER_CORE_ITEM.get() && !this.hasCore() && bodyInclination >= 1.055f){
            this.coreDelay = 80;
            this.setCore( pPlayer.getItemInHand( pHand ).copy() );
            pPlayer.setItemInHand( pHand, ItemStack.EMPTY );
            if(getCorePercentage() > 2) this.core.hurt( 20, RandomSource.create(), null );
            if(!pPlayer.isShiftKeyDown()) this.setTamed( pPlayer );
            this.level.playSound( pPlayer, this.blockPosition().above(), Init.INSERTING_CORE_GOLEM.get(), SoundSource.NEUTRAL, 0.5F, 1.0F );
            return InteractionResult.SUCCESS;
        } else if (pPlayer.getItemInHand( pHand ).isEmpty() && this.hasCore() && (bodyInclination <= 0.0f || this.getCorePercentage() <= 2)
                && ( this.getOwnerUUID() == null || this.getOwnerUUID().equals( pPlayer.getUUID() ) )) {
            pPlayer.setItemInHand( pHand, this.core.copy() );
            this.setCore( ItemStack.EMPTY );
            if(this.isTame() && this.getOwnerUUID().equals( pPlayer.getUUID() )) this.setUnTamed();
            this.soundFlag = true;
            return InteractionResult.SUCCESS;
        } else if(pPlayer.getItemInHand( pHand ).getItem() == Init.MAGMATIC_OBSIDIAN_ITEM.get()
                && this.getHealth() < this.getMaxHealth()){

            if(!pPlayer.getAbilities().instabuild){
                pPlayer.getItemInHand( pHand ).shrink( 1 );
            }
            this.playSound( SoundEvents.IRON_GOLEM_REPAIR , 1.0f , 1.0f );
            this.heal( 16.0f );

            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    public boolean isOn(){
        return this.entityData.get(isOn);
    }

    public void setOn( boolean on ){
        this.entityData.define(isOn, on);
    }

    @Override
    public boolean save( CompoundTag pCompound ){
        CompoundTag core = new CompoundTag();
        this.core.save( core );
        pCompound.put("CORE", core);
        pCompound.putBoolean("ON", this.entityData.get(isOn));
        pCompound.putFloat("HEAD_INCLINATION", this.headInclination);
        pCompound.putFloat("BODY_INCLINATION", this.bodyInclination);
        pCompound.putFloat("ALT_BODY_INCLINATION", this.altBodyInclination);
        return super.save( pCompound );
    }

    @Override
    public void load( CompoundTag pCompound ){
        this.setCore( ItemStack.of( (CompoundTag) pCompound.get( "CORE" ) ).copy() );
        this.setOn(pCompound.getBoolean("ON"));
        this.headInclination = pCompound.getFloat("HEAD_INCLINATION");
        this.bodyInclination = pCompound.getFloat("BODY_INCLINATION");
        this.altBodyInclination = pCompound.getFloat("ALT_BODY_INCLINATION");
        super.load( pCompound );
    }

    @Override
    protected void dropEquipment(){
        this.level.addFreshEntity( new ItemEntity( this.level, this.getX(), this.getY(), this.getZ(), this.core.copy() ) );
    }

    @Override
    public void writeSpawnData( FriendlyByteBuf buffer ){
        buffer.writeItem(this.core.copy());
        buffer.writeBoolean(this.entityData.get(isOn));
        buffer.writeFloat(this.headInclination);
        buffer.writeFloat(this.bodyInclination);
        buffer.writeFloat(this.altBodyInclination);
    }

    @Override
    public void readSpawnData( FriendlyByteBuf additionalData ){
        this.setCore( additionalData.readItem().copy() );
        this.setOn(additionalData.readBoolean());
        this.headInclination = additionalData.readFloat();
        this.bodyInclination = additionalData.readFloat();
        this.altBodyInclination = additionalData.readFloat();
    }

    @Override
    public Packet <?> getAddEntityPacket(){
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound( DamageSource p_184601_1_ ){ return Init.ENRAGED_MAGMATIC_GOLEM_HURT.get(); }

    @Nullable
    @Override
    protected SoundEvent getDeathSound(){ return Init.ENRAGED_MAGMATIC_GOLEM_DIES.get(); }

    public boolean doHurtTarget( Entity pEntity) {
        this.attackAnimationTick = 10;
        this.level.broadcastEntityEvent(this, (byte)4);
        float f = this.getAttackDamage();
        float f1 = (int)f > 0 ? f / 2.0F + (float)this.random.nextInt((int)f) : f;
        boolean flag = pEntity.hurt( DamageSource.mobAttack(this), f1);
        if (flag) {
            boolean flag1 = new Random().nextInt(20) == 5;
            pEntity.setDeltaMovement(pEntity.getDeltaMovement().add(this.getLookAngle().x() * 1.7F * (flag1 ? 2.0F : 1.0F), 0.7F , this.getLookAngle().z() * 1.7F * (flag1 ? 2.0F : 1.0F)));
            this.doEnchantDamageEffects(this, pEntity);
            for (Entity entity : pEntity.level.getEntities(pEntity, new AABB( pEntity.blockPosition().east(2).south(2).below(), pEntity.blockPosition().north(2).west(2).above() ) )){
                if(!(entity instanceof EnragedMagmaticGolemEntity)){
                    boolean flag2 = !(entity instanceof ItemEntity);
                    if(flag2) entity.hurt( DamageSource.mobAttack(this), f1/8.0F);
                    float v = 1.2F * (flag1 ? 2.0F : 1.0F);
                    entity.setDeltaMovement( pEntity.getDeltaMovement().add( this.getLookAngle().x() * v , 0.7F , this.getLookAngle().z() * v ) );
                }
            }
        }
        boolean fireFlag = new Random().nextInt(100) < 10;
        if (fireFlag) pEntity.setSecondsOnFire( 10 );

        this.playSound( Init.ENRAGED_MAGMATIC_GOLEM_ATTACK.get(), 0.7F, 1.0F);
        return flag;
    }


    private float getAttackDamage() {
        return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE) * (this.getCorePercentage() / 100.0F);
    }

    @Override
    public void aiStep(){
        super.aiStep();
        if (this.attackAnimationTick > 0) {
            --this.attackAnimationTick;
        }
        if (this.rangedAttackAnimationTick > 0) {
            --this.rangedAttackAnimationTick;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte pId) {
        if (pId == 4) {
            this.attackAnimationTick = 10;
            this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        } else if(pId == 5){
            this.rangedAttackAnimationTick = 20;
        }else{
            super.handleEntityEvent(pId);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public int getAttackAnimationTick(){
        return this.attackAnimationTick;
    }

    public void performPreRangedAttackAnimation(){
        this.rangedAttackAnimationTick = 20;
        this.level.broadcastEntityEvent( this, (byte) 5 );
    }

    @OnlyIn(Dist.CLIENT)
    public int getRangedAttackAnimationTick(){
        return this.rangedAttackAnimationTick;
    }

    /**
     * Deprecated call, overriding/implementing is fine.
     */
    @Deprecated
    public void setTamed( Player pPlayer ){
        this.tame( pPlayer );
        this.navigation.stop();
        this.setTarget( null );
        this.level.broadcastEntityEvent( this , (byte) 7 );
    }

    /**
     * Deprecated call, overriding/implementing is fine.
     */
    @Deprecated
    public void setUnTamed(){
        this.setTame( false );
        this.setOwnerUUID(null);
    }

    @Override
    public boolean fireImmune(){
        return true;
    }

    @Override
    protected void playStepSound( BlockPos p_180429_1_ , BlockState p_180429_2_ ){
        this.playSound( Init.ENRAGED_MAGMATIC_GOLEM_STEP.get(), 0.4F , 1.0F);
        super.playStepSound( p_180429_1_ , p_180429_2_ );
    }

    @Override
    public void checkDespawn(){
    }

    // Custom Ranged Attack Goal
    public class SpikeAttackGoal extends Goal{

        private final EnragedMagmaticGolemEntity golem;
        private final float distanceToActivate;
        private long lastCanUseCheck = 0L;
        private int animationDelay;

        public SpikeAttackGoal(EnragedMagmaticGolemEntity pEntity, float pDistance){
            this.golem = pEntity;
            this.distanceToActivate = pDistance;
        }

        @Override
        public boolean canUse(){
            LivingEntity target = golem.getTarget();
            if(this.golem.isDelayElapsed() && this.golem.getCorePercentage() >= 50){
                if((this.golem.level.getGameTime() - this.lastCanUseCheck) > 100L){
                    if(target == null){
                        return false;
                    }else if(!target.isAlive()){
                        return false;
                    }else if(golem.distanceTo( target ) >= this.distanceToActivate && isReachable( target )){
                        this.lastCanUseCheck = this.golem.level.getGameTime();
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public boolean canContinueToUse(){
            LivingEntity target = golem.getTarget();
            return this.animationDelay > 0 || (target != null && !target.isAlive() && golem.distanceTo( target ) >= this.distanceToActivate && isReachable( target ) && this.golem.getCorePercentage() >= 50);
        }

        @Override
        public void start(){
            this.golem.setAggressive( true );
            this.animationDelay = 10;
            golem.performPreRangedAttackAnimation();
            if(!this.golem.level.isClientSide()) this.golem.level.playSound(null, this.golem.blockPosition(), Init.GROUND_PUNCH.get() , SoundSource.NEUTRAL , 1.0F , 1.0F);
        }

        @Override
        public void stop(){
            if(animationDelay <= 0){
                LivingEntity livingentity = this.golem.getTarget();
                if(!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test( livingentity )){
                    this.golem.setTarget( null );
                }
                this.golem.setAggressive( false );
                this.golem.getNavigation().stop();
            }
        }

        @Override
        public void tick(){
            this.animationDelay = Math.max(0, --animationDelay);
            this.golem.navigation.stop();
            if(this.animationDelay == 0){
                if( this.golem.getTarget() != null){
                    performAttack( this.golem.getTarget() , this.golem.distanceTo( this.golem.getTarget() ) );
                    this.golem.spawnHitParticles( 100 );
                }
            }
        }

        private void performAttack( LivingEntity target, double distanceToTarget ){
            this.golem.getCore().hurt( 3, RandomSource.create(), null );
            float f = (float)Mth.atan2(target.getZ() - this.golem.getZ(), target.getX() - this.golem.getX());
            for(int l = 0; l < distanceToTarget; ++l) {
                double d2 = 1.25D * (l - 3.0D);
                int j = 1 * l;
                if(!this.golem.level.isClientSide()){
                    BlockPos pos = new BlockPos( this.golem.getX() + (double)Mth.cos(f) * d2, target.getY() - 1.0D,this.golem.getZ() + (double)Mth.sin(f) * d2 );
                    if(this.golem.level.getBlockState(pos).isFaceSturdy(target.level, pos, Direction.UP)){
                        ((ServerLevel) this.golem.level).sendParticles( new BlockParticleOption( ParticleTypes.BLOCK ,
                                        target.level.getBlockState( pos ) ) ,
                                this.golem.getX() + (double) Mth.cos( f ) * d2 , target.getY() - 0.25D , this.golem.getZ() + (double) Mth.sin( f ) * d2
                                , (int) (distanceToTarget - l) , 0 , 0 , 0 , (distanceToTarget - l) * 2 );
                        if(target.level.getBlockState( pos ).getBlock() != Blocks.AIR){
                            ((ServerLevel) this.golem.level).sendParticles( ParticleTypes.REVERSE_PORTAL ,
                                    this.golem.getX() + (double) Mth.cos( f ) * d2 , target.getY() - 0.25D , this.golem.getZ() + (double) Mth.sin( f ) * d2
                                    , (int) distanceToTarget , 0 , 0 , 0 , 0.5F );
                        }
                        target.level.playSound( null, pos, Init.ENRAGED_GOLEM_SPIKE_ATTACK.get(), SoundSource.BLOCKS, 0.7F, 1.0F );
                    } else if(this.golem.level.getBlockState(pos.below()).isFaceSturdy(target.level, pos.below(), Direction.UP)){
                        ((ServerLevel) this.golem.level).sendParticles( new BlockParticleOption( ParticleTypes.BLOCK ,
                                        target.level.getBlockState( pos.below() ) ) ,
                                this.golem.getX() + (double) Mth.cos( f ) * d2 , target.getY() - 1.25D , this.golem.getZ() + (double) Mth.sin( f ) * d2
                                , (int) (distanceToTarget - l) , 0 , 0 , 0 , (distanceToTarget - l) * 2 );
                        if(target.level.getBlockState( pos.below() ).getBlock() != Blocks.AIR){
                            ((ServerLevel) this.golem.level).sendParticles( ParticleTypes.REVERSE_PORTAL ,
                                    this.golem.getX() + (double) Mth.cos( f ) * d2 , target.getY() - 1.25D , this.golem.getZ() + (double) Mth.sin( f ) * d2
                                    , (int) distanceToTarget , 0 , 0 , 0 , 0.5F );
                        }
                        target.level.playSound( null, pos.below(), Init.ENRAGED_GOLEM_SPIKE_ATTACK.get(), SoundSource.BLOCKS, 0.7F, 1.0F );
                    }
                }
            }
            for(int i = 0; i < 5; ++i) {
                float f1 = f + (float)i * (float)Math.PI * 0.4F;
                BlockPos pos = new BlockPos( this.golem.getX() + (double)Mth.cos(f) * 1.25D * (int)(distanceToTarget - 2.0D) + (double)Mth.cos(f1) * 1.9D,
                        target.blockPosition().getY() ,this.golem.getZ() + (double)Mth.sin(f) * (int)(distanceToTarget - 2.0D) + (double)Mth.sin(f1) * 1.9D );
                if(target.level.getBlockState(pos.below()).isFaceSturdy(target.level, pos, Direction.UP)){
                    target.level.addFreshEntity( EnragedGolemSpikeEntity.createEnragedGolemSpikeEntity( target.level ,
                            this.golem.getX() + (double) Mth.cos( f ) * 1.25D * (int) (distanceToTarget - 2.0D) + (double) Mth.cos( f1 ) * 1.9D ,
                            target.blockPosition().getY() , this.golem.getZ() + (double) Mth.sin( f ) * (int) (distanceToTarget - 2.0D) + (double) Mth.sin( f1 ) * 1.9D , f ) );
                } else if(target.level.getBlockState(pos.below(2)).isFaceSturdy(target.level, pos.below(), Direction.UP)){
                    target.level.addFreshEntity( EnragedGolemSpikeEntity.createEnragedGolemSpikeEntity( target.level ,
                            this.golem.getX() + (double) Mth.cos( f ) * 1.25D * (int) (distanceToTarget - 2.0D) + (double) Mth.cos( f1 ) * 1.9D ,
                            target.blockPosition().below().getY() , this.golem.getZ() + (double) Mth.sin( f ) * (int) (distanceToTarget - 2.0D) + (double) Mth.sin( f1 ) * 1.9D , f ) );
                }
            }
            BlockPos pos = new BlockPos( this.golem.getX() + (double)Mth.cos(f) * 1.25D * (int)(distanceToTarget - 2.0D),
                    target.blockPosition().getY() ,this.golem.getZ() + (double)Mth.sin(f) * (int)(distanceToTarget - 2.0D) );
            if(target.level.getBlockState(pos.below()).isFaceSturdy(target.level, pos, Direction.UP)){
                target.level.addFreshEntity( EnragedGolemSpikeEntity.createEnragedGolemSpikeEntity( target.level ,
                        this.golem.getX() + (double) Mth.cos( f ) * 1.25D * (int) (distanceToTarget - 2.0D) ,
                        target.blockPosition().getY() , this.golem.getZ() + (double) Mth.sin( f ) * (int) (distanceToTarget - 2.0D) , f ) );
            } else if(target.level.getBlockState(pos.below(2)).isFaceSturdy(target.level, pos.below(), Direction.UP)){
                target.level.addFreshEntity( EnragedGolemSpikeEntity.createEnragedGolemSpikeEntity( target.level ,
                        this.golem.getX() + (double) Mth.cos( f ) * 1.25D * (int) (distanceToTarget - 2.0D) ,
                        target.blockPosition().below().getY() , this.golem.getZ() + (double) Mth.sin( f ) * (int) (distanceToTarget - 2.0D) , f ) );
            }
        }

        private boolean isReachable(LivingEntity pTarget) {
            return golem.blockPosition().getY() == pTarget.blockPosition().getY() || golem.blockPosition().below().getY() == pTarget.blockPosition().getY()
                    || golem.blockPosition().below(2).getY() == pTarget.blockPosition().getY() || golem.blockPosition().below(3).getY() == pTarget.blockPosition().getY();
        }

        @Override
        public boolean isInterruptable(){
            return false;
        }
    }
}

