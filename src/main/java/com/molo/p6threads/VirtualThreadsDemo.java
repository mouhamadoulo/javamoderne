package com.molo.p6threads;

import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * Les Virtual Threads sont disponibles depuis Java 21.
 * Ils sont particulièrement intéressants pour les applications qui effectuent beaucoup d'opérations bloquantes, par exemple des appels HTTP, des requêtes SQL ou des accès réseau.
 */
public class VirtualThreadsDemo {

    public static void main(String[] args) throws InterruptedException {

        // 1. Créer un virtual thread
        Thread vt = Thread.ofVirtual()
                .name("virtual-worker")
                .start(() -> System.out.println(
                        Thread.currentThread()));

        vt.join();

        // 2. Méthode raccourcie
        Thread.startVirtualThread(() ->
                System.out.println("Virtual thread démarré"));

        // 3. Un virtual thread par tâche
        try (var executor =
                     Executors.newVirtualThreadPerTaskExecutor()) {

            var tasks = IntStream.range(0, 1_000)
                    .mapToObj(i -> executor.submit(() -> {
                        // Simule une opération bloquante
                        Thread.sleep(10);
                        return i;
                    }))
                    .toList();

            // get() attend le résultat de chaque tâche
            long total = 0;
            for (var future : tasks) {
                total += future.get();
            }

            System.out.println("Total : " + total);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 4. Comparer le type de thread
        System.out.println(
                "Thread courant virtuel ? " +
                        Thread.currentThread().isVirtual());
    }
}
