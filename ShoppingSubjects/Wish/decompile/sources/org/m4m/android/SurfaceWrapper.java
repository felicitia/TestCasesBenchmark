package org.m4m.android;

import android.view.Surface;
import org.m4m.domain.ISurfaceWrapper;
import org.m4m.domain.Wrapper;

public class SurfaceWrapper extends Wrapper<Surface> implements ISurfaceWrapper {
    SurfaceWrapper(Surface surface) {
        super(surface);
    }
}
