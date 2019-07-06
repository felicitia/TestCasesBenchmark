package org.m4m.domain;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import org.m4m.AudioFormat;
import org.m4m.MediaFile;
import org.m4m.VideoFormat;

public class MultipleMediaSource implements IMediaSource {
    private CommandQueue commandQueue = new CommandQueue();
    private int connectedPluginsCount = 0;
    private MediaFile currentMediaFile = null;
    private Hashtable<Integer, Long> currentSampleTimes = new Hashtable<>();
    private boolean isLastFile = true;
    private Iterator<MediaFile> mediaFileIterator = null;
    private LinkedList<MediaFile> mediaFiles = new LinkedList<>();
    private int nextFileRequest = 0;
    private Hashtable<Integer, Long> sampleTimeOffsets = new Hashtable<>();
    private Dictionary<Integer, Integer> trackIdMap = new Hashtable();

    public boolean canConnectFirst(IInputRaw iInputRaw) {
        return true;
    }

    public void fillCommandQueues() {
    }

    public CommandQueue getOutputCommandQueue() {
        return this.commandQueue;
    }

    public void close() throws IOException {
        Iterator it = this.mediaFiles.iterator();
        while (it.hasNext()) {
            ((MediaFile) it.next()).getMediaSource().close();
        }
    }

    public void pull(Frame frame) {
        if (this.currentMediaFile == this.mediaFiles.getLast()) {
            this.isLastFile = true;
        }
        pullFrameFromMediaSource(frame);
        hasData();
        this.currentSampleTimes.put(Integer.valueOf(frame.getTrackId()), Long.valueOf(frame.getSampleTime()));
        if (isLastFrame() && !isLastFile()) {
            switchToNextFile();
        }
    }

    public void nextFile() {
        this.nextFileRequest++;
        if (this.nextFileRequest == this.connectedPluginsCount) {
            hasData();
            this.nextFileRequest = 0;
        }
    }

    public MediaFormat getMediaFormatByType(MediaFormatType mediaFormatType) {
        for (MediaFormat mediaFormat : this.currentMediaFile.getMediaSource().getMediaFormats()) {
            if (mediaFormat.getMimeType().startsWith(mediaFormatType.toString())) {
                return mediaFormat;
            }
        }
        return null;
    }

    private void pullFrameFromMediaSource(Frame frame) {
        if (this.currentMediaFile.getMediaSource().getOutputCommandQueue().dequeue().left == Command.HasData) {
            this.currentMediaFile.getMediaSource().pull(frame);
            frame.trackId = mapTrackId(frame.trackId);
            frame.setSampleTime(safeGet((Long) this.sampleTimeOffsets.get(Integer.valueOf(frame.getTrackId()))) + frame.getSampleTime());
        }
    }

    private int mapTrackId(int i) {
        return this.trackIdMap.get(Integer.valueOf(i)) != null ? ((Integer) this.trackIdMap.get(Integer.valueOf(i))).intValue() : i;
    }

    private long safeGet(Long l) {
        if (l == null) {
            return 0;
        }
        return l.longValue();
    }

    private void switchToNextFile() {
        long maxCurrentTimeOffset = getMaxCurrentTimeOffset();
        for (Integer intValue : this.currentSampleTimes.keySet()) {
            this.sampleTimeOffsets.put(Integer.valueOf(intValue.intValue()), Long.valueOf(maxCurrentTimeOffset + 1));
        }
        this.currentMediaFile = (MediaFile) this.mediaFileIterator.next();
        this.currentMediaFile.start();
    }

    private boolean isLastFrame() {
        CommandQueue outputCommandQueue = this.currentMediaFile.getMediaSource().getOutputCommandQueue();
        Pair first = outputCommandQueue.first();
        boolean z = false;
        if (first == null) {
            return false;
        }
        if (outputCommandQueue.size() == 1 && first.left == Command.EndOfFile) {
            z = true;
        }
        return z;
    }

    public boolean isLastFile() {
        return this.isLastFile;
    }

    public void incrementConnectedPluginsCount() {
        this.connectedPluginsCount++;
    }

    public void start() {
        this.currentMediaFile.start();
        hasData();
    }

    public void add(MediaFile mediaFile) throws RuntimeException {
        validate(mediaFile);
        this.mediaFiles.add(mediaFile);
        this.mediaFileIterator = this.mediaFiles.iterator();
        this.currentMediaFile = (MediaFile) this.mediaFileIterator.next();
        boolean z = true;
        if (this.mediaFiles.size() != 1) {
            z = false;
        }
        this.isLastFile = z;
    }

    private void validate(MediaFile mediaFile) throws RuntimeException {
        if (this.mediaFiles.size() != 0) {
            AudioFormat audioFormat = (AudioFormat) mediaFile.getMediaSource().getMediaFormatByType(MediaFormatType.AUDIO);
            if (((AudioFormat) ((MediaFile) this.mediaFiles.getFirst()).getMediaSource().getMediaFormatByType(MediaFormatType.AUDIO)) != null && audioFormat == null) {
                throw new RuntimeException("The stream you are trying to add has no audio track, but the first added stream has audio track. Please select a stream with audio track.");
            }
        }
    }

    public long getMaxCurrentTimeOffset() {
        long j = 0;
        for (Long longValue : this.currentSampleTimes.values()) {
            long longValue2 = longValue.longValue();
            if (longValue2 > j) {
                j = longValue2;
            }
        }
        return j;
    }

    private void hasData() {
        Pair first = this.currentMediaFile.getMediaSource().getOutputCommandQueue().first();
        if (first != null) {
            first.right = Integer.valueOf(mapTrackId(((Integer) first.right).intValue()));
            if (first.left != Command.EndOfFile) {
                this.commandQueue.queue((Command) first.left, (Integer) first.right);
            } else if (!this.isLastFile) {
                queueCommand(Command.OutputFormatChanged);
            } else {
                queueCommand(Command.EndOfFile);
            }
        }
    }

    private void queueCommand(Command command) {
        for (Integer intValue : this.currentMediaFile.getMediaSource().getSelectedTracks()) {
            this.commandQueue.queue(command, Integer.valueOf(mapTrackId(intValue.intValue())));
        }
    }

    public int getTrackIdByMediaType(MediaFormatType mediaFormatType) {
        return this.currentMediaFile.getMediaSource().getTrackIdByMediaType(mediaFormatType);
    }

    public void selectTrack(int i) {
        Iterator it = this.mediaFiles.iterator();
        while (it.hasNext()) {
            ((MediaFile) it.next()).getMediaSource().selectTrack(i);
        }
    }

    public void setTrackMap(int i, int i2) {
        this.trackIdMap.put(Integer.valueOf(i), Integer.valueOf(i2));
    }

    public void stop() {
        this.commandQueue.clear();
        queueCommand(Command.EndOfFile);
    }

    public long getSegmentsDurationInMicroSec() {
        Iterator it = this.mediaFiles.iterator();
        long j = 0;
        while (it.hasNext()) {
            j += ((MediaFile) it.next()).getSegmentsDurationInMicroSec();
        }
        return j;
    }

    public Resolution getOutputResolution() {
        VideoFormat videoFormat = (VideoFormat) getMediaFormatByType(MediaFormatType.VIDEO);
        return videoFormat == null ? new Resolution(0, 0) : videoFormat.getVideoFrameSize();
    }

    public boolean hasTrack(MediaFormatType mediaFormatType) {
        return getTrackIdByMediaType(mediaFormatType) != -1;
    }

    public void verify() {
        Iterator it = this.mediaFiles.iterator();
        while (it.hasNext()) {
            MediaFile mediaFile = (MediaFile) it.next();
            boolean z = false;
            boolean z2 = mediaFile.getMediaSource().getTrackIdByMediaType(MediaFormatType.VIDEO) != -1;
            int trackIdByMediaType = mediaFile.getMediaSource().getTrackIdByMediaType(MediaFormatType.AUDIO);
            boolean z3 = z2;
            if (!z2) {
                z = true;
            }
            if (z3 && z) {
                throw new RuntimeException("Cannot process files with and without video in the same pipeline.");
            }
        }
    }
}
