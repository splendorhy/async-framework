package org.splendor.parallel;


import org.splendor.callback.ICallback;
import org.splendor.callback.IWorker;
import org.splendor.timer.SystemClock;
import org.splendor.worker.WorkResult;
import org.splendor.wrapper.WorkerWrapper;

import java.util.Map;

/**
 * @author splendor.s
 * @create 2023/01/10 下午3:05
 * @description
 */
public class ParWorker4 implements IWorker<String, String>, ICallback<String, String> {

    @Override
    public String action(String object, Map<String, WorkerWrapper> allWrappers) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "result = " + SystemClock.now() + "---param = " + object + " from 4";
    }


    @Override
    public String defaultValue() {
        return "worker4--default";
    }

    @Override
    public void begin() {
        //System.out.println(Thread.currentThread().getName() + "- start --" + System.currentTimeMillis());
    }

    @Override
    public void result(boolean success, String param, WorkResult<String> workResult) {
        if (success) {
            System.out.println("callback worker4 success--" + SystemClock.now() + "----" + workResult.getResult()
                    + "-threadName:" +Thread.currentThread().getName());
        } else {
            System.err.println("callback worker4 failure--" + SystemClock.now() + "----"  + workResult.getResult()
                    + "-threadName:" +Thread.currentThread().getName());
        }
    }

}
