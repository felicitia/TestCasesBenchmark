package com.contextlogic.wish.api.service.standalone;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.datacenter.AuthenticationDataCenter;
import com.contextlogic.wish.api.model.SignupFlowPageInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultCodeFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.http.HttpCookieManager;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class LoginService extends SingleApiService {

    public static class LoginContext {
        public boolean createNewUser;
        public String email;
        public String fbId;
        public String firstName;
        public String googleAuthToken;
        public String googleId;
        public String lastName;
        public String password;
        public boolean sessionRefresh;
    }

    public static class SignupFlowContext implements Parcelable {
        public static final Creator<SignupFlowContext> CREATOR = new Creator<SignupFlowContext>() {
            public SignupFlowContext createFromParcel(Parcel parcel) {
                return new SignupFlowContext(parcel);
            }

            public SignupFlowContext[] newArray(int i) {
                return new SignupFlowContext[i];
            }
        };
        public String genderInferred;
        public SignupFlowType signupFlowMode;
        public ArrayList<SignupFlowPageInfo> signupFlowPageInfos;

        public enum SignupFlowType implements Parcelable {
            Categories,
            FreeGifts,
            None;
            
            public static final Creator<SignupFlowType> CREATOR = null;

            public int describeContents() {
                return 0;
            }

            static {
                CREATOR = new Creator<SignupFlowType>() {
                    public SignupFlowType createFromParcel(Parcel parcel) {
                        return SignupFlowType.values()[parcel.readInt()];
                    }

                    public SignupFlowType[] newArray(int i) {
                        return new SignupFlowType[i];
                    }
                };
            }

            public SignupFlowType fromString(String str) {
                if (str == null) {
                    return None;
                }
                char c = 65535;
                int hashCode = str.hashCode();
                if (hashCode != -426390953) {
                    if (hashCode == 1296516636 && str.equals("categories")) {
                        c = 0;
                    }
                } else if (str.equals("freegifts")) {
                    c = 1;
                }
                switch (c) {
                    case 0:
                        return Categories;
                    case 1:
                        return FreeGifts;
                    default:
                        return None;
                }
            }

            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(ordinal());
            }
        }

        public int describeContents() {
            return 0;
        }

        public SignupFlowContext() {
        }

        protected SignupFlowContext(Parcel parcel) {
            this.signupFlowMode = (SignupFlowType) parcel.readParcelable(SignupFlowType.class.getClassLoader());
            this.signupFlowPageInfos = parcel.readArrayList(SignupFlowPageInfo.class.getClassLoader());
            this.genderInferred = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.signupFlowMode, i);
            parcel.writeList(this.signupFlowPageInfos);
            parcel.writeString(this.genderInferred);
        }

        public static SignupFlowContext createFromLoginResponseData(JSONObject jSONObject) {
            SignupFlowContext signupFlowContext = new SignupFlowContext();
            signupFlowContext.signupFlowMode = SignupFlowType.Categories.fromString(JsonUtil.optString(jSONObject, "signup_flow_type"));
            signupFlowContext.signupFlowPageInfos = JsonUtil.parseArray(jSONObject, "signup_flow_pages", new DataParser<SignupFlowPageInfo, JSONObject>() {
                public SignupFlowPageInfo parseData(JSONObject jSONObject) throws JSONException, ParseException {
                    return new SignupFlowPageInfo(jSONObject);
                }
            });
            signupFlowContext.genderInferred = JsonUtil.optString(jSONObject, "inferred_gender");
            return signupFlowContext;
        }
    }

    public interface SuccessCallback {
        void onSuccess(String str, boolean z, SignupFlowContext signupFlowContext);
    }

    /* access modifiers changed from: protected */
    public void parseSuccess(LoginContext loginContext, ApiResponse apiResponse, SuccessCallback successCallback) throws JSONException {
        String string = apiResponse.getData().getString("user");
        boolean optBoolean = apiResponse.getData().optBoolean("new_user", false);
        String value = HttpCookieManager.getInstance().getSessionCookie() != null ? HttpCookieManager.getInstance().getSessionCookie().value() : null;
        if (value == null || value.isEmpty()) {
            throw new JSONException("Invalid session ID");
        }
        AuthenticationDataCenter.getInstance().initializeData(string, value, System.currentTimeMillis(), loginContext.fbId, loginContext.email, loginContext.googleId);
        final SignupFlowContext createFromLoginResponseData = SignupFlowContext.createFromLoginResponseData(apiResponse.getData());
        if (successCallback != null) {
            final SuccessCallback successCallback2 = successCallback;
            final String str = string;
            final boolean z = optBoolean;
            AnonymousClass1 r2 = new Runnable() {
                public void run() {
                    successCallback2.onSuccess(str, z, createFromLoginResponseData);
                }
            };
            postRunnable(r2);
        }
    }

    /* access modifiers changed from: protected */
    public void parseFailure(ApiResponse apiResponse, final String str, final DefaultFailureCallback defaultFailureCallback) {
        if (defaultFailureCallback != null) {
            postRunnable(new Runnable() {
                public void run() {
                    defaultFailureCallback.onFailure(str);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void parseFailureMoreDetail(final int i, final String str, final DefaultCodeFailureCallback defaultCodeFailureCallback) {
        if (defaultCodeFailureCallback != null) {
            postRunnable(new Runnable() {
                public void run() {
                    defaultCodeFailureCallback.onFailure(str, i);
                }
            });
        }
    }
}
