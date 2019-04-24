package com.xiaowei.mh.service.protocol;

/**
 * Created by WingsChan on 2018/9/1.
 */
public enum CMDEnum {

    /**
     * 心跳
     */
    HEART("heart", "01", "心跳包"),
    FAULT_ALARM("faultAlarm", "02", "故障警报"),
    FAULT_RECOVERY("faultRecovery", "03", "故障恢复"),
    DEVICE_OPENING_ALARM("deviceOpeningAlarm", "04", "设备开盖警报"),
    SETUP("setup", "20", "设置指令"),
    UN_KNOWN("unKnown", "", "未知"),;


    private String cmd;

    private String cmdHex;

    private String remark;

    CMDEnum(String cmd, String cmdHex, String remark) {
        this.cmd = cmd;
        this.cmdHex = cmdHex;
        this.remark = remark;
    }

    public static String getCMDbyHex(String cmdHex) {
        CMDEnum[] cmdEnums = values();
        for (CMDEnum paramEnum : cmdEnums) {
            if (paramEnum.getCmdHex().equalsIgnoreCase(cmdHex)) {
                return paramEnum.getCmd();
            }
        }
        return null;
    }

    public static String getCMDHexbyCMD(String cmd) {
        CMDEnum[] cmdEnums = values();
        for (CMDEnum paramEnum : cmdEnums) {
            if (paramEnum.getCmd().equalsIgnoreCase(cmd)) {
                return paramEnum.getCmdHex();
            }
        }
        return null;
    }

    public static String fetCmdByHex(String cmdHex) {
        if (cmdHex.equalsIgnoreCase(HEART.getCmdHex())) {
            return HEART.getCmd();
        } else if (cmdHex.equalsIgnoreCase(FAULT_ALARM.getCmdHex())) {
            return FAULT_ALARM.getCmd();
        } else if (cmdHex.equalsIgnoreCase(FAULT_RECOVERY.getCmdHex())) {
            return FAULT_RECOVERY.getCmd();
        } else if (cmdHex.equalsIgnoreCase(DEVICE_OPENING_ALARM.getCmdHex())) {
            return DEVICE_OPENING_ALARM.getCmd();
        } else if (cmdHex.equalsIgnoreCase(SETUP.getCmdHex())) {
            return SETUP.getCmd();
        } else {
            return UN_KNOWN.getCmd();
        }
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getCmdHex() {
        return cmdHex;
    }

    public void setCmdHex(String cmdHex) {
        this.cmdHex = cmdHex;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
