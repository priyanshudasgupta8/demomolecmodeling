package molec.mod;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
// todo: find out the atomic sizes of each atom and match it to radius
public class AtomsBonds {
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
        PhosphoM.setDiffuseColor(Color.YELLOW);

        c.setMaterial(PhosphoM);
        return c;
    }

    //todo: find more atoms to make compounds
}
