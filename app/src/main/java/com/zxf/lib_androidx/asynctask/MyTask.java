package com.zxf.lib_androidx.asynctask;

import android.os.AsyncTask;

import java.util.List;

/**
 * Created by zxf on 2018/9/18.
 */

public class MyTask extends AsyncTask<TaskItem, Integer, TaskItem> {
    private TaskListener listener;
    private Object result;
    
    public MyTask() {
    }
    
    public static MyTask newInstance () {
        return new MyTask ();
    }

    /** 异步执行后台线程要完成的任务,耗时操作将在此方法中完成 */
    protected TaskItem doInBackground (TaskItem... items) {
        TaskItem item = items[0];
        this.listener = item.getListener ();
        if (this.listener != null) {
            if (this.listener instanceof TaskListListener) {
                this.result = ((TaskListListener) this.listener).getList ();
            } else if (this.listener instanceof TaskObjectListener) {
                this.result = ((TaskObjectListener) this.listener).getObject ();
            } else {
                this.listener.get ();
            }
        }
        return item;
    }
    
    protected void onCancelled () {
        super.onCancelled ();
    }

    /** 当doInBackground方法完成后,系统将自动调用此方法,并将doInBackground方法返回的值传入此方法.通过此方法进行UI的更新 */
    protected void onPostExecute (TaskItem item) {
        if (this.listener != null) {
            if (this.listener instanceof TaskListListener) {
                ((TaskListListener) this.listener).update ((List) this.result);
            } else if (this.listener instanceof TaskObjectListener) {
                ((TaskObjectListener) this.listener).update (this.result);
            } else {
                this.listener.update ();
            }
        }
    }

    /** 执行后台耗时操作前被调用,通常用于进行初始化操作 */
    protected void onPreExecute () {
        super.onPreExecute ();
    }

    /** 当在doInBackground方法中调用publishProgress方法更新任务执行进度后,将调用此方法.通过此方法我们可以知晓任务的完成进度 */
    protected void onProgressUpdate (Integer... values) {
        super.onProgressUpdate (values);
        if (this.listener != null) {
            this.listener.onProgressUpdate (values);
        }
    }
}
