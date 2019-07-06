package org.m4m.domain;

import java.util.ArrayList;
import java.util.List;
import org.m4m.IProgressListener;

public class CommandHandlerFactory {
    List<entry> handlerCreators = new ArrayList();

    private class entry {
        public IHandlerCreator handlerCreator;
        public Pair<Command, Integer> leftCommand;
        public Pair<Command, Integer> rightCommand;

        private entry(Pair<Command, Integer> pair, Pair<Command, Integer> pair2, IHandlerCreator iHandlerCreator) {
            this.leftCommand = pair;
            this.rightCommand = pair2;
            this.handlerCreator = iHandlerCreator;
        }
    }

    public ICommandHandler create(Pair<Command, Integer> pair, Pair<Command, Integer> pair2, IProgressListener iProgressListener) {
        return findHandlerCreator(pair, pair2).create();
    }

    public void register(Pair<Command, Integer> pair, Pair<Command, Integer> pair2, IHandlerCreator iHandlerCreator) {
        List<entry> list = this.handlerCreators;
        entry entry2 = new entry(pair, pair2, iHandlerCreator);
        list.add(entry2);
    }

    private IHandlerCreator findHandlerCreator(Pair<Command, Integer> pair, Pair<Command, Integer> pair2) {
        for (entry entry2 : this.handlerCreators) {
            if (entry2.leftCommand == null && entry2.rightCommand != null && entry2.rightCommand.equals(pair2)) {
                return entry2.handlerCreator;
            }
            if (entry2.rightCommand == null && entry2.leftCommand != null && entry2.leftCommand.equals(pair)) {
                return entry2.handlerCreator;
            }
            if (entry2.leftCommand != null && entry2.rightCommand != null && entry2.leftCommand.equals(pair) && entry2.rightCommand.equals(pair2)) {
                return entry2.handlerCreator;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Command handler for pair (");
        sb.append(pair);
        sb.append(", ");
        sb.append(pair2);
        sb.append(") not found");
        throw new IllegalArgumentException(sb.toString());
    }
}
