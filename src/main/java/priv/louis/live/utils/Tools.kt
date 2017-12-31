package priv.louis.live.utils

import java.security.MessageDigest
import java.util.*


class Tools {
    fun generateUUID(): String {
        return UUID.randomUUID().toString().replace("-".toRegex(), "")
    }

    /**
     * 生成md5
     *
     * @param message
     * @return
     */
    fun encoderByMd5(message: String): String {
        var md5str = ""
        try {
            // 1 创建一个提供信息摘要算法的对象，初始化为md5算法对象
            val md = MessageDigest.getInstance("MD5")

            // 2 将消息变成byte数组
            val input = message.toByteArray()

            // 3 计算后获得字节数组,这就是那128位了
            val buff = md.digest(input)

            // 4 把数组每一字节（一个字节占八位）换成16进制连成md5字符串
            md5str = bytesToHex(buff)

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return md5str
    }

    /**
     * 二进制转十六进制
     *
     * @param bytes
     * @return
     */
    fun bytesToHex(bytes: ByteArray): String {
        val md5str = StringBuffer()
        // 把数组每一字节换成16进制连成md5字符串
        var digital: Int
        for (i in bytes.indices) {
            digital = bytes[i].toInt()

            if (digital < 0) {
                digital += 256
            }
            if (digital < 16) {
                md5str.append("0")
            }
            md5str.append(Integer.toHexString(digital))
        }
        return md5str.toString()
    }
}