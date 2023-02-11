package io.github.btdstudios.headowneradditions.config;

import lombok.Getter;
import lombok.Setter;
import net.mysterymod.api.minecraft.KeyCode;
import net.mysterymod.mod.MysteryMod;
import net.mysterymod.mod.config.GsonConfig;

import javax.inject.Singleton;
import java.io.File;

@Singleton
@Getter
@Setter
public final class GeneralConfig extends GsonConfig {
    private boolean enabled = true;
    private boolean chatMessageEnabled = true;
    private boolean clipboardMessageEnabled = false;
    private boolean chatErrorNoSkullDetectedEnabled = true;
    private int rayTraceDistance = 10;
    private KeyCode keyCode = KeyCode.KEY_NUMPAD0;
    private String addonChatPrefix = "%%&%%8[%%&%%2HeadOwner-Additions%%&%%8]%%&%%7";
    private String chatMessage = "%%CHAT-PREFIX%% %%HEAD-NAME%% | %%HEAD-UUID%%";
    private String clipboardMessage = "%%HEAD-NAME%% | %%HEAD-UUID%%";
    private String errorMessageNoSkullDetected = "%%CHAT-PREFIX%% §4§lNo skull could be detected.";
    private String unknownTranslation = "UNKNOWN";

    public GeneralConfig() {
        super(new File("MysteryMod/configs/" + MysteryMod.getInstance().getMinecraftVersion().getVersionName() + "/HeadOwnerAdditions.json"));
        this.initialize();
    }
}