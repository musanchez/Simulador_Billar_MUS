package com.example.physicsenginev_0_1.modelos;

import javafx.scene.paint.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class Body {
    private float xPos;
    private float yPos;
    private float xVel;
    private float yVel;
    private float mass;
    private Color color;
    private float xNetForce;
    private float yNetForce;
    private Boolean isMovable;

    private float k;

    public Body(float xPos, float yPos, float xVel, float yVel, float mass, Color color, Boolean isMovable, float k) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xVel = xVel;
        this.yVel = yVel;
        this.mass = mass;
        this.color = color;
        this.isMovable = isMovable;
        this.k = k;
    }
}
