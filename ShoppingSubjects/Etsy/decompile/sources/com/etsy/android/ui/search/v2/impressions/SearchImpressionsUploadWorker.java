package com.etsy.android.ui.search.v2.impressions;

import androidx.work.Worker;
import androidx.work.Worker.Result;
import com.etsy.android.BOEApplication;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.logger.l;
import kotlin.TypeCastException;

/* compiled from: SearchImpressionsUploadWorker.kt */
public final class SearchImpressionsUploadWorker extends Worker {

    /* compiled from: SearchImpressionsUploadWorker.kt */
    public interface a {
        l logCat();

        d searchImpressionRepository();
    }

    public Result d() {
        EtsyApplication etsyApplication = BOEApplication.get();
        if (etsyApplication == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.etsy.android.ui.search.v2.impressions.SearchImpressionsUploadWorker.DependencyProvider");
        }
        a aVar = (a) etsyApplication;
        d searchImpressionRepository = aVar.searchImpressionRepository();
        l logCat = aVar.logCat();
        try {
            searchImpressionRepository.a();
        } catch (Exception e) {
            logCat.b("doWork() - Error uploading search impressions", e);
        }
        return Result.SUCCESS;
    }
}
