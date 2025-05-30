package com.example;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setTitle("test");

        // create sphere and move it around
        Sphere sphere = new Sphere(40);
        sphere.translateXProperty().set(500);
        sphere.translateYProperty().set(250);

        //Camera object
        Camera cam = new PerspectiveCamera();

        Group g = new Group();
        Scene s = new Scene(g , 1000, 500);
        s.setFill(Color.BLANCHEDALMOND);
        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case W: 
                    sphere.translateZProperty().set(sphere.getTranslateZ()+10);
                    break;
                case S: 
                    sphere.translateZProperty().set(sphere.getTranslateZ()-10);
                    break;
                case A: 
                    sphere.translateXProperty().set(sphere.getTranslateX()-1);
                    break;
                case D: 
                    sphere.translateXProperty().set(sphere.getTranslateX()+1);
                    break;
                case UP: 
                    sphere.translateYProperty().set(sphere.getTranslateY()-1);
                    break;
                case DOWN: 
                    sphere.translateYProperty().set(sphere.getTranslateY()+1);
                    break;
                // just remember - mouse events are able to be implemented
                // including!!!! pressing on a button
            }
        });
        s.setCamera(cam);

        g.getChildren().add(sphere);
        stage.setScene(s);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}