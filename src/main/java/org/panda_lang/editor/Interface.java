package org.panda_lang.editor;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Interface implements Initializable {

    @FXML private MenuItem menuFileOpenFile;
    @FXML private MenuItem menuFileOpenFolder;
    @FXML private MenuItem menuFileExit;
    @FXML private TreeView<String> filesTree;
    @FXML private TabPane tabPane;

    private Explorer tree;

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

        this.tree = new Explorer(filesTree);
        this.tree.open(new File("./"));
    }

    public void displayFile(File file) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/tab.fxml"));
            loader.load();
            TabInterface ti = loader.getController();
            ti.run(tabPane, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
