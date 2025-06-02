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
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
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


        // create sphere and move it to center of screen
       
        Sphere sphere = AtomsBonds.Oxygen(500, 250);
        sphere.translateXProperty().set(500);
        sphere.translateYProperty().set(250);
        Cylinder bond = AtomsBonds.Bond(460, 250);
        Sphere sphere2 = AtomsBonds.Carbon(420, 250);
        bond.rotateProperty().set(90);


        //Camera object
        Camera cam = new PerspectiveCamera();

        //group - collection of items put into stage
        Group g = new Group();

        // scene - window created (?)
        Scene s = new Scene(g , 1000, 500);
        s.setFill(Color.BLANCHEDALMOND);

        // how we are handling key presses here
        Rotate worldRotX = new Rotate(0, Rotate.X_AXIS);
        Rotate worldRotY = new Rotate(0, Rotate.Y_AXIS);

        g.getTransforms().addAll(worldRotY, worldRotX);

        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch(event.getCode()){
                case LEFT:
                    worldRotY.setAngle(worldRotY.getAngle() + 10);
                    break;
                case RIGHT:
                    worldRotY.setAngle(worldRotY.getAngle() - 10);
                    break;
                case UP:
                    worldRotX.setAngle(worldRotX.getAngle() + 10);
                    break;
                case DOWN:
                    worldRotX.setAngle(worldRotX.getAngle() - 10);
                    break;
                case W: //w/s is for z
                    g.setTranslateZ(g.getTranslateZ() + 10);
                    break;
                case S:
                    g.setTranslateZ(g.getTranslateZ() - 10);
                    break;
                case D:// a/d is x axis
                    g.setTranslateX(g.getTranslateX() + 10);
                    break;
                case A:
                    g.setTranslateX(g.getTranslateX() - 10);
                    break;
                case CONTROL:// shift/contr is for y axis
                    g.setTranslateY(g.getTranslateY() + 10);
                    break;
                case SHIFT:
                    g.setTranslateY(g.getTranslateY() - 10);
                    break;
                default:
                    break;
            }
        });
        s.setCamera(cam);


        g.getChildren().add(sphere);
        g.getChildren().add(sphere2);
        g.getChildren().add(bond);
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
