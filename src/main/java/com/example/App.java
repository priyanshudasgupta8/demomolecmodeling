package com.example;
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {


    private static Scene scene;

    private double mouseAnchorX;
    private double mouseAnchorY;

    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();
        
        stage.setTitle("test");
        Group g = new Group();
        Group p = AtomsBonds.makeProtein(AtomsBonds.Al(200, 200, 200), 200, 200, 200);
        g.getChildren().add(p);
        g.getChildren().add(root);

        //Camera object
        Camera cam = new PerspectiveCamera();

        ComboBox dropdown = new ComboBox<>();
        ObservableList<String> list = dropdown.getItems();
        list.add("Alanine");
        list.add("Glucose");

        root.setTop(dropdown);

        dropdown.setOnAction(eve -> {
            String selected = (String) dropdown.getValue();
            g.getChildren().clear(); // clear the group before adding new items
            switch (selected) {
                case "Alanine":
                    g.getChildren().remove(p);
                    g.getChildren().add(dropdown);
                    g.getChildren().add(AtomsBonds.makeProtein(AtomsBonds.Al(200, 200, 200), 200, 200, 200));
                    break;
                case "Glucose":
                    g.getChildren().remove(p);
                    g.getChildren().add(dropdown);
                    g.getChildren().add(AtomsBonds.Phosphorus(200, 200, 200));
                    break;
            }
        });


        // scene - window created (?)
        // 4th parameter always true to enable correct 3d behavior
        Scene s = new Scene(g, 1000, 500, true);
        s.setFill(Color.BLACK);

        // how we are handling key presses here - instead or rotating the camera, we are rotating the world
        // and moving the group around - this is stupid code and not so efficient, but it works
        Rotate worldRotX = new Rotate(0, Rotate.X_AXIS);
        Rotate worldRotY = new Rotate(0, Rotate.Y_AXIS);
        Rotate worldRotZ = new Rotate(0, Rotate.Z_AXIS);

        g.getTransforms().addAll(worldRotY, worldRotX, worldRotZ);
        

        // Keyboard event handler for camera controls
        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch(event.getCode()){
                /*  left right rotations
                this is the rotation of the world, *not the camera* (!!!!!!!!)
                this is ******* terrible code and is absolutely suboptimal, but it works
                alternative is matrix transformations and we need methods knowledge to do that 
                etaash mathamsetty couldn't figure it out so we're doing it this way
                */
                case Q: // shift/control is for z axis changes
                    g.setTranslateZ(g.getTranslateZ() + 10);
                    break;
                case E:
                    g.setTranslateZ(g.getTranslateZ() - 10);
                    break;
                case D:// a/d is x axis changes
                    g.setTranslateX(g.getTranslateX() - 10);
                    break;
                case A:
                    g.setTranslateX(g.getTranslateX() + 10);
                    break;
                case S:// w/s is for y axis changes
                    g.setTranslateY(g.getTranslateY() - 10);
                    break;
                case W:
                    g.setTranslateY(g.getTranslateY() + 10);
                    break;
                case R:
                    worldRotZ.setAngle(worldRotZ.getAngle() - 2);
                    System.out.println(worldRotZ);
                    break;
                case F:
                    worldRotZ.setAngle(worldRotZ.getAngle() + 2);
                    System.out.println(worldRotZ);
                    break;
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

        //Mouse Drag --> rotates world and shit; makes u trip
        s.setOnMousePressed(e -> {
            mouseAnchorX = e.getSceneX();
            mouseAnchorY = e.getSceneY();
        });
 

        s.setOnMouseDragged(e -> {
            double deltaX = e.getSceneX() - mouseAnchorX;
            double deltaY = e.getSceneY() - mouseAnchorY;

            if (e.isPrimaryButtonDown()) {
                // Left-click drag = rotate
                worldRotX.setAngle(worldRotX.getAngle() - deltaY / 10);
                worldRotY.setAngle(worldRotY.getAngle() - deltaX / 10);
            } else if (e.isSecondaryButtonDown()) {
                // Right-click drag = translate
                g.setTranslateX(g.getTranslateX() + deltaX);
                g.setTranslateY(g.getTranslateY() + deltaY);
            }
            mouseAnchorX = e.getSceneX();
            mouseAnchorY = e.getSceneY();
        });   

        
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
