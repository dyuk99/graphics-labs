package main;

import javafx.scene.paint.Color;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.shape.*;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Lab1");

        Group root = new Group();
        Scene scene = new Scene(root, 610, 372);
        scene.setFill(Color.rgb(109, 111, 3, 1));

        Rectangle wall = new Rectangle(47, 26, 517, 300);
        wall.setFill(Color.rgb(107, 0, 1, 1));
        root.getChildren().add(wall);

        Line[] lines = new Line[]{
                new Line(296.0f, 31.0f, 296.0f, 107.0f),
                new Line(296.0f, 235.0f, 296.0f, 321.0f),
                new Line(148.0f, 117.0f, 148.0f, 221.0f),
                new Line(444.0f, 117.0f, 444.0f, 216.0f),
                new Line(52.0f, 107.0f, 559.0f, 107.0f),
                new Line(52.0f, 225.0f, 559.0f, 225.0f)
        };

        for (Line line:lines) {
            line.setStrokeWidth(10.0f);
            line.setStroke(Color.YELLOW);
            root.getChildren().add(line);
        }

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
