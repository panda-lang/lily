package org.panda_lang.editor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Editor extends Application {

    public static Editor instance;
    private Stage stage;
    private Interface anInterface;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        instance = this;

        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/interface.fxml"));
        //root.getStylesheets().add("/css/dark.css");
        Scene scene = new Scene(root, bounds.getWidth(), bounds.getHeight());

        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());

        stage.setTitle("Panda Editor");
        stage.setScene(scene);

        stage.setMaximized(true);

        stage.show();
    }

    protected void initAnInterface(Interface anInterface) {
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
