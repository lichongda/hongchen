package com.hongchen.entity.admin;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;


/**
 * <p>
 * 
 * </p>
 *
 * @author hongchen
 * @since 2017-03-28
 */
@TableName("admin_role_permission")
public class AdminRolePermission implements  Serializable{
    private static final long serialVersionUID = 1L;

	@TableField("role_id")
	private Integer roleId;
	@TableField("permission_id")
	private Integer permissionId;


	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}
}
