package org.panda_lang.lily.util;

import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.panda_lang.core.util.IOUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

public class FXCSSUpdater {

    {
        URL.setURLStreamHandlerFactory(new StringURLStreamHandlerFactory());
    }

    private String css;
    private Scene scene;

    public FXCSSUpdater(Scene scene) {
        this.scene = scene;
    }

    public void bindCss(StringProperty cssProperty){
        cssProperty.addListener(e -> {
            this.css = cssProperty.get();
            Platform.runLater(()->{
                scene.getStylesheets().clear();
                scene.getStylesheets().add("internal:stylesheet.css");
            });
        });
    }

    public void applyCssToParent(Parent parent){
        parent.getStylesheets().clear();
        scene.getStylesheets().add("internal:stylesheet.css");
    }

    private class StringURLConnection extends URLConnection {

        public StringURLConnection(URL url){
            super(url);
        }

        @Override
        public void connect() throws IOException {}

        @Override public InputStream getInputStream() throws IOException {
            return IOUtils.convertStringToStream(css);
        }

    }

    private class StringURLStreamHandlerFactory implements URLStreamHandlerFactory {
        URLStreamHandler streamHandler = new URLStreamHandler(){
            @Override
            protected URLConnection openConnection(URL url) throws IOException {
                if (url.toString().toLowerCase().endsWith(".css")) {
                    return new StringURLConnection(url);
                }
                throw new FileNotFoundException();
            }
        };

        @Override
        public URLStreamHandler createURLStreamHandler(String protocol) {
            if ("internal".equals(protocol)) {
                return streamHandler;
            }
            return null;
        }

    }

}
