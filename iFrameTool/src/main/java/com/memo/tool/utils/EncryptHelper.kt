package com.memo.tool.utils

import com.blankj.utilcode.util.EncodeUtils
import com.blankj.utilcode.util.EncryptUtils

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-07-18 17:13
 */
object EncryptHelper {

    /**
     * 8位DES密钥 自己生成
     */
    private const val secretKeyDes8 = "o5j7r4j9"

    /**
     * 32位AES密钥 自己生成
     */
    private const val secretKeyAes32 = "qwe1saz2xdr3fcv8gytuh04bmnkjlpoi"

    /**
     * 生成的512位公钥 自行百度或者自己生成
     */
    private const val publicRsaSecretKey512 =
        "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAOquhNSCWjOlPTeblsMZZn0aWBKQGPAo\n" +
                "MRNOZ61eoYoSKNqb0toN0uh6CvGrREzuIxZavC6XnOfYUewGIXMv/Y0CAwEAAQ=="

    /**
     * 生成的512位私钥 自行百度或者自己生成
     */
    private const val privateRsaSecretKey512 =
        "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEA6q6E1IJaM6U9N5uW\n" +
                "wxlmfRpYEpAY8CgxE05nrV6hihIo2pvS2g3S6HoK8atETO4jFlq8Lpec59hR7AYh\n" +
                "cy/9jQIDAQABAkBKFdfnAlOZdDOUJ3D7nDwniDZ/fa/xuK5tatX9/B7A6AnJNNxT\n" +
                "GJKKvGI9modit7x3xcq4FYxVxIxpWdk54GxdAiEA/+Tpqzk4/sO1EGhGNzkygEVU\n" +
                "/EFpdCTifBAjwDUVN98CIQDqx1xWIRYXmfMVXPN/XsDa/KYk7CAPdBEeAuhmEl4o\n" +
                "EwIgMwxlmSTXO+uq6VUD/OxKKNCr/Y7HDMZ28C0AFM4CJ50CIQDVGSEBeESSJYUT\n" +
                "JexJ9to7qkiviLdM73+96svAIfneiwIhAPymybew+btYQ28lyEgUEYW7JqQqRdRz\n" +
                "kwvoYg5roZAN"

    /**
     * DES加密
     */
    @JvmStatic
    fun encryptDES(content: String): String {
        return EncryptUtils.encrypt3DES2HexString(
            content.toByteArray(),
            secretKeyDes8.toByteArray(),
            "DES",
            null
        )
    }

    /**
     * DES解密
     */
    @JvmStatic
    fun decryptDES(secret: String): String {
        return String(
            EncryptUtils.decryptHexStringDES(
                secret,
                secretKeyDes8.toByteArray(),
                "DES",
                null
            )
        )
    }

    /**
     * AES加密
     */
    @JvmStatic
    fun encryptAES(content: String): String {
        return EncryptUtils.encryptAES2HexString(
            content.toByteArray(),
            secretKeyAes32.toByteArray(),
            "AES",
            null
        )
    }

    /**
     * AES解密
     */
    @JvmStatic
    fun decryptAES(secret: String): String {
        return String(
            EncryptUtils.decryptHexStringAES(
                secret,
                secretKeyAes32.toByteArray(),
                "AES",
                null
            )
        )
    }

    /**
     * RSA加密
     */
    @JvmStatic
    fun encryptRsa(content: String): String {
        return EncryptUtils.encryptRSA2HexString(
            content.toByteArray(),
            EncodeUtils.base64Decode(publicRsaSecretKey512.toByteArray()),
            true,
            "RSA"
        )
    }

    /**
     * RSA解密
     */
    @JvmStatic
    fun decryptRsa(secret: String): String {
        return String(
            EncryptUtils.decryptHexStringRSA(
                secret,
                EncodeUtils.base64Decode(privateRsaSecretKey512.toByteArray()),
                false,
                "RSA"
            )
        )
    }

    /**
     * 获取数据md5
     */
    @JvmStatic
    fun md5(content: String): String {
        return EncryptUtils.encryptMD5ToString(content) ?: content
    }
}