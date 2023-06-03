package com.example.physicsenginev_0_1.implement;

import com.example.physicsenginev_0_1.interfaces.IBody;
import com.example.physicsenginev_0_1.interfaces.ICollision;
import com.example.physicsenginev_0_1.interfaces.IUtility;
import com.example.physicsenginev_0_1.modelos.Body;
import com.example.physicsenginev_0_1.modelos.Circle;
import com.example.physicsenginev_0_1.modelos.Rectangle;
import javafx.scene.paint.Color;

import java.util.List;

import static java.lang.Math.sqrt;


public class CollisonService implements ICollision {
    @Override
    public Boolean checkCollision(Circle c1, Circle c2, float kCol) {
        float drx = (c2.getXPos() - c1.getXPos());
        float dry = (float) (c2.getYPos() - c1.getYPos());

        float dr = (float) Math.sqrt((Math.pow(drx, 2) + Math.pow(dry, 2)));

        float d = c1.getRadius() + c2.getRadius() - dr;

        if (d > 0) {

            float nXForce = -(1 / dr) * drx * kCol * d;
            float nYForce = -(1 / dr) * dry * kCol * d;
            //K =2000
            IBody iBody = new CIBody();
            iBody.addForce(c1, nXForce, nYForce);
            iBody.addForce(c2, -nXForce, -nYForce);

        }
        return d > 0;
    }

    @Override
    public void collideObjects(List<Body> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++)
                if (list.get(i) instanceof Circle & list.get(j) instanceof Circle) {
                    Circle circle1 = (Circle) list.get(i);
                    Circle circle2 = (Circle) list.get(j);
                    if (checkCollision(circle1, circle2, 2000)) {
                        if (!circle1.getIsMovable()) {
                            list.set(j, null);
                        } else if (!circle2.getIsMovable()) {
                            list.set(i, null);
                        }
                    }
                } else {
                    if (list.get(i) instanceof Circle & list.get(j) instanceof Rectangle) {
                        checkCollision((Circle) list.get(i), (Rectangle) list.get(j), 2000);
                    }
                    if (list.get(j) instanceof Circle & list.get(i) instanceof Rectangle)   {
                        checkCollision((Circle) list.get(j), (Rectangle) list.get(i), 2000);
                    }
                    /*
                    Codigo para la implementación de la colisión
                     */
                }

        }
    }

    @Override
    public void checkCollision(Circle circle, Rectangle rectangle, float kCol) {
        IUtility utility = new Utility();
        float dist;
        float a, b, p, q, w, h, r;
        w = rectangle.getWidth();
        h = rectangle.getHeight() ;
        p = circle.getXPos();
        q = circle.getYPos();
        a = rectangle.getXPos();
        b = rectangle.getYPos();
        r = circle.getRadius();

        float xCenter = rectangle.getXPos() + rectangle.getWidth()/2;
        float yCenter = rectangle.getYPos() - rectangle.getHeight()/2;

        float cPointX;
        float cPointY;

        if (p <= a) {
            cPointX = a;
            if (q >= b) {
                cPointY = b;
            } else if (q > b - h) {
                cPointY = q;
            } else {
               cPointY = b - h;
            }
        } else if (p < a + w) {
            cPointX = p;
            if (q >= b) {
                cPointY = b;
            } else if (q > b - h) {
                return;
            } else {
                cPointY = b - h;
            }
        } else {
            cPointX = a + w;
            if (q >= b) {
                cPointY = b;
            } else if (q > b - h) {
              cPointY = q;
            } else {
              cPointY = b - h;
            }
        }

        dist = utility.cDistance(p, q, cPointX, cPointY);
        float drx = p - cPointX;
        float dry = q - cPointY;

        if (dist < Math.pow(circle.getRadius(), 2)) {
            float nXForce = (float) ((1 / Math.sqrt(dist)) * drx * kCol * (r - Math.sqrt(dist)));
            float nYForce = (float) ((1 / Math.sqrt(dist)) * dry * kCol * (r - Math.sqrt(dist)));
            //K =2000
            IBody iBody = new CIBody();

            iBody.addForce(circle, nXForce, nYForce);
            iBody.addForce(rectangle, -nXForce, -nYForce);
        }

    }

}
