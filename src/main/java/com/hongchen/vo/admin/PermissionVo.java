package com.hongchen.vo.admin;

import com.hongchen.util.ReflectToStringUtil;
import com.hongchen.validator.First;
import com.hongchen.validator.Second;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * Created by lichongda on 2017/4/11.
 */
public class PermissionVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer permissionId;

    private Integer permissionSubId;//子菜单

    @NotEmpty(message="资源名称不能为空",groups={First.class,Second.class})
    @Length(max=50, message="资源名称长度不能超过50个字符",groups={First.class,Second.class})
    private String permissionName;

    private String  originalPermissionName;

    @NotEmpty(message="权限资源不能为空",groups={First.class,Second.class})
    private String permissionResource;//权限资源

    private String permissionDescription;

    private Integer permissionParentId;

    private Integer permissionTypeId;

    private String permissionUrl;

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Integer getPermissionSubId() {
        return permissionSubId;
    }

    public void setPermissionSubId(Integer permissionSubId) {
        this.permissionSubId = permissionSubId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionResource() {
        return permissionResource;
    }

    public void setPermissionResource(String permissionResource) {
        this.permissionResource = permissionResource;
    }

    public String getPermissionDescription() {
        return permissionDescription;
    }

    public void setPermissionDescription(String permissionDescription) {
        this.permissionDescription = permissionDescription;
    }

    public Integer getPermissionParentId() {
        return permissionParentId;
    }

    public void setPermissionParentId(Integer permissionParentId) {
        this.permissionParentId = permissionParentId;
    }

    public Integer getPermissionTypeId() {
        return permissionTypeId;
    }

    public void setPermissionTypeId(Integer permissionTypeId) {
        this.permissionTypeId = permissionTypeId;
    }

    public String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl;
    }

    public String getOriginalPermissionName() {
        return originalPermissionName;
    }

    public void setOriginalPermissionName(String originalPermissionName) {
        this.originalPermissionName = originalPermissionName;
    }

    @Override
    public String toString() {
        return ReflectToStringUtil.toStringUtil(this,false);
    }
}
