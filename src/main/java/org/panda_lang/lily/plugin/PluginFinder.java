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
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

import java.util.Set;

public class PluginFinder {

    private final PluginManager pluginManager;

    public PluginFinder(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    public void find() throws Exception {
        ConfigurationBuilder config = new ConfigurationBuilder();
        config.setClassLoaders(new ClassLoader[]{ getClass().getClassLoader() });
        config.addUrls(Lily.class.getProtectionDomain().getCodeSource().getLocation().toURI().toURL());

        Reflections reflections = new Reflections(config);
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(PluginProperties.class);

        for (Class<?> clazz : annotated) {
            Object objectInstance = clazz.newInstance();

            if (!(objectInstance instanceof LilyPlugin)) {
                continue;
            }

            LilyPlugin lilyPlugin = (LilyPlugin) objectInstance;
            pluginManager.registerPlugin(lilyPlugin);
        }

        System.out.println("Amount of loaded plugins: " + annotated.size());
    }

}
