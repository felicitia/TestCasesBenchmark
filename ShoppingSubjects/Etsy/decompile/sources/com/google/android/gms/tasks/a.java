package com.google.android.gms.tasks;

import android.support.annotation.NonNull;

public interface a<TResult, TContinuationResult> {
    TContinuationResult then(@NonNull f<TResult> fVar) throws Exception;
}
