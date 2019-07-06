package com.gu.toolargetool;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/* compiled from: Formatter */
public abstract class b {
    public static b c = new b() {
        public String a(Activity activity, Bundle bundle) {
            StringBuilder sb = new StringBuilder();
            sb.append(activity.getClass().getSimpleName());
            sb.append(".onSaveInstanceState wrote: ");
            sb.append(TooLargeTool.bundleBreakdown(bundle));
            return sb.toString();
        }

        public String a(FragmentManager fragmentManager, Fragment fragment, Bundle bundle) {
            StringBuilder sb = new StringBuilder();
            sb.append(fragment.getClass().getSimpleName());
            sb.append(".onSaveInstanceState wrote: ");
            sb.append(TooLargeTool.bundleBreakdown(bundle));
            String sb2 = sb.toString();
            Bundle arguments = fragment.getArguments();
            if (arguments == null) {
                return sb2;
            }
            StringBuilder sb3 = new StringBuilder();
            sb3.append(sb2);
            sb3.append("\n* fragment arguments = ");
            sb3.append(TooLargeTool.bundleBreakdown(arguments));
            return sb3.toString();
        }
    };

    public abstract String a(Activity activity, Bundle bundle);

    public abstract String a(FragmentManager fragmentManager, Fragment fragment, Bundle bundle);
}
