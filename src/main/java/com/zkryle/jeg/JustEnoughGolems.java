package com.zkryle.jeg;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mojang.authlib.GameProfile;
import com.zkryle.jeg.common.JEGFakePlayer;
import com.zkryle.jeg.common.events.EventSubscriber;
import com.zkryle.jeg.core.Init;

import net.minecraft.world.server.ServerWorld;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(JustEnoughGolems.MOD_ID)
public class JustEnoughGolems
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "justenoughgolems";
    
    public static final GameProfile GAME_PROFILE = new GameProfile(UUID.nameUUIDFromBytes("jeg.common".getBytes(StandardCharsets.UTF_8)), "[JustEnoughGolems]");

    public JustEnoughGolems() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        Init.SOUNDS.register( bus );
        Init.BLOCKS.register( bus );
        Init.ITEMS.register( bus );
        Init.ENTITIES.register( bus );

        LOGGER.info("Fake player readout: UUID = {}, name = {}", GAME_PROFILE.getId(), GAME_PROFILE.getName());
        
        MinecraftForge.EVENT_BUS.register(this);
        bus.register( new EventSubscriber() );
    }
    
    @SubscribeEvent
    public void onWorldUnload(final WorldEvent.Unload event) {
    	// Drop any reference of the fake player to the world, to allow unloading
        if (event.getWorld() instanceof ServerWorld) {
            JEGFakePlayer.releaseInstance(event.getWorld());
        }
    }
    
    @SubscribeEvent
    public void onEntityPlace(final BlockEvent.EntityPlaceEvent event) {
    	event.setCanceled(false);
    }
}
