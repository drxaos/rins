package com.github.drxaos.rinsworld;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Locality locality = new Locality();

        Animator animator = new Animator(locality);


        animator.runLoop();
    }
}
