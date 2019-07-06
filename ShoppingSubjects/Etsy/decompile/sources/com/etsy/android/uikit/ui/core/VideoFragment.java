package com.etsy.android.uikit.ui.core;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.a.o;
import com.etsy.android.uikit.nav.b;

public class VideoFragment extends TrackingBaseFragment {
    View mActivityIndicator;
    MediaController mMediaController;
    String mVideoUrl;
    VideoView mVideoView;

    /* access modifiers changed from: protected */
    public void onVideoPrepared() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        this.mMediaController = new MediaController(getActivity());
        if (arguments != null) {
            this.mVideoUrl = arguments.getString("video_url");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(k.fragment_listing_video, viewGroup, false);
        this.mVideoView = (VideoView) inflate.findViewById(i.listing_video_player);
        this.mActivityIndicator = inflate.findViewById(i.activity_indicator);
        this.mVideoView.setMediaController(this.mMediaController);
        return inflate;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (this.mVideoUrl == null || this.mVideoUrl.equals("")) {
            Toast.makeText(getActivity(), o.no_video_found, 0).show();
            b.b(getActivity()).h();
            return;
        }
        this.mVideoView.setVideoURI(Uri.parse(this.mVideoUrl));
        this.mVideoView.setOnErrorListener(new OnErrorListener() {
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                VideoFragment.this.onVideoError();
                return false;
            }
        });
        this.mVideoView.setOnPreparedListener(new OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {
                VideoFragment.this.mActivityIndicator.setVisibility(8);
                VideoFragment.this.mMediaController.show(5000);
                VideoFragment.this.mVideoView.start();
                VideoFragment.this.onVideoPrepared();
            }
        });
        this.mVideoView.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                VideoFragment.this.onVideoCompleted();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onVideoCompleted() {
        b.b(getActivity()).h();
    }

    /* access modifiers changed from: protected */
    public void onVideoError() {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(this.mVideoUrl), "video/mp4");
            startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(getActivity(), o.no_video_found, 0).show();
            b.b(getActivity()).h();
        }
    }
}
