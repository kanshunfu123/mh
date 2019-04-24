package com.xiaowei.mh.service.protocol;

import com.xiaowei.mh.common.util.hex.BytesHexStrTranslate;
import com.xiaowei.mh.service.protocol.manhole.ParamMhEnum;
import com.xiaowei.mh.service.protocol.manhole.XiaoweiMhDataReq;
import com.xiaowei.mh.service.protocol.rw.ParamRwEnum;
import com.xiaowei.mh.service.protocol.rw.XiaoweiRwDataReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 自己定义的协议
 * 数据包格式
 * +——----——+——-----——+——----——+——----——+——----——+——----——+
 * |数据头     |数据包长度     |加密状态    |数据        |校验位     |数据尾
 * +——----——+——-----——+——----——+——----——+——----——+——----——+
 * 1.协议开始标志head_data，为int类型的数据，16进制表示为0xEC 1个字节
 */
public class XiaoweiProtocolV1 {

    private static final Logger log = LoggerFactory.getLogger(XiaoweiProtocolV1.class);
    /**
     * 消息的开头的信息标志
     */
    private String headData = ConstantValue.HEAD_DATA;
    /**
     * 消息的结束的信息标志
     */
    private String endData = ConstantValue.END_DATA;

    /**
     * 消息的长度
     */
    private int dataLength;

    /**
     * 消息的内容
     */
    private byte[] content;


    /**
     * 加密位
     */
    private String encHex;

    /**
     * 校验位
     */
    private String crcHex;

    /**
     * 设备类型
     */
    private String deviceType;

    /**
     * 硬件版本号
     */
    private String version;

    /**
     * 指令
     */
    private String cmd;

    /**
     * 参数个数
     */
    private Integer paramCnt;

    /**
     * 解析后的参数list
     */
    private List<XiaoweiParam> params = new ArrayList<>();

    /**
     * 翻译后的数据
     */
    private HashMap<String, Object> paramsData = new HashMap<>();

    /**
     * 电梯参数
     */
    private XiaoweiEcDataReq xiaoweiEcDataReq;

    /**
     * 河道水
     */
    private XiaoweiRwDataReq xiaoweiRwDataReq;

    /**
     * 井盖
     */
    private XiaoweiMhDataReq xiaoweiMhDataReq;

    /**
     * 十六进制数据完整包
     */
    private String dataStr;

    public XiaoweiProtocolV1(int dataLength, byte[] content, String dataStr) {
        this(dataLength, content);
        this.dataStr = dataStr;
    }


    /**
     * 解析content内容
     */
    public void initData() {
        byte[] encByte = subBytes(this.content, 0, 1);
        encHex = BytesHexStrTranslate.bytesToHexFun3(encByte);

        Integer crcIndex = this.content.length - 2;
        byte[] crcByte = subBytes(this.content, crcIndex, 2);
        crcHex = BytesHexStrTranslate.bytesToHexFun3(crcByte);

        byte[] data = subBytes(this.content, 1, this.content.length - 3);

        //设备类型
        byte[] deviceTypeByte = subBytes(data, 0, 1);
        String deviceTypeHex = BytesHexStrTranslate.bytesToHexFun3(deviceTypeByte);
        deviceType = DeviceTypeEnum.fetchDeviceTypeByHex(deviceTypeHex);

        //硬件版本号
        byte[] versionByte = subBytes(data, 1, 1);
        String versionTypeHex = BytesHexStrTranslate.bytesToHexFun3(versionByte);
        version = versionTypeHex;

        //指令
        byte[] cmdByte = subBytes(data, 2, 1);
        String cmdByteHex = BytesHexStrTranslate.bytesToHexFun3(cmdByte);
        cmd = CMDEnum.fetCmdByHex(cmdByteHex);

        //参数数量
        byte[] paramCntByte = subBytes(data, 3, 1);
        String paramCntByteHex = BytesHexStrTranslate.bytesToHexFun3(paramCntByte);
        paramCnt = Integer.parseInt(paramCntByteHex, 16);

        //解析参数
        byte[] paramData = subBytes(data, 4, data.length - 4);

        for (int i = 0; i < paramCnt; i++) {
            // 参数代码
            byte[] paramCodeByte = subBytes(paramData, 0, 1);
            String paramCode = BytesHexStrTranslate.bytesToHexFun3(paramCodeByte);

            //长度
            byte[] paramLength = subBytes(paramData, 1, 1);
            String paramLengthHex = BytesHexStrTranslate.bytesToHexFun3(paramLength);
            Integer length = Integer.parseInt(paramLengthHex, 16);

            //内容
            byte[] paramContentByte = subBytes(paramData, 2, length);
            String paramContent = BytesHexStrTranslate.bytesToHexFun3(paramContentByte);
            XiaoweiParam param = new XiaoweiParam(paramCode, length, paramContent);
            params.add(param);
            //重置paramData
            paramData = subBytes(paramData, 2 + length, paramData.length - 2 - length);
        }

        if (DeviceTypeEnum.EC.getDeviceType().equalsIgnoreCase(this.deviceType)) {
            for (XiaoweiParam param : params) {
                String paramKey = ParamEcEnum.getParamKey(param.getParamCode());
                if (paramKey != null) {
                    paramsData.put(paramKey, param.getParamContent());
                } else {
                    log.warn("paramKek未记录：" + paramKey);
                }
            }
            xiaoweiEcDataReq = XiaoweiEcDataReq.buildByDataMap(paramsData);
        } else if (DeviceTypeEnum.RIVER_WATER.getDeviceType().equalsIgnoreCase(this.deviceType)) {
            for (XiaoweiParam param : params) {
                String paramKey = ParamRwEnum.getParamKey(param.getParamCode());
                if (paramKey != null) {
                    paramsData.put(paramKey, param.getParamContent());
                } else {
                    log.warn("paramKek未记录：" + paramKey);
                }
            }
            xiaoweiRwDataReq = XiaoweiRwDataReq.buildByDataMap(paramsData);
        } else if (DeviceTypeEnum.MANHOLE.getDeviceType().equalsIgnoreCase(this.deviceType)) {
            for (XiaoweiParam param : params) {
                String paramKey = ParamMhEnum.getParamKey(param.getParamCode());
                if (paramKey != null) {
                    paramsData.put(paramKey, param.getParamContent());
                } else {
                    log.warn("paramKek未记录：" + paramKey);
                }
            }
            xiaoweiMhDataReq = XiaoweiMhDataReq.buildByDataMap(paramsData);
        }
    }

    public XiaoweiProtocolV1() {
    }

    /**
     * 用于初始化，SmartCarProtocol
     *
     * @param dataLength 协议里面，消息数据的长度
     * @param content    协议里面，消息的数据
     */
    public XiaoweiProtocolV1(int dataLength, byte[] content) {
        this.dataLength = dataLength;
        this.content = content;
    }

    public String getHeadData() {
        return headData;
    }

    public void setHeadData(String headData) {
        this.headData = headData;
    }

    public String getEndData() {
        return endData;
    }

    public void setEndData(String endData) {
        this.endData = endData;
    }

    public int getDataLength() {
        return dataLength;
    }

    public void setDataLength(int dataLength) {
        this.dataLength = dataLength;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }


    public String getEncHex() {
        return encHex;
    }

    public void setEncHex(String encHex) {
        this.encHex = encHex;
    }

    public String getCrcHex() {
        return crcHex;
    }

    public void setCrcHex(String crcHex) {
        this.crcHex = crcHex;
    }

    public List<XiaoweiParam> getParams() {
        return params;
    }

    public void setParams(List<XiaoweiParam> params) {
        this.params = params;
    }

    public HashMap<String, Object> getParamsData() {
        return paramsData;
    }

    public void setParamsData(HashMap<String, Object> paramsData) {
        this.paramsData = paramsData;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public Integer getParamCnt() {
        return paramCnt;
    }

    public void setParamCnt(Integer paramCnt) {
        this.paramCnt = paramCnt;
    }

    public XiaoweiEcDataReq getXiaoweiEcDataReq() {
        return xiaoweiEcDataReq;
    }

    public void setXiaoweiEcDataReq(XiaoweiEcDataReq xiaoweiEcDataReq) {
        this.xiaoweiEcDataReq = xiaoweiEcDataReq;
    }

    public XiaoweiRwDataReq getXiaoweiRwDataReq() {
        return xiaoweiRwDataReq;
    }

    public XiaoweiProtocolV1 setXiaoweiRwDataReq(XiaoweiRwDataReq xiaoweiRwDataReq) {
        this.xiaoweiRwDataReq = xiaoweiRwDataReq;
        return this;
    }

    public XiaoweiMhDataReq getXiaoweiMhDataReq() {
        return xiaoweiMhDataReq;
    }

    public XiaoweiProtocolV1 setXiaoweiMhDataReq(XiaoweiMhDataReq xiaoweiMhDataReq) {
        this.xiaoweiMhDataReq = xiaoweiMhDataReq;
        return this;
    }

    @Override
    public String toString() {
        return "XiaoweiProtocolV1{" +
                "headData='" + headData + '\'' +
                ", endData='" + endData + '\'' +
                ", dataLength=" + dataLength +
                ", content=" + Arrays.toString(content) +
                ", encHex='" + encHex + '\'' +
                ", crcHex='" + crcHex + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", version='" + version + '\'' +
                ", cmd='" + cmd + '\'' +
                ", paramCnt=" + paramCnt +
                ", params=" + params +
                ", paramsData=" + paramsData +
                ", xiaoweiEcDataReq=" + xiaoweiEcDataReq +
                ", xiaoweiRwDataReq=" + xiaoweiRwDataReq +
                ", xiaoweiMhDataReq=" + xiaoweiMhDataReq +
                ", dataStr='" + dataStr + '\'' +
                '}';
    }

    public static byte[] subBytes(byte[] src, int begin, int count) {
        byte[] bs = new byte[count];
        System.arraycopy(src, begin, bs, 0, count);
        return bs;
    }

    public String getDataStr() {
        return dataStr;
    }

    public XiaoweiProtocolV1 setDataStr(String dataStr) {
        this.dataStr = dataStr;
        return this;
    }
}
