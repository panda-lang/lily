/*
 * Copyright (c) 2015-2018 Dzikoysk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
