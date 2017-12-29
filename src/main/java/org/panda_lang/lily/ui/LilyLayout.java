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

package org.panda_lang.lily.ui;

import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.Collection;

public class LilyLayout {

    private final String name;
    private final Collection<LilyLayout> layouts;
    private final BorderPane borderPane;

    public LilyLayout(String name) {
        this(name, new BorderPane());
    }

    public LilyLayout(String name, BorderPane borderPane) {
        this.name = name;
        this.borderPane = borderPane;
        this.layouts = new ArrayList<>();
    }

    public LilyLayout findLayout(String name) {
        if (getName().equals(name)) {
            return this;
        }

        for (LilyLayout layout : layouts) {
            String layoutName = layout.getName();

            if (layoutName.equals(name)) {
                return layout;
            }
        }

        return null;
    }

    public void addLayout(LilyLayout layout) {
        layouts.add(layout);
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public Collection<LilyLayout> getLayouts() {
        return layouts;
    }

    public String getName() {
        return name;
    }

}
