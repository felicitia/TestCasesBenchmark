package com.contextlogic.wish.api.service;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.JobIntentService;
import com.contextlogic.wish.api.model.WishGoogleAppIndexingData;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.GetUpdateAppIndexDataService;
import com.contextlogic.wish.api.service.standalone.GetUpdateAppIndexDataService.SuccessCallback;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.appindexing.FirebaseAppIndex;
import com.google.firebase.appindexing.Indexable;
import io.fabric.sdk.android.Fabric;
import java.util.List;

public class AppIndexingUpdateService extends JobIntentService {
    private static final int UNIQUE_JOB_ID = AppIndexingUpdateService.class.getName().hashCode();

    private class AppIndexUpdateException extends Exception {
        AppIndexUpdateException(String str) {
            super(str);
        }
    }

    public static void enqueueWork(Context context) {
        enqueueWork(context, AppIndexingUpdateService.class, UNIQUE_JOB_ID, new Intent());
    }

    /* access modifiers changed from: protected */
    public void onHandleWork(Intent intent) {
        new GetUpdateAppIndexDataService().requestService(new SuccessCallback() {
            public void onSuccess(List<WishGoogleAppIndexingData> list) {
                AppIndexingUpdateService.this.updateIndex(list);
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                if (Fabric.isInitialized()) {
                    AppIndexingUpdateService appIndexingUpdateService = AppIndexingUpdateService.this;
                    if (str == null) {
                        str = "No error message.";
                    }
                    Crashlytics.logException(new AppIndexUpdateException(str));
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateIndex(List<WishGoogleAppIndexingData> list) {
        if (list != null && !list.isEmpty()) {
            Indexable[] indexableArr = new Indexable[list.size()];
            for (int i = 0; i < list.size(); i++) {
                indexableArr[i] = ((WishGoogleAppIndexingData) list.get(i)).toAppIndexIndexable();
            }
            FirebaseAppIndex.getInstance().update(indexableArr).addOnFailureListener(new OnFailureListener() {
                public void onFailure(Exception exc) {
                    Crashlytics.logException(exc);
                }
            });
        }
    }
}
