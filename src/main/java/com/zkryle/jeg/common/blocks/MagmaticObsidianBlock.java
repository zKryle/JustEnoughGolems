package com.zkryle.jeg.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class MagmaticObsidianBlock extends Block{

    public MagmaticObsidianBlock( Properties p_i48440_1_ ){
        super( p_i48440_1_ );
    }

    @Override
    public InteractionResult use( BlockState pState , Level pLevel , BlockPos pPos , Player pPlayer , InteractionHand pHand , BlockHitResult pHit ){
        if(pPlayer.getItemInHand( pHand ).getItem() == Items.BUCKET){
            pPlayer.setItemInHand( pHand, new ItemStack(Items.LAVA_BUCKET));
            pLevel.setBlockAndUpdate( pPos, Blocks.CRYING_OBSIDIAN.defaultBlockState() );
            pLevel.playSound( pPlayer, pPos, SoundEvents.BUCKET_FILL_LAVA, SoundSource.BLOCKS, 1.0F, 1.0F );
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
