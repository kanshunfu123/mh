package com.xiaowei.mh.common.res.home;

import java.io.Serializable;

/**
 * created by 韩金群 2019/3/1
 */
public class StandardRwRes implements Serializable {
   private String yes;
   private String no;
   private String name;

    public String getYes() {
        return yes;
    }

    public void setYes(String yes) {
        this.yes = yes;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
