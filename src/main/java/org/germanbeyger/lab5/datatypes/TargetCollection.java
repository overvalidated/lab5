package org.germanbeyger.lab5.datatypes;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.germanbeyger.lab5.interfaces.ITargetCollection;

public class TargetCollection implements ITargetCollection {
    private final LinkedList<Ticket> targetCollection;
    private final MetaInformationTargetCollection metainfo; 
    private Ticket maxTicket = null; // max ticket.
    private int nextId = 0;

    public TargetCollection() {
        // Collection created from zero
        targetCollection = new LinkedList<>();
        metainfo = new MetaInformationTargetCollection();
    }

    // Service functions

    public int getNextId() {
        return nextId;
    }

    public int countElements() {
        return targetCollection.size();
    }

    private int identifyTicket(int idxTicket) throws NoSuchElementException {
        int idxList = 0;
        for (Ticket ticket : targetCollection) {
            if (ticket.getId() == idxTicket) {
                return idxList;
            }
            idxList++;
        }
        throw new NoSuchElementException("No ticket with this index was found. ");
    }

    public Iterator<Ticket> iterator() {
        return targetCollection.iterator();
    }

    // Main functionality

    public void addToHistory(String command) {
        metainfo.addToHistory(command);
    }

    public void add(Ticket ticket) {
        nextId++;
        targetCollection.add(ticket);
    }

    public void addIfMax(Ticket ticket) {
        // Mm, first time? 
        if (maxTicket == null) {
            add(ticket);
            maxTicket = ticket;
            return;
        } 
        
        if (ticket.compareTo(maxTicket) > 0) {
            add(ticket);
            maxTicket = ticket;
        }
    }

    public void clear() {
        targetCollection.clear();
    }

    public void removeIf(Predicate<Ticket> filterFunction) {
        targetCollection.removeIf(filterFunction);
    }

    public LinkedList<Ticket> filter(Predicate<Ticket> predicate) {
        return targetCollection.stream()
                .filter(predicate)
                .collect(Collectors.toCollection(() -> new LinkedList<Ticket>()));
    }

    public LinkedList<Ticket> getSortedCollection() {
        LinkedList<Ticket> sortedCollection = new LinkedList<>(targetCollection);
        sortedCollection.sort((a,b) -> -a.compareTo(b));
        return sortedCollection;
    }

    public void remove(int idxTicket) { 
        try {
            int idxReal = identifyTicket(idxTicket);
            targetCollection.remove(idxReal);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(int idx, Ticket ticket) {
        try {
            int idxReal = identifyTicket(idx);
            targetCollection.set(idxReal, ticket);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        
    }

    // Metadata processing

    public String getStringifiedHistory() {
        return metainfo.getStringifiedHistory();
    }

    public String getInfoAboutCollection() {
        return metainfo.getInfoAboutCollection(this);
    }
}
