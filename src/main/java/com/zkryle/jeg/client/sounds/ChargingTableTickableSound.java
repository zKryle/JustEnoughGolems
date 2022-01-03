package com.zkryle.jeg.client.sounds;

import com.zkryle.jeg.common.tileentities.ChargingTableTileEntity;
import com.zkryle.jeg.core.Init;
import net.minecraft.client.audio.TickableSound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;

public class ChargingTableTickableSound extends TickableSound{

    ChargingTableTileEntity chargingTableTileEntity;

    public ChargingTableTickableSound( ChargingTableTileEntity chargingTableTileEntity ){
        super( Init.CHARGING_STATION_LOOP.get() , SoundCategory.BLOCKS );
        this.chargingTableTileEntity = chargingTableTileEntity;
        this.looping = true;
        this.delay = 0;
        this.volume = 0.05F;
        this.x = (float)chargingTableTileEntity.getBlockPos().getX();
        this.y = (float)chargingTableTileEntity.getBlockPos().getY();
        this.z = (float)chargingTableTileEntity.getBlockPos().getZ();
    }

    @Override
    public boolean canStartSilent() {
        return true;
    }

    @Override
    public void tick(){
        if(!chargingTableTileEntity.hasCore() || chargingTableTileEntity.isRemoved()) this.stop();
    }
}
