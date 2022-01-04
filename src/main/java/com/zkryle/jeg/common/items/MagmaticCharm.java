package com.zkryle.jeg.common.items;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Explosion;

public class MagmaticCharm extends Item{
    public MagmaticCharm( Properties pProperties ){
        super( pProperties );
    }

    @Override
    public InteractionResult useOn( UseOnContext pContext ){
        if(!pContext.getLevel().isClientSide())pContext.getLevel().explode( pContext.getPlayer(), pContext.getClickedPos().getX(), pContext.getClickedPos().getY(), pContext.getClickedPos().getZ(),
                3, true, Explosion.BlockInteraction.DESTROY);
        pContext.getItemInHand().shrink( 1 );
        return InteractionResult.SUCCESS;
    }
}
