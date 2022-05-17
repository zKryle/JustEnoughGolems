package com.zkryle.jeg.common.golem;

import com.zkryle.jeg.core.Init;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;

public class EnragedGolemSpikeEntity extends Entity{

    private int lifeTicks = 32;
    private float raiseAnim = 0.0F;

    public EnragedGolemSpikeEntity( EntityType <EnragedGolemSpikeEntity> p_i48580_1_ , Level p_i48580_2_ ){
        super( p_i48580_1_ , p_i48580_2_ );
    }

    private EnragedGolemSpikeEntity( Level p_i47276_1_ , double p_i47276_2_ , double p_i47276_4_ , double p_i47276_6_ , float p_i47276_8_ ){
        this( Init.ENRAGED_GOLEM_SPIKE_ENTITY.get() , p_i47276_1_ );
        this.yRotO = p_i47276_8_ * (180F / (float) Math.PI);
        this.setPos( p_i47276_2_ , p_i47276_4_ , p_i47276_6_ );
    }

    public static EnragedGolemSpikeEntity createEnragedGolemSpikeEntity( Level level , double p_i47276_2_ , double p_i47276_4_ , double p_i47276_6_ , float p_i47276_8_ ){
        return new EnragedGolemSpikeEntity( level , p_i47276_2_ , p_i47276_4_ , p_i47276_6_ , p_i47276_8_ );
    }

    @Override
    public void tick(){
        super.tick();
        if(!this.level.isClientSide()){
            if(lifeTicks == 32){
                this.level.playSound( null, this.blockPosition(), Init.ENRAGED_GOLEM_SPIKE_SOUND.get(), SoundSource.HOSTILE, 0.7F, 1.0F );
            }
            for(LivingEntity livingentity : this.level.getEntitiesOfClass( LivingEntity.class , this.getBoundingBox().inflate( 0.2D , 0.0D , 0.2D ) )){
                this.dealDamageTo( livingentity );
            }
        }
        if(--this.lifeTicks < 0){
            this.remove(RemovalReason.DISCARDED);
        }
        if(this.raiseAnim < 1.0F){
            this.raiseAnim += 0.1F;
        }
    }

    private void dealDamageTo( LivingEntity p_190551_1_ ){
        if(p_190551_1_.isAlive() && !p_190551_1_.isInvulnerable() && !(p_190551_1_ instanceof EnragedMagmaticGolemEntity)){
            p_190551_1_.hurt( DamageSource.MAGIC , 4.0F );
        }
    }

    @OnlyIn(Dist.CLIENT)
    public float getAnimationProgress( float pPartialTicks ){
        int i = this.lifeTicks - 2;
        return i <= 0 ? 1.0F : 1.0F - ((float) i - pPartialTicks) / 20.0F;
    }

    @OnlyIn( Dist.CLIENT )
    public float getRaiseAnim(){
        return raiseAnim;
    }

    @Override
    protected void defineSynchedData(){

    }

    @Override
    protected void readAdditionalSaveData( CompoundTag pCompound ){

    }

    @Override
    protected void addAdditionalSaveData( CompoundTag pCompound ){

    }

    @Override
    public Packet <?> getAddEntityPacket(){
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
