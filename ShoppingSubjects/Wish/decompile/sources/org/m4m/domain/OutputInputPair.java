package org.m4m.domain;

public class OutputInputPair {
    public CommandHandlerFactory commandHandlerFactory;
    public IInputRaw input;
    public IOutputRaw output;

    public OutputInputPair(IOutputRaw iOutputRaw, IInputRaw iInputRaw, CommandHandlerFactory commandHandlerFactory2) {
        this.output = iOutputRaw;
        this.input = iInputRaw;
        this.commandHandlerFactory = commandHandlerFactory2;
    }
}
