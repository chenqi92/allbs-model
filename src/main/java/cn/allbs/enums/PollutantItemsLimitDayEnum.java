package cn.allbs.enums;

import cn.allbs.constant.ParamConstant;
import cn.allbs.utils.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 空气质量分指数对应的污染物浓度限值 24小时平均值 即日均
 *
 * @author ChenQi
 */
@Getter
@AllArgsConstructor
public enum PollutantItemsLimitDayEnum {

    /**
     * 污染物24小时浓度限值
     */
    SO2(PollutionGas.a21026.toString(), PollutionGas.a21026.getMeaning(), "μg/m³", 1000D, CommonUtil.list(true, 0, 50, 150, 475, 800, 1600, 2100, 2620)),

    NO2(PollutionGas.a21004.toString(), PollutionGas.a21004.getMeaning(), "μg/m³", 1000D, CommonUtil.list(true, 0, 40, 80, 180, 280, 565, 750, 940)),

    PM10(PollutionGas.a34002.toString(), PollutionGas.a34002.getMeaning(), "μg/m³", 0.001, CommonUtil.list(true, 0, 50, 150, 250, 350, 420, 500, 600)),

    CO(PollutionGas.a21005.toString(), PollutionGas.a21005.getMeaning(), "mg/m³", 1D, CommonUtil.list(true, 0, 2, 14, 14, 24, 36, 48, 60)),

    /**
     * 臭氧日 每8小时滑动平均
     */
    O3("O3", "臭氧", "μg/m³", 1000D, CommonUtil.list(true, 0, 100, 160, 215, 265, 800)),

    PM25(PollutionGas.a34004.toString(), PollutionGas.a34004.getMeaning(), "μg/m³", 0.001, CommonUtil.list(true, 0, 35, 75, 115, 150, 250, 350, 500));

    /**
     * 污染物编码
     */
    private final String code;

    /**
     * 污染物名称
     */
    private final String name;

    /**
     * 污染物单位 计算用标准
     */
    private final String unit;

    /**
     * Hj212 比率换算
     */
    private final Double rate;

    /**
     * 取值区间
     */
    private final List<Integer> sectionList;

    private final static Map<String, PollutantItemsLimitDayEnum> DAY_ENUM_MAP = new HashMap<>(9);

    static {
        for (PollutantItemsLimitDayEnum dayEnum : PollutantItemsLimitDayEnum.values()) {
            DAY_ENUM_MAP.put(dayEnum.getCode(), dayEnum);
        }
    }

    /**
     * 计算每日对应污染物的空气质量分指数
     *
     * @param code 污染物code
     * @param cp   污染物项目质量浓度值
     * @return 污染物的空气质量分指数
     */
    public static Double AirAqiCountDayAverage(String code, double cp) {
        if (!DAY_ENUM_MAP.containsKey(code) || cp <= 0) {
            return null;
        }
        if (O3.getCode().equals(code) && cp > O3.sectionList.stream().max(Integer::compareTo).get()) {
            return PollutantItemsLimitHourEnum.AirAqiCountRealTime(O3.getCode(), cp);
        }
        List<Integer> sectionList = DAY_ENUM_MAP.get(code).getSectionList();
        cp = DAY_ENUM_MAP.get(code).getRate();
        for (int i = 0; i < sectionList.size(); i++) {
            if (cp < sectionList.get(i)) {
                return CommonUtil.round(BigDecimal.valueOf(ParamConstant.I_AQI.get(i) - ParamConstant.I_AQI.get(i - 1)).divide(BigDecimal.valueOf((sectionList.get(i) - sectionList.get(i - 1))), 2, BigDecimal.ROUND_HALF_DOWN).multiply(BigDecimal.valueOf((cp - sectionList.get(i - 1)))).add(BigDecimal.valueOf(ParamConstant.I_AQI.get(i - 1))), 2).doubleValue();
            }
        }
        return null;
    }
}
