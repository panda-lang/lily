package org.panda_lang.lily.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.panda_lang.lily.Lily;
import org.panda_lang.lily.util.ResourcesBuilder;
import org.panda_lang.panda.util.IOUtils;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class EditorTab implements Initializable {

    private static final String template;

    static {
        // Initialize template
        ResourcesBuilder resourcesBuilder = new ResourcesBuilder();
        resourcesBuilder.importCss("/libs/codemirror/style.min.css");
        resourcesBuilder.importScript("/libs/codemirror/script.min.js");
        resourcesBuilder.importScript("/libs/codemirror/panda.min.js");
        template = IOUtils.convertStreamToString(Lily.class.getResourceAsStream("/ui/editor.html"))
                .replace("{imports}", resourcesBuilder.toString());
    }

    @FXML private Tab tab;
    @FXML private WebView webView;

    private WebEngine webEngine;
    private String title;
    private boolean changes;
    private boolean succeeded;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Style
        webEngine = webView.getEngine();
        GridPane.setHgrow(webView, Priority.ALWAYS);
        GridPane.setVgrow(webView, Priority.ALWAYS);

        // Events
        webView.setOnKeyPressed(key -> {
            if (changes) return;
            tab.setText(title + " *");
            changes = true;
        });
    }

    public void run(TabPane pane, File file) {
        if (file == null) {
            return;
        }

        // Tab Settings
        this.title = file.getName();
        tab.setText(title);

        // Engine settings
        webView.setVisible(true);
        webEngine.setJavaScriptEnabled(true);

        // Load content
        String source = template.replace("{code}", IOUtils.getContentOfFile(file));
        webEngine.loadContent(source);
        webView.setUserData(file);

        // Tabs
        pane.getTabs().add(tab);
        pane.getSelectionModel().select(tab);

        // Accelerators
        webView.getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_ANY), () -> {
            File f = (File) webView.getUserData();
            IOUtils.overrideFile(f, (String) webEngine.executeScript("editor.getValue()"));
            tab.setText(title);
            changes = false;
        });

        // State listener
        webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
            switch (newValue) {
                case SUCCEEDED: {
                    succeeded = true;
                }
            }
        });
    }

}
