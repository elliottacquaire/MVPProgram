#include "UrlManager.h"
#include <string.h>

typedef struct {
    char *key;
    char *value;
} URL_MAP;

static const URL_MAP urlMap[] = {
        // 生产环境地址
        {"ENV_P_URL",            "https://apw.a.cn/"},
        {"ENV_P_H5_URL",         "https://h5.xia.cn"},
        {"ENV_P_H5CAU_URL",      "https://h5.cn"},
        {"ENV_P_FILE_URL",       "https://apin.cn/"},

        // 预生产环境地址
        {"ENV_PP_URL",           "https://ain.cn/"},
        {"ENV_PP_H5_URL",        "https://h5.cn"},
        {"ENV_PP_H5CAU_URL",     "https://h5c=ln.cn"},
        {"ENV_PP_FILE_URL",      "https://appf=glin.cn/"},

        // 开发环境地址
        {"ENV_DEV_URL",          "http://apn.com/"},
        {"ENV_DEV_H5_URL",       "https://h5-=.cn"},
        {"ENV_DEV_H5CAU_URL",    "https://h5=glin.cn"},
        {"ENV_DEV_FILE_URL",     "https://appf=lin.cn/"},


        // 测试环境地址
        {"ENV_TEST_URL",         "http://ddf.cn/"},
        {"ENV_TEST_H5_URL",      "https://hn.cn"},
        {"ENV_TEST_H5CAU_URL",   "https://h5cn.cn"},
        {"ENV_TEST_FILE_URL",    "https://appin.cn/"},


        // 测试环境2地址
        {"ENV_TEST_2_URL",       "http://alin.cn/"},
        {"ENV_TEST_2_H5_URL",    "https://hn.cn"},
        {"ENV_TEST_2_H5CAU_URL", "https://h5in.cn"},
        {"ENV_TEST_2_FILE_URL",  "https://appn.cn/"},


        // 联调环境地址
        {"ENV_LT_URL",           "http://gggw/mgw.htm"},
        {"ENV_LT_H5_URL",        "https://h5in.cn"},
        {"ENV_LT_H5CAU_URL",     "https://h5cin.cn"},
        {"ENV_LT_FILE_URL",      "https://appfin.cn/"},
};

char *getUrlByKey(char *inKey) {
    int i = 0;
    // 数组长度
    int len = sizeof(urlMap) / sizeof(URL_MAP);
    while (i < len) {
        if (strcmp(urlMap[i].key, inKey) == 0) {
            return urlMap[i].value;
        }
        i++;
    }
    return NULL;
}
