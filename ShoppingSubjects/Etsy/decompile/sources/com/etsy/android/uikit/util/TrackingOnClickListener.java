package com.etsy.android.uikit.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.ViewClickAnalyticsLog.ViewAction;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.logger.i;
import com.etsy.android.lib.models.datatypes.EtsyId;

public abstract class TrackingOnClickListener extends h implements OnClickListener {
    private static final String TAG = f.a(TrackingOnClickListener.class);

    public abstract void onViewClick(View view);

    public TrackingOnClickListener() {
    }

    public TrackingOnClickListener(@NonNull AnalyticsLogAttribute analyticsLogAttribute, @Nullable Object obj) {
        super(analyticsLogAttribute, obj);
    }

    public TrackingOnClickListener(@NonNull AnalyticsLogAttribute analyticsLogAttribute, @Nullable EtsyId etsyId) {
        super(analyticsLogAttribute, etsyId);
    }

    public TrackingOnClickListener(i... iVarArr) {
        super(iVarArr);
    }

    public final void onClick(View view) {
        trackAction(view, ViewAction.clicked);
        onViewClick(view);
    }
}
