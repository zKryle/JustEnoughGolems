package com.zkryle.jeg.common.blocks;

import com.zkryle.jeg.common.tileentities.ChargingTableBlockEntity;
import com.zkryle.jeg.core.Init;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class ChargingTableBlock extends Block implements EntityBlock{

    public ChargingTableBlock( Properties p_i48440_1_ ){
        super( p_i48440_1_ );
    }

    @Override
    public VoxelShape getShape( BlockState pState , BlockGetter pLevel , BlockPos pPos , CollisionContext pContext ){
        return Block.box(0.5d, 0.0d, 0.5d, 15.5d, 15.0d, 15.5d);
    }

    @Override
    public InteractionResult use( BlockState pState , Level pLevel , BlockPos pPos , Player pPlayer , InteractionHand pHand , BlockHitResult pHit ){
        ChargingTableBlockEntity te = (ChargingTableBlockEntity) pLevel.getBlockEntity( pPos );
        if(te.hasCore() && pPlayer.getItemInHand( pHand ).isEmpty() && (!(te.getCharge() > 0) || te.getCore().getDamageValue() == 0)) {
            pPlayer.setItemInHand( pHand, te.getCore().copy() );
            te.setCore( ItemStack.EMPTY );
            if(pLevel.isClientSide()) te.resetRotationAnim();
            pLevel.playSound( pPlayer, pPos, Init.EXTRACTING_CORE.get(), SoundSource.BLOCKS, 1.0F, 1.0F );
            return InteractionResult.SUCCESS;
        } else if(!te.hasCore() && pPlayer.getItemInHand( pHand ).getItem() == Init.NETHER_CORE_ITEM.get()){
            if(0 == pPlayer.getItemInHand( pHand ).getDamageValue()){
                return InteractionResult.PASS;
            } else{
                pLevel.playSound( pPlayer, pPos, Init.INSERTING_CORE.get(), SoundSource.BLOCKS, 1.0F, 1.0F );
                te.setCore( pPlayer.getItemInHand( pHand ).copy() );
                pPlayer.setItemInHand( pHand , ItemStack.EMPTY );
                return InteractionResult.SUCCESS;
            }
        } else if(pPlayer.getItemInHand( pHand ).getItem() == Items.LAVA_BUCKET && te.getCharge() < 1200){
            te.setCharge( (short) (te.getCharge() + 300) );
            pPlayer.setItemInHand( pHand , Items.LAVA_BUCKET.getCraftingRemainingItem( pPlayer.getItemInHand( pHand ) ) );
            pLevel.playSound( pPlayer, pPos, Init.CHARGING_STATION_LAVA.get(), SoundSource.BLOCKS, 1.0F, 1.0F );
            if(!pLevel.isClientSide()){
                ((ServerLevel)pLevel).sendParticles( new BlockParticleOption( ParticleTypes.BLOCK, Blocks.LAVA.defaultBlockState() ) , pPos.getX() + 0.5F, pPos.getY(), pPos.getZ() + 0.5F,
                        40, 0, 1.0F, 0, 20);
            }
            return InteractionResult.SUCCESS;
        }
        return super.use( pState , pLevel , pPos , pPlayer , pHand , pHit );
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity( BlockPos pPos , BlockState pState ){
        return new ChargingTableBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker <T> getTicker( Level pLevel , BlockState pState , BlockEntityType <T> pBlockEntityType ){
        return createTickerHelper(pBlockEntityType, Init.CHARGING_TABLE_BLOCK_ENTITY_TYPE.get(), ChargingTableBlockEntity::tick);
    }

    @Nullable
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> p_152133_, BlockEntityType<E> p_152134_, BlockEntityTicker<? super E> p_152135_) {
        return p_152134_ == p_152133_ ? (BlockEntityTicker<A>)p_152135_ : null;
    }
}
