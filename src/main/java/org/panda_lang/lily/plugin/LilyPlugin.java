/*
 * Copyright (c) 2015-2017 Dzikoysk
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
