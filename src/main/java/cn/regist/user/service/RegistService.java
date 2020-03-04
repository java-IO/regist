package cn.regist.user.service;

import cn.regist.user.mapper.RegistMapper;
import cn.regist.user.pojo.User;
import cn.regist.user.utils.MD5Util;
import cn.regist.user.utils.PhoneCodeUtil;
import cn.regist.user.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Service
public class RegistService {
    @Autowired
    private RegistMapper registMapper;

    /**
     * 手机号唯一性验证
     *
     * @param user
     * @return 200可以注册并发送验证码，202不能注册
     */
    public SysResult phone(User user) {
        //获取seesion域
        //       HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //       HttpSession session = httpServletRequest.getSession();
        //判断是不是唯一电话
        int phone = registMapper.selectUserByPhone(user.getPhone());
        if (phone >= 1) {
            return SysResult.build(202, "手机号已经存在！！！", null);
        }
        //发送验证码到手机
        PhoneCodeUtil.getPhonemsg(user.getPhone());
        return SysResult.ok();
    }

    /**
     * 注册功能实现
     *
     * @param user
     * @return 200注册成功
     */
    public SysResult regist(User user) {
        //添加数据
        //补齐数据，添加id和加密密码
        user.setId(UUID.randomUUID().toString());
        String md5Password = MD5Util.md5(user.getPassword());
        user.setPassword(md5Password);
        registMapper.insertUser(user);
        return SysResult.ok();
    }
}
