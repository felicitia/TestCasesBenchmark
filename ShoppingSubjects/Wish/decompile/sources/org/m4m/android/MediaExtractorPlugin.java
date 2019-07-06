package org.m4m.android;

import android.content.Context;
import android.media.MediaExtractor;
import android.media.MediaMetadataRetriever;
import java.io.FileDescriptor;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.m4m.Uri;
import org.m4m.domain.IMediaExtractor;
import org.m4m.domain.MediaFormat;

public class MediaExtractorPlugin implements IMediaExtractor {
    private Context context;
    private FileDescriptor fileDescriptor;
    private MediaExtractor mediaExtractor = new MediaExtractor();
    private String path;
    private Uri uri;

    public void setDataSource(Context context2, Uri uri2) throws IOException {
        this.context = context2;
        this.uri = uri2;
        this.mediaExtractor.setDataSource(context2, android.net.Uri.parse(uri2.getString()), null);
    }

    public int getTrackCount() {
        return this.mediaExtractor.getTrackCount();
    }

    public MediaFormat getTrackFormat(int i) {
        if (this.mediaExtractor.getTrackFormat(i).getString("mime").contains("video")) {
            return new VideoFormatAndroid(this.mediaExtractor.getTrackFormat(i));
        }
        if (this.mediaExtractor.getTrackFormat(i).getString("mime").contains("audio")) {
            return new AudioFormatAndroid(this.mediaExtractor.getTrackFormat(i));
        }
        return null;
    }

    public void selectTrack(int i) {
        this.mediaExtractor.selectTrack(i);
    }

    public int getSampleTrackIndex() {
        return this.mediaExtractor.getSampleTrackIndex();
    }

    public boolean advance() {
        return this.mediaExtractor.advance();
    }

    public void release() {
        this.mediaExtractor.release();
    }

    public int getSampleFlags() {
        return this.mediaExtractor.getSampleFlags();
    }

    public void seekTo(long j, int i) {
        this.mediaExtractor.seekTo(j, i);
    }

    public int getRotation() {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        if (this.path != null) {
            mediaMetadataRetriever.setDataSource(this.path);
        } else if (this.fileDescriptor != null) {
            mediaMetadataRetriever.setDataSource(this.fileDescriptor);
        } else if (this.uri != null) {
            mediaMetadataRetriever.setDataSource(this.context, android.net.Uri.parse(this.uri.getString()));
        } else {
            throw new IllegalStateException("File not set");
        }
        String extractMetadata = mediaMetadataRetriever.extractMetadata(24);
        mediaMetadataRetriever.release();
        return Integer.parseInt(extractMetadata);
    }

    public int readSampleData(ByteBuffer byteBuffer) {
        return this.mediaExtractor.readSampleData(byteBuffer, 0);
    }

    public long getSampleTime() {
        return this.mediaExtractor.getSampleTime();
    }
}
