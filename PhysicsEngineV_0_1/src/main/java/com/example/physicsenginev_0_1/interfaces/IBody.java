package com.example.physicsenginev_0_1.interfaces;

import com.example.physicsenginev_0_1.modelos.Body;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public interface IBody {
    public void calcForces(Body body);

    public void draw(Body body, GraphicsContext context);

    public void sim(Body body, float delta);

    public void addForce(Body body, float fx, float fy);

}
