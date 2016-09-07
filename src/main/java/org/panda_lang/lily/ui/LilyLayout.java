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
