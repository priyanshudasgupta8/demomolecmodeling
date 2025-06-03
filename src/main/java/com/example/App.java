package com.example;

import java.io.IOException;
import java.util.ArrayList;

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
import javafx.scene.shape.Shape3D;
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

        // Create a list of shapes to hold the atoms and bonds
        ArrayList<Shape3D> shapes = new ArrayList<Shape3D>();

        // Create atoms and bonds
        Sphere Oxy = AtomsBonds.Oxygen(500, 250);
        Cylinder bond = AtomsBonds.Bond(460, 250);
        Sphere Carb = AtomsBonds.Carbon(420, 250);

        // add everything into shapers arraylist
        shapes.add(Oxy);
        shapes.add(Carb);
        shapes.add(bond);

        Group g = AtomsBonds.makeProtein(new Group(), 200, 200);

        /* remember: bonds have to be rotated because they are initially vertical
        * 0 is vertical, 90 is horizontal, all intervals in between are diagonal
        */
        bond.rotateProperty().set(90);

        //Camera object
        Camera cam = new PerspectiveCamera();

        //group - collection of items put into stage
        // Group g = new Group();

        // remeber that we can add groups to groups
        // so we can build complex shapes by creating small groups and sticking them together
        Group g2 = new Group();

        // scene - window created (?)
        Scene s = new Scene(g , 1000, 500);
        s.setFill(Color.BLANCHEDALMOND);

        // how we are handling key presses here - instead or rotating the camera, we are rotating the world
        // and moving the group around - this is stupid code and not so efficient, but it works
        Rotate worldRotX = new Rotate(0, Rotate.X_AXIS);
        Rotate worldRotY = new Rotate(0, Rotate.Y_AXIS);

        g.getTransforms().addAll(worldRotY, worldRotX);
        

        // Keyboard event handler for camera controls
        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch(event.getCode()){
                /*  left right rotations
                this is the rotation of the world, *not the camera* (!!!!!!!!)
                this is fucking terrible code and is absolutely suboptimal, but it works
                alternative is matrix transformations and we need methods knowledge to do that 
                etaash mathamsetty couldn't figure it out so we're doing it this way
                */
                case LEFT:
                    worldRotY.setAngle(worldRotY.getAngle() + 2);
                    break;
                case RIGHT:
                    worldRotY.setAngle(worldRotY.getAngle() - 2);
                    break;
                // up down rotations
                case UP:
                    worldRotX.setAngle(worldRotX.getAngle() + 2);
                    break;
                case DOWN:
                    worldRotX.setAngle(worldRotX.getAngle() - 2);
                    break;
                case Q: // shift/control is for z axis changes
                    g.setTranslateZ(g.getTranslateZ() + 10);
                    break;
                case E:
                    g.setTranslateZ(g.getTranslateZ() - 10);
                    break;
                case D:// a/d is x axis changes
                    g.setTranslateX(g.getTranslateX() + 10);
                    break;
                case A:
                    g.setTranslateX(g.getTranslateX() - 10);
                    break;
                case S:// w/s is for y axis changes
                    g.setTranslateY(g.getTranslateY() + 10);
                    break;
                case W:
                    g.setTranslateY(g.getTranslateY() - 10);
                    break;
                // todo - get mouse controls to move the camera
                // todo - add key controls to change the molecule shown
                // todo - add overlay UI to show controls and molecule info
                case ESCAPE: // escape key to exit
                    System.exit(0);
                    break;
                case SPACE: // space key to reset the camera
                    worldRotX.setAngle(0);
                    worldRotY.setAngle(0);
                    g.setTranslateX(0);
                    g.setTranslateY(0);
                    g.setTranslateZ(0);
                    break;
                default:
                    break;
            }
        });
        s.setCamera(cam);

        // General iteration code for adding atoms and bonds to groups
            for (Shape3D shape : shapes) {
                g.getChildren().add(shape);
        }       

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
