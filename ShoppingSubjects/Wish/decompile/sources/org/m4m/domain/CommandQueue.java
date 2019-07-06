package org.m4m.domain;

import java.util.Iterator;
import java.util.LinkedList;

public class CommandQueue implements Iterable<Pair<Command, Integer>> {
    protected LinkedList<Pair<Command, Integer>> queue = new LinkedList<>();

    public void queue(Command command, Integer num) {
        this.queue.add(new Pair(command, num));
    }

    public Pair<Command, Integer> dequeue() {
        return (Pair) this.queue.poll();
    }

    public Iterator<Pair<Command, Integer>> iterator() {
        return this.queue.iterator();
    }

    public Pair<Command, Integer> first() {
        if (size() == 0) {
            return null;
        }
        return (Pair) this.queue.peek();
    }

    public void clear() {
        this.queue.clear();
    }

    public int size() {
        return this.queue.size();
    }
}
