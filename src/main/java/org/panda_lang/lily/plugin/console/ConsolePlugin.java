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

package org.panda_lang.lily.plugin.console;

import org.panda_lang.lily.Lily;
import org.panda_lang.lily.LilyConstants;
import org.panda_lang.lily.plugin.LilyPlugin;
import org.panda_lang.lily.plugin.PluginProperties;
import org.panda_lang.lily.ui.LilyUI;

@PluginProperties(name = "Console", version = LilyConstants.VERSION)
public class ConsolePlugin extends LilyPlugin {

    private ConsolePane consolePane;

    @Override
    public void onEnable(Lily lily) {
        this.consolePane = new ConsolePane();

        LilyUI ui = lily.getUI();
    }

    @Override
    public void onDisable() {

    }

    public ConsolePane getConsolePane() {
        return consolePane;
    }

}
