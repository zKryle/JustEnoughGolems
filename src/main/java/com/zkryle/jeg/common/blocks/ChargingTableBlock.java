package com.zkryle.jeg.common.blocks;

import com.zkryle.jeg.common.tileentities.ChargingTableTileEntity;
import com.zkryle.jeg.core.Init;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;

public class ChargingTableBlock extends Block {

    public ChargingTableBlock( Properties p_i48440_1_ ){
        super( p_i48440_1_ );
    }

    @Override
    public boolean hasTileEntity( BlockState state ){
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity( BlockState state , IBlockReader world ){
        return Init.CHARGING_TABLE_TILE_ENTITY_TYPE.get().create();
    }


    @Override
    public VoxelShape getShape( BlockState pState , IBlockReader pLevel , BlockPos pPos , ISelectionContext pContext ){
        return Block.box(0.5d, 0.0d, 0.5d, 15.5d, 15.0d, 15.5d);
    }

    @Override
    public ActionResultType use( BlockState pState , World pLevel , BlockPos pPos , PlayerEntity pPlayer , Hand pHand , BlockRayTraceResult pHit ){
        ChargingTableTileEntity te = (ChargingTableTileEntity) pLevel.getBlockEntity( pPos );
        if(te.hasCore() && pPlayer.getItemInHand( pHand ).isEmpty() && (!(te.getCharge() > 0) || te.getCore().getDamageValue() == 0)) {
            pPlayer.setItemInHand( pHand, te.getCore().copy() );
            te.setCore( ItemStack.EMPTY );
            if(pLevel.isClientSide()) te.resetRotationAnim();
            pLevel.playSound( pPlayer, pPos, Init.EXTRACTING_CORE.get(), SoundCategory.BLOCKS, 1.0F, 1.0F );
            return ActionResultType.SUCCESS;
        } else if(!te.hasCore() && pPlayer.getItemInHand( pHand ).getItem() == Init.NETHER_CORE_ITEM.get()){
            if(0 == pPlayer.getItemInHand( pHand ).getDamageValue()){
                return ActionResultType.PASS;
            } else{
                pLevel.playSound( pPlayer, pPos, Init.INSERTING_CORE.get(), SoundCategory.BLOCKS, 1.0F, 1.0F );
                te.setCore( pPlayer.getItemInHand( pHand ).copy() );
                pPlayer.setItemInHand( pHand , ItemStack.EMPTY );
                return ActionResultType.SUCCESS;
            }
        } else if(pPlayer.getItemInHand( pHand ).getItem() == Items.LAVA_BUCKET && te.getCharge() < 1200){
            te.setCharge( (short) (te.getCharge() + 300) );
            pPlayer.setItemInHand( pHand , Items.LAVA_BUCKET.getContainerItem( pPlayer.getItemInHand( pHand ) ) );
            pLevel.playSound( pPlayer, pPos, Init.CHARGING_STATION_LAVA.get(), SoundCategory.BLOCKS, 1.0F, 1.0F );
            if(!pLevel.isClientSide()){
                ((ServerWorld)pLevel).sendParticles( new BlockParticleData( ParticleTypes.BLOCK, Blocks.LAVA.defaultBlockState() ) , pPos.getX() + 0.5F, pPos.getY(), pPos.getZ() + 0.5F,
                        40, 0, 1.0F, 0, 20);
            }
            return ActionResultType.SUCCESS;
        }
        return super.use( pState , pLevel , pPos , pPlayer , pHand , pHit );
    }
    
}
