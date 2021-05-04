package org.germanbeyger.lab5.commands;

public final class Exit {
    private Exit() {}

    public static void execute(Object... args) {
        System.exit(0);
    }
}
