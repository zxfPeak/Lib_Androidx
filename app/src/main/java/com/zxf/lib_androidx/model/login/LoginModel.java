package com.zxf.lib_androidx.model.login;


import com.zxf.lib_androidx.model.BaseModel;
import com.zxf.lib_androidx.model.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * 登录的模型类
 * Created by zxf on 2018/9/19.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class LoginModel extends BaseModel {

    private int count;
    private int pages;
    private int pageNo;
    private int limit;
    private Value value;
    private long requestStartTime;

    public void setCount(int count) {
        this.count = count;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public void setRequestStartTime(long requestStartTime) {
        this.requestStartTime = requestStartTime;
    }

    public int getCount() {

        return count;
    }

    public int getPages() {
        return pages;
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getLimit() {
        return limit;
    }

    public Value getValue() {
        return value;
    }

    public long getRequestStartTime() {
        return requestStartTime;
    }
}
