package com.etsy.etsyapi;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import java.util.Map;

public interface Request<T> extends Parcelable {
    c<T> request();

    c<T> request(@NonNull Map<String, Object> map);
}
