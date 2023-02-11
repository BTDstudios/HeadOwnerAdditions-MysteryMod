package io.github.btdstudios.headowneradditions.config;

import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import net.mysterymod.mod.addon.AddonSettingsProvider;
import net.mysterymod.mod.gui.settings.SettingsGui;
import net.mysterymod.mod.gui.settings.component.SettingsComponent;
import net.mysterymod.mod.gui.settings.component.SettingsComponentProvider;
import net.mysterymod.mod.gui.settings.component.input.BigTextFieldComponent;
import net.mysterymod.mod.gui.settings.component.input.KeybindComponent;
import net.mysterymod.mod.gui.settings.component.slider.FancySliderComponent;
import net.mysterymod.mod.gui.settings.component.text.TitleComponent;
import net.mysterymod.mod.gui.settings.component.toggle.ToggleComponent;

import java.util.List;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public final class GeneralConfigProvider implements AddonSettingsProvider {
    private final GeneralConfig generalConfig;

    @Override
    public void addSettings(SettingsGui settingsGui, SettingsComponentProvider settingsComponentProvider, List<SettingsComponent> components) {
        components.add(new TitleComponent("HeadOwner Additions"));
        components.add(new TitleComponent("ChatColors: %%&%% instead of §"));
        components.add(ToggleComponent.create("HeadOwner-Additions", null, toggleState -> {generalConfig.setEnabled(toggleState); generalConfig.saveConfigAsynchronously();}, generalConfig.isEnabled()));
        components.add(new BigTextFieldComponent("Chat-Prefix (%%CHAT-PREFIX%%)", "§8[§2HeadOwner-Additions§8] §7", 250, generalConfig.getAddonChatPrefix(), clipboardText -> {generalConfig.setAddonChatPrefix(clipboardText); generalConfig.saveConfigAsynchronously();}));

        components.add(new TitleComponent("General"));
        components.add(new FancySliderComponent("Distance", null, 1, 25, 1, generalConfig.getRayTraceDistance(), newDistance -> {generalConfig.setRayTraceDistance(Math.round(newDistance)); generalConfig.saveConfigAsynchronously();}));

        components.add(new TitleComponent("Keys"));
        components.add(new KeybindComponent("Hotkey", null, generalConfig.getKeyCode(), key -> {generalConfig.setKeyCode(key); generalConfig.saveConfigAsynchronously();}));

        components.add(new TitleComponent("Messages"));
        components.add(ToggleComponent.create("Enable Chat-Message", null, toggleState -> {generalConfig.setChatMessageEnabled(toggleState); generalConfig.saveConfigAsynchronously();}, generalConfig.isChatMessageEnabled()));
        components.add(new BigTextFieldComponent("Chat-Message (Variables: %%CHAT-PREFIX%%, %%&%%, %%HEAD-NAME%%, %%HEAD-UUID%%)", "%%HEAD-NAME%%", 250, generalConfig.getChatMessage(), clipboardText -> {generalConfig.setChatMessage(clipboardText); generalConfig.saveConfigAsynchronously();}));
        components.add(ToggleComponent.create("Enable Clipboard-Message", null, toggleState -> {generalConfig.setClipboardMessageEnabled(toggleState); generalConfig.saveConfigAsynchronously();}, generalConfig.isClipboardMessageEnabled()));
        components.add(new BigTextFieldComponent("Clipboard-Message (Variables: %%HEAD-NAME%%, %%HEAD-UUID%%)", "%%HEAD-NAME%%", 250, generalConfig.getClipboardMessage(), clipboardText -> {generalConfig.setClipboardMessage(clipboardText); generalConfig.saveConfigAsynchronously();}));

        components.add(new TitleComponent("Errors"));
        components.add(ToggleComponent.create("Enable No-Skull-Message", null, toggleState -> {generalConfig.setChatErrorNoSkullDetectedEnabled(toggleState); generalConfig.saveConfigAsynchronously();}, generalConfig.isChatErrorNoSkullDetectedEnabled()));
        components.add(new BigTextFieldComponent("No-Skull Detected (Variables: %%CHAT-PREFIX%%, %%&%%)", "%%HEAD-NAME%%", 250, generalConfig.getClipboardMessage(), clipboardText -> {generalConfig.setClipboardMessage(clipboardText); generalConfig.saveConfigAsynchronously();}));

        components.add(new TitleComponent("Translations"));
        components.add(new BigTextFieldComponent("Translation: 'Unknown'", "Unknown", 50, generalConfig.getUnknownTranslation(), translation -> {generalConfig.setUnknownTranslation(translation); generalConfig.saveConfigAsynchronously();}));
    }
}