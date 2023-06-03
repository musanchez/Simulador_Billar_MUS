package com.example.physicsenginev_0_1;

import com.example.physicsenginev_0_1.implement.CIBody;
import com.example.physicsenginev_0_1.implement.CollisonService;
import com.example.physicsenginev_0_1.interfaces.IBody;
import com.example.physicsenginev_0_1.interfaces.ICollision;
import com.example.physicsenginev_0_1.modelos.Body;
import com.example.physicsenginev_0_1.modelos.Circle;
import com.example.physicsenginev_0_1.modelos.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.sqrt;

public class World {
    //Define la región visible en la ventana, definido por las coordenadas de la
    //esquina izquierda superior (xMin/yMax) y un factor de escala
    private final double xMin = -5.0; //metros
    private final double yMax = 8.0; //metros
    private final double scale =  50.0; //pixel/metro

    public static final double g = 9.81;

    private static World world = null;
    public static World getInstance() {
        if (world == null)
            world = new World();
        return world;
    }


    //Agrega objetos al mundo de simulación
    public void create() {
    }
    private GraphicsContext gc = null;

    public void setGraphicsContext(GraphicsContext gc) {
        this.gc = gc;
    }

    public void drawCircle(double xCenter, double yCenter, double r, Color color) {
        double xPixel = toPixelX(xCenter);
        double yPixel = toPixelY(yCenter);
        double rPixel = scale*r;
        gc.setFill(color);
        gc.fillOval(xPixel-rPixel, yPixel-rPixel, 2*rPixel, 2*rPixel);
    }
    private double toPixelX(double x) {
        return scale*(x-xMin);
    }
    private double toPixelY(double y) {
        return scale*(yMax-y);
    }

    Circle circle = new Circle(6,3.2f,0,0,1,Color.GREEN,true,0.3f,0.3f);
    List<Circle> poolBalls = trianglePool(5, circle);
    //simulación de un periodo de tiempo, avanza el tiempo deltaT

    IBody iBody = new CIBody();
    ICollision iCollision = new CollisonService();


    public List<Body> callPoolObjects() {
        List<Circle> triangle = trianglePool(5, circle);

        Rectangle rectangle = new Rectangle(-3,6.5f,0,0,1,Color.GREEN,false,0,9,12);

        Rectangle borderUp = new Rectangle(-3.5F,7,0,0,1,Color.BROWN,false,0,0.5f,13);
        Rectangle borderDown = new Rectangle(-3.5F,-2.5f,0,0,1,Color.BROWN,false,0,0.5f,13);

        Rectangle borderLeft = new Rectangle(-3.5F,7,0,0,1,Color.BROWN,false,0,10,0.5f);

        Rectangle borderRight = new Rectangle(9,7,0,0,1,Color.BROWN,false,0,10,0.5f);

        Circle whiteBall = new Circle(0,2,19,0,1,Color.WHITESMOKE,true,0.1f,0.3f);

        Circle hole1 = new Circle(-2.5f,6,0,0,1,Color.BLACK,false,0,0.5f);
        Circle hole2 = new Circle(3,6,0,0,1,Color.BLACK,false,0,0.5f);
        Circle hole3 = new Circle(8.5f,6,0,0,1,Color.BLACK,false,0,0.5f);
        Circle hole4 = new Circle(-2.5f,-2,0,0,1,Color.BLACK,false,0,0.5f);
        Circle hole5 = new Circle(3,-2,0,0,1,Color.BLACK,false,0,0.5f);
        Circle hole6 = new Circle(8.5f,-2,0,0,1,Color.BLACK,false,0,0.5f);

        List<Body> list = new ArrayList<Body>(List.of(rectangle, borderDown, borderUp, borderLeft, borderRight));
        list.add(hole1);
        list.add(hole2);
        list.add(hole3);
        list.add(hole4);
        list.add(hole5);
        list.add(hole6);

        for (int i = 0; i < triangle.size(); i++) {
            list.add(triangle.get(i));
        }

        list.add(whiteBall);

        return list;

    }
    List<Body> poolObjects = callPoolObjects();
    public void run(double t, double deltaT) {
        for (int i = 0; i < poolObjects.size(); i++) {
            iBody.calcForces(poolObjects.get(i));
        }
        iCollision.collideObjects(poolObjects);
        for (int i = 0; i < poolObjects.size(); i++) {
            iBody.sim(poolObjects.get(i),(float) (deltaT) );

        }
        for (int i = 0; i < poolObjects.size(); i++) {
            iBody.draw(poolObjects.get(i), gc);
        }

        if (deltaT > 1.0/30) {
            System.out.println("Lento!");
        }

    }
    public List<Circle> alignedCircles(int cantBalls, Circle startBall) {
        List<Color> colors = new ArrayList<Color>(List.of(Color.PURPLE, Color.DARKGRAY, Color.RED, Color.SANDYBROWN,
                Color.DARKGOLDENROD, Color.CHOCOLATE, Color.DARKBLUE, Color.DARKORANGE));
        Random random = new Random();
        int index;

        List <Circle> circles = new ArrayList<Circle>();
        for (int i = 0; i < cantBalls; i++) {
            index = random.nextInt(colors.size());
            circles.add(new Circle(startBall.getXPos(), startBall.getYPos() - 2 * i * startBall.getRadius()
            , startBall.getXVel(), startBall.getYVel(),startBall.getMass(), colors.get(index),true,
                    startBall.getRadius(),startBall.getRadius()));

        }
        return circles;
    }
    public List<Circle> startBalls(Circle startBall, int cantBalls) {
        List<Circle> circles = new ArrayList<>();
        for (int i = 0; i < cantBalls; i++) {
            circles.add(new Circle((float) (startBall.getXPos() - startBall.getRadius() * i * sqrt(3)),
                    startBall.getYPos() - i * startBall.getRadius()
                    , startBall.getXVel(), startBall.getYVel(),startBall.getMass(), startBall.getColor(),true,
                    startBall.getRadius(),startBall.getRadius()));
        }
        return circles;
    }
    public List<Circle> trianglePool(int cantBall, Circle startBall) {
        List<Circle> circlesPool = new ArrayList<Circle>();
        List<Circle> startBalls = startBalls(startBall, cantBall);
        for (int i = 0; i < cantBall; i++) {
            List<Circle> aligned = alignedCircles(5 - i, startBalls.get(i));
            for (int j = 0; j < 5 - i; j++) {
                circlesPool.add(aligned.get(j));
            }
        }
        return circlesPool;
    }
}
