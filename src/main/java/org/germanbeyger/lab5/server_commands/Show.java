package org.germanbeyger.lab5.server_commands;

import java.util.stream.Collectors;

import org.germanbeyger.lab5.datatypes.SendableCommand;
import org.germanbeyger.lab5.datatypes.TargetCollection;
import org.germanbeyger.lab5.datatypes.Ticket;

public final class Show {
    private Show() {}

    public static String execute(SendableCommand command, TargetCollection targetCollection) {
        String result = "";
        for (Ticket ticket : targetCollection.stream()
            .sorted((a, b) -> {return a.getCoordinates().compareTo(b.getCoordinates());}).collect(Collectors.toList()))
            result += ticket.toString() + '\n';
        if (result == "") result = "Nothing to show!";
        return result;
    }
}
