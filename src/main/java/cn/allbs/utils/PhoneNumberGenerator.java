package cn.allbs.utils;

import java.util.Random;

/**
 * 类 PhoneNumberGenerator 生成随机得手机号
 *
 * @author ChenQi
 * @date 2024/3/14
 */
public class PhoneNumberGenerator {

    private static final String[] MOBILE_PREFIXES = {
            "130", "131", "132", "133", "134", "135", "136", "137", "138", "139",
            "145", "147", // 中国移动
            "150", "151", "152", "153", "155", "156", "157", "158", "159", // 中国联通
            "170", "171", // 虚拟运营商等
            "173", "175", "176", "177", "178", // 中国电信
            "180", "181", "182", "183", "184", "185", "186", "187", "188", "189" // 中国移动、中国联通
    };

    public static String generateRandomPhoneNumber() {
        Random random = new Random();
        String prefix = MOBILE_PREFIXES[random.nextInt(MOBILE_PREFIXES.length)];
        StringBuilder sb = new StringBuilder(prefix);
        for (int i = 0; i < 8; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
