package com.hongchen.vo.admin;

import com.hongchen.util.ReflectToStringUtil;
import com.hongchen.validator.First;
import com.hongchen.validator.Second;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by lichongda on 2017/4/6.
 */
public class UserVo implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer usersId;
    @NotNull(message="用户名不能为空",groups={First.class})
    @Length(min=1, max=50, message="用户长度必须1-50",groups={First.class,Second.class})
    private String userName;

    private String originalUserName;

    @NotNull(message="昵称不能为空",groups={First.class,Second.class})
    @Length(min=1, max=50, message="昵称长度必须1-50之间",groups={First.class,Second.class})
    private String  nickName;

    @NotNull(message="密码不能为空",groups={First.class})
    @Length(min=6, max=50, message="密码长度必须6-50",groups={First.class})
    private String  userPassword;

    private String  roleDescription;

    private String roleId;

    private String originalRoleId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getOriginalUserName() {
        return originalUserName;
    }

    public void setOriginalUserName(String originalUserName) {
        this.originalUserName = originalUserName;
    }

    public Integer getUsersId() {
        return usersId;
    }

    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }

    public String getOriginalRoleId() {
        return originalRoleId;
    }

    public void setOriginalRoleId(String originalRoleId) {
        this.originalRoleId = originalRoleId;
    }

    @Override
    public String toString() {
        return ReflectToStringUtil.toStringUtil(this,false);
    }
}
