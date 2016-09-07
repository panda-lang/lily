package org.panda_lang.lily.plugin;

import org.panda_lang.lily.Lily;

public abstract class LilyPlugin {

    public void onLoad() {
    }

    public abstract void onEnable(Lily lily);

    public abstract void onDisable();

    public abstract String getName();

}
