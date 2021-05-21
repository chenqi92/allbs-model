package cn.allbs.utils;

import cn.allbs.enums.PollutionGas;
import cn.allbs.enums.PollutionWater;
import lombok.experimental.UtilityClass;

/**
 * 污染因子操作工具
 *
 * @author ChenQi
 */
@UtilityClass
public class PollutantUtil {

    /**
     * 获取气体污染因子的信息
     *
     * @param gasCode 气体污染因子编码
     * @return 该废气的详细信息
     */
    public PollutionGas gasInfo(String gasCode) {
        if (!PollutionGas.GAS_MAP.containsKey(gasCode)) {
            return null;
        }
        return PollutionGas.GAS_MAP.get(gasCode);
    }

    /**
     * 获取水体污染因子信息
     *
     * @param waterCode 废水污染因子编码
     * @return 该废水的详细信息
     */
    public PollutionWater waterInfo(String waterCode) {
        if (!PollutionWater.WATER_MAP.containsKey(waterCode)) {
            return null;
        }
        return PollutionWater.WATER_MAP.get(waterCode);
    }

    /**
     * hj2005国标转为hj2017国标
     *
     * @param oldCode 旧国标编码hj212-2005
     * @return 新国标编码hj212-2017
     */
    public String oldCodeCast(String oldCode) {
        if (PollutionGas.OLD_CAST_MAP.containsKey(oldCode)) {
            return PollutionGas.OLD_CAST_MAP.get(oldCode);
        }
        if (PollutionWater.OLD_CAST_MAP.containsKey(oldCode)) {
            return PollutionWater.OLD_CAST_MAP.get(oldCode);
        }
        return oldCode;
    }
}
