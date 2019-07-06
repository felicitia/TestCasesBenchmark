package com.etsy.android.uikit.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnLongClickListener;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.ViewClickAnalyticsLog.ViewAction;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.logger.i;
import com.etsy.android.lib.models.datatypes.EtsyId;

public abstract class TrackingOnLongClickListener extends h implements OnLongClickListener {
    private static final String TAG = f.a(TrackingOnClickListener.class);

    public abstract boolean onViewLongClick(View view);

    public TrackingOnLongClickListener() {
    }

    public TrackingOnLongClickListener(@NonNull AnalyticsLogAttribute analyticsLogAttribute, @Nullable Object obj) {
        super(analyticsLogAttribute, obj);
    }

    public TrackingOnLongClickListener(@NonNull AnalyticsLogAttribute analyticsLogAttribute, @Nullable EtsyId etsyId) {
        super(analyticsLogAttribute, etsyId);
    }

    public TrackingOnLongClickListener(i... iVarArr) {
        super(iVarArr);
    }

    public final boolean onLongClick(View view) {
        trackAction(view, ViewAction.long_clicked);
        return onViewLongClick(view);
    }
}
