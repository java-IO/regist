package cn.regist.user.controller;

import cn.regist.user.pojo.User;
import cn.regist.user.service.RegistService;
import cn.regist.user.utils.ValidUtil;
import cn.regist.user.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
@RequestMapping("/user")
public class RegistController {
    @Autowired
    private RegistService registService;

    /**
     * ajax手机号唯一验证
     *
     * @param user
     * @return 返回202不能注册，200能注册且发送验证码到手机
     */
    @RequestMapping("/phone")
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
            return registService.phone(user);
        }
        return SysResult.build(202, "请输入正确的电话号码", null);
    }

    /**
     * ajax手机验证码比对
     *
     * @param verifi
     * @return 返回202验证码有误，200验证码正确
     */
    @RequestMapping("/verifi")
    public SysResult verifi(String verifi, HttpSession session) {
        //获取seesion域
        String code = (String) session.getAttribute("code");
        //控制台打印属性
        System.out.println("getSeesioncode:" + code);
        System.out.println("verifi:" + verifi);
        if (verifi != null && verifi.equals(code)) {
            return SysResult.ok();
        }
        return SysResult.build(202, "验证码有误", null);
    }

    /**
     * 注册功能实现
     *
     * @param user
     * @param errors
     * @return 返回202，注册失败，200注册成功
     */
    @RequestMapping("/regist")
    public SysResult regist(@Valid User user, Errors errors) {
        //--valid验证错误提示
        String errmsg = ValidUtil.validMsg(user, errors);
        if (errmsg != null) {//errmsg内有值，说明信息有错误
            return SysResult.build(202, errmsg, null);
        }
        try {
            System.out.println(user.getPhone());
            return registService.regist(user);
        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.build(202, "注册失败", null);
        }
    }


}
