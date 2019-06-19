package com.linkpets.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ryan on 2018/11/19.
 */
public enum DefaultSettingCode {

    DEFAULT_FLAG(1, "默认有效删除位");

    private int code;

    private String message;

    DefaultSettingCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public static String getMessage(String name) {
        for (DefaultSettingCode item : DefaultSettingCode.values()) {
            if (item.name().equals(name)) {
                return item.message;
            }
        }
        return name;
    }

    public static int getCode(String name) {
        for (DefaultSettingCode item : DefaultSettingCode.values()) {
            if (item.name().equals(name)) {
                return item.code;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return this.name();
    }


}
