package com.gionee.gnifweb.biz.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ad on 2017/2/21.
 */
public class QueryMap {
    private Map<String, Object> map = new HashMap<String, Object>();

    public QueryMap() {
    }

    public void put(String key, Object value) {
        this.map.put(key, value);
    }

    public Map<String, Object> getMap() {
        return this.map;
    }
}
