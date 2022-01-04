package com.zkryle.jeg.common;

import com.mojang.authlib.GameProfile;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.network.Connection;
import net.minecraft.network.ConnectionProtocol;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket.RelativeArgument;
import net.minecraft.network.protocol.game.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.common.UsernameCache;
import net.minecraftforge.common.util.FakePlayer;

import javax.annotation.Nonnull;
import javax.crypto.Cipher;
import java.lang.ref.WeakReference;
import java.net.SocketAddress;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;

import static com.zkryle.jeg.JustEnoughGolems.GAME_PROFILE;

/**
 * The fake player used for golems which interact with things in world
 *
 * @author matyrobbrt
 *
 */
public class JEGFakePlayer extends FakePlayer {

	private static WeakReference<JEGFakePlayer> instance;

	private UUID emulatingUUID = null;

	public JEGFakePlayer(ServerLevel world) {
		super(world, new FakeGameProfile());
		((FakeGameProfile) this.getGameProfile()).fakePlayer = this;
	}

	public void setEmulatingUUID(UUID uuid) { emulatingUUID = uuid; }

	@Nonnull
	@Override
	public UUID getUUID() { return emulatingUUID != null ? emulatingUUID : super.getUUID(); }

	@Override
	public boolean canBeAffected(MobEffectInstance pPotioneffect) {
		return false;
	}

	/**
	 * We need to emulate any interaction of golems
	 *
	 * @author matyrobbrt
	 *
	 */
	private static class FakeGameProfile extends GameProfile {

		private JEGFakePlayer fakePlayer = null;

		public FakeGameProfile() {
			super(GAME_PROFILE.getId(), GAME_PROFILE.getName());
		}

		private UUID getEmulatingUUID() { return fakePlayer != null ? fakePlayer.emulatingUUID : null; }

		@Override
		public UUID getId() {
			UUID emulatingUUID = getEmulatingUUID();
			return emulatingUUID != null ? emulatingUUID : super.getId();
		}

		@Override
		public String getName() {
			UUID emulatingUUID = getEmulatingUUID();
			if (emulatingUUID == null) { return super.getName(); }
			// Check the username cache. We are trying to have the owner's name
			String emulatingName = UsernameCache.getLastKnownUsername(emulatingUUID);
			return emulatingName == null ? "JEG Fake Player" : emulatingName;
		}

		@Override
		public boolean equals(final Object o) {
			if (this == o) { return true; }
			if (!(o instanceof GameProfile)) { return false; }

			final GameProfile that = (GameProfile) o;

			if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) { return false; }
			return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
		}

		@Override
		public int hashCode() {
			int result = getId() != null ? getId().hashCode() : 0;
			result = 31 * result + (getName() != null ? getName().hashCode() : 0);
			return result;
		}
	}

	/**
	 * Acquire a Fake Player and accept a function which uses the player <br>
	 *
	 * <b>The player should be garbage collected, so do no store a reference to
	 * it</b>
	 *
	 * @param world              World to set on the fake player
	 * @param fakePlayerExecutor the action that the player should do
	 * @param <R>                Result of a computation
	 *
	 * @return the return value of fakePlayerExecutor
	 */
	public static <R> R withFakePlayer(ServerLevel world, Function<JEGFakePlayer, R> fakePlayerExecutor) {
		JEGFakePlayer actual = instance != null ? instance.get() : null;
		if (actual == null) {
			actual = new JEGFakePlayer(world);
			actual.connection = new FakeNetworkHandler(world.getServer(), actual);
			instance = new WeakReference<>(actual);
		}
		JEGFakePlayer player = actual;
		player.level = world;
		R result = fakePlayerExecutor.apply(player);
		player.emulatingUUID = null;
		player.level = null;
		return result;
	}

	/**
	 * Same as
	 * {@link #withFakePlayer(ServerLevel, Function)}
	 * but sets the fake player's position.
	 *
	 * @param world              World to set on the fake player
	 * @param fakePlayerExecutor the action that the player should do
	 * @param x                  X pos to set
	 * @param y                  Y pos to set
	 * @param z                  Z pos to set
	 * @param <R>                Result of a computation
	 *
	 * @return the return value of fakePlayerConsumer
	 */
	public static <R> R withFakePlayer(ServerLevel world, double x, double y, double z,
			Function<JEGFakePlayer, R> fakePlayerExecutor) {
		return withFakePlayer(world, fakePlayer -> {
			fakePlayer.setPosRaw(x, y, z);
			return fakePlayerExecutor.apply(fakePlayer);
		});
	}

	/**
	 * Releases any reference to a level that is about to get unloaded by garbage
	 * collecting the fake player if it holds the level
	 * 
	 * @param level
	 */
	public static void releaseInstance(LevelAccessor level) {
		// If the fake player has a reference to the world getting unloaded, destroy the
		// player so that the world can still be unloaded
		JEGFakePlayer act = instance != null ? instance.get() : null;
		if (act != null && act.level == level) {
			act.level = null;
		}
	}

	/**
	 * Network handler to prevent NPEs
	 * 
	 * @author matyrobbrt
	 *
	 */
	private static final class FakeNetworkHandler extends ServerGamePacketListenerImpl {

		public FakeNetworkHandler(MinecraftServer server, JEGFakePlayer player) {
			super(server, new FakeNetworkManager(PacketFlow.CLIENTBOUND), player);
		}

		@Override
		public void tick() {
		}

		@Override
		public void resetPosition() {
		}

		@Override
		public void disconnect(Component textComponent) {
		}

		@Override
		public void handlePlayerInput(ServerboundPlayerInputPacket packet) {
		}

		@Override
		public void handleMoveVehicle(ServerboundMoveVehiclePacket packet) {
		}

		@Override
		public void handleAcceptTeleportPacket(ServerboundAcceptTeleportationPacket packet) {
		}

		@Override
		public void handleRecipeBookSeenRecipePacket(ServerboundRecipeBookSeenRecipePacket packet) {
		}

		@Override
		public void handleRecipeBookChangeSettingsPacket(ServerboundRecipeBookChangeSettingsPacket packet) {
		}

		@Override
		public void handleSeenAdvancements(ServerboundSeenAdvancementsPacket packet) {
		}

		@Override
		public void handleCustomCommandSuggestions(ServerboundCommandSuggestionPacket packet) {
		}

		@Override
		public void handleSetCommandBlock(ServerboundSetCommandBlockPacket packet) {
		}

		@Override
		public void handleSetCommandMinecart(ServerboundSetCommandMinecartPacket packet) {
		}

		@Override
		public void handlePickItem(ServerboundPickItemPacket packet) {
		}

		@Override
		public void handleRenameItem(ServerboundRenameItemPacket packet) {
		}

		@Override
		public void handleSetBeaconPacket(ServerboundSetBeaconPacket packet) {
		}

		@Override
		public void handleSetStructureBlock(ServerboundSetStructureBlockPacket packet) {
		}

		@Override
		public void handleSetJigsawBlock(ServerboundSetJigsawBlockPacket packet) {
		}

		@Override
		public void handleJigsawGenerate(ServerboundJigsawGeneratePacket packet) {
		}

		@Override
		public void handleSelectTrade(ServerboundSelectTradePacket packet) {
		}

		@Override
		public void handleEditBook(ServerboundEditBookPacket packet) {
		}

		@Override
		public void handleEntityTagQuery(ServerboundEntityTagQuery packet) {
		}

		@Override
		public void handleBlockEntityTagQuery(ServerboundBlockEntityTagQuery packet) {
		}

		@Override
		public void handleMovePlayer(ServerboundMovePlayerPacket packet) {
		}

		@Override
		public void teleport(double x, double y, double z, float yaw, float pitch) {
		}

		@Override
		public void teleport(double x, double y, double z, float yaw, float pitch, Set<RelativeArgument> relativeSet) {
		}

		@Override
		public void handlePlayerAction(ServerboundPlayerActionPacket packet) {
		}

		@Override
		public void handleUseItemOn(ServerboundUseItemOnPacket packet) {
		}

		@Override
		public void handleUseItem(ServerboundUseItemPacket packet) {
		}

		@Override
		public void handleTeleportToEntityPacket(ServerboundTeleportToEntityPacket packet) {
		}

		@Override
		public void handleResourcePackResponse(ServerboundResourcePackPacket packet) {
		}

		@Override
		public void handlePaddleBoat(ServerboundPaddleBoatPacket packet) {
		}

		@Override
		public void onDisconnect(Component reason) {
		}

		@Override
		public void send(Packet<?> packet) {
		}

		@Override
		public void send(Packet<?> pPacket,
				GenericFutureListener<? extends io.netty.util.concurrent.Future<? super Void>> pFutureListeners) {
		}

		@Override
		public void handleSetCarriedItem(ServerboundSetCarriedItemPacket packet) {
		}

		@Override
		public void handleChat(ServerboundChatPacket packet) {
		}

		@Override
		public void handleAnimate(ServerboundSwingPacket packet) {
		}

		@Override
		public void handlePlayerCommand(ServerboundPlayerCommandPacket packet) {
		}

		@Override
		public void handleInteract(ServerboundInteractPacket packet) {
		}

		@Override
		public void handleClientCommand(ServerboundClientCommandPacket packet) {
		}

		@Override
		public void handleContainerClose(ServerboundContainerClosePacket packet) {
		}

		@Override
		public void handleContainerClick(ServerboundContainerClickPacket packet) {
		}

		@Override
		public void handlePlaceRecipe(ServerboundPlaceRecipePacket packet) {
		}

		@Override
		public void handleContainerButtonClick(ServerboundContainerButtonClickPacket packet) {
		}

		@Override
		public void handleSetCreativeModeSlot(ServerboundSetCreativeModeSlotPacket packet) {
		}

		@Override
		public void handleSignUpdate(ServerboundSignUpdatePacket packet) {
		}

		@Override
		public void handleKeepAlive(ServerboundKeepAlivePacket packet) {
		}

		@Override
		public void handlePlayerAbilities(ServerboundPlayerAbilitiesPacket packet) {
		}

		@Override
		public void handleClientInformation(ServerboundClientInformationPacket packet) {
		}

		@Override
		public void handleCustomPayload(ServerboundCustomPayloadPacket packet) {
		}

		@Override
		public void handleChangeDifficulty(ServerboundChangeDifficultyPacket packet) {
		}

		@Override
		public void handleLockDifficulty(ServerboundLockDifficultyPacket packet) {
		}

		private final static class FakeNetworkManager extends Connection {

			public FakeNetworkManager(PacketFlow packetDirection) {
				super(packetDirection);
			}

			@Override
			public void channelActive(ChannelHandlerContext channelHandlerContext) {
			}

			@Override
			public void setProtocol(ConnectionProtocol newState) {
			}

			@Override
			public void channelInactive(ChannelHandlerContext channelHandlerContext) {
			}

			@Override
			public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) {
			}

			@Override
			protected void channelRead0(ChannelHandlerContext channelHandlerContext, Packet<?> packet) {
			}

			@Override
			public void send(Packet<?> packet) {
			}

			@Override
			public void send(Packet<?> pPacket,
					GenericFutureListener<? extends io.netty.util.concurrent.Future<? super Void>> p_201058_2_) {
			}

			@Override
			public void tick() {
			}

			@Override
			protected void tickSecond() {
			}

			@Override
			public SocketAddress getRemoteAddress() { return null; }

			@Override
			public void disconnect(Component message) {
			}

			@Override
			public boolean isMemoryConnection() { return false; }

			@Override
			public void setEncryptionKey(Cipher splitter, Cipher prepender) {
			}

			@Override
			public boolean isConnected() { return false; }

			@Override
			public boolean isConnecting() { return true; }

			@Override
			public void setReadOnly() {
			}

			@Override
			public void handleDisconnection() {
			}

			@Override
			public io.netty.channel.Channel channel() {
				return null;
			}


		}

	}

}
