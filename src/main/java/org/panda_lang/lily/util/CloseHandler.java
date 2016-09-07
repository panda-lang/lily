package org.panda_lang.lily.util;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import org.panda_lang.lily.Lily;

public class CloseHandler implements EventHandler<WindowEvent> {

    private final Lily lily;

    public CloseHandler(Lily lily) {
        this.lily = lily;
    }

    @Override
    public void handle(WindowEvent event) {
        lily.exit();
    }

}
