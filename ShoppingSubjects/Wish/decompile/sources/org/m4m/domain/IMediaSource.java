package org.m4m.domain;

public interface IMediaSource extends IOutput, IVideoOutput {
    int getTrackIdByMediaType(MediaFormatType mediaFormatType);

    void selectTrack(int i);
}
