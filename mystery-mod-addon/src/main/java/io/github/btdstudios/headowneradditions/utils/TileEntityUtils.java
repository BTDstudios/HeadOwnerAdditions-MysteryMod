package io.github.btdstudios.headowneradditions.utils;

import com.google.inject.Inject;
import io.github.btdstudios.headowneradditions.config.GeneralConfig;
import lombok.RequiredArgsConstructor;
import net.mysterymod.api.minecraft.entity.IEntityPlayer;
import net.mysterymod.api.minecraft.entity.ITileEntity;
import net.mysterymod.api.minecraft.entity.ITileEntitySkull;
import net.mysterymod.api.minecraft.world.BlockPosition;
import net.mysterymod.api.minecraft.world.IWorld;
import net.mysterymod.mod.MysteryMod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class TileEntityUtils {
    /**
     * Get the {@link BlockPosition} at which the {@link IEntityPlayer}.
     *
     * @return An {@link BlockPosition} or {@code null }if the {@link IEntityPlayer} is not raytracing at a block.
     * @since 1.0.0
     */
    @Nullable
    public static BlockPosition getPlayerRayTraceBlockPos(GeneralConfig generalConfig) {
        return MysteryMod.getInstance().getMinecraft().getEntityPlayer().rayTraceBlock(generalConfig.getRayTraceDistance(), 1.0F);
    }

    /**
     * Get an {@link ITileEntity} from a {@link BlockPosition}.
     *
     * @param blockPosition The {@link BlockPosition}, where to check for an {@link ITileEntity}.
     * @return An {@link ITileEntity} if found on the position, else {@code null}.
     * @since 1.0.0
     */
    @Nullable
    public static ITileEntity getTileEntityLooking(@NotNull BlockPosition blockPosition) {
        return getPlayerIWorld().find(blockPosition);
    }

    public static IWorld getPlayerIWorld() {
        return MysteryMod.getInstance().getMinecraft().getIWorld();
    }

    /**
     *
     * Get the skull a {@link IEntityPlayer} is looking at.
     *
     * @return A {@link ITileEntitySkull} if player is looking at one, else {@code null}.
     * @since 1.0.0
     */
    @Nullable
    public static ITileEntitySkull getPlayerLookingSkull(GeneralConfig generalConfig) {
        BlockPosition blockPosition = getPlayerRayTraceBlockPos(generalConfig);

        if (blockPosition != null) {
            ITileEntity iTileEntity = getTileEntityLooking(blockPosition);

            if (iTileEntity instanceof ITileEntitySkull) {
                return (ITileEntitySkull) iTileEntity;
            }
        }
        return null;
    }
}
