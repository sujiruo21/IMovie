package com.zl.imovie.network.http;

/**
 * @author zl
 * @function 所有请求相关的地址
 */

public class HttpConstants {

    private static final String ROOT_URL = "http://10.0.2.2:8080";

    /**
     * 请求本地产品列表
     */
    public static String PRODUCT_LIST = ROOT_URL + "/fund/search.jsp";

    /**
     * 本地产品列表更新时间措请求
     */
    public static String PRODUCT_LATESAT_UPDATE = ROOT_URL + "/fund/upsearch.jsp";

    /**
     * 登陆接口
     */
    public static String LOGIN = ROOT_URL + "/user/login_phone.jsp";

    /**
     * 检查更新接口
     */
    public static String CHECK_UPDATE = ROOT_URL + "/config/check_update.jsp";

    /**
     * 首页产品请求接口
     */
    public static String HOME_RECOMMAND = ROOT_URL + "/imovie/home_data.json";

    /**
     * 课程详情接口
     */
    public static String COURSE_DETAIL = ROOT_URL + "/product/course_detail.jsp";

}


