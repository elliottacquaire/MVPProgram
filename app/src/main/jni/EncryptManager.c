#include "EncryptManager.h"
#include <string.h>

#define AES_KEY "igwYyzDeqS"

//char* getRsaPublicKey() {
//	return RSA_PUBLIC_KEY;
//}

char *getAesKey() {
    return AES_KEY;
}

typedef struct {
    char *key;
    char *value;
} RSA_MAP;

static const RSA_MAP rsaMap[] = {
        // 初始化RSA的公钥
        {"MMGWSECRET_FOR_MD5_KEY", "20mn;oglcwqZ"},
        {"MMGWSECRET_FOR_AES_KEY", "A_-s&xlt;;l%"},
};

char *getRsaByKey(char *inKey) {
    int i = 0;
    // 数组长度
    int len = sizeof(rsaMap) / sizeof(RSA_MAP);
    while (i < len) {
        if (strcmp(rsaMap[i].key, inKey) == 0) {
            return rsaMap[i].value;
        }
        i++;
    }
    return NULL;
}
