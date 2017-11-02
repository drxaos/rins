package com.github.drxaos.rinsworld;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Locality locality = new Locality();
        locality.add(new Wall(Coord.xy(0, 0)));

        Animator animator = new Animator(locality);


        animator.runLoop();
    }
}
