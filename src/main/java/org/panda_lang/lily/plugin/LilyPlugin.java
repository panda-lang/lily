package org.panda_lang.lily.plugin;

import org.panda_lang.lily.Lily;

public abstract class LilyPlugin {

    protected PluginProperties pluginProperties;

    public LilyPlugin() {
        this.pluginProperties = getClass().getAnnotation(PluginProperties.class);
    }

    protected final void initialize() {
        if (pluginProperties == null) {
            throw new RuntimeException("LilyPlugin without PluginProperties annotation.");
        }
    }

    public void onLoad() {
    }

    public String getVersion() {
        return pluginProperties.version();
    }

    public String getName() {
        return pluginProperties.name();
    }

    public PluginProperties getPluginProperties() {
        return pluginProperties;
    }

    public abstract void onEnable(Lily lily);

    public abstract void onDisable();

}
