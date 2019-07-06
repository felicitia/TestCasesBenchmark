package com.facebook.share.internal;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.AccessToken;
import com.facebook.FacebookRequestError;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.b;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.a.a.a;
import com.facebook.common.a.d;
import com.facebook.common.a.e;
import com.facebook.common.a.f;
import com.facebook.common.a.g;
import com.facebook.internal.aa;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphContent;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceShareDialogFragment extends DialogFragment {
    private static final String DEVICE_SHARE_ENDPOINT = "device/share";
    private static final String EXTRA_ERROR = "error";
    private static final String REQUEST_STATE_KEY = "request_state";
    public static final String TAG = "DeviceShareDialogFragment";
    private static ScheduledThreadPoolExecutor backgroundExecutor;
    private volatile ScheduledFuture codeExpiredFuture;
    private TextView confirmationCode;
    private volatile RequestState currentRequestState;
    /* access modifiers changed from: private */
    public Dialog dialog;
    private ProgressBar progressBar;
    private ShareContent shareContent;

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
        private long b;

        public int describeContents() {
            return 0;
        }

        RequestState() {
        }

        public String a() {
            return this.a;
        }

        public void a(String str) {
            this.a = str;
        }

        public long b() {
            return this.b;
        }

        public void a(long j) {
            this.b = j;
        }

        protected RequestState(Parcel parcel) {
            this.a = parcel.readString();
            this.b = parcel.readLong();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.a);
            parcel.writeLong(this.b);
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
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
        View inflate = getActivity().getLayoutInflater().inflate(e.com_facebook_device_auth_dialog_fragment, null);
        this.progressBar = (ProgressBar) inflate.findViewById(d.progress_bar);
        this.confirmationCode = (TextView) inflate.findViewById(d.confirmation_code);
        ((Button) inflate.findViewById(d.cancel_button)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                DeviceShareDialogFragment.this.dialog.dismiss();
            }
        });
        ((TextView) inflate.findViewById(d.com_facebook_device_auth_instructions)).setText(Html.fromHtml(getString(f.com_facebook_device_auth_instructions)));
        this.dialog.setContentView(inflate);
        startShare();
        return this.dialog;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if (this.codeExpiredFuture != null) {
            this.codeExpiredFuture.cancel(true);
        }
        finishActivity(-1, new Intent());
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.currentRequestState != null) {
            bundle.putParcelable(REQUEST_STATE_KEY, this.currentRequestState);
        }
    }

    private void finishActivity(int i, Intent intent) {
        if (this.currentRequestState != null) {
            a.c(this.currentRequestState.a());
        }
        FacebookRequestError facebookRequestError = (FacebookRequestError) intent.getParcelableExtra("error");
        if (facebookRequestError != null) {
            Toast.makeText(getContext(), facebookRequestError.getErrorMessage(), 0).show();
        }
        if (isAdded()) {
            FragmentActivity activity = getActivity();
            activity.setResult(i, intent);
            activity.finish();
        }
    }

    private void detach() {
        if (isAdded()) {
            getFragmentManager().beginTransaction().remove(this).commit();
        }
    }

    public void setShareContent(ShareContent shareContent2) {
        this.shareContent = shareContent2;
    }

    private Bundle getGraphParametersForShareContent() {
        ShareContent shareContent2 = this.shareContent;
        if (shareContent2 == null) {
            return null;
        }
        if (shareContent2 instanceof ShareLinkContent) {
            return m.a((ShareLinkContent) shareContent2);
        }
        if (shareContent2 instanceof ShareOpenGraphContent) {
            return m.a((ShareOpenGraphContent) shareContent2);
        }
        return null;
    }

    private void startShare() {
        Bundle graphParametersForShareContent = getGraphParametersForShareContent();
        if (graphParametersForShareContent == null || graphParametersForShareContent.size() == 0) {
            finishActivityWithError(new FacebookRequestError(0, "", "Failed to get share content"));
        }
        StringBuilder sb = new StringBuilder();
        sb.append(aa.b());
        sb.append("|");
        sb.append(aa.c());
        graphParametersForShareContent.putString(AccessToken.ACCESS_TOKEN_KEY, sb.toString());
        graphParametersForShareContent.putString("device_info", a.a());
        GraphRequest graphRequest = new GraphRequest(null, DEVICE_SHARE_ENDPOINT, graphParametersForShareContent, HttpMethod.POST, new b() {
            public void a(GraphResponse graphResponse) {
                FacebookRequestError a2 = graphResponse.a();
                if (a2 != null) {
                    DeviceShareDialogFragment.this.finishActivityWithError(a2);
                    return;
                }
                JSONObject b = graphResponse.b();
                RequestState requestState = new RequestState();
                try {
                    requestState.a(b.getString("user_code"));
                    requestState.a(b.getLong(AccessToken.EXPIRES_IN_KEY));
                    DeviceShareDialogFragment.this.setCurrentRequestState(requestState);
                } catch (JSONException unused) {
                    DeviceShareDialogFragment.this.finishActivityWithError(new FacebookRequestError(0, "", "Malformed server response"));
                }
            }
        });
        graphRequest.j();
    }

    /* access modifiers changed from: private */
    public void finishActivityWithError(FacebookRequestError facebookRequestError) {
        detach();
        Intent intent = new Intent();
        intent.putExtra("error", facebookRequestError);
        finishActivity(-1, intent);
    }

    private static synchronized ScheduledThreadPoolExecutor getBackgroundExecutor() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
        synchronized (DeviceShareDialogFragment.class) {
            if (backgroundExecutor == null) {
                backgroundExecutor = new ScheduledThreadPoolExecutor(1);
            }
            scheduledThreadPoolExecutor = backgroundExecutor;
        }
        return scheduledThreadPoolExecutor;
    }

    /* access modifiers changed from: private */
    public void setCurrentRequestState(RequestState requestState) {
        this.currentRequestState = requestState;
        this.confirmationCode.setText(requestState.a());
        this.confirmationCode.setVisibility(0);
        this.progressBar.setVisibility(8);
        this.codeExpiredFuture = getBackgroundExecutor().schedule(new Runnable() {
            public void run() {
                DeviceShareDialogFragment.this.dialog.dismiss();
            }
        }, requestState.b(), TimeUnit.SECONDS);
    }
}
