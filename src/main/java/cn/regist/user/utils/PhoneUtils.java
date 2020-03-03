/*
package cn.regist.user.utils;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
public class PhoneUtils extends HttpServlet {

    */
/**
     * 获取验证码
     *//*

        private static final long serialVersionUID = 1L;
        //短信平台相关参数
        private String apiUrl = "https://openapi.miaodiyun.com";
        private String appId = "d8fec8b4546375b4fa49fedf7f293600";
        private String appSecret = "c384b67bdsserev3343cdda4de5c8";

        public PhoneUtils() {
            super();
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request, response);
        }

        */
/**
         * 短信平台使用的是榛子云短信(smsow.zhenzikj.com)
         *//*

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                String mobile = request.getParameter("mobile");
                JSONObject json = null;
                //生成6位验证码
                String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
                //发送短信
                ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
                String result = "{code:0}";//client.send(mobile, "您的验证码为:" + verifyCode + "，该码有效期为5分钟，该码只能使用一次!");
                json = JSONObject.parseObject(result);
                if(json.getIntValue("code") != 0){//发送短信失败
                    renderData(response, "fail");
                    return;
                }
                //将验证码存到session中,同时存入创建时间
                //以json存放，这里使用的是阿里的fastjson
                HttpSession session = request.getSession();
                json = new JSONObject();
                json.put("mobile", mobile);
                json.put("verifyCode", verifyCode);
                json.put("createTime", System.currentTimeMillis());
                // 将认证码存入SESSION
                request.getSession().setAttribute("verifyCode", json);
                renderData(response, "success");
                return ;
            } catch (Exception e) {
                e.printStackTrace();
            }
            renderData(response, "fail");
        }

        protected void renderData(HttpServletResponse response, String data){
            try {
                response.setContentType("text/plain;charset=UTF-8");
                response.getWriter().write(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


*/
