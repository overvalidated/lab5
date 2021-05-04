package org.germanbeyger.lab5.datatypes;

import java.util.LinkedList;

import org.germanbeyger.lab5.interfaces.IMetainfo;

import java.time.Instant;
import java.util.Date;

public class MetaInformationTargetCollection implements IMetainfo {
    private LinkedList<String> history = new LinkedList<>();
    private Date creationDate;

    public MetaInformationTargetCollection() {

        creationDate = Date.from(Instant.now());
    }

    public boolean verify() {
        return (creationDate != null && history != null);
    }

    public void addToHistory(String command) {
        history.addFirst(command);
        if (history.size() == 12)
            history.removeLast();
    }

    public String getStringifiedHistory() {
        int i = 0;
        String historyAsString = "";
        for (String element : history) {
            i += 1;
            historyAsString += String.format("%d) %s\n", i, element);
        }
        return historyAsString;
    }

    public String getInfoAboutCollection(TargetCollection targetCollection) {
        return String.format("Creation date: %s\nNumber of elements: %d\nOperated type: Ticket", 
                creationDate, targetCollection.countElements());
    }

}