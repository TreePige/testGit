package com.rongdu.eloan.modules.common.constant;


public class SystemConstant {

public static enum FEE_TYPE{
        /**
         * 停车费
         */
        PARKING_FEE,
        /**
         * gps安装费
         */
        GPS_SETUP_FEE,
        /**
         * gps使用费，按期计算
         */
        GPS_USE_FEE,
        /**
         * 借款保证金
         */
        CASH_DEPOSIT_AS_COLLATERAL,
        
        /**
         * 平台服务费
         */
        PLATFORM_SERVICE_FEE,
        /**
         * 违章处理费
         */
        ILLEGAL_HANDLING_CHARGE,
        /**
         * 调查费
         */
        INVESTIGATION_FEE,
        /**
         * 信用报告费
         */
        CREDIT_REPORT_FEE,
        /**
         * 信息咨询费
         */
        CONSULT_FEE,
        /**
         * 抵押公证费
         */
        NOTARIAL_FEE,
        /**
         * 车辆评估费
         */
        VEHICLE_VALUATION_FEE,
        /**
         * 末期费用退还
         */
        LAST_PERIOD_BACK_FEE
    }

    /***********************  菜单：是否是菜单  ****************************************/	
	/** 不是菜单 */
	public static final byte MENU_NO = 0;
	/** 是菜单 */
	public static final byte MENU_IS = 1;
	/** 所有菜单 */
	public static final byte MENU_ALL = -1;
	
	
/***********************  组织机构类型：公司或部门  ********************************/	
	/** 部门 */
	public static final byte OFFICE_TYPE_DEPARTMENT = 0;
	/** 公司 */
	public static final byte OFFICE_TYPE_COMPANY = 1;
	
	
/***********************  用户状态  ****************************************/	
	/** 用户状态：正常状态 */
	public static final byte USER_STATUS_NORMAL = 0;
	

/***********************  消息类型  ****************************************/	
	/**消息类型：短信**/
	public static final byte MSG_TYPE_SMS = 1;
	/**消息类型：邮件**/
	public static final byte MSG_TYPE_EMAIL = 2;


/***********************  消息配置信息是否启用  ****************************************/	
	/**消息配置信息：禁用**/
	public static final byte MSG_CONFIG_NO_USE = 0;
	/**消息配置信息：启用**/
	public static final byte MSG_CONFIG_IS_USE = 1;
	
/***********************  模板信息是否启用  ****************************************/	
	/**模板信息：禁用**/
	public static final byte MSG_TEMPLATE_NO_USE = 0;
	/**模板信息：启用**/
	public static final byte MSG_TEMPLATE_IS_USE = 1;
	
	
/***********************  通知信息发送结果状态  ****************************************/	
	/**通知信息发送结果状态：待发送**/
	public static final byte MSG_RESULT_STATUS_WAIT = 0;
	/**通知信息发送结果状态：发送成功**/
	public static final byte MSG_RESULT_STATUS_SUCC = 1;
	/**通知信息发送结果状态：发送失败**/
	public static final byte MSG_RESULT_STATUS_FAIL = -1;
	
	
/***********************  系统通知信息模块Nid  ****************************************/	
	
	/****审批模块发送消息Nid****/
	public static final String MSG_BORROW_APPLY = "borrow_apply";
	
	public static final String MSG_SEND_REPAT_PLAN = "send_repayPlan";
	
	
/*********************** 日志类型 ****************************************/	
	
	/** 日志类型（1：接入日志） */
	public static final String TYPE_ACCESS = "1";
	
    /** 日志类型（2：错误日志） */
	public static final String TYPE_EXCEPTION = "2";
	
	/** 日志类型（3：操作日志） */
	public static final String TYPE_OPERATOR = "3";
	
/*********************** security用户组处理常量类 ****************************************/	
	/** system用户登录名 */
    public static final String SYSTEM_LOGIN_NAME = "mlms";
    /** system用户初始化密码 */
    public static final String SYSTEM_PASSWORD_DEFAULT = "123456";
    public static final String CMS_SYSTEM_PASSWORD_DEFAULT = "abc12345";
    /** 默认角色 */
    public static final String ROLE_DEFAULT = "ROLE_DEFAULT";

    /*********************** 业务状态量类 ****************************************/
    /** 信贷 */
    public static final Integer BTYPE_CREDIT = 1;
    /** 主借款人 */
    public static final Integer MAIN_BORROWER = 0;
    /** 状态记录（opLog表中的otype） */
    public static final Integer OTYPE_STATE = 3;
    /** 沟通记录（opLog表中的otype） */
	public static final Integer OTYPE_MESSAGE = 1;
	/** 更新记录（opLog表中的otype） */
	public static final Integer OTYPE_UPDATE_INFO = 2;
	
	/*********************** 流程状态量类 ****************************************/
	/** 申请进件 */
	public static final String FLOW_STATE_APPLY_DOCUMENTING = "1";
	/** 继续跟进 */
	public static final byte CONTINUE_FOLLOWUP = -1;
	/** 附件状态正常 */
	public static final byte ATTACHEMENT_STATE_NORMAL = 1;
	/** 附件状态不正常 */
	public static final byte ATTACHEMENT_STATE_ABNORMAL = 0;
	/** 驳回标识：被驳回 */
	public static final Integer REJECTION_SIGN_REJECTITED = 1;
	/** 驳回标识：正常 */
	public static final Integer REJECTION_SIGN_NORMAL = 0;
	
	/************************* 前台表单 中英文 Mapping *************************/
	/******* 申请进件  *******/
	public static final String FORM_CN_MAX_AMOUNT = "申请借款最高额度";
	public static final String FORM_CN_MIN_AMOUNT = "申请借款最低额度";
	public static final String FORM_ACCOUNT = "申请借款额度";
	
	/******* 放款页 *******/
	public static final String FORM_CN_ACCOUNT = "放款金额";
	public static final String FORM_CN_BANK = "放款银行";
	public static final String FORM_CN_BANK_CARD_ID = "银行账号";
	public static final String FORM_CN_LOAN_TIME = "放款时间";
	
	/******* 处理回访页 *******/
	public static final String FORM_CN_CONTENT = "执行内容";
	public static final String FORM_CN_VISIT_STATUS = "回访状态";
	public static final String FORM_CN_SUGGESTION = "综合建议";
	public static final String FORM_CN_VISIT_TIME = "回访时间";
	
	/******* 咨询页 *******/
	public static final String FORM_CN_NAME = "客户姓名";
	public static final String FORM_CN_SEX = "性别";
	public static final String FORM_CN_BIRTHDAY = "生日";
	public static final String FORM_CN_PRODUCT = "产品";
	public static final String FORM_CN_IDCARD = "身份证";
	public static final String FORM_CN_CUSTOMER_SOURCE = "客户来源";
	public static final String FORM_CN_BORROW_AMOUNT = "借款金额";
	public static final String FORM_CN_BORROW_USE = "借款用途";
	public static final String FORM_CN_AREA = "居住地";
	public static final String FORM_CN_REJECT_REASON = "驳回理由";
	
	/******* 审核页 *******/
	public static final String FORM_CN_RECOMMENDED_ACCOUNT = "建议额度";
	public static final String FORM_CN_MANAGEMENT_FEE = "管理费率";
	public static final String FORM_CN_OVERDUE_PENALTY_RATE = "逾期罚息";
	public static final String FORM_CN_REPAYMENT_DAY = "每期还款日";
	public static final String FORM_CN_TIME_LIMIT = "借款期限";
	public static final String FORM_CN_VOUCH_TYPE = "担保类型";
	public static final String FORM_CN_REPAYMENT_RATE = "贷款利率";
	public static final String FORM_CN_PROJECT_NAME = "项目名称";
	public static final String FORM_CN_NEXT_STEP = "处理意见";
	public static final String FORM_CN_NEXT_USER = "处理人员";
	public static final String FORM_CN_REJECT_TO = "驳回至";
	public static final String FORM_CN_REPAYMENT_TYPE = "还款类型";
	public static final String FORM_CN_REPAYMENT_PENALTY_RATE = "还款违约金率";
	
	/******* 处理意见 *******/
	public static final String FORM_CN_REJECTION_TYPE = "拒贷类型";
	public static final String FORM_CN_MATERIAL_TYPE = "补充资料类型";
	public static final String FORM_CN_NEXT_STEP_COMMENT = "处理意见";
	/************************* 前台表单 中英文 Mapping END *************************/
	
	/***************************************************************************************/
	//合同生成 --信贷
	public static final String PATH_FILE_TEMPLATES = "/resources/contractfile/templates/jierong";//信贷模板路经
	public static final String CHEKUAIRONG_PATH_FILE_TEMPLATES = "/resources/contractfile/templates/chekuairong";//车快融模板路经
	public static final String PATH_FILE_HTML      = "/resources/contractfile/htmlfile/";
	public static final String PATH_FILE_PDF       = "/resources/contractfile/pdffile/";
	public static final String PATH_FILE_ROOT      = "resources/contractfile/pdffile/";
	
	//协议文件名-jierong-----信贷	
	public static final String TEMPLATE_NAME_BORROW_              = "borrow";//借款协议
	public static final String TEMPLATE_NAME_ABOUTREPAYMENT_      = "aboutRepayment";//还款事项提醒
	public static final String TEMPLATE_NAME_LETTERAUTHORIZATION_ = "letterAuthorization";//委托授权书
	public static final String TEMPLATE_NAME_CREDITCONSULTING_    = "creditConsultingAndManagementServices";//信用咨询和管理服务协议
	
	//合同生成 --车贷
	public static final String CAR_PATH_FILE_HTML               = "/resources/contractfile/htmlfile/car/";//html文件存放路经
	public static final String CAR_PATH_FILE_PDF                = "/resources/contractfile/pdffile/car/"; //pdf文件存放路经
	public static final String CAR_PATH_FILE_ROOT               = "resources/contractfile/pdffile/car/";  //存数据库不带/
	public static final String CAR_PATH_FILE_TEMPLATES          = "/resources/contractfile/templates/car";         //车贷模板路经
	public static final String CAR_ROLLOVER_PATH_FILE_TEMPLATES = "/resources/contractfile/templates/car/rollover";//车贷展期模板路经
	//public static final String CAR_ROLLOVER_PATH_FILE_HTML      = "/resources/contractfile/htmlfile/car/rollover/";
	//public static final String CAR_ROLLOVER_PATH_FILE_PDF       = "/resources/contractfile/pdffile/car/rollover/";
	//public static final String CAR_ROLLOVER_PATH_FILE_ROOT      = "resources/contractfile/pdffile/car/rollover/";
		
	//车贷协议名
	public static final String CAR_TEMPLATE_NAME_BORROW_                    = "borrow";          //借款协议正文
	public static final String CAR_TEMPLATE_NAME_MORTGAGE_                  = "mortgage";        //抵押合同
	public static final String CAR_TEMPLATE_NAME_BORROWSUPPLEMENT_          = "borrowsupplement";//借款协议信息补充表
	public static final String CAR_TEMPLATE_NAME_INFOCONSULTING_            = "infoconsult";     //信息咨询及管理服务协议正文
	public static final String CAR_TEMPLATE_NAME_ENTRUSTAUTH_               = "entrustauthorized";//委托扣款授权书（首次）
	public static final String CAR_TEMPLATE_NAME_VEHICLE_TRADING_AUTH_      = "vehicletradingauthorization";//车辆交易授权委托书
	public static final String CAR_TEMPLATE_NAME_REMOVE_MORTGAGE_           = "removemortgage";             //车辆解除抵押委托书
	
	//车贷展期协议名
	public static final String CAR_ROLLOVER_TEMPLATE_NAME_BORROW_           = "rollover_borrow";  //展期协议正文
	public static final String CAR_ROLLOVER_TEMPLATE_NAME_ENTRUSTAUTH_      = "rollover_entrustauthorized"; //委托扣款授权书 
	public static final String CAR_ROLLOVER_TEMPLATE_NAME_MORTGAGECONTRACT_ = "rollover_mortgagecontract";//抵押合同
	public static final String CAR_ROLLOVER_TEMPLATE_NAME_MORTGAGEREGIST_   = "rollover_mortgageregistration";//车辆抵押登记承诺书
	public static final String CAR_ROLLOVER_TEMPLATE_NAME_SUPPLEMENTARY_    = "rollover_supplementary";//信息咨询及管理服务补充协议
	
	//协议文件名-chekuairong	
	public static final String CHEKUAIRONG_NAME_LOANAGREEMENT_              = "LoanAgreement";//借款协议
	public static final String CHEKUAIRONG_NAME_BORROWINGADVISORY_          = "BorrowingAdvisory";//借款咨询及管理服务协议
	public static final String CHEKUAIRONG_NAME_BORROWINGSUPPLEMENT_        = "BorrowingSupplement";//借款协议信息补充表
	/***************************************************************************************/
	
	public static final String TASK_NAME_SEPERATOR = "-";
	
	//合同编号规范
	public static final String CONTRACT_JK="JK";//借款
	public static final String CONTRACT_DB="DB";//挡保
	public static final String CONTRACT_DY="DY";//抵押
	
	
	public static final byte ATTACHEMENT_A_TYPE_CREDIT_INVESTIGATION = 1;
	
	/** 还款方式：等额本息 */
	public static final byte REPAYMENT_TYPE_AVERAGE_CAPITAL_PLUS_INTEREST = 2;
	/** 还款方式：等额本金 */
	public static final byte REPAYMENT_TYPE_AVERAGE_CAPITAL = 3;
	/** 还款方式：一次性还本付息 */
	public static final byte REPAYMENT_TYPE_ONE_TIME = 1;
	
	/** 车贷还款方式：等额本息 */
	public static final byte REPAYMENT_TYPE_AVERAGE_CAPITAL_PLUS_INTEREST_CAR_SIX = 6;
	
	/** 车贷还款方式：等额本息 */
	public static final byte REPAYMENT_TYPE_AVERAGE_CAPITAL_PLUS_INTEREST_CAR_NINE = 9;
	
	/** 巡检计划每周一生成. */
	public static final byte INSPECTION_PLAN_ONE_WEEK = 1;
	
	
	//邮件通知
	public static final String MAIL_CN_COMPANY = "融都科技";
	
/********************************** 角色定义*/
	
	//角色定义 销售
    public static final long  SALE_DEP = 2;
    
    //风控初审
    public static final long RISK_TRIAL_DEP  = 3; 
    
    
    //  //风控终审
    public static final long RISK_FINAL_DEP  = 4;
    
    //业务部
    public static final long BUSINESS_DEP  = 5;
    
    //财务
    public static final long FINACE_DEP  = 6;
    
    
    //征信
    
    public static final long CREDIT_DEP  = 7;
	public static final String FORM_CN_CREATOR = "创建者";
	public static final String FORM_CN_PLAN_VISIT_TIME = "计划回访时间";
	public static final String FORM_CN_VISITOR = "回访人";
	public static final String FORM_CN_VISIT_TYPE = "回访类型";

    /********************************** 角色定义******************************/

	public static final String SERVICE_VARIABLES = "service_variables";
	public static final String LOGIN_INFO_VARIABLES = "login_info_variables";
	public static final String TASK_INFO_VARIABLES = "task_info_variables";
    public static final String LOGIN_ROLE_INFO_VARIABLES = "login_role_info_variables";

	public static final byte IS_DELETE_NORMAL = 0;
	public static final String PROCESS_LAUNCHER = "process_launcher";
	public static final String PROCESS_LAUNCHER_ROLEID = "process_launcher_roleid";

	
	//车贷拒贷标识
	public static final String REJECT_PROCESS = "rejectProcess";
	
	public static final String UNPASS_TYPE = "unpass";
	public static final String MATERIAL_TYPE = "supply-material";
	public static final String SECONDARY_AUDIT_AMOUNT_THRESHOLD = "SECONDARY_AUDIT_AMOUNT_THRESHOLD";
	public static final String SECONDARY_FINANCIAL_CONFIRMATION_AMOUNT_THRESHOLD = "SECONDARY_FINANCIAL_CONFIRMATION_AMOUNT_THRESHOLD";
	public static final String MANAGER_CONFIRMATION_AMOUNT_THRESHOLD = "MANAGER_CONFIRMATION_AMOUNT_THRESHOLD";


    public static final Byte REPAYMENT_STATUS_REPAYING = 0;

    // 合同状态
    public static final Byte CONTRACT_STATUS_SIGNED = 1;
    public static final Byte CONTRACT_STATUS_FAILED_SIGN = 2;
    public static final Byte CONTRACT_STATUS_RESIGN = 3;
    public static final String FORM_CN_COLLATERAL_TYPE = "押品类型";
    public static final String FORM_CN_MORTGAGE_TYPE = "抵押方式";
    public static final String FORM_CN_TOTAL_MANAGERMENT_FEE = "管理费";
    public static final Byte REPAYMENT_STATUS_NORMAL = 0;
    public static final Byte PUB_PROCESS_TYPE_NOT_EXTENSION = 0;
    public static final String FORM_CN_TALK_LOG = "沟通记录";
	public static final Byte NORMAL_PROCESS_TYPE = 0;


	// 营业厅类型
    public static byte OFFICE_TYPE_BUSINESS_HALL = 2;
    
    // 销售部类型
    public static byte OFFICE_TYPE_SALE = 3;


    public static final String FORM_CN_MORTGATE_TYPE = "抵押类型";

	public static final Byte VOUCH_TYPE_CREDIT = 1;
	public static final Byte VOUCH_TYPE_CAR = 2;
	
	//流程类别
	public static final String PROCESS_DESCRIPTION_AFTER_LOAN_LEADER ="afterLoanLeader";//贷后主管
	
	public static final String CONTRACT_COMPANY_ROINCHINA = "roinchina";//华夏融创
	public static final String CONTRACT_COMPANY_HENHAODAI = "henhaodai";//很好贷

	
	public static final boolean START_NEW_REPAYMENT=true;

	//很好贷四种类型
	public static final Integer HENHAODAI_PRODUCT_A = 0;
	public static final Integer HENHAODAI_PRODUCT_B = 1;
	public static final Integer HENHAODAI_PRODUCT_C = 2;
	public static final Integer HENHAODAI_PRODUCT_D = 3;
	
	/**
	 * ORIGINAL_PROCESSINSTANCEID:贷后变更流程中的原始流程id
	 *
	 * @since 1.0.0
	 */
	public static final String ORIGINAL_PROCESSINSTANCEID="original_processInstanceId";
    /**
     * PREYREYPAYCAPITAL_FOR_EXTENSION:提前还款本金（展期申请）
     *
     * @since 1.0.0
     */
    public static final String PREYREYPAYCAPITAL_FOR_EXTENSION="preyRepayCapital_for_extension";
    /**
     * SUGGESTIONLOANAMOUNT_FOR_EXTENSION:展期建议批贷金额(展期)
     *
     * @since 1.0.0
     */
    public static final String SUGGESTIONLOANAMOUNT_FOR_EXTENSION="suggestionLoanAmount_for_extension";
}
