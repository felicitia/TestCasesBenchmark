package org.m4m;

import org.m4m.domain.FileSegment;
import org.m4m.domain.graphics.TextureRenderer.FillMode;

public interface IBaseVideoEffect {
    FileSegment getSegment();

    void setFillMode(FillMode fillMode);

    void setSegment(FileSegment fileSegment);
}
