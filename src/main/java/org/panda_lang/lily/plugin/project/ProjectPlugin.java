package org.panda_lang.lily.plugin.project;

import javafx.scene.layout.BorderPane;
import org.panda_lang.lily.Lily;
import org.panda_lang.lily.LilyConstants;
import org.panda_lang.lily.plugin.LilyPlugin;
import org.panda_lang.lily.plugin.PluginProperties;
import org.panda_lang.lily.ui.LilyLayout;
import org.panda_lang.lily.ui.LilyUI;

import java.io.File;

@PluginProperties(name = "Project", version = LilyConstants.VERSION)
public class ProjectPlugin extends LilyPlugin {

    private ProjectView projectView;

    @Override
    public void onEnable(Lily lily) {
        this.projectView = new ProjectView(this);

        LilyUI ui = lily.getUI();
        LilyLayout layout = ui.getLayout();
        BorderPane pane = layout.getBorderPane();

        LilyLayout projectLayout = new LilyLayout("project");
        layout.addLayout(projectLayout);

        BorderPane projectBorderPane = projectLayout.getBorderPane();
        projectBorderPane.setLeft(projectView);
        pane.setCenter(projectBorderPane);

        File currentDirectory = new File(".");
        projectView.open(currentDirectory);
    }

    @Override
    public void onDisable() {

    }

    public ProjectView getProjectView() {
        return projectView;
    }

}
