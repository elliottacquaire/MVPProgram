package com.li.mvpprogram.base;

public interface SearchBaseView<T> extends BaseView<T>{
    /**
     * 记录用户的点击行为
     * @param
     */
//    void clickBusiness(BusinessVo businessVo);

    void showErrorTip(String msg);

    void goToAccountBook(String chartUrl);

    /**
     * 跳转乡邻账房
     *
     * @param merchantOrderUrl
     * @param merchantManageUrl
     */

    void goToAccountant(String merchantOrderUrl, String merchantManageUrl);

    /**
     * 跳转乡邻小二
     *
     * @param addOrderUrl
     */

    void goToYoungWaiter(String addOrderUrl);

    /**
     * 查询并生成客户号成功
     */
    void requestCustomerNoSuccess();

    /**
     * 查询失败
     *
     * @param errMsg
     */
    void requestCustomerNoFail(String errMsg);

    void goToFinancePage();


    /**
     * 查询用户授信结果
     */
//    void queryCustomerAuthen(AuthorizationResultDTO applyDTO);

    /**
     * 查询授信结果失败
     */
    void queryCustomerAuthenFail(String errMsg);



}
