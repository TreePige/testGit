package com.rongdu.eloan.modules.common.utils.saver;

import com.rongdu.eloan.modules.system.domain.SysRole;
import com.rongdu.eloan.modules.system.domain.SysUser;

/**
 * Created by FHJ on 2015/5/8.
 * 保存登录信息，可以扩展
 */
public class LoginContext {
    private SysUser loginUser;
    private SysRole role;

    public LoginContext(SysUser loginUser, SysRole role) {
        this.loginUser = loginUser;
        this.role = role;
    }

    public SysUser getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(SysUser loginUser) {
        this.loginUser = loginUser;
    }

    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }
}
