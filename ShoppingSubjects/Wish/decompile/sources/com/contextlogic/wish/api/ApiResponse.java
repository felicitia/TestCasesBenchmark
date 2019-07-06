package com.contextlogic.wish.api;

import com.contextlogic.wish.util.JsonUtil;
import org.json.JSONObject;

public class ApiResponse {
    private int mCode;
    private JSONObject mData;
    private String mMessage;

    public ApiResponse(JSONObject jSONObject) {
        this.mMessage = JsonUtil.optString(jSONObject, "msg");
        this.mCode = jSONObject.optInt("code", -1);
        this.mData = jSONObject.optJSONObject("data");
        if (this.mData == null) {
            this.mData = new JSONObject();
        }
    }

    public JSONObject getData() {
        return this.mData;
    }

    public boolean hasData() {
        return this.mData != null;
    }

    public int getCode() {
        return this.mCode;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public boolean isErrorResponse() {
        return this.mCode != 0;
    }
}
