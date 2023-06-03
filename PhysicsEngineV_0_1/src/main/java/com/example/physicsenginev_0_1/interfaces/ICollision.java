package com.example.physicsenginev_0_1.interfaces;

import com.example.physicsenginev_0_1.modelos.Body;
import com.example.physicsenginev_0_1.modelos.Circle;
import com.example.physicsenginev_0_1.modelos.Rectangle;

import java.util.List;

public interface ICollision {
    public Boolean checkCollision(Circle c1, Circle c2, float kCol);

    public void collideObjects(List<Body> list);

    public void checkCollision(Circle circle, Rectangle rectangle, float kCol);
}

