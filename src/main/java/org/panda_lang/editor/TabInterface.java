package org.panda_lang.editor;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import org.panda_lang.panda.util.IOUtils;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class TabInterface implements Initializable {

    @FXML private Tab tab;
    @FXML private TextArea textArea;

    private String title;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textArea.setStyle(
                "-fx-text-fill: black;"+
                "-fx-background-color: white;"+
                "-fx-font-family: Consolas ;" +
                "-fx-font-size: 13px;");
        textArea.setOnKeyPressed(key -> {
            tab.setText(title + " *");
        });
    }

    public void run(TabPane pane, File file) {
        String content = IOUtils.getContent(file);
        this.title = file.getName();
        tab.setText(title);
        textArea.setUserData(file);
        textArea.setText(content);
        pane.getTabs().add(tab);
        textArea.getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_ANY), () -> {
            File f = (File) textArea.getUserData();
            IOUtils.overrideFile(f, textArea.getText());
            tab.setText(title);
        });
    }

}
