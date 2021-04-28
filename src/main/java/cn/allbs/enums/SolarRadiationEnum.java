package cn.allbs.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 太阳辐射等级
 *
 * @author ChenQi
 */
@Getter
@AllArgsConstructor
public enum SolarRadiationEnum {

    /**
     * 太阳辐射等级
     */
    A("A"), B("B"), C("C"), D("D"), E("E"), F("F"), AB("A~B"), BC("B~C"), CD("C~D"), DE("D~E");

    private final String level;

    /**
     * 获取太阳辐射等级（特纳尔法）
     *
     * @param aLevel 太阳辐射等级
     * @param ws     风速
     * @return 太阳辐射等级
     */
    public static String solarRadiationLevel(Integer aLevel, double ws) {
        Map<Integer, String> solarMap = new HashMap<Integer, String>(9);
        ws = new BigDecimal(ws).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        if (ws <= 1.9) {
            solarMap.put(AtmosphericStabilityEnum.LEVEL1.getLevel(), A.getLevel());
            solarMap.put(AtmosphericStabilityEnum.LEVEL2.getLevel(), AB.getLevel());
            solarMap.put(AtmosphericStabilityEnum.LEVEL3.getLevel(), B.getLevel());
            solarMap.put(AtmosphericStabilityEnum.LEVEL4.getLevel(), D.getLevel());
            solarMap.put(AtmosphericStabilityEnum.LEVEL5.getLevel(), E.getLevel());
            solarMap.put(AtmosphericStabilityEnum.LEVEL6.getLevel(), F.getLevel());
        } else if (ws > 2 && ws <= 2.9) {
            solarMap.put(AtmosphericStabilityEnum.LEVEL1.getLevel(), AB.getLevel());
            solarMap.put(AtmosphericStabilityEnum.LEVEL2.getLevel(), B.getLevel());
            solarMap.put(AtmosphericStabilityEnum.LEVEL3.getLevel(), C.getLevel());
            solarMap.put(AtmosphericStabilityEnum.LEVEL4.getLevel(), D.getLevel());
            solarMap.put(AtmosphericStabilityEnum.LEVEL5.getLevel(), E.getLevel());
            solarMap.put(AtmosphericStabilityEnum.LEVEL6.getLevel(), F.getLevel());
        } else if (ws > 3 && ws <= 4.9) {
            solarMap.put(AtmosphericStabilityEnum.LEVEL1.getLevel(), B.getLevel());
            solarMap.put(AtmosphericStabilityEnum.LEVEL2.getLevel(), BC.getLevel());
            solarMap.put(AtmosphericStabilityEnum.LEVEL3.getLevel(), C.getLevel());
            solarMap.put(AtmosphericStabilityEnum.LEVEL4.getLevel(), D.getLevel());
            solarMap.put(AtmosphericStabilityEnum.LEVEL5.getLevel(), D.getLevel());
            solarMap.put(AtmosphericStabilityEnum.LEVEL6.getLevel(), E.getLevel());
        } else if (ws > 5 && ws <= 5.9) {
            solarMap.put(AtmosphericStabilityEnum.LEVEL1.getLevel(), C.getLevel());
            solarMap.put(AtmosphericStabilityEnum.LEVEL2.getLevel(), CD.getLevel());
            solarMap.put(AtmosphericStabilityEnum.LEVEL3.getLevel(), D.getLevel());
            solarMap.put(AtmosphericStabilityEnum.LEVEL4.getLevel(), D.getLevel());
            solarMap.put(AtmosphericStabilityEnum.LEVEL5.getLevel(), D.getLevel());
            solarMap.put(AtmosphericStabilityEnum.LEVEL6.getLevel(), D.getLevel());
        } else if (ws >= 6) {
            solarMap.put(AtmosphericStabilityEnum.LEVEL1.getLevel(), C.getLevel());
            solarMap.put(AtmosphericStabilityEnum.LEVEL2.getLevel(), D.getLevel());
            solarMap.put(AtmosphericStabilityEnum.LEVEL3.getLevel(), D.getLevel());
            solarMap.put(AtmosphericStabilityEnum.LEVEL4.getLevel(), D.getLevel());
            solarMap.put(AtmosphericStabilityEnum.LEVEL5.getLevel(), D.getLevel());
            solarMap.put(AtmosphericStabilityEnum.LEVEL6.getLevel(), D.getLevel());
        }
        return solarMap.get(aLevel);
    }
}
