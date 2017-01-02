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

package org.panda_lang.lily;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.panda_lang.lily.plugin.PluginFinder;
import org.panda_lang.lily.plugin.PluginManager;
import org.panda_lang.lily.ui.LilyUI;
import org.panda_lang.panda.Panda;

public class Lily extends Application {

    public static Lily instance;

    private final Panda panda;
    private final PluginManager pluginManager;
    private final LilyComposition composition;
    private Stage stage;
    private LilyUI ui;

    public Lily() {
        instance = this;

        this.panda = new Panda();
        this.pluginManager = new PluginManager(this);
        this.composition = new LilyComposition(this);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        this.ui = new LilyUI(this);

        PluginFinder pluginFinder = new PluginFinder(pluginManager);
        pluginFinder.find();

        ui.initialize();
        pluginManager.loadPlugins();
        pluginManager.enablePlugins();

        stage.show();
    }

    public void exit() {
        pluginManager.disablePlugins();

        Platform.exit();
        System.exit(-1);
    }

    public LilyUI getUI() {
        return this.ui;
    }

    public Stage getStage() {
        return this.stage;
    }

    public LilyComposition getComposition() {
        return composition;
    }

    public PluginManager getPluginManager() {
        return pluginManager;
    }

    public Panda getPanda() {
        return panda;
    }

    public static Lily getInstance() {
        return instance;
    }

}
