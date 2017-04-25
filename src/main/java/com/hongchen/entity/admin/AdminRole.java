package com.hongchen.entity.admin;

import com.baomidou.mybatisplus.activerecord.Model;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.List;


/**
 * <p>
 * 
 * </p>
 *
 * @author hongchen
 * @since 2017-03-28
 */
@TableName("admin_role")
public class AdminRole extends Model<AdminRole> {

    private static final long serialVersionUID = 1L;

	@TableId(value = "role_id",type = IdType.AUTO)
	private Integer roleId;
    /**
     * 角色名称
     */
	@TableField("role_name")
	private String roleName;
    /**
     * 角色说明
     */
	@TableField("role_description")
	private String roleDescription;
    /**
     * 角色状态
     */
	private Boolean status;
	@TableField("create_time")
	private Date createTime;
	@TableField(exist = false)
	private List<AdminPermission> permissionList;// 一个角色对应多个权限
	@TableField(exist = false)
	private List<AdminUser> userListserList;// 一个角色对应多个用户


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

	public Boolean isStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.roleId;
	}

	public List<AdminPermission> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<AdminPermission> permissionList) {
		this.permissionList = permissionList;
	}

	public List<AdminUser> getUserListserList() {
		return userListserList;
	}

	public void setUserListserList(List<AdminUser> userListserList) {
		this.userListserList = userListserList;
	}

	@Transient
	public List<String> getPermissionsName() {
		List<String> list = new ArrayList<String>();
		List<AdminPermission> perlist = getPermissionList();
		if(perlist != null && perlist.size() > 0){
			for (AdminPermission per : perlist) {
				list.add(per.getPermissionName());
			}
		}

		return list;
	}
}
