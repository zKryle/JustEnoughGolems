package com.zkryle.jeg.common.tileentities;

import com.zkryle.jeg.common.ICoreOwner;
import com.zkryle.jeg.core.Init;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ChargingTableBlockEntity extends BlockEntity implements ICoreOwner{

    protected ItemStack core = ItemStack.EMPTY;
    private short charge  = 0;
    private short rotationAnim = 0;
    private byte soundTick = 0;

    protected ChargingTableBlockEntity( BlockEntityType <?> typeIn , BlockPos pWorldPosition , BlockState pBlockState ){
        super( typeIn , pWorldPosition , pBlockState );
    }

    public ChargingTableBlockEntity( BlockPos pWorldPosition , BlockState pBlockState ){
        this( Init.CHARGING_TABLE_BLOCK_ENTITY_TYPE.get() , pWorldPosition , pBlockState );
    }

    public static void tick( Level level , BlockPos pos , BlockState state , ChargingTableBlockEntity blockEntity ){
        if(level.isClientSide()){
            if(blockEntity.rotationAnim < 360){
                if(blockEntity.getCorePercentage() > 10){
                    blockEntity.rotationAnim += blockEntity.getCorePercentage() / 10;
                }
            }else{
                blockEntity.resetRotationAnim();
            }
        }

        if(blockEntity.hasCore()){
            if(blockEntity.charge > 0){
                if(blockEntity.getCore().getDamageValue() > 0){
                    blockEntity.getCore().setDamageValue( blockEntity.core.getDamageValue() - 1 );
                }
                blockEntity.charge--;
            }
            if(blockEntity.soundTick == 0){
                blockEntity.playLoop();
                blockEntity.soundTick = 105;
            }
            blockEntity.soundTick = (byte) Math.max( 0 , --blockEntity.soundTick );
        } else {
            if(blockEntity.soundTick == 0){
                blockEntity.playLoopEnd();
            }
            blockEntity.soundTick = (byte) Math.max( -1 , --blockEntity.soundTick );
        }

    }

    @Override
    protected void saveAdditional( CompoundTag pCompound ){
        CompoundTag core = new CompoundTag();
        this.core.save( core );
        pCompound.putShort( "CHARGE" , this.charge );
        pCompound.put( "CORE" , core );
    }

    @Override
    public void load( CompoundTag pCompound ){
        this.setCore( ItemStack.of( (CompoundTag) pCompound.get( "CORE" ) ).copy() );
        this.setCharge( pCompound.getShort( "CHARGE" ) );
        super.load( pCompound );
    }

    @Override
    public CompoundTag getUpdateTag(){
        return this.saveWithoutMetadata();
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket(){
        return ClientboundBlockEntityDataPacket.create( this );
    }

    @Override
    public boolean hasCore(){
        return !this.core.isEmpty();
    }

    @Override
    public boolean hasCoreClient() {
        return false;
    }

    @Override
    public ItemStack getCore(){
        return this.core;
    }

    @Override
    public void setCore( ItemStack core ){
        this.core = core;
        this.setChanged();
        if(!core.isEmpty()) soundTick = 0;
    }

    @Override
    public boolean isDelayElapsed(){
        return false;
    }

    @Override
    public float getCorePercentageClient() {
        return 0;
    }

    @OnlyIn(Dist.CLIENT)
    public short getRotationAnim(){
        return this.rotationAnim;
    }

    @OnlyIn(Dist.CLIENT)
    public void resetRotationAnim(){
        this.rotationAnim = 0;
    }

    public short getCharge(){
        return this.charge;
    }

    public void setCharge( short charge ){
        this.charge = charge;
    }

    private void playLoop(){
        this.level.playSound( null , this.getBlockPos() ,
                                                             Init.CHARGING_STATION_LOOP.get() , SoundSource.BLOCKS ,
                                                             0.05F , 1.0F );
    }

    private void playLoopEnd(){
        this.level.playSound( null , this.getBlockPos() ,
                                                             Init.CHARGING_STATION_LOOP_END.get() ,
                                                             SoundSource.BLOCKS , 0.05F , 1.0F );
    }
}
