package com.example;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
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
    public static Sphere Carbon(double x, double y, double z) {
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
    public static Sphere Oxygen(double x, double y, double z) {
        Sphere o = new Sphere(20);
        o.translateXProperty().set(x);
        o.translateYProperty().set(y);
        o.translateZProperty().set(z);

        PhongMaterial OxyM = new PhongMaterial();
        OxyM.setDiffuseColor(Color.RED);
        OxyM.setSpecularColor(Color.RED);

        o.setMaterial(OxyM);
        return o;
    }
    public static Sphere Hydrogen(double x, double y, double z) {
        Sphere h = new Sphere(15);
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
        Sphere n = new Sphere(20);
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

    public static Sphere Phosphorus(double x, double y, double z) {
        Sphere p = new Sphere(20);
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
        Sphere s = new Sphere(20);
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
    public static Group makeProtein(Group R_group, double x, double y, double z) {
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
        protein.getChildren().add(R_group);
        
        R_group.setRotationAxis(Rotate.X_AXIS);
        R_group.setRotate((180.0*Math.acos(-1.0/3))/Math.PI);
        R_group.setTranslateZ( 20.0*Math.sqrt(2)/3);
        R_group.setTranslateY(-45);


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

        // matrixRotateNode(b2, Math.toRadians(angle-12), Math.toRadians(30), Math.toRadians(0));
        matrixRotateNode(b2, Math.toRadians(angle-12), Math.toRadians(30), Math.toRadians(0));
        System.out.println(Math.toRadians(angle-12));
        b2.setTranslateX(x - lBondDisplacement);
        b2.setTranslateY(y + 10);
        b2.setTranslateZ(z + dBondDisplacement);
        protein.getChildren().add(b2);
        
        // amino group
        double X = x - 2*lBondDisplacement;
        double Y = y + 20;
        double Z = z + 2*dBondDisplacement;
        protein.getChildren().add(Nitrogen(X, Y, Z));

        Cylinder b3 = Bond(0, 0, 0); // x-81, y+21, z
        matrixRotateNode(b3, Math.PI/2-0.991519, 0.524494, 0); // method is to find plane, rodriguez's theorem it w.r.t. nhat and then find unit vector and convert to spherical
        protein.getChildren().add(b3);
        b3.setTranslateX(X + 30*Math.sin(-0.579277));
        b3.setTranslateY(Y - 30*Math.sin(0.524494));
        b3.setTranslateZ(Z + 30*Math.sin(Math.PI/2-0.991519) -1);

        Cylinder b4 = Bond(0,0,0); // x-81, y-21, z
        matrixRotateNode(b4, Math.PI/2-1.12103671373, -1.24639325873, 0);
        protein.getChildren().add(b4);
        b4.setTranslateX(X + 15*Math.sin(0.962364910941) - 8);
        b4.setTranslateY(Y + 15*Math.sin(1.24639325873));
        b4.setTranslateZ(Z + 45*Math.sin(Math.PI/2-1.12103671373));

        protein.getChildren().add(Hydrogen(X + 2*(15*Math.sin(0.962364910941) - 8), Y + 1.5*(15*Math.sin(1.24639325873)), Z + 2.4*(45*Math.sin(Math.PI/2-1.12103671373))));
        protein.getChildren().add(Hydrogen(X + 2*(30*Math.sin(-0.579277)), Y - 2*(30*Math.sin(0.524494)), Z + 2*(30*Math.sin(Math.PI/2-0.991519) - 1)));

        
        // carboxyl group
        double Xi = x + 2*rBondDisplacement;
        double Yi = y + 20;
        
        protein.getChildren().add(Carbon(Xi+rBondDisplacement, Yi+5, Z-4));
        Cylinder b5 = Bond(Xi+2*rBondDisplacement, Yi-20, Z);
        Cylinder b5_1 = Bond((Xi+2*rBondDisplacement)-10, Yi-25, Z-4);

        
        double arb = Math.sqrt( (Math.pow(Math.cos(Math.toRadians(30)), 2)) + 
        (Math.pow(Math.sin(Math.toRadians(60)), 2)) );


        double COxylAngX = Math.acos(Math.cos(Math.toRadians(30))/arb);
        double COxylAngY = Math.asin(Math.sin(Math.toRadians(60))/arb);
        matrixRotateNode(b5, Math.toRadians(angle+COxylAngX), Math.toRadians(30+COxylAngY), Math.toRadians(90));
        matrixRotateNode(b5_1, Math.toRadians(angle+COxylAngX), Math.toRadians(30+COxylAngY), Math.toRadians(90));

        protein.getChildren().add(Oxygen(Xi+(2.5*rBondDisplacement), Yi-52.5, Z-2));


        Cylinder b6 = Bond(Xi+2*rBondDisplacement, Yi+30, Z-4);
        matrixRotateNode(b6, Math.toRadians(-(angle+COxylAngX)), Math.toRadians(-(30+COxylAngY)), Math.toRadians(90));
        protein.getChildren().add(Oxygen(Xi+(3*rBondDisplacement), Yi+60, Z-2));

        Cylinder b7 = Bond(0,0,0);
        matrixRotateNode(b7, Math.toRadians(-(angle+COxylAngX)), Math.PI-Math.toRadians(-(30+COxylAngY)),0);
        b7.setTranslateX(Xi+4*rBondDisplacement);
        b7.setTranslateY(Yi+70);
        b7.setTranslateZ(Z+12);
        protein.getChildren().add(Hydrogen(Xi+5*rBondDisplacement, Yi+80, Z+24));

        
        protein.getChildren().add(b5);
        protein.getChildren().add(b5_1);
        protein.getChildren().add(b6);
        protein.getChildren().add(b7);

        return protein;
    }

    public static Group Al(double x, double y, double z) {
        Group Al = new Group();

        double arb = Math.sqrt( (Math.pow(Math.cos(Math.toRadians(71)), 2)) + (Math.pow(Math.sin(Math.toRadians(71)), 2)) );
        double AlaAngZ = Math.acos(Math.cos(Math.toRadians(71))/arb);
        double AlaAngY = Math.acos(Math.sin(Math.toRadians(71))/arb);

        // Al.getChildren().add(Carbon(x, y-10, z));

        Cylinder b1 = Bond(x, y-(30*Math.sin(19)) , z+100);
        matrixRotateNode(b1, Math.toRadians(0), Math.toRadians(AlaAngY), Math.toRadians(AlaAngZ));
        Al.getChildren().add(b1);
        Al.getChildren().add(Hydrogen(x, y - 950*(Math.sin(19)), z-(120*Math.sin(Math.toRadians(79)))-15));

        // Cylinder b2 = Bond(x , y + 60*Math.sin(Math.toRadians(19)), z - (60*Math.sin(Math.toRadians(79))));
        // matrixRotateNode(b2, Math.toRadians(180), Math.toRadians(AlaAngY), Math.toRadians(AlaAngZ));
        
        return Al;
    }

    public static Group makeGlucose(double x, double y, double z) {
        Group glucose = new Group();

        glucose.getChildren().add(Carbon(200,200,200));
        Cylinder b1 = Bond(0,0,0);
        matrixRotateNode(b1, Math.toRadians(90), 0, 0);
        b1.setTranslateX(235);
        b1.setTranslateY(200);
        b1.setTranslateZ(200);
        glucose.getChildren().add(b1);

        glucose.getChildren().add(Oxygen(270,200,200));
        Cylinder b2 = Bond(0,0,0);
        matrixRotateNode(b2, Math.toRadians(30), 0, 0);
        b2.setTranslateX(270+35*Math.cos(Math.toRadians(60)));
        b2.setTranslateY(200+35*Math.sin(Math.toRadians(60)));
        b2.setTranslateZ(200);
        glucose.getChildren().add(b2);

        glucose.getChildren().add(Carbon(270+70*Math.cos(Math.toRadians(60)),200+70*Math.sin(Math.toRadians(60)),200));
        Cylinder b3 = Bond(0,0,0);
        matrixRotateNode(b3, Math.toRadians(-30), 0, 0);
        b3.setTranslateX(200-35*Math.cos(Math.toRadians(60)));
        b3.setTranslateY(200+35*Math.sin(Math.toRadians(60)));
        b3.setTranslateZ(200);
        glucose.getChildren().add(b3);

        //9oclock
        glucose.getChildren().add(Carbon(200-70*Math.cos(Math.toRadians(60)),200+70*Math.sin(Math.toRadians(60)),200));
        Cylinder b4 = Bond(0,0,0);
        matrixRotateNode(b4, Math.toRadians(30), 0, 0);
        b4.setTranslateX(200-35*Math.cos(Math.toRadians(60)));
        b4.setTranslateY(200+105*Math.sin(Math.toRadians(60)));
        b4.setTranslateZ(200);
        glucose.getChildren().add(b4);

        glucose.getChildren().add(Carbon(200,200+140*Math.sin(Math.toRadians(60)),200));
        Cylinder b5 = Bond(0,0,0);
        matrixRotateNode(b5, Math.toRadians(-30), 0, 0);
        b5.setTranslateX(270+35*Math.cos(Math.toRadians(60)));
        b5.setTranslateY(200+105*Math.sin(Math.toRadians(60)));
        b5.setTranslateZ(200);
        glucose.getChildren().add(b5);

        glucose.getChildren().add(Carbon(270,200+140*Math.sin(Math.toRadians(60)),200));
        Cylinder b6 = Bond(0,0,0);
        matrixRotateNode(b6, Math.toRadians(90), 0, 0);
        b6.setTranslateX(235);
        b6.setTranslateY(200+140*Math.sin(Math.toRadians(60)));
        b6.setTranslateZ(200);
        glucose.getChildren().add(b6);

        //9oclock
        glucose.getChildren().add(Hydrogen(200-70*Math.cos(Math.toRadians(60))-(70/Math.sqrt(2)),200+70*Math.sin(Math.toRadians(60)),200-(70/Math.sqrt(2))));
        Cylinder b9 = Bond(0,0,0);
        matrixRotateNode(b9, Math.toRadians(90), Math.toRadians(-45), 0);
        b9.setTranslateX(200-70*Math.cos(Math.toRadians(60))-(35/Math.sqrt(2)));
        b9.setTranslateY(200+70*Math.sin(Math.toRadians(60)));
        b9.setTranslateZ(200-(35/Math.sqrt(2)));
        glucose.getChildren().add(b9);

        glucose.getChildren().add(Oxygen(200-70*Math.cos(Math.toRadians(60))-(70/Math.sqrt(2)),200+70*Math.sin(Math.toRadians(60)),200+(70/Math.sqrt(2))));
        glucose.getChildren().add(Hydrogen(200-70*Math.cos(Math.toRadians(60))-(70/Math.sqrt(2)),200+70*Math.sin(Math.toRadians(60)),200+(70/Math.sqrt(2))+18));
        Cylinder b10 = Bond(0,0,0);
        matrixRotateNode(b10, Math.toRadians(90), Math.toRadians(45), 0);
        b10.setTranslateX(200-70*Math.cos(Math.toRadians(60))-(35/Math.sqrt(2)));
        b10.setTranslateY(200+70*Math.sin(Math.toRadians(60)));
        b10.setTranslateZ(200+(35/Math.sqrt(2)));
        glucose.getChildren().add(b10);

        // 3 O CLOCK
        glucose.getChildren().add(Oxygen(270+70*Math.cos(Math.toRadians(60))+(70/Math.sqrt(2)),200+70*Math.sin(Math.toRadians(60)),200-(70/Math.sqrt(2))));
        glucose.getChildren().add(Hydrogen(270+70*Math.cos(Math.toRadians(60))+(70/Math.sqrt(2)),200+70*Math.sin(Math.toRadians(60)),200-(70/Math.sqrt(2))-18));

        Cylinder b11 = Bond(0,0,0);
        matrixRotateNode(b11, Math.toRadians(90), Math.toRadians(45), 0);
        b11.setTranslateX(270+70*Math.cos(Math.toRadians(60))+(35/Math.sqrt(2)));
        b11.setTranslateY(200+70*Math.sin(Math.toRadians(60)));
        b11.setTranslateZ(200-(35/Math.sqrt(2)));
        glucose.getChildren().add(b11);

        glucose.getChildren().add(Hydrogen(270+70*Math.cos(Math.toRadians(60))+(70/Math.sqrt(2)),200+70*Math.sin(Math.toRadians(60)),200+(70/Math.sqrt(2))));        
        Cylinder b12 = Bond(0,0,0);
        matrixRotateNode(b12, Math.toRadians(90), Math.toRadians(-45), 0);
        b12.setTranslateX(270+70*Math.cos(Math.toRadians(60))+(35/Math.sqrt(2)));
        b12.setTranslateY(200+70*Math.sin(Math.toRadians(60)));
        b12.setTranslateZ(200+(35/Math.sqrt(2)));
        glucose.getChildren().add(b12);

        //7oclock
        glucose.getChildren().add(Oxygen(200-(70/Math.sqrt(2))*Math.cos(Math.toRadians(60)),200+140*Math.cos(Math.toRadians(30))+(70/Math.sqrt(2))*Math.sin(Math.toRadians(60)),200-(70/Math.sqrt(2))));
        glucose.getChildren().add(Hydrogen(200-(70/Math.sqrt(2))*Math.cos(Math.toRadians(60)),200+140*Math.cos(Math.toRadians(30))+(70/Math.sqrt(2))*Math.sin(Math.toRadians(60)),200-(70/Math.sqrt(2))-18));
        Cylinder b13 = Bond(0,0,0);
        matrixRotateNode(b13, Math.toRadians(150), Math.toRadians(-45), 0);
        b13.setTranslateX(200-(35/Math.sqrt(2))*Math.cos(Math.toRadians(60)));
        b13.setTranslateY(200+140*Math.cos(Math.toRadians(30))+(35/Math.sqrt(2))*Math.sin(Math.toRadians(60)));
        b13.setTranslateZ(200-(35/Math.sqrt(2)));
        glucose.getChildren().add(b13);

        glucose.getChildren().add(Hydrogen(200-(70/Math.sqrt(2))*Math.cos(Math.toRadians(60)),200+140*Math.cos(Math.toRadians(30))+(70/Math.sqrt(2))*Math.sin(Math.toRadians(60)),200+(70/Math.sqrt(2))));
        Cylinder b14 = Bond(0,0,0);
        matrixRotateNode(b14, Math.toRadians(150), Math.toRadians(45), 0);
        b14.setTranslateX(200-(35/Math.sqrt(2))*Math.cos(Math.toRadians(60)));
        b14.setTranslateY(200+140*Math.cos(Math.toRadians(30))+(35/Math.sqrt(2))*Math.sin(Math.toRadians(60)));
        b14.setTranslateZ(200+(35/Math.sqrt(2)));
        glucose.getChildren().add(b14);

        //5oclock
        glucose.getChildren().add(Hydrogen(270+(70/Math.sqrt(2))*Math.cos(Math.toRadians(60)),200+140*Math.cos(Math.toRadians(30))+(70/Math.sqrt(2))*Math.sin(Math.toRadians(60)),200-(70/Math.sqrt(2))));
        Cylinder b15 = Bond(0,0,0);
        matrixRotateNode(b15, Math.toRadians(-150), Math.toRadians(-45), 0);
        b15.setTranslateX(270+(35/Math.sqrt(2))*Math.cos(Math.toRadians(60)));
        b15.setTranslateY(200+140*Math.cos(Math.toRadians(30))+(35/Math.sqrt(2))*Math.sin(Math.toRadians(60)));
        b15.setTranslateZ(200-(35/Math.sqrt(2)));
        glucose.getChildren().add(b15);

        glucose.getChildren().add(Oxygen(270+(70/Math.sqrt(2))*Math.cos(Math.toRadians(60)),200+140*Math.cos(Math.toRadians(30))+(70/Math.sqrt(2))*Math.sin(Math.toRadians(60)),200+(70/Math.sqrt(2))));
        glucose.getChildren().add(Hydrogen(270+(70/Math.sqrt(2))*Math.cos(Math.toRadians(60)),200+140*Math.cos(Math.toRadians(30))+(70/Math.sqrt(2))*Math.sin(Math.toRadians(60)),200+(70/Math.sqrt(2))+18));
        Cylinder b16 = Bond(0,0,0);
        matrixRotateNode(b16, Math.toRadians(-150), Math.toRadians(45), 0);
        b16.setTranslateX(270+(35/Math.sqrt(2))*Math.cos(Math.toRadians(60)));
        b16.setTranslateY(200+140*Math.cos(Math.toRadians(30))+(35/Math.sqrt(2))*Math.sin(Math.toRadians(60)));
        b16.setTranslateZ(200+(35/Math.sqrt(2)));
        glucose.getChildren().add(b16);

        // 11 O Clock
        glucose.getChildren().add(Carbon(200-(70/Math.sqrt(2))*Math.cos(Math.toRadians(60)),200-(70/Math.sqrt(2))*Math.sin(Math.toRadians(60)),200-(70/Math.sqrt(2))));
        Cylinder b7 = Bond(0,0,0);
        matrixRotateNode(b7, Math.toRadians(30), Math.toRadians(-45), 0);
        b7.setTranslateX(200-(35/Math.sqrt(2))*Math.cos(Math.toRadians(60)));
        b7.setTranslateY(200-(35/Math.sqrt(2))*Math.sin(Math.toRadians(60)));
        b7.setTranslateZ(200-(35/Math.sqrt(2)));
        glucose.getChildren().add(b7);

        glucose.getChildren().add(Hydrogen(200-(70/Math.sqrt(2))*Math.cos(Math.toRadians(60)),200-(70/Math.sqrt(2))*Math.sin(Math.toRadians(60)),200+(70/Math.sqrt(2))));
        Cylinder b8 = Bond(0,0,0);
        matrixRotateNode(b8, Math.toRadians(30), Math.toRadians(45), 0);
        b8.setTranslateX(200-(35/Math.sqrt(2))*Math.cos(Math.toRadians(60)));
        b8.setTranslateY(200-(35/Math.sqrt(2))*Math.sin(Math.toRadians(60)));
        b8.setTranslateZ(200+(35/Math.sqrt(2)));
        glucose.getChildren().add(b8);

        glucose.getChildren().add(Oxygen(
            200-(70/Math.sqrt(2))*Math.cos(Math.toRadians(60)),
            200-(70/Math.sqrt(2))*Math.sin(Math.toRadians(60)),
            200-(70/Math.sqrt(2))-70
        ));
        glucose.getChildren().add(Hydrogen(
            200-(70/Math.sqrt(2))*Math.cos(Math.toRadians(60)),
            200-(70/Math.sqrt(2))*Math.sin(Math.toRadians(60)),
            200-(70/Math.sqrt(2))-88
        ));
        Cylinder b17 = Bond(0,0,0);
        matrixRotateNode(b17, Math.toRadians(0), Math.toRadians(-90), 0);
        b17.setTranslateX(200-(70/Math.sqrt(2))*Math.cos(Math.toRadians(60)));
        b17.setTranslateY(200-(70/Math.sqrt(2))*Math.sin(Math.toRadians(60)));
        b17.setTranslateZ(200-(70/Math.sqrt(2))-35);
        glucose.getChildren().add(b17);




        return glucose;
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
