package io.github.btdstudios.headowneradditions.utils;

import net.mysterymod.api.minecraft.entity.ITileEntitySkull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

@SuppressWarnings("unused")
public interface IGameProfileUtils {

    @Nullable
    String getName(ITileEntitySkull iTileEntitySkull);

    @Nullable
    UUID getID(ITileEntitySkull iTileEntitySkull);

    boolean isComplete(ITileEntitySkull iTileEntitySkull);

    boolean isLegacy(ITileEntitySkull iTileEntitySkull);
}
