package com.hongchen.constants;

public interface Constants {

	/**
	 * 错误返回码
	 */
	int SUCCESS = 0;
	int ERROE = 1;  //错误
	int PARAMETER_VALIDATION_ERROR = 1;// 参数错误
	int SESSION_DISABLED = 2;// 登录超时
	int BUSINESS_ERROR = 3;// 逻辑异常
	int SYSTEM_ERROR = 4;// 系统错误
	int SHIRO_ERROR = 5;// 关于shiro的异常

	/**
	 * 角色
	 */
	int SUPER_ADMINISTRATOR = 1; //超级管理员

	/**
	 * 菜单类型
	 */
	int MENU_PARENT_ID = 1;//父菜单Id
	int MENU_FUNCTION= 1; //功能
	int MENU_BUTTON = 2 ; //按钮



	/**
	 * 缓存Key
	 */
	String CACHE_TOKNID = "honghcne:tokenId_";         //tokenId+用户Id
	String CACHE_USER_ID = "honghcne:userId_";         //tokenId+用户Id
	int CACHE_TOKENID_TIME = 30;                       //tokenId有效时间




}
