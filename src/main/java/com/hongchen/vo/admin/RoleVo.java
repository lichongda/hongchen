package com.hongchen.vo.admin;

import com.hongchen.util.ReflectToStringUtil;
import com.hongchen.validator.First;
import com.hongchen.validator.Second;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by lichongda on 2017/4/6.
 */
public class RoleVo implements Serializable{
    private Integer roleId;
    private String  originalRoleName;

    @NotEmpty(message="角色名不能为空",groups={First.class})
    @Length(max=50, message="角色名长度不能超过50个字符",groups={First.class,Second.class})
    private String  roleName;

    @Length(max=100, message="角色说明长度不能超过100个字符",groups={First.class,Second.class})
    private String  roleDescription;

    private String  addId;//需添加的权限ID

    private String  delId;//需删除的权限ID

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getAddId() {
        return addId;
    }

    public void setAddId(String addId) {
        this.addId = addId;
    }

    public String getDelId() {
        return delId;
    }

    public void setDelId(String delId) {
        this.delId = delId;
    }

    public String getOriginalRoleName() {
        return originalRoleName;
    }

    public void setOriginalRoleName(String originalRoleName) {
        this.originalRoleName = originalRoleName;
    }

    @Override
    public String toString() {
        return ReflectToStringUtil.toStringUtil(this,false);
    }
}
