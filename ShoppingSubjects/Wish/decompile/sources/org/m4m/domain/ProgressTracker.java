package org.m4m.domain;

public class ProgressTracker {
    private float currentProgress = 0.0f;
    private float finish = 0.0f;

    public float getProgress() {
        return this.currentProgress / this.finish;
    }

    public void setFinish(float f) {
        this.finish = f;
    }

    public void track(float f) {
        if (f > this.currentProgress) {
            this.currentProgress = f;
        }
    }
}
