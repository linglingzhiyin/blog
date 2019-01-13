package com.shiro;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class Test {
    public static void main(String[] args) {
        String hashAlgorithmName = "MD5";//加密方式
        Object crdentials = "123";//密码原值
        Object salt = ByteSource.Util.bytes("user1"+"2");//盐值
        int hashIterations = 2;//加密2次
        Object result = new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations);
        System.out.println(result);
    }

}
