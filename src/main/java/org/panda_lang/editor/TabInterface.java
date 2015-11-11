package org.panda_lang.editor;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.panda_lang.panda.util.IOUtils;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class TabInterface implements Initializable {

    private static final String template;

    @FXML private Tab tab;
    @FXML private WebView webView;

    private String title;
    private WebEngine engine;
    private boolean changes;

    static {
        ResourcesBuilder resourcesBuilder = new ResourcesBuilder(Editor.class);
        resourcesBuilder.importCss("/html/cm/codemirror.min.css");
        resourcesBuilder.importCss("/html/cm/default.min.css");
        resourcesBuilder.importCss("/html/cm/dracula.min.css");
        resourcesBuilder.importScript("/html/cm/codemirror.min.js");
        resourcesBuilder.importScript("/html/cm/clike.min.js");

        template = IOUtils.convertStreamToString(Editor.class.getResourceAsStream("/html/tab.html"))
                .replace("${imports}", resourcesBuilder.toString());
    }

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

        // {initData}
        engine.loadContent(template);
        webView.setUserData(file);

        // {tab.anem}
        this.title = file.getName();
        tab.setText(title);

        // {add}
        pane.getTabs().add(tab);
        pane.getSelectionModel().select(tab);
        int selected = pane.getSelectionModel().getSelectedIndex();

        // {accelerators}
        webView.getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_ANY), () -> {
            File f = (File) webView.getUserData();
            IOUtils.overrideFile(f, (String) engine.executeScript("editor.getValue()"));
            tab.setText(title);
            changes = false;
        });

        // {source}
        final String source = content;
        engine.getLoadWorker().stateProperty().addListener((ov, oldState, newState) -> {
            switch (newState) {
                case SUCCEEDED:
                    if (!pane.getSelectionModel().isSelected(selected)) return;
                    webView.setVisible(true);
                    engine.executeScript("editor.setValue('" + source + "')");
                    break;
            }
        });
    }

}
