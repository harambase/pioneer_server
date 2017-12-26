package com.harambase.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MapParam extends HashMap<String, Object> implements Serializable {

    /**
     * 作为Key的字段对应MapParam的Key
     */
    public static final String KEY_FIELD = "mapKeyField";
    /**
     * 作为Value的字段对应MapParam的Key
     */
    public static final String VALUE_FIELD = "mapValueField";

    public MapParam() {

    }

    /**
     * 指定keyField和valueField
     *
     * @param keyField   Map中key对应的字段
     * @param valueField Map中value对应的字段
     */
    public MapParam(String keyField, String valueField) {
        this.put(KEY_FIELD, keyField);
        this.put(VALUE_FIELD, valueField);
    }

    public static Map<String, String> pieChartValue(String value, String name) {
        HashMap<String, String> map = new HashMap<>();
        map.put("value", value);
        map.put("name", name);
        return map;
    }

}