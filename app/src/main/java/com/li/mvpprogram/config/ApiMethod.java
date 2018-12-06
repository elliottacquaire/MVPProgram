/**/

package com.li.mvpprogram.config;

/**
 * 接口方法
 */
public interface ApiMethod {

    String FACADE_PATH = "";
    String ACT_PATH = "";

    String METHOD_LOGOUT = FACADE_PATH + ".AppLoginService.logout";

    String METHOD_GET_MOBILE_BY_NODE_CODE = FACADE_PATH + ".AppLoginService.getMobileByNodeCode";

    String METHOD_RESET_USER_LOGIN = FACADE_PATH + ".AppLoginService.resetUserLogin";

    String METHDO_USER_FIRST_LOGIN = FACADE_PATH + ".AppLoginService.userFirstLogin";

    String METHOD_SEND_SMS = FACADE_PATH + ".AppLoginService.sendSms";

    String METHOD_SMS_CODE_SEND = FACADE_PATH + ".AppLoginService.smsCodeSend";

    String METHOD_APP_BANNER_DATA = FACADE_PATH + ".app.XLAppIndexPageService.getBanerList";

    String METHOD_APP_BUSSINESS_DATA = FACADE_PATH + ".app.XLAppIndexPageService.getBusinessList";

    String METHOD_APP_HOME_DATA = FACADE_PATH + ".app.XLAppIndexPageService.getXLAppHomeData";

    String METHOD_BUSI_RANK_DATA = FACADE_PATH + ".app.XLAppIndexPageService.getBusiVisitUrlInfo";


    //更多月份
    String METHOD_GETPROFITDTOLIST = FACADE_PATH + ".app.EarnPageService.getProfitDetailList";

    //赚钱首页
    String METHOD_GETEARNHOMEDATA = FACADE_PATH + ".app.EarnPageService.getEarnHomeData";

    //众邦宝报表查询
    String METHOD_NODE_ACHIEVE_DETAIL = FACADE_PATH + ".app.EarnPageService.queryNodeAchieveDetailV2";
    //众邦宝历史
    String METHOD_IMPORT_LIST = FACADE_PATH + ".app.EarnPageService.queryImportList";
    //众邦宝明细查询
    String METHOD_LAST_IMPORT = FACADE_PATH + ".app.EarnPageService.queryLastImport";

    String METHOD_AUTO_LOGIN = FACADE_PATH + ".AppLoginService.autoLogin";
    // 自定登录接口v1.3
    String METHOD_AUTO_LOGIN_VOPT = FACADE_PATH + ".AppLoginService.autoLoginVOPT";

    String METHOD_LOGIN = FACADE_PATH + ".AppLoginService.loginByMobile";

    String METHOD_LOGIN_BY_MOBILE_V1_3 = FACADE_PATH + ".AppLoginService.loginByMobileV1_3";

    String METHOD_ACTIVATE_DEVICE = FACADE_PATH + ".DeviceInfoService.activateDevice";

    String METHOD_IS_EXIST_PARTY_ATTR_ACCOUNT = FACADE_PATH + ".app.XLAppIndexPageService.isExistPartyAttrAccount";


    String METHOD_APP_MESSAGE_LIST = FACADE_PATH + ".app.XLAppIndexPageService.getMsgList";

    String METHOD_VERSIION = FACADE_PATH + ".app.XLAppIndexPageService.version";

    String METHOD_USER_UPLOAD_APP = FACADE_PATH + ".AppInstallUploadService.uploadAppInstallInfo";


    String METHOD_SUBMITPUSHINFO = FACADE_PATH + ".app.XLAppIndexPageService.submitPushInfo";


    //新人领取红包
    String METHOD_CASHREDPACKET = FACADE_PATH + ".RedPacketService.cashRedPacket";
    //推送统计
    String METHOD_UPDATE_PUSHMSG = FACADE_PATH + ".MessageService.updatePushMsg";

    String METHOD_POINT_RUSH = FACADE_PATH + ".RedPacketService.getPointRushList";

    //交易密码状态
    String METHOD_TRANSACTION_PWD_STATUS = FACADE_PATH + ".app.PersonInfoService.isSetTradePassword";

    //查询站长个人信息
    String METHOD_MANAGER_INFOMATION = FACADE_PATH + ".app.PersonInfoService.getPersonInfo";


    //我的业绩当前站长数据汇总
    String METHOD_BANKBUSINESS_STATION = FACADE_PATH + ".PresentedFlowersService.getAgentDetail";

    //我的业绩当前排行榜单
    String METHOD_BANKBUSINESS_RANKING = FACADE_PATH + ".PresentedFlowersService.queryRankList";

    //我的业绩献花
    String METHOD_BANKBUSINESS_XIANHUA = FACADE_PATH + ".PresentedFlowersService.insertFlowersLog";

    //献花列表查询
    String METHOD_QUERY_TODAY_RECORD_NODE_FLOWERS = FACADE_PATH + ".PresentedFlowersService.queryHistoryRecordNode";

    //打赏列表查询
    String METHOD_QUERY_TODAY_RECORD_NODE = FACADE_PATH + ".RewardService.queryHistoryRecordNode";


    //1.3.0 我的
    //修改头像
    String METHOD_UP_LOAD_IMG = FACADE_PATH + ".UserService.uploadImg";
    //获取个人信息

    //首页banner
    String METHOD_QUERY_INDEXBANNERS = FACADE_PATH + ".app.IndexService.indexBannersV2";
    //首页业务菜单
    String METHOD_QUERY_INDEXBUSINESS = FACADE_PATH + ".app.IndexService.indexBusinessV3";
    //记录首页业务菜单点击行为
    String METHOD_CLICK_BUSINESS = FACADE_PATH + ".app.IndexService.clickBusiness";
    //获取个人信息
    String METHOD_GET_USERINFORM = FACADE_PATH + ".UserService.getUserInfo";
    // 新闻列表查询
    String METHOD_INDEX_NEWS_MSG = FACADE_PATH + ".app.IndexService.indexNewsMsg";
    //修改个人信息
    String METHOD_UPDATA_USERINFORM = FACADE_PATH + ".UserService.updateUserInfo";

    /*是否有未读消息*/
    String METHOD_HAS_UNREADMSG = FACADE_PATH + ".MessageService.hasUnReadMsg";
    /*是否领取新人红包*/
    String METHOD_ISCASHREDPACKET = FACADE_PATH + ".RedPacketService.isCashRedPacket";

    String METHOD_TOTAL_SOLAR_DATAVO = FACADE_PATH + ".app.XLAppIndexPageService.getTotalSolarData";
    //我的 收款账户 去提现 显示
    String METHOD_IS_SHOW_ACCOUNT_TIPS = FACADE_PATH + ".app.PersonInfoService.isShowAccountTips";

    //修改手机 验证当前手机号
    String METHOD_VALIDATESMS = FACADE_PATH + ".AppLoginService.validateSms";

    //修改手机 绑定当前手机号
    String METHOD_BIND_NEW_PHONE = FACADE_PATH + ".UserService.bindNewPhone";

    // 便民列表
    String METHOD_GET_APP_MENUS = FACADE_PATH + ".AppMenuService.getAppMenus";

    //我的页面 各个服务显示
    String METHOD_PERSON_INFORM_SERVICE = FACADE_PATH + ".app.PersonInfoService.getRowInfo";

    // 查询个人推荐基本信息
    String METHOD_GET_INVITE_INFO = FACADE_PATH + ".app.ActivityInviteService.inviteInfo";

    // 查询当前用户排名
    String METHOD_GET_INVITE_USER_RANKING = FACADE_PATH + ".app.ActivityInviteService.inviteUserRanking";

    // 分页查询邀请排行榜
    String METHOD_GET_INVITE_INVITE_RANKING = FACADE_PATH + ".app.ActivityInviteService.inviteRanking";

    //显示邀请的新人数
    String METHOD_GET_INVITE_INVITE_INVITEALERT = FACADE_PATH + ".app.ActivityInviteService.inviteAlert";

    //我的粉丝/关注
    String METHOD_GET_LIST_FOLLOW_OR_FANS = FACADE_PATH + ".UserRelationService.queryFollowsOrFans";

    //邀请好友列表
    String METHOD_INVITE_DETAIL = FACADE_PATH + ".app.ActivityInviteService.inviteDetail";

    //找朋友
    String METHOD_GET_LIST_FIND_FRIENDS = FACADE_PATH + ".UserRelationService.findFriends";
    //他的粉丝和关注列表
    String METHOD_QUERY_RELATION_LIST = FACADE_PATH + ".UserRelationService.queryRelationList";

    //关注和取消关注
    String METHOD_FOLLOW = FACADE_PATH + ".UserRelationService.follow";
    //根据PartyId查询个人信息
    String METHOD_GET_INVITE_GET_USER_BY_PARTY_ID = FACADE_PATH + ".UserService.getUserByPartyId";

    //查找联系人
    String METHOD_QUERY_CONTACT = FACADE_PATH + ".UserRelationService.queryContact";

    //查询账户余额
    String METHOD_QUERY_ACCOUNT_BALANCE = FACADE_PATH + ".RedPacketService.queryAcctBalance";

    //发送红包
    String METHOD_SEND_RED_PACKET = FACADE_PATH + ".RedPacketService.sendRedPacket";

    //查看红包
    String METHOD_QUERY_RED_PACKET = FACADE_PATH + ".RedPacketService.queryRedPacketDetail";

    // 换肤
    String METHOD_THEME_SWITCH = FACADE_PATH + ".app.IndexService.themeSwitch";


    //广场微博列表
    String METHOD_QUERY_CIRCLE_LIST = FACADE_PATH + ".app.ArticleService.queryArticleList";

    // 微博页评论列表
    String METHOD_QUERY_COMMENTS_LIST = FACADE_PATH + ".app.ArticleService.queryCommentsList";

    // 最热评论列表
    String METHOD_QUERY_TIP_COMMENTS_LIST = FACADE_PATH + ".app.ArticleService.queryTopComments";

    // 评论点赞
    String METHOD_PRAISE_ARTICLE = FACADE_PATH + ".app.ArticleService.praiseArticle";

    //个人发表的微博
    String METHOD_QUERY_PUBLISHARTICLE = FACADE_PATH + ".app.ArticleService.publishArticle";

    //回复微博
    String METHOD_QUERY_PUBLISHCOMMENTS = FACADE_PATH + ".app.ArticleService.publishComments";

    // 个人发表的微博数
    String METHOD_QUERY_ARTUCLE_COUNT = FACADE_PATH + ".app.ArticleService.queryArticleCount";

    // 微博明细查询
    String METHOD_QUERY_ARTICLE = FACADE_PATH + ".app.ArticleService.queryArticle";

    // 评论删除
    String METHOD_DELETE_COMMENTS = FACADE_PATH + ".app.ArticleService.deleteComments";

    // 个人微博删除
    String METHOD_DELETE_ARTICLE = FACADE_PATH + ".app.ArticleService.deleteArticle";

    // 获取举报列表
    String METHOD_QUERY_REPROT_LIST = FACADE_PATH + ".app.ArticleService.queryReprotList";

    // 举报
    String METHOD_REPORT = FACADE_PATH + ".app.ArticleService.report";

    // 举报用户
    String METHOD_REPORT_USER = FACADE_PATH + ".app.ArticleService.reportUser";

    // 微博收藏
    String METHOD_COLLECT_ARTICAL = FACADE_PATH + ".app.ArticleService.collectArticle";

    // 删除微博收藏
    String METHOD_DELETE_COLLECT_ARTICAL = FACADE_PATH + ".app.ArticleService.removeCollectArticle";

    //获取收藏的微博列表
    String METHOD_QUERY_COLLECT_LIST = FACADE_PATH + ".app.ArticleService.queryCollectArticle";

    //个人收藏短视频分页查询
    String METHOD_QUERY_COLLECT_LIST_V2 = FACADE_PATH + ".app.ArticleService.queryCollectArticleV2";

    // 显示是否弹出红包雨提示
    String METHOD_QUERY_QUERY_SHARE_ALERT = FACADE_PATH + ".app.ActivityShareService.queryShareAlert";
    //确认每日任务提示框
    String METHOD_CONFIRM_SHARE_ALERT = FACADE_PATH + ".app.ActivityShareService.confirmShareAlert";

    //5个赞是否完成
    String METHOD_QUERY_SHARE_PROGESS_ALERT = FACADE_PATH + ".app.ActivityShareService.queryShareProgessAlert";
    //设置不再弹出分享框
    String METHOD_COMFIRM_SHARE_PROGESS_ALERT = FACADE_PATH + ".app.ActivityShareService.confirmShareProgessAlert";
    //查询评论和点赞的消息提醒,分享列表
    String METHOD_QUERY_XYCOMMENTS_LIST = FACADE_PATH + ".app.ArticleService.queryArticleTipList";

    //查询未读评论和赞数
    String METHOD_QUERY_ARTICLE_TIPS = FACADE_PATH + ".app.ArticleService.queryArticleTips";

    // 查询站点业绩明细
    String METHOD_QUERY_NODE_ACHIEVE_DETAIL = FACADE_PATH + ".app.EarnPageService.queryNodeAchieveDetail";

    // 查询站长最新银行业绩
    String METHOD_QUERY_LAST_BANK_ACHIEVE = FACADE_PATH + ".app.EarnPageService.queryLastBankAchieve";

    //微博分享明细查询
    String METHOD_QUERY_ARTICLE_SHARE_INFO = FACADE_PATH + ".app.ArticleService.queryArticleShareInfo";

    //我的业绩历史记录
    String METHOD_QUERY_BANKACHIEVES_HITORY__LIST = FACADE_PATH + ".app.EarnPageService.queryBankAchieves";
    //热门微博
    String METHOD_QUERY_HOME_INDEX_ARTICLE_LIST = FACADE_PATH + ".app.IndexService.indexArticle";

    // 设置/重置登录密码
    String METHOD_RESET_PASSWORD = FACADE_PATH + ".AppLoginService.resetPassword";
    // 用户名密码登录
    String METHOD_LOGIN_BY_PASSWORD = FACADE_PATH + ".AppLoginService.loginByPassword";
    //活动推荐
    String METHOD_INDEX_ACTIVITY = FACADE_PATH + ".app.IndexService.indexActivity";
    //全部应用
    String METHOD_INDEX_INDEXBUSINESSALL = FACADE_PATH + ".app.IndexService.indexBusinessAll";
    //头条
    String METHOD_INDEX_INDEX_POINT_RUSH = FACADE_PATH + ".app.IndexService.indexPointRush";
    //发现-活动中心
    String METHOD_QUERY_ACTIVITY_URL = FACADE_PATH + ".app.DiscoryService.discoryActivityUrl";
    //发现-活动专享
    String METHOD_QUERY_PRIVILEGE_URL = FACADE_PATH + ".app.DiscoryService.discoryDiscount";
    //乡邻公告未读数
    String METHOD_QUERY_XXGG_UNREAD_COUNT = FACADE_PATH + ".app.PersonalService.unReadMsgCount";
    // 我的设置信息
    String METHOD_PERSON_SET_INFO = FACADE_PATH + ".app.PersonalService.personSetInfo";
    // 意见反馈
    String METHOD_SUBMIT_SUGGESTION = FACADE_PATH + ".app.PersonalService.submitFeedback";
    //站长月报
    String METHOD_QUERY_MONTHREPORTS_LIST = FACADE_PATH + ".NodeGrowUpService.queryMonthReports";
    // 系统参数配置查询
    String METHOD_QUERY_PARA = FACADE_PATH + ".app.SystemParaService.queryPara";

    //首页微博推荐
    String METHOD_QUERY_INDEXARTICLE = FACADE_PATH + ".app.IndexService.indexAlert";

    // 查询个人在我的页面显示信息
    String METHOD_QUERY_PERSON_INFO = FACADE_PATH + ".app.PersonalService.personInfo";
    //活动弹框点击永不提示接口
    String METHOD_QUERY_INDEX_ALERT_CONFIRM = FACADE_PATH + ".app.IndexService.indexAlertConfirm";
    //批量删除乡邻公告接口
    String METHOD_DELETE_XLGG_MESSAGE = FACADE_PATH + ".app.PersonalService.deleteUserMsg";
    // 判断用户是否设置登陆密码
    String METHOD_HAS_PASSWORD = FACADE_PATH + ".AppLoginService.hasPassword";
    //明细界面预算剩余，支出BudgetTotal
    String METHOD_QUERY_BUGETTOTAL = FACADE_PATH + ".app.FilofaxService.queryBudgetTotal";

    //明细界面列表
    String METHOD_QUERY_BUDGETDETAIL = FACADE_PATH + ".app.FilofaxService.queryBudgetDetail";
    //明细界面删除
    String METHOD_DELETE_FILOFAXDETAIL = FACADE_PATH + ".app.FilofaxService.deleteFilofaxDetail";

    //个人账号统计明细查询
    String METHOD_QUERY_ACCOUNT_TOTAL = FACADE_PATH + ".app.FilofaxService.queryAccountTotal";
    // 查询个人类别
    String METHOD_QUERY_CATEGORY_LIST = FACADE_PATH + ".app.FilofaxService.queryCategoryList";
    //记一笔，新增账户明细
    String METHOD_ADD_FILOFAX_DETAIL = FACADE_PATH + ".app.FilofaxService.addFilofaxDetail";

    // 查询时间区域内日占比
    String METHOD_QUERY_DAILYPROPORTION = FACADE_PATH + ".app.FilofaxService.queryDailyProportion";

    // 预算设置界面查询预算金额
    String METHOD_QUERY_BUDGET = FACADE_PATH + ".app.FilofaxService.queryBudget";

    // 预算设置界面更新预算金额
    String METHOD_UPDATE_BUDGET = FACADE_PATH + ".app.FilofaxService.updateBudget";

    // 钱包更新个人账号信息
    String METHOD_UPDATE_ACCOUNT = FACADE_PATH + ".app.FilofaxService.updateAccount";

    // 查询个人账户列表
    String METHOD_QUERY_ACCOUNT_LIST = FACADE_PATH + ".app.FilofaxService.queryAccountList";

    // 新增类别
    String METHOD_ADD_CATEGORY = FACADE_PATH + ".app.FilofaxService.addCategory";

    // 提交实名认证信息，并实名认证
    String METHOD_ADD_REAL_NAME = FACADE_PATH + ".app.PersonalService.addRealName";

    // 查询实名认证信息
    String METHOD_QUERY_REAL_NAME = FACADE_PATH + ".app.PersonalService.queryRealName";

    // 服务器端统计分享次数
    String METHOD_SHARE_ARTICLE = FACADE_PATH + ".app.ArticleService.shareArticle";

    // 区域信息服务接口
    String METHOD_QUERY_DISTRICT_LIST = FACADE_PATH + ".app.PersonalService.queryDistrictList";

    // 微博分页查询V2
    String METHOD_QUERY_ARTICLE_LIST_V2 = FACADE_PATH + ".app.ArticleService.queryArticleListV2";

    // 新增账户
    String METHOD_ADD_ACCOUNT = FACADE_PATH + ".app.FilofaxService.addAccount";

    // 删除账户
    String METHOD_DELETE_ACCOUNT = FACADE_PATH + ".app.FilofaxService.deleteAccount";

    // 现金等账户界面，流入流出查询
    String METHOD_QUERY_CASH_OUTANDSUM = FACADE_PATH + ".app.FilofaxService.queryInSumAndOutSum";

    //获取用户默认商家
    String METHOD_QUERY_DEFAULT_MERCHANT = FACADE_PATH + ".app.AccountantMerchantService.queryDefaultMerchant";

    //提交订单信息
    String METHOD_SUBMIT_PREORDER = FACADE_PATH + ".app.AccountantMerchantService.submitPreOrder";

    //查询订单状态
    String METHOD_QUERY_ORDERDTATUS = FACADE_PATH + ".app.AccountantMerchantService.queryOrderStatus";

    //创建群可拉入群用户查询
    String METHOD_QUERY_CREATEMEMBERS = FACADE_PATH + ".app.GroupService.createMembers";

    //个人组织列表查询
    String METHOD_QUERY_LISTORGANIZE_MINE = FACADE_PATH + ".app.OrganizeService.listOrganize";

    // 微博公开范围个人组织列表查询
    String METHOD_QUERY_PUBLIC_SCOPE_MINE = FACADE_PATH + ".app.OrganizeService.listOrganizeV2";

    /**
     * 发布短视频
     */
    String METHOD_PUBLISH_SHORT_VIDEO = FACADE_PATH + ".app.ArticleService.publishShortVideo";
    /**
     * 查询话题
     */
    String METHOD_QUERY_TOPIC_LIST = FACADE_PATH + ".app.ArticleService.queryTopicList";


    //创建组织
    String METHOD_QUERY_CREATE_MINE = FACADE_PATH + ".app.OrganizeService.create";

    //组织明细
    String METHOD_QUERY_ORGANIZEDETAIL = FACADE_PATH + ".app.OrganizeService.organizeDetail";

    //删除组织成员
    String METHOD_QUERY_DELETEMEMBER = FACADE_PATH + ".app.OrganizeService.deleteMember";

    //组织公告列表查询
    String METHOD_QUERY_QUERYNOTICELIST = FACADE_PATH + ".app.OrganizeService.queryNoticeList";

    //发布群公告
    String METHOD_QUERY_PUBLISHNOTICE = FACADE_PATH + ".app.OrganizeService.publishNotice";

    //公告明细
    String METHOD_QUERY_QUERYNOTICE = FACADE_PATH + ".app.OrganizeService.queryNotice";

    //转让管理员
    String METHOD_QUERY_ASSIGNMANAGER = FACADE_PATH + ".app.OrganizeService.assignManager";

    //退出组织
    String METHOD_QUERY_EXIT = FACADE_PATH + ".app.OrganizeService.exit";

    //批量添加组织成员
    String METHOD_ADDMEMBERS = FACADE_PATH + ".app.OrganizeService.batchAddmember";

    //添加单个组织成员
    String METHOD_ADDMEMBER = FACADE_PATH + ".app.OrganizeService.addMember";

    //确认申请
    String METHOD_CONFIRMAPPLY = FACADE_PATH + ".app.OrganizeService.confirmApply";

    //组织加入申请列表
    String METHOD_LISTAPPLY = FACADE_PATH + ".app.OrganizeService.listApply";

    //查询当前用户默认组织
    String METHOD_DEFAULTORGANIZE = FACADE_PATH + ".app.OrganizeService.defaultOrganize";

    //创建群时本地联系人分析
    String METHOD_CREATE_NATIVEMEMBERS = FACADE_PATH + ".app.GroupService.createNativeMembers";

    //用户密码登陆
    String METHOD_LOGIN_PASSWORD_V3 = FACADE_PATH + ".AppLoginService.loginPasswordV3";

    // 用户验证码登录
    String METHOD_LOGIN_MOBILE_V3 = FACADE_PATH + ".AppLoginService.loginMobileV3";

    // 设置登录密码
    String METHOD_SET_PASSWORD = FACADE_PATH + ".AppLoginService.setPassword";

    // 登录流程，用户实名认证
    String METHOD_REAL_NAME_AUTH_V3 = FACADE_PATH + ".AppLoginService.realNameAuthV3";

    // 完善用户资料
    String METHOD_SUBMIT_USER_INFO = FACADE_PATH + ".AppLoginService.submitUserInfo";

    // 查询当前登录用户信息
    String METHOD_QUERY_PERSONAL = FACADE_PATH + ".app.PersonalService.queryPersonal";

    // 自动登录V2
    String METHOD_AUTO_LOGIN_V3 = FACADE_PATH + ".AppLoginService.autoLoginV3";

    //组织搜索
    String METHOD_QUERYORGANIZE = FACADE_PATH + ".app.OrganizeService.queryOrganize";

    //申请加入组织
    String METHOD_QUERYORAPPLY = FACADE_PATH + ".app.OrganizeService.apply";

    // 根据partyId查询用户
    String METHOD_QUERY_USER = FACADE_PATH + ".app.PersonalService.queryUser";

    // 修改用户信息
    String METHOD_UPDATE_USER = FACADE_PATH + ".app.PersonalService.updateUser";

    // 查询当前登陆用户站点信息（针对站长）
    String METHOD_QUERY_NODE_INFO = FACADE_PATH + ".app.PersonalService.queryNodeInfo";
    //查询当前用户所有群
    String METHOD_QUERY_GROUPLIST = FACADE_PATH + ".app.GroupService.list";

    //创建群
    String METHOD_CREATE_GROUP = FACADE_PATH + ".app.GroupService.create";

    //根据融云群id查询群信息
    String METHOD_QUERY_GROUP_BYRUID = FACADE_PATH + ".app.GroupService.queryGroupByRUId";

    //更新群信息
    String METHOD_UPDATA_GROUP = FACADE_PATH + ".app.GroupService.update";

    //批量删除群成员
    String METHOD_DELETE_GROUPMEMBERRS = FACADE_PATH + ".app.GroupService.batchDeleteMember";

    //解散群
    String METHOD_DISSMISS_GROUP = FACADE_PATH + ".app.GroupService.dismiss";

    //转让群主
    String METHOD_ASSIGN_MANAGER = FACADE_PATH + ".app.GroupService.assignManager";

    //批量添加群成员
    String METHOD_ADD_GROUPMEMBER = FACADE_PATH + ".app.GroupService.addNativeMembers";

    // 获取验证码
    String METHOD_SMS_CODE_SEND_V2 = FACADE_PATH + ".AppLoginService.smsCodeSendV2";

    // 退出群聊
    String METHOD_EXIT_GROUP = FACADE_PATH + ".app.GroupService.exit";

    // 发送短信邀请好友
    String METHOD_QUERY_SENDMESSAGE = FACADE_PATH + ".app.OrganizeService.sendMessage";

    // 秒息宝通用接口(需要登录)
    String METHOD_CURRENCY_INTEGERESTLOGIN = FACADE_PATH + ".app.InterestService.busiRequestStr";

    // 秒息宝理财基金产品信息查询(不需要登录)
    String METHOD_FINANCE_GETPRODUCTINFO = FACADE_PATH + ".app.InterestService.getFnZyProductInfo";
    //查询订单的优惠金额和URL
    String METHOD_SUBMIT_SUNAMOUNTANDURI = FACADE_PATH + ".app.AccountantMerchantService.querySubAmountAndUrl";

    //删除申请
    String METHOD_QUERY_DELETEAPPLY = FACADE_PATH + ".app.OrganizeService.deleteApply";

    //查询分享二维码地址，带头像logo
    String METHOD_QUERY_QRCODE_URL = FACADE_PATH + ".app.PersonalService.queryQrCode";

    //交易密码
    String METHOD_VERIFY_TRANSACTION_PASSWORD_PATH = FACADE_PATH + ".app.InterestService.confirmPassword";

    //根据姓名身份证验证是否为当前app用户
    String METHOD_CONFIRM_USERNAME_AND_IDNUMBER_PATH = FACADE_PATH + ".app.InterestService.confirmUserNameAndIdNumber";
    //交易密码确认
    String CONFIRM_PASSWORD = FACADE_PATH + ".app.InterestService.confirmPassword";
    //查询默认村
    String METHOD_QUERY_DEFAULT_VILLAGE_PATH = FACADE_PATH + ".app.IndexService.queryDefaultVillage";

    //微博分页查询
    String METHOD_QUERY_ARTICLE_LIST_V3_PATH = FACADE_PATH + ".app.ArticleService.queryArticleListV3";

    //提醒未读数
    String METHOD_QUERY_ARTICLE_TIPS_V1_PATH = FACADE_PATH + ".app.ArticleService.queryArticleTipsV1";

    //提醒列表
    String METHOD_QUERY_ARTICLE_TIP_LIST_V1_PATH = FACADE_PATH + ".app.ArticleService.queryArticleTipListV1";

    //发表广播，村务 3.4
    String METHOD_QUERY_PUBLISHARTICALV1 = FACADE_PATH + ".app.ArticleService.publishArticleV1";

    //发表广播，村务 明细查询接口
    String METHOD_QUERY_ARTICAL = FACADE_PATH + ".app.ArticleService.queryArticle";

    //广播，村务 分页查询V3 3.4
    String METHOD_QUERY_ARTICALLISTV3 = FACADE_PATH + ".app.ArticleService.queryArticleListV3";

    // 返回所有群成员，村成员
    String METHOD_QUERY_LISTMEMBERS = FACADE_PATH + ".app.GroupService.listMembers";

    // 已推荐新闻查询 (V3.4）
    String METHOD_LIST_USER_NEWS = FACADE_PATH + ".app.DiscoryService.listUserNews";

    // 新闻更新（阅读，分享）（V3.4）
    String METHOD_OPERATE_NEWS = FACADE_PATH + ".app.DiscoryService.operateNews";

    // 新闻推荐（V3.4）
    String METHOD_RECOMMEND_NEWS = FACADE_PATH + ".app.DiscoryService.recommendNews";

    // 推荐好友（V3.4）
    String METHOD_RECOMMEND_FRIENDS = FACADE_PATH + ".UserRelationService.recommendFriend";

    //获取注册金币数量
    String REGISTER_GOLD = FACADE_PATH + ".app.GoldService.queryRegisterGold";

    //获取签到金币数量
    String SIGN_GOLD = FACADE_PATH + ".app.GoldService.querySignGold";

    //签到
    String SIGN = FACADE_PATH + ".app.GoldService.sign";

    //获取游戏列表
    String GAMES_LIST = FACADE_PATH + ".app.IndexService.queryGame";

    //修改游戏点击量
    String UPDATE_CLICK_GAME = FACADE_PATH + ".app.IndexService.updateClickGame";

    //新闻详情
    String NEWS_DETIAL = FACADE_PATH + ".app.DiscoryService.newsDetail";

    // 查询频道列表
    String METHOD_QUERY_CHANNEL = FACADE_PATH + ".app.DiscoryService.queryChannel";

    // 修改频道列表
    String METHOD_UPDATE_CHANNEL = FACADE_PATH + ".app.DiscoryService.updateChannel";

    // 发奖励
    String METHOD_AWARDGOLE = FACADE_PATH + ".app.GoldService.award";

    //查询晒收入奖励金额
    String SHARE_INCOME = FACADE_PATH + ".app.GoldService.queryRewardAmountByCategary";

    //乡邻购未读数
    String METHOD_QUERY_BUY_UNREADCOUNT = FACADE_PATH + ".app.PersonalService.unReadMsgCountV2";

    //查找招工信息
    String RECURITMENT = FACADE_PATH + ".app.ArticleService.queryRecruitment";

    //查询最近发布招工的地址
    String RECURITMENT_ADDRESS = FACADE_PATH + ".app.ArticleService.queryUserUsedAddress";

    //查询乡邻购列表
    String QUERY_SHOPPING_MESSAGE = FACADE_PATH + ".app.PersonalService.listUserMsg";

    //查询最新一条乡邻购消息
    String QUERY_SHOPPING_FIRSTMSG = FACADE_PATH + ".app.PersonalService.queryFirstMsg";

    //首页乡邻账单是否弹窗
    String BILLS = FACADE_PATH + ".app.IndexService.queryUserAnnualReportPopup";

    //倒计时
    String SPRING_FESTIVAL = FACADE_PATH + ".app.IndexService.queryCycleDays";

    //是否是村和群管理员
    String VILLAGE_AND_GROUP_MANAGER = FACADE_PATH + ".app.OrganizeService.isVillageAndGroupManager";

    //秒息宝充值
    String METHOD_FINANCE_RECHARGEMONEY = FACADE_PATH + ".app.InterestService.rechargeMoney";

    //秒息宝提现
    String METHOD_FINANCE_WITHDRAWCASH = FACADE_PATH + ".app.InterestService.withdrawCash";

    //秒息宝申购
    String METHOD_FINANCE_FUNDPURCHASE = FACADE_PATH + ".app.InterestService.fundPurchase";

    //秒息宝基金赎回
    String METHOD_FINANCE_FUNDREDEEM = FACADE_PATH + ".app.InterestService.fundRedeem";

    //秒息宝预申请用户访问令牌信息
    String METHOD_FINANCE_APPLY_FINANCETOKEN = FACADE_PATH + ".app.InterestService.preApplyChannelAccessToken";

    //拷贝家谱-获取内链Id（消息）
    String METHOD_GET_FAMTREE_LINK = FACADE_PATH + ".app.UserGenealogyService.privateCopyGenealogysId";
    //一键添加家谱 获取添加家谱id
    String METHOD_GET_FAMTREE_ADD = FACADE_PATH + ".app.UserGenealogyService.publicAddGenealogyId";

    //银行业务理财数据记录
    String METHOD_QUERY_FINANCIAL_RECORD_LIST = FACADE_PATH + ".app.EarnPageService.getFinanceDataByNodePartyId";

    //51活动
    String METHOD_SELECTACT = "com.xianglin.act.common.service.facade.ActService.selectAct";
    //新人好礼的红包是否打开过
    String METHOD_OPEN = "com.xianglin.act.biz.service.implement.ActServiceImpl.open";

    //修改图案密码
    String METHOD_SET_PATTERN_PWD = FACADE_PATH + ".AppLoginService.setPatternPassword";

    // 用户登录(注册)v4
    String METHOD_LOGIN_V4 = FACADE_PATH + ".AppLoginService.loginV4";
    // 自动登录v4
    String METHOD_AUTO_LOGIN_V4 = FACADE_PATH + ".AppLoginService.autoLoginV4";

    //判断用户是否设置密码
    String METHOD_HAS_PASSWORD_V2 = FACADE_PATH + ".AppLoginService.hasPasswordV2";

    /**
     * 步步生金活动
     * 同步数据：SYNCH_STEP_DETAIL
     * 查询用户步步生金活动总量：QUERY_STEP_TOTAIL
     * 兑换金币
     */
    String SYNCH_STEP_DETAIL = ACT_PATH + ".StepService.synchStepDetail";
    String QUERY_STEP_TOTAIL = ACT_PATH + ".StepService.queryStepTotail";
    String REWARD = ACT_PATH + ".StepService.reward";
    String QUERY_SYSCONFIG_VAULE = ACT_PATH + ".SysConfigService.querySysConfigVaule";

    //全部应用
    String METHOD_INDEX_BUSINESS_ALL_V2 = FACADE_PATH + ".app.IndexService.indexBusinessAllV2";

    // 查询推送消息轮询开关
    String METHOD_QUERY_PUSH_STATUS = FACADE_PATH + ".MessageService.queryPushStatus";

    // 查询带推送消息
    String METHOD_QUERY_PUSH_MSG = FACADE_PATH + ".MessageService.queryPushMsg";

    // 客户端上送日志
    String METHOD_PUT_CLICNET_LOG = FACADE_PATH + ".app.LogService.putClientLog";

    //查询是否是账房用户
    String METHOD_QUERY_MERCHANT_STATUS = FACADE_PATH + ".app.AccountantMerchantService.queryMerchantStatus";

    //是否启用启动页
    String METHOD_QUERY_ISSHOWADACTIVITY = FACADE_PATH + ".app.SystemParaService.queryPara";

    //查询启动页配置
    String METHOD_QUERY_STARTPAGE = FACADE_PATH + ".app.IndexService.queryStartPage";

    //查询启动页配置
    String METHOD_QUERY_BUSINESSARTICLE = FACADE_PATH + ".app.IndexService.queryBusinessArticleByqueryKey";

    //查询关注微博
    String METHOD_QUERY_FOLLOW_ARTICLE = FACADE_PATH + ".app.ArticleService.queryFollowArticle";

    //查询感兴趣微博
    String METHOD_QUERY_RECOMMEND_ARTICLE = FACADE_PATH + ".app.ArticleService.queryRecommendArticle";

    // 首页查询新闻
    String METHOD_QUERY_INDEX_MSG = FACADE_PATH + ".app.IndexService.queryIndexMsg";

    //首页运营位
    String METHOD_QUERY_OPEARTE_POSITION = FACADE_PATH + ".app.IndexService.queryOpeartePosition";

    //4.0首页豆腐块
    String METHOD_QUERY_INDEXBUSINESS4 = FACADE_PATH + ".app.IndexService.indexBusinessV4";

    //4.0有新动态
    String METHOD_QUERY_NEW_ARTICLE_USER = FACADE_PATH + ".app.PersonalService.queryNewArticleUser";

    //查询热门微博id集合
    String METHOD_QUERY_HOTSPOT_ARTICLE_IDS = FACADE_PATH + ".app.ArticleService.queryPopularArticles";

    //微博id集合查询微博
    String METHOD_QUERY_ARTICLE_BY_IDS = FACADE_PATH + ".app.ArticleService.queryArticleListByIds";

    //步步生金排行榜
    String METHOD_QUERY_RANKING = ACT_PATH + ".StepService.queryRanking";

    //步步生金奖励明细查询
    String METHOD_QUERY_REWARD_LIST = ACT_PATH + ".StepService.queryRewardList";

    //步步生金分享
    String METHOD_QUERY_CONTEENT_SHARE = ACT_PATH + ".StepService.queryContentShare";

    //福利树分享信息
    String METHOD_ACTPLANTSERVICE_SHARE = ACT_PATH + ".ActPlantService.share";

    //查询短视频
    String METHOD_QUERY_SHORT_VIDEOS = FACADE_PATH + ".app.ArticleService.queryShortVideos";

    //查询当前用户的二维码
    String METHOD_QUERY_USER_QRCODE = FACADE_PATH +".app.PersonalService.queryUserQRCode";

    //加入群聊
    String METHOD_JOIN_GROUP = FACADE_PATH + ".app.GroupService.joinGroup";

    //修改个人消息设置
    String METHOD_UPDATE_PERSONAL_CONFIG = FACADE_PATH + ".app.PersonalService.updatePersonalConfig";

    //查询个人消息设置
    String METHOD_QUERY_PERSONAL_CONFIG = FACADE_PATH  + ".app.PersonalService.queryPersonalConfig";

    //乡邻广场
    String METHOD_QUERY_XL_QUARE = FACADE_PATH + ".app.IndexService.queryXlQuare";

}
