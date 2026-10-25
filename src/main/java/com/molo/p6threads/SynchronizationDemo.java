package com.molo.p6threads;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Le problème fondamental : deux threads modifient simultanément une même variable.
 */
public class SynchronizationDemo {

    // 1. Race condition : version incorrecte
    static class UnsafeCounter {
        int count = 0;

        void increment() {
            count++; // Lecture + addition + écriture
        }
    }

    // 2. Synchronisation avec synchronized
    static class SafeCounter {
        private int count = 0;

        synchronized void increment() {
            count++;
        }

        synchronized int getCount() {
            return count;
        }
    }

    // 3. Compteur atomique
    static class AtomicCounter {
        private final AtomicInteger count = new AtomicInteger();

        void increment() {
            count.incrementAndGet();
        }

        int getCount() {
            return count.get();
        }
    }

    // 4. Publication d'un état avec volatile
    static class Flag {
        volatile boolean running = true;
    }

    public static void main(String[] args) throws InterruptedException {

        SafeCounter counter = new SafeCounter();

        Runnable task = () -> {
            for (int i = 0; i < 100_000; i++) {
                counter.increment();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(counter.getCount()); // 200000
    }
}

