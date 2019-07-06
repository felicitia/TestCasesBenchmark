package com.facebook.appevents.codeless;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.AccessibilityDelegate;
import com.facebook.FacebookException;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.appevents.codeless.internal.EventBinding;
import com.facebook.appevents.codeless.internal.EventBinding.ActionType;
import com.facebook.appevents.codeless.internal.c;
import com.facebook.appevents.internal.b;
import com.facebook.f;
import java.lang.ref.WeakReference;

class CodelessLoggingEventListener {
    /* access modifiers changed from: private */
    public static final String a = CodelessLoggingEventListener.class.getCanonicalName();

    public static class AutoLoggingAccessibilityDelegate extends AccessibilityDelegate {
        private int accessibilityEventType;
        private AccessibilityDelegate existingDelegate;
        private WeakReference<View> hostView;
        private EventBinding mapping;
        private WeakReference<View> rootView;

        public AutoLoggingAccessibilityDelegate(EventBinding eventBinding, View view, View view2) {
            if (eventBinding != null && view != null && view2 != null) {
                this.existingDelegate = c.e(view2);
                this.mapping = eventBinding;
                this.hostView = new WeakReference<>(view2);
                this.rootView = new WeakReference<>(view);
                ActionType d = eventBinding.d();
                switch (eventBinding.d()) {
                    case CLICK:
                        this.accessibilityEventType = 1;
                        break;
                    case SELECTED:
                        this.accessibilityEventType = 4;
                        break;
                    case TEXT_CHANGED:
                        this.accessibilityEventType = 16;
                        break;
                    default:
                        StringBuilder sb = new StringBuilder();
                        sb.append("Unsupported action type: ");
                        sb.append(d.toString());
                        throw new FacebookException(sb.toString());
                }
            }
        }

        public void sendAccessibilityEvent(View view, int i) {
            if (i == -1) {
                Log.e(CodelessLoggingEventListener.a, "Unsupported action type");
            }
            if (i == this.accessibilityEventType) {
                if (this.existingDelegate != null && !(this.existingDelegate instanceof AutoLoggingAccessibilityDelegate)) {
                    this.existingDelegate.sendAccessibilityEvent(view, i);
                }
                logEvent();
            }
        }

        private void logEvent() {
            final String c = this.mapping.c();
            final Bundle a = CodelessMatcher.a(this.mapping, (View) this.rootView.get(), (View) this.hostView.get());
            if (a.containsKey("_valueToSum")) {
                a.putDouble("_valueToSum", b.a(a.getString("_valueToSum")));
            }
            a.putString("_is_fb_codeless", "1");
            f.d().execute(new Runnable() {
                public void run() {
                    AppEventsLogger.a(f.f()).a(c, a);
                }
            });
        }
    }

    CodelessLoggingEventListener() {
    }

    public static AutoLoggingAccessibilityDelegate a(EventBinding eventBinding, View view, View view2) {
        return new AutoLoggingAccessibilityDelegate(eventBinding, view, view2);
    }
}
