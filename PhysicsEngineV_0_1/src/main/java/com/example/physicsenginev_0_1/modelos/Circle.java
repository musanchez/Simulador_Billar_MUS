package com.example.physicsenginev_0_1.modelos;


import javafx.scene.paint.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Circle extends Body {
    private float radius;

    public Circle(float xPos, float yPos, float xVel, float yVel, float mass, Color color, float xNetForce, float yNetForce, Boolean isMovable, float radius, float k) {
        super(xPos, yPos, xVel, yVel, mass, color, xNetForce, yNetForce, isMovable, k);
        this.radius = radius;
    }

    public Circle(float xPos, float yPos, float xVel, float yVel, float mass, Color color, Boolean isMovable, float k, float radius) {
        super(xPos, yPos, xVel, yVel, mass, color, isMovable, k);
        this.radius = radius;
    }

}
