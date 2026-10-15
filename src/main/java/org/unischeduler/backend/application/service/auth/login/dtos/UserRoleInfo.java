package org.unischeduler.backend.application.service.auth.login.dtos;

public class UserRoleInfo {
    private String userRoleId;
    private String userRoleCode;

    public UserRoleInfo() {}

    public UserRoleInfo(String userRoleId, String userRoleCode) {
        this.userRoleId = userRoleId;
        this.userRoleCode = userRoleCode;
    }

    public String getUserRoleId() {
        return userRoleId;
    }

    public String getUserRoleCode() {
        return userRoleCode;
    }

    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId;
    }

    public void setUserRoleCode(String userRoleCode) {
        this.userRoleCode = userRoleCode;
    }
}
