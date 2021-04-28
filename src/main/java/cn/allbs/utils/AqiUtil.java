package cn.allbs.utils;

import cn.allbs.constant.CommonConstant;
import cn.allbs.constant.ParamConstant;
import cn.allbs.enums.PollutantItemsLimitDayEnum;
import cn.allbs.enums.PollutantItemsLimitHourEnum;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 大气Aqi计算
 *
 * @author ChenQi
 */
@UtilityClass
public class AqiUtil {

    /**
     * 计算实时/日的AQI数据 SO2（μg/m³） NO2（μg/m³） PM10（μg/m³） CO(mg/m³) O3（μg/m³） PM2.5（μg/m³）
     *
     * @param pollutantValueMap 以因子code为key,浓度为value的 map
     * @param isDay             是否为日平均aqi
     * @return aqi 数据和首要污染物code,超标污染物code 多个的情况下以逗号分隔
     */
    public Map<String, Object> countRealAqi(Map<String, Double> pollutantValueMap, boolean isDay) {
        Map<String, Object> aqiMap = new HashMap<>(5);
        if (MapUtil.isNotEmpty(pollutantValueMap)) {
            // aqi
            double maxAqi = 0;
            // 主要污染物
            List<String> primaryP = new ArrayList<>();
            // 超标污染物
            List<String> exP = new ArrayList<>();
            for (Map.Entry<String, Double> entry : pollutantValueMap.entrySet()) {
                String k = entry.getKey();
                Double v = entry.getValue();
                if (v == null) {
                    continue;
                }
                Double aqi;
                if (isDay) {
                    aqi = PollutantItemsLimitDayEnum.AirAqiCountDayAverage(k, v);
                } else {
                    aqi = PollutantItemsLimitHourEnum.AirAqiCountRealTime(k, v);
                }
                if (aqi == null) {
                    continue;
                }
                if (aqi > maxAqi) {
                    maxAqi = aqi;
                    primaryP.clear();
                }
                if (aqi > ParamConstant.PRIMARY_POLLUTANT_MIN_LIMIT) {
                    primaryP.add(k);
                }
                if (aqi > ParamConstant.EXCESSIVE_POLLUTANT_MIN_LIMIT) {
                    exP.add(k);
                }
            }
            if (maxAqi == 0) {
                return null;
            }
            aqiMap.put(CommonConstant.AQI, maxAqi);
            aqiMap.put(CommonConstant.PRIMARY_POLLUTANT, CollUtil.join(primaryP, ","));
            aqiMap.put(CommonConstant.EXCESSIVE_POLLUTANT, CollUtil.join(exP, ","));
        }
        return aqiMap;
    }
}