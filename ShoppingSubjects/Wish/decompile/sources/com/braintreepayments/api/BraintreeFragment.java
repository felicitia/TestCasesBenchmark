package com.braintreepayments.api;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.braintreepayments.api.exceptions.BraintreeException;
import com.braintreepayments.api.exceptions.ConfigurationException;
import com.braintreepayments.api.interfaces.BraintreeCancelListener;
import com.braintreepayments.api.interfaces.BraintreeErrorListener;
import com.braintreepayments.api.interfaces.BraintreeListener;
import com.braintreepayments.api.interfaces.BraintreeResponseListener;
import com.braintreepayments.api.interfaces.ConfigurationListener;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCreatedListener;
import com.braintreepayments.api.interfaces.PaymentMethodNoncesUpdatedListener;
import com.braintreepayments.api.interfaces.QueuedCallback;
import com.braintreepayments.api.interfaces.UnionPayListener;
import com.braintreepayments.api.internal.AnalyticsDatabase;
import com.braintreepayments.api.internal.AnalyticsEvent;
import com.braintreepayments.api.internal.AnalyticsIntentService;
import com.braintreepayments.api.internal.AnalyticsSender;
import com.braintreepayments.api.internal.BraintreeHttpClient;
import com.braintreepayments.api.models.AndroidPayCardNonce;
import com.braintreepayments.api.models.Authorization;
import com.braintreepayments.api.models.Configuration;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.braintreepayments.api.models.TokenizationKey;
import com.braintreepayments.browserswitch.BrowserSwitchFragment;
import com.braintreepayments.browserswitch.BrowserSwitchFragment.BrowserSwitchResult;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import org.json.JSONException;

public class BraintreeFragment extends BrowserSwitchFragment {
    /* access modifiers changed from: private */
    public AnalyticsDatabase mAnalyticsDatabase;
    private Authorization mAuthorization;
    private final List<PaymentMethodNonce> mCachedPaymentMethodNonces = new ArrayList();
    private final Queue<QueuedCallback> mCallbackQueue = new ArrayDeque();
    /* access modifiers changed from: private */
    public BraintreeCancelListener mCancelListener;
    private Configuration mConfiguration;
    /* access modifiers changed from: private */
    public BraintreeResponseListener<Exception> mConfigurationErrorListener;
    /* access modifiers changed from: private */
    public ConfigurationListener mConfigurationListener;
    private int mConfigurationRequestAttempts = 0;
    private CrashReporter mCrashReporter;
    /* access modifiers changed from: private */
    public BraintreeErrorListener mErrorListener;
    protected GoogleApiClient mGoogleApiClient;
    private boolean mHasFetchedPaymentMethodNonces = false;
    protected BraintreeHttpClient mHttpClient;
    private String mIntegrationType;
    private boolean mNewActivityNeedsConfiguration;
    /* access modifiers changed from: private */
    public PaymentMethodNonceCreatedListener mPaymentMethodNonceCreatedListener;
    private PaymentMethodNoncesUpdatedListener mPaymentMethodNoncesUpdatedListener;
    private String mSessionId;
    private UnionPayListener mUnionPayListener;

    /* JADX WARNING: Can't wrap try/catch for region: R(5:13|14|15|16|(2:17|18)) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.braintreepayments.api.BraintreeFragment newInstance(android.app.Activity r4, java.lang.String r5) throws com.braintreepayments.api.exceptions.InvalidArgumentException {
        /*
            if (r4 != 0) goto L_0x000a
            com.braintreepayments.api.exceptions.InvalidArgumentException r4 = new com.braintreepayments.api.exceptions.InvalidArgumentException
            java.lang.String r5 = "Activity is null"
            r4.<init>(r5)
            throw r4
        L_0x000a:
            android.app.FragmentManager r0 = r4.getFragmentManager()
            java.lang.String r1 = "com.braintreepayments.api.BraintreeFragment"
            android.app.Fragment r1 = r0.findFragmentByTag(r1)
            com.braintreepayments.api.BraintreeFragment r1 = (com.braintreepayments.api.BraintreeFragment) r1
            if (r1 != 0) goto L_0x0089
            com.braintreepayments.api.BraintreeFragment r1 = new com.braintreepayments.api.BraintreeFragment
            r1.<init>()
            android.os.Bundle r2 = new android.os.Bundle
            r2.<init>()
            com.braintreepayments.api.models.Authorization r5 = com.braintreepayments.api.models.Authorization.fromString(r5)     // Catch:{ InvalidArgumentException -> 0x0081 }
            java.lang.String r3 = "com.braintreepayments.api.EXTRA_AUTHORIZATION_TOKEN"
            r2.putParcelable(r3, r5)     // Catch:{ InvalidArgumentException -> 0x0081 }
            java.lang.String r5 = "com.braintreepayments.api.EXTRA_SESSION_ID"
            java.lang.String r3 = com.braintreepayments.api.internal.UUIDHelper.getFormattedUUID()
            r2.putString(r5, r3)
            java.lang.String r5 = "com.braintreepayments.api.EXTRA_INTEGRATION_TYPE"
            java.lang.String r3 = com.braintreepayments.api.internal.IntegrationType.get(r4)
            r2.putString(r5, r3)
            r1.setArguments(r2)
            int r5 = android.os.Build.VERSION.SDK_INT     // Catch:{ IllegalStateException -> 0x0076 }
            r2 = 24
            if (r5 < r2) goto L_0x0065
            android.app.FragmentTransaction r5 = r0.beginTransaction()     // Catch:{ IllegalStateException | NullPointerException -> 0x0054 }
            java.lang.String r2 = "com.braintreepayments.api.BraintreeFragment"
            android.app.FragmentTransaction r5 = r5.add(r1, r2)     // Catch:{ IllegalStateException | NullPointerException -> 0x0054 }
            r5.commitNow()     // Catch:{ IllegalStateException | NullPointerException -> 0x0054 }
            goto L_0x0089
        L_0x0054:
            android.app.FragmentTransaction r5 = r0.beginTransaction()     // Catch:{ IllegalStateException -> 0x0076 }
            java.lang.String r2 = "com.braintreepayments.api.BraintreeFragment"
            android.app.FragmentTransaction r5 = r5.add(r1, r2)     // Catch:{ IllegalStateException -> 0x0076 }
            r5.commit()     // Catch:{ IllegalStateException -> 0x0076 }
            r0.executePendingTransactions()     // Catch:{ IllegalStateException -> 0x0089 }
            goto L_0x0089
        L_0x0065:
            android.app.FragmentTransaction r5 = r0.beginTransaction()     // Catch:{ IllegalStateException -> 0x0076 }
            java.lang.String r2 = "com.braintreepayments.api.BraintreeFragment"
            android.app.FragmentTransaction r5 = r5.add(r1, r2)     // Catch:{ IllegalStateException -> 0x0076 }
            r5.commit()     // Catch:{ IllegalStateException -> 0x0076 }
            r0.executePendingTransactions()     // Catch:{ IllegalStateException -> 0x0089 }
            goto L_0x0089
        L_0x0076:
            r4 = move-exception
            com.braintreepayments.api.exceptions.InvalidArgumentException r5 = new com.braintreepayments.api.exceptions.InvalidArgumentException
            java.lang.String r4 = r4.getMessage()
            r5.<init>(r4)
            throw r5
        L_0x0081:
            com.braintreepayments.api.exceptions.InvalidArgumentException r4 = new com.braintreepayments.api.exceptions.InvalidArgumentException
            java.lang.String r5 = "Tokenization Key or client token was invalid."
            r4.<init>(r5)
            throw r4
        L_0x0089:
            android.content.Context r4 = r4.getApplicationContext()
            r1.mContext = r4
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.braintreepayments.api.BraintreeFragment.newInstance(android.app.Activity, java.lang.String):com.braintreepayments.api.BraintreeFragment");
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
        if (this.mContext == null) {
            this.mContext = getActivity().getApplicationContext();
        }
        this.mNewActivityNeedsConfiguration = false;
        this.mCrashReporter = CrashReporter.setup(this);
        this.mSessionId = getArguments().getString("com.braintreepayments.api.EXTRA_SESSION_ID");
        this.mIntegrationType = getArguments().getString("com.braintreepayments.api.EXTRA_INTEGRATION_TYPE");
        this.mAuthorization = (Authorization) getArguments().getParcelable("com.braintreepayments.api.EXTRA_AUTHORIZATION_TOKEN");
        this.mAnalyticsDatabase = AnalyticsDatabase.getInstance(getApplicationContext());
        if (this.mHttpClient == null) {
            this.mHttpClient = new BraintreeHttpClient(this.mAuthorization);
        }
        if (bundle != null) {
            ArrayList parcelableArrayList = bundle.getParcelableArrayList("com.braintreepayments.api.EXTRA_CACHED_PAYMENT_METHOD_NONCES");
            if (parcelableArrayList != null) {
                this.mCachedPaymentMethodNonces.addAll(parcelableArrayList);
            }
            this.mHasFetchedPaymentMethodNonces = bundle.getBoolean("com.braintreepayments.api.EXTRA_FETCHED_PAYMENT_METHOD_NONCES");
            try {
                setConfiguration(Configuration.fromJson(bundle.getString("com.braintreepayments.api.EXTRA_CONFIGURATION")));
            } catch (JSONException unused) {
            }
        } else if (this.mAuthorization instanceof TokenizationKey) {
            sendAnalyticsEvent("started.client-key");
        } else {
            sendAnalyticsEvent("started.client-token");
        }
        fetchConfiguration();
    }

    @TargetApi(23)
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttach(getActivity());
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mNewActivityNeedsConfiguration = true;
    }

    public void onResume() {
        super.onResume();
        if (getActivity() instanceof BraintreeListener) {
            addListener((BraintreeListener) getActivity());
            if (this.mNewActivityNeedsConfiguration && getConfiguration() != null) {
                this.mNewActivityNeedsConfiguration = false;
                postConfigurationCallback();
            }
        }
        flushCallbacks();
        if (this.mGoogleApiClient != null && !this.mGoogleApiClient.isConnected() && !this.mGoogleApiClient.isConnecting()) {
            this.mGoogleApiClient.connect();
        }
    }

    public void onPause() {
        super.onPause();
        if (getActivity() instanceof BraintreeListener) {
            removeListener((BraintreeListener) getActivity());
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelableArrayList("com.braintreepayments.api.EXTRA_CACHED_PAYMENT_METHOD_NONCES", (ArrayList) this.mCachedPaymentMethodNonces);
        bundle.putBoolean("com.braintreepayments.api.EXTRA_FETCHED_PAYMENT_METHOD_NONCES", this.mHasFetchedPaymentMethodNonces);
        if (this.mConfiguration != null) {
            bundle.putString("com.braintreepayments.api.EXTRA_CONFIGURATION", this.mConfiguration.toJson());
        }
    }

    public void onStop() {
        super.onStop();
        if (this.mGoogleApiClient != null) {
            this.mGoogleApiClient.disconnect();
        }
        flushAnalyticsEvents();
    }

    public void onDetach() {
        super.onDetach();
        if (this.mGoogleApiClient != null) {
            this.mGoogleApiClient.disconnect();
            this.mGoogleApiClient = null;
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.mCrashReporter.tearDown();
    }

    public void startActivityForResult(Intent intent, int i) {
        if (!isAdded()) {
            postCallback((Exception) new BraintreeException("BraintreeFragment is not attached to an Activity. Please ensure it is attached and try again."));
        } else {
            super.startActivityForResult(intent, i);
        }
    }

    public String getReturnUrlScheme() {
        StringBuilder sb = new StringBuilder();
        sb.append(getApplicationContext().getPackageName().toLowerCase().replace("_", ""));
        sb.append(".braintree");
        return sb.toString();
    }

    public void onBrowserSwitchResult(int i, BrowserSwitchResult browserSwitchResult, Uri uri) {
        int i2 = browserSwitchResult == BrowserSwitchResult.OK ? -1 : browserSwitchResult == BrowserSwitchResult.CANCELED ? 0 : 1;
        onActivityResult(i, i2, new Intent().setData(uri));
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        switch (i) {
            case 13487:
                ThreeDSecure.onActivityResult(this, i2, intent);
                break;
            case 13488:
                Venmo.onActivityResult(this, i2, intent);
                break;
            case 13489:
                AndroidPay.onActivityResult(this, i2, intent);
                break;
            default:
                switch (i) {
                    case 13591:
                        PayPal.onActivityResult(this, i2, intent);
                        break;
                    case 13592:
                        VisaCheckout.onActivityResult(this, i2, intent);
                        break;
                    case 13593:
                        GooglePayment.onActivityResult(this, i2, intent);
                        break;
                }
        }
        if (i2 == 0) {
            postCancelCallback(i);
        }
    }

    public <T extends BraintreeListener> void addListener(T t) {
        if (t instanceof ConfigurationListener) {
            this.mConfigurationListener = (ConfigurationListener) t;
        }
        if (t instanceof BraintreeCancelListener) {
            this.mCancelListener = (BraintreeCancelListener) t;
        }
        if (t instanceof PaymentMethodNoncesUpdatedListener) {
            this.mPaymentMethodNoncesUpdatedListener = (PaymentMethodNoncesUpdatedListener) t;
        }
        if (t instanceof PaymentMethodNonceCreatedListener) {
            this.mPaymentMethodNonceCreatedListener = (PaymentMethodNonceCreatedListener) t;
        }
        if (t instanceof BraintreeErrorListener) {
            this.mErrorListener = (BraintreeErrorListener) t;
        }
        if (t instanceof UnionPayListener) {
            this.mUnionPayListener = (UnionPayListener) t;
        }
        flushCallbacks();
    }

    public <T extends BraintreeListener> void removeListener(T t) {
        if (t instanceof ConfigurationListener) {
            this.mConfigurationListener = null;
        }
        if (t instanceof BraintreeCancelListener) {
            this.mCancelListener = null;
        }
        if (t instanceof PaymentMethodNoncesUpdatedListener) {
            this.mPaymentMethodNoncesUpdatedListener = null;
        }
        if (t instanceof PaymentMethodNonceCreatedListener) {
            this.mPaymentMethodNonceCreatedListener = null;
        }
        if (t instanceof BraintreeErrorListener) {
            this.mErrorListener = null;
        }
        if (t instanceof UnionPayListener) {
            this.mUnionPayListener = null;
        }
    }

    public void sendAnalyticsEvent(String str) {
        final AnalyticsEvent analyticsEvent = new AnalyticsEvent(this.mContext, getSessionId(), this.mIntegrationType, str);
        waitForConfiguration(new ConfigurationListener() {
            public void onConfigurationFetched(Configuration configuration) {
                if (configuration.getAnalytics().isEnabled()) {
                    BraintreeFragment.this.mAnalyticsDatabase.addEvent(analyticsEvent);
                }
            }
        });
    }

    private void flushAnalyticsEvents() {
        if (getConfiguration() != null && getConfiguration().toJson() != null && getConfiguration().getAnalytics().isEnabled()) {
            try {
                getApplicationContext().startService(new Intent(this.mContext, AnalyticsIntentService.class).putExtra("com.braintreepayments.api.internal.AnalyticsIntentService.EXTRA_AUTHORIZATION", getAuthorization().toString()).putExtra("com.braintreepayments.api.internal.AnalyticsIntentService.EXTRA_CONFIGURATION", getConfiguration().toJson()));
            } catch (RuntimeException unused) {
                AnalyticsSender.send(getApplicationContext(), this.mAuthorization, getHttpClient(), getConfiguration().getAnalytics().getUrl(), false);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void postConfigurationCallback() {
        postOrQueueCallback(new QueuedCallback() {
            public boolean shouldRun() {
                return BraintreeFragment.this.mConfigurationListener != null;
            }

            public void run() {
                BraintreeFragment.this.mConfigurationListener.onConfigurationFetched(BraintreeFragment.this.getConfiguration());
            }
        });
    }

    /* access modifiers changed from: protected */
    public void postCancelCallback(final int i) {
        postOrQueueCallback(new QueuedCallback() {
            public boolean shouldRun() {
                return BraintreeFragment.this.mCancelListener != null;
            }

            public void run() {
                BraintreeFragment.this.mCancelListener.onCancel(i);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void postCallback(final PaymentMethodNonce paymentMethodNonce) {
        if (paymentMethodNonce instanceof AndroidPayCardNonce) {
            Iterator it = new ArrayList(this.mCachedPaymentMethodNonces).iterator();
            while (it.hasNext()) {
                PaymentMethodNonce paymentMethodNonce2 = (PaymentMethodNonce) it.next();
                if (paymentMethodNonce2 instanceof AndroidPayCardNonce) {
                    this.mCachedPaymentMethodNonces.remove(paymentMethodNonce2);
                }
            }
        }
        this.mCachedPaymentMethodNonces.add(0, paymentMethodNonce);
        postOrQueueCallback(new QueuedCallback() {
            public boolean shouldRun() {
                return BraintreeFragment.this.mPaymentMethodNonceCreatedListener != null;
            }

            public void run() {
                BraintreeFragment.this.mPaymentMethodNonceCreatedListener.onPaymentMethodNonceCreated(paymentMethodNonce);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void postCallback(final Exception exc) {
        postOrQueueCallback(new QueuedCallback() {
            public boolean shouldRun() {
                return BraintreeFragment.this.mErrorListener != null;
            }

            public void run() {
                BraintreeFragment.this.mErrorListener.onError(exc);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void postOrQueueCallback(QueuedCallback queuedCallback) {
        if (!queuedCallback.shouldRun()) {
            this.mCallbackQueue.add(queuedCallback);
        } else {
            queuedCallback.run();
        }
    }

    /* access modifiers changed from: protected */
    public void flushCallbacks() {
        ArrayDeque<QueuedCallback> arrayDeque = new ArrayDeque<>();
        arrayDeque.addAll(this.mCallbackQueue);
        for (QueuedCallback queuedCallback : arrayDeque) {
            if (queuedCallback.shouldRun()) {
                queuedCallback.run();
                this.mCallbackQueue.remove(queuedCallback);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void fetchConfiguration() {
        if (getConfiguration() == null && !ConfigurationManager.isFetchingConfiguration() && this.mAuthorization != null && this.mHttpClient != null) {
            if (this.mConfigurationRequestAttempts >= 3) {
                postCallback((Exception) new ConfigurationException("Configuration retry limit has been exceeded. Create a new BraintreeFragment and try again."));
                return;
            }
            this.mConfigurationRequestAttempts++;
            ConfigurationManager.getConfiguration(this, new ConfigurationListener() {
                public void onConfigurationFetched(Configuration configuration) {
                    BraintreeFragment.this.setConfiguration(configuration);
                    BraintreeFragment.this.postConfigurationCallback();
                    BraintreeFragment.this.flushCallbacks();
                }
            }, new BraintreeResponseListener<Exception>() {
                public void onResponse(Exception exc) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Request for configuration has failed: ");
                    sb.append(exc.getMessage());
                    sb.append(". ");
                    sb.append("Future requests will retry up to 3 times");
                    final ConfigurationException configurationException = new ConfigurationException(sb.toString(), exc);
                    BraintreeFragment.this.postCallback((Exception) configurationException);
                    BraintreeFragment.this.postOrQueueCallback(new QueuedCallback() {
                        public boolean shouldRun() {
                            return BraintreeFragment.this.mConfigurationErrorListener != null;
                        }

                        public void run() {
                            BraintreeFragment.this.mConfigurationErrorListener.onResponse(configurationException);
                        }
                    });
                    BraintreeFragment.this.flushCallbacks();
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void waitForConfiguration(final ConfigurationListener configurationListener) {
        fetchConfiguration();
        postOrQueueCallback(new QueuedCallback() {
            public boolean shouldRun() {
                return BraintreeFragment.this.getConfiguration() != null && BraintreeFragment.this.isAdded();
            }

            public void run() {
                configurationListener.onConfigurationFetched(BraintreeFragment.this.getConfiguration());
            }
        });
    }

    /* access modifiers changed from: protected */
    public Authorization getAuthorization() {
        return this.mAuthorization;
    }

    /* access modifiers changed from: protected */
    public Context getApplicationContext() {
        return this.mContext;
    }

    /* access modifiers changed from: protected */
    public Configuration getConfiguration() {
        return this.mConfiguration;
    }

    /* access modifiers changed from: protected */
    public void setConfiguration(Configuration configuration) {
        this.mConfiguration = configuration;
        getHttpClient().setBaseUrl(configuration.getClientApiUrl());
    }

    /* access modifiers changed from: protected */
    public BraintreeHttpClient getHttpClient() {
        return this.mHttpClient;
    }

    /* access modifiers changed from: protected */
    public String getSessionId() {
        return this.mSessionId;
    }

    /* access modifiers changed from: protected */
    public String getIntegrationType() {
        return this.mIntegrationType;
    }
}
