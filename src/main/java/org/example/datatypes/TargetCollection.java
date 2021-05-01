package org.example;

import java.time.Instant;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Predicate;

import org.example.interfaces.ITargetCollection;

public class TargetCollection implements ITargetCollection {
    private final LinkedList<Ticket> targetCollection = new LinkedList<>();
    private final Date creationDate;
    private int nextId = 0;

    public TargetCollection() {
        // Collection created from zero
        creationDate = Date.from(Instant.now());
    }

    public int getNextId() {
        return nextId;
    }

    public boolean add(Ticket ticket) {
        nextId++;
        return targetCollection.add(ticket);
    }

    public void remove(int idx) {   
        if (idx > targetCollection.size() || idx < 1)
            System.out.printf("The index must be in range from 1 to the number of " + 
                    "objects in collection, which is %d\n", targetCollection.size());
        else targetCollection.remove(idx - 1);
    }

    public void clear() {
        targetCollection.clear();
    }

    public void filter(Predicate<Ticket> filterFunction) {
        targetCollection.removeIf(filterFunction);
    }

    public void update(int idx, Ticket ticket) {
        if (idx > targetCollection.size() || idx < 1)
            System.out.printf("The index must be in range from 1 to the number of " + 
                    "objects in collection, which is %d\n", targetCollection.size());
        else {
            this.remove(idx - 1);
            targetCollection.add(idx - 1, ticket);
        }
    }

    public Iterator<Ticket> iterator() {
        return targetCollection.iterator();
    }

    public String toString() {
        return String.format("Size of collection: %d\n" + 
                "Creation date: %s",
                targetCollection.size(), creationDate);
    }

    
}
