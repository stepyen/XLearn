package com.stepyen.xlearn.utils;

import java.util.Collection;

/**
 * date：2020-03-30
 * author：stepyen
 * description：集合工具类
 */
public class CollectionUtil {

    public static boolean isEmpty(Collection collection) {

        if (null == collection || collection.isEmpty()) {
            return true;
        }

        return false;
    }

    public static boolean isNoEmpty(Collection collection) {
        if (null != collection && !collection.isEmpty()) {
            return true;
        }

        return false;
    }




}
