package com.zking.zkingedu.common.utils;

import com.zking.zkingedu.common.model.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordHelper {
    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    public static final String ALGORITHM_NAME = "md5";//基础散列算法
    public static final int HASH_ITERATIONS = 2;//自定义散列次数

    public void encryptPassword(User user) {
        //随机字符串作为salt因子，实际参与运算的salt我们还引入其他干扰因子

    }
}
