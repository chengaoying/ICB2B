package cn.core.framework.common;

/**
 * 常量类
 * @author JC
 * @date 2015-8-1
 */
public class Constant { 
	
	public static final String CONST_BACKEND = "backend";				//后台标示
	public static final String CONST_FRONTEND = "frontend";				//前端标示
	
	public static final String CONST_LANG_CN = "zh";				//中文
	public static final String CONST_LANG_EN = "en";				//英文
	
	
	public static final String CONST_SUCCESS = "success";			//成功
	public static final String CONST_ERROR = "error";				//失败
	
	public static final byte CONST_STATUS_ENABLE = 1;				//状态：启用
	public static final byte CONST_STATUS_DISABLE = 0;				//状态：禁用
	
	public static final byte CONST_SYS_SUPER_USER = 1;							//超级管理用户标识
	public static final String CONST_SESSION_BACKEND_USER = "backend_user";		//管理员用户变量名（session）
	public static final String CONST_SESSION_FRONTEND_USER = "frontend_user";	//前端用户变量名(session)
	
	public static final byte CONST_RES_ACCESS_AUTH_YES = 1;			//资源可以访问标示
	public static final byte CONST_RES_ACCESS_AUTH_NO = 0;			//资源不能访问标示
	
	public static final byte CONST_MEMBER_TYPE_COMMON = 0;			//会员类型：普通会员
	public static final byte CONST_MEMBER_TYPE_BUYER = 1;			//会员类型：采购商
	public static final byte CONST_MEMBER_TYPE_SUPPLIER = 2;		//会员类型：供应商
	//public static final byte CONST_MEMBER_TYPE_BOTH = 3;			//会员类型：即是采购商也是供应商
	
	public static final byte CONST_MEMBER_STATUS_ACTIVE_FAIL = -1;	//会员状态：激活失败
	public static final byte CONST_MEMBER_STATUS_INACTIVE = 0;		//会员状态：待激活
	public static final byte CONST_MEMBER_STATUS_INCOMPLETE = 1;	//会员状态：待完善信息
	public static final byte CONST_MEMBER_STATUS_CHECKING = 2;		//会员状态：待审核
	public static final byte CONST_MEMBER_STATUS_CHECK_FAIL = 3;	//会员状态：审核未通过
	public static final byte CONST_MEMBER_STATUS_CHECK_SUCCESS = 4; //会员状态：审核通过
	
	public static final byte CONST_PRODUCT_STATUS_CHECKING = 0;		//产品状态：待审核
	public static final byte CONST_PRODUCT_STATUS_CHECK_FAIL = 1;	//产品状态：审核未通过
	public static final byte CONST_PRODUCT_STATUS_CHECK_SUCCESS = 2;//产品状态：审核通过
	
	public static final byte CONST_ORDER_STATUS_UNPAID = 0;		//订单状态：未付款
	public static final byte CONST_ORDER_STATUS_PAID = 1;		//订单状态：已付款
	public static final byte CONST_ORDER_STATUS_UNDELIVER = 2;	//订单状态：待发货
	public static final byte CONST_ORDER_STATUS_DELIVER = 3;	//订单状态：已发货
	public static final byte CONST_ORDER_STATUS_SUCCESS = 4;	//订单状态：交易完成
	
	
}
