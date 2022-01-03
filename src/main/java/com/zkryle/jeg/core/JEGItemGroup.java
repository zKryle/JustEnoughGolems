package com.zkryle.jeg.core;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class JEGItemGroup extends ItemGroup{
    public static final JEGItemGroup JEG_ITEM_GROUP = new JEGItemGroup(
            ItemGroup.TABS.length, "jeg_itemgroup");

    public JEGItemGroup( int length , String label ){
        super( length, label );
    }

    @Override
    public ItemStack makeIcon(){
        return Init.PRECIOUS_MOSSY_COBBLESTONE.get().asItem().getDefaultInstance();
    }
}
