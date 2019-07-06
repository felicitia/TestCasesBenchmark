package com.contextlogic.wish.api.model;

import android.os.Parcelable;
import com.contextlogic.wish.api.service.standalone.LogErrorService;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseModel implements Parcelable {
    /* access modifiers changed from: protected */
    public abstract void parseJson(JSONObject jSONObject) throws JSONException, ParseException;

    public BaseModel() {
    }

    public BaseModel(JSONObject jSONObject) throws JSONException, ParseException {
        try {
            parseJson(jSONObject);
        } catch (ParseException | JSONException e) {
            LogErrorService.logModelParseError(this, e);
            throw e;
        }
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
