package org.panda_lang.lily.plugin.project;

import org.panda_lang.lily.Lily;
import org.panda_lang.lily.plugin.LilyPlugin;

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

    @Override
    public String getName() {
        return "Project";
    }

}
