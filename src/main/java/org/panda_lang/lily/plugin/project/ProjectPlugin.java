package org.panda_lang.lily.plugin.project;

import org.panda_lang.lily.Lily;
import org.panda_lang.lily.LilyConstants;
import org.panda_lang.lily.plugin.LilyPlugin;
import org.panda_lang.lily.plugin.PluginProperties;

@PluginProperties(name = "Project", version = LilyConstants.VERSION)
public class ProjectPlugin extends LilyPlugin {

    private ProjectView projectView;

    @Override
    public void onEnable(Lily lily) {
        this.projectView = new ProjectView(this);
    }

    @Override
    public void onDisable() {

    }

    public ProjectView getProjectView() {
        return projectView;
    }

}
