package com.usebutton.merchant;

import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.usebutton.merchant.exception.ButtonNetworkException;
import java.util.Map;

/* compiled from: ButtonApi */
interface a {
    @Nullable
    @WorkerThread
    o a(String str, String str2, boolean z, Map<String, String> map) throws ButtonNetworkException;
}
