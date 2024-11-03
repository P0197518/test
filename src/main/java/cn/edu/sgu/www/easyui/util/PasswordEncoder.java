package cn.edu.sgu.www.easyui.util;

import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * 密码加密器
 * @author heyunlin
 * @version 1.0
 */
public class PasswordEncoder {

    /**
     * 对用户提供的密码进行加密
     * @param password 用户提供的密码
     * @return String
     */
    public static String encode(String password) {
        // 加密过程
        // 1. 使用MD5算法
        // 2. 使用随机的盐值
        // 3. 循环5次
        // 4. 盐的处理方式为：盐 + 原密码 + 盐 + 原密码 + 盐
        // 注意：因为使用了随机盐，盐值必须被记录下来，本次的返回结果使用$分隔盐与密文
        String salt = UUID.randomUUID().toString().replace("-", "");
        String encodedPassword = password;

        for (int i = 0; i < 5; i++) {
            encodedPassword = DigestUtils.md5DigestAsHex(
                    (salt + encodedPassword + salt + salt + encodedPassword + salt + salt + salt).getBytes()
            );
        }

        return salt + encodedPassword;
    }

    /**
     * 匹配密码
     * @param password 用户提供的密码
     * @param encodedPassword 加密后的密码
     * @return boolean
     */
    public static boolean matches(String password, String encodedPassword) {
        String salt = encodedPassword.substring(0, 32); // 获取盐
        String newPassword = password;

        for (int i = 0; i < 5; i++) {
            newPassword = DigestUtils.md5DigestAsHex(
                    (salt + newPassword + salt + salt + newPassword + salt + salt + salt).getBytes()
            );
        }
        newPassword = salt + newPassword;

        return newPassword.equals(encodedPassword);
    }

    public static void main(String[] args) {
        String password = "mhxy1218";
        String encoded = encode(password);

        System.out.println(encoded);
    }

}