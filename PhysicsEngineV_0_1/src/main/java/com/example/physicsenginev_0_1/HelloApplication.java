package com.example.physicsenginev_0_1;

import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;

import java.io.IOException;

public class HelloApplication extends Application {

    private AnimationTimer timer;
    private Timeline timeline;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Mesa de Billar - Marcos");
        //docu: https://docs.oracle.com/javafx/2/canvas/jfxpub-canvas.html
        Canvas canvas = new Canvas(800, 600);
        World.getInstance().setGraphicsContext( canvas.getGraphicsContext2D() );
        World.getInstance().create();


        Group root = new Group();
        root.getChildren().add(canvas);
        stage.setScene(new Scene(root));
        stage.show();

        timeline = new Timeline(60);
        timeline.setCycleCount(Timeline.INDEFINITE);

        timer = new AnimationTimer() {
            Long last = null;
            Long start = null;
            double smoothedFrameRate = -1.0;
            @Override
            public void handle(long now) {
                if (last == null) {
                    last = now;
                    start = now;
                    return;
                }
                double dt = (now-last)*1e-9;
                double t = (now-start)*1e-9;
                last = now;

                //dibujar fondo
                canvas.getGraphicsContext2D().setFill(Color.DARKBLUE);
                canvas.getGraphicsContext2D().fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                //escribir "frame rate"
                double currentFrameRate = 1.0/dt;
                if (smoothedFrameRate<0)
                    smoothedFrameRate = currentFrameRate;
                else
                    smoothedFrameRate = 0.95*smoothedFrameRate + 0.05*currentFrameRate;
                String frameRate = String.valueOf( (int) (smoothedFrameRate + 0.5) );
                canvas.getGraphicsContext2D().setStroke(Color.WHITE);
                canvas.getGraphicsContext2D().strokeText(frameRate,10,30);
                World.getInstance().run(t, dt);
            }
        };
        timeline.play();
        timer.start();

    }

}