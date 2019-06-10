import javax.media.j3d.*; // for transform
import javax.vecmath.Color3f;
import java.awt.Color;

public class Utils {
    public static Appearance getBodyAppearence() {
        Appearance ap = new Appearance();

        Color3f emissive = new Color3f(new Color(0,0, 0));
        Color3f ambient = new Color3f(Color.ORANGE);
        Color3f diffuse = new Color3f(Color.ORANGE);
        Color3f specular = new Color3f(new Color(0,0, 0));
        // ambient, emissive, diffuse, specular, 1.0f
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));

        return ap;
    }


    public static Appearance getHeadAppearence() {
        Appearance ap = new Appearance();

        Color3f emissive = new Color3f(new Color(0,0, 0));
        Color3f ambient = new Color3f(Color.BLACK);
        Color3f diffuse = new Color3f(Color.WHITE);
        Color3f specular = new Color3f(Color.WHITE);
        // ambient, emissive, diffuse, specular, 1.0f
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));

        return ap;
    }

    public static Appearance getGriphAppearence() {
        Appearance ap = new Appearance();

        Color3f emissive = new Color3f(new Color(0,0, 0));
        Color3f ambient = new Color3f(Color.BLACK);
        Color3f diffuse = new Color3f(Color.BLACK);
        Color3f specular = new Color3f(Color.BLACK);
        // ambient, emissive, diffuse, specular, 1.0f
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));

        return ap;
    }

    public static Appearance getRezinAppearence() {
        Appearance ap = new Appearance();

        Color3f emissive = new Color3f(new Color(0,0, 0));
        Color3f ambient = new Color3f(Color.PINK);
        Color3f diffuse = new Color3f(Color.PINK);
        Color3f specular = new Color3f(Color.PINK);
        // ambient, emissive, diffuse, specular, 1.0f
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));

        return ap;
    }

    public static Appearance getMetalAppearence() {
        Appearance ap = new Appearance();

        Color3f emissive = new Color3f(new Color(0,0, 0));
        Color3f ambient = new Color3f(Color.LIGHT_GRAY);
        Color3f diffuse = new Color3f(Color.LIGHT_GRAY);
        Color3f specular = new Color3f(Color.LIGHT_GRAY);
        // ambient, emissive, diffuse, specular, 1.0f
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));

        return ap;
    }

}
