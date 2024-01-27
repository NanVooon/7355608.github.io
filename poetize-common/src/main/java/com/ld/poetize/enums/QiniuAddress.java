package com.ld.poetize.enums;

import lombok.Getter;

/**
 * @Author zuosy
 * @Date 2024/1/27 15:30
 **/
@Getter
public enum QiniuAddress {

    HUA_DONG_ZJ("华东浙江"),
    HUA_BEI("华北"),
    HUA_NAN("华南"),
    BEI_MEI("北美"),
    XIN_JIA_PO("新加坡"),
    HUA_DONG_ZJ2("华东浙江2");

    private final String value;

    QiniuAddress(String value) {
        this.value = value;
    }
}
