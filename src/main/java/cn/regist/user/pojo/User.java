package cn.regist.user.pojo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {
    private String id;
    @NotNull
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9]{8,18}$",message = "请输入8-18位的字母和数字的结合")
    private String password;
    @NotNull
    @Pattern(regexp = "^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$",message = "请输入正确的手机号")
    private String phone;
    public User() {
    }

    public User(String id, String password, String phone) {
        this.id = id;
        this.password = password;
        this.phone = phone;
    }

    public String getId(String s) {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", password=" + password +
                ", phone=" + phone +
                '}';
    }
}
