package org.m4m.domain;

public enum Command {
    HasData,
    NeedData,
    OutputFormatChanged,
    NextPair,
    NeedInputFormat,
    Drained,
    EndOfFile
}
