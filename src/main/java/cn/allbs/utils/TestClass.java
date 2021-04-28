package cn.allbs.utils;

import cn.allbs.enums.AqiLevelEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenQi
 * @date 2021/4/25
 */
public class TestClass {

    public static void main(String[] args) {
//        System.out.println("距离：" + LngLatUtil.getDistanceOfMeter(0, 34.22, 135.89, 24.22));
//        int[] stepRange = NumberUtil.range(30, 24, 30);
//        System.out.println(Arrays.asList(stepRange));
//        System.out.println("经纬度" + LngLatUtil.calLocationByDistanceAndLocationAndDirection(47, 116.378972, 39.928879, 100));
//        System.out.println(Math.floor(56.2 / 100));
//        System.out.println(Math.toDegrees(Math.atan2(4, 3)));
//        System.out.println("地面点源：" + GaussUtil.getGaussPlumePointsInFloor(116.378972, 39.928879, 2.9, 30, 0, 10, 1, 45, 1));
//        System.out.println("高架点源：" + GaussUtil.getGaussPlumePointsInElevated(116.378972, 39.928879, 2.9, 120, 30, 2, 49.01, 0, 10, 1, 46, 2));
        System.out.println(AqiLevelEnum.getLevelInfoByAqi(30).getAirLevel());
        Map<String, Double> sMap = new HashMap<>();
        sMap.put("SO2", 602.33);
        sMap.put("NO2", 125.67);
        sMap.put("CO", 3.24);
        sMap.put("O3", 259.67);
        sMap.put("w00100", 100D);
        System.out.println(AqiUtil.countRealAqi(sMap, true));
    }
}
