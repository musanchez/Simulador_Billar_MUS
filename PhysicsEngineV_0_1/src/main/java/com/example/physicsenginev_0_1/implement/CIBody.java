package com.example.physicsenginev_0_1.implement;

import com.example.physicsenginev_0_1.interfaces.IBody;
import com.example.physicsenginev_0_1.modelos.Body;
import com.example.physicsenginev_0_1.modelos.Circle;
import com.example.physicsenginev_0_1.modelos.Rectangle;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class CIBody implements IBody {
    @Override
    public void calcForces(Body body) {
        if (body != null) {
            float y = -body.getMass() * 0 - body.getK() * body.getYVel();
            float x = -body.getK() * body.getXVel();
            body.setXNetForce(x);
            body.setYNetForce(y);
        }

    }

    @Override
    public void draw(Body body, GraphicsContext graphicsContext) {
        if (body != null) {
            graphicsContext.setFill(body.getColor());
            float xPixel = 50 * (body.getXPos() + 5);
            float yPixel = 50 * (8 - body.getYPos());
            if (body.getClass() == Rectangle.class) {
                Rectangle rectangle = (Rectangle) body;
                float wPixel = rectangle.getWidth() * 50;
                float hPixel = rectangle.getHeight() * 50;
                graphicsContext.fillRect(xPixel, yPixel, wPixel, hPixel);
            }
            if (body.getClass() == Circle.class){
                Circle circle = (Circle) body;
                float rPixel = 50 * circle.getRadius();
                graphicsContext.fillOval(xPixel - rPixel, yPixel - rPixel, 2*rPixel, 2*rPixel);
            }
        }

    }
    @Override
    public void sim(Body body, float delta) {
        if (body != null) {
            float ax = body.getXNetForce()/body.getMass();
            float ay = body.getYNetForce()/body.getMass();

            float nvelx = delta * ax + body.getXVel();
            float nvely = delta * ay + body.getYVel();

            body.setXVel(nvelx);
            body.setYVel(nvely);

            if (body.getIsMovable()) {
                body.setXPos(body.getXVel() * delta + body.getXPos());

                body.setYPos(body.getYVel() * delta + body.getYPos());
            }

        }


    }

    @Override
    public void addForce(Body body, float fx, float fy) {
        float nFx = body.getXNetForce() + fx;
        float nFy = body.getYNetForce() + fy;
        body.setXNetForce(nFx);
        body.setYNetForce(nFy);
    }
}
