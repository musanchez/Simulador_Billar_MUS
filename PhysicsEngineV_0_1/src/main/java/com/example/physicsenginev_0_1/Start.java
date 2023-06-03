package com.example.physicsenginev_0_1;

import javafx.application.Application;

public class Start {
    public static void main(String[] args) {
        System.setProperty("quantum.multithreaded","false");
        Application.launch(HelloApplication.class,args);
    }
}