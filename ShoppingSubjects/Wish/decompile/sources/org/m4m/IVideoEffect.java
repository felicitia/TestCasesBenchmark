package org.m4m;

import org.m4m.domain.Resolution;

public interface IVideoEffect extends IBaseVideoEffect {
    void applyEffect(int i, long j, float[] fArr);

    int getAngle();

    void setInputResolution(Resolution resolution);

    void start();
}
