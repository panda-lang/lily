package org.panda_lang.editor;

import javafx.concurrent.Worker;
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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.panda_lang.panda.util.IOUtils;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class TabInterface implements Initializable {

    @FXML private Tab tab;
    @FXML private WebView webView;

    private String title;
    private WebEngine engine;
    private boolean changes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        engine = webView.getEngine();
        webView.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent;");

        // {events}
        webView.setOnKeyPressed(key -> {
            if(changes) return;
            tab.setText(title + " *");
            changes = true;
        });
    }

    public void run(TabPane pane, File file) {
        if(file == null) return;

        // {resources}
        String content = IOUtils.getContent(file);
        content = content.replace("'", "\\'");
        content = content.replace(System.getProperty("line.separator"), "\\n");
        content = content.replace("\n", "\\n");
        content = content.replace("\r", "\\n");

        String url = getClass().getResource("/html/area.html").toExternalForm();

        // {initData}
        engine.load(url);
        webView.setUserData(file);

        // {tab.anem}
        this.title = file.getName();
        tab.setText(title);

        // {add}
        pane.getTabs().add(tab);
        pane.getSelectionModel().select(tab);

        // {accelerators}
        webView.getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_ANY), () -> {
            File f = (File) webView.getUserData();
            IOUtils.overrideFile(f, (String) engine.executeScript("getSource();"));
            tab.setText(title);
            changes = false;
        });

        // {source}
        final String source = content;
        engine.getLoadWorker().stateProperty().addListener((ov, oldState, newState) -> {
            switch (newState) {
                case SUCCEEDED:
                    engine.executeScript("editor.setValue('" + source + "');");
                    break;
            }
        });
    }

}
