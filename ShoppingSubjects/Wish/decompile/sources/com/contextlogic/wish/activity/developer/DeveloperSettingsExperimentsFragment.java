package com.contextlogic.wish.activity.developer;

import android.widget.ListView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter.ExperimentDefinition;
import java.util.ArrayList;

public class DeveloperSettingsExperimentsFragment extends UiFragment<DeveloperSettingsExperimentsActivity> {
    private DeveloperSettingsExperimentsAdapter mAdapter;
    private ArrayList<ExperimentDefinition> mExperimentDefinitions;
    private ListView mListView;

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.developer_settings_experiments_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        this.mExperimentDefinitions = ExperimentDataCenter.getInstance().getExperimentDefinitions();
        this.mListView = (ListView) findViewById(R.id.developer_settings_experiments_fragment_listview);
        this.mAdapter = new DeveloperSettingsExperimentsAdapter((DeveloperSettingsExperimentsActivity) getBaseActivity(), this);
        this.mListView.setAdapter(this.mAdapter);
    }

    public ArrayList<ExperimentDefinition> getExperimentDefinitions() {
        return this.mExperimentDefinitions;
    }
}
