package com.zkryle.jeg;

import com.mojang.authlib.GameProfile;
import com.zkryle.jeg.common.events.EventSubscriber;
import com.zkryle.jeg.core.Config;
import com.zkryle.jeg.core.Init;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(JustEnoughGolems.MOD_ID)
public class JustEnoughGolems
{
    public static final GameProfile GAME_PROFILE = new GameProfile( UUID.nameUUIDFromBytes("jeg.common".getBytes( StandardCharsets.UTF_8)), "[JustEnoughGolems]");

    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "justenoughgolems";

    public JustEnoughGolems() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModLoadingContext.get().registerConfig( ModConfig.Type.COMMON, Config.SPEC, "JustEnoughGolems-common.toml" );

        Init.SOUNDS.register( bus );
        Init.BLOCKS.register( bus );
        Init.ITEMS.register( bus );
        Init.ENTITIES.register( bus );
        Init.BLOCK_ENTITY_TYPES.register( bus );


        MinecraftForge.EVENT_BUS.register(this);
        bus.register( new EventSubscriber() );
    }
}
