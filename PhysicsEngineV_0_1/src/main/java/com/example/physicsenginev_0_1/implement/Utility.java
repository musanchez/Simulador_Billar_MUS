package com.example.physicsenginev_0_1.implement;

import com.example.physicsenginev_0_1.interfaces.IUtility;

public class Utility implements IUtility {
    public float cDistance(float x1, float y1, float x2, float y2) {
        return (float) (Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}
