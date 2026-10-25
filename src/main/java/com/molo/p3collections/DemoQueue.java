package com.molo.p3collections;

import java.util.*;

/**
 * Files et piles
 * Queue sert notamment à traiter des éléments dans un ordre donné.
 * Deque permet d'ajouter et de retirer aux deux extrémités.
 */
public class DemoQueue {
    public static void main(String[] args) {
        // File FIFO : premier entré, premier sorti
        Queue<String> file = new ArrayDeque<>();
        file.offer("Client 1");
        file.offer("Client 2");

        System.out.println(file.peek()); // Client 1
        System.out.println(file.poll()); // Client 1
        System.out.println(file.poll()); // Client 2

        // Pile LIFO : dernier entré, premier sorti
        Deque<Integer> pile = new ArrayDeque<>();
        pile.push(10);
        pile.push(20);

        System.out.println(pile.pop()); // 20

        // File de priorité : plus petit élément d'abord
        Queue<Integer> priorites = new PriorityQueue<>();
        priorites.offer(30);
        priorites.offer(10);
        priorites.offer(20);

        System.out.println(priorites.poll()); // 10
    }
}

