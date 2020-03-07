package cn.regist.user.controller;

import cn.regist.user.confi.ClusterConfig;
import cn.regist.user.pojo.User;
import cn.regist.user.service.RegistService;
import cn.regist.user.utils.ValidUtil;
import cn.regist.user.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import redis.clients.jedis.JedisCluster;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/user")
public class RegistController {
    @Autowired
    private RegistService registService;
/*    @Autowired
    private static JedisCluster jedis;*/
    /**
     * ajax手机号唯一验证
     *
     * @param user
     * @return 返回202不能注册，200能注册且发送验证码到手机
     */
    @RequestMapping(value = "/phone", method = RequestMethod.GET)
    public SysResult phone(@Valid User user, Errors erros) {
        boolean falg = true;
        if (erros.hasErrors()) {
            List<FieldError> ferrs = erros.getFieldErrors();
            for (FieldError ferr : ferrs) {
                String f = ferr.getField();//错误的字段
                if (f.equals("phone"))//如果有这个字段，就不让进入数据库验证
                    falg = false;
            }
        }
        if (user.getPhone() != null && falg == true) {
            System.out.println("电话号码：" + user.getPhone());
            return registService.phone(user);
        }
        return SysResult.build(202, "请输入正确的电话号码", null);
    }

    /**
     * 注册功能实现
     *
     * @param user
     * @param errors
     * @return 返回202，注册失败，200注册成功
     */
    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public SysResult regist(@Valid User user, Errors errors,String auth) {
        //new的jedis对象
        JedisCluster jedisCluster = new ClusterConfig().initCluster();
        //--valid验证错误提示
        String errmsg = ValidUtil.validMsg(user, errors);
        if (errmsg != null) {//errmsg内有值，说明信息有错误
            return SysResult.build(202, errmsg, null);
        }
        //获取redis值
        String title = jedisCluster.get("title");
        //控制台打印属性
        System.out.println("getTitleCode:" + title);
        System.out.println("verifi:" + auth);
        if (auth != null && auth.equals(title)) {
            try {
                System.out.println("注册成功返回电话："+user.getPhone());
                return registService.regist(user);
            } catch (Exception e) {
                e.printStackTrace();
                return SysResult.build(202, "注册失败", null);
            }
        } return SysResult.build(202, "验证码有误", null);

    }


}
