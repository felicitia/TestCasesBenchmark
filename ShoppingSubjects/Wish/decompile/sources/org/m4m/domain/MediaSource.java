package org.m4m.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import org.m4m.VideoFormat;

public class MediaSource implements IMediaSource {
    private CommandQueue commandQueue = new CommandQueue();
    private int lastTrackId = 0;
    private final IMediaExtractor mediaExtractor;
    private long seekPosition = 0;
    private boolean seekedOutsideNeededSegment = false;
    private Segments segments = new Segments(new ArrayList());
    private Set<Integer> selectedTracks = new HashSet();
    private PluginState state = PluginState.Drained;

    public boolean canConnectFirst(IInputRaw iInputRaw) {
        return true;
    }

    public void fillCommandQueues() {
    }

    public void incrementConnectedPluginsCount() {
    }

    public MediaSource(IMediaExtractor iMediaExtractor) {
        this.mediaExtractor = iMediaExtractor;
    }

    public void selectTrack(int i) {
        if (i > this.mediaExtractor.getTrackCount() - 1) {
            throw new RuntimeException("Attempt to select non-existing track.");
        }
        this.mediaExtractor.selectTrack(i);
        this.selectedTracks.add(Integer.valueOf(i));
    }

    public CommandQueue getOutputCommandQueue() {
        return this.commandQueue;
    }

    public void close() throws IOException {
        this.mediaExtractor.release();
    }

    public void pull(Frame frame) {
        if (this.state != PluginState.Normal) {
            throw new IllegalStateException("Attempt to pull frame from not started media source or after EOF.");
        }
        readSampleData(frame);
        if (!frame.equals((Object) Frame.EOF())) {
            this.mediaExtractor.advance();
            checkIfHasData();
        }
    }

    private void readSampleData(Frame frame) {
        frame.setSampleTime(getSampleTime());
        frame.setTrackId(getTrackId());
        frame.setFlags(this.mediaExtractor.getSampleFlags());
        frame.setLength(this.mediaExtractor.readSampleData(frame.getByteBuffer()));
        frame.getByteBuffer().position(0);
        frame.toSkipFrame(getSkipDecision());
    }

    private boolean getSkipDecision() {
        return this.mediaExtractor.getSampleTime() < getSeekPosition();
    }

    private void checkIfHasData() {
        if (this.mediaExtractor.getSampleTrackIndex() == -1) {
            drain();
        } else if (!reachedSeekPosition(this.mediaExtractor.getSampleTime())) {
            hasData();
        } else if (this.segments.isInsideSegment(this.mediaExtractor.getSampleTime())) {
            hasData();
        } else {
            Pair segmentAfter = this.segments.getSegmentAfter(this.mediaExtractor.getSampleTime());
            if (segmentAfter == null) {
                drain();
            } else {
                seek(((Long) segmentAfter.left).longValue());
            }
        }
    }

    private boolean reachedSeekPosition(long j) {
        return j >= getSeekPosition();
    }

    private void hasData() {
        this.commandQueue.queue(Command.HasData, Integer.valueOf(this.mediaExtractor.getSampleTrackIndex()));
        this.lastTrackId = this.mediaExtractor.getSampleTrackIndex();
    }

    private int getTrackId() {
        int sampleTrackIndex = this.mediaExtractor.getSampleTrackIndex();
        return sampleTrackIndex == -1 ? this.lastTrackId : sampleTrackIndex;
    }

    private long getSampleTime() {
        long sampleTime = this.mediaExtractor.getSampleTime();
        if (!reachedSeekPosition(sampleTime)) {
            return sampleTime;
        }
        this.segments.saveSampleTime(sampleTime);
        return this.segments.shift(sampleTime);
    }

    private void drain() {
        this.state = PluginState.Draining;
        this.commandQueue.clear();
        this.commandQueue.queue(Command.EndOfFile, Integer.valueOf(this.lastTrackId));
    }

    public Iterable<MediaFormat> getMediaFormats() {
        LinkedList linkedList = new LinkedList();
        for (int i = 0; i < this.mediaExtractor.getTrackCount(); i++) {
            linkedList.add(this.mediaExtractor.getTrackFormat(i));
        }
        return linkedList;
    }

    public MediaFormat getMediaFormatByType(MediaFormatType mediaFormatType) {
        for (MediaFormat mediaFormat : getMediaFormats()) {
            if (mediaFormat.getMimeType().startsWith(mediaFormatType.toString())) {
                return mediaFormat;
            }
        }
        return null;
    }

    public void start() {
        this.state = PluginState.Normal;
        if (this.segments.isEmpty()) {
            this.segments.add(new Pair(Long.valueOf(0), Long.valueOf(getDurationInMicroSec())));
        } else {
            removeOutOfBoundSegments();
        }
        seek(((Long) this.segments.first().left).longValue());
    }

    private void removeOutOfBoundSegments() {
        this.segments.removeOutOfBoundSegments(getDurationInMicroSec());
    }

    public int getTrackIdByMediaType(MediaFormatType mediaFormatType) {
        for (int i = 0; i < this.mediaExtractor.getTrackCount(); i++) {
            if (this.mediaExtractor.getTrackFormat(i) != null && this.mediaExtractor.getTrackFormat(i).getMimeType() != null && this.mediaExtractor.getTrackFormat(i).getMimeType().startsWith(mediaFormatType.toString())) {
                return i;
            }
        }
        return -1;
    }

    public long getDurationInMicroSec() {
        long maxSelectedTracksDuration = getMaxSelectedTracksDuration();
        return maxSelectedTracksDuration == 0 ? getMaxAllTracksDuration() : maxSelectedTracksDuration;
    }

    private long getMaxSelectedTracksDuration() {
        long j = 0;
        for (Integer intValue : this.selectedTracks) {
            int intValue2 = intValue.intValue();
            if (this.mediaExtractor.getTrackFormat(intValue2) != null && this.mediaExtractor.getTrackFormat(intValue2).getDuration() > j) {
                j = this.mediaExtractor.getTrackFormat(intValue2).getDuration();
            }
        }
        return j;
    }

    private long getMaxAllTracksDuration() {
        long j = 0;
        int i = 0;
        for (MediaFormat mediaFormat : getMediaFormats()) {
            if (this.mediaExtractor.getTrackFormat(i).getDuration() > j) {
                j = this.mediaExtractor.getTrackFormat(i).getDuration();
            }
            i++;
        }
        return j;
    }

    public Set<Integer> getSelectedTracks() {
        return this.selectedTracks;
    }

    public void seek(long j) {
        this.mediaExtractor.seekTo(j, 0);
        this.commandQueue.clear();
        if (hasVideoTrack()) {
            while (!isVideoTrack()) {
                this.mediaExtractor.advance();
            }
        }
        setSeekPosition(j);
        checkIfHasData();
    }

    private boolean hasVideoTrack() {
        for (Integer intValue : this.selectedTracks) {
            if (isVideoTrack(intValue.intValue())) {
                return true;
            }
        }
        return false;
    }

    private boolean isVideoTrack() {
        return isVideoTrack(getTrackId());
    }

    private boolean isVideoTrack(int i) {
        return this.mediaExtractor.getTrackFormat(i).getMimeType().startsWith("video");
    }

    public void stop() {
        drain();
    }

    public int getRotation() {
        return this.mediaExtractor.getRotation();
    }

    public long getSegmentsDurationInMicroSec() {
        if (this.segments.isEmpty()) {
            return getDurationInMicroSec();
        }
        long j = 0;
        for (Pair pair : this.segments.asCollection()) {
            j += ((Long) pair.right).longValue() - ((Long) pair.left).longValue();
        }
        return j;
    }

    public Resolution getOutputResolution() {
        VideoFormat videoFormat = (VideoFormat) getMediaFormatByType(MediaFormatType.VIDEO);
        if (videoFormat != null) {
            return videoFormat.getVideoFrameSize();
        }
        throw new UnsupportedOperationException("Failed to get output resolution.");
    }

    private void setSeekPosition(long j) {
        this.seekPosition = j;
    }

    private long getSeekPosition() {
        return this.seekPosition;
    }
}
