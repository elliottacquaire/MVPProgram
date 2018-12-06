package com.li.mvpprogram.base;

public interface SearchBasePresenter extends BasePresenter {

    /**
     * 记录用户的点击行为
     * @param businessVo
     */
//    void clickBusiness(BusinessVo businessVo);

    /**
     * 获取相邻账本图表URL
     */
    void getAccountChartUrl();

    /**
     * 获取乡邻账房订单汇总Url
     */
    void getMerchantOrderUrl();

    /**
     * 查询并生成客户号
     */
    void requestCustomerNo();

    /**
     * 获取秒息宝业务状态
     */
    void getFinanceBizStatus();

    /**
     * 获取乡邻小二添加报单Url
     */
    void getAddOrderUrl();

    /**
     * 获取乡邻账房我的店铺Url
     */
    void getmerchantManageUrl(String merchantOrderUrl);

    /**
     * 获取秒息宝token
     */
//    void getFinanceToken(SysParaVo status);

    /**
     * 查询授信结果
     */
    void queryCustomerAuthen();

}
