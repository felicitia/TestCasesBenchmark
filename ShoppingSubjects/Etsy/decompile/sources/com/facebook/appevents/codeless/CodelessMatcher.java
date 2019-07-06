package com.facebook.appevents.codeless;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import com.facebook.FacebookException;
import com.facebook.appevents.codeless.CodelessLoggingEventListener.AutoLoggingAccessibilityDelegate;
import com.facebook.appevents.codeless.internal.EventBinding;
import com.facebook.appevents.codeless.internal.PathComponent;
import com.facebook.appevents.codeless.internal.PathComponent.MatchBitmaskType;
import com.facebook.appevents.codeless.internal.c;
import com.facebook.f;
import com.facebook.internal.j;
import com.facebook.internal.k;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CodelessMatcher {
    /* access modifiers changed from: private */
    public static final String a = CodelessMatcher.class.getCanonicalName();
    private final Handler b = new Handler(Looper.getMainLooper());
    private Set<Activity> c = new HashSet();
    private Set<ViewMatcher> d = new HashSet();
    private HashMap<String, String> e = new HashMap<>();

    protected static class ViewMatcher implements OnGlobalLayoutListener, OnScrollChangedListener, Runnable {
        private final String activityName;
        private HashMap<String, String> delegateMap;
        @Nullable
        private List<EventBinding> eventBindings;
        private final Handler handler;
        private WeakReference<View> rootView;

        public ViewMatcher(View view, Handler handler2, HashMap<String, String> hashMap, String str) {
            this.rootView = new WeakReference<>(view);
            this.handler = handler2;
            this.delegateMap = hashMap;
            this.activityName = str;
            this.handler.postDelayed(this, 200);
        }

        public void run() {
            j a = k.a(f.j());
            if (a != null && a.k()) {
                this.eventBindings = EventBinding.a(a.l());
                if (this.eventBindings != null) {
                    View view = (View) this.rootView.get();
                    if (view != null) {
                        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
                        if (viewTreeObserver.isAlive()) {
                            viewTreeObserver.addOnGlobalLayoutListener(this);
                            viewTreeObserver.addOnScrollChangedListener(this);
                        }
                        startMatch();
                    }
                }
            }
        }

        public void onGlobalLayout() {
            startMatch();
        }

        public void onScrollChanged() {
            startMatch();
        }

        private void startMatch() {
            if (this.eventBindings != null && this.rootView.get() != null) {
                for (int i = 0; i < this.eventBindings.size(); i++) {
                    findView((EventBinding) this.eventBindings.get(i), (View) this.rootView.get());
                }
            }
        }

        public void findView(EventBinding eventBinding, View view) {
            if (eventBinding != null && view != null) {
                if (TextUtils.isEmpty(eventBinding.e()) || eventBinding.e().equals(this.activityName)) {
                    List a = eventBinding.a();
                    if (a.size() <= 25) {
                        for (a attachListener : findViewByPath(eventBinding, view, a, 0, -1, this.activityName)) {
                            attachListener(attachListener, view, eventBinding);
                        }
                    }
                }
            }
        }

        public static List<a> findViewByPath(EventBinding eventBinding, View view, List<PathComponent> list, int i, int i2, String str) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(".");
            sb.append(String.valueOf(i2));
            String sb2 = sb.toString();
            ArrayList arrayList = new ArrayList();
            if (view == null) {
                return arrayList;
            }
            if (i >= list.size()) {
                arrayList.add(new a(view, sb2));
            } else {
                PathComponent pathComponent = (PathComponent) list.get(i);
                if (pathComponent.a.equals("..")) {
                    ViewParent parent = view.getParent();
                    if (parent instanceof ViewGroup) {
                        List findVisibleChildren = findVisibleChildren((ViewGroup) parent);
                        int size = findVisibleChildren.size();
                        for (int i3 = 0; i3 < size; i3++) {
                            arrayList.addAll(findViewByPath(eventBinding, (View) findVisibleChildren.get(i3), list, i + 1, i3, sb2));
                        }
                    }
                    return arrayList;
                } else if (pathComponent.a.equals(".")) {
                    arrayList.add(new a(view, sb2));
                    return arrayList;
                } else if (!isTheSameView(view, pathComponent, i2)) {
                    return arrayList;
                } else {
                    if (i == list.size() - 1) {
                        arrayList.add(new a(view, sb2));
                    }
                }
            }
            if (view instanceof ViewGroup) {
                List findVisibleChildren2 = findVisibleChildren((ViewGroup) view);
                int size2 = findVisibleChildren2.size();
                for (int i4 = 0; i4 < size2; i4++) {
                    arrayList.addAll(findViewByPath(eventBinding, (View) findVisibleChildren2.get(i4), list, i + 1, i4, sb2));
                }
            }
            return arrayList;
        }

        private static boolean isTheSameView(View view, PathComponent pathComponent, int i) {
            String str;
            String str2;
            if (pathComponent.b != -1 && i != pathComponent.b) {
                return false;
            }
            if (!view.getClass().getCanonicalName().equals(pathComponent.a)) {
                if (!pathComponent.a.matches(".*android\\..*")) {
                    return false;
                }
                String[] split = pathComponent.a.split("\\.");
                if (split.length <= 0) {
                    return false;
                }
                if (!view.getClass().getSimpleName().equals(split[split.length - 1])) {
                    return false;
                }
            }
            if ((pathComponent.h & MatchBitmaskType.ID.getValue()) > 0 && pathComponent.c != view.getId()) {
                return false;
            }
            if ((pathComponent.h & MatchBitmaskType.TEXT.getValue()) > 0 && !pathComponent.d.equals(c.c(view))) {
                return false;
            }
            if ((pathComponent.h & MatchBitmaskType.DESCRIPTION.getValue()) > 0) {
                String str3 = pathComponent.f;
                if (view.getContentDescription() == null) {
                    str2 = "";
                } else {
                    str2 = String.valueOf(view.getContentDescription());
                }
                if (!str3.equals(str2)) {
                    return false;
                }
            }
            if ((pathComponent.h & MatchBitmaskType.HINT.getValue()) > 0 && !pathComponent.g.equals(c.d(view))) {
                return false;
            }
            if ((pathComponent.h & MatchBitmaskType.TAG.getValue()) > 0) {
                String str4 = pathComponent.e;
                if (view.getTag() == null) {
                    str = "";
                } else {
                    str = String.valueOf(view.getTag());
                }
                if (!str4.equals(str)) {
                    return false;
                }
            }
            return true;
        }

        private static List<View> findVisibleChildren(ViewGroup viewGroup) {
            ArrayList arrayList = new ArrayList();
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getVisibility() == 0) {
                    arrayList.add(childAt);
                }
            }
            return arrayList;
        }

        private void attachListener(a aVar, View view, EventBinding eventBinding) {
            if (eventBinding != null) {
                try {
                    View a = aVar.a();
                    if (a != null) {
                        String b = aVar.b();
                        AccessibilityDelegate e = c.e(a);
                        if (!this.delegateMap.containsKey(b) && (e == null || !(e instanceof AutoLoggingAccessibilityDelegate))) {
                            a.setAccessibilityDelegate(CodelessLoggingEventListener.a(eventBinding, view, a));
                            this.delegateMap.put(b, eventBinding.c());
                        }
                    }
                } catch (FacebookException e2) {
                    Log.e(CodelessMatcher.a, "Failed to attach auto logging event listener.", e2);
                }
            }
        }
    }

    public static class a {
        private WeakReference<View> a;
        private String b;

        public a(View view, String str) {
            this.a = new WeakReference<>(view);
            this.b = str;
        }

        @Nullable
        public View a() {
            if (this.a == null) {
                return null;
            }
            return (View) this.a.get();
        }

        public String b() {
            return this.b;
        }
    }

    public void a(Activity activity) {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            throw new FacebookException("Can't add activity to CodelessMatcher on non-UI thread");
        }
        this.c.add(activity);
        this.e.clear();
        b();
    }

    public void b(Activity activity) {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            throw new FacebookException("Can't remove activity from CodelessMatcher on non-UI thread");
        }
        this.c.remove(activity);
        this.d.clear();
        this.e.clear();
    }

    public static Bundle a(EventBinding eventBinding, View view, View view2) {
        List list;
        Bundle bundle = new Bundle();
        if (eventBinding == null) {
            return bundle;
        }
        List<com.facebook.appevents.codeless.internal.a> b2 = eventBinding.b();
        if (b2 != null) {
            for (com.facebook.appevents.codeless.internal.a aVar : b2) {
                if (aVar.b == null || aVar.b.length() <= 0) {
                    if (aVar.c.size() > 0) {
                        if (aVar.d.equals("relative")) {
                            list = ViewMatcher.findViewByPath(eventBinding, view2, aVar.c, 0, -1, view2.getClass().getSimpleName());
                        } else {
                            list = ViewMatcher.findViewByPath(eventBinding, view, aVar.c, 0, -1, view.getClass().getSimpleName());
                        }
                        Iterator it = list.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            a aVar2 = (a) it.next();
                            if (aVar2.a() != null) {
                                String c2 = c.c(aVar2.a());
                                if (c2.length() > 0) {
                                    bundle.putString(aVar.a, c2);
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    bundle.putString(aVar.a, aVar.b);
                }
            }
        }
        return bundle;
    }

    private void b() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            c();
        } else {
            this.b.post(new Runnable() {
                public void run() {
                    CodelessMatcher.this.c();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        for (Activity activity : this.c) {
            this.d.add(new ViewMatcher(activity.getWindow().getDecorView().getRootView(), this.b, this.e, activity.getClass().getSimpleName()));
        }
    }
}
