package com.example.physicsenginev_0_1.modelos;


import javafx.scene.paint.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rectangle extends Body {
    private float height;
    private float width;

    public Rectangle(float xPos, float yPos, float xVel, float yVel, float mass, Color color, float xNetForce, float yNetForce, Boolean isMovable, float height, float width, float k) {
        super(xPos, yPos, xVel, yVel, mass, color, xNetForce, yNetForce, isMovable, k);
        this.height = height;
        this.width = width;
    }

    public Rectangle(float xPos, float yPos, float xVel, float yVel, float mass, Color color, Boolean isMovable, float k, float height, float width) {
        super(xPos, yPos, xVel, yVel, mass, color, isMovable, k);
        this.height = height;
        this.width = width;
    }
}
