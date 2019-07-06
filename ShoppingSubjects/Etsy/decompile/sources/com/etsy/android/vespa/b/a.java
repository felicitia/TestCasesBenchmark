package com.etsy.android.vespa.b;

import android.os.Bundle;
import android.support.annotation.Nullable;
import java.util.HashMap;

/* compiled from: IPagination */
public interface a {
    boolean canLoadContent();

    @Nullable
    String getApiNextLink();

    int getLoadTriggerPosition();

    @Nullable
    HashMap<String, String> getPaginationParams();

    void onSaveInstanceState(Bundle bundle);

    void onSuccess(Object obj, int i);

    void onViewStateRestored(@Nullable Bundle bundle);

    void recalculateLoadTriggerPosition(int i);

    void reset();

    void setContentExhausted(boolean z);
}
