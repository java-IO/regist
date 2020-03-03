package cn.regist.user.mapper;

import cn.regist.user.pojo.User;

import javax.crypto.spec.PSource;

public interface RegistMapper {

    int selectUserByPhone(String phone);

    void insertUser(User user);
}
