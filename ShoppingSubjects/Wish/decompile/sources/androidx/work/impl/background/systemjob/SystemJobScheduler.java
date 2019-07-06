package androidx.work.impl.background.systemjob;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;
import androidx.work.impl.Scheduler;
import androidx.work.impl.model.WorkSpec;
import java.util.List;

@TargetApi(23)
public class SystemJobScheduler implements Scheduler {
    private final JobScheduler mJobScheduler;
    private final SystemJobInfoConverter mSystemJobInfoConverter;

    public SystemJobScheduler(Context context) {
        this((JobScheduler) context.getSystemService("jobscheduler"), new SystemJobInfoConverter(context));
    }

    public SystemJobScheduler(JobScheduler jobScheduler, SystemJobInfoConverter systemJobInfoConverter) {
        this.mJobScheduler = jobScheduler;
        this.mSystemJobInfoConverter = systemJobInfoConverter;
    }

    public void schedule(WorkSpec... workSpecArr) {
        for (WorkSpec workSpec : workSpecArr) {
            scheduleInternal(workSpec);
            if (VERSION.SDK_INT == 23) {
                scheduleInternal(workSpec);
            }
        }
    }

    public void scheduleInternal(WorkSpec workSpec) {
        JobInfo convert = this.mSystemJobInfoConverter.convert(workSpec);
        Log.d("SystemJobScheduler", String.format("Scheduling work ID %s Job ID %s", new Object[]{workSpec.id, Integer.valueOf(convert.getId())}));
        this.mJobScheduler.schedule(convert);
    }

    public void cancel(String str) {
        List<JobInfo> allPendingJobs = this.mJobScheduler.getAllPendingJobs();
        if (allPendingJobs != null) {
            for (JobInfo jobInfo : allPendingJobs) {
                if (str.equals(jobInfo.getExtras().getString("EXTRA_WORK_SPEC_ID"))) {
                    this.mJobScheduler.cancel(jobInfo.getId());
                    if (VERSION.SDK_INT != 23) {
                        return;
                    }
                }
            }
        }
    }
}
