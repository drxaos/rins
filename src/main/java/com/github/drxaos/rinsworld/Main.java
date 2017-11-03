package com.github.drxaos.rinsworld;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TestLab testLab = new TestLab();
        Animator animator = new Animator(testLab);
        animator.runLoop();
    }
}
