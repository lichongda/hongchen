package com.hongchen.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 
 *
 */
@TableName("admin_permission")
public class Permission implements Serializable {

	@TableField(exist = false)
	protected static final long serialVersionUID = 1L;

	/**  */
	@TableId(type = IdType.AUTO)
	protected Integer permissionId;

	/** 名称 */
	protected String permissionName;

	/** 权限资源 */
	protected String permissionResource;

	/** 权限资源 */
	protected String permissionDescription;

	/**  */
	protected Boolean status;

	/**  */
	protected Integer permissionParentid;

	/**  */
	protected Date createTime;

	public Integer getPermissionId() {
		return this.permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionName() {
		return this.permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getPermissionResource() {
		return this.permissionResource;
	}

	public void setPermissionResource(String permissionResource) {
		this.permissionResource = permissionResource;
	}

	public String getPermissionDescription() {
		return this.permissionDescription;
	}

	public void setPermissionDescription(String permissionDescription) {
		this.permissionDescription = permissionDescription;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Integer getPermissionParentid() {
		return this.permissionParentid;
	}

	public void setPermissionParentid(Integer permissionParentid) {
		this.permissionParentid = permissionParentid;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
