package com.zkryle.jeg.common.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.world.Explosion;

public class MagmaticCharm extends Item{
    public MagmaticCharm( Properties pProperties ){
        super( pProperties );
    }

    @Override
    public ActionResultType useOn( ItemUseContext pContext ){
        pContext.getLevel().explode( pContext.getPlayer(), pContext.getClickedPos().getX(), pContext.getClickedPos().getY(), pContext.getClickedPos().getZ(),
                3, true, Explosion.Mode.DESTROY);
        pContext.getItemInHand().shrink( 1 );
        return ActionResultType.SUCCESS;
    }
}
