package org.panda_lang.lily;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.panda_lang.lily.ui.Interface;

public class Lily extends Application {

    public static Lily instance;
    private Stage stage;
    private Interface anInterface;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        instance = this;

        // Size of the screen
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();

        // Lily's ui
        Parent root = FXMLLoader.load(getClass().getResource("/ui/interface.fxml"));
        Scene scene = new Scene(root, bounds.getWidth() - 20, bounds.getHeight() * 0.5);
        root.getStylesheets().add("/ui/themes/dark_material.css");

        // Lily's position
        stage.setWidth(bounds.getWidth() - 20);
        stage.setHeight(bounds.getHeight() * 0.8);
        stage.setX((bounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((bounds.getHeight() - stage.getHeight()) / 2);

        // Others
        stage.setTitle("Lily the Panda IDE");
        stage.setScene(scene);
        stage.show();
    }

    public void initAnInterface(Interface anInterface) {
        this.anInterface = anInterface;
    }

    public Interface getInterface() {
        return this.anInterface;
    }

    public Stage getStage() {
        return this.stage;
    }


    public static void main(String[] args) throws Exception {
        launch(args);
    }

}
