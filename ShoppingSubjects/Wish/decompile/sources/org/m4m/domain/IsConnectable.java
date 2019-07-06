package org.m4m.domain;

import java.util.Collection;

public interface IsConnectable {
    boolean isConnectable(Collection<IOutputRaw> collection, IInputRaw iInputRaw);

    boolean isConnectable(IOutputRaw iOutputRaw, Collection<IInputRaw> collection);
}
