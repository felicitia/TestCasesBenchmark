package com.facebook.login;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.etsy.android.lib.models.ResponseConstants;
import com.facebook.AccessToken;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookActivity;
import com.facebook.FacebookException;
import com.facebook.FacebookRequestError;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.b;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.a.a.a;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.common.a.d;
import com.facebook.common.a.e;
import com.facebook.common.a.f;
import com.facebook.common.a.g;
import com.facebook.internal.SmartLoginOption;
import com.facebook.internal.aa;
import com.facebook.internal.k;
import com.facebook.internal.z;
import com.facebook.internal.z.c;
import com.facebook.login.LoginClient.Request;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceAuthDialog extends DialogFragment {
    private static final String DEVICE_LOGIN_ENDPOINT = "device/login";
    private static final String DEVICE_LOGIN_STATUS_ENDPOINT = "device/login_status";
    private static final int LOGIN_ERROR_SUBCODE_AUTHORIZATION_DECLINED = 1349173;
    private static final int LOGIN_ERROR_SUBCODE_AUTHORIZATION_PENDING = 1349174;
    private static final int LOGIN_ERROR_SUBCODE_CODE_EXPIRED = 1349152;
    private static final int LOGIN_ERROR_SUBCODE_EXCESSIVE_POLLING = 1349172;
    private static final String REQUEST_STATE_KEY = "request_state";
    /* access modifiers changed from: private */
    public AtomicBoolean completed = new AtomicBoolean();
    private TextView confirmationCode;
    private volatile GraphRequestAsyncTask currentGraphRequestPoll;
    /* access modifiers changed from: private */
    public volatile RequestState currentRequestState;
    private DeviceAuthMethodHandler deviceAuthMethodHandler;
    /* access modifiers changed from: private */
    public Dialog dialog;
    private TextView instructions;
    /* access modifiers changed from: private */
    public boolean isBeingDestroyed = false;
    /* access modifiers changed from: private */
    public boolean isRetry = false;
    /* access modifiers changed from: private */
    public Request mRequest = null;
    private ProgressBar progressBar;
    private volatile ScheduledFuture scheduledPoll;

    private static class RequestState implements Parcelable {
        public static final Creator<RequestState> CREATOR = new Creator<RequestState>() {
            /* renamed from: a */
            public RequestState createFromParcel(Parcel parcel) {
                return new RequestState(parcel);
            }

            /* renamed from: a */
            public RequestState[] newArray(int i) {
                return new RequestState[i];
            }
        };
        private String a;
        private String b;
        private String c;
        private long d;
        private long e;

        public int describeContents() {
            return 0;
        }

        RequestState() {
        }

        public String a() {
            return this.a;
        }

        public String b() {
            return this.b;
        }

        public void a(String str) {
            this.b = str;
            this.a = String.format(Locale.ENGLISH, "https://facebook.com/device?user_code=%1$s&qr=1", new Object[]{str});
        }

        public String c() {
            return this.c;
        }

        public void b(String str) {
            this.c = str;
        }

        public long d() {
            return this.d;
        }

        public void a(long j) {
            this.d = j;
        }

        public void b(long j) {
            this.e = j;
        }

        protected RequestState(Parcel parcel) {
            this.b = parcel.readString();
            this.c = parcel.readString();
            this.d = parcel.readLong();
            this.e = parcel.readLong();
        }

        public boolean e() {
            boolean z = false;
            if (this.e == 0) {
                return false;
            }
            if ((new Date().getTime() - this.e) - (this.d * 1000) < 0) {
                z = true;
            }
            return z;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.b);
            parcel.writeString(this.c);
            parcel.writeLong(this.d);
            parcel.writeLong(this.e);
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.deviceAuthMethodHandler = (DeviceAuthMethodHandler) ((LoginFragment) ((FacebookActivity) getActivity()).getCurrentFragment()).getLoginClient().g();
        if (bundle != null) {
            RequestState requestState = (RequestState) bundle.getParcelable(REQUEST_STATE_KEY);
            if (requestState != null) {
                setCurrentRequestState(requestState);
            }
        }
        return onCreateView;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        this.dialog = new Dialog(getActivity(), g.com_facebook_auth_dialog);
        this.dialog.setContentView(initializeContentView(a.b() && !this.isRetry));
        return this.dialog;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if (!this.isBeingDestroyed) {
            onCancel();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.currentRequestState != null) {
            bundle.putParcelable(REQUEST_STATE_KEY, this.currentRequestState);
        }
    }

    public void onDestroy() {
        this.isBeingDestroyed = true;
        this.completed.set(true);
        super.onDestroy();
        if (this.currentGraphRequestPoll != null) {
            this.currentGraphRequestPoll.cancel(true);
        }
        if (this.scheduledPoll != null) {
            this.scheduledPoll.cancel(true);
        }
    }

    public void startLogin(Request request) {
        this.mRequest = request;
        Bundle bundle = new Bundle();
        bundle.putString("scope", TextUtils.join(",", request.getPermissions()));
        String deviceRedirectUriString = request.getDeviceRedirectUriString();
        if (deviceRedirectUriString != null) {
            bundle.putString("redirect_uri", deviceRedirectUriString);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(aa.b());
        sb.append("|");
        sb.append(aa.c());
        bundle.putString(AccessToken.ACCESS_TOKEN_KEY, sb.toString());
        bundle.putString("device_info", a.a());
        GraphRequest graphRequest = new GraphRequest(null, DEVICE_LOGIN_ENDPOINT, bundle, HttpMethod.POST, new b() {
            public void a(GraphResponse graphResponse) {
                if (!DeviceAuthDialog.this.isBeingDestroyed) {
                    if (graphResponse.a() != null) {
                        DeviceAuthDialog.this.onError(graphResponse.a().getException());
                        return;
                    }
                    JSONObject b = graphResponse.b();
                    RequestState requestState = new RequestState();
                    try {
                        requestState.a(b.getString("user_code"));
                        requestState.b(b.getString(ResponseConstants.CODE));
                        requestState.a(b.getLong("interval"));
                        DeviceAuthDialog.this.setCurrentRequestState(requestState);
                    } catch (JSONException e) {
                        DeviceAuthDialog.this.onError(new FacebookException((Throwable) e));
                    }
                }
            }
        });
        graphRequest.j();
    }

    /* access modifiers changed from: private */
    public void setCurrentRequestState(RequestState requestState) {
        this.currentRequestState = requestState;
        this.confirmationCode.setText(requestState.b());
        this.instructions.setCompoundDrawablesWithIntrinsicBounds(null, new BitmapDrawable(getResources(), a.b(requestState.a())), null, null);
        this.confirmationCode.setVisibility(0);
        this.progressBar.setVisibility(8);
        if (!this.isRetry && a.a(requestState.b())) {
            AppEventsLogger.a(getContext()).a("fb_smart_login_service", (Double) null, (Bundle) null);
        }
        if (requestState.e()) {
            schedulePoll();
        } else {
            poll();
        }
    }

    /* access modifiers changed from: private */
    public View initializeContentView(boolean z) {
        View view;
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        if (z) {
            view = layoutInflater.inflate(e.com_facebook_smart_device_dialog_fragment, null);
        } else {
            view = layoutInflater.inflate(e.com_facebook_device_auth_dialog_fragment, null);
        }
        this.progressBar = (ProgressBar) view.findViewById(d.progress_bar);
        this.confirmationCode = (TextView) view.findViewById(d.confirmation_code);
        ((Button) view.findViewById(d.cancel_button)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                DeviceAuthDialog.this.onCancel();
            }
        });
        this.instructions = (TextView) view.findViewById(d.com_facebook_device_auth_instructions);
        this.instructions.setText(Html.fromHtml(getString(f.com_facebook_device_auth_instructions)));
        return view;
    }

    /* access modifiers changed from: private */
    public void poll() {
        this.currentRequestState.b(new Date().getTime());
        this.currentGraphRequestPoll = getPollRequest().j();
    }

    /* access modifiers changed from: private */
    public void schedulePoll() {
        this.scheduledPoll = DeviceAuthMethodHandler.b().schedule(new Runnable() {
            public void run() {
                DeviceAuthDialog.this.poll();
            }
        }, this.currentRequestState.d(), TimeUnit.SECONDS);
    }

    private GraphRequest getPollRequest() {
        Bundle bundle = new Bundle();
        bundle.putString(ResponseConstants.CODE, this.currentRequestState.c());
        GraphRequest graphRequest = new GraphRequest(null, DEVICE_LOGIN_STATUS_ENDPOINT, bundle, HttpMethod.POST, new b() {
            public void a(GraphResponse graphResponse) {
                if (!DeviceAuthDialog.this.completed.get()) {
                    FacebookRequestError a2 = graphResponse.a();
                    if (a2 != null) {
                        int subErrorCode = a2.getSubErrorCode();
                        if (subErrorCode != DeviceAuthDialog.LOGIN_ERROR_SUBCODE_CODE_EXPIRED) {
                            switch (subErrorCode) {
                                case DeviceAuthDialog.LOGIN_ERROR_SUBCODE_EXCESSIVE_POLLING /*1349172*/:
                                case DeviceAuthDialog.LOGIN_ERROR_SUBCODE_AUTHORIZATION_PENDING /*1349174*/:
                                    DeviceAuthDialog.this.schedulePoll();
                                    break;
                                case DeviceAuthDialog.LOGIN_ERROR_SUBCODE_AUTHORIZATION_DECLINED /*1349173*/:
                                    break;
                                default:
                                    DeviceAuthDialog.this.onError(graphResponse.a().getException());
                                    break;
                            }
                        }
                        DeviceAuthDialog.this.onCancel();
                        return;
                    }
                    try {
                        DeviceAuthDialog.this.onSuccess(graphResponse.b().getString(AccessToken.ACCESS_TOKEN_KEY));
                    } catch (JSONException e) {
                        DeviceAuthDialog.this.onError(new FacebookException((Throwable) e));
                    }
                }
            }
        });
        return graphRequest;
    }

    /* access modifiers changed from: private */
    public void presentConfirmation(final String str, final c cVar, final String str2, String str3) {
        String string = getResources().getString(f.com_facebook_smart_login_confirmation_title);
        String string2 = getResources().getString(f.com_facebook_smart_login_confirmation_continue_as);
        String string3 = getResources().getString(f.com_facebook_smart_login_confirmation_cancel);
        String format = String.format(string2, new Object[]{str3});
        Builder builder = new Builder(getContext());
        builder.setMessage(string).setCancelable(true).setNegativeButton(format, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                DeviceAuthDialog.this.completeLogin(str, cVar, str2);
            }
        }).setPositiveButton(string3, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                DeviceAuthDialog.this.dialog.setContentView(DeviceAuthDialog.this.initializeContentView(false));
                DeviceAuthDialog.this.startLogin(DeviceAuthDialog.this.mRequest);
            }
        });
        builder.create().show();
    }

    /* access modifiers changed from: private */
    public void onSuccess(final String str) {
        Bundle bundle = new Bundle();
        bundle.putString("fields", "id,permissions,name");
        AccessToken accessToken = new AccessToken(str, com.facebook.f.j(), "0", null, null, null, null, null);
        GraphRequest graphRequest = new GraphRequest(accessToken, "me", bundle, HttpMethod.GET, new b() {
            public void a(GraphResponse graphResponse) {
                if (!DeviceAuthDialog.this.completed.get()) {
                    if (graphResponse.a() != null) {
                        DeviceAuthDialog.this.onError(graphResponse.a().getException());
                        return;
                    }
                    try {
                        JSONObject b2 = graphResponse.b();
                        String string = b2.getString("id");
                        c a2 = z.a(b2);
                        String string2 = b2.getString(ResponseConstants.NAME);
                        a.c(DeviceAuthDialog.this.currentRequestState.b());
                        if (!k.a(com.facebook.f.j()).g().contains(SmartLoginOption.RequireConfirm) || DeviceAuthDialog.this.isRetry) {
                            DeviceAuthDialog.this.completeLogin(string, a2, str);
                            return;
                        }
                        DeviceAuthDialog.this.isRetry = true;
                        DeviceAuthDialog.this.presentConfirmation(string, a2, str, string2);
                    } catch (JSONException e) {
                        DeviceAuthDialog.this.onError(new FacebookException((Throwable) e));
                    }
                }
            }
        });
        graphRequest.j();
    }

    /* access modifiers changed from: private */
    public void completeLogin(String str, c cVar, String str2) {
        this.deviceAuthMethodHandler.a(str2, com.facebook.f.j(), str, cVar.a(), cVar.b(), AccessTokenSource.DEVICE_AUTH, null, null);
        this.dialog.dismiss();
    }

    /* access modifiers changed from: private */
    public void onError(FacebookException facebookException) {
        if (this.completed.compareAndSet(false, true)) {
            if (this.currentRequestState != null) {
                a.c(this.currentRequestState.b());
            }
            this.deviceAuthMethodHandler.a((Exception) facebookException);
            this.dialog.dismiss();
        }
    }

    /* access modifiers changed from: private */
    public void onCancel() {
        if (this.completed.compareAndSet(false, true)) {
            if (this.currentRequestState != null) {
                a.c(this.currentRequestState.b());
            }
            if (this.deviceAuthMethodHandler != null) {
                this.deviceAuthMethodHandler.a();
            }
            this.dialog.dismiss();
        }
    }
}
