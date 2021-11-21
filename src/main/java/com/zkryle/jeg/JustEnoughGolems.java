package com.zkryle.jeg;

import com.zkryle.jeg.common.events.EventSubscriber;
import com.zkryle.jeg.core.Init;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(JustEnoughGolems.MOD_ID)
public class JustEnoughGolems
{
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "justenoughgolems";

    public JustEnoughGolems() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        Init.SOUNDS.register( bus );
        Init.BLOCKS.register( bus );
        Init.ITEMS.register( bus );
        Init.ENTITIES.register( bus );


        MinecraftForge.EVENT_BUS.register(this);
        bus.register( new EventSubscriber() );
    }
}
