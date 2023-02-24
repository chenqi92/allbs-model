package cn.allbs.utils;

import cn.allbs.constant.StringPoolConstant;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 类 AsciiUtil
 * </p>
 *
 * @author ChenQi
 * @since 2023/2/20 16:23
 */
public class AsciiUtil {


    /**
     * 字节数组转16进制字符串
     */
    public static String bytes2HexString(byte[] b) {
        StringBuilder r = new StringBuilder();

        for (byte value : b) {
            String hex = Integer.toHexString(value & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            r.append(hex.toUpperCase());
        }

        return r.toString();
    }

    /**
     * 字符转换为字节
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * 16进制字符串转字节数组
     */
    public static byte[] hexString2Bytes(String hex) {

        if ((hex == null) || (hex.equals(""))) {
            return null;
        } else if (hex.length() % 2 != 0) {
            return null;
        } else {
            hex = hex.toUpperCase();
            int len = hex.length() / 2;
            byte[] b = new byte[len];
            char[] hc = hex.toCharArray();
            for (int i = 0; i < len; i++) {
                int p = 2 * i;
                b[i] = (byte) (charToByte(hc[p]) << 4 | charToByte(hc[p + 1]));
            }
            return b;
        }

    }

    /**
     * 字节数组转字符串
     */
    public static String bytes2String(byte[] b) throws Exception {
        return bytes2String(b, StandardCharsets.UTF_8);
    }

    /**
     * 字节数组转字符串
     */
    public static String bytes2String(byte[] b, Charset charset) throws Exception {
        return new String(b, charset);
    }

    /**
     * 字符串转字节数组
     */
    public static byte[] string2Bytes(String s) {
        return s.getBytes();
    }

    /**
     * 16进制字符串转字符串
     */
    public static String hex2String(String hex) throws Exception {
        return bytes2String(hexString2Bytes(hex));
    }

    /**
     * 字符串转16进制字符串
     */
    public static String string2HexString(String s) {
        return bytes2HexString(string2Bytes(s));
    }

    /**
     * char[] 转为 byte[]
     *
     * @param chars
     * @return
     */
    public static byte[] getBytes(char[] chars) {
        Charset cs = StandardCharsets.UTF_8;
        CharBuffer cb = CharBuffer.allocate(chars.length);
        cb.put(chars);
        cb.flip();
        ByteBuffer bb = cs.encode(cb);
        return bb.array();
    }

    /**
     * byte[] 转为char[]
     *
     * @param bytes
     * @return
     */
    public static char[] getChars(byte[] bytes) {
        Charset cs = StandardCharsets.UTF_8;
        ByteBuffer bb = ByteBuffer.allocate(bytes.length);
        bb.put(bytes).flip();
        CharBuffer cb = cs.decode(bb);
        return cb.array();
    }

    /**
     * char 转为byte[]
     *
     * @param c
     * @return
     */
    public static byte[] charToBytes(char c) {
        byte[] b = new byte[2];
        b[0] = (byte) ((c & 0xFF00) >> 8);
        b[1] = (byte) (c & 0xFF);
        return b;
    }

    /**
     * byte[] 转为char
     *
     * @param b
     * @return
     */
    public static char byteToChar(byte[] b) {
        int hi = (b[0] & 0xFF) << 8;
        int lo = b[1] & 0xFF;
        return (char) (hi | lo);
    }

    /**
     * 以空格分隔16进制字符串转为byte[]
     *
     * @param str
     * @return
     */
    public static byte[] strToBytes(String str) {
        String[] strs = str.trim().split(StringPoolConstant.SPACE);
        byte[] b = new byte[strs.length];
        if (strs.length == 0) {
            return b;
        }
        for (int i = 0; i < strs.length; i++) {
            b[i] = hexString2Bytes(strs[i])[0];
        }
        return b;
    }

    /**
     * 计算算术校验和
     *
     * @param msg 待计算的字节数组
     * @param len 最后保留的字节数
     * @return 舍去8*len 位以上的进位位后所形成的len 字节二进制数 后转十进制结果
     */
    public static byte[] sumCheck(byte[] msg, int len) {
        long mSum = 0;
        byte[] mByte = new byte[len];

        for (byte byteMsg : msg) {
            long mNum = ((long) byteMsg >= 0) ? (long) byteMsg : ((long) byteMsg + 256);
            mSum += mNum;
        }

        for (int livCount = 0; livCount < len; livCount++) {
            mByte[len - livCount - 1] = (byte) (mSum >> (livCount * 8) & 0xff);
        }
        return mByte;
    }

    /**
     * GB18030 转utf-8
     *
     * @param bytes gb18030 字节数组
     * @return
     */
    public static String gb18030ToUtf8(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        CharBuffer gb180303 = Charset.forName("GB18030").decode(byteBuffer);
        ByteBuffer utf8 = StandardCharsets.UTF_8.encode(gb180303);
        return new String(utf8.array()).trim();
    }

    /**
     * utf-8 转gb18030
     *
     * @param str
     * @return
     */
    public static byte[] utf8ToGb18030ByteArray(String str) {
        ByteBuffer gb18030 = Charset.forName("GB18030").encode(str);
        return gb18030.array();
    }

    /**
     * 二进制转16进制
     *
     * @param s
     * @return
     */
    public static String twoToSixteen(String s) {
        int data = Integer.valueOf(s, 2);
        return Integer.toHexString(data);
    }

    /**
     * 十进制转二进制
     *
     * @param num
     * @return
     */
    public static String tenToTwo(String num) {
        return Integer.toBinaryString(Integer.parseInt(num));
    }

    /**
     * 十六进制转十进制
     *
     * @param num
     * @return
     */
    public static String sixteenToTen(String num) {
        BigInteger lngNum = new BigInteger(num, 16);
        return String.valueOf(lngNum.intValue());
    }

    /**
     * 十进制转十六进制
     *
     * @param str
     * @return
     */
    public static String tenToSixteen(String str) {
        int data = Integer.parseInt(str);
        String preData = Integer.toHexString(data);
        return preData;
    }

    public static String gbkToUnicode(String str, String charsetName) {
        StringBuilder st = new StringBuilder();
        try {
            byte[] by = str.getBytes(charsetName);
            for (int i = 0; i < by.length; i++) {
                String strs = Integer.toHexString(by[i]);
                if (strs.length() > 2) {
                    strs = strs.substring(strs.length() - 2);
                }
                st.append(strs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return st.toString();
    }

    /**
     * 将汉字转换为16进制
     *
     * @param str
     * @return
     */
    public static String gbkToUnicode(String str) {
        return gbkToUnicode(str, Charset.defaultCharset().name());
    }

    /**
     * 字符串转ascii
     *
     * @param str
     * @return
     */
    public static String strToASCII(String str) {
        StringBuilder sb = new StringBuilder();
        char[] ch = str.toCharArray();
        for (char c : ch) {
            String s = Integer.toHexString(c);
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * byte数组转short<br>
     * 默认以小端序转换
     *
     * @param bytes byte数组
     * @return short值
     */
    public static short bytesToShort(byte[] bytes) {
        return bytesToShort(bytes, ByteOrder.LITTLE_ENDIAN);
    }

    /**
     * 16进制字节数组转为short
     */
    public static short bytesToShort(byte[] bytes, ByteOrder byteOrder) {
        if (ByteOrder.LITTLE_ENDIAN == byteOrder) {
            //小端模式，数据的高字节保存在内存的高地址中，而数据的低字节保存在内存的低地址中
            return (short) (bytes[0] & 0xff | (bytes[1] & 0xff) << Byte.SIZE);
        } else {
            return (short) (bytes[1] & 0xff | (bytes[0] & 0xff) << Byte.SIZE);
        }
    }

    /**
     * 获取一个字节整数高四位
     *
     * @param data 一个字节的数
     * @return 取高四位的后的数
     */
    public static int getHeight4FromShort(short data) {
        int height;
        height = ((data & 0xf0) >> 4);
        return height;
    }

    /**
     * 获取一个字节整数高四位
     *
     * @param data 一个字节的数
     * @return 取高四位的后的数
     */
    public static int getHeight4FromByte(byte data) {
        int height;
        height = ((data & 0xf0) >> 4);
        return height;
    }

    /**
     * 获取一个字节整数低四位
     *
     * @param data 一个字节的数据
     * @return 取低四位后的数据
     */
    public static int getLow4FromShort(short data) {
        int low;
        low = (data & 0x0f);
        return low;
    }

    /**
     * 获取一个字节整数低四位
     *
     * @param data 一个字节的数据
     * @return 取低四位后的数据
     */
    public static int getLow4FromByte(byte data) {
        int low;
        low = (data & 0x0f);
        return low;
    }

    /**
     * 两个16进制的字节转换为1个字节10进制整数（用于GBF293K）
     *
     * @return 转换后的一个字节整数
     */
    public static short bytesToByte(byte[] bytes, ByteOrder byteOrder) {
        if (bytes.length == 1) {
            return (short) (bytes[0] & 0xff);
        }
        if (ByteOrder.LITTLE_ENDIAN == byteOrder) {
            //小端模式，数据的高字节保存在内存的高地址中，而数据的低字节保存在内存的低地址中
            return (short) (getLow4FromByte(bytes[0]) << 4 | getLow4FromByte(bytes[1]));
        } else {
            return (short) (getLow4FromByte(bytes[1]) << 4 | getLow4FromByte(bytes[0]));
        }
    }

    /**
     * 两个16进制的字节转换为1个字节10进制整数（用于GBF293K）
     *
     * @return 转换后的一个字节整数
     */
    public static short bytesToByte(byte[] bytes) {
        return bytesToByte(bytes, ByteOrder.LITTLE_ENDIAN);
    }

    /**
     * 两个16进制的字节转换为1个字节10进制整数（用于GBF293K）
     *
     * @return 转换后的一个字节整数
     */
    public static short bytesToShort(byte[] bytes, ByteOrder byteOrder, int shift) {
        if (bytes.length == 1) {
            return (short) (bytes[0] & 0xff);
        }
        if (ByteOrder.LITTLE_ENDIAN == byteOrder) {
            //小端模式，数据的高字节保存在内存的高地址中，而数据的低字节保存在内存的低地址中
            return (short) (getLow4FromShort((short) (bytes[0] - shift)) << 4 | getLow4FromShort((short) (bytes[1] - shift)));
        } else {
            return (short) (getLow4FromShort((short) (bytes[1] - shift)) << 4 | getLow4FromShort((short) (bytes[0] - shift)));
        }
    }

    /**
     * 两个16进制的字节转换为1个字节10进制整数（用于GBF293K）
     *
     * @return 转换后的一个字节整数
     */
    public static short bytesToShort(byte[] bytes, int shift) {
        return bytesToShort(bytes, ByteOrder.LITTLE_ENDIAN, shift);
    }
}
