package com.xiaowei.mh.common.req.fegin;
import com.xiaowei.mh.common.error.BaseReq;
import com.xiaowei.mh.common.res.home.AreaToDeviceVO_v1;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by MOMO on 2019/1/18.
 */
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AreaToDeviceReq_v1 extends BaseReq {
    //下拉框的id  如果为0 则查询当前登录用户下角色所拥有的所有设备
    @NotBlank(message = "下拉框的id必填 selectId")
    private Long selectId;

    //层级 0=省    1=市     2=区     3=街道    4=详细地址
    private Integer level;
    //用户id
    @NotNull(message = "用户userId 必填", groups = {Query.class})
    private Long userId;
    //第三方组id  小为为1
    @NotNull(message = "三方组id   必填", groups = {Query.class})
    private Long groupId;

    public static AreaToDeviceVO_v1 areaToDeviceVO_v1(AreaToDeviceReq_v1 areaToDeviceReq_v1) {
        if (areaToDeviceReq_v1.getSelectId() == 0) {
            AreaToDeviceVO_v1 areaToDeviceVO_v1 = new AreaToDeviceVO_v1();
            areaToDeviceVO_v1.setUserId(areaToDeviceReq_v1.getUserId());
            return areaToDeviceVO_v1;
        } else {
            if (areaToDeviceReq_v1.getLevel() == 0) {
                AreaToDeviceVO_v1 areaToDeviceVO_v1 = new AreaToDeviceVO_v1();
                areaToDeviceVO_v1.setProvinceId(areaToDeviceReq_v1.getSelectId());
                areaToDeviceVO_v1.setUserId(areaToDeviceReq_v1.getUserId());
                return areaToDeviceVO_v1;
            } else if (areaToDeviceReq_v1.getLevel() == 1) {
                AreaToDeviceVO_v1 areaToDeviceVO_v1 = new AreaToDeviceVO_v1();
                areaToDeviceVO_v1.setUserId(areaToDeviceReq_v1.getUserId());
                areaToDeviceVO_v1.setCityId(areaToDeviceReq_v1.getSelectId());
                return areaToDeviceVO_v1;
            } else if (areaToDeviceReq_v1.getLevel() == 2) {
                AreaToDeviceVO_v1 areaToDeviceVO_v1 = new AreaToDeviceVO_v1();
                areaToDeviceVO_v1.setAreaId(areaToDeviceReq_v1.getSelectId());
                areaToDeviceVO_v1.setUserId(areaToDeviceReq_v1.getUserId());
                return areaToDeviceVO_v1;
            } else if (areaToDeviceReq_v1.getLevel() == 3) {
                AreaToDeviceVO_v1 areaToDeviceVO_v1 = new AreaToDeviceVO_v1();
                areaToDeviceVO_v1.setAddressId(areaToDeviceReq_v1.getSelectId());
                areaToDeviceVO_v1.setUserId(areaToDeviceReq_v1.getUserId());
                return areaToDeviceVO_v1;
            } else if (areaToDeviceReq_v1.getLevel() == 4) {
                AreaToDeviceVO_v1 areaToDeviceVO_v1 = new AreaToDeviceVO_v1();
                areaToDeviceVO_v1.setVillageId(areaToDeviceReq_v1.getSelectId());
                areaToDeviceVO_v1.setUserId(areaToDeviceReq_v1.getUserId());
                return areaToDeviceVO_v1;
            }
        }
        AreaToDeviceVO_v1 areaToDeviceVO_v1 = new AreaToDeviceVO_v1();
        areaToDeviceVO_v1.setUserId(areaToDeviceReq_v1.getUserId());
        return areaToDeviceVO_v1;
    }
}
