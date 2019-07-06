package androidx.work.impl.workers;

import androidx.work.Worker;
import androidx.work.Worker.WorkerResult;

public class CombineContinuationsWorker extends Worker {
    public WorkerResult doWork() {
        setOutputData(getInputData());
        return WorkerResult.SUCCESS;
    }
}
