package cn.allbs.enums;

import cn.allbs.utils.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 地表水环境质量标准_2002
 *
 * @author ChenQi
 */
@Getter
@AllArgsConstructor
public enum WaterQualityItemEnum {

    /**
     * 地表水水质等级计算标准
     */
    PH(PollutionWater.w01001.toString(), PollutionWater.w01001.getMeaning(), "无量纲", 1D, CommonUtil.list(true, 6D, 9D), CompareWayEnum.SECTION, null), DO(PollutionWater.w01009.toString(), PollutionWater.w01009.getMeaning(), "mg/L", 1D, CommonUtil.list(true, 7.5, 6D, 5D, 3D, 2D), CompareWayEnum.GATHER_THAN, null), COD_MN(PollutionWater.w01019.toString(), PollutionWater.w01019.getMeaning(), "mg/L", 1D, CommonUtil.list(true, 2D, 4D, 6D, 10D, 15D), CompareWayEnum.LESS_THAN, null), COD(PollutionWater.w01018.toString(), PollutionWater.w01018.getMeaning(), "mg/L", 1D, CommonUtil.list(true, 15D, 15D, 20D, 30D, 40D), CompareWayEnum.LESS_THAN, null), BOD5(PollutionWater.w01017.toString(), PollutionWater.w01017.getMeaning(), "mg/L", 1D, CommonUtil.list(true, 3D, 3D, 4D, 6D, 10D), CompareWayEnum.LESS_THAN, null), NH3N(PollutionWater.w21003.toString(), PollutionWater.w21003.getMeaning(), "mg/L", 1D, CommonUtil.list(true, 0.15, 0.5, 1.0, 1.5, 2.0), CompareWayEnum.LESS_THAN, null), TP(PollutionWater.w21011.toString(), PollutionWater.w21011.getMeaning(), "mg/L", 1D, CommonUtil.list(true, 0.02, 0.1, 0.2, 0.3, 0.4), CompareWayEnum.LESS_THAN, CommonUtil.list(true, 0.01, 0.025, 0.05, 0.1, 0.2)), TN(PollutionWater.w21001.toString(), PollutionWater.w21001.getMeaning(), "mg/L", 1D, CommonUtil.list(true, 0.2, 0.5, 1.0, 1.5, 2.0), CompareWayEnum.LESS_THAN, null), CU(PollutionWater.w20138.toString(), PollutionWater.w20138.getMeaning(), "mg/L", 1D, CommonUtil.list(true, 0.01, 1.0, 1.0, 1.0, 1.0), CompareWayEnum.LESS_THAN, null), ZN(PollutionWater.w20139.toString(), PollutionWater.w20139.getMeaning(), "mg/L", 1D, CommonUtil.list(true, 0.05, 1.0, 1.0, 2.0, 2.0), CompareWayEnum.LESS_THAN, null), F_IDE(PollutionWater.w21017.toString(), PollutionWater.w21017.getMeaning(), "mg/L", 1D, CommonUtil.list(true, 1.0, 1.0, 1.0, 1.5, 1.5), CompareWayEnum.LESS_THAN, null), SE(PollutionWater.w20140.toString(), PollutionWater.w20140.getMeaning(), "mg/L", 1D, CommonUtil.list(true, 0.01, 0.01, 0.01, 0.02, 0.02), CompareWayEnum.LESS_THAN, null), ARSENIC(PollutionWater.w20141.toString(), PollutionWater.w20141.getMeaning(), "mg/L", 1D, CommonUtil.list(true, 0.05, 0.05, 0.05, 0.1, 0.1), CompareWayEnum.LESS_THAN, null), MERCURY(PollutionWater.w20142.toString(), PollutionWater.w20142.getMeaning(), "mg/L", 0.001D, CommonUtil.list(true, 0.00005, 0.00005, 0.0001, 0.001, 0.001), CompareWayEnum.LESS_THAN, null), CD(PollutionWater.w20143.toString(), PollutionWater.w20143.getMeaning(), "mg/L", 0.001D, CommonUtil.list(true, 0.001, 0.005, 0.005, 0.005, 0.01), CompareWayEnum.LESS_THAN, null), CR_VI(PollutionWater.w20117.toString(), PollutionWater.w20117.getMeaning(), "mg/L", 0.001, CommonUtil.list(true, 0.01, 0.05, 0.05, 0.05, 0.1), CompareWayEnum.LESS_THAN, null), PL(PollutionWater.w20144.toString(), PollutionWater.w20144.getMeaning(), "mg/L", 1D, CommonUtil.list(true, 0.01, 0.01, 0.05, 0.05, 0.1), CompareWayEnum.LESS_THAN, null), CY(PollutionWater.w21016.toString(), PollutionWater.w21016.getMeaning(), "mg/L", 1D, CommonUtil.list(true, 0.005, 0.05, 0.2, 0.2, 0.2), CompareWayEnum.LESS_THAN, null), VP(PollutionWater.w23002.toString(), PollutionWater.w23002.getMeaning(), "mg/L", 1D, CommonUtil.list(true, 0.002, 0.002, 0.005, 0.01, 0.1), CompareWayEnum.LESS_THAN, null), PE(PollutionWater.w22001.toString(), PollutionWater.w22001.getMeaning(), "mg/L", 1D, CommonUtil.list(true, 0.05, 0.05, 0.05, 0.5, 1.0), CompareWayEnum.LESS_THAN, null), AS(PollutionWater.w19002.toString(), PollutionWater.w19002.getMeaning(), "mg/L", 1D, CommonUtil.list(true, 0.2, 0.2, 0.2, 0.3, 0.3), CompareWayEnum.LESS_THAN, null), S_IDE(PollutionWater.w21019.toString(), PollutionWater.w21019.getMeaning(), "mg/L", 1D, CommonUtil.list(true, 0.05, 0.1, 0.2, 0.5, 1.0), CompareWayEnum.LESS_THAN, null), FC(PollutionWater.w02003.toString(), PollutionWater.w02003.getMeaning(), "个/L", 1D, CommonUtil.list(true, 200D, 2000D, 10000D, 20000D, 40000D), CompareWayEnum.LESS_THAN, null);

    /**
     * hj212 编码
     */
    private final String code;

    /**
     * 因子名称
     */
    private final String pollutantName;

    /**
     * 水质等级计算时用的单位
     */
    private final String unit;

    /**
     * 换算比例
     */
    private final Double rate;

    /**
     * 非（湖、库）中的比较区间
     */
    private final List<Double> sectionList;

    /**
     * 比较方式
     */
    private final CompareWayEnum compareWa;

    /**
     * 在（湖、库）中的比较区间
     */
    private final List<Double> standbySectionList;

    private static final Map<String, WaterQualityItemEnum> WATER_QUALITY_MAP = new HashMap<>(32);

    static {
        for (WaterQualityItemEnum water : WaterQualityItemEnum.values()) {
            WATER_QUALITY_MAP.put(water.getCode(), water);
        }
    }

    /**
     * 计算当前污染物会最终影响的水质等级
     *
     * @param code              HJ212 因子编码
     * @param isLakeOrReservoir 是否在湖或库中
     * @param v                 因子浓度
     * @return 当前污染物会形成的水质等级
     */
    public static Integer waterLevelCount(String code, boolean isLakeOrReservoir, double v) {
        if (WATER_QUALITY_MAP.containsKey(code)) {
            WaterQualityItemEnum waterItem = WATER_QUALITY_MAP.get(code);
            List<Double> sList = waterItem.getSectionList();
            // 是否为湖泊、水库
            if (isLakeOrReservoir && CommonUtil.isNotEmpty(waterItem.getStandbySectionList())) {
                sList = waterItem.getStandbySectionList();
            }
            Double last = sList.get(sList.size() - 1);
            if (CompareWayEnum.SECTION.equals(waterItem.compareWa)) {
                if (v < sList.get(0) || v > last) {
                    return WaterLevelEnum.VI.getLevel();
                }
            } else if (CompareWayEnum.GATHER_THAN.equals(waterItem.compareWa)) {
                if (v < last) {
                    return WaterLevelEnum.VI.getLevel();
                }
                for (int i = 0; i < sList.size(); i++) {
                    if (v >= sList.get(i)) {
                        return i + 1;
                    }
                }
            } else {
                if (v > last) {
                    return WaterLevelEnum.VI.getLevel();
                }
                for (int i = 0; i < sList.size(); i++) {
                    if (v <= sList.get(i)) {
                        return i + 1;
                    }
                }
            }
        }
        return null;
    }

}
