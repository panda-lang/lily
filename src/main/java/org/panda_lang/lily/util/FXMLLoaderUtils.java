package org.panda_lang.lily.util;

import javafx.fxml.FXMLLoader;
import org.panda_lang.lily.Lily;

import java.io.IOException;

public class FXMLLoaderUtils {

    public static void loadElementFromResources(Object element, String location) {
        FXMLLoader fxmlLoader = new FXMLLoader(Lily.class.getResource(location));
        fxmlLoader.setRoot(element);
        fxmlLoader.setController(element);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
