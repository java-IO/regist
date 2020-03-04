package cn.regist.user.utils;

import cn.regist.user.pojo.User;
import cn.regist.user.vo.SysResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import javax.validation.Valid;
import java.util.List;

public class ValidUtil {
    public static String validMsg(@Valid User user,Errors erros){
        String errMsg = null;
        if (erros.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            List<FieldError> ferrs = erros.getFieldErrors();
            for (FieldError ferr : ferrs) {
                String field = ferr.getField();//错误的字段
                String emsg = ferr.getDefaultMessage();//错误的提示
                sb.append("##[" + emsg + "]##<br>");//错误字段与错误提示的拼接
            }
            errMsg = sb.toString();//转换数据
            return errMsg;
        }
        return errMsg;
    }
}
