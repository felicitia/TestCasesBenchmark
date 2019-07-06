package com.contextlogic.wish.activity.developer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter.ExperimentDefinition;
import java.util.ArrayList;

public class DeveloperSettingsExperimentsAdapter extends BaseAdapter {
    private DeveloperSettingsExperimentsActivity mBaseActivity;
    private DeveloperSettingsExperimentsFragment mFragment;

    static class ItemRowHolder {
        Spinner rowSpinner;
        TextView rowText;

        ItemRowHolder() {
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public boolean isEnabled(int i) {
        return true;
    }

    public DeveloperSettingsExperimentsAdapter(DeveloperSettingsExperimentsActivity developerSettingsExperimentsActivity, DeveloperSettingsExperimentsFragment developerSettingsExperimentsFragment) {
        this.mBaseActivity = developerSettingsExperimentsActivity;
        this.mFragment = developerSettingsExperimentsFragment;
    }

    public int getCount() {
        return this.mFragment.getExperimentDefinitions().size();
    }

    public ExperimentDefinition getItem(int i) {
        return (ExperimentDefinition) this.mFragment.getExperimentDefinitions().get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ItemRowHolder itemRowHolder;
        int i2 = 0;
        if (view == null) {
            LayoutInflater layoutInflater = this.mBaseActivity.getLayoutInflater();
            itemRowHolder = new ItemRowHolder();
            view = layoutInflater.inflate(R.layout.developer_settings_experiments_fragment_row, viewGroup, false);
            itemRowHolder.rowText = (TextView) view.findViewById(R.id.developer_settings_experiments_fragment_row_text);
            itemRowHolder.rowSpinner = (Spinner) view.findViewById(R.id.developer_settings_experiments_fragment_row_spinner);
            view.setTag(itemRowHolder);
        } else {
            itemRowHolder = (ItemRowHolder) view.getTag();
        }
        final ExperimentDefinition item = getItem(i);
        itemRowHolder.rowText.setText(item.getName());
        final ArrayList buckets = item.getBuckets(true);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this.mBaseActivity, R.layout.spinner_item, buckets);
        arrayAdapter.setDropDownViewResource(17367049);
        itemRowHolder.rowSpinner.setAdapter(arrayAdapter);
        itemRowHolder.rowSpinner.setOnItemSelectedListener(null);
        String bucketForExperiment = ExperimentDataCenter.getInstance().getBucketForExperiment(item.getName());
        int i3 = 0;
        while (true) {
            if (i3 >= buckets.size()) {
                break;
            } else if (((String) buckets.get(i3)).equals(bucketForExperiment)) {
                i2 = i3;
                break;
            } else {
                i3++;
            }
        }
        itemRowHolder.rowSpinner.setSelection(i2);
        itemRowHolder.rowSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                String str;
                String bucketForExperiment = ExperimentDataCenter.getInstance().getBucketForExperiment(item.getName());
                if (i == 0) {
                    str = null;
                } else {
                    str = (String) buckets.get(i);
                }
                if (str != null && !str.equals(bucketForExperiment)) {
                    DeveloperSettingsExperimentsAdapter.this.changeExperimentBucket(item.getName(), str);
                }
            }
        });
        return view;
    }

    /* access modifiers changed from: private */
    public void changeExperimentBucket(final String str, final String str2) {
        this.mFragment.withServiceFragment(new ServiceTask<DeveloperSettingsExperimentsActivity, DeveloperSettingsServiceFragment>() {
            public void performTask(DeveloperSettingsExperimentsActivity developerSettingsExperimentsActivity, DeveloperSettingsServiceFragment developerSettingsServiceFragment) {
                developerSettingsServiceFragment.setExperimentBucket(str, str2);
            }
        });
    }
}
