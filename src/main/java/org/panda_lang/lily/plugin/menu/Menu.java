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

package org.panda_lang.lily.plugin.menu;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import org.panda_lang.lily.util.FXMLLoaderUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class Menu extends BorderPane implements Initializable {

    @FXML public MenuItem menuFileSettings;
    @FXML public MenuItem menuFileSaveAll;
    @FXML public MenuItem menuEditRedo;
    @FXML public MenuItem menuEditCut;
    @FXML public MenuItem menuEditCopy;
    @FXML public MenuItem menuEditPaste;
    @FXML public MenuItem menuEditFind;
    @FXML public MenuItem menuEditSelectAll;
    @FXML public MenuItem menuEditDelete;
    @FXML private MenuItem menuFileNew;
    @FXML private MenuItem menuFileOpenFile;
    @FXML private MenuItem menuFileOpenFolder;
    @FXML private MenuItem menuFileExit;
    @FXML private MenuItem menuEditUndo;
    @FXML private MenuItem menuRunRun;
    @FXML private MenuItem menuGitClone;
    @FXML private MenuItem menuHelpAbout;

    public Menu() {
        FXMLLoaderUtils.loadElementFromResources(this, "/plugins/menu/menu.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MenuUtils.extend(menuFileOpenFolder);
        MenuUtils.extend(menuEditUndo);
        MenuUtils.extend(menuRunRun);
        MenuUtils.extend(menuGitClone);
        MenuUtils.extend(menuHelpAbout);

        /*
        // Action: File -> New
        menuFileNew.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(tree.getDirectory());
            fileChooser.showOpenDialog(Lily.instance.getStage());
            tree.open(tree.getDirectory());
        });

        // Action: File -> Open
        menuFileOpenFile.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(Lily.instance.getStage());
            if (file != null) {
                tree.open(file);
            }
        });
        // Action: File -> Open folder
        menuFileOpenFolder.setOnAction(event -> {
            DirectoryChooser fileChooser = new DirectoryChooser();
            File file = fileChooser.showDialog(Lily.instance.getStage());
            if (file != null) {
                tree.open(file);
            }
        });
        // Action: File -> Exit
        menuFileExit.setOnAction(event -> System.exit(-1));

        // Action: Run -> Run
        menuRunRun.setOnAction(event -> {
            ConsolePane consolePane = ConsolePane.getInstance();
            consolePane.clear();
            workspaceAssistantsPane.getItems().clear();
            workspaceAssistantsPane.getItems().add(consolePane);
            consolePane.bind(workspaceAssistantsPane);

            String source = (String) getCurrentTab().getWebEngine().executeScript("editor.getValue()");

            // TODO
            // PandaScript pandaScript = Lily.instance.getPanda().getPandaLoader().loadSimpleScript(source);
            // pandaScript.call(MethodBlock.class, "main");
        });
        */
    }


}
