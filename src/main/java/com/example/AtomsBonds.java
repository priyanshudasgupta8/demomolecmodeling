package com.example;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
// todo: find out the atomic sizes of each atom and match it to radius
public class AtomsBonds {
    /*  for the elements, we do individual methods instead of child classes and inheritance because
    i am stupid and forgot to do this when i was making the project
    and now i don't want to change it because it would take too long
    I do know how to do inheritance and polymorphism, but I just don't want to change it
    so we have individual methods for each atom type
    yeah
    */
    public static Sphere Carbon(int x, int y) {
        Sphere c = new Sphere(25);
        c.translateXProperty().set(x);
        c.translateYProperty().set(y);

        PhongMaterial CarbM = new PhongMaterial();
        CarbM.setDiffuseColor(Color.BLACK);

        c.setMaterial(CarbM);
        return c;
    }
    public static Sphere Oxygen(int x, int y) {
        Sphere o = new Sphere(25);
        o.translateXProperty().set(x);
        o.translateYProperty().set(y);

        PhongMaterial OxyM = new PhongMaterial();
        OxyM.setDiffuseColor(Color.RED);

        o.setMaterial(OxyM);
        return o;
    }
    public static Sphere Hydrogen(int x, int y) {
        Sphere c = new Sphere(25);
        c.translateXProperty().set(x);
        c.translateYProperty().set(y);

        return c;
    }
    public static Sphere Nitrogen(int x, int y) {
        Sphere c = new Sphere(25);
        c.translateXProperty().set(x);
        c.translateYProperty().set(y);

        PhongMaterial NitroM = new PhongMaterial();
        NitroM.setDiffuseColor(Color.BLUE);

        c.setMaterial(NitroM);
        return c;
    }
    public static Cylinder Bond(int x, int y) {
        Cylinder b = new Cylinder(2, 30);
        b.translateXProperty().set(x);
        b.translateYProperty().set(y);

        PhongMaterial BondCol = new PhongMaterial();
        BondCol.setDiffuseColor(Color.DIMGREY);

        b.setMaterial(BondCol);
        return b;
    }
    public static Sphere Phosphorus(int x, int y) {
        Sphere c = new Sphere(25);
        c.translateXProperty().set(x);
        c.translateYProperty().set(y);

        PhongMaterial PhosphoM = new PhongMaterial();
        PhosphoM.setDiffuseColor(Color.PURPLE);

        c.setMaterial(PhosphoM);
        return c;
    }
    public static Sphere Sulfur(int x, int y) {
        Sphere c = new Sphere(25);
        c.translateXProperty().set(x);
        c.translateYProperty().set(y);

        PhongMaterial PhosphoM = new PhongMaterial();
        PhosphoM.setDiffuseColor(Color.YELLOW);

        c.setMaterial(PhosphoM);
        return c;
    }
    //todo: find more atoms that are necessary for organic chemistry and add them here

    // now we are doing inheritance and polymorphism because I am not stupid anymore
    // also i'm not gonna make 21 proteins skull emoji

    // todo - figure out if groups can be added to other groups w/out issues
    public static Group makeProtein(Group R_group, int x, int y) {
        Group protein = new Group();

        // alpha carbon + hydrogen
        protein.getChildren().add(Carbon(x, y));
        protein.getChildren().add(Bond(x, y - 30));
        protein.getChildren().add(Hydrogen(x, y - 60));

        protein.getChildren().add(Bond(x, y + 30));

        /* weird thing with bond rotation - you can't rotate the bond in the constructor
        so we have to do it after (because method returns null) ---- also you can't do it when adding into group soooooo
        also these are not actually accurate in position but its more simple rn hahahaha
        actual bonds are angled but we'll do it later */

        Cylinder b1 = Bond(x+30, y);
        b1.rotateProperty().set(109.5);
        protein.getChildren().add(b1);
        Cylinder b2 = Bond(x-30, y);
        b2.rotateProperty().set(-109.5);
        protein.getChildren().add(b2);

        // amino group
        protein.getChildren().add(Nitrogen(x - 60, y));
        
        Cylinder b3 = Bond(x-81, y+21);
        b3.rotateProperty().set(45);

        Cylinder b4 = Bond(x-81, y-21);
        b4.rotateProperty().set(135);

        protein.getChildren().add(b3);
        protein.getChildren().add(b4);
        protein.getChildren().add(Hydrogen(x - 111, y + 42));
        protein.getChildren().add(Hydrogen(x - 111, y - 42));

        // carboxyl group
        // todo - make this mathematically correct
        protein.getChildren().add(Carbon(x + 60, y+15));
        Cylinder b5 = Bond(x + 90, y - 6);
        b5.rotateProperty().set(45);
        protein.getChildren().add(b5);
        Cylinder b52 = Bond(x + 68, y - 17);
        b52.rotateProperty().set(45);
        protein.getChildren().add(b52);

        protein.getChildren().add(Oxygen(x + 95, y - 35));
        


        Cylinder b6 = Bond(x + 90, y + 36);
        b6.rotateProperty().set(135);
        protein.getChildren().add(b6);
        protein.getChildren().add(Oxygen(x + 111, y + 57));
        Cylinder b7 = Bond(x + 111, y + 87);
        protein.getChildren().add(b7);
        protein.getChildren().add(Hydrogen(x + 111, y + 117));




        return protein;
    }
    
}
