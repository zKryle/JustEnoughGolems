package com.zkryle.jeg.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.ObserverBlock;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

import javax.annotation.Nullable;

public class MossyObserver extends ObserverBlock{
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public MossyObserver( Properties pProperties ){
        super( pProperties );
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.SOUTH).setValue(POWERED, Boolean.FALSE ));
    }

    @Override
    public BlockState updateShape( BlockState pState , Direction pFacing , BlockState pFacingState , IWorld pLevel , BlockPos pCurrentPos , BlockPos pFacingPos ){
        if (pState.getValue(FACING) == pFacing && !pState.getValue(POWERED)) {
            if(pLevel.getBlockState( pCurrentPos.relative( pFacing ) ).getBlock() instanceof CropsBlock){
                CropsBlock pCropBlock = (CropsBlock) pLevel.getBlockState( pCurrentPos.relative( pFacing ) ).getBlock();
                if(pCropBlock.isMaxAge( pLevel.getBlockState( pCurrentPos.relative( pFacing ) ) )) this.startSignal( pLevel , pCurrentPos );
            }
        }
        return pState;
    }

    @Override
    public boolean canConnectRedstone( BlockState state , IBlockReader world , BlockPos pos , @Nullable Direction side ){
        return state.getValue(FACING) == side;
    }
}
