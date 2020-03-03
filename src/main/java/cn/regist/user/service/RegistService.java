package cn.regist.user.service;

import cn.regist.user.mapper.RegistMapper;
import cn.regist.user.pojo.User;
import cn.regist.user.utils.MD5Util;
import cn.regist.user.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

@Service
public class RegistService {
    @Autowired
    private RegistMapper registMapper;

    public SysResult regist(User user) {
        System.out.println("22222222222");
        //判断是不是唯一电话
      int phone=registMapper.selectUserByPhone(user.getPhone());
      if (phone>=1){
          return SysResult.build(202,"手机号已经存在！！！",null);
      }
      //用户电话验证

        //添加数据
        //补齐数据，添加id和加密密码
        user.setId(UUID.randomUUID().toString());
        String md5Password = MD5Util.md5(user.getPassword());
        user.setPassword(md5Password);
        registMapper.insertUser(user);
        return SysResult.ok();
    }
}
