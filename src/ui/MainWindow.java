package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;


public class MainWindow extends Stage {


    private Button openText;
    private Button openImage;
    private ImageView imagePost;
    private TextArea textPost;

    public MainWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
            Parent parent = loader.load();


            openImage = (Button) loader.getNamespace().get("openImage");
            imagePost = (ImageView) loader.getNamespace().get("imagePost");

            openText = (Button) loader.getNamespace().get("openText");
            textPost = (TextArea) loader.getNamespace().get("textPost");

            Scene scene = new Scene(parent, 600, 400);
            setScene(scene);

            init();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void init() {

    }

}
