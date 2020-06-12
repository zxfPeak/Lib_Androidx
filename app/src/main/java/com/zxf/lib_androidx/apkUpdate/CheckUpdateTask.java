package com.zxf.lib_androidx.apkUpdate;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.widget.Toast;

import com.zxf.lib_androidx.R;
import com.zxf.lib_androidx.views.LoadingDialog;


/**
 * @author feicien (ithcheng@gmail.com)
 * @since 2016-07-05 19:21
 */
class CheckUpdateTask extends AsyncTask<Void, Void, String> {

    private Context mContext;
    private int mType;
    private boolean mShowProgressDialog;
    private String url;
    private LoadingDialog loadingDialog;
    private String updateContent;
    private int serviceVersion;

    CheckUpdateTask(Context context, String url, String updateContent, int serviceVersion, int type, boolean showProgressDialog) {

        this.mContext = context;
        this.url = url;
        this.updateContent = updateContent;
        this.serviceVersion = serviceVersion;
        this.mType = type;
        this.mShowProgressDialog = showProgressDialog;

    }


    protected void onPreExecute() {
        if (mShowProgressDialog) {
            loadingDialog = new LoadingDialog(mContext);
            loadingDialog.setMessage(mContext.getString(R.string.android_auto_update_dialog_checking));
            loadingDialog.showDialog();
        }
    }


    @Override
    protected void onPostExecute(String result) {

        if (loadingDialog != null) {
            loadingDialog.dismissDialog();
        }

        if (!TextUtils.isEmpty(result)) {
            parseJson(result);
        }
    }

    private void parseJson(String result) {

        int versionCode = AppUtils.getVersionCode(mContext);
        if (serviceVersion > versionCode) {
            if (mType == Constants.TYPE_NOTIFICATION) {
                new NotificationHelper(mContext).showNotification(updateContent, result);
            } else if (mType == Constants.TYPE_DIALOG) {
                showDialog(mContext, updateContent, result);
            }
        } else if (mShowProgressDialog) {
            Toast.makeText(mContext, mContext.getString(R.string.android_auto_update_toast_no_new_update), Toast.LENGTH_SHORT).show();
        }

    }


    /**
     * Show dialog
     */
    private void showDialog(Context context, String content, String apkUrl) {
        UpdateDialog.show(context, content, apkUrl);
    }


    @Override
    protected String doInBackground(Void... args) {
        return url;
    }
}
