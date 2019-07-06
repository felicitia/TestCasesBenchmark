package io.branch.referral;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import io.branch.referral.Branch.c;
import io.branch.referral.Branch.j;
import io.branch.referral.Branch.l;
import io.branch.referral.SharingHelper.SHARE_WITH;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class ShareLinkManager {
    /* access modifiers changed from: private */
    public static int k = 100;
    AnimatedDialog a;
    c b;
    j c;
    Context d;
    final int e = 5;
    final int f = 100;
    /* access modifiers changed from: private */
    public List<ResolveInfo> g;
    private Intent h;
    /* access modifiers changed from: private */
    public final int i = Color.argb(60, 17, 4, 56);
    /* access modifiers changed from: private */
    public final int j = Color.argb(20, 17, 4, 56);
    /* access modifiers changed from: private */
    public boolean l = false;
    private int m = -1;
    /* access modifiers changed from: private */
    public int n = 50;
    /* access modifiers changed from: private */
    public l o;
    private List<String> p = new ArrayList();
    private List<String> q = new ArrayList();

    private class CopyLinkItem extends ResolveInfo {
        private CopyLinkItem() {
        }

        public CharSequence loadLabel(PackageManager packageManager) {
            return ShareLinkManager.this.o.n();
        }

        public Drawable loadIcon(PackageManager packageManager) {
            return ShareLinkManager.this.o.m();
        }
    }

    private class MoreShareItem extends ResolveInfo {
        private MoreShareItem() {
        }

        public CharSequence loadLabel(PackageManager packageManager) {
            return ShareLinkManager.this.o.l();
        }

        public Drawable loadIcon(PackageManager packageManager) {
            return ShareLinkManager.this.o.k();
        }
    }

    private class a extends BaseAdapter {
        public int a;

        public long getItemId(int i) {
            return (long) i;
        }

        private a() {
            this.a = -1;
        }

        public int getCount() {
            return ShareLinkManager.this.g.size();
        }

        public Object getItem(int i) {
            return ShareLinkManager.this.g.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            b bVar;
            if (view == null) {
                bVar = new b(ShareLinkManager.this.d);
            } else {
                bVar = (b) view;
            }
            ResolveInfo resolveInfo = (ResolveInfo) ShareLinkManager.this.g.get(i);
            bVar.a(resolveInfo.loadLabel(ShareLinkManager.this.d.getPackageManager()).toString(), resolveInfo.loadIcon(ShareLinkManager.this.d.getPackageManager()), i == this.a);
            bVar.setTag(resolveInfo);
            bVar.setClickable(false);
            return bVar;
        }

        public boolean isEnabled(int i) {
            return this.a < 0;
        }
    }

    private class b extends TextView {
        Context a;
        int b;

        public b(Context context) {
            super(context);
            this.a = context;
            setPadding(100, 5, 5, 5);
            setGravity(8388627);
            setMinWidth(this.a.getResources().getDisplayMetrics().widthPixels);
            this.b = ShareLinkManager.this.n != 0 ? h.b(context, ShareLinkManager.this.n) : 0;
        }

        public void a(String str, Drawable drawable, boolean z) {
            StringBuilder sb = new StringBuilder();
            sb.append("\t");
            sb.append(str);
            setText(sb.toString());
            setTag(str);
            if (drawable == null) {
                setTextAppearance(this.a, 16973890);
                setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            } else {
                if (this.b != 0) {
                    drawable.setBounds(0, 0, this.b, this.b);
                    setCompoundDrawables(drawable, null, null, null);
                } else {
                    setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
                }
                setTextAppearance(this.a, 16973892);
                ShareLinkManager.k = Math.max(ShareLinkManager.k, drawable.getIntrinsicHeight() + 5);
            }
            setMinHeight(ShareLinkManager.k);
            setTextColor(this.a.getResources().getColor(17170444));
            if (z) {
                setBackgroundColor(ShareLinkManager.this.i);
            } else {
                setBackgroundColor(ShareLinkManager.this.j);
            }
        }
    }

    ShareLinkManager() {
    }

    public Dialog a(l lVar) {
        this.o = lVar;
        this.d = lVar.b();
        this.b = lVar.h();
        this.c = lVar.i();
        this.h = new Intent("android.intent.action.SEND");
        this.h.setType("text/plain");
        this.m = lVar.v();
        this.p = lVar.e();
        this.q = lVar.d();
        this.n = lVar.w();
        try {
            a((List<SHARE_WITH>) lVar.c());
        } catch (Exception e2) {
            com.google.a.a.a.a.a.a.a(e2);
            if (this.b != null) {
                this.b.a(null, null, new c("Trouble sharing link", -110));
            } else {
                Log.i("BranchSDK", "Unable create share options. Couldn't find applications on device to share the link.");
            }
        }
        return this.a;
    }

    public void a(boolean z) {
        if (this.a != null && this.a.isShowing()) {
            if (z) {
                this.a.cancel();
            } else {
                this.a.dismiss();
            }
        }
    }

    private void a(List<SHARE_WITH> list) {
        final ListView listView;
        PackageManager packageManager = this.d.getPackageManager();
        ArrayList arrayList = new ArrayList();
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(this.h, 65536);
        List<ResolveInfo> arrayList2 = new ArrayList<>();
        final ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList(list);
        Iterator it = queryIntentActivities.iterator();
        while (true) {
            SHARE_WITH share_with = null;
            if (!it.hasNext()) {
                break;
            }
            ResolveInfo resolveInfo = (ResolveInfo) it.next();
            String str = resolveInfo.activityInfo.packageName;
            Iterator it2 = arrayList4.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                SHARE_WITH share_with2 = (SHARE_WITH) it2.next();
                if (resolveInfo.activityInfo != null && str.toLowerCase().contains(share_with2.toString().toLowerCase())) {
                    share_with = share_with2;
                    break;
                }
            }
            if (share_with != null) {
                arrayList.add(resolveInfo);
                list.remove(share_with);
            }
        }
        queryIntentActivities.removeAll(arrayList);
        queryIntentActivities.addAll(0, arrayList);
        if (this.p.size() > 0) {
            for (ResolveInfo resolveInfo2 : queryIntentActivities) {
                if (this.p.contains(resolveInfo2.activityInfo.packageName)) {
                    arrayList2.add(resolveInfo2);
                }
            }
        } else {
            arrayList2 = queryIntentActivities;
        }
        for (ResolveInfo resolveInfo3 : arrayList2) {
            if (!this.q.contains(resolveInfo3.activityInfo.packageName)) {
                arrayList3.add(resolveInfo3);
            }
        }
        for (ResolveInfo resolveInfo4 : queryIntentActivities) {
            Iterator it3 = arrayList4.iterator();
            while (it3.hasNext()) {
                if (((SHARE_WITH) it3.next()).toString().equalsIgnoreCase(resolveInfo4.activityInfo.packageName)) {
                    arrayList3.add(resolveInfo4);
                }
            }
        }
        arrayList3.add(new CopyLinkItem());
        queryIntentActivities.add(new CopyLinkItem());
        arrayList.add(new CopyLinkItem());
        if (arrayList.size() > 1) {
            if (queryIntentActivities.size() > arrayList.size()) {
                arrayList.add(new MoreShareItem());
            }
            this.g = arrayList;
        } else {
            this.g = arrayList3;
        }
        final a aVar = new a();
        if (this.m <= 1 || VERSION.SDK_INT < 21) {
            listView = new ListView(this.d);
        } else {
            listView = new ListView(this.d, null, 0, this.m);
        }
        listView.setHorizontalFadingEdgeEnabled(false);
        listView.setBackgroundColor(-1);
        listView.setSelector(new ColorDrawable(0));
        if (this.o.u() != null) {
            listView.addHeaderView(this.o.u(), null, false);
        } else if (!TextUtils.isEmpty(this.o.t())) {
            TextView textView = new TextView(this.d);
            textView.setText(this.o.t());
            textView.setBackgroundColor(this.j);
            textView.setTextColor(this.j);
            textView.setTextAppearance(this.d, 16973892);
            textView.setTextColor(this.d.getResources().getColor(17170432));
            textView.setPadding(100, 5, 5, 5);
            listView.addHeaderView(textView, null, false);
        }
        listView.setAdapter(aVar);
        if (this.o.s() >= 0) {
            listView.setDividerHeight(this.o.s());
        } else if (this.o.q()) {
            listView.setDividerHeight(0);
        }
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (view.getTag() instanceof MoreShareItem) {
                    ShareLinkManager.this.g = arrayList3;
                    aVar.notifyDataSetChanged();
                    return;
                }
                if (ShareLinkManager.this.b != null) {
                    String str = "";
                    if (!(view.getTag() == null || ShareLinkManager.this.d == null || ((ResolveInfo) view.getTag()).loadLabel(ShareLinkManager.this.d.getPackageManager()) == null)) {
                        str = ((ResolveInfo) view.getTag()).loadLabel(ShareLinkManager.this.d.getPackageManager()).toString();
                    }
                    ShareLinkManager.this.o.p().b(((ResolveInfo) view.getTag()).loadLabel(ShareLinkManager.this.d.getPackageManager()).toString());
                    ShareLinkManager.this.b.a(str);
                }
                aVar.a = i - listView.getHeaderViewsCount();
                aVar.notifyDataSetChanged();
                ShareLinkManager.this.a((ResolveInfo) view.getTag());
                if (ShareLinkManager.this.a != null) {
                    ShareLinkManager.this.a.cancel();
                }
            }
        });
        if (this.o.r() > 0) {
            this.a = new AnimatedDialog(this.d, this.o.r());
        } else {
            this.a = new AnimatedDialog(this.d, this.o.q());
        }
        this.a.setContentView(listView);
        this.a.show();
        if (this.b != null) {
            this.b.a();
        }
        this.a.setOnDismissListener(new OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                if (ShareLinkManager.this.b != null) {
                    ShareLinkManager.this.b.b();
                    ShareLinkManager.this.b = null;
                }
                if (!ShareLinkManager.this.l) {
                    ShareLinkManager.this.d = null;
                    ShareLinkManager.this.o = null;
                }
                ShareLinkManager.this.a = null;
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(final ResolveInfo resolveInfo) {
        this.l = true;
        final String charSequence = resolveInfo.loadLabel(this.d.getPackageManager()).toString();
        this.o.p().a(new io.branch.referral.Branch.b() {
            public void a(String str, c cVar) {
                if (cVar == null) {
                    ShareLinkManager.this.a(resolveInfo, str, charSequence);
                    return;
                }
                String j = ShareLinkManager.this.o.j();
                if (j == null || j.trim().length() <= 0) {
                    if (ShareLinkManager.this.b != null) {
                        ShareLinkManager.this.b.a(str, charSequence, cVar);
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Unable to share link ");
                        sb.append(cVar.a());
                        Log.i("BranchSDK", sb.toString());
                    }
                    if (cVar.b() == -113 || cVar.b() == -117) {
                        ShareLinkManager.this.a(resolveInfo, str, charSequence);
                        return;
                    }
                    ShareLinkManager.this.a(false);
                    ShareLinkManager.this.l = false;
                    return;
                }
                ShareLinkManager.this.a(resolveInfo, j, charSequence);
            }
        }, true);
    }

    /* access modifiers changed from: private */
    public void a(ResolveInfo resolveInfo, String str, String str2) {
        if (this.b != null) {
            this.b.a(str, str2, null);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Shared link with ");
            sb.append(str2);
            Log.i("BranchSDK", sb.toString());
        }
        if (resolveInfo instanceof CopyLinkItem) {
            a(str, this.o.f());
            return;
        }
        this.h.setPackage(resolveInfo.activityInfo.packageName);
        String g2 = this.o.g();
        String f2 = this.o.f();
        if (this.c != null) {
            String a2 = this.c.a(str2);
            String b2 = this.c.b(str2);
            if (!TextUtils.isEmpty(a2)) {
                g2 = a2;
            }
            if (!TextUtils.isEmpty(b2)) {
                f2 = b2;
            }
        }
        if (g2 != null && g2.trim().length() > 0) {
            this.h.putExtra("android.intent.extra.SUBJECT", g2);
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(f2);
        sb2.append("\n");
        sb2.append(str);
        this.h.putExtra("android.intent.extra.TEXT", sb2.toString());
        this.d.startActivity(this.h);
    }

    @SuppressLint({"NewApi"})
    private void a(String str, String str2) {
        if (VERSION.SDK_INT < 11) {
            ((ClipboardManager) this.d.getSystemService("clipboard")).setText(str);
        } else {
            ((android.content.ClipboardManager) this.d.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(str2, str));
        }
        Toast.makeText(this.d, this.o.o(), 0).show();
    }
}
