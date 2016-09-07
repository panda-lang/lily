package org.panda_lang.lily.composition;

import org.panda_lang.lily.Lily;
import org.panda_lang.lily.plugin.PluginManager;
import org.panda_lang.lily.plugin.console.ConsolePlugin;
import org.panda_lang.lily.plugin.editor.EditorPlugin;
import org.panda_lang.lily.plugin.menu.MenuPlugin;
import org.panda_lang.lily.plugin.project.ProjectPlugin;

public class PluginComposition {

    private final Lily lily;

    public PluginComposition(Lily lily) {
        this.lily = lily;
        this.initialize();
    }

    private void initialize() {
        PluginManager pluginManager = lily.getPluginManager();

        ConsolePlugin consolePlugin = new ConsolePlugin();
        pluginManager.registerPlugin(consolePlugin);

        EditorPlugin editorPlugin = new EditorPlugin();
        pluginManager.registerPlugin(editorPlugin);

        MenuPlugin menuPlugin = new MenuPlugin();
        pluginManager.registerPlugin(menuPlugin);

        ProjectPlugin projectPlugin = new ProjectPlugin();
        pluginManager.registerPlugin(projectPlugin);
    }

}
