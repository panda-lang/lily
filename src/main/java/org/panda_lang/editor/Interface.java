package org.panda_lang.editor;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.panda_lang.panda.Panda;

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
    @FXML private MenuItem menuHelpAbout;
    @FXML private TreeView<String> filesTree;
    @FXML private TabPane tabPane;

    private Explorer tree;
    private List<TabInterface> tabs;
    private TabInterface currentTab;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Editor.instance.initAnInterface(this);

        menuFileOpenFile.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(Editor.instance.getStage());
            if (file != null) {
                tree.open(file);
            }
        });
        menuFileOpenFolder.setOnAction(event -> {
            DirectoryChooser fileChooser = new DirectoryChooser();
            File file = fileChooser.showDialog(Editor.instance.getStage());
            if (file != null) {
                tree.open(file);
            }
        });
        menuFileExit.setOnAction(event -> System.exit(-1));
        menuHelpAbout.setOnAction(event -> {
            Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(Editor.instance.getStage());
            VBox dialogVbox = new VBox(20);
            Text text = new Text("  Panda Editor for Panda " + Panda.PANDA_VERSION);
            dialogVbox.getChildren().add(text);
            Scene dialogScene = new Scene(dialogVbox, text.getText().length() * 6.5, 30);
            dialog.setScene(dialogScene);
            dialog.show();
        });

        this.tabs = new ArrayList<>();
        this.tree = new Explorer(filesTree);
        this.tree.open(new File("./"));
    }

    public void displayFile(File file) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/tab.fxml"));
            loader.load();
            TabInterface ti = loader.getController();
            tabs.add(ti);
            ti.run(tabPane, file);
            currentTab = ti;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TabInterface getCurrentTab() {
        return currentTab;
    }

    public List<TabInterface> getTabs() {
        return tabs;
    }

}
