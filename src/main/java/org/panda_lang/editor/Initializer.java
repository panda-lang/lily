package org.panda_lang.editor;

import javafx.scene.web.WebView;
import org.panda_lang.panda.util.IOUtils;

public class Initializer {

    public static void init() {
        WebView wv = new WebView();
        ResourcesBuilder resourcesBuilder = new ResourcesBuilder(Editor.class);
        resourcesBuilder.importCss("/html/cm/codemirror.min.css");
        resourcesBuilder.importCss("/html/cm/default.min.css");
        resourcesBuilder.importCss("/html/cm/dracula.min.css");
        resourcesBuilder.importScript("/html/cm/codemirror.min.js");
        resourcesBuilder.importScript("/html/cm/clike.min.js");
        String template = IOUtils.convertStreamToString(Editor.class.getResourceAsStream("/html/tab.html"))
                .replace("${imports}", resourcesBuilder.toString());
        wv.getEngine().loadContent(template);
    }

}
