package org.panda_lang.lily;

import org.panda_lang.lily.composition.PluginComposition;

public class LilyComposition {

    private final PluginComposition pluginComposition;

    public LilyComposition(Lily lily) {
        this.pluginComposition = new PluginComposition(lily);
    }

    public PluginComposition getPluginComposition() {
        return pluginComposition;
    }

}
