package com.otaliastudios.cameraview;

import android.hardware.Camera.Parameters;

public class ExtraProperties {
    private float horizontalViewingAngle;
    private float verticalViewingAngle;

    ExtraProperties(Parameters parameters) {
        this.verticalViewingAngle = parameters.getVerticalViewAngle();
        this.horizontalViewingAngle = parameters.getHorizontalViewAngle();
    }
}
