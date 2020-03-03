/*
package cn.regist.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

*/
/**
 * 注册
 *//*

public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public RegisterServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        String mobile = request.getParameter("mobile");
        String verifyCode = request.getParameter("verifyCode");
        JSONObject json = (JSONObject)request.getSession().getAttribute("verifyCode");
        if(json == null){
            renderData(response, "验证码错误");
            return ;
        }
        if(!json.getString("mobile").equals(mobile)){
            renderData(response, "手机号错误");
            return ;
        }
        if(!json.getString("verifyCode").equals(verifyCode)){
            renderData(response, "验证码错误");
            return ;
        }
        if((System.currentTimeMillis() - json.getLong("createTime")) > 1000 * 60 * 5){
            renderData(response, "验证码已过期");
            return ;
        }
        //其他业务代码
        renderData(response, "success");
    }
    protected void renderData(HttpServletResponse response, String data){
        try {
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().write(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}*/
