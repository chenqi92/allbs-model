package cn.allbs.utils;

import cn.hutool.core.collection.CollUtil;
import lombok.experimental.UtilityClass;

import java.util.List;

/**
 * @author ChenQi
 * @date 2021/4/26
 */
@UtilityClass
public class CommonUtil {

    /**
     * 如果list包括元素将不会重复插入
     *
     * @param list list
     * @param t    元素
     * @param <T>  泛型
     */
    public <T> void notContainAdd(List<T> list, T t) {
        if (!CollUtil.contains(list, t)) {
            list.add(t);
        }
    }
}
