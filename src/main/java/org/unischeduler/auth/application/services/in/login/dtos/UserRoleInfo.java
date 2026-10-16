package org.unischeduler.auth.application.services.in.login.dtos;

public class UserRoleInfo {
    private Long userRoleId;
    private String userRoleCode;

    public UserRoleInfo() {}

    public UserRoleInfo(Long userRoleId, String userRoleCode) {
        this.userRoleId = userRoleId;
        this.userRoleCode = userRoleCode;
    }

    public Long getUserRoleId() {
        return userRoleId;
    }

    public String getUserRoleCode() {
        return userRoleCode;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public void setUserRoleCode(String userRoleCode) {
        this.userRoleCode = userRoleCode;
    }
}
