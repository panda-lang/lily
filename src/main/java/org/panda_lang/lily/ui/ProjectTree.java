package org.panda_lang.lily.ui;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.panda_lang.lily.Lily;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ProjectTree {

    private final TreeView<String> tree;
    private final Image defaultFileIcon;
    private final Image defaultFolderIcon;
    private final Map<TreeItem<String>, File> files;

    public ProjectTree(TreeView<String> tree) {
        this.tree = tree;
        this.files = new HashMap<>();
        this.defaultFileIcon = new Image(getClass().getResourceAsStream("/icons/material_defaultFileIcon.png"));
        this.defaultFolderIcon = new Image(getClass().getResourceAsStream("/icons/material_defaultFolderIcon.png"));

        this.tree.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2) {
                TreeItem<String> item = tree.getSelectionModel().getSelectedItem();
                File file = files.get(item);
                Lily.instance.getInterface().displayFile(file);
            }
        });
    }

    public void open(File dir) {
        findFiles(dir, null);
    }

    private void addFile(TreeItem<String> root, File file) {
        TreeItem<String> item = new TreeItem<>(file.getName());
        item.setGraphic(new ImageView(defaultFileIcon));
        root.getChildren().add(item);
        files.put(item, file);
    }

    private void findFiles(File dir, TreeItem<String> parent) {
        TreeItem<String> root = new TreeItem<>(dir.getName());

        if (dir.isFile()) {
            addFile(root, dir);
        } else {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        findFiles(file, root);
                    } else {
                        addFile(root, file);
                    }
                }
            }
        }

        root.setExpanded(false);
        root.setGraphic(new ImageView(defaultFolderIcon));
        if (parent == null) {
            root.setExpanded(true);
            tree.setRoot(root);
        } else {
            parent.getChildren().add(root);
        }
    }

}
