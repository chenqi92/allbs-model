package cn.allbs.enums;

import cn.allbs.model.SectionBo;
import cn.allbs.utils.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ChenQi
 * @date 2021/4/27
 */
@Getter
@AllArgsConstructor
public enum AqiLevelEnum {

    /**
     * 空气质量指数及相关信息
     */
    I(1, new SectionBo(0D, 50D), "优", "绿色", "{\"R\":0,\"G\":228,\"B\":0}", "{\"C\":40,\"M\":0,\"Y\":100,\"K\":0}", "空气质量令人满意，基本无空气污染", "各类人群可正常活动"),
    II(2, new SectionBo(51D, 100D), "良", "黄色", "{\"R\":255,\"G\":255,\"B\":0}", "{\"C\":0,\"M\":0,\"Y\":100,\"K\":0}", "空气质量可接受，但某些污染物可能对极少数异常敏感人群监控有较弱影响", "极少数异常敏感人群应减少户外活动"),
    III(3, new SectionBo(101D, 150D), "轻度污染", "橙色", "{\"R\":255,\"G\":126,\"B\":0}", "{\"C\":0,\"M\":52,\"Y\":100,\"K\":0}", "易感人群症状有轻度加剧，健康人群出现刺激症状", "儿童、老年人及心脏病、呼吸系统疾病患者应减少长时间、高强度的户外锻炼"),
    IV(4, new SectionBo(151D, 200D), "中度污染", "红色", "{\"R\":255,\"G\":0,\"B\":0}", "{\"C\":0,\"M\":100,\"Y\":100,\"K\":0}", "进一步加剧易感人群症状，可能对健康人群心脏、呼吸系统有影响", "儿童、老年人及心脏病、呼吸系统疾病患者应避免长时间、高强度的户外锻炼，一般人群适宜减少户外运动"),
    V(5, new SectionBo(201D, 300D), "重度污染", "紫色", "{\"R\":153,\"G\":0,\"B\":76}", "{\"C\":10,\"M\":100,\"Y\":40,\"K\":30}", "心脏病和肺病患者症状显著加剧，运动耐受力降低，健康人群普遍出现症状", "儿童、老年人和心脏病、肺病患者应停留在室内，停止户外活动，一般人群减少户外活动"),
    VI(6, new SectionBo(301D, null), "严重污染", "褐红色", "{\"R\":126,\"G\":0,\"B\":35}", "{\"C\":30,\"M\":100,\"Y\":100,\"K\":30}", "健康人群运动耐受力降低，有明显强烈症状，提前出现某些疾病", "儿童、老年人和病人应当停留在室内，避免体力消耗，一般人群应避免户外活动");

    /**
     * 空气质量等级
     */
    private final Integer airLevel;

    /**
     * aqi 数据区间
     */
    private final SectionBo sectionBo;

    /**
     * 空气质量指数类别
     */
    private final String airGrade;

    /**
     * 代表颜色
     */
    private final String behalfColor;

    /**
     * 电脑屏幕显示色彩
     */
    private final String rgb;

    /**
     * 印刷色彩模式
     */
    private final String cmyk;

    /**
     * 对健康的影响情况
     */
    private final String healthEffect;

    /**
     * 建议措施
     */
    private final String suggestions;

    /**
     * 根据aqi数值判断aqi等级及相关信息
     *
     * @param aqi aqi数值
     * @return aqi相关信息
     */
    public static AqiLevelEnum getLevelInfoByAqi(int aqi) {
        AqiLevelEnum[] aqiLevelEnums = AqiLevelEnum.values();
        for (AqiLevelEnum aqiLevelEnum : aqiLevelEnums) {
            if (CommonUtil.inSection(aqiLevelEnum.getSectionBo(), aqi)) {
                return aqiLevelEnum;
            }
        }
        return null;
    }
}
