package org.m4m.domain;

import java.util.ArrayList;

public final class MatchingCommands extends ArrayList<Pair<Command, Command>> {
    public MatchingCommands() {
        add(new Pair(Command.HasData, Command.NeedData));
        add(new Pair(Command.HasData, Command.NeedInputFormat));
        add(new Pair(Command.OutputFormatChanged, Command.NeedInputFormat));
        add(new Pair(Command.OutputFormatChanged, Command.NeedData));
        add(new Pair(Command.EndOfFile, Command.NeedInputFormat));
        add(new Pair(Command.EndOfFile, Command.NeedData));
    }
}
