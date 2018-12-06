/**/

package com.li.mvpprogram.config;

/**
 * 项目中配置的常量类
 */
public final class Constants {

    /**
     * realm db版本号
     */
    public static final int DB_VERSION = 4;

    /**
     * preference文件名称
     */
    public static final String PREFERENCE_NAME = "xl_data";

    /**
     * preference 中 的key值
     */

    public static final String SP_KEY_USER_TYPE = "userType";

    public static final String SP_KEY_DEVICE_ID = "deviceId";

    public static final String SP_KEY_ACTIVITIES = "activitiesFirstShow";

    public static final String SP_KEY_WELCOME_FLAG = "welcome_flag";

    public static final String SP_KEY_SIGN_FLAG = "sign_flag";

    public static final String SP_KEY_JPUSH_ALIAS_FLAG = "jpush_alias_flag";

    public static final String SP_KEY_JPUSH_MSG_FLAG = "jpush_msg_flag";

    public static final String SP_KEY_JPUSH_EXTRAS = "jpush_extras";

    public static final String SP_KEY_HWPUSH_EXTRAS = "hw_push_extras";

    public static final String SP_KEY_XLPUSH_EXTRAS = "xl_push_extras";

    public static final String JPUSH_KEY_EXTRAS_TYPE = "type";

    public static final String JPUSH_KEY_EXTRAS_URL = "url";

    public static final String JPUSH_KEY_EXTRAS_NODEPARTYID = "jpush_nodePartyId";

    public static final String JPUSH_KEY_EXTRAS_PARTYID = "jpush_partyId";

    public static final String JPUSH_KEY_EXTRAS_MOBILEPHONE = "jpush_mobilePhone";

    public static final String UPDATE_KEY_EXTRAS_TIME_FIRST = "update_time_first";

    public static final String UPDATE_KEY_EXTRAS_TIME_SECOND = "update_time_second";

    public static final String UPDATE_KEY_EXTRAS_TIME_THIRD = "update_time_third";

    public static final String UPDATE_KEY_EXTRAS_DOWNLOAD_SUCCESS = "update_download_success";

    public static final String UPDATE_KEY_EXTRAS_IS_LOWDING = "update_is_loading";

    public static final String SP_KEY_UPLOAD_CONTACTS_TIME = "upload_contacts_time";

    public static final String SP_KEY_UPLOAD_CONTACTS_USER_ID = "upload_contacts_time_user_id";

    public static final String USER_AGENT = "User-Agent";

    public static final String AGENT = "ANDROID";

    //首页引导key
    public static final String SP_KEY_XL_HOME_GUIDE_FLAG = "xl_home_guide_flag";
    /**
     * platform
     */
    public static final String PLATFORM = "androidPhone";

    /**
     * http header中设备唯一标识did
     */
    public static final String DID = "did";
    /**
     * 默认的did
     */
    public static final String DEFAULT_DID = "-1";
    /**
     * partyId
     */
    public static final String PARTY_ID = "partyId";
    /**
     * cookie
     */
    public static final String COOKIE = "cookie";
    /**
     * 最近一次登录成功的用户手机号码
     */
    public static final String LAST_USER_PHONE = "last_user_phone";
    /**
     * 最近一次登录成功的用户头像url
     */
    public static final String LAST_USER_HEAD_IMG = "last_suer_head_img";
    /**
     * cookie preference文件名
     */
    public static final String COOKIE_PREFERENCE = "cookie_preference";
    /**
     * did preference文件名
     */
    public static final String DID_PREFERENCE = "did_preference";
    /**
     * 51活动弹框是否弹过
     */
    public static final String ACTIVITIES_PREFERENCE = "activitied_preference";
    /**
     * welcome preference文件名
     */
    public static final String WELCOME_PREFERENCE = "welcome_preference";
    /**
     * 图案密码开关preference文件名
     */
    public static final String PATTERN_PREFERENCE = "pattern_preference";

    /**
     * 签到 preference文件名
     */
    public static final String SIGN_PREFERENCE = "sign_preference";

    /**
     * jpush preference文件名
     */
    public static final String JPUSH_PREFERENCE = "jpush_preference";

    /**
     * hwpush preference文件名
     */
    public static final String HUAWEI_PREFERENCE = "huawei_preference";

    /**
     * update preference文件名
     */
    public static final String UPDATE_PREFERENCE = "update_preference";

    /**
     * guide cicle preference 文件名
     */
    public static final String CIRCLE_GUIDE_PREFERENCE = "circle_guide";
    /**
     * 首页 我的业绩入口链接地址
     */
    //public static String URL_BUSINESS_YH = "/home/nodeManager/management.html?nodePartyId=11000366";
    /**
     * 首页 借款业务入口链接地址
     */
    //public static String URL_BUSINESS_JK = "";
    /**
     * 首页 乡邻购业务入口链接地址
     */
    //public static String URL_BUSINESS_XLG = "https://mai.xianglin.cn/index.php/wap/";
    /**
     * 首页 水电煤业务入口链接地址
     */
    //public static String URL_BUSINESS_SDM = "";

    /**
     * 首页 手机充值业务入口链接地址
     */
    //public static String URL_BUSINESS_SJCZ = "https://mai.xianglin.cn/wap/index.html#/recharge/index";

    /**
     * 排名 业绩排名入口链接地址
     */
    //public static String URL_RANK_YJ = "/home/nodeManager/billboard.html?nodePartyId=11000366";


    /**
     * 排名 业绩排名入口链接地址
     */
    // public static String URL_RANK_BANK_YJ = "/home/nodeManager/bankEarningsList.html";

    /**
     * 排名 关于存款排名链接地址
     */
    //public static String URL_RANK_CK = "/home/nodeManager/ranking.html?nodePartyId=11000366";


    /**
     * 我的 站长个人信息链接地址
     */
    public static String URL_MINE_MANAGER_INFO = "/home/nodeManager/personaInfo.html?partyId=";

    /**
     * 我的 收款账户链接地址
     */
    public static String URL_MINE_ACCOUNT = "/home/network/nodeManager/accountBalance.html?markFlag=true&mobilePhone=";


    /**
     * 余额不足 去充值
     */
    public static String URL_RECHANGE = "/home/network/nodeManager/balanceRecharge.html";

    /**
     * 我的 设备管理链接地址
     */
    //public static String URL_MINE_DEVICE_MANGE = "";

    /**
     * 我的 设备押金链接地址
     */
    //public static String URL_MINE_DEVICE_DEPOSIT = "";

    /**
     * 我的 我的业绩管理链接地址
     */
    public static String URL_MINE_BANK_BIZ_MANAGE = "/home/nodeManager/management.html?nodePartyId=";

    /**
     * 我的 问卷调查链接地址
     */
    public static String URL_MINE_QUESTIONNAIRE = "/home/nodeManager/questions.html";

    /*乡邻介绍*/
    public static String URL_HOME_VILLAGER = "/home/nodeManager/villager.html";

    /**
     * 我的 联系客服经理链接地址
     */
    public static String URL_MINE_CALL_MANAGER = "/home/nodeManager/contactManager.html";

    /**
     * 交易密码修改链接地址
     */
    public static String URL_MINE_ALERT_PWD = "/home/network/nodeManager/setTradePwd.html?mobilePhone=%s&Btype=%s&partyId=%s&markFlag=%b";


    /**
     * 赚钱 我的业绩链接地址
     */
    //public static String URL_MAKEMONEY_BANK ="/home/nodeManager/bankBusiness.html?";

    /**
     * 赚钱 电商业务链接地址
     */
    //public static String URL_MAKEMONEY_SHOP ="/home/nodeManager/achievementCommission.html?";

    /**
     * 赚钱 缴费业务链接地址
     */
    //public static String URL_MAKEMONEY_PAY ="/home/nodeManager/rechargeList.html?";

    /**
     * 连连支付域名
     */
    public static final String URL_LIAN_LIAN = "https://yintong.com.cn";

    public static final String URL_GUANGFU = ENVController.H5_URL + "/home/nodeManager/guangfu.html";

    public static final String URL_NEWS_DETAIL = ENVController.H5_URL + "/home/nodeManager/topNews.html";

    /**
     * 更新提示的时间间隔
     */
    public static final long UPDATE_TIP_TIME = 6 * 60 * 60 * 1000;

    /**
     * 融云token
     */
    public static final String RONG_YUN_TOKEN = "RONG_YUN_TOKEN";

    /**
     * 友盟分享 appId&appSecret
     */
    public static final String WX_APP_ID = "wx4181f82e4f0a3026";
    public static final String WX_APP_SECRET = "d14c698f345b59e52299306724ed3f96";
    // QQ和QQ空间分享
    public static final String QQ_APP_ID = "1105692380";
    public static final String QQ_APP_KEY = "g8l6ZORU4cZADyR8";
    //新浪微博
    public static final String SINA_APP_KEY = "2203324233";
    public static final String SINA_APP_SECRET = "93621d4a25db83b91fb966e865630391";
    public static final String SINA_REDIRECT_URL = "http://sns.whalecloud.com";

    /**
     * 上传手机程序列表的过期时间
     */
    public static final String UPLOAD_APPSBEAN_EXPIRES_TIME = "UPLOAD_APPSBEAN_EXPIRES_TIME";


    /**
     * 上次弹出红包雨的时间
     */
    public static final String UPLOAD_RAD_RAIN_EXPIRES_TIME = "UPLOAD_RAD_RAIN_EXPIRES_TIME";
    public static final String UPLOAD_RAD_RAIN_FLAG = "UPLOAD_RAD_RAIN_FLAG";
    /**
     * 广场提示
     */
    public static final String CIRCLE_NOTICATION = "circle_notication";

    public static final String NODE_MANAGER = "nodeManager";

    public static final String USER = "user";

    public static final String VISITOR = "visitor";
    /**
     * go 弹窗时间
     */
    public static final String UPLOAD_GO_DIALOG_FLAG = "UPLOAD_GO_DIALOG_FLAG";

    //云知声语音开发帐号APIKEY  SECRETKEY
    public static final String APIKEY = "4hywjeewfs374yuxwptbp7gbk3zml6zqd5smelqy";

    public static final String SECRETKEY = "8ee603a98b1796272c27370de16b5ae3";

    // 默认当前组织ID
    public static final long DEFAULT_ORGINIZATION_ID = 0l;

    //显示去默认组织
    public static final String IS_SHOWD_ORG_DIALOG = "isShowOrgDialog";
    //保存是否去默认组织
    public final static String PREFERENCE_PUTORG_DIALOG = "SAVE_ORG_GIALOG_GLAG";

    //显示买火车票弹窗
    public static final String IS_SHOWD_TRAIN_DIALOG = "isShowTraomDialog";
    //保存是否买火车票弹窗
    public final static String PREFERENCE_TRAIN_DIALOG = "SAVE_TRAIN_GIALOG_GLAG";

    public final static String INVITING_FRIENDS = "/home/nodeManager/active/friendsSupport.html";

    //Face++ 活体检测结果标识
    //meglive校验字符串,根据活体认证图片生成和图片值对应
    public final static String FACEID_DELTA = "delta";
    //活体检测生成质量最好的照片
    public final static String FACEID_IMAGE_BEST = "image_best";
    //活体检测生成的全景照片
    public final static String FACEID_IMAGE_ENV = "image_env";
    //活体检测生成的照片集
    public final static String FACEID_IMAGES = "images";
    //活体检测=>对比类型 1:有源比对,0:无源比对
    public final static String FACEID_COMPARISON_TYPE_1 = "1";
    //比对图片类型=>比对人脸图像
    public final static String FACEID_IMAGE_TYPE_RAW_IMAGE = "raw_image";
    //比对图片类型=>活体比对
    public final static String FACEID_IMAGE_TYPE_MEGLIVE = "meglive";
    //身份证识别正反面标识:正面或者反面
    public final static String FACEID_IDCARD_SIDE = "side";
    //身份证正反面照片
    public final static String FACEID_IDCARD_IMAGE = "idcardImg";
    //身份证头像区域图片
    public final static String FACEID_PORTRAIT_IMAGE = "portraitImg";
    //摄像头方向
    public final static String FACEID_CAMERA_ORITATION = "isVertical";

    //个人信息收集与征信授权书
    public final static String LOAN_CREDIT_AUTHORIZATION = "/home/nodeManager/SecondsTreasure/file/loan_credit_authorization_letter.html";
    //用户须知与免责声明
    public final static String LOAN_DISCLAIMER = "/home/nodeManager/SecondsTreasure/file/loan_disclaimer_file.html";
    //贷款成功页标识
    public final static String LOAN_PRODUCT_IDENTIFICATION = "loan_product_identification";
    //是否第一次进入贷款首页
    public final static String IS_FIRST_TO_LOAN_PRODUCT = "first_to_loan_product";

    /**
     * 引导页面配置 preference文件名
     */
    public static final String GUIDE_PAGES_PREFERENCE = "guide_pages_preference";
    /**
     * 引导页面配置key
     */
    public static final String GUIDE_PAGES_KEY = "guide_pages_key";
    /**
     * 广告界面  图片配置 url 的 key
     */
    public static final String SP_KEY_AD_URL = "advertisement_url";
    /**
     * 广告界面  图片配置 preference文件名
     */
    public static final String AD_PREFERENCE = "advertisement_preference";

    /**
     * 搜索界面  历史记录 preference文件名
     */
    public static final String SEARCH_PREFERENCE = "search_history_preference";

    /**
     * 搜索界面  历史记录的 key   应用：ICON 微博：ARTICLE 全部ALL
     */
    public static final String SP_KEY_SEARCH_CONTENT = "search_history_key_all";
    public static final String SP_KEY_SEARCH_CONTENT_ICON = "search_history_key_icon";
    public static final String SP_KEY_SEARCH_CONTENT_ARTICAL = "search_history_key_artical";

    /**
     * 新消息通知 preference文件名
     */
    public static final String NOTIFICATION_PREFERENCE = "notification_preference";

    /**
     * 新消息通知 key
     */
    public static final String SP_KEY_NOTIFICATION_PREFERENCE_FLAG = "notification_preference_flag";

    //招工preference 文件
    public static final String RECRUIT_GUIDE_PREFERENCE = "recruit_guide_preference";

    //记录用户是否已经进入过招工模块
    public static final String SP_KEY_RECRUIT_TO_HOME = "recruit_to_home";
}
