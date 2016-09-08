package org.panda_lang.lily.plugin.menu;

import javafx.scene.layout.BorderPane;
import org.panda_lang.lily.Lily;
import org.panda_lang.lily.LilyConstants;
import org.panda_lang.lily.plugin.LilyPlugin;
import org.panda_lang.lily.plugin.PluginProperties;
import org.panda_lang.lily.ui.LilyLayout;
import org.panda_lang.lily.ui.LilyUI;

@PluginProperties(name = "Console", version = LilyConstants.VERSION)
public class MenuPlugin extends LilyPlugin {

    private Menu menu;

    @Override
    public void onEnable(Lily lily) {
        this.menu = new Menu();

        LilyUI ui = lily.getUI();
        LilyLayout layout = ui.getLayout();
        BorderPane borderPane = layout.getBorderPane();

        LilyLayout menuLayout = new LilyLayout("menu", menu);
        layout.addLayout(menuLayout);
        borderPane.setTop(menu);
    }

    @Override
    public void onDisable() {

    }

    public Menu getMenu() {
        return menu;
    }

}
