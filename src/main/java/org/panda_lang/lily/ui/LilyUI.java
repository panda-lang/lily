/*
 * Copyright (c) 2015-2017 Dzikoysk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.panda_lang.lily.ui;

import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.panda_lang.lily.Lily;
import org.panda_lang.lily.LilyConstants;
import org.panda_lang.lily.util.CloseHandler;

public class LilyUI {

    private final Lily lily;
    private LilyLayout layout;

    public LilyUI(Lily lily) {
        this.lily = lily;
        this.layout = new LilyLayout("ui");
    }

    public void initialize() {
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();

        Stage stage = lily.getStage();
        stage.setWidth(bounds.getWidth() - 2);
        stage.setHeight(bounds.getHeight() * 0.9);
        stage.setX((bounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((bounds.getHeight() - stage.getHeight()) / 2);

        Parent root = layout.getBorderPane();
        root.getStylesheets().add("/ui/themes/light_material.css");

        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
        stage.setScene(scene);

        CloseHandler handler = new CloseHandler(lily);
        stage.setOnCloseRequest(handler);

        stage.getIcons().add(new Image("/ui/icons/icon.png"));
        stage.setTitle(LilyConstants.NAME + " " + LilyConstants.VERSION);
    }

    public LilyLayout getLayout() {
        return layout;
    }

    public Lily getLily() {
        return lily;
    }

}
