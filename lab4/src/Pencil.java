import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pencil implements ActionListener {
    private float upperEyeLimit = 15.0f;
    private float lowerEyeLimit = 5.0f;
    private float farthestEyeLimit = 28.0f;
    private float nearestEyeLimit = 25.0f;

    private TransformGroup treeTransformGroup;
    private TransformGroup viewingTransformGroup;
    private Transform3D treeTransform3D = new Transform3D();
    private Transform3D viewingTransform = new Transform3D();
    private float angle = 0;
    private float eyeHeight;
    private float eyeDistance;
    private boolean descend = true;
    private boolean approaching = true;

    public static void main(String[] args) {
        new Pencil();
    }

    private Pencil() {
        Timer timer = new Timer(50, this);
        SimpleUniverse universe = new SimpleUniverse();

        viewingTransformGroup = universe.getViewingPlatform().getViewPlatformTransform();
        universe.addBranchGraph(createSceneGraph());

        eyeHeight = upperEyeLimit;
        eyeDistance = farthestEyeLimit;
        timer.start();
    }

    private BranchGroup createSceneGraph() {
        BranchGroup objRoot = new BranchGroup();

        treeTransformGroup = new TransformGroup();
        treeTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        buildPencil();
        objRoot.addChild(treeTransformGroup);

        Background background = new Background(new Color3f(0.9f, 0.9f, 0.9f)); // white color
        BoundingSphere sphere = new BoundingSphere(new Point3d(0,0,0), 1000000000);
        background.setApplicationBounds(sphere);
        objRoot.addChild(background);

        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),100000.0);
        Color3f light1Color = new Color3f(1.0f, 1, 1);
        Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
        DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
        light1.setInfluencingBounds(bounds);
        objRoot.addChild(light1);

        Color3f ambientColor = new Color3f(1.0f, 1.0f, 1.0f);
        AmbientLight ambientLightNode = new AmbientLight(ambientColor);
        ambientLightNode.setInfluencingBounds(bounds);
        objRoot.addChild(ambientLightNode);
        return objRoot;
    }


    private TransformGroup buildTG() {
        return buildTG(new Vector3f(), new Transform3D());
    }

    private TransformGroup buildTG(Vector3f translation) {
        return buildTG(translation, new Transform3D());
    }

    private TransformGroup buildTG(Vector3f translation, Transform3D rotation){
        Transform3D transform = new Transform3D();
        TransformGroup transformG = new TransformGroup();
        transform.setTranslation(translation);
        transform.mul(rotation, transform);
        transformG.setTransform(transform);
        return transformG;
    }

    private void buildPencil() {

        // body
        Box body1 = new Box(1.73f, 9, 1, Utils.getBodyAppearence());
        TransformGroup body1TG = buildTG();
        body1TG.addChild(body1);

        Box body2 = new Box(1.73f, 9, 1, Utils.getBodyAppearence());
        Transform3D b2T = new Transform3D();
        b2T.rotY(Math.PI/3);
        TransformGroup body2TG = buildTG(new Vector3f(), b2T);
        body2TG.addChild(body2);


        Box body3 = new Box(1.73f, 9, 1, Utils.getBodyAppearence());
        Transform3D b3T = new Transform3D();
        b3T.rotY(2*Math.PI/3);
        TransformGroup body3TG = buildTG(new Vector3f(), b3T);
        body3TG.addChild(body3);

        body1TG.addChild(body2TG);
        body1TG.addChild(body3TG);

        //head
        Cone head = new Cone(1.73f, 4f, Utils.getHeadAppearence());
        TransformGroup headTG = buildTG(new Vector3f(0, 11, 0));
        headTG.addChild(head);
        body1TG.addChild(headTG);

        //griph
        Cone griph = new Cone(0.53f, 1.5f, Utils.getGriphAppearence());
        TransformGroup griphTG = buildTG(new Vector3f(0, 12.5f, 0));
        griphTG.addChild(griph);
        body1TG.addChild(griphTG);

        // rezin
        Cylinder rezin = new Cylinder(1.73f, 5, Utils.getRezinAppearence());
        TransformGroup rezinTG = buildTG(new Vector3f(0, -10, 0));
        rezinTG.addChild(rezin);
        body1TG.addChild(rezinTG);

        // metal
        Cylinder metal = new Cylinder(2.1f, 2.5f, Utils.getMetalAppearence());
        TransformGroup metalTG = buildTG(new Vector3f(0, -9, 0));
        metalTG.addChild(metal);
        body1TG.addChild(metalTG);


//        // pages
//        var pages = new Box(6, 2, 8, Utils.getPagesAppearence());
//        var pagesTG = buildTG();
//        pagesTG.addChild(pages);
//
//        // cover
//
//        var cover = new Box(6.5f, 0.2f, 8.5f, Utils.getCoverAppearence());
//        var coverTG = buildTG(new Vector3f(0, 2, 0));
//        coverTG.addChild(cover);
//        pagesTG.addChild(coverTG);
//
//        var cover2 = new Box(6.5f, 0.2f, 8.5f, Utils.getCoverAppearence());
//        var cover2TG = buildTG(new Vector3f(0, -2, 0));
//        cover2TG.addChild(cover2);
//        pagesTG.addChild(cover2TG);
//
//        var cover3 = new Box(0.5f, 2.4f, 8.5f, Utils.getCoverAppearence());
//        var cover3TG = buildTG(new Vector3f(6, 0, 0));
//        cover3TG.addChild(cover3);
//        pagesTG.addChild(cover3TG);
//
//        // writings
//
//        var writing1 = new Box(4f, 2f, 1f, Utils.getWritingAppearence());
//        var writing1TG = buildTG(new Vector3f(0, 0.4f, 3));
//        writing1TG.addChild(writing1);
//        pagesTG.addChild(writing1TG);
//
//        var writing2 = new Box(4f, 1f, 4f, Utils.getWritingAppearence());
//        var writing2TG = buildTG(new Vector3f(2.55f, 0, 0));
//        writing2TG.addChild(writing2);
//        pagesTG.addChild(writing2TG);
//
//        // zakladka
//
//        var stripe = new Box(1f, 0.2f, 8.5f, Utils.getStripeAppearence());
//        var stripeTG = buildTG(new Vector3f(3, 0, -3));
//        stripeTG.addChild(stripe);
//        pagesTG.addChild(stripeTG);




        treeTransformGroup.addChild(body1TG);

    }

    // ActionListener interface
    @Override
    public void actionPerformed(ActionEvent e) {
        float delta = 0.03f;

        // rotation of the castle
        treeTransform3D.rotZ(angle);
        treeTransformGroup.setTransform(treeTransform3D);
        angle += delta;

        // change of the camera position up and down within defined limits
        if (eyeHeight > upperEyeLimit){
            descend = true;
        }else if(eyeHeight < lowerEyeLimit){
            descend = false;
        }
        if (descend){
            eyeHeight -= delta;
        }else{
            eyeHeight += delta;
        }

        // change camera distance to the scene
        if (eyeDistance > farthestEyeLimit){
            approaching = true;
        }else if(eyeDistance < nearestEyeLimit){
            approaching = false;
        }
        if (approaching){
            eyeDistance -= delta;
        }else{
            eyeDistance += delta;
        }

        Point3d eye = new Point3d(eyeDistance, eyeDistance, eyeHeight); // spectator's eye
        Point3d center = new Point3d(.0f, .0f ,0.1f); // sight target
        Vector3d up = new Vector3d(.0f, .0f, 1.0f);; // the camera frustum
        viewingTransform.lookAt(eye, center, up);
        viewingTransform.invert();
        viewingTransformGroup.setTransform(viewingTransform);
    }
}
