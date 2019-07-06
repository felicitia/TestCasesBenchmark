package com.google.android.gms.internal.ads;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.ui.dialog.EtsyDialogFragment;
import com.google.android.gms.common.internal.Constants;
import org.json.JSONException;
import org.json.JSONObject;

@bu
public class m {
    private final nn a;
    private final String b;

    public m(nn nnVar) {
        this(nnVar, "");
    }

    public m(nn nnVar, String str) {
        this.a = nnVar;
        this.b = str;
    }

    public final void a(int i, int i2, int i3, int i4) {
        try {
            this.a.zza("onSizeChanged", new JSONObject().put(EtsyDialogFragment.OPT_X_BUTTON, i).put("y", i2).put(ResponseConstants.WIDTH, i3).put(ResponseConstants.HEIGHT, i4));
        } catch (JSONException e) {
            gv.b("Error occured while dispatching size change.", e);
        }
    }

    public final void a(int i, int i2, int i3, int i4, float f, int i5) {
        try {
            this.a.zza("onScreenInfoChanged", new JSONObject().put(ResponseConstants.WIDTH, i).put(ResponseConstants.HEIGHT, i2).put("maxSizeWidth", i3).put("maxSizeHeight", i4).put(Constants.PARAM_DENSITY, (double) f).put("rotation", i5));
        } catch (JSONException e) {
            gv.b("Error occured while obtaining screen information.", e);
        }
    }

    public final void a(String str) {
        try {
            this.a.zza("onError", new JSONObject().put("message", str).put(ResponseConstants.ACTION, this.b));
        } catch (JSONException e) {
            gv.b("Error occurred while dispatching error event.", e);
        }
    }

    public final void b(int i, int i2, int i3, int i4) {
        try {
            this.a.zza("onDefaultPositionReceived", new JSONObject().put(EtsyDialogFragment.OPT_X_BUTTON, i).put("y", i2).put(ResponseConstants.WIDTH, i3).put(ResponseConstants.HEIGHT, i4));
        } catch (JSONException e) {
            gv.b("Error occured while dispatching default position.", e);
        }
    }

    public final void b(String str) {
        try {
            this.a.zza("onReadyEventReceived", new JSONObject().put("js", str));
        } catch (JSONException e) {
            gv.b("Error occured while dispatching ready Event.", e);
        }
    }

    public final void c(String str) {
        try {
            this.a.zza("onStateChanged", new JSONObject().put(ResponseConstants.STATE, str));
        } catch (JSONException e) {
            gv.b("Error occured while dispatching state change.", e);
        }
    }
}
