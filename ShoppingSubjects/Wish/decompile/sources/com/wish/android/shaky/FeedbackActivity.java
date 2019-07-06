package com.wish.android.shaky;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import com.threatmetrix.TrustDefender.StrongAuth;

public class FeedbackActivity extends AppCompatActivity {
    private int feedbackType;
    /* access modifiers changed from: private */
    public Uri imageUri;
    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if ("ActionFeedbackTypeSelected".equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("ExtraFeedbackType", 2);
                FeedbackActivity.this.setFeedbackType(intExtra);
                FeedbackActivity.this.startFormFragment(intExtra);
                if (FeedbackActivity.this.imageUri != null && intExtra == 0) {
                    FeedbackActivity.this.startDrawFragment();
                }
            } else if ("ActionEditImage".equals(intent.getAction())) {
                FeedbackActivity.this.startDrawFragment();
            } else if ("ActionDrawingComplete".equals(intent.getAction())) {
                FeedbackActivity.this.onBackPressed();
            } else if ("ActionSubmitFeedback".equals(intent.getAction())) {
                FeedbackActivity.this.submitFeedbackIntent(intent.getStringExtra("ExtraUserMessage"));
            }
        }
    };
    private Bundle userData;

    public static Intent newIntent(Context context, Uri uri, Bundle bundle) {
        Intent intent = new Intent(context, FeedbackActivity.class);
        intent.putExtra("screenshotUri", uri);
        intent.putExtra("userData", bundle);
        return intent;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.shaky_feedback);
        this.imageUri = (Uri) getIntent().getParcelableExtra("screenshotUri");
        this.userData = getIntent().getBundleExtra("userData");
        if (bundle == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.shaky_fragment_container, (Fragment) new SelectFragment()).commit();
        }
    }

    /* access modifiers changed from: protected */
    public void onResumeFragments() {
        super.onResumeFragments();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("ActionFeedbackTypeSelected");
        intentFilter.addAction("ActionSubmitFeedback");
        intentFilter.addAction("ActionEditImage");
        intentFilter.addAction("ActionDrawingComplete");
        LocalBroadcastManager.getInstance(this).registerReceiver(this.receiver, intentFilter);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.receiver);
    }

    private void changeToFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().setTransition(4097).replace(R.id.shaky_fragment_container, fragment).addToBackStack(null).commit();
    }

    /* access modifiers changed from: private */
    public void startFormFragment(int i) {
        changeToFragment(FormFragment.newInstance(getString(getTitleResId(i)), getString(getHintResId(i)), this.imageUri));
    }

    /* access modifiers changed from: private */
    public void startDrawFragment() {
        changeToFragment(DrawFragment.newInstance(this.imageUri));
    }

    /* access modifiers changed from: private */
    public void setFeedbackType(int i) {
        this.feedbackType = i;
    }

    /* access modifiers changed from: private */
    public void submitFeedbackIntent(String str) {
        Intent intent = new Intent("EndFeedbackFlow");
        intent.putExtra("screenshotUri", this.imageUri);
        intent.putExtra(StrongAuth.AUTH_TITLE, getString(getTitleResId(this.feedbackType)));
        intent.putExtra("message", str);
        intent.putExtra("userData", this.userData);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        finish();
    }

    private int getTitleResId(int i) {
        switch (i) {
            case 0:
                return R.string.shaky_bug_title;
            case 1:
                return R.string.shaky_feature_title;
            default:
                return R.string.shaky_general_title;
        }
    }

    private int getHintResId(int i) {
        switch (i) {
            case 0:
                return R.string.shaky_bug_hint;
            case 1:
                return R.string.shaky_feature_hint;
            default:
                return R.string.shaky_general_hint;
        }
    }
}
