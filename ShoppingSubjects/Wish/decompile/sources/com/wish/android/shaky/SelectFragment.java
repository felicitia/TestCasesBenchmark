package com.wish.android.shaky;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class SelectFragment extends Fragment {
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.shaky_select, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        FeedbackTypeAdapter feedbackTypeAdapter = new FeedbackTypeAdapter(getActivity(), getData());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.shaky_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(feedbackTypeAdapter);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.shaky_toolbar);
        toolbar.setTitle(R.string.shaky_feedback_title);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SelectFragment.this.getActivity().onBackPressed();
            }
        });
    }

    private FeedbackItem[] getData() {
        return new FeedbackItem[]{new FeedbackItem(getString(R.string.shaky_row1_title), getString(R.string.shaky_row1_subtitle), R.drawable.ic_bug_report_black_48dp, 0), new FeedbackItem(getString(R.string.shaky_row2_title), getString(R.string.shaky_row2_subtitle), R.drawable.ic_lightbulb_outline_black_48dp, 1), new FeedbackItem(getString(R.string.shaky_row3_title), getString(R.string.shaky_row3_subtitle), R.drawable.ic_feedback_black_48dp, 2)};
    }
}
