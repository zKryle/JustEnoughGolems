package com.zkryle.jeg.common.tileentities;

import com.zkryle.jeg.client.sounds.ChargingTableTickableSound;
import com.zkryle.jeg.common.ICoreOwner;
import com.zkryle.jeg.core.Init;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ChargingTableTileEntity extends TileEntity implements ITickableTileEntity, ICoreOwner{

    protected ItemStack core = ItemStack.EMPTY;
    private short charge = 0;
    private short rotationAnim = 0;
    private boolean startedLoop = false;

    protected ChargingTableTileEntity( TileEntityType <?> typeIn ){
        super( typeIn );
    }

    public ChargingTableTileEntity(){
        this( Init.CHARGING_TABLE_TILE_ENTITY_TYPE.get() );
    }

    @Override
    public CompoundNBT save( CompoundNBT pCompound ){
        CompoundNBT core = new CompoundNBT();
        this.core.save( core );
        pCompound.putShort( "CHARGE", this.charge );
        pCompound.put("CORE", core);
        return super.save( pCompound );
    }

    @Override
    public void load( BlockState pState , CompoundNBT pCompound ){
            this.setCore( ItemStack.of( (CompoundNBT) pCompound.get( "CORE" ) ).copy() );
            this.setCharge( pCompound.getShort( "CHARGE" ) );
            super.load( pState, pCompound );
    }

    @Override
    public CompoundNBT getUpdateTag()
    {
        return this.save(new CompoundNBT());
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket()
    {
        CompoundNBT nbt = new CompoundNBT();
        this.save(nbt);
        this.setChanged();
        return new SUpdateTileEntityPacket(this.getBlockPos(), 0, nbt);
    }

    @Override
    public void onDataPacket( NetworkManager net, SUpdateTileEntityPacket packet){
        CompoundNBT tag = packet.getTag();
        this.load(level.getBlockState( worldPosition ), tag);
        this.setChanged();
        level.sendBlockUpdated(worldPosition, level.getBlockState(worldPosition).getBlock().defaultBlockState(), level.getBlockState(worldPosition), 2);
    }

    @Override
    public void tick(){
        if(rotationAnim < 360){
            if(this.getCorePercentage() > 10){
                rotationAnim += getCorePercentage() / 10;
            }
        } else {
            resetRotationAnim();
        }

        if(this.hasCore()){
            if(this.charge > 0){
                if(this.getCore().getDamageValue() > 0){
                    this.getCore().setDamageValue( core.getDamageValue() - 1 );
                }
                charge--;
            }

            if(!this.startedLoop){
                Minecraft.getInstance().getSoundManager().play( new ChargingTableTickableSound( this ) );
                this.startedLoop = true;
            }
        } else this.startedLoop = false;

    }

    @Override
    public boolean hasCore(){
        return !this.core.isEmpty();
    }

    @Override
    public void setCore( ItemStack core ){
        if(core.isEmpty()) this.playLoopEnd();
        this.core = core;
    }

    @Override
    public ItemStack getCore(){
        return this.core;
    }

    @Override
    public boolean isDelayElapsed(){
        return false;
    }

    @OnlyIn( Dist.CLIENT )
    public short getRotationAnim(){
        return this.rotationAnim;
    }

    @OnlyIn( Dist.CLIENT )
    public void resetRotationAnim(){
        this.rotationAnim = 0;
    }

    public void setCharge (short charge){
        this.charge = charge;
    }

    public short getCharge(){
        return this.charge;
    }

    private void playLoopEnd(){
        if(!this.level.isClientSide()) this.level.playSound( null, this.getBlockPos(), Init.CHARGING_STATION_LOOP_END.get(), SoundCategory.BLOCKS, 0.05F, 1.0F );
    }
}
