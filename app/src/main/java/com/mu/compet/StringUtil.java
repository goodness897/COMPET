package com.mu.compet;

/**
 * Created by Mu on 2016-10-21.
 */

public class StringUtil {

    public static boolean isEmpty(String str) {
        if(str == null || str.trim().length() ==0)
            return true;

        return false;
    }

    public static boolean isAnyEmpty(String str1, String str2) {
        if(isEmpty(str1) || isEmpty(str2))
            return true;

        return false;
    }

    public static boolean isAllEmpty(String str1, String str2) {
        if(isEmpty(str1) && isEmpty(str2))
            return true;

        return false;
    }
}