package com.zxf.lib_androidx.model.login;

/**
 * Created by zxf on 2018/9/21.
 */

public class Value {
    private String authority;
    private int userId;
    private int orgId;
    private String username;
    private String token;

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAuthority() {

        return authority;
    }

    public int getUserId() {
        return userId;
    }

    public int getOrgId() {
        return orgId;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }
}
