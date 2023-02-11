package io.github.btdstudios.headowneradditions.version_specific.implementation;

import io.github.btdstudios.headowneradditions.utils.IGameProfileUtils;
import net.mysterymod.api.minecraft.entity.ITileEntitySkull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

@SuppressWarnings("unused")
public class GameProfileUtils implements IGameProfileUtils {
    @Nullable
    @Override
    public String getName(@NotNull ITileEntitySkull iTileEntitySkull) {
        return iTileEntitySkull.getProfile().getName();
    }

    @Nullable
    @Override
    public UUID getID(@NotNull ITileEntitySkull iTileEntitySkull) {
        return iTileEntitySkull.getProfile().getId();
    }

    @Override
    public boolean isComplete(@NotNull ITileEntitySkull iTileEntitySkull) {
        return iTileEntitySkull.getProfile().isComplete();
    }

    @Override
    public boolean isLegacy(@NotNull ITileEntitySkull iTileEntitySkull) {
        return iTileEntitySkull.getProfile().isLegacy();
    }
}