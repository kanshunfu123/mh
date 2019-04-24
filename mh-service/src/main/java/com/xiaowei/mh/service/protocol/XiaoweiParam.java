package com.xiaowei.mh.service.protocol;

import java.io.Serializable;

/**
 * Created by WingsChan on 2018/9/2.
 */
public class XiaoweiParam implements Serializable {


    /**
     * 参数代码
     */
    private String paramCode;

    /**
     * 参数长度
     */
    private Integer length;

    /**
     * 参数内容
     */
    private String paramContent;

    public XiaoweiParam() {
    }

    public XiaoweiParam(String paramCode, Integer length, String paramContent) {
        this.paramCode = paramCode;
        this.length = length;
        this.paramContent = paramContent;
    }

    @Override
    public String toString() {
        return "XiaoweiParam [paramCode=" + paramCode + ", length="
                + length + ", paramContent=" + paramContent + "]";
    }

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getParamContent() {
        return paramContent;
    }

    public void setParamContent(String paramContent) {
        this.paramContent = paramContent;
    }
}
