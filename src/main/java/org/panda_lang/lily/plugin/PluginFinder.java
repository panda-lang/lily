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
