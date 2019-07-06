package com.onfido.android.sdk.capture.ui.options;

import kotlin.collections.ArraysKt;

public enum FlowAction {
    WELCOME,
    CAPTURE_DOCUMENT,
    MESSAGE_FACE_VERIFICATION,
    MESSAGE_LIVENESS_VERIFICATION,
    CAPTURE_FACE,
    CAPTURE_LIVENESS,
    CAPTURE_LIVENESS_CONFIRMATION,
    SYNC_LOADING,
    FINAL,
    MESSAGE;
    
    public static final Companion Companion = null;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FlowAction[] getDefaultFlow() {
            return new FlowAction[]{FlowAction.WELCOME, FlowAction.CAPTURE_DOCUMENT, FlowAction.CAPTURE_FACE, FlowAction.FINAL};
        }

        public final FlowAction[] getNonDuplicable() {
            return new FlowAction[]{FlowAction.CAPTURE_DOCUMENT, FlowAction.CAPTURE_FACE, FlowAction.MESSAGE_FACE_VERIFICATION, FlowAction.CAPTURE_LIVENESS, FlowAction.SYNC_LOADING, FlowAction.FINAL};
        }
    }

    static {
        Companion = new Companion(null);
    }

    public final boolean isCapture() {
        return ArraysKt.contains(new FlowAction[]{CAPTURE_DOCUMENT, CAPTURE_FACE, CAPTURE_LIVENESS}, this);
    }
}
