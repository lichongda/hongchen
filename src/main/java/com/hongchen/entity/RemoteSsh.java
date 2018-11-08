package com.hongchen.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;


/**
 * <p>
 * 
 * </p>
 *
 * @author hongchen
 * @since 2018-10-31
 */
@TableName("remote_ssh")
public class RemoteSsh extends Model<RemoteSsh> {

    private static final long serialVersionUID = 1L;
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
    /**
     * 名称
     */
	private String name;
    /**
     * 命令
     */
	private String command;
    /**
     * 密钥名称
     */
	@TableField("key_name")
	private String keyName;
    /**
     * 连接类型: 0密码连接 1密钥连接
     */
	@TableField("connection_type")
	private Integer connectionType;
    /**
     * 服务密码
     */
	@TableField("server_password")
	private String serverPassword;
    /**
     * 服务器账号
     */
	@TableField("server_account")
	private String serverAccount;
    /**
     * 服务器端口
     */
	@TableField("server_port")
	private Integer serverPort;
    /**
     * 服务器地址
     */
	@TableField("server_ip")
	private String serverIp;
    /**
     * 备注
     */
	private String remarks;
	@TableField("create_time")
	private Date createTime;
	@TableField("update_time")
	private Date updateTime;
	@TableField("create_user")
	private String createUser;
	@TableField("update_user")
	private String updateUser;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public Integer getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(Integer connectionType) {
		this.connectionType = connectionType;
	}

	public String getServerPassword() {
		return serverPassword;
	}

	public void setServerPassword(String serverPassword) {
		this.serverPassword = serverPassword;
	}

	public String getServerAccount() {
		return serverAccount;
	}

	public void setServerAccount(String serverAccount) {
		this.serverAccount = serverAccount;
	}

	public Integer getServerPort() {
		return serverPort;
	}

	public void setServerPort(Integer serverPort) {
		this.serverPort = serverPort;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
