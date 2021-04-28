package cn.allbs.enums;

import cn.allbs.constant.ParamConstant;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.NumberUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 空气质量分指数对应的污染物浓度限值 1小时平均值 即实时值
 *
 * @author ChenQi
 */
@Getter
@AllArgsConstructor
public enum PollutantItemsLimitHourEnum {

    /**
     * 污染物一小时浓度限值
     */
    SO2("SO2", "二氧化硫", "μg/m³", CollUtil.list(true, 0, 150, 500, 650, 800)),

    NO2("NO2", "二氧化氮", "μg/m³", CollUtil.list(true, 0, 100, 200, 700, 1200, 2340, 3090, 3840)),

    CO("CO", "一氧化碳", "mg/m³", CollUtil.list(true, 0, 5, 10, 35, 60, 90, 120, 150)),

    O3("O3", "臭氧", "μg/m³", CollUtil.list(true, 0, 160, 200, 300, 400, 800, 1000, 1200));

    /**
     * 污染物编码
     */
    private final String code;

    /**
     * 污染物名称
     */
    private final String name;

    /**
     * 污染物单位
     */
    private final String unit;

    /**
     * 取值区间
     */
    private final List<Integer> sectionList;

    private static final Map<String, PollutantItemsLimitHourEnum> HOUR_ENUM_MAP = new HashMap<>(7);

    static {
        for (PollutantItemsLimitHourEnum hourEnum : PollutantItemsLimitHourEnum.values()) {
            HOUR_ENUM_MAP.put(hourEnum.getCode(), hourEnum);
        }
    }

    /**
     * 计算实时对应污染物的空气质量分指数
     *
     * @param code 污染物code
     * @param cp   污染物项目质量浓度值
     * @return 污染物的空气质量分指数
     */
    public static Double AirAqiCountRealTime(String code, double cp) {
        if (!HOUR_ENUM_MAP.containsKey(code) || cp <= 0) {
            return null;
        }
        if (SO2.getCode().equals(code) && cp > SO2.sectionList.stream().max(Integer::compareTo).get()) {
            return PollutantItemsLimitDayEnum.AirAqiCountDayAverage(SO2.getCode(), cp);
        }
        List<Integer> sectionList = HOUR_ENUM_MAP.get(code).getSectionList();
        for (int i = 0; i < sectionList.size(); i++) {
            // 判断在哪个区间中
            if (cp <= sectionList.get(i)) {
                // 计算aqi
                return NumberUtil.round(BigDecimal.valueOf(ParamConstant.I_AQI.get(i) - ParamConstant.I_AQI.get(i - 1)).divide(BigDecimal.valueOf(sectionList.get(i) - sectionList.get(i - 1)), 2, BigDecimal.ROUND_HALF_DOWN).multiply(BigDecimal.valueOf((cp - sectionList.get(i - 1)))).add(BigDecimal.valueOf(ParamConstant.I_AQI.get(i - 1))), 2).doubleValue();
            }
        }
        return null;
    }
}
