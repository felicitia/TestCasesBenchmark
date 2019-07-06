package org.m4m;

public interface IProgressListener {
    void onError(Exception exc);

    void onMediaDone();

    void onMediaPause();

    void onMediaProgress(float f);

    void onMediaStart();

    void onMediaStop();
}
