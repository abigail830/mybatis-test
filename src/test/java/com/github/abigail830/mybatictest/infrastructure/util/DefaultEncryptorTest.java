package com.github.abigail830.mybatictest.infrastructure.util;

import org.junit.jupiter.api.Test;

class DefaultEncryptorTest {

    @Test
    void encrypt() {
        DefaultEncryptor encryptor = new DefaultEncryptor();
        String pwd = encryptor.encrypt("Wishlist20181008");
        System.out.println(pwd);
    }

    @Test
    void decrypt() {
        DefaultEncryptor encryptor = new DefaultEncryptor();
        String pwd = "mWiOp2l/HuLSL8na/U6BA1TjG/PRpbi5B/dUJ1RYs9A=";
        System.out.println(encryptor.decrypt(pwd));
    }
}