package org.germanbeyger.lab5.datatypes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.germanbeyger.lab5.interfaces.ITargetCollection;

public class TargetCollection implements ITargetCollection {
    private final LinkedList<Ticket> targetCollection;
    private final MetaInformationTargetCollection metainfo; 
    private Ticket maxTicket = null; // max ticket.
    private int nextId = 1;

    public TargetCollection() {
        // Collection created from zero
        targetCollection = new LinkedList<>();
        metainfo = new MetaInformationTargetCollection();
    }

    public boolean verify() {
        ArrayList<Integer> ids = new ArrayList<Integer>(targetCollection.stream()
                .map(input -> input.getId())
                .collect(Collectors.toList()));
        ids.sort(Integer::compareTo);
        if (nextId < 1) {
            return false;
        }
        try {
            int prev = ids.get(0);
            for (int i = 1; i < ids.size()-2; i++) {
                if (ids.get(i).equals(prev)) {
                    return false;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            return true;    
        }
        return targetCollection.stream().allMatch(input -> input.verify()) && metainfo.verify();
    }

    // Service functions

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
        ticket.setId(nextId);
        ticket.setCurrentDate();
        nextId++;
        if (maxTicket == null) {
            maxTicket = ticket;
        } else if (ticket.compareTo(maxTicket) > 0) {
            maxTicket = ticket;
        }
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
        nextId = 1;
        maxTicket = null;
    }

    public Stream<Ticket> stream() {
        return targetCollection.stream();
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

    public void remove(int idxTicket) throws NoSuchElementException{ 
        int idxReal = identifyTicket(idxTicket);
        targetCollection.remove(idxReal);
    }

    public void update(int idx, Ticket ticket) throws NoSuchElementException {
        if (idx < 1) return;
        int idxReal = identifyTicket(idx);
        ticket.setId(idx);
        ticket.setCurrentDate();
        targetCollection.set(idxReal, ticket);
    }

    // Metadata processing

    public String getStringifiedHistory() {
        return metainfo.getStringifiedHistory();
    }

    public String getInfoAboutCollection() {
        return metainfo.getInfoAboutCollection(this);
    }
}
