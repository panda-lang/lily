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
