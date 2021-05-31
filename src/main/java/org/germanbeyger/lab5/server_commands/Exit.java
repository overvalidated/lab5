package org.germanbeyger.lab5.server_commands;

public final class Exit {
    private Exit() {}

    public static void execute(Object... args) {
        System.exit(0);
    }
}
