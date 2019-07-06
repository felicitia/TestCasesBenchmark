package org.m4m.domain;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class FrameBuffer {
    private final Set<Integer> configuredTrackIndexes = new HashSet();
    private final Queue<Frame> frames = new LinkedList();
    private int numberOfTracks;

    public FrameBuffer(int i) {
        this.numberOfTracks = i;
    }

    public void configure(int i) {
        this.configuredTrackIndexes.add(Integer.valueOf(i));
    }

    public boolean areAllTracksConfigured() {
        return this.numberOfTracks == this.configuredTrackIndexes.size();
    }

    public void push(Frame frame) {
        this.frames.add(frame);
    }

    public boolean canPull() {
        return areAllTracksConfigured() && !this.frames.isEmpty();
    }

    public Frame pull() {
        return (Frame) this.frames.poll();
    }

    public void addTrack() {
        this.numberOfTracks++;
    }
}
