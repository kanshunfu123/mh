package com.xiaowei.mh.mapper.mapper;



import com.xiaowei.mh.mapper.dataobject.SysUser;

import java.util.List;

/**
 * Created by 李杰 on 2018/7/24.
 */
public interface UserMapper {


    /**
     * 登录
     * 根据手机号查询用户
     *
     * @return
     */
    public List<SysUser> getUserBytelephone(SysUser sysUser);


}
