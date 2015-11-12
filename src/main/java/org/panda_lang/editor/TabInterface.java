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
        resourcesBuilder.importScript("/html/cm/codemirror.min.js");
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

        // {tab.anem}
        this.title = file.getName();
        tab.setText(title);

        // {initData}
        engine.loadContent(template.replace("${code}", IOUtils.getContent(file)));
        webView.setUserData(file);

        // {add}
        pane.getTabs().add(tab);
        pane.getSelectionModel().select(tab);

        // {accelerators}
        webView.getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_ANY), () -> {
            File f = (File) webView.getUserData();
            IOUtils.overrideFile(f, (String) engine.executeScript("editor.getValue()"));
            tab.setText(title);
            changes = false;
        });

    }

}
