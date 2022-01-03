package com.zkryle.jeg.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class MagmaticObsidianBlock extends Block{

    public MagmaticObsidianBlock( Properties p_i48440_1_ ){
        super( p_i48440_1_ );
    }

    @Override
    public ActionResultType use( BlockState pState , World pLevel , BlockPos pPos , PlayerEntity pPlayer , Hand pHand , BlockRayTraceResult pHit ){
        if(pPlayer.getItemInHand( pHand ).getItem() == Items.BUCKET){
            pPlayer.setItemInHand( pHand, new ItemStack(Items.LAVA_BUCKET));
            pLevel.setBlockAndUpdate( pPos, Blocks.CRYING_OBSIDIAN.defaultBlockState() );
            pLevel.playSound( pPlayer, pPos, SoundEvents.BUCKET_FILL_LAVA, SoundCategory.BLOCKS, 1.0F, 1.0F );
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }
}
