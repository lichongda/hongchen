package com.hongchen.entity.admin;

import java.beans.Transient;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.hongchen.validator.First;
import com.hongchen.validator.Second;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * <p>
 * 管理员
 * </p>
 *
 * @author hongchen
 * @since 2017-03-28
 */
@TableName("admin_user")
public class AdminUser implements  Serializable {
    private static final long serialVersionUID = 1L;
	@TableId(value = "user_id", type = IdType.AUTO)
	private Integer userId;
    /**
     * 用户名
     */
	@TableField("user_name")
	private String userName;
    /**
     * 用户昵称
     */
	@TableField("nick_name")
	private String nickName;
    /**
     * 用户密码
     */
	@TableField("user_password")
	private String userPassword;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 用户状态
     */
	@TableField("user_status")
	private Boolean userStatus;
    /**
     * 用户部门
     */
	@TableField("department_id")
	private Integer departmentId;
	@TableField(exist = false)
	private List<AdminRole> roleList;// 一个用户具有多个角色


	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Boolean isUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Boolean userStatus) {
		this.userStatus = userStatus;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public List<AdminRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<AdminRole> roleList) {
		this.roleList = roleList;
	}

	/*@Transient 不参与序列化*/
	@Transient
	public Set<String> getRolesName() {
		List<AdminRole> roles = getRoleList();
		Set<String> set = new HashSet<String>();
		if (roles != null) {
			for (AdminRole role : roles) {
				set.add(role.getRoleName());
			}
		}
		return set;
	}
}
