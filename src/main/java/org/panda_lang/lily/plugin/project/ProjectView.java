package org.panda_lang.lily.plugin.project;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.panda_lang.lily.util.FileComparator;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ProjectView extends TreeView<String> {

    private final ProjectView tree;
    private final Image defaultFileIcon;
    private final Image defaultFolderIcon;
    private final Map<TreeItem<String>, File> files;
    private final FileComparator comparator;
    private File directory;

    public ProjectView(ProjectPlugin projectPlugin) {
        super();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/plugins/project/project_view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.tree = this;
        this.files = new HashMap<>();
        this.comparator = new FileComparator();
        this.defaultFileIcon = new Image(getClass().getResourceAsStream("/ui/icons/material_defaultFileIcon.png"));
        this.defaultFolderIcon = new Image(getClass().getResourceAsStream("/ui/icons/material_defaultFolderIcon.png"));

        this.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() < 2) {
                return;
            }

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

            //Lily.instance.getUI().displayFile(file);
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
            return;
        }

        File[] files = directory.listFiles();

        if (files != null) {
            sort(files);

            for (File file : files) {
                if (file.isDirectory()) {
                    findFiles(file, root);
                    continue;
                }

                addFile(root, file);
            }
        }

        root.setExpanded(false);
        root.setGraphic(new ImageView(defaultFolderIcon));

        if (parent == null) {
            root.setExpanded(true);
            tree.setRoot(root);
            return;
        }

        parent.getChildren().add(root);
    }

    public void sort(File[] array) {
        Arrays.sort(array, comparator);
    }

    public File getDirectory() {
        return directory;
    }

}
