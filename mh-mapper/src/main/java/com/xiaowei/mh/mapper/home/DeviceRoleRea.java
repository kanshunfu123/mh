package com.xiaowei.mh.mapper.home;

import lombok.*;

import java.util.List;

/**
 * Created by 韩金群
 * CreateTime 2019/1/14
 */
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceRoleRea {
    //0 地表水 1井盖 2生活用水 3电梯'
    private String deviceType;
    List<DeviceRoleDeviceRea> cw;
    List<DeviceRoleDeviceRea> rw;
    List<DeviceRoleDeviceRea> ec;
    List<DeviceRoleDeviceRea> mh;
}
