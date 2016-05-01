package org.panda_lang.lily;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.panda_lang.lily.ui.Interface;
import org.panda_lang.panda.Panda;

public class Lily extends Application {

    public static Lily instance;
    private Panda panda;
    private Stage stage;
    private Interface anInterface;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        instance = this;

        this.stage = stage;
        this.panda = new Panda();

        // Size of the screen
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();

        // Lily's ui
        Parent root = FXMLLoader.load(getClass().getResource("/ui/interface.fxml"));
        Scene scene = new Scene(root, bounds.getWidth() - 2, bounds.getHeight() * 0.9);
        root.getStylesheets().add("/ui/themes/default_material.css");
        stage.getIcons().add(new Image("/ui/icons/icon.png"));

        // Lily's position
        stage.setWidth(bounds.getWidth() - 2);
        stage.setHeight(bounds.getHeight() * 0.9);
        stage.setX((bounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((bounds.getHeight() - stage.getHeight()) / 2);

        // Others
        panda.initializeDefaultElements();
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

    public Panda getPanda() {
        return panda;
    }

}
