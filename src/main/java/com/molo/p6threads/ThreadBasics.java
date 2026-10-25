package com.molo.p6threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Cette classe permet de comprendre les bases de la programmation concurrente.
 * Les notions à retenir :
 * Concept	Rôle
 * start()	Lance l'exécution dans un nouveau thread.
 * run()	Exécute directement la méthode dans le thread appelant si on l'appelle soi-même.
 * join()	Attend la terminaison d'un thread.
 * sleep()	Suspend temporairement le thread courant.
 * interrupt()	Demande une interruption coopérative.
 * isInterrupted()	Consulte le statut d'interruption sans l'effacer.
 * Thread.State	NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, TERMINATED.
 * ExecutorService	Gère l'exécution de tâches et, avec un pool fixe, réutilise un nombre limité de threads.
 */
public class ThreadBasics {

    public static void main(String[] args) throws InterruptedException {

        // 1. Créer un thread avec Runnable
        Runnable task = () ->
                System.out.println("Exécuté par " +
                        Thread.currentThread().getName());

        Thread thread = new Thread(task, "mon-thread");
        thread.start(); // Démarre un nouveau thread
        thread.join();  // Attend sa fin

        // 2. États d'un thread
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        System.out.println("Avant start : " + t.getState()); // NEW
        t.start();
        t.join();
        System.out.println("Après join : " + t.getState());  // TERMINATED

        // 3. Interruption d'un thread
        Thread worker = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                // sleep() efface le statut d'interruption
                Thread.currentThread().interrupt();
            }
            System.out.println("Worker arrêté proprement");
        });

        worker.start();
        worker.interrupt();
        worker.join();

        // 4. Pool de threads
        try (ExecutorService pool = Executors.newFixedThreadPool(2)) {
            pool.submit(task);
            pool.submit(task);
        } // Attend la fin des tâches soumises
    }
}

