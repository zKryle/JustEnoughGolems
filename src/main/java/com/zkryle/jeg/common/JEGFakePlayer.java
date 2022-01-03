package com.zkryle.jeg.common;

import static com.zkryle.jeg.JustEnoughGolems.GAME_PROFILE;

import java.lang.ref.WeakReference;
import java.net.SocketAddress;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.crypto.Cipher;

import com.mojang.authlib.GameProfile;

import net.minecraft.network.IPacket;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.PacketDirection;
import net.minecraft.network.ProtocolType;
import net.minecraft.network.play.ServerPlayNetHandler;
import net.minecraft.network.play.client.*;
import net.minecraft.network.play.server.SPlayerPositionLookPacket.Flags;
import net.minecraft.potion.EffectInstance;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IWorld;
import net.minecraft.world.server.ServerWorld;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraftforge.common.UsernameCache;
import net.minecraftforge.common.util.FakePlayer;

/**
 * The fake player used for golems which interact with things in world
 *
 * @author matyrobbrt
 *
 */
public class JEGFakePlayer extends FakePlayer {

	private static WeakReference<JEGFakePlayer> instance;

	private UUID emulatingUUID = null;

	public JEGFakePlayer(ServerWorld world) {
		super(world, new FakeGameProfile());
		((FakeGameProfile) this.getGameProfile()).fakePlayer = this;
	}

	public void setEmulatingUUID(UUID uuid) { emulatingUUID = uuid; }

	@Nonnull
	@Override
	public UUID getUUID() { return emulatingUUID != null ? emulatingUUID : super.getUUID(); }

	@Override
	public boolean canBeAffected(EffectInstance pPotioneffect) {
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
	public static <R> R withFakePlayer(ServerWorld world, Function<JEGFakePlayer, R> fakePlayerExecutor) {
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
	 * {@link #withFakePlayer(net.minecraft.world.server.ServerWorld, java.util.function.Function)}
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
	public static <R> R withFakePlayer(ServerWorld world, double x, double y, double z,
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
	public static void releaseInstance(IWorld level) {
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
	private static final class FakeNetworkHandler extends ServerPlayNetHandler {

		public FakeNetworkHandler(MinecraftServer server, JEGFakePlayer player) {
			super(server, new FakeNetworkManager(PacketDirection.CLIENTBOUND), player);
		}

		@Override
		public void tick() {
		}

		@Override
		public void resetPosition() {
		}

		@Override
		public void disconnect(ITextComponent textComponent) {
		}

		@Override
		public void handlePlayerInput(CInputPacket packet) {
		}

		@Override
		public void handleMoveVehicle(CMoveVehiclePacket packet) {
		}

		@Override
		public void handleAcceptTeleportPacket(CConfirmTeleportPacket packet) {
		}

		@Override
		public void handleRecipeBookSeenRecipePacket(CMarkRecipeSeenPacket packet) {
		}

		@Override
		public void handleRecipeBookChangeSettingsPacket(CUpdateRecipeBookStatusPacket packet) {
		}

		@Override
		public void handleSeenAdvancements(CSeenAdvancementsPacket packet) {
		}

		@Override
		public void handleCustomCommandSuggestions(CTabCompletePacket packet) {
		}

		@Override
		public void handleSetCommandBlock(CUpdateCommandBlockPacket packet) {
		}

		@Override
		public void handleSetCommandMinecart(CUpdateMinecartCommandBlockPacket packet) {
		}

		@Override
		public void handlePickItem(CPickItemPacket packet) {
		}

		@Override
		public void handleRenameItem(CRenameItemPacket packet) {
		}

		@Override
		public void handleSetBeaconPacket(CUpdateBeaconPacket packet) {
		}

		@Override
		public void handleSetStructureBlock(CUpdateStructureBlockPacket packet) {
		}

		@Override
		public void handleSetJigsawBlock(CUpdateJigsawBlockPacket packet) {
		}

		@Override
		public void handleJigsawGenerate(CJigsawBlockGeneratePacket packet) {
		}

		@Override
		public void handleSelectTrade(CSelectTradePacket packet) {
		}

		@Override
		public void handleEditBook(CEditBookPacket packet) {
		}

		@Override
		public void handleEntityTagQuery(CQueryEntityNBTPacket packet) {
		}

		@Override
		public void handleBlockEntityTagQuery(CQueryTileEntityNBTPacket packet) {
		}

		@Override
		public void handleMovePlayer(CPlayerPacket packet) {
		}

		@Override
		public void teleport(double x, double y, double z, float yaw, float pitch) {
		}

		@Override
		public void teleport(double x, double y, double z, float yaw, float pitch, Set<Flags> relativeSet) {
		}

		@Override
		public void handlePlayerAction(CPlayerDiggingPacket packet) {
		}

		@Override
		public void handleUseItemOn(CPlayerTryUseItemOnBlockPacket packet) {
		}

		@Override
		public void handleUseItem(CPlayerTryUseItemPacket packet) {
		}

		@Override
		public void handleTeleportToEntityPacket(CSpectatePacket packet) {
		}

		@Override
		public void handleResourcePackResponse(CResourcePackStatusPacket packet) {
		}

		@Override
		public void handlePaddleBoat(CSteerBoatPacket packet) {
		}

		@Override
		public void onDisconnect(ITextComponent reason) {
		}

		@Override
		public void send(IPacket<?> packet) {
		}

		@Override
		public void send(IPacket<?> pPacket,
				GenericFutureListener<? extends io.netty.util.concurrent.Future<? super Void>> pFutureListeners) {
		}

		@Override
		public void handleSetCarriedItem(CHeldItemChangePacket packet) {
		}

		@Override
		public void handleChat(CChatMessagePacket packet) {
		}

		@Override
		public void handleAnimate(CAnimateHandPacket packet) {
		}

		@Override
		public void handlePlayerCommand(CEntityActionPacket packet) {
		}

		@Override
		public void handleInteract(CUseEntityPacket packet) {
		}

		@Override
		public void handleClientCommand(CClientStatusPacket packet) {
		}

		@Override
		public void handleContainerClose(CCloseWindowPacket packet) {
		}

		@Override
		public void handleContainerClick(CClickWindowPacket packet) {
		}

		@Override
		public void handlePlaceRecipe(CPlaceRecipePacket packet) {
		}

		@Override
		public void handleContainerButtonClick(CEnchantItemPacket packet) {
		}

		@Override
		public void handleSetCreativeModeSlot(CCreativeInventoryActionPacket packet) {
		}

		@Override
		public void handleContainerAck(CConfirmTransactionPacket packet) {
		}

		@Override
		public void handleSignUpdate(CUpdateSignPacket packet) {
		}

		@Override
		public void handleKeepAlive(CKeepAlivePacket packet) {
		}

		@Override
		public void handlePlayerAbilities(CPlayerAbilitiesPacket packet) {
		}

		@Override
		public void handleClientInformation(CClientSettingsPacket packet) {
		}

		@Override
		public void handleCustomPayload(CCustomPayloadPacket packet) {
		}

		@Override
		public void handleChangeDifficulty(CSetDifficultyPacket packet) {
		}

		@Override
		public void handleLockDifficulty(CLockDifficultyPacket packet) {
		}

		private final static class FakeNetworkManager extends NetworkManager {

			public FakeNetworkManager(PacketDirection packetDirection) {
				super(packetDirection);
			}

			@Override
			public void channelActive(ChannelHandlerContext channelHandlerContext) {
			}

			@Override
			public void setProtocol(ProtocolType newState) {
			}

			@Override
			public void channelInactive(ChannelHandlerContext channelHandlerContext) {
			}

			@Override
			public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) {
			}

			@Override
			protected void channelRead0(ChannelHandlerContext channelHandlerContext, IPacket<?> packet) {
			}

			@Override
			public void send(IPacket<?> packet) {
			}

			@Override
			public void send(IPacket<?> pPacket,
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
			public void disconnect(ITextComponent message) {
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
			public void setupCompression(int threshold) {
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
