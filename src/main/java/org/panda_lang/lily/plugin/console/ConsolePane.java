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

package org.panda_lang.lily.plugin.console;

import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class ConsolePane extends BorderPane {

    private static ConsolePane instance;
    private final ConsoleOutputStream consoleOutputStream;
    private final TextArea textArea;

    protected ConsolePane() {
        this.textArea = new TextArea();
        this.consoleOutputStream = new ConsoleOutputStream(this);

        instance = this;
        setCenter(textArea);
        System.setOut(new PrintStream(consoleOutputStream));
    }

    public void bind(Region parent) {
        prefWidthProperty().bind(parent.prefWidthProperty());
        prefHeightProperty().bind(parent.prefHeightProperty());
    }

    public void clear() {
        textArea.setText("");
    }

    public void write(char c) {
        textArea.setText(textArea.getText() + c);
    }

    public TextArea getTextArea() {
        return textArea;
    }

    public ConsoleOutputStream getConsoleOutputStream() {
        return consoleOutputStream;
    }

    public static ConsolePane getInstance() {
        return instance == null ? new ConsolePane() : instance;
    }

    private class ConsoleOutputStream extends OutputStream {

        private final ConsolePane consolePane;

        public ConsoleOutputStream(ConsolePane consolePane) {
            this.consolePane = consolePane;
        }

        @Override
        public void write(int b) throws IOException {
            consolePane.write((char) b);
        }

    }

}
