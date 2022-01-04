package com.zkryle.jeg.core;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class JEGItemGroup extends CreativeModeTab{
    public static final JEGItemGroup JEG_ITEM_GROUP = new JEGItemGroup(
            CreativeModeTab.TABS.length, "jeg_itemgroup");

    public JEGItemGroup( int length , String label ){
        super( length, label );
    }

    @Override
    public ItemStack makeIcon(){
        return Init.PRECIOUS_MOSSY_COBBLESTONE.get().asItem().getDefaultInstance();
    }
}
