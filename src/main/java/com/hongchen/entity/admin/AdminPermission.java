package com.hongchen.entity.admin;

import com.baomidou.mybatisplus.activerecord.Model;
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
@TableName("admin_permission")
public class AdminPermission extends Model<AdminPermission> {
    private static final long serialVersionUID = 1L;
	@TableId(value = "permission_id", type = IdType.AUTO)
	private Integer permissionId;
    /**
     * 名称
     */
	@TableField("permission_name")
	private String permissionName;
    /**
     * 权限资源
     */
	@TableField("permission_resource")
	private String permissionResource;
    /**
     * 权限资源
     */
	@TableField("permission_description")
	private String permissionDescription;
	private Boolean status;
	@TableField("permission_parentId")
	private Integer permissionParentId;
	@TableField("create_time")
	private Date createTime;
	/**
	* 权限类型
	*/
	@TableField("permission_type_id")
	private Integer permissionTypeId;

	/**
	 *跳转地址
	 */
	@TableField("permission_url")
	private String permissionUrl;

	@TableField(exist = false)
	private List<AdminPermission> subAdminPermission;


	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
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

	public Boolean isStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Integer getPermissionParentId() {
		return permissionParentId;
	}

	public void setPermissionParentId(Integer permissionParentId) {
		this.permissionParentId = permissionParentId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getPermissionTypeId() {
		return permissionTypeId;
	}

	public void setPermissionTypeId(Integer permissionTypeId) {
		this.permissionTypeId = permissionTypeId;
	}

	public List<AdminPermission> getSubAdminPermission() {
		return subAdminPermission;
	}

	public void setSubAdminPermission(List<AdminPermission> subAdminPermission) {
		this.subAdminPermission = subAdminPermission;
	}

	public String getPermissionUrl() {
		return permissionUrl;
	}

	public void setPermissionUrl(String permissionUrl) {
		this.permissionUrl = permissionUrl;
	}

	@Override
	protected Serializable pkVal() {
		return this.permissionId;
	}

}
