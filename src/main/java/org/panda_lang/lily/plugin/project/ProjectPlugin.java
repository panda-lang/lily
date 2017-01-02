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
