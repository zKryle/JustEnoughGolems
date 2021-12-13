package com.zkryle.jeg.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.ObserverBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import javax.annotation.Nullable;

public class MossyObserver extends ObserverBlock{
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public MossyObserver( Properties pProperties ){
        super( pProperties );
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.SOUTH).setValue(POWERED, Boolean.FALSE ));
    }

    @Override
    public BlockState updateShape( BlockState pState , Direction pFacing , BlockState pFacingState , LevelAccessor pLevel , BlockPos pCurrentPos , BlockPos pFacingPos ){
        if (pState.getValue(FACING) == pFacing && !pState.getValue(POWERED)) {
            if(pLevel.getBlockState( pCurrentPos.relative( pFacing ) ).getBlock() instanceof CropBlock){
                CropBlock pCropBlock = (CropBlock) pLevel.getBlockState( pCurrentPos.relative( pFacing ) ).getBlock();
                if(pCropBlock.isMaxAge( pLevel.getBlockState( pCurrentPos.relative( pFacing ) ) )) this.startSignal( pLevel , pCurrentPos );
            }
        }
        return pState;
    }

    @Override
    public boolean canConnectRedstone( BlockState state , BlockGetter world , BlockPos pos , @Nullable Direction side ){
        return state.getValue(FACING) == side;
    }
}
