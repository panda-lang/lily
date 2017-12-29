/*
 * Copyright (c) 2015-2018 Dzikoysk
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

package org.panda_lang.lily.plugin.menu;

import javafx.scene.control.MenuItem;

public class MenuUtils {

    public static void extend(MenuItem menuItem) {
        String currentName = menuItem.getText();
        StringBuilder builder = new StringBuilder(currentName);
        int required = 50 - currentName.length();

        for (int i = 0; i < required; i++) {
            builder.append(' ');
        }

        menuItem.setText(builder.toString());
    }

}
