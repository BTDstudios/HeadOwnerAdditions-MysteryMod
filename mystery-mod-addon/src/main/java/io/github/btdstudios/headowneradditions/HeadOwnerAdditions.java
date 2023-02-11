package io.github.btdstudios.headowneradditions;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import io.github.btdstudios.headowneradditions.config.GeneralConfig;
import io.github.btdstudios.headowneradditions.config.GeneralConfigProvider;
import io.github.btdstudios.headowneradditions.utils.ChildInjectModule;
import io.github.btdstudios.headowneradditions.utils.IGameProfileUtils;
import io.github.btdstudios.headowneradditions.utils.TileEntityUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.mysterymod.api.event.EventHandler;
import net.mysterymod.api.event.key.KeyEvent;
import net.mysterymod.api.input.Keyboard;
import net.mysterymod.api.listener.ListenerChannel;
import net.mysterymod.api.minecraft.entity.ITileEntitySkull;
import net.mysterymod.mod.MysteryMod;
import net.mysterymod.mod.addon.Addon;
import net.mysterymod.mod.clipboard.IClipboardHandler;

import java.util.UUID;
import java.util.logging.Logger;

@Singleton
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class HeadOwnerAdditions extends Addon {
    @Getter
    private static HeadOwnerAdditions instance;
    private final Logger logger;
    private final GeneralConfig generalConfig;
    private final Injector injector;
    private final ListenerChannel listenerChannel;
    private final Keyboard keyboard;
    private final IClipboardHandler iClipboardHandler;
    private IGameProfileUtils iGameProfileUtils;

    @Override
    public void onEnable() {
        logger.info("Starting up " + this.getName() + " on " + MysteryMod.getInstance().getMinecraftVersion() + " with MysteryMod " + MysteryMod.getInstance().getModVersion());
        setSettingsProvider(MysteryMod.getInjector().getInstance(GeneralConfigProvider.class));
        listenerChannel.registerListener(this);
        Injector childInjector = injector.createChildInjector(ChildInjectModule.create());
        this.iGameProfileUtils = childInjector.getInstance(IGameProfileUtils.class);
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onKey(KeyEvent event) {
        if (!generalConfig.isEnabled()) {
            return;
        }

        if (keyboard.isKeyDown(generalConfig.getKeyCode())) {
            ITileEntitySkull iTileEntitySkull = TileEntityUtils.getPlayerLookingSkull(generalConfig);

            if (iTileEntitySkull == null) {
                if (generalConfig.isChatErrorNoSkullDetectedEnabled()) {
                    String noSkullDetectedChatMessage = generalConfig.getErrorMessageNoSkullDetected().replace("%%CHAT-PREFIX%%", generalConfig.getAddonChatPrefix()).replace("%%&%%", "§");
                    MysteryMod.getInstance().getMinecraft().addChatMessage(noSkullDetectedChatMessage);
                }
                return;
            }

            String skullName = iGameProfileUtils.getName(iTileEntitySkull);
            UUID skullID = iGameProfileUtils.getID(iTileEntitySkull);
            String skullNameResultText = skullName != null ? skullName : generalConfig.getUnknownTranslation();
            String skullIDResultText = skullID != null ? skullID.toString() : generalConfig.getUnknownTranslation();

            if (generalConfig.isChatMessageEnabled()) {
                String skullResultMessage = generalConfig.getChatMessage().replace("%%CHAT-PREFIX%%", generalConfig.getAddonChatPrefix()).replace("%%&%%", "§").replace("%%HEAD-NAME%%", skullNameResultText).replace("%%HEAD-UUID%%", skullIDResultText);
                MysteryMod.getInstance().getMinecraft().addChatMessage(skullResultMessage);
            }

            if (generalConfig.isClipboardMessageEnabled()) {
                String clipboardMessage = generalConfig.getClipboardMessage().replace("%%HEAD-NAME%%", skullNameResultText).replace("%%HEAD-UUID%%", skullIDResultText);
                iClipboardHandler.copy(clipboardMessage);
            }
        }
    }
}

// TODO Integrate PopUp like in Raw MysteryMod HeadOwner
// TODO Add A GUI, which displays the Head in the middle like in LabyMod-Addon Forengötter