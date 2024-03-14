package cn.allbs.utils;

import java.util.Random;

/**
 * 类 RandomUtils 根据权重随机
 */
public class RandomUtils {

    private RandomUtils() {
    }

    /**
     * 带权重的随机决策——基于线性扫描
     * 注意：
     * 1. 时间复杂度O(n)，n = prizePool.length
     * 2. 将权重大的数据靠前，可以减少列表遍历的次数
     *
     * @param prizePool  - “奖品池”，即需要决策的数据
     * @param weightPool - “奖品权重”，即数据所占的权重（决定数据被选中的概率）
     * @param <E>        - 数据类型
     * @return - 随机抽中的“奖品”
     */
    public static <E> E randomDecisionWithWeight(E[] prizePool, int[] weightPool) {
        if (prizePool.length == 0 || prizePool.length != weightPool.length) {
            throw new IllegalArgumentException();
        }

        /*
         * 奖品池、奖品权重：
         *
         *      A      B           C                       D
         *   |-----|-------|---------------|-------------------------------|
         *    1/15    2/15        4/15                    8/15
         *
         *  生成一个[0,15)区间内的随机数x，根据x所处的子区间决定抽取到的奖品：
         *                                     x
         *  |----------------------------------|
         */

        // 计算总权重，确定随机数生成范围
        int weightSum = 0;
        for (int weight : weightPool) {
            if (weight < 0) {
                throw new IllegalArgumentException("权重不允许是负数！");
            }
            weightSum += weight;
        }

        // 抽取数据
        int randomPrizePoint = new Random().nextInt(weightSum);
        E randomPrize = null;
        for (int i = 0; i < weightPool.length; i++) {
            if (randomPrizePoint < weightPool[i]) {
                randomPrize = prizePool[i];
                break;
            } else {
                randomPrizePoint -= weightPool[i];
            }
        }

        return randomPrize;
    }
}
