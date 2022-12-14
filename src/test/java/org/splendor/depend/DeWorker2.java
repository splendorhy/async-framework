package org.splendor.depend;

import org.splendor.callback.ICallback;
import org.splendor.callback.IWorker;
import org.splendor.worker.WorkResult;
import org.splendor.wrapper.WorkerWrapper;

import java.util.Map;

/**
 * @author splendor.s
 * @create 2022/11/10 下午3:05
 * @description
 */
public class DeWorker2 implements IWorker<WorkResult<User>, String>, ICallback<WorkResult<User>, String> {

    @Override
    public String action(WorkResult<User> result, Map<String, WorkerWrapper> allWrappers) {
        System.out.println("par2的入参来自于par1： " + result.getResult());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result.getResult().getName();
    }


    @Override
    public String defaultValue() {
        return "default";
    }

    @Override
    public void begin() {
        //System.out.println(Thread.currentThread().getName() + "- start --" + System.currentTimeMillis());
    }

    @Override
    public void result(boolean success, WorkResult<User> param, WorkResult<String> workResult) {
        System.out.println("worker2 的结果是：" + workResult.getResult());
    }

}
