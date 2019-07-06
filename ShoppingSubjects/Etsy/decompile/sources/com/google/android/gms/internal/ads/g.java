package com.google.android.gms.internal.ads;

import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.os.Environment;
import com.etsy.android.lib.models.editable.EditableListing;
import com.google.android.gms.ads.internal.ao;

final class g implements OnClickListener {
    private final /* synthetic */ String a;
    private final /* synthetic */ String b;
    private final /* synthetic */ f c;

    g(f fVar, String str, String str2) {
        this.c = fVar;
        this.a = str;
        this.b = str2;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        DownloadManager downloadManager = (DownloadManager) this.c.b.getSystemService(EditableListing.LISTING_TYPE_DOWNLOAD);
        try {
            String str = this.a;
            String str2 = this.b;
            Request request = new Request(Uri.parse(str));
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, str2);
            ao.g().a(request);
            downloadManager.enqueue(request);
        } catch (IllegalStateException unused) {
            this.c.a("Could not store picture.");
        }
    }
}
