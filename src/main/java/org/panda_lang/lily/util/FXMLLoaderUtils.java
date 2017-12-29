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

package org.panda_lang.lily.util;

import javafx.fxml.FXMLLoader;
import org.panda_lang.lily.Lily;

import java.io.IOException;

public class FXMLLoaderUtils {

    public static void loadElementFromResources(Object element, String location) {
        FXMLLoader fxmlLoader = new FXMLLoader(Lily.class.getResource(location));
        fxmlLoader.setRoot(element);
        fxmlLoader.setController(element);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
