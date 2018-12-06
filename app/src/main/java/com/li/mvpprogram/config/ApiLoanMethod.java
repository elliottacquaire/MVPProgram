/*
 */

package com.li.mvpprogram.config;

/**
 * loan 接口方法
 *
 */
public interface ApiLoanMethod {
    /**
     * 黑格 身份证扫描 apix  key
     */
    String APIX_KEY = "9bb6e0a582134de7764d0931ffa81a91";

    // 授权结果更新 flag 为 1：身份认证。 2：真实信息填写。 3：是否有熟悉的站长
    String UPDATE_FLAG_1 = "1";
    String UPDATE_FLAG_2 = "2";
    String UPDATE_FLAG_3 = "3";

    //立即借款下单
    String XL_1024 = "XL_1024";
    //立即借款数据回显
    String XL_1054 = "XL_1054";
    //余额是否够用
    String XL_1053 = "XL_1053";
    //真实信息回显
    String XL_1047 = "XL_1047";
    //立即借款集合获取
    String XL_1055 = "XL_1055";

    // 真实信息填写
    String XL_1018 = "XL_1018";
    // 授权结果更新
    String XL_1019 = "XL_1019";
    // 图片上传/保存图片路径
    String XL_1020 = "XL_1020";
    // 图片删除
    String XL_1021 = "XL_1021";
    // 图片查询
    String XL_1022 = "XL_1022";
    // 授权结果查询
    String XL_1023 = "XL_1023";
    // 订单详情
    String XL_1025 = "XL_1025";
    // 客户编号生成
    String XL_1026 = "XL_1026";
    // 客户真实信息查询
    String XL_1027 = "XL_1027";
    // 授信结果查询
    String XL_1028 = "XL_1028";
    // 客户订单列表
    String XL_1029 = "XL_1029";
    // 授信申请
    String XL_1031 = "XL_1031";
    // 同步通讯录
    String XL_1033 = "XL_1033";
    // 提升额度
    String XL_1034 = "XL_1034";
    // 按类型汇总图片张数
    String XL_1035 = "XL_1035";

    // 还款计划
    String XL_1051 = "XL_1051";
    // 获得产品列表
    String XL_1061 = "XL_1061";
    // 修改提示信息弹出框
    String XL_1064 = "XL_1064";
    // 致富贷首页信息
    String XL_1065 = "XL_1065";
    // 用户信息审核
    String XL_1068 = "XL_1068";
    // 用户审核信息提交
    String XL_1069 = "XL_1069";
    /**
     * 获取合同url
     */
    String XL_1044 = "XL_1044";
    /**
     * 站长查看订单
     */
    String XL_1045 = "XL_1045";
    /**
     * 用户管理-列表
     */
    String XL_1048 = "XL_1048";
    /**
     * 待推荐用户列表
     */
    String XL_1049 = "XL_1049";
    /**
     * 站长推荐
     */
    String XL_1050 = "XL_1050";
    /**
     * 用户须知、征信授权书、360贷款导航URL地址
     * */
    String XL_1052 = "XL_1052";

    /**
     * XL_1056 app还款
     */
    String XL_1056 = "XL_1056";

    /**
     * XL_1057 试算还款金额
     */
    String XL_1057 = "XL_1057";

    /**
     * XL_1058 查询支付记录
     */
    String XL_1058 = "XL_1058";

    /**
     * XL_1060 站长代付款校验
     */
    String XL_1060 = "XL_1060";

    /**
     * XL_1063 App业务管理
     */
    String XL_1063 = "XL_1063";
    /**
     * XL_1063 我的借款新接口
     */
    String XL_1066 = "XL_1066";
    /**
     * XL_1067 用户推荐产品类型列表
     */
    String XL_1067 = "XL_1067";

    /**
     * XL_1071 获取上传用户借款资料url
     */
    String XL_1071 = "XL_1071";

    /**
     * XL_1070 获取借款订单html
     */
    String XL_1070 = "XL_1070";
    /**
     * XL_1072 普惠贷查询还款信息
     */
    String XL_1072 = "XL_1072";

    /**
     * XL_1073 资料认证信息确认
     */
    String XL_1073 = "XL_1073";

    String FACADE_PATH = "com.xianglin.appserv.common.service.facade";

    String FACADE_LOAN_PATH = "com.xianglin.loanbiz.common.service.facade";
    // loan 接口
    String METHOD_LOAN_SERVICE_CURRENCY_API = FACADE_LOAN_PATH + ".ApiService.currencyApi";

    // loan 认证
    String METHOD_LOAN_CHECK_AUTH = FACADE_PATH + ".LoanService.checkAuth";

    // 文件上传(走网关接口)
    String METHOD_FILE_UPLOAD = FACADE_PATH + ".LoanService.fileUpload";

    // 文件下载(走网关接口)
    String METHOD_FILE_DOWNLOAD = FACADE_PATH + ".LoanService.fileDownload";

    //活体检测=>活体对比接口
    String XL_1040 = "XL_1040";
    //身份证OCR识别
    String XL_1039 = "XL_1039";
}
