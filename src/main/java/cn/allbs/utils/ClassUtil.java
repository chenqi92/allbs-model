package cn.allbs.utils;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 获取class 包括父类
 *
 * @author ChenQi
 */
@UtilityClass
public class ClassUtil {

    /**
     * 类域获取
     * <p>
     * 获取当前类包括父类的所有域
     *
     * @param clazz 需要遍历的类
     * @return 所有域
     */
    public Field[] getClassFields(Class<?> clazz) {
        List<Field> list = new ArrayList<>();
        while (clazz != null) {
            list.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return list.toArray(new Field[0]);
    }
}
