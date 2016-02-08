package org.panda_lang.lily.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.panda_lang.lily.Lily;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Interface implements Initializable {

    @FXML private MenuItem menuFileOpenFile;
    @FXML private MenuItem menuFileOpenFolder;
    @FXML private MenuItem menuFileExit;
    @FXML private MenuItem menuEditUndo;
    @FXML private MenuItem menuRunRun;
    @FXML private MenuItem menuHelpAbout;
    @FXML private SplitPane splitPane;
    @FXML private TreeView<String> filesTree;
    @FXML private TabPane tabPane;

    private ProjectTree tree;
    private List<EditorTab> tabs;
    private EditorTab currentTab;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize Interface
        Lily.instance.initAnInterface(this);

        // Extend
        extend(menuFileOpenFolder);
        extend(menuEditUndo);
        extend(menuRunRun);
        extend(menuHelpAbout);

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

        // SplitPane
        this.splitPane.setDividerPositions(0.25, 0.75);

        // EditorTabs
        this.tabs = new ArrayList<>();

        // ProjectTree
        this.tree = new ProjectTree(filesTree);
        this.tree.open(new File("./"));
    }

    private void extend(MenuItem menuItem) {
        String currentName = menuItem.getText();
        StringBuilder builder = new StringBuilder(currentName);
        int required = 50 - currentName.length();
        for (int i = 0; i < required; i++) {
            builder.append(' ');
        }
        menuItem.setText(builder.toString());
    }

    public void displayFile(File file) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/tab.fxml"));
            loader.load();
            EditorTab ti = loader.getController();
            tabs.add(ti);
            ti.run(tabPane, file);
            currentTab = ti;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public EditorTab getCurrentTab() {
        return currentTab;
    }

    public List<EditorTab> getTabs() {
        return tabs;
    }

}
