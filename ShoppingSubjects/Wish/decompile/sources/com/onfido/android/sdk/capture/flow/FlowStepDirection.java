package com.onfido.android.sdk.capture.flow;

import com.onfido.android.sdk.capture.R;

public enum FlowStepDirection {
    LEFT_TO_RIGHT(Integer.valueOf(17432578), Integer.valueOf(17432579)),
    RIGHT_TO_LEFT(Integer.valueOf(R.anim.onfido_slide_in_right), Integer.valueOf(R.anim.onfido_slide_out_left)),
    NO_DIRECTION(null, null, 3, null);
    
    private final Integer b;
    private final Integer c;

    protected FlowStepDirection(Integer num, Integer num2) {
        this.b = num;
        this.c = num2;
    }

    public final Integer getSlideInAnimation() {
        return this.b;
    }

    public final Integer getSlideOutAnimation() {
        return this.c;
    }
}
