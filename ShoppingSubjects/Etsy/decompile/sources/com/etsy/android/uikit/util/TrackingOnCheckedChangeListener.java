package com.etsy.android.uikit.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.ViewClickAnalyticsLog.ViewAction;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.logger.i;
import com.etsy.android.lib.models.datatypes.EtsyId;

public abstract class TrackingOnCheckedChangeListener extends h implements OnCheckedChangeListener {
    private static final String TAG = f.a(TrackingOnCheckedChangeListener.class);

    public abstract void onViewCheckedChanged(CompoundButton compoundButton, boolean z);

    public TrackingOnCheckedChangeListener() {
    }

    public TrackingOnCheckedChangeListener(@NonNull AnalyticsLogAttribute analyticsLogAttribute, @Nullable Object obj) {
        super(analyticsLogAttribute, obj);
    }

    public TrackingOnCheckedChangeListener(@NonNull AnalyticsLogAttribute analyticsLogAttribute, @Nullable EtsyId etsyId) {
        super(analyticsLogAttribute, etsyId);
    }

    public TrackingOnCheckedChangeListener(i... iVarArr) {
        super(iVarArr);
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        trackAction(compoundButton, z ? ViewAction.checked : ViewAction.unchecked);
        onViewCheckedChanged(compoundButton, z);
    }
}
