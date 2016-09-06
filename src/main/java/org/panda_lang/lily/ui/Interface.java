package org.panda_lang.lily.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.panda_lang.lily.Lily;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Interface implements Initializable {

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

    @FXML private SplitPane workspacePane;
    @FXML private SplitPane workspaceEditorPane;
    @FXML private SplitPane workspaceAssistantsPane;
    @FXML private TreeView<String> filesTree;
    @FXML private TabPane tabPane;

    private ProjectTree tree;
    private EditorTab currentTab;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize Interface
        Lily.instance.initAnInterface(this);

        // Dividers
        workspacePane.setDividerPositions(1, 0);
        workspaceEditorPane.setDividerPositions(0.25, 0.75);
        workspaceAssistantsPane.setDividerPositions(1, 0);

        // ProjectTree
        tree = new ProjectTree(filesTree);
        try {
            tree.open(new File("./").getCanonicalFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Extend
        extend(menuFileOpenFolder);
        extend(menuEditUndo);
        extend(menuRunRun);
        extend(menuGitClone);
        extend(menuHelpAbout);

        // Initialize Actions
        initializeActions();
    }

    protected void initializeActions() {
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

        tabPane.getSelectionModel().selectedItemProperty().addListener((ov, t, t1) -> {
            currentTab = (EditorTab) tabPane.getSelectionModel().getSelectedItem();
        });
    }

    public void extend(MenuItem menuItem) {
        String currentName = menuItem.getText();
        StringBuilder builder = new StringBuilder(currentName);
        int required = 50 - currentName.length();
        for (int i = 0; i < required; i++) {
            builder.append(' ');
        }
        menuItem.setText(builder.toString());
    }

    public void displayFile(File file) throws IOException {
        EditorTab editorTab = new EditorTab();
        editorTab.run(tabPane, file);
        currentTab = editorTab;
    }

    public EditorTab getCurrentTab() {
        return currentTab;
    }

}
