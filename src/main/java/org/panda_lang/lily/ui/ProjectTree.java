package org.panda_lang.lily.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.panda_lang.lily.Lily;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ProjectTree {

    private final TreeView<String> tree;
    private final Image defaultFileIcon;
    private final Image defaultFolderIcon;
    private final Map<TreeItem<String>, File> files;
    private File directory;

    public ProjectTree(TreeView<String> tree) {
        this.tree = tree;
        this.files = new HashMap<>();
        this.defaultFileIcon = new Image(getClass().getResourceAsStream("/ui/icons/material_defaultFileIcon.png"));
        this.defaultFolderIcon = new Image(getClass().getResourceAsStream("/ui/icons/material_defaultFolderIcon.png"));

        this.tree.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2) {
                TreeItem<String> item = tree.getSelectionModel().getSelectedItem();
                File file = files.get(item);

                if (file.isDirectory()) {
                    return;
                }

                double bytes = file.length();
                double kilobytes = (bytes / 1024);
                double megabytes = (kilobytes / 1024);

                if (megabytes > 1) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Lily Warning");
                    alert.setHeaderText("File is too large");
                    alert.setContentText("File can not be larger than 1MB");

                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image("/ui/icons/icon.png"));

                    alert.showAndWait();
                    return;
                }

                try {
                    Lily.instance.getInterface().displayFile(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void open(File directory) {
        this.directory = directory;
        findFiles(directory, null);
    }

    private void addFile(TreeItem<String> root, File file) {
        TreeItem<String> item = new TreeItem<>(file.getName());
        item.setGraphic(new ImageView(defaultFileIcon));
        root.getChildren().add(item);
        files.put(item, file);
    }

    private void findFiles(File directory, TreeItem<String> parent) {
        TreeItem<String> root = new TreeItem<>(directory.getName());

        if (directory.isFile()) {
            addFile(root, directory);
        }
        else {
            final File[] files = directory.listFiles();

            if (files != null) {
                sort(files);

                for (File file : files) {
                    if (file.isDirectory()) {
                        findFiles(file, root);
                    }
                    else {
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
        }
        else {
            parent.getChildren().add(root);
        }
    }

    public void sort(File[] array) {
        Comparator<File> comp = (f1, f2) -> {
            if (f1.isDirectory() && !f2.isDirectory()) {
                return -1;
            }
            else if (!f1.isDirectory() && f2.isDirectory()) {
                return 1;
            }
            else {
                return f1.compareTo(f2);
            }
        };
        Arrays.sort(array, comp);
    }

    public File getDirectory() {
        return directory;
    }

}
