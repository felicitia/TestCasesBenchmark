package com.wish.android.shaky;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import java.io.FileNotFoundException;

public class DrawFragment extends Fragment {
    private static final String TAG = "DrawFragment";
    private Uri imageUri;
    /* access modifiers changed from: private */
    public Paper paper;

    public static DrawFragment newInstance(Uri uri) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("imageUri", uri);
        DrawFragment drawFragment = new DrawFragment();
        drawFragment.setArguments(bundle);
        return drawFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.shaky_draw, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.paper = (Paper) view.findViewById(R.id.shaky_paper);
        this.imageUri = (Uri) getArguments().getParcelable("imageUri");
        if (this.imageUri != null) {
            try {
                this.paper.setImageBitmap(BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(this.imageUri)));
            } catch (FileNotFoundException e) {
                Log.e("Screenshot error", e.getMessage(), e);
            }
        }
        view.findViewById(R.id.shaky_button_clear).setOnClickListener(createClearClickListener());
        view.findViewById(R.id.shaky_button_save).setOnClickListener(createSaveClickListener());
        view.findViewById(R.id.shaky_button_brush).setOnClickListener(createBrushClickListener());
        view.findViewById(R.id.shaky_button_undo).setOnClickListener(createUndoClickListener());
        if (bundle == null) {
            Toast.makeText(getActivity(), getString(R.string.shaky_draw_hint), 0).show();
        }
    }

    private OnClickListener createClearClickListener() {
        return new OnClickListener() {
            public void onClick(View view) {
                DrawFragment.this.paper.clear();
            }
        };
    }

    private OnClickListener createBrushClickListener() {
        return new OnClickListener() {
            public void onClick(View view) {
                Button button = (Button) view;
                DrawFragment.this.paper.toggleBrush();
                button.setText(DrawFragment.this.getString(DrawFragment.this.paper.isThinBrush() ? R.string.shaky_draw_brush : R.string.shaky_draw_brush_white));
            }
        };
    }

    private OnClickListener createSaveClickListener() {
        return new OnClickListener() {
            public void onClick(View view) {
                Bitmap capture = DrawFragment.this.paper.capture();
                if (capture != null) {
                    DrawFragment.this.saveBitmap(capture);
                }
                LocalBroadcastManager.getInstance(DrawFragment.this.getActivity()).sendBroadcast(new Intent("ActionDrawingComplete"));
            }
        };
    }

    private OnClickListener createUndoClickListener() {
        return new OnClickListener() {
            public void onClick(View view) {
                DrawFragment.this.paper.undo();
            }
        };
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x002e A[SYNTHETIC, Splitter:B:17:0x002e] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x003d A[SYNTHETIC, Splitter:B:22:0x003d] */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void saveBitmap(android.graphics.Bitmap r4) {
        /*
            r3 = this;
            r0 = 0
            android.support.v4.app.FragmentActivity r1 = r3.getActivity()     // Catch:{ FileNotFoundException -> 0x0024 }
            android.content.ContentResolver r1 = r1.getContentResolver()     // Catch:{ FileNotFoundException -> 0x0024 }
            android.net.Uri r2 = r3.imageUri     // Catch:{ FileNotFoundException -> 0x0024 }
            java.io.OutputStream r1 = r1.openOutputStream(r2)     // Catch:{ FileNotFoundException -> 0x0024 }
            android.graphics.Bitmap$CompressFormat r0 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ FileNotFoundException -> 0x001f, all -> 0x001c }
            r2 = 100
            r4.compress(r0, r2, r1)     // Catch:{ FileNotFoundException -> 0x001f, all -> 0x001c }
            if (r1 == 0) goto L_0x003a
            r1.close()     // Catch:{ IOException -> 0x0032 }
            goto L_0x003a
        L_0x001c:
            r4 = move-exception
            r0 = r1
            goto L_0x003b
        L_0x001f:
            r4 = move-exception
            r0 = r1
            goto L_0x0025
        L_0x0022:
            r4 = move-exception
            goto L_0x003b
        L_0x0024:
            r4 = move-exception
        L_0x0025:
            java.lang.String r1 = TAG     // Catch:{ all -> 0x0022 }
            java.lang.String r2 = "Failed to write updated bitmap to disk"
            android.util.Log.e(r1, r2, r4)     // Catch:{ all -> 0x0022 }
            if (r0 == 0) goto L_0x003a
            r0.close()     // Catch:{ IOException -> 0x0032 }
            goto L_0x003a
        L_0x0032:
            r4 = move-exception
            java.lang.String r0 = TAG
            java.lang.String r1 = "Failed to close output stream"
            android.util.Log.e(r0, r1, r4)
        L_0x003a:
            return
        L_0x003b:
            if (r0 == 0) goto L_0x0049
            r0.close()     // Catch:{ IOException -> 0x0041 }
            goto L_0x0049
        L_0x0041:
            r0 = move-exception
            java.lang.String r1 = TAG
            java.lang.String r2 = "Failed to close output stream"
            android.util.Log.e(r1, r2, r0)
        L_0x0049:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wish.android.shaky.DrawFragment.saveBitmap(android.graphics.Bitmap):void");
    }
}
