package com.molo.p4progfonc;

/**
 * Classe de Test
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("\n=== LAMBDAS ===");
        LambdaExamples.demo();

        System.out.println("\n=== INTERFACES FONCTIONNELLES ===");
        FunctionExamples.demo();

        System.out.println("\n=== STREAMS ===");
        StreamExamples.demo();

        System.out.println("\n=== OPTIONAL ===");
        OptionalExamples.demo();
    }
}
