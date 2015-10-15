package org.panda_lang.peditor;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Interface implements Initializable {

    @FXML private BorderPane borderPane;
    @FXML private ToolBar toolBar;
    @FXML private SplitPane splitPane;
    @FXML private TabPane tabPane;

    @FXML private AnchorPane spLeft;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Parent explorerRoot = FXMLLoader.load(getClass().getResource("Explorer.fxml"));
            ScrollPane explorer = new ScrollPane(explorerRoot);
            explorer.setFitToHeight(true);
            explorer.setFitToWidth(true);

            spLeft.getChildren().add(0, explorer);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
