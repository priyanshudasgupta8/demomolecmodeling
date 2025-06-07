package com.example;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
// todo: find out the atomic sizes of each atom and match it to radius
public class AtomsBonds {
    /*  for the elements, we do individual methods instead of child classes and inheritance because
    i am stupid and forgot to do this when i was making the project
    and now i don't want to change it because it would take too long
    I do know how to do inheritance and polymorphism, but I just don't want to change it
    so we have individual methods for each atom type
    yeah
    */
    public static Sphere Carbon(int x, int y, int z) {
        Sphere c = new Sphere(20);
        c.translateXProperty().set(x);
        c.translateYProperty().set(y);
        c.translateZProperty().set(z);

        PhongMaterial CarbM = new PhongMaterial();
        CarbM.setDiffuseColor(Color.rgb(100, 100, 100));
        CarbM.setSpecularColor(Color.rgb(100, 100, 100));

        c.setMaterial(CarbM);
        return c;
    }
    public static Sphere Oxygen(int x, int y, int z) {
        Sphere o = new Sphere(25);
        o.translateXProperty().set(x);
        o.translateYProperty().set(y);
        o.translateZProperty().set(z);

        PhongMaterial OxyM = new PhongMaterial();
        OxyM.setDiffuseColor(Color.RED);
        OxyM.setSpecularColor(Color.RED);

        o.setMaterial(OxyM);
        return o;
    }
    public static Sphere Hydrogen(int x, int y, int z) {
        Sphere h = new Sphere(20*(53.0/70));
        h.translateXProperty().set(x);
        h.translateYProperty().set(y);
        h.translateZProperty().set(z);

        PhongMaterial HyM = new PhongMaterial();
        HyM.setDiffuseColor(Color.WHITE);
        HyM.setSpecularColor(Color.WHITE);

        h.setMaterial(HyM);
        return h;
    }
    public static Sphere Nitrogen(double x, double y, double z) {
        Sphere n = new Sphere(20/1.07);
        n.translateXProperty().set(x);
        n.translateYProperty().set(y);
        n.translateZProperty().set(z);

        PhongMaterial NitroM = new PhongMaterial();
        NitroM.setDiffuseColor(Color.rgb(30, 80, 231));
        NitroM.setSpecularColor(Color.rgb(30, 80, 231));

        n.setMaterial(NitroM);
        return n;
    }

    // TODO: variable bond properties
    public static Cylinder Bond(double x, double y, double z) {
        Cylinder b = new Cylinder(3, 60);
        b.translateXProperty().set(x);
        b.translateYProperty().set(y);
        b.translateZProperty().set(z);

        PhongMaterial BondCol = new PhongMaterial();
        BondCol.setDiffuseColor(Color.DARKGREY);
        BondCol.setSpecularColor(Color.DARKGREY);

        b.setMaterial(BondCol);
        return b;
    }

    public static Sphere Phosphorus(int x, int y, int z) {
        Sphere p = new Sphere(25);
        p.translateXProperty().set(x);
        p.translateYProperty().set(y);
        p.translateZProperty().set(z);

        PhongMaterial PhosphoM = new PhongMaterial();
        PhosphoM.setDiffuseColor(Color.PURPLE);
        PhosphoM.setSpecularColor(Color.PURPLE);

        p.setMaterial(PhosphoM);
        return p;
    }

    public static Sphere Sulfur(int x, int y, int z) {
        Sphere s = new Sphere(25);
        s.translateXProperty().set(x);
        s.translateYProperty().set(y);
        s.translateZProperty().set(z);

        PhongMaterial SulfM = new PhongMaterial();
        SulfM.setDiffuseColor(Color.YELLOW);
        SulfM.setSpecularColor(Color.YELLOW);

        s.setMaterial(SulfM);
        return s;
    }
    //todo: find more atoms that are necessary for organic chemistry and add them here

    // now we are doing inheritance and polymorphism because I am not stupid anymore
    // also i'm not gonna make 21 proteins skull emoji

    // todo - figure out if groups can be added to other groups w/out issues
    public static Group makeProtein(Group R_group, int x, int y, int z) {
        Group protein = new Group();

        // alpha carbon + hydrogen
        /*
         * [0,  0,        0]
         * [0,-60,        0]
         * [0, 10,60sqrt2/3] --> R
         */
        protein.getChildren().add(Carbon(x, y, z));
        protein.getChildren().add(Bond(x, y - 30, z));
        protein.getChildren().add(Hydrogen(x, y - 60, z));

        // R-group
        Cylinder r = Bond(x, y + 10, z - (60.0*Math.sqrt(2)/3));
        r.setRotationAxis(Rotate.X_AXIS);
        r.setRotate((180.0*Math.acos(-1.0/3))/Math.PI);
        
        protein.getChildren().add(r);


        double angle = (195.0 * Math.acos(-1.0 / 3)) / Math.PI; // Angle in degrees b/w atoms
        // Create the cylinder - center at origin for now lmfao
        Cylinder b1 = Bond(0, 0, 0);
        Cylinder b2 = Bond(0, 0, 0); // x-30, y, z
        
        // Apply the rotation + centers of bonds
        double lBondDisplacement = 60.0 * Math.sqrt(2) / 3 + (30 * Math.cos(Math.toRadians(75)));
        double rBondDisplacement = 60.0 * Math.sqrt(2) / 3 - (30 * Math.cos(Math.toRadians(75)));
        double dBondDisplacement = 30 * Math.sin(Math.toRadians(30));

        matrixRotateNode(b1, Math.toRadians(-angle), Math.toRadians(30), Math.toRadians(0));
        b1.setTranslateX(x + rBondDisplacement);
        b1.setTranslateY(y + 10);
        b1.setTranslateZ(z + dBondDisplacement);
        protein.getChildren().add(b1);

        matrixRotateNode(b2, Math.toRadians(angle-12), Math.toRadians(30), Math.toRadians(0));
        b2.setTranslateX(x - lBondDisplacement);
        b2.setTranslateY(y + 10);
        b2.setTranslateZ(z + dBondDisplacement);
        protein.getChildren().add(b2);
        
        // amino group
        protein.getChildren().add(Nitrogen(x - 2*lBondDisplacement, y + 20, z + 2*dBondDisplacement));
        
        // Cylinder b3 = Bond(x-81, y+21, z);
        // b3.rotateProperty().set(45);

        // Cylinder b4 = Bond(x-81, y-21, z);
        // b4.rotateProperty().set(135);

        // protein.getChildren().add(b3);
        // protein.getChildren().add(b4);
        // protein.getChildren().add(Hydrogen(x - 111, y + 42, z+300));
        // protein.getChildren().add(Hydrogen(x - 111, y - 42, z));

        // // carboxyl group
        // // todo - make this mathematically correct
        // protein.getChildren().add(Carbon(x + 60, y+15, z));
        // Cylinder b5 = Bond(x + 90, y - 6, z);
        // b5.rotateProperty().set(45);
        // protein.getChildren().add(b5);
        // Cylinder b52 = Bond(x + 68, y - 17, z);
        // b52.rotateProperty().set(45);
        // protein.getChildren().add(b52);

        // protein.getChildren().add(Oxygen(x + 95, y - 35, z));
        


        // Cylinder b6 = Bond(x + 90, y + 36, z);
        // b6.rotateProperty().set(135);
        // protein.getChildren().add(b6);
        // protein.getChildren().add(Oxygen(x + 111, y + 57, z));
        // Cylinder b7 = Bond(x + 111, y + 87, z);
        // protein.getChildren().add(b7);
        // protein.getChildren().add(Hydrogen(x + 111, y + 117, z+200));




        return protein;
    }

    // 1 AM grind; i give up on trying alternatives matrices it is
    public static Node matrixRotateNode(Node n, double alf, double bet, double gam){
        double A11=Math.cos(alf)*Math.cos(gam);
        double A12=Math.cos(bet)*Math.sin(alf)+Math.cos(alf)*Math.sin(bet)*Math.sin(gam);
        double A13=Math.sin(alf)*Math.sin(bet)-Math.cos(alf)*Math.cos(bet)*Math.sin(gam);
        double A21=-Math.cos(gam)*Math.sin(alf);
        double A22=Math.cos(alf)*Math.cos(bet)-Math.sin(alf)*Math.sin(bet)*Math.sin(gam);
        double A23=Math.cos(alf)*Math.sin(bet)+Math.cos(bet)*Math.sin(alf)*Math.sin(gam);
        double A31=Math.sin(gam);
        double A32=-Math.cos(gam)*Math.sin(bet);
        double A33=Math.cos(bet)*Math.cos(gam);

        double d = Math.acos((A11+A22+A33-1d)/2d);
        if(d!=0d){
            double den=2d*Math.sin(d);
            Point3D p= new Point3D((A32-A23)/den,(A13-A31)/den,(A21-A12)/den);
            n.setRotationAxis(p);
            n.setRotate(Math.toDegrees(d));    
        }
        return n;                
    }
    
}
