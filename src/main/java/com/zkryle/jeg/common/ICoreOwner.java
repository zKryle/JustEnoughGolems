package com.zkryle.jeg.common;

import net.minecraft.world.item.ItemStack;

public interface ICoreOwner {
    boolean hasCore();

    boolean hasCoreClient();
    void setCore( ItemStack core);
    ItemStack getCore();
    boolean isDelayElapsed();
    default float getCorePercentage(){
        return this.hasCore() ? ((float)(this.getCore().getMaxDamage() - this.getCore().getDamageValue()) / (float)this.getCore().getMaxDamage()) * 100.0F : 0.0F;
    }

    float getCorePercentageClient();

}
