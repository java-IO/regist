package cn.regist.user.controller;
import cn.regist.user.pojo.User;
import cn.regist.user.service.RegistService;
import cn.regist.user.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class RegistController {
    @Autowired
    private RegistService registService;

    @RequestMapping("/regist")
    public SysResult regist(@Valid User user, Errors erros) {
//fsdfsd
        //--valid验证错误提示
        if (erros.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            List<FieldError> ferrs = erros.getFieldErrors();
            for (FieldError ferr : ferrs) {
                String field = ferr.getField();//错误的字段
                String emsg = ferr.getDefaultMessage();//错误的提示
                sb.append("##[" + emsg + "]##<br>");//错误字段与错误提示的拼接
            }
            String errMsg = sb.toString();//转换数据
            return SysResult.build(202, errMsg, null);
        }
            try {
                System.out.println("1111111");
                System.out.println(user.getPhone());
                return registService.regist(user);
            } catch (Exception e) {
                e.printStackTrace();
                return SysResult.build(202, "注册失败", null);
            }
        }


}
