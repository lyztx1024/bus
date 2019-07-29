package org.aoju.bus.crypto;

/**
 * 模式
 *
 * @author aoju.org
 * @version 3.0.1
 * @group 839128
 * @since JDK 1.8
 */
public enum Mode {
    /**
     * 默认的AES加密方式：AES/CBC/PKCS5Padding
     */
    AES("AES"),
    ARCFOUR("ARCFOUR"),
    Blowfish("Blowfish"),
    /**
     * 默认的DES加密方式：DES/ECB/PKCS5Padding
     */
    DES("DES"),
    /**
     * 3DES算法，默认实现为：DESede/CBC/PKCS5Padding
     */
    DESede("DESede"),
    RC2("RC2"),

    /**
     * RSA算法
     */
    RSA("RSA"),
    /**
     * RSA算法，此算法用了默认补位方式为RSA/ECB/PKCS1Padding
     */
    RSA_ECB_PKCS1("RSA/ECB/PKCS1Padding"),
    /**
     * RSA算法，此算法用了RSA/None/NoPadding
     */
    RSA_None("RSA/None/NoPadding"),
    /**
     * EC算法
     */
    EC("EC"),

    PBEWithMD5AndDES("PBEWithMD5AndDES"),
    PBEWithSHA1AndDESede("PBEWithSHA1AndDESede"),
    PBEWithSHA1AndRC2_40("PBEWithSHA1AndRC2_40"),

    // The RSA signature algorithm
    NONEwithRSA("NONEwithRSA"),

    // The MD2/MD5 with RSA Encryption signature algorithm
    MD2withRSA("MD2withRSA"),
    MD5withRSA("MD5withRSA"),

    // The signature algorithm with SHA-* and the RSA
    SHA1withRSA("SHA1withRSA"),
    SHA256withRSA("SHA256withRSA"),
    SHA384withRSA("SHA384withRSA"),
    SHA512withRSA("SHA512withRSA"),

    // The Digital Signature Algorithm
    NONEwithDSA("NONEwithDSA"),
    // The DSA with SHA-1 signature algorithm
    SHA1withDSA("SHA1withDSA"),

    // The ECDSA signature algorithms
    NONEwithECDSA("NONEwithECDSA"),
    SHA1withECDSA("SHA1withECDSA"),
    SHA256withECDSA("SHA256withECDSA"),
    SHA384withECDSA("SHA384withECDSA"),
    SHA512withECDSA("SHA512withECDSA"),

    HmacMD5("HmacMD5"),
    HmacSHA1("HmacSHA1"),
    HmacSHA256("HmacSHA256"),
    HmacSHA384("HmacSHA384"),
    HmacSHA512("HmacSHA512"),

    MD2("MD2"),
    MD5("MD5"),
    SHA1("SHA-1"),
    SHA256("SHA-256"),
    SHA384("SHA-384"),
    SHA512("SHA-512"),
    SHAPRNG("SHA1PRNG");

    private String value;

    /**
     * 构造
     *
     * @param value 算法的字符串表示，区分大小写
     */
    Mode(String value) {
        this.value = value;
    }

    /**
     * 获得算法的字符串表示形式
     *
     * @return 算法字符串
     */
    public String getValue() {
        return this.value;
    }
}