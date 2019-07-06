package org.m4m.domain;

import java.util.List;

class PairQueueSpecification {
    private final PairCommandSpecification pairCommandSpecification;

    public PairQueueSpecification(List<Pair<Command, Command>> list) {
        this.pairCommandSpecification = new PairCommandSpecification(list);
    }

    public boolean satisfiedBy(CommandQueue commandQueue, CommandQueue commandQueue2) {
        Pair first = commandQueue.first();
        Pair first2 = commandQueue2.first();
        if (first2 == null) {
            return false;
        }
        if (first2.left == Command.NextPair) {
            return true;
        }
        if (first == null) {
            return false;
        }
        if (first.left == Command.NextPair) {
            return true;
        }
        return this.pairCommandSpecification.satisfiedBy(first, first2);
    }
}
