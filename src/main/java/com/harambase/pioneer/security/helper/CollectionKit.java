package com.harambase.pioneer.security.helper;

import com.github.pagehelper.StringUtil;

public class CollectionKit {

    public static <T> String join(T[] array, String conjunction) {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (T item : array) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(conjunction);
            }
            sb.append(item);
        }
        return sb.toString();
    }

    public static Integer[] toIntArray(String split, String str) {
        if (StringUtil.isEmpty(str)) {
            return new Integer[] {};
        }
        String[] arr = str.split(split);
        final Integer[] ints = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            final Integer v = Integer.parseInt(arr[i], 0);
            ints[i] = v;
        }
        return ints;
    }
}
