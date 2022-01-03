package com.zkryle.jeg.client.events;

import com.zkryle.jeg.JustEnoughGolems;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JustEnoughGolems.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class StaticClientEventSubscriber{
    public static ResourceLocation RECHARGING_CORE = new ResourceLocation( JustEnoughGolems.MOD_ID, "item/special/recharging_core" );
    public static ResourceLocation FULL_CORE = new ResourceLocation( JustEnoughGolems.MOD_ID, "item/special/full_core" );

    @SubscribeEvent
    public static void registerAdditionalModels( ModelRegistryEvent event ){
        ModelLoader.addSpecialModel( RECHARGING_CORE );
        ModelLoader.addSpecialModel( FULL_CORE );
    }
}
