package org.panda_lang.lily.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import org.panda_lang.panda.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditorTab extends Tab implements Initializable {

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

    @FXML private WebView webView;

    private WebEngine webEngine;
    private String title;
    private boolean changed;
    private boolean succeeded;

    public EditorTab() throws IOException {
        super();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/tab.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Style
        webEngine = webView.getEngine();
        GridPane.setHgrow(webView, Priority.ALWAYS);
        GridPane.setVgrow(webView, Priority.ALWAYS);
    }

    public void run(TabPane pane, File file) {
        if (file == null) {
            return;
        }

        // Tab Settings
        this.title = file.getName();
        setText(title);

        // Engine settings
        webView.setVisible(true);
        webEngine.setJavaScriptEnabled(true);

        // Load source
        String source = IOUtils.getContentOfFile(file);
        if (source == null) {
            source = "";
        }
        else {
            source = StringUtils.replace(source, "    ", "\t");
        }

        // Load content
        String content = template.replace("{code}", source);
        webEngine.loadContent(content);
        webView.setUserData(file);

        // Tabs
        pane.getTabs().add(this);
        pane.getSelectionModel().select(this);

        // Accelerators
        webView.getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_ANY), () -> {
            File f = (File) webView.getUserData();
            String src = (String) webEngine.executeScript("editor.getValue()");
            // StringUtils.replace(src, "\t", "    ")
            IOUtils.overrideFile(f, src);
            setText(title);
            changed = false;
        });

        // State listener
        webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
            switch (newValue) {
                case SUCCEEDED: {
                    succeeded = true;
                }
            }
        });

        // Events
        webView.setOnKeyPressed(key -> {
            if (!changed) {
                KeyCode keyCode = key.getCode();
                if (keyCode.isLetterKey() || keyCode.isDigitKey() || keyCode.isWhitespaceKey() || keyCode == KeyCode.BACK_SPACE) {
                    setText(title + " *");
                    changed = true;
                }
            }
        });
    }

    public void setWebView(WebView webView) {
        this.webView = webView;
    }

    public boolean isChanged() {
        return changed;
    }

    public boolean isSucceeded() {
        return succeeded;
    }

    public WebEngine getWebEngine() {
        return webEngine;
    }

    public WebView getWebView() {
        return webView;
    }

    public Tab getTab() {
        return this;
    }

    public String getTitle() {
        return title;
    }

    public static String getTemplate() {
        return template;
    }

}
