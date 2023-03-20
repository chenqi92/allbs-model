package cn.allbs.utils;

import cn.allbs.enums.WaterQualityItemEnum;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 水质等级计算
 *
 * @author ChenQi
 */
@UtilityClass
public class WaterGradeUtil {

    /**
     * 根据因子浓度值计算水质等级 浓度值单位为HJ212中缺省计量单位, 默认不为湖泊、水库中水质
     *
     * @param pollutantMap 以HJ212因子编码为key 浓度值为value的map
     * @return 水质等级
     */
    public Integer countWaterGrade(Map<String, Double> pollutantMap) {
        return countWaterGrade(pollutantMap, false);
    }

    /**
     * 根据因子浓度值计算水质等级 浓度值单位为HJ212中缺省计量单位
     *
     * @param pollutantMap      以HJ212因子编码为key 浓度值为value的map
     * @param isLakeOrReservoir 是否检测水库、湖泊中的水质等级
     * @return 水质等级
     */
    public Integer countWaterGrade(Map<String, Double> pollutantMap, boolean isLakeOrReservoir) {
        if (null != pollutantMap && !pollutantMap.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            pollutantMap.forEach((k, v) -> {
                if (v == null) {
                    return;
                }
                Integer grade = WaterQualityItemEnum.waterLevelCount(k, isLakeOrReservoir, v);
                if (grade == null) {
                    return;
                }
                list.add(grade);
            });
            return list.stream().max(Integer::compareTo).orElse(null);
        }
        return null;
    }
}
