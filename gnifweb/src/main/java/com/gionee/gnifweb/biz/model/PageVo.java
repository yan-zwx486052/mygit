package com.gionee.gnifweb.biz.model;

import java.util.List;

/**
 * 返回给页面的分页类
 * Created by zhang on 2017/2/20.
 */
public class PageVo<T> {

    private Integer total;
    private List<T> rows;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
