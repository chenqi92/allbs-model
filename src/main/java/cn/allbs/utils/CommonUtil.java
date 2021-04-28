package cn.allbs.utils;

import cn.allbs.model.SectionBo;
import cn.hutool.core.collection.CollUtil;
import lombok.experimental.UtilityClass;

import java.util.List;

/**
 * @author ChenQi
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

    /**
     * 判断数值是否在区间内
     *
     * @param sectionBo 区间对象
     * @param v         待判断的数值
     * @return 是否在区间内 是-true 否-false
     */
    public boolean inSection(SectionBo sectionBo, double v) {
        if (sectionBo.getMin() == null && v <= sectionBo.getMax()) {
            return true;
        }
        if (sectionBo.getMax() == null && v >= sectionBo.getMin()) {
            return true;
        }
        if (v <= sectionBo.getMax() && v >= sectionBo.getMin()) {
            return true;
        }
        return false;
    }
}
