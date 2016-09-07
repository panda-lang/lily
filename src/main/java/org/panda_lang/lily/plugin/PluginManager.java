package org.panda_lang.lily.plugin;

import org.panda_lang.lily.Lily;

import java.util.ArrayList;
import java.util.Collection;

public class PluginManager {

    private final Lily lily;
    private final Collection<LilyPlugin> plugins;

    public PluginManager(Lily lily) {
        this.lily = lily;
        this.plugins = new ArrayList<>();
    }

    public void loadPlugins() {
        for (LilyPlugin plugin : plugins) {
            plugin.onLoad();
        }
    }

    public void enablePlugins() {
        for (LilyPlugin plugin : plugins) {
            plugin.onEnable(lily);
        }
    }

    public void disablePlugins() {
        for (LilyPlugin plugin : plugins) {
            plugin.onDisable();
        }
    }

    public void registerPlugin(LilyPlugin lilyPlugin) {
        plugins.add(lilyPlugin);
    }

    @SuppressWarnings("unchecked")
    public <T extends LilyPlugin> T getPlugin(String name) {
        for (LilyPlugin plugin : plugins) {
            String pluginName = plugin.getName();

            if (name.equals(pluginName)) {
                return (T) plugin;
            }
        }

        return null;
    }

    public Collection<LilyPlugin> getPlugins() {
        return plugins;
    }

}
