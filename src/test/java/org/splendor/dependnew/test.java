package org.splendor.dependnew;

import org.splendor.depend.DeWorker;
import org.splendor.depend.DeWorker1;
import org.splendor.depend.DeWorker2;
import org.splendor.depend.User;
import org.splendor.executor.Async;
import org.splendor.worker.WorkResult;
import org.splendor.wrapper.WorkerWrapper;

import java.util.concurrent.ExecutionException;

/**
 * @author splendor.s
 * @create 2022/11/10 下午4:26
 * @description
 */
public class test {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        org.splendor.depend.DeWorker w = new DeWorker();
        org.splendor.depend.DeWorker1 w1 = new DeWorker1();
        org.splendor.depend.DeWorker2 w2 = new DeWorker2();

        WorkerWrapper<WorkResult<org.splendor.depend.User>, String> workerWrapper2 =  new WorkerWrapper.Builder<WorkResult<org.splendor.depend.User>, String>()
                .worker(w2)
                .callback(w2)
                .id("third")
                .build();

        WorkerWrapper<WorkResult<org.splendor.depend.User>, org.splendor.depend.User> workerWrapper1 = new WorkerWrapper.Builder<WorkResult<org.splendor.depend.User>, org.splendor.depend.User>()
                .worker(w1)
                .callback(w1)
                .id("second")
                .next(workerWrapper2)
                .build();

        WorkerWrapper<String, org.splendor.depend.User> workerWrapper = new WorkerWrapper.Builder<String, org.splendor.depend.User>()
                .worker(w)
                .param("0")
                .id("first")
                .next(workerWrapper1, true)
                .callback(w)
                .build();

        //虽然尚未执行，但是也可以先取得结果的引用，作为下一个任务的入参。V1.2前写法，需要手工给
        //V1.3后，不用给wrapper setParam了，直接在worker的action里自行根据id获取即可.参考dependnew包下代码
        WorkResult<org.splendor.depend.User> result = workerWrapper.getWorkResult();
        WorkResult<User> result1 = workerWrapper1.getWorkResult();
        workerWrapper1.setParam(result);
        workerWrapper2.setParam(result1);

        Async.beginWork(3500, workerWrapper);

        System.out.println(workerWrapper2.getWorkResult());
        Async.shutDown();
    }

}
