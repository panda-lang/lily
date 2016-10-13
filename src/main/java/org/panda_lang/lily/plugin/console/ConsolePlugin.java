package org.panda_lang.lily.plugin.console;

import org.panda_lang.lily.Lily;
import org.panda_lang.lily.LilyConstants;
import org.panda_lang.lily.plugin.LilyPlugin;
import org.panda_lang.lily.plugin.PluginProperties;
import org.panda_lang.lily.ui.LilyUI;

@PluginProperties(name = "Console", version = LilyConstants.VERSION)
public class ConsolePlugin extends LilyPlugin {

    private ConsolePane consolePane;

    @Override
    public void onEnable(Lily lily) {
        this.consolePane = new ConsolePane();

        LilyUI ui = lily.getUI();
    }

    @Override
    public void onDisable() {

    }

    public ConsolePane getConsolePane() {
        return consolePane;
    }

}
