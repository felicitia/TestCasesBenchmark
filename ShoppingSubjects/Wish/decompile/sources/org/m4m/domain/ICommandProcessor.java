package org.m4m.domain;

public interface ICommandProcessor {
    void add(OutputInputPair outputInputPair);

    void stop();
}
