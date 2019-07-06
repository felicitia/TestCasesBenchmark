package org.m4m.domain;

import java.util.List;

class PairCommandSpecification {
    private final List<Pair<Command, Command>> matchingCommands;

    public PairCommandSpecification(List<Pair<Command, Command>> list) {
        this.matchingCommands = list;
    }

    public boolean satisfiedBy(Pair<Command, Integer> pair, Pair<Command, Integer> pair2) {
        if (pair == null || pair2 == null) {
            return false;
        }
        for (Pair pair3 : this.matchingCommands) {
            if (pair3.left != null && pair3.left == pair.left && pair3.right != null && pair3.right == pair2.left && pair.right == pair2.right) {
                return true;
            }
            if (pair3.left == null && pair3.right == pair2.left && pair.right == pair2.right) {
                return true;
            }
            if (pair3.right == null && pair3.left == pair2.left && pair.right == pair2.right) {
                return true;
            }
        }
        return false;
    }
}
