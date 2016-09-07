package org.panda_lang.lily.plugin.menu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/plugins/menu/menu.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
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
